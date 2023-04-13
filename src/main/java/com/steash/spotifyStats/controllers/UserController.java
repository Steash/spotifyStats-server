package com.steash.spotifyStats.controllers;

import com.steash.spotifyStats.domains.User;
import com.steash.spotifyStats.dtos.user.LoginRequestDto;
import com.steash.spotifyStats.dtos.user.LoginResponseDto;
import com.steash.spotifyStats.dtos.user.SaveUserDto;
import com.steash.spotifyStats.dtos.user.UserDto;
import com.steash.spotifyStats.mappers.UserDtoMapper;
import com.steash.spotifyStats.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/get")
    public Stream<UserDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::userToDto);
    }

    @GetMapping("/get/{id}")
    public Optional<UserDto> find(@PathVariable long id) {
        return Optional.of(userMapper.userToDto(userRepository.findById(id).get()));
    }

    @PostMapping("/create")
    public void create(@RequestBody SaveUserDto saveUserDto) {
        User user = userMapper.dtoToUser(saveUserDto);

        userRepository.save(user);
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
      Optional<User> userOptional = userRepository.findBySpotifyId(loginRequestDto.getSpotifyId());

      User user = userOptional.get();

      if (userOptional.isPresent()) {
          // Save new tokens to User
          user.setAccessToken(loginRequestDto.getAccessToken());
          user.setRefreshToken(loginRequestDto.getRefreshToken());
          userRepository.save(user);

          return new LoginResponseDto(user.getId());
      }

      return null;
    }

    @PutMapping("/logout")
    public boolean deleteUserToken(@RequestHeader("Authentication") String accessToken) {
        Optional<User> userOptional = userRepository.findByAccessToken(accessToken);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Delete tokens
            user.setAccessToken(null);
            user.setRefreshToken(null);
            userRepository.save(user);

            return true;
        }
        return false;
    }
}
