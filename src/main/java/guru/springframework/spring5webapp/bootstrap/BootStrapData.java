package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Bootstrap started");

        // Varrak
        Publisher ppp = new Publisher();

        ppp.setName("Varrak");

        publisherRepository.save(ppp);

        // Jaan
        Author jaan = new Author("Jaan", "Kross");
        Book aaa = new Book("Keisri hull", "111-111-111");

        jaan.getBooks().add(aaa);
        aaa.getAuthors().add(jaan);
        aaa.setPublisher(ppp);

        ppp.getBooks().add(aaa);

        authorRepository.save(jaan);
        bookRepository.save(aaa);
        publisherRepository.save(ppp);

        //Rod
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "23444");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(ppp);

        ppp.getBooks().add(aaa);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(ppp);

        System.out.println("No of books: " + bookRepository.count());
        System.out.println("No of authors: " + authorRepository.count());
        System.out.println("No of publishers: " + publisherRepository.count());
        System.out.println("No of ppp books: " + ppp.getBooks().size());
    }
}
