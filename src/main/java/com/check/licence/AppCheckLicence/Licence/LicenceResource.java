package com.check.licence.AppCheckLicence.Licence;

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

import com.check.licence.AppCheckLicence.model.Licence;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LicenceResource {
	
	@Autowired
	private LicenceHardcodeService licenceService;
	
	
	@GetMapping("/licences")
	public List<Licence> getAllLicence(){
		return licenceService.findAll();
	}
   
	//DELETE
		@DeleteMapping("/licences/{id}")
		public ResponseEntity<Void> deleteLicence(@PathVariable long id){
			Licence todo = licenceService.deleteById(id);
			if(todo !=null) {
				return ResponseEntity.noContent().build();
			}
		return ResponseEntity.notFound().build();
		}
		
		@GetMapping("/licences/{id}")
		public Licence getTodo(@PathVariable long id){
			return licenceService.findById(id);
				}
		
			//Edit/update a todo
		//put //users/{username}/todos/{id}
		@PutMapping("/licences/{id}")
		public ResponseEntity<Licence> updateTodo(
				@PathVariable long id,@RequestBody Licence ls){
			Licence todoUpdates = licenceService.Save(ls);
			return new  ResponseEntity<Licence>(ls, HttpStatus.OK);
		}
		
		@PostMapping("/licences")
		public ResponseEntity<Void> updateLicence(@RequestBody Licence licence){
		    licence.setName(licence.getClient());
			
			Licence createdLicence = licenceService.Save(licence);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(createdLicence.getId())
			.toUri();
			
			return   ResponseEntity.created(uri).build();
		}

	}
