package com.steash.spotifyStats.mappers;

import com.steash.spotifyStats.domains.Artist;
import com.steash.spotifyStats.dtos.artist.ArtistDto;
import com.steash.spotifyStats.dtos.artist.SaveArtistDto;
import com.steash.spotifyStats.dtos.topArtist.SaveTopArtistDto;
import org.springframework.stereotype.Component;

@Component
public class TopArtistToArtistMapper {
    public Artist TopArtistDtoArtist(SaveTopArtistDto saveTopArtistDto){
        /*
         * Used to create a Artist object from a SaveArtistDto object
         */
        Artist artist = new Artist();
        artist.setSpotifyId(saveTopArtistDto.getArtistSpotifyId());
        artist.setName(saveTopArtistDto.getArtistName());
        artist.setAvatar(saveTopArtistDto.getAvatar());

        return artist;
    }
}