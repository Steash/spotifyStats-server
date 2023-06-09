package com.steash.spotifyStats.mappers;

import com.steash.spotifyStats.domains.User;
import com.steash.spotifyStats.dtos.user.SaveUserDto;
import com.steash.spotifyStats.dtos.user.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {

    public User dtoToUser(SaveUserDto saveUserDto){
        /*
         * Used to create a User object from a SaveUserDto object
         */
        User user = new User();
        user.setDisplayName(saveUserDto.getDisplayName());
        user.setSpotifyId(saveUserDto.getSpotifyId());
        user.setEmail(saveUserDto.getEmail());
        user.setCountry(saveUserDto.getCountry());
        user.setAvatar(saveUserDto.getAvatar());
        user.setProduct(saveUserDto.getProduct());
        user.setAccessToken(saveUserDto.getAccessToken());
        user.setRefreshToken(saveUserDto.getRefreshToken());

        return user;
    }

    public UserDto userToDto(User user){
        /*
         * Used to create a UserDto object from a User object
         */
        UserDto userDto = new UserDto();

        userDto.setDisplayName(user.getDisplayName());
        userDto.setSpotifyId(user.getSpotifyId());
        userDto.setEmail(user.getEmail());
        userDto.setCountry(user.getCountry());
        userDto.setAvatar(user.getAvatar());
        userDto.setProduct(user.getProduct());

        return userDto;
    }
}
