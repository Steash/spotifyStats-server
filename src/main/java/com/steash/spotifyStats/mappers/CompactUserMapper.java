package com.steash.spotifyStats.mappers;

import com.steash.spotifyStats.domains.User;
import com.steash.spotifyStats.dtos.user.CompactUserDto;
import com.steash.spotifyStats.dtos.user.UserDto;
import org.springframework.stereotype.Component;

@Component
public class CompactUserMapper {
    public CompactUserDto compactUserDto(User user){
        /*
         * Used to create a UserDto object from a User object
         */
        CompactUserDto compactUserDto = new CompactUserDto();

        compactUserDto.setDisplayName(user.getDisplayName());
        compactUserDto.setSpotifyId(user.getSpotifyId());
        compactUserDto.setAvatar(user.getAvatar());

        return compactUserDto;
    }

}
