package com.steash.spotifyStats.mappers;

import com.steash.spotifyStats.domains.TopArtist;
import com.steash.spotifyStats.dtos.topArtist.TopArtistUserDto;
import org.springframework.stereotype.Component;

@Component
public class TopArtistToUserMapper {

    public TopArtistUserDto topArtistUserDto(TopArtist topArtist){
        /*
         * Used to create a TopArtistUserDto object from a TopArtist object
         */

        TopArtistUserDto topArtistUserDto = new TopArtistUserDto();

        topArtistUserDto.setUserSpotifyId(topArtist.getUser().getSpotifyId());
        topArtistUserDto.setDisplayName(topArtist.getUser().getDisplayName());
        topArtistUserDto.setAvatar(topArtist.getUser().getAvatar());

        return topArtistUserDto;
    }
}
