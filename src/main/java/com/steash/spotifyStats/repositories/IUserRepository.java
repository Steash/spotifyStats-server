package com.steash.spotifyStats.repositories;

import com.steash.spotifyStats.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {

}
