package com.check.licence.AppCheckLicence.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;



@Entity
public class Client {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long client_id;
	
	private String name;
	//@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JoinTable(name = "client_licence", joinColumns = @JoinColumn(name = "client_id"), inverseJoinColumns = @JoinColumn(name = "licence_id"))
	//private Set<Licence> licences;
	
	public Client( String name) {
		super();
		this.name = name;
	//	this.licences = licences;
	}
	
	public Client() {
		super();
	}

	public Client(Long client_id, String name) {
		super();
		this.client_id = client_id;
		this.name = name;
	//	this.licences = licences;
	}
	public Long getClient_id() {
		return client_id;
	}
	public void setClient_id(Long client_id) {
		this.client_id = client_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Client [client_id=" + client_id + ", name=" + name + "]";
	}
	
}
