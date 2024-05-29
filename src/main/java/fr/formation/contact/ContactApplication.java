package fr.formation.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.formation.contact.entity.Client;
import fr.formation.contact.entity.Contact;
import fr.formation.contact.repository.ContactRepository;
import fr.formation.contact.service.ContactException;
import fr.formation.contact.service.ContactService;

@SpringBootApplication
public class ContactApplication implements CommandLineRunner{
	@Autowired
	ContactService service;
	
	/// Pour la démo
	@Autowired 
	ContactRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(ContactApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Client maBoite = new Client("Maboite","ici pas là","LaVille");
		
		Contact jean = new Contact("Cerien","Jean","02145577");
		jean.setClient(maBoite);
		System.out.println(jean);
		service.addContact(jean);
		System.out.println(jean);
		
		System.out.println("------------------");
		service.getAll().forEach(System.out::println);
		

		System.out.println("--------------------");
		service.getFromNomAndPrenom("Cerien","Jean").forEach(System.out::println);
		
		
		Contact martin = new Contact("Martin","Martin","545454");
		try {
			service.addContact(martin);
		} catch (ContactException e) {
			System.err.println(e.getMessage());
		}
		
		System.out.println("----------------");
		System.out.println(repo.getTelContactOfClientInVille("LaVille"));
		System.out.println(repo.getVilleClientOfContactTel("02145577"));
	}

}
