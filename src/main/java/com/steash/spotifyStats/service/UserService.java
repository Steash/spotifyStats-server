package com.steash.spotifyStats.service;
import com.steash.spotifyStats.domains.TopArtist;
import com.steash.spotifyStats.domains.User;
import com.steash.spotifyStats.repositories.ITopArtistRepository;
import com.steash.spotifyStats.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ITopArtistRepository topArtistRepository;

    public List<TopArtist> getAllTopArtistsForUser(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return topArtistRepository.findAllByUser(user);
    }
}
