package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.models.Novost;
import com.example.demo.repository.NovostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NovostServiceImpl implements NovostService {

    private final NovostRepository novostRepository;

    @Autowired
    public NovostServiceImpl(NovostRepository novostRepository) {
        this.novostRepository = novostRepository;
    }

    @Override
    public List<Novost> getAllNovosti() {
        return novostRepository.findAll();
    }

    @Override
    public Novost createNovost(Novost novost) {
        return novostRepository.save(novost);
    }

    @Override
    public Novost updateNovost(Novost dataNovost) throws ResourceNotFoundException {
        Optional<Novost> novostDb = novostRepository.findById(dataNovost.getId_novost());

        if (novostDb.isPresent()) {
            Novost novostUpdate = novostDb.get();
            novostUpdate.setNaslov(dataNovost.getNaslov());
            novostUpdate.setDatum(dataNovost.getDatum());
            novostUpdate.setOpis(dataNovost.getOpis());

            novostRepository.save(novostUpdate);
            return novostUpdate;
        } else {
            throw new ResourceNotFoundException("Zapis nije pronađen: " + dataNovost.getId_novost());
        }
    }

    @Override
    public void deleteNovost(long novostId) {
        Optional<Novost> novostDb = novostRepository.findById(novostId);
        if (novostDb.isPresent()) {
            novostRepository.delete(novostDb.get());
        } else {
            throw new ResourceNotFoundException("Zapis nije pronađen.");
        }
    }

    @Override
    public Novost getNovost(long id_novost) {
        if (id_novost == 0)
            return new Novost();
        Optional<Novost> novostDb = novostRepository.findById(id_novost);
        if (novostDb.isPresent()) {
            return novostDb.get();
        } else {
            return new Novost();
        }
    }
}
