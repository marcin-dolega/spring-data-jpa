package pl.dolega.sdjpaintro.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.dolega.sdjpaintro.domain.AuthorUuid;
import pl.dolega.sdjpaintro.repositories.AuthorUuidRepository;

@Profile({"local", "default"})
@Component
public class AuthorUuidInitializer implements CommandLineRunner {

    private final AuthorUuidRepository authorUUIDRepository;

    public AuthorUuidInitializer(AuthorUuidRepository authorUUIDRepository) {
        this.authorUUIDRepository = authorUUIDRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        authorUUIDRepository.deleteAll();

        AuthorUuid authorUUID = new AuthorUuid();
        authorUUID.setFirstName("Jack");
        authorUUID.setLastName("Black");

        AuthorUuid jackBlack = authorUUIDRepository.save(authorUUID);
        System.out.println("Saved Author UUID: " + jackBlack.getId());
    }
}
