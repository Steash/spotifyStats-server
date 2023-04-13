package com.steash.spotifyStats.repositories;

import com.steash.spotifyStats.domains.TopArtist;
import com.steash.spotifyStats.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITopArtistRepository extends JpaRepository<TopArtist, Long> {
//    List<TopArtist> findByUser(User user);
}
