package pl.dolega.sdjpaintro.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.dolega.sdjpaintro.domain.Author;
import pl.dolega.sdjpaintro.repositories.AuthorRepository;

@Profile({"local", "default"})
@Component
public class AuthorInitializer implements CommandLineRunner {

    private final AuthorRepository authorRepository;

    public AuthorInitializer(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        authorRepository.deleteAll();

        Author kido = new Author("Kido", "Dog");
        authorRepository.save(kido);
        Author tiga = new Author("Tiga", "Cat");
        authorRepository.save(tiga);

        authorRepository.findAll()
                .forEach(author -> {
                    System.out.println("Author Id: " + author.getId());
                    System.out.println("Author name: " + author.getFirstName());
                });
    }
}
