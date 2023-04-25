package com.steash.spotifyStats.controllers;

import com.steash.spotifyStats.domains.Artist;
import com.steash.spotifyStats.domains.TopArtist;
import com.steash.spotifyStats.dtos.artist.ArtistDto;
import com.steash.spotifyStats.dtos.artist.SaveArtistDto;
import com.steash.spotifyStats.dtos.topArtist.SaveTopArtistDto;
import com.steash.spotifyStats.dtos.topArtist.TopArtistDto;
import com.steash.spotifyStats.mappers.ArtistDtoMapper;
import com.steash.spotifyStats.repositories.IArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/artist")
public class ArtistController {
    @Autowired
    IArtistRepository artistRepository;

    @Autowired
    ArtistDtoMapper artistMapper;

    @Autowired
    JdbcTemplate jdbcTemplate;


    @GetMapping("/get")
    public Stream<ArtistDto> findAll() {
        return artistRepository.findAll().stream().map(artistMapper::artistToDto);
    }

    @GetMapping("/get/{spotifyId}")
    public Optional<ArtistDto> findBySpotifyId(@PathVariable String spotifyId) {
        return Optional.of(artistMapper.artistToDto(artistRepository.findBySpotifyId(spotifyId).get()));
    }

    @PostMapping("/create/artists")
    public ResponseEntity createArtists(@RequestBody SaveArtistDto[] saveArtistDtos) {
        // Create array of all non-existing artists
        List<Artist> artistsToAdd = Arrays.stream(saveArtistDtos)
                .map(artistMapper::dtoToArtist)
                .filter(artist -> artistRepository.findBySpotifyId(artist.getSpotifyId()).isEmpty())
                .collect(Collectors.toList());

        // Insert this array into database
        if (!artistsToAdd.isEmpty()) {
            artistRepository.saveAll(artistsToAdd);
            return ResponseEntity.ok("Artists added successfully.");
        }

        return ResponseEntity.ok("All artists already exist in the database.");
    }


    @PostMapping("/create")
    public void create(@RequestBody SaveArtistDto saveArtistDto) {
        Artist artist = artistMapper.dtoToArtist(saveArtistDto);
        artistRepository.save(artist);
    }

//    public void createArtist(SaveArtistDto saveArtistDto) {
//        Artist artist = artistMapper.dtoToArtist(saveArtistDto);
//
//        artistRepository.save(artist);
//    }
}
