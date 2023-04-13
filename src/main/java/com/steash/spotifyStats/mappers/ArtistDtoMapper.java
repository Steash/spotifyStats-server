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
        artist.setName(saveArtistDto.getName());
        artist.setSpotifyId(saveArtistDto.getSpotifyId());

        return artist;
    }

    public ArtistDto artistToDto(Artist artist){
        /*
         * Used to create a ArtistDto object from a Artist object
         */
        ArtistDto artistDto = new ArtistDto();

        artistDto.setId(artist.getId());
        artistDto.setName(artist.getName());
        artistDto.setSpotifyId(artist.getSpotifyId());

        return artistDto;
    }


}
