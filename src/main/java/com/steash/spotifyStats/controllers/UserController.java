package com.steash.spotifyStats.controllers;

import com.steash.spotifyStats.domains.Artist;
import com.steash.spotifyStats.domains.TopArtist;
import com.steash.spotifyStats.domains.User;
import com.steash.spotifyStats.dtos.topArtist.UserTopArtistDto;
import com.steash.spotifyStats.dtos.user.*;
import com.steash.spotifyStats.mappers.CompactUserMapper;
import com.steash.spotifyStats.mappers.MusicBuddyDtoMapper;
import com.steash.spotifyStats.mappers.UserDtoMapper;
import com.steash.spotifyStats.mappers.UserTopArtistMapper;
import com.steash.spotifyStats.repositories.IUserRepository;
import com.steash.spotifyStats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDtoMapper userMapper;

    @Autowired
    private CompactUserMapper compactUserMapper;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserTopArtistMapper userTopArtistMapper;

    @Autowired
    private MusicBuddyDtoMapper musicBuddyDtoMapper;

    @GetMapping("/get")
    public Stream<UserDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::userToDto);
    }

    @GetMapping("/get/{spotifyId}")
    public Optional<UserDto> find(@PathVariable String spotifyId) {
        return Optional.of(userMapper.userToDto(userRepository.findBySpotifyId(spotifyId).get()));
    }

    //    @GetMapping("/get/{id}/topArtists")
