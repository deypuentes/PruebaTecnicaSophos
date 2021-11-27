package com.sophos.identity.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name="user_identity")
public class UserIdentity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @NotBlank(message = "Username is required")
    private String username;
    
    @Column
    @NotBlank(message = "Password is required")
    @Size(min = 3, max = 8, message="El password tiene que tener entre 3 y 8 caracteres")
    @Pattern(regexp = "[A-Za-z0-9]+", message="El password No puede tener caracteres especiales")
    private String password;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}





    
	
    
}

