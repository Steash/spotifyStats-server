package com.steash.spotifyStats.controllers;

import com.steash.spotifyStats.domains.User;
import com.steash.spotifyStats.dtos.user.*;
import com.steash.spotifyStats.mappers.UserDtoMapper;
import com.steash.spotifyStats.repositories.IUserRepository;
import com.steash.spotifyStats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Stream;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDtoMapper userMapper;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private UserService userService;

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

//        return page.isEmpty() ? Page.empty() : new PageImpl<>(
//                page.getContent(),
//                pageable,
//                page.getTotalElements()
//        );

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
}
