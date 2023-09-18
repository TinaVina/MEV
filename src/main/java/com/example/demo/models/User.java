package com.example.demo.models;

import java.util.Set;

import jakarta.persistence.*;
import java.util.HashSet;
@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_user;
    
    @Column 
    private String email;
    
    @Column 
    private String password;

	public long getId_user() {
		return id_user;
	}

	public void setId_user(long id_user) {
		this.id_user = id_user;
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
	
	//spajanje user s roles
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_role"))
	private Set<Role> roles;

	//spajanje tablice user s clanovima
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_clan", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_clan"))
	 
	    private Set<Clan> clanovi = new HashSet<>();

	    
	  	//CLANOVI
	    public Set<Clan> getClanove() {
	        return clanovi;
	    }

	    public void setClanove(Set<Clan> clanovi) {
	        this.clanovi = clanovi;
	    }
	
	    
	    
	    //ROLES
	    public Set<Role> getRoles() {
	        return roles;
	    }

	    public void setRoles(Set<Role> roles) {
	        this.roles = roles;
	    }
    
  
    

}