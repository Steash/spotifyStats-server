package com.steash.spotifyStats.mappers;

import com.steash.spotifyStats.domains.TopArtist;
import com.steash.spotifyStats.domains.User;
import com.steash.spotifyStats.dtos.topArtist.TopArtistUserDto;
import com.steash.spotifyStats.dtos.topArtist.UserTopArtistDto;
import com.steash.spotifyStats.dtos.user.MusicBuddyDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MusicBuddyDtoMapper {
    UserTopArtistMapper userTopArtistMapper;

    public MusicBuddyDto saveMusicBuddyDto(User user, List<UserTopArtistDto> topArtists){
        /*
         * Used to create a MusicBuddyDto object from a User object and a List of TopArtists
         */

        MusicBuddyDto musicBuddyDto = new MusicBuddyDto();

        musicBuddyDto.setMutualTopArtists(topArtists);
        musicBuddyDto.setSpotifyId(user.getSpotifyId());
        musicBuddyDto.setDisplayName(user.getDisplayName());
        musicBuddyDto.setAvatar(user.getAvatar());

        return musicBuddyDto;
    }
}
