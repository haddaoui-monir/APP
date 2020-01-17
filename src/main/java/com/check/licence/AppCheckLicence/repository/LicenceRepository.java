package com.check.licence.AppCheckLicence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.check.licence.AppCheckLicence.model.Client;
import com.check.licence.AppCheckLicence.model.Licence;


@Repository
public interface LicenceRepository extends JpaRepository<Licence, Long>{
	public List<Licence> findAllByOrderByClientDesc();
	 public List<Licence> findByClient(String client);
	 public List<Licence> findByName(String name);
}
