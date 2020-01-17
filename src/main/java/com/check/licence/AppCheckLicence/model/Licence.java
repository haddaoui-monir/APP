package com.check.licence.AppCheckLicence.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Licence {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private  Long licence_id;
	private  Date datedebut;
	private  Date datefin;
	private  String description;
	private int expiration;
	private String client;
	private String licencekey;
	private String name;
	
	public Licence(long id, Date datedebut, Date datefin, String description,int expiration,String client,String licencekey, String name) {
		super();
		this.licence_id = id;
		this.datedebut = datedebut;
		this.datefin = datefin;
		this.description = description;
		this.expiration = expiration;
		this.client = client;
		this.licencekey = licencekey;
		this.name = name;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Long getLicence_id() {
		return licence_id;
	}

	public void setLicence_id(Long licence_id) {
		this.licence_id = licence_id;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getLicencekey() {
		return licencekey;
	}
	public void setLicencekey(String licencekey) {
		this.licencekey = licencekey;
	}
	public int getExpiration() {
		return expiration;
	}
	public void setExpiration(int i) {
		this.expiration = i;
	}
	public Licence() {
		super();
	}
	public long getId() {
		return licence_id;
	}
	public void setId(long id) {
		this.licence_id = id;
	}
	public Date getDatedebut() {
		return datedebut;
	}
	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}
	public Date getDatefin() {
		return datefin;
	}
	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Licence [id=" + licence_id + ", datedebut=" + datedebut + ", datefin=" + datefin +
				",client=" + client +", description="+ description + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (licence_id ^ (licence_id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Licence other = (Licence) obj;
		if (licence_id != other.licence_id)
			return false;
		return true;
	}
	

}
