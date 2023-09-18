package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.models.Clan;

import com.example.demo.repository.ClanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class ClanServiceImpl implements ClanService {

    private final ClanRepository clanRepository;
   

    @Autowired
    public ClanServiceImpl(ClanRepository clanRepository) {
        this.clanRepository = clanRepository;
      
    }

    @Override
    public List<Clan> getAllClanovi() {
        return clanRepository.findAll();
    }
    
    @Override
    public Clan findClanByEmailAndPassword(String email, String password) {
        return clanRepository.findByEmailAndPassword(email, password);
    }
    
    

	@Override
	public Clan createClan(Clan Clan) {
		return clanRepository.save(Clan);
	}
    
	
	@Override
	public Clan updateClan(Clan dataClan) throws ResourceNotFoundException{
		Optional<Clan>productDb = this.clanRepository.findById(dataClan.getId_clan());
		
		if (productDb.isPresent()) {
			Clan ClanUpdate = productDb.get();
			ClanUpdate.setIme(dataClan.getIme());
			ClanUpdate.setPrezime(dataClan.getPrezime());
			ClanUpdate.setEmail(dataClan.getEmail());
			ClanUpdate.setPassword(dataClan.getPassword());
			ClanUpdate.setStudijski_program(dataClan.getStudijski_program());
			ClanUpdate.setFunkcija(dataClan.getFunkcija());
			ClanUpdate.setStatus(dataClan.getStatus());
			ClanUpdate.setRole(dataClan.getRole());
		
			clanRepository.save(ClanUpdate);
			return ClanUpdate;
			} else {
			throw new ResourceNotFoundException("Zapis nije pronađen : " +
			   dataClan.getId_clan());
			}

	}
	
	@Override
	public void deleteClan(long ClanId) {
		Optional<Clan> productDb = this.clanRepository.findById(ClanId);
		if (productDb.isPresent()) {
		this.clanRepository.delete(productDb.get());
		} else {
		throw new ResourceNotFoundException("Zapis nije pronađen.");
		}
		}
	
	@Override
	public Clan getClan(long id_clan) {
		if (id_clan == 0)
		return new Clan();
		Optional<Clan> productDb = this.clanRepository.findById(id_clan);
		if (productDb.isPresent()) {
		return productDb.get();
		} else {
		return new Clan();
		}
		}
    
 
    
}
