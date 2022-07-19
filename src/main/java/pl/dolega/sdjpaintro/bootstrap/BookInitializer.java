package pl.dolega.sdjpaintro.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.dolega.sdjpaintro.domain.Book;
import pl.dolega.sdjpaintro.repositories.BookRepository;

@Profile({"local", "default"})
@Component
public class BookInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    public BookInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        bookRepository.deleteAll();

        Book bookDDD = new Book("Domain Driven Design", "0321125215", "Addison-Wesley Professional");
        bookRepository.save(bookDDD);

        Book bookSIA = new Book("Spring in Action", "9781617297571", "O'Reilly");
        bookRepository.save(bookSIA);

        bookRepository.findAll()
                .forEach(book -> {
                    System.out.println("Book Id: " + book.getId());
                    System.out.println("Book Title: " + book.getTitle());
                });
    }
}
