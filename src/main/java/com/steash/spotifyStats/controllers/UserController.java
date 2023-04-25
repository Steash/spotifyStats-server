package com.steash.spotifyStats.controllers;

import com.steash.spotifyStats.domains.TopArtist;
import com.steash.spotifyStats.domains.User;
import com.steash.spotifyStats.dtos.user.*;
import com.steash.spotifyStats.mappers.UserDtoMapper;
import com.steash.spotifyStats.repositories.IUserRepository;
import com.steash.spotifyStats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/get/{id}")
    public Optional<UserDto> find(@PathVariable long id) {
        return Optional.of(userMapper.userToDto(userRepository.findById(id).get()));
    }

//    @GetMapping("/get/{id}/topArtists")
//    public List<TopArtist> getTopArtists(@PathVariable long id) {
//        return userService.getAllTopArtistsForUser((id));
//    }

    @PostMapping("/create")
    public void create(@RequestBody SaveUserDto saveUserDto) {
        User user = userMapper.dtoToUser(saveUserDto);

        userRepository.save(user);
    }

    // Finds user by Spotify ID and sets its new tokens
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
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

            return new LoginResponseDto(user.getId());
        }

      return null;
    }
    // Finds user by its User ID and clears its tokens
    @PutMapping("/logout")
    public boolean logout(@RequestBody LogoutRequestDto logoutRequestDto) {
        // Find User
        Optional<User> userOptional = userRepository.findById(logoutRequestDto.getUserId());

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Clear tokens
            user.setAccessToken(null);
            user.setRefreshToken(null);
            userRepository.save(user);

            return true;
        }
        return false;
    }
}
