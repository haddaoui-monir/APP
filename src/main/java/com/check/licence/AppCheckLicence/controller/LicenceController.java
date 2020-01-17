package com.check.licence.AppCheckLicence.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.check.licence.AppCheckLicence.Licence.LicenceHardcodeService;
import com.check.licence.AppCheckLicence.model.Client;
import com.check.licence.AppCheckLicence.model.Licence;
import com.check.licence.AppCheckLicence.repository.ClientRepository;
import com.check.licence.AppCheckLicence.repository.LicenceRepository;
import com.check.licence.AppCheckLicence.service.EmailService;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LicenceController {

	@Autowired
	private LicenceHardcodeService licenceService;
	
	@Autowired
	private LicenceRepository licenceRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	
	@Autowired
	EmailService emailService;
	
	List<Licence> licenceDB = new ArrayList<Licence>();
	
	//Get licence
	   @GetMapping("/jpa/licences")
	   public List<Licence> getAllLicence(){
			return licenceRepository.findAll(org.springframework.data.domain.Sort.by("client").descending().and(org.springframework.data.domain.Sort.by("name").descending()));	
		  }
	//DELETE
		@DeleteMapping("/jpa/licences/{id}")
		public ResponseEntity<Void> deleteLicence(@PathVariable Long id){  
			
			boolean Btest = licenceService.testExistingClient(id);
			//if(Btest)
			licenceRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		
   //Get licence ID
		@GetMapping("/jpa/licences/{id}")
		public Licence getLicence(@PathVariable long id){
			return licenceRepository.findById(id).get();
				}
   //Edit/update a todo
   //put //users/{username}/todos/{id}
		@PutMapping("/jpa/licences/{id}")
		public ResponseEntity<Licence> updateLicence(
				@PathVariable long id,@RequestBody Licence ls){			
//			 if(testExistingClientUpdate(ls))
//				 ls.setName(ls.getName());     
//			 else
//				 ls.setName("OOOOOS");
			if(!ls.getName().equalsIgnoreCase(ls.getClient())) {
				licenceService.testExistingClient(ls.getId());
				ls.setName(ls.getClient());
			}
			Licence listLs = getLicence(ls.getId());
			ls.setExpiration(licenceService.nbJour(ls.getDatedebut(), ls.getDatefin()));
			// update key if user change datefin
			if(ls.getExpiration() != listLs.getExpiration())
				ls.setLicencekey(LicenceKey(ls.getDatefin()));
			   Licence todoUpdates = licenceRepository.save(ls);
			return new  ResponseEntity<Licence>(ls, HttpStatus.OK);
		}
		
		@PostMapping("/jpa/licences")
		public ResponseEntity<Void> createLicence(@RequestBody Licence ls){
			
			ls.setExpiration(licenceService.nbJour(ls.getDatedebut(), ls.getDatefin()));
			ls.setLicencekey(LicenceKey(ls.getDatefin()));
			if(licenceService.testClientLicence(ls.getClient())) 
			ls.setName(ls.getClient());
			else ls.setName("");
			Licence createdLicence = licenceRepository.save(ls);
			///Add CLIENT
			licenceService.addClient(ls.getClient(),ls.getId());
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(createdLicence.getId())
			.toUri();
			return   ResponseEntity.created(uri).build();
		}
		
		//Add Client
		public void addClient(String clientName, Long id) {
			 Boolean testClient = true;

			List<Client> clienttest = clientRepository.findByName(clientName);
			for (Client client : clienttest) {
				//System.out.println("******************"+client.getName());
				if(client.getName().equalsIgnoreCase(clientName))
				testClient = false;
			}
				   if(testClient == true) {
				     Client pants = new Client( id,clientName);
				     Client clientLicence = clientRepository.save(pants);
				   }
		}
		public String LicenceKey(Date dateLicence) {
			String uri = "http://cloud.peaqock.com:10000/licence/v1/generate/"+dateLicence;
			   RestTemplate restTemplate = new RestTemplate();
			   String  result = restTemplate.getForObject(uri, String.class);
			  // System.out.println("**1"+result);
			   HashMap<String, Object> map = new HashMap<String, Object>();
		        
		       ObjectMapper mapper01 = new ObjectMapper();
		       try
		       {
		           //Convert Map to JSON
		           map = mapper01.readValue(result, new TypeReference<Map<String, Object>>(){});
		           //Print JSON output
		           //System.out.println("mmap"+map.get("value"));
		       } 
		       catch (JsonGenerationException e) {
		           e.printStackTrace();
		       } catch (JsonMappingException e) {
		           e.printStackTrace();
		       } catch (IOException e) {
		           e.printStackTrace();
		       }
			return (String) map.get("value");
		}
	


	}
