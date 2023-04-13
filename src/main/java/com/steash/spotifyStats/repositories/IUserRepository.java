package com.steash.spotifyStats.repositories;

import com.steash.spotifyStats.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findBySpotifyId(String userSpotifyId);

    Optional<User> findByAccessToken(String accessToken);
}
