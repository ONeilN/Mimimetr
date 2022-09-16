package com.nugumanov.mimimetr.repositories;

import com.nugumanov.mimimetr.models.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Aizat Nugumanov
 */
@Repository
public interface CatsRepository extends JpaRepository<Cat, Integer> {
    List<Cat> findTop10ByOrderByVoicesDesc();
}
