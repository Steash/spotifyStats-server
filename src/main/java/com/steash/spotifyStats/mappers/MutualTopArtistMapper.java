package com.steash.spotifyStats.mappers;

import com.steash.spotifyStats.domains.Artist;
import com.steash.spotifyStats.domains.TopArtist;
import com.steash.spotifyStats.dtos.topArtist.MutualTopArtistDto;
import com.steash.spotifyStats.dtos.topArtist.SaveTopArtistDto;
import com.steash.spotifyStats.dtos.topArtist.TopArtistUserDto;
import org.springframework.stereotype.Component;

@Component
public class MutualTopArtistMapper {
    public MutualTopArtistDto mutualTopArtistDto(TopArtist topArtist){
        /*
         * Used to create a MutualTopArtistDto object from a TopArtist object
         */

        MutualTopArtistDto mutualTopArtistDto = new MutualTopArtistDto();

        mutualTopArtistDto.setArtistName(topArtist.getArtist().getName());
        mutualTopArtistDto.setArtistSpotifyId(topArtist.getArtist().getSpotifyId());
        mutualTopArtistDto.setTopArtistRank(topArtist.getRank());
        mutualTopArtistDto.setArtistAvatar(topArtist.getArtist().getAvatar());
        mutualTopArtistDto.setUserSpotifyId(topArtist.getUser().getSpotifyId());

        return mutualTopArtistDto;
    }
}
