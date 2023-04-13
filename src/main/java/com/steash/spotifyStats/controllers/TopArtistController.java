package com.steash.spotifyStats.controllers;

import com.steash.spotifyStats.domains.TopArtist;
import com.steash.spotifyStats.domains.User;
import com.steash.spotifyStats.dtos.topArtist.SaveTopArtistDto;
import com.steash.spotifyStats.dtos.topArtist.TopArtistDto;
import com.steash.spotifyStats.dtos.user.SaveUserDto;
import com.steash.spotifyStats.dtos.user.UserDto;
import com.steash.spotifyStats.mappers.TopArtistDtoMapper;
import com.steash.spotifyStats.repositories.ITopArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/topArtist")
public class TopArtistController {
    @Autowired
    private TopArtistDtoMapper topArtistMapper;
    @Autowired
    private ITopArtistRepository topArtistRepository;

    @GetMapping("/get")
    public Stream<TopArtistDto> findAll() {
        return topArtistRepository.findAll().stream().map(topArtistMapper::topArtistToDto);
    }

    @GetMapping("/get/{id}")
    public Optional<TopArtistDto> find(@PathVariable long id) {
        return Optional.of(topArtistMapper.topArtistToDto(topArtistRepository.findById(id).get()));
    }

//    @GetMapping("/get/myTopArtists")
//    public Optional<TopArtistDto> find(@PathVariable long id) {
//        return Optional.of(topArtistMapper.topArtistToDto(topArtistRepository.findById(id).get()));
//    }

    @PostMapping("/create")
    public void create(@RequestBody SaveTopArtistDto saveTopArtistDto) throws IOException {
        TopArtist topArtist = topArtistMapper.dtoToTopArtist(saveTopArtistDto);

//        topArtistRepository.save(topArtist);
    }

}
