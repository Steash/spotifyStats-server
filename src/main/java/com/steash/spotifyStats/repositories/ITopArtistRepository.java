package com.steash.spotifyStats.repositories;

import com.steash.spotifyStats.domains.Artist;
import com.steash.spotifyStats.domains.TopArtist;
import com.steash.spotifyStats.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITopArtistRepository extends JpaRepository<TopArtist, Long> {
    List<TopArtist> findAllByUser(User user);

    List<TopArtist> findAllByArtistAndUserNot(Artist artist, User user);

    @Query("SELECT ta FROM TopArtist ta " +
                "WHERE ta.user.spotifyId = :userId1 " +
                "AND ta.artist IN " +
            "(SELECT ta2.artist FROM TopArtist ta2 " +
                "WHERE ta2.user.spotifyId = :userId2)")
    List<TopArtist> findCommonTopArtists(@Param("userId1") String userId1, @Param("userId2") String userId2);


}
