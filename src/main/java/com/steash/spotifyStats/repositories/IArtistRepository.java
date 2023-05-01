package com.steash.spotifyStats.repositories;

import com.steash.spotifyStats.domains.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IArtistRepository extends JpaRepository<Artist, Long> {
    Optional<Artist> findBySpotifyId(String artistSpotifyId);


}
