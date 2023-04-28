package com.steash.spotifyStats.controllers;

import com.steash.spotifyStats.domains.Artist;
import com.steash.spotifyStats.domains.TopArtist;
import com.steash.spotifyStats.domains.User;
import com.steash.spotifyStats.dtos.artist.SaveArtistDto;
import com.steash.spotifyStats.dtos.topArtist.SaveTopArtistDto;
import com.steash.spotifyStats.dtos.topArtist.TopArtistDto;
import com.steash.spotifyStats.dtos.topArtist.TopArtistUserDto;
import com.steash.spotifyStats.dtos.topArtist.UserTopArtistDto;
import com.steash.spotifyStats.dtos.user.SaveUserDto;
import com.steash.spotifyStats.dtos.user.UserDto;
import com.steash.spotifyStats.mappers.*;
import com.steash.spotifyStats.repositories.IArtistRepository;
import com.steash.spotifyStats.repositories.ITopArtistRepository;
import com.steash.spotifyStats.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/topArtist")
public class TopArtistController {
    @Autowired
    private TopArtistDtoMapper topArtistDtoMapper;
    @Autowired
    private ITopArtistRepository topArtistRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ArtistDtoMapper artistDtoMapper;

    @Autowired
    private IArtistRepository artistRepository;

    @Autowired
    private TopArtistToArtistMapper topArtistToArtistMapper;

    @Autowired
    private UserTopArtistMapper userTopArtistMapper;

    @Autowired
    private TopArtistToUserMapper topArtistToUserMapper;

    @GetMapping("/get")
    public Stream<TopArtistDto> findAll() {
        return topArtistRepository.findAll().stream().map(topArtistDtoMapper::topArtistToDto);
    }

    @GetMapping("/get/{id}")
    public Optional<TopArtistDto> find(@PathVariable long id) {
        return Optional.of(topArtistDtoMapper.topArtistToDto(topArtistRepository.findById(id).get()));
    }

    @GetMapping("/get/user/{userId}")
    public Stream<UserTopArtistDto> findMyTopArtists(@PathVariable long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return topArtistRepository.findAllByUser(user).stream().map(userTopArtistMapper::userTopArtistDto);
//        return Optional.of(userTopArtistMapper.userTopArtistDto(userRepository.findById(userId).get()));
    }

    @GetMapping("/get/mutual-fans")
    public ResponseEntity<Stream<TopArtistUserDto>> findCommonFans(@RequestParam String artistSpotifyId, @RequestParam String userSpotifyId) {
        Optional<Artist> artist = artistRepository.findBySpotifyId(artistSpotifyId);
        Optional<User> user = userRepository.findBySpotifyId(userSpotifyId);
        if (!artist.isPresent() || !user.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        List<TopArtist> topArtists = topArtistRepository.findAllByArtistAndUserNot(artist.get(), user.get());
        if (topArtists.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Stream<TopArtistUserDto> usersWithSameTopArtist = topArtists.stream()
                .map(topArtistToUserMapper::topArtistUserDto);

        return ResponseEntity.ok(usersWithSameTopArtist);
    }

    @PostMapping("/create")
    public void create(@RequestBody SaveTopArtistDto saveTopArtistDto) {
        TopArtist topArtist = topArtistDtoMapper.dtoToTopArtist(saveTopArtistDto);

        topArtistRepository.save(topArtist);
    }

    @PostMapping("/create/multiple")
    public ResponseEntity createTopArtists(@RequestBody SaveTopArtistDto[] saveTopArtistDtos) {
        // Create array of all non-existing artists
        List<Artist> artistsToAdd = Arrays.stream(saveTopArtistDtos)
                .map(topArtistToArtistMapper::TopArtistDtoArtist)
                .filter(artist -> artistRepository.findBySpotifyId(artist.getSpotifyId()).isEmpty())
                .collect(Collectors.toList());

        // Insert this array into database
        if (!artistsToAdd.isEmpty()) {
            artistRepository.saveAll(artistsToAdd);
        }

        // Clear current TopArtists
        if (topArtistRepository.count() > 0) {
            topArtistRepository.deleteAll();
        }

        // Create array of Top Artists To Add
        List<TopArtist> topArtistsToAdd = Arrays.stream(saveTopArtistDtos)
                .map(topArtistDtoMapper::dtoToTopArtist)
                .collect(Collectors.toList());

        topArtistRepository.saveAll(topArtistsToAdd);

        return ResponseEntity.ok("Top artists added successfully.");
    }



}
