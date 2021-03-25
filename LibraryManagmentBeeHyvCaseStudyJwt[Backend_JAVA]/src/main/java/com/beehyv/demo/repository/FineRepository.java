package com.beehyv.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beehyv.demo.Dto.Fine;

public interface FineRepository extends JpaRepository< Fine , Integer> {

	Fine findBymyvalue(int i);

	
}
