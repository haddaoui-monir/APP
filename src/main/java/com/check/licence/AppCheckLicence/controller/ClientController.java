package com.check.licence.AppCheckLicence.controller;


import java.net.URI;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.check.licence.AppCheckLicence.model.Client;
import com.check.licence.AppCheckLicence.repository.ClientRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@GetMapping("/jpa/client")
	public List<Client> getAllClient(){
		return clientRepository.findAll();	
	}
	//DELETE
		@DeleteMapping("/jpa/client/{id}")
		public ResponseEntity<Void> deleteClient(@PathVariable Long id){

			clientRepository.deleteById( id);
			return ResponseEntity.noContent().build();
		}
		@GetMapping("/jpa/client/{id}")
		public Client getClient(@PathVariable long id){
			return clientRepository.findById(id).get();
				}
			//Edit/update a todo
		//put //users/{username}/todos/{id}
		@PutMapping("/jpa/client/{id}")
		public ResponseEntity<Client> updateClient(
				@PathVariable long id,@RequestBody Client ls){
			Client todoUpdates = clientRepository.save(ls);
			return new  ResponseEntity<Client>(ls, HttpStatus.OK);
		}
		
		@PostMapping("/jpa/client")
		public ResponseEntity<Void> createClient(@RequestBody Client ls){
			
			Client createdClient = clientRepository.save(ls);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(createdClient.getClient_id())
			.toUri();
			
			return   ResponseEntity.created(uri).build();
		}
		
}
