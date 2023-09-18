package com.example.demo.models;

import java.util.Set;

import io.micrometer.common.lang.NonNull;

import java.util.HashSet;
import jakarta.persistence.*;

@Entity
@Table(name = "Clan")
public class Clan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_clan;

    @Column
    private String ime;

    @Column 
    private String prezime;
    
    @Column 
    private String email;
    
    @Column 
    private String password;
    
    @Column 
    private String studijski_program;
    
    @Column 
    private String status;
    
    @Column 
    private String funkcija;
    
    @Column
    private String role;
    

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getId_clan() {
		return id_clan;
	}

	public void setId_clan(long id_clan) {
		this.id_clan = id_clan;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStudijski_program() {
		return studijski_program;
	}

	public void setStudijski_program(String studijski_program) {
		this.studijski_program = studijski_program;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFunkcija() {
		return funkcija;
	}

	public void setFunkcija(String funkcija) {
		this.funkcija = funkcija;
	}
    
	
    @ManyToMany(mappedBy = "clanovi", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<User> users = new HashSet<>();



    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
    
    
   
    
    

}