package com.steash.spotifyStats.mappers;
import com.steash.spotifyStats.controllers.ArtistController;
import com.steash.spotifyStats.controllers.TopArtistController;
import com.steash.spotifyStats.domains.Artist;
import com.steash.spotifyStats.domains.TopArtist;
import com.steash.spotifyStats.domains.User;
import com.steash.spotifyStats.dtos.artist.ArtistDto;
import com.steash.spotifyStats.dtos.artist.SaveArtistDto;
import com.steash.spotifyStats.dtos.topArtist.SaveTopArtistDto;
import com.steash.spotifyStats.dtos.topArtist.TopArtistDto;
import com.steash.spotifyStats.repositories.IArtistRepository;
import com.steash.spotifyStats.repositories.ITopArtistRepository;
import com.steash.spotifyStats.repositories.IUserRepository;
import com.steash.spotifyStats.service.SpotifyApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class TopArtistDtoMapper {
    @Autowired
    private IArtistRepository artistRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ArtistDtoMapper artistMapper;

    @Autowired
    private ITopArtistRepository topArtistRepository;

    private ArtistController artistController;

    private SpotifyApiService spotifyApiService;
    public TopArtist dtoToTopArtist(SaveTopArtistDto saveTopArtistDto) throws IOException {
        /*
         * Used to create a TopArtist object from a SaveTopArtist object
         */
        TopArtist topArtist = new TopArtist();

        // Get Artist object from Artist Spotify ID
        Optional<Artist> optionalArtist = artistRepository.findBySpotifyId(saveTopArtistDto.getArtistSpotifyId());

        // Create Artist object if it doesn't already exist
        if (optionalArtist.isEmpty()) {

            // Call spotify service to retrieve artistDto
            SaveArtistDto saveArtistDto = spotifyApiService.getArtistJson(
                    saveTopArtistDto.getArtistSpotifyId(), saveTopArtistDto.getAccessToken()
            );

            // Create an Artist object from the retrieved artistDto
//            artistController.createArtist(saveArtistDto);
            Artist artist = artistMapper.dtoToArtist(saveArtistDto);
            artistRepository.save(artist);

            optionalArtist = artistRepository.findBySpotifyId(saveTopArtistDto.getArtistSpotifyId());
//            return null;
        }

        // Get User object from User ID
        Optional<User> optionalUser = userRepository.findById(saveTopArtistDto.getUserId());
        if (optionalUser.isEmpty()) {
            return null;
        }

        topArtist.setArtist(optionalArtist.get());
        topArtist.setRank(saveTopArtistDto.getRank());
        topArtist.setUser(optionalUser.get());

//        topArtistRepository.save(topArtist);
        return topArtist;
    }

    public TopArtistDto topArtistToDto(TopArtist topArtist){
        /*
         * Used to create a TopArtistDto object from a TopArtist object
         */

        TopArtistDto topArtistDto = new TopArtistDto();

        topArtistDto.setId(topArtist.getId());
        topArtistDto.setArtistName(topArtist.getArtist().getName());
        topArtistDto.setArtistSpotifyId(topArtist.getArtist().getSpotifyId());
        topArtistDto.setRank(topArtist.getRank());
        topArtistDto.setUserDisplayName(topArtist.getUser().getDisplayName());
        topArtistDto.setUserId(topArtist.getUser().getId());

        return topArtistDto;
    }
}
