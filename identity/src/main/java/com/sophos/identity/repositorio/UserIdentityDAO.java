package com.sophos.identity.repositorio;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sophos.identity.dominio.UserIdentity;



@Repository
public interface UserIdentityDAO extends JpaRepository<UserIdentity, Integer>{
	
	UserIdentity findByUsername(String username);



}
