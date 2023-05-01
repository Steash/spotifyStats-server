package com.steash.spotifyStats.mappers;

import com.steash.spotifyStats.domains.TopArtist;
import com.steash.spotifyStats.dtos.topArtist.TopArtistDto;
import com.steash.spotifyStats.dtos.topArtist.UserTopArtistDto;
import org.springframework.stereotype.Component;

@Component
public class UserTopArtistMapper {

    public UserTopArtistDto userTopArtistDto(TopArtist topArtist){
        /*
         * Used to create a UserTopArtistDto object from a TopArtist object
         */

        UserTopArtistDto userTopArtistDto = new UserTopArtistDto();

        userTopArtistDto.setArtistName(topArtist.getArtist().getName());
        userTopArtistDto.setArtistSpotifyId(topArtist.getArtist().getSpotifyId());
        userTopArtistDto.setRank(topArtist.getRank());
        userTopArtistDto.setAvatar(topArtist.getArtist().getAvatar());

        return userTopArtistDto;
    }
}
