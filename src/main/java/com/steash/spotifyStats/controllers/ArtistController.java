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
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Stream;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/artist")
public class ArtistController {
    @Autowired
    IArtistRepository artistRepository;

    @Autowired
    ArtistDtoMapper artistMapper;


    @GetMapping("/get")
    public Stream<ArtistDto> findAll() {
        return artistRepository.findAll().stream().map(artistMapper::artistToDto);
    }


    @GetMapping("/get/{id}")
    public Optional<ArtistDto> find(@PathVariable long id) {
        return Optional.of(artistMapper.artistToDto(artistRepository.findById(id).get()));
    }

    @GetMapping("/get/test")
    public String getTest() {
        return "hello world";
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