//    public List<TopArtist> getTopArtists(@PathVariable long id) {
//        return userService.getAllTopArtistsForUser((id));
//    }
    @GetMapping("/get/{mySpotifyId}/isFriend/{friendId}")
    public ResponseEntity<?> isFriend(@PathVariable String mySpotifyId,
                                       @PathVariable String friendId)  {
        User me = userRepository.findBySpotifyId(mySpotifyId).orElse(null);
        User friend = userRepository.findBySpotifyId(friendId).orElse(null);

        if (me == friend) {
            return ResponseEntity.badRequest().body("Both users are the same.");
        }

        boolean usersFound = me != null && friend != null;

        if (usersFound) {
            if (me.getFriends().contains(friend)) {
                return ResponseEntity.ok().body(true);
            }
            return ResponseEntity.ok().body(false);
        }

        return ResponseEntity.notFound().build();

    }

    @GetMapping("/get/{spotifyId}/friends")
    public ResponseEntity<?> getFriends(@PathVariable String spotifyId) {
        Optional<User> userOptional = userRepository.findBySpotifyId(spotifyId);

        if (userOptional.isPresent()) {
            List<CompactUserDto> friends = userOptional.get().getFriends()
                    .stream()
                    .map(compactUserMapper::compactUserDto)
                    .toList();

            if (friends.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("An empty friend list was found for user: " + spotifyId);
            }

            return ResponseEntity.ok(friends);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/get/{spotifyId}/received-friend-requests")
    public ResponseEntity<?> getReceivedFriendRequests(@PathVariable String spotifyId) {
        Optional<User> userOptional = userRepository.findBySpotifyId(spotifyId);

        if (userOptional.isPresent()) {
            List<CompactUserDto> receivedFriendRequests = userOptional.get().getReceivedFriendRequests()
                    .stream().map(compactUserMapper::compactUserDto)
                    .toList();

            if (receivedFriendRequests.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No friend requests were found for user: " + spotifyId);
            }

            return ResponseEntity.ok(receivedFriendRequests);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/get/{spotifyId}/sent-friend-requests")
    public ResponseEntity<?> getSentFriendRequests(@PathVariable String spotifyId) {
        User user = userRepository.findBySpotifyId(spotifyId).orElse(null);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        List<CompactUserDto> sentFriendRequests = userRepository.findAll()
                .stream()
                .filter(friend -> friend.getReceivedFriendRequests().contains(user))
                .map(compactUserMapper::compactUserDto)
                .toList();

        if (sentFriendRequests.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(spotifyId + " has no sent friend requests.");
        }

        return ResponseEntity.ok(sentFriendRequests);
    }

    @GetMapping("get/music-buddies/{spotifyId}")
    public ResponseEntity<?> getMusicBuddies(@PathVariable String spotifyId) {
        User me = userRepository.findBySpotifyId(spotifyId).orElse(null);

        if (me == null) {
            return ResponseEntity.notFound().build();
        }

        List<TopArtist> myTopArtists = me.getTopArtists();
        List<User> myFriends = me.getFriends();
        List<MusicBuddyDto> musicBuddies = new ArrayList<>();;

        // Loop through my Friends
        for (User friend : myFriends ) {
            List<UserTopArtistDto> mutualTopArtists = new ArrayList<>();;
            List<TopArtist> topArtists = friend.getTopArtists();

            // Check for each friend if they have matching topArtists
            for (TopArtist theirTopArtist : topArtists) {
                for (TopArtist myTopArtist : myTopArtists) {
                    if (myTopArtist.getArtist().equals(theirTopArtist.getArtist())) {
                        // Add the topArtists to the list of matching topArtists
                        mutualTopArtists.add(userTopArtistMapper.userTopArtistDto(theirTopArtist));
                    }
                }
            }

            if (!mutualTopArtists.isEmpty()) {
                musicBuddies.add(musicBuddyDtoMapper.saveMusicBuddyDto(friend, mutualTopArtists));
            }
        }

        if (musicBuddies.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No common top artists were found with anyone on your friends list.");
        }

        // Sort musicBuddies in descending order on mutualTopArtist field
        Comparator<MusicBuddyDto> comparator = Comparator.comparingInt(dto -> dto.getMutualTopArtists().size());
        comparator = comparator.reversed();
        Collections.sort(musicBuddies, comparator);

//        Collections.sort(musicBuddies, Compara);

        return ResponseEntity.ok().body(musicBuddies);
    }

    @PostMapping("/search")
    public ResponseEntity<SearchRspDto<UserDto>> getUserSearchResult(@RequestBody SearchReqDto searchReqDto) {
        Pageable pageable = PageRequest.of(
                searchReqDto.getPageNumber(),
                searchReqDto.getNumberPerPage(),
                Sort.by(Sort.Direction.fromString(searchReqDto.getDirectionOfSort()),
                        searchReqDto.getPropertyToSortBy())
        );

        Page<User> page = userRepository.findByDisplayNameContainingOrSpotifyIdContainingOrEmailContaining(
                searchReqDto.getSearchTerm(), searchReqDto.getSearchTerm(), searchReqDto.getSearchTerm(), pageable
        );

        if (page.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        SearchRspDto<UserDto> responseDto = new SearchRspDto<>(
                searchReqDto.getNumberPerPage(),
                page.getTotalPages(),
                page.getNumberOfElements(),
                page.getContent().stream()
                        .map(userMapper::userToDto).toList()
        );

        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping("/create")
    public void create(@RequestBody SaveUserDto saveUserDto) {
        User user = userMapper.dtoToUser(saveUserDto);

        userRepository.save(user);
    }

    @PostMapping("/{receiverId}/accept-friend-request/{senderId}")
    public ResponseEntity confirmFriend(@PathVariable String receiverId,
                                        @PathVariable String senderId) {
        User receiver = userRepository.findBySpotifyId(receiverId).orElse(null);
        User sender = userRepository.findBySpotifyId(senderId).orElse(null);

        if (receiver == null && sender == null) {
            return ResponseEntity.notFound().build();
        }

        List<User> receivedFriendRequests = receiver.getReceivedFriendRequests();

        if (!receivedFriendRequests.contains(sender)) {
            return ResponseEntity.badRequest().body("Friend request not found");
        }

        boolean friendAlreadyExists = !receiver.acceptFriend(sender);

        if (friendAlreadyExists) {
            return ResponseEntity.badRequest().body("Friend already exists.");
        }

        receivedFriendRequests.remove(sender);

        userRepository.save(receiver);

        return ResponseEntity.ok("Friend added successfully.");
    }

    @PutMapping(("/{receiverId}/reject-friend-request/{senderId}"))
    public ResponseEntity rejectFriend(@PathVariable String senderId,
                                       @PathVariable String receiverId) {
        User sender = userRepository.findBySpotifyId(senderId).orElse(null);
        User receiver = userRepository.findBySpotifyId(receiverId).orElse(null);

        if (receiver == null && sender == null) {
            return ResponseEntity.notFound().build();
        }

        List<User> receivedFriendRequests = receiver.getReceivedFriendRequests();

        if (!receivedFriendRequests.contains(sender)) {
            return ResponseEntity.badRequest().body("Friend request not found");
        }

        receivedFriendRequests.remove(sender);

        userRepository.save(receiver);

        return ResponseEntity.ok("Friend request successfully rejected.");
    }

    @PostMapping("/{senderId}/addFriend/{receiverId}")
    public ResponseEntity sendFriendRequest(@PathVariable String senderId,
                                            @PathVariable String receiverId) {
        User sender = userRepository.findBySpotifyId(senderId).orElse(null);
        User receiver = userRepository.findBySpotifyId(receiverId).orElse(null);

        boolean sameUser = sender == receiver;

        if (sameUser) {
            return ResponseEntity.badRequest().body("You cannot add yourself to your friends list.");
        }

        boolean usersFound = sender != null && receiver != null;

        if (usersFound) {
            if (receiver.getFriends().contains(sender)) {
                return ResponseEntity.badRequest().body("You are already friends.");
            }

            List<User> receivedFriendRequests = receiver.getReceivedFriendRequests();

            boolean requestAlreadySent = !receiver.addToReceivedFriendRequest(sender);

            if (requestAlreadySent) {
                return ResponseEntity.badRequest().body("Request already sent.");
            }

            if (sender.getReceivedFriendRequests().contains(receiver)) {
                sender.acceptFriend(receiver);
                return ResponseEntity.ok(receiver + " already sent you a request. " + receiver + " was added to your friends.");
            }

            userRepository.save(receiver);

            return ResponseEntity.ok("Request sent successfully.");
        }
        return ResponseEntity.notFound().build();
    }

    // Finds user by Spotify ID and sets its new tokens
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        // Find User
        Optional<User> userOptional = userRepository.findBySpotifyId(
                loginRequestDto.getSpotifyId()
        );

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Save new tokens to User
            user.setAccessToken(loginRequestDto.getAccessToken());
            user.setRefreshToken(loginRequestDto.getRefreshToken());
            userRepository.save(user);

            return ResponseEntity.ok().body(new LoginResponseDto(user.getSpotifyId()));
        }

        return ResponseEntity.noContent().build();
    }

    // Finds user by its User ID and clears its tokens
    @PutMapping("/logout")
    public ResponseEntity logout(@RequestBody LogoutRequestDto logoutRequestDto) {
        // Find User
        Optional<User> userOptional = userRepository.findBySpotifyId(logoutRequestDto.getUserSpotifyId());

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Clear tokens
            user.setAccessToken(null);
            user.setRefreshToken(null);
            userRepository.save(user);

            return ResponseEntity.ok("Successfully logged out");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{currentUserId}/delete-friend/{friendId}")
    public ResponseEntity deleteFriend(@PathVariable String currentUserId,
                                       @PathVariable String friendId) {
        User currentUser = userRepository.findBySpotifyId(currentUserId).orElse(null);
        User friend = userRepository.findBySpotifyId(friendId).orElse(null);

        if (currentUser == null && friend == null) {
            return ResponseEntity.notFound().build();
        }

        List<User> friendslist = currentUser.getFriends();

        if (!friendslist.contains(friend)) {
            return ResponseEntity.badRequest().body("You do not have user: " + friendId + " on your friends list.");
        }

        currentUser.deleteFriend(friend);
        userRepository.save(currentUser);

        return ResponseEntity.ok("Friend " + friendId + " successfully deleted.");
    }

    @DeleteMapping("/{currentUserId}/retract-friend-request/{friendId}")
    public ResponseEntity retractFriendRequest(@PathVariable String currentUserId,
                                               @PathVariable String friendId) {
        User currentUser = userRepository.findBySpotifyId(currentUserId).orElse(null);
        User friend = userRepository.findBySpotifyId(friendId).orElse(null);

        if (currentUser == null && friend == null) {
            return ResponseEntity.notFound().build();
        }

        List<User> receivedFriendRequests = friend.getReceivedFriendRequests();

        if (!receivedFriendRequests.contains(currentUser)) {
            return ResponseEntity.badRequest().body(friendId + " has not received a friend request from " + currentUserId + ".");
        }

        friend.removeFriendRequestFrom(currentUser);
        userRepository.save(friend);

        return ResponseEntity.ok("Friend request to " + friendId + " has successfully been retracted.");
    }
}
