package com.example.demo.service;

import com.example.demo.models.Clan;


import java.util.List;

public interface ClanService {
    List<Clan> getAllClanovi();
    
    Clan findClanByEmailAndPassword(String email, String password);
    
	Clan createClan(Clan clan);
	
	Clan updateClan(Clan clan);
    
	void deleteClan(long id);
    
	Clan getClan(long id_clan);
    
    
    
    
    
    
}
