package pl.dolega.sdjpaintro.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.dolega.sdjpaintro.domain.BookUuid;
import pl.dolega.sdjpaintro.repositories.BookUuidRepository;

@Profile({"local", "default"})
@Component
public class BookUuidInitializer implements CommandLineRunner {

    private final BookUuidRepository bookUuidRepository;

    public BookUuidInitializer(BookUuidRepository bookUuidRepository) {
        this.bookUuidRepository = bookUuidRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        bookUuidRepository.deleteAll();

        BookUuid bookUuid = new BookUuid();
        bookUuid.setTitle("All About UUIDs");
        BookUuid savedBookUuid = bookUuidRepository.save(bookUuid);
        System.out.println("Saved Book UUID: " + savedBookUuid.getId());
    }
}
