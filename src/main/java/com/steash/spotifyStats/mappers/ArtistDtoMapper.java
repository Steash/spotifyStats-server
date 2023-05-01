package com.steash.spotifyStats.mappers;

import com.steash.spotifyStats.domains.Artist;
import com.steash.spotifyStats.dtos.artist.ArtistDto;
import com.steash.spotifyStats.dtos.artist.SaveArtistDto;
import org.springframework.stereotype.Component;

@Component
public class ArtistDtoMapper {


    public Artist dtoToArtist(SaveArtistDto saveArtistDto){
        /*
         * Used to create a Artist object from a SaveArtistDto object
         */
        Artist artist = new Artist();
        artist.setSpotifyId(saveArtistDto.getSpotifyId());
        artist.setName(saveArtistDto.getName());
        artist.setAvatar(saveArtistDto.getAvatar());

        return artist;
    }

    public ArtistDto artistToDto(Artist artist){
        /*
         * Used to create a ArtistDto object from a Artist object
         */
        ArtistDto artistDto = new ArtistDto();

        artistDto.setSpotifyId(artist.getSpotifyId());
        artistDto.setName(artist.getName());
        artistDto.setAvatar(artist.getAvatar());

        return artistDto;
    }
}
