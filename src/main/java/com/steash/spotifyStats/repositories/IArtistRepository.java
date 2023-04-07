package com.steash.spotifyStats.repositories;

import com.steash.spotifyStats.domains.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArtistRepository extends JpaRepository<Artist, Long> {
}
