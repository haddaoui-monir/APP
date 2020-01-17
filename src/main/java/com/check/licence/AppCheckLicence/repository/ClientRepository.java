package com.check.licence.AppCheckLicence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.check.licence.AppCheckLicence.model.Client;
import com.check.licence.AppCheckLicence.model.Licence;

public  interface ClientRepository extends JpaRepository<Client, Long>{

	
    public List<Client> findByName(String name);

	  
	
	  
}
