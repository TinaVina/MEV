package com.example.demo.service;

import java.util.List;


import com.example.demo.models.Novost;

public interface NovostService {
	List<Novost> getAllNovosti();
	
	Novost createNovost(Novost novost);
	
	Novost updateNovost(Novost novost);
    
	void deleteNovost(long id);
    
	Novost getNovost(long id_novost);
}
