package com.nugumanov.mimimetr.repositories;

import com.nugumanov.mimimetr.models.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Aizat Nugumanov
 */
@Repository
public interface GuestsRepository extends JpaRepository<Guest, Integer> {

    Optional<Guest> findGuestByCookieValue(String cookieValue);
}
