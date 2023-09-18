package com.example.demo.models;



import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Novost")
public class Novost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_novost;

    
    
    @Column
    private String naslov;

    @Column 
    private Date datum;
    
    @Column
    private String opis;

	public long getId_novost() {
		return id_novost;
	}

	public void setId_novost(long id_novost) {
		this.id_novost = id_novost;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}
    
    
    
    
}