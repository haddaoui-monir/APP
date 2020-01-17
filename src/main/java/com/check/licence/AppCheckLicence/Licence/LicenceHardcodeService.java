package com.check.licence.AppCheckLicence.Licence;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.check.licence.AppCheckLicence.model.Client;
import com.check.licence.AppCheckLicence.model.Licence;
import com.check.licence.AppCheckLicence.repository.ClientRepository;
import com.check.licence.AppCheckLicence.repository.LicenceRepository;


@Component
public class LicenceHardcodeService {
	
	@Autowired
	private LicenceRepository licenceRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	List<Licence> licenceDB = new ArrayList<Licence>();

	
	private static List<Licence> licences = new ArrayList();
	private static List<Licence> licences01 = new ArrayList();
	private static long idCount =0;
	
	public List<Licence> getAllLicence(){
		return licenceRepository.findAll(org.springframework.data.domain.Sort.by("client").descending().and(org.springframework.data.domain.Sort.by("name")));	
	}
	
	public String  ExpirationLicence() {
		licences01 = getAllLicence();
		String  value="";
		for(Licence ls : licences01 ) {
			if( nbJour(ls.getDatefin(),ls.getDatefin()) < 12) {
				value =value+" Client "+" "+ls.getDatefin()+"\t\n";
				System.out.println("  ******************************   "+value);
			}
		}
		 return value;
	}
	
	public void testExistingClientUpdate(Licence licenceID) {
		//Licence licenceID = licenceRepository.findById(id).get();
			 licenceDB = getAllLicence();
			  for(Licence licenceDB : licenceDB) {
		    	   if(licenceDB.getClient().equals(licenceID.getClient()) 
		    			   &&  licenceDB.getId() !=   licenceID.getId()) {
		    		   licenceDB.setName(licenceID.getClient());
		    		   //licenceDB.setClient("dddsdsd");
		    		   licenceRepository.save(licenceDB); 
		    		   break ; // if 
		    	   } 
		    	   System.out.println("****"+licenceID.getClient()+"*3333"+licenceDB.getClient());
		         }
		       
	       }
	public boolean testExistingClient(Long id) {
		Licence licenceID = licenceRepository.findById(id).get();
		if(!licenceID.getName().equals("")) {
			 licenceDB = getAllLicence();
		       for(Licence licenceDB : licenceDB) {
		    	   if(licenceDB.getClient().equalsIgnoreCase(licenceID.getName()) 
		    			   &&  licenceDB.getId() !=   licenceID.getId()) {
		    		   licenceDB.setName(licenceID.getClient());
		    		   //licenceDB.setClient("dddsdsd");
		    		   licenceRepository.save(licenceDB); 
		    		   return true; // if 
		    	   } 
		   
		         }
		      }
		return false;
	}
	
	//Add Client
			public void addClient(String clientName, Long id) {
				 Boolean testClient = true;
				List<Client> clienttest = clientRepository.findByName(clientName);
				for (Client client : clienttest) {
					System.out.println("******************"+client.getName());
					if(client.getName().equalsIgnoreCase(clientName))
					testClient = false;
				}
					   if(testClient == true) {
					     Client pants = new Client( id,clientName);
					     Client clientLicence = clientRepository.save(pants);
					   }				
			}
			public boolean testClient(String clientName) {
				 Boolean testClient = true;
				List<Client> clienttest = clientRepository.findByName(clientName);
				for (Client client : clienttest) {
					if(client.getName().equalsIgnoreCase(clientName))
					return testClient = false;
				}
			  return testClient;			
			}
			public boolean testClientLicence(String clientName) {
				 Boolean testClient = true;
				List<Licence> clienttest = licenceRepository.findByClient(clientName);
				for (Licence client : clienttest) {
					if(client.getName().equalsIgnoreCase(clientName))
					return testClient = false;
				}
			  return testClient;			
			}
	
	public int nbJour(Date dateApres, Date datedebut) {
		//System.out.println(datedebut);
		//System.out.println(dateApres + "\n");

		DecimalFormat crunchifyFormatter = new DecimalFormat("###,###");

		// getTime() returns the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this Date object
		Date DateHD = new Date();
		long diff = datedebut.getTime() - DateHD.getTime();
		int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
		//System.out.println("difference between days: " + diffDays);

		int diffhours = (int) (diff / (60 * 60 * 1000));
		//System.out.println("difference between hours: " + crunchifyFormatter.format(diffhours));

		int diffmin = (int) (diff / (60 * 1000));
		//System.out.println("difference between minutues: " + crunchifyFormatter.format(diffmin));

		int diffsec = (int) (diff / (1000));
		//System.out.println("difference between seconds: " + crunchifyFormatter.format(diffsec));

		//System.out.println("difference between milliseconds: " + crunchifyFormatter.format(diff));
		
		return diffDays;
	}
	static {
		//licences.add(new licence(++idcount,new date(), new date(),"licence012"));
	//	licences.add(new licence(++idcount,new date(), new date(),"gestion stock"));
	//	licences.add(new licence(++idcount,new date(), new date(),"info licence012"));
	//	licences.add(new licence(++idcount,new date(), new date(),"expiration"));
	//	licences.add(new licence(++idcount,new date(), new date(),"001 app"));
	}
	public List<Licence> findAll(){
		return licences;
	}
	
	public Licence deleteById(long id) {
		Licence licence = findById(id);
		if(licence==null) return null;
		if(licences.remove(licence))
		return licence;
		return null;
	}

	public Licence findById(long id) {
		for(Licence ls: licences)
			if(ls.getId()== id) {
				return ls;
			}
		return null;
	}
	public Licence Save(Licence ls) {
		if(ls.getId() ==-1 || ls.getId()==0) {
			ls.setId(++idCount);
			licences.add(ls);
		}else {
			deleteById(ls.getId());
			licences.add(ls);
		}
		return ls;
	 }
	
	
	

}
