package com.beehyv.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.beehyv.demo.model.Penality;

public interface PenalityRepository extends JpaRepository< Penality, Integer> {

	Penality getPenalityByrandomTokenGenrator(int id);

}
