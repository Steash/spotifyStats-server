package com.steash.spotifyStats.repositories;

import com.steash.spotifyStats.domains.TopArtist;
import com.steash.spotifyStats.domains.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findBySpotifyId(String userSpotifyId);


//    @Query("SELECT DISTINCT u FROM User u" +
//                 "WHERE (u.")
//    Page<User> searchUser(String searchTerm, Pageable pageable);

    Page<User> findByDisplayNameContainingOrSpotifyIdContainingOrEmailContaining(
            String displayName, String spotifyId, String email, Pageable pageable
    );

//    List<User> findMusicBuddies(@Param("userId1") String userId1);
}
