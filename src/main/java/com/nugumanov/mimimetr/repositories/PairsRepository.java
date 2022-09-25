package com.nugumanov.mimimetr.repositories;

import com.nugumanov.mimimetr.models.Pair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Aizat Nugumanov
 */
@Repository
public interface PairsRepository extends JpaRepository<Pair, Integer> {
}
