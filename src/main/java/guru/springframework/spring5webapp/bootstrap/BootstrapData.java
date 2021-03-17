package guru.springframework.spring5webapp.bootstrap;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootstrapData implements CommandLineRunner{

	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;
	
	public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, 
			PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}


	@Override
	public void run(String... args) throws Exception {
		
		Publisher santillan = new Publisher("Santillan", "Calle 32", "Miramar", "Buenos Aires", "7607");
		publisherRepository.save(santillan);
		
		Author eric = new Author("Eric", "Evans");
		Book ddd = new Book("Domain Driven Development", "123123");
		eric.getBooks().add(ddd);
		ddd.getAuthors().add(eric);
		
		Author rod = new Author("Rod", "Johnson");
		Book noEJB = new Book("J2EE Development", "4546576");
		rod.getBooks().add(noEJB);
		noEJB.getAuthors().add(rod);
		
		santillan.getBooks().add(ddd);
		santillan.getBooks().add(noEJB);
		
		ddd.setPublisher(santillan);
		noEJB.setPublisher(santillan);
	
		authorRepository.saveAll(Arrays.asList(eric, rod));
		bookRepository.saveAll(Arrays.asList(ddd, noEJB));
		
		
		System.out.println("Started in Bootstrap");
		System.out.println("Number of Books: "+bookRepository.count());
		System.out.println("Number of publishers: "+publisherRepository.count());
	}
	

}
