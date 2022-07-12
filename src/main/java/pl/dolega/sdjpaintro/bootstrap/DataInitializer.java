package pl.dolega.sdjpaintro.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.dolega.sdjpaintro.domain.Book;
import pl.dolega.sdjpaintro.repositories.BookRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Book bookDDD = new Book("Domain Driven Design", "0321125215", "Addison-Wesley Professional");
        System.out.println("Id: " + bookDDD.getId());
        Book savedDDD = bookRepository.save(bookDDD);
        System.out.println("Id: " + bookDDD.getId());


        Book bookSIA = new Book("Spring in Action", "9781617297571", "O'Reilly");
        Book savedSIA = bookRepository.save(bookSIA);

        bookRepository.findAll()
                .forEach(book -> {
                    System.out.println("Book Id: " + book.getId());
                    System.out.println("Book Title: " + book.getTitle());
                });
    }
}
