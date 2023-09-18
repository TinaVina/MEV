package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Clan;


@Repository("ClanRepository")
public interface ClanRepository extends JpaRepository<Clan, Long> {
	List<Clan> findAll();

	List<Clan> findAllByime(String ime);
	
	Clan findByEmailAndPassword(String email, String password);
}
