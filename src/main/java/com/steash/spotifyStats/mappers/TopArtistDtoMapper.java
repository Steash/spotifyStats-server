package com.steash.spotifyStats.mappers;
import com.steash.spotifyStats.domains.TopArtist;
import com.steash.spotifyStats.dtos.topArtist.SaveTopArtistDto;
import com.steash.spotifyStats.dtos.topArtist.TopArtistDto;
import org.springframework.stereotype.Component;

@Component
public class TopArtistDtoMapper {
    public TopArtist dtoToTopArtist(SaveTopArtistDto saveTopArtistDto){
        /*
         * Used to create a TopArtist object from a SaveTopArtist object
         */
        TopArtist topArtist = new TopArtist();

        topArtist.setArtist(saveTopArtistDto.getArtist());
        topArtist.setRank(saveTopArtistDto.getRank());
        topArtist.setUser(saveTopArtistDto.getUser());

        return topArtist;
    }

    public TopArtistDto topArtistToDto(TopArtist topArtist){
        /*
         * Used to create a TopArtistDto object from a TopArtist object
         */
        TopArtistDto topArtistDto = new TopArtistDto();

        topArtistDto.setId(topArtist.getId());
        topArtistDto.setArtist(topArtist.getArtist());
        topArtistDto.setRank(topArtistDto.getRank());
        topArtistDto.setUser(topArtistDto.getUser());

        return topArtistDto;
    }
}
