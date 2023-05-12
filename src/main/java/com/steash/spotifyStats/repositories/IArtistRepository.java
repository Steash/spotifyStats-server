package com.steash.spotifyStats.repositories;

import com.steash.spotifyStats.domains.Artist;
import com.steash.spotifyStats.domains.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IArtistRepository extends JpaRepository<Artist, Long> {
    Optional<Artist> findBySpotifyId(String artistSpotifyId);

    Page<Artist> findByNameContaining(
            String name, Pageable pageable
    );

}
