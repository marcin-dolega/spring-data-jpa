package pl.dolega.sdjpaintro;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import pl.dolega.sdjpaintro.domain.AuthorUuid;
import pl.dolega.sdjpaintro.domain.BookNatural;
import pl.dolega.sdjpaintro.domain.BookUuid;
import pl.dolega.sdjpaintro.domain.composite.AuthorComposite;
import pl.dolega.sdjpaintro.domain.composite.AuthorEmbedded;
import pl.dolega.sdjpaintro.domain.composite.NameId;
import pl.dolega.sdjpaintro.repositories.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"pl.dolega.sdjpaintro.bootstrap"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MySQLIntegrationTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorUuidRepository authorUuidRepository;

    @Autowired
    BookUuidRepository bookUuidRepository;

    @Autowired
    BookNaturalRepository bookNaturalRepository;

    @Autowired
    AuthorCompositeRepository authorCompositeRepository;

    @Autowired
    AuthorEmbeddedRepository authorEmbeddedRepository;

    @Test
    void testMySQL() {
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(2);
    }

    @Test
    void testAuthorUuid() {
        AuthorUuid author = new AuthorUuid("Martyn", "Groch");
        authorUuidRepository.save(author);
        var fetched = authorUuidRepository.getReferenceById(author.getId());
        assertNotNull(fetched);
        assertEquals(author, fetched);
    }

    @Test
    void testBookUuid() {
        BookUuid book = new BookUuid("Przygody Groszka", "666", "Wilkowisko");
        bookUuidRepository.save(book);
        var fetched = bookUuidRepository.getReferenceById(book.getId());
        assertNotNull(fetched);
        assertEquals(book, fetched);
    }

    @Test
    void bookNaturalTest() {
        BookNatural bookNatural = new BookNatural();
        bookNatural.setTitle("My Book");
        var saved = bookNaturalRepository.save(bookNatural);
        var fetched = bookNaturalRepository.getReferenceById(saved.getTitle());
        assertNotNull(fetched);
        assertEquals(saved, fetched);
    }

    @Test
    void authorCompositeTest() {
        NameId nameId = new NameId("John", "Cena");
        AuthorComposite authorComposite = new AuthorComposite();
        authorComposite.setFirstName(nameId.getFirstName());
        authorComposite.setLastName(nameId.getLastName());
        authorComposite.setCountry("US");
        var saved = authorCompositeRepository.save(authorComposite);
        assertNotNull(saved);
        var fetched = authorCompositeRepository.getReferenceById(nameId);
        assertNotNull(fetched);
        assertEquals(saved, fetched);
    }

    @Test
    void authorEmbeddedTest() {
        NameId nameId = new NameId("John", "Cena");
        AuthorEmbedded authorEmbedded = new AuthorEmbedded(nameId);
        authorEmbedded.setCountry("US");
        var saved = authorEmbeddedRepository.save(authorEmbedded);
        assertNotNull(saved);
        var fetched = authorEmbeddedRepository.getReferenceById(nameId);
        assertNotNull(fetched);
        assertEquals(saved, fetched);
    }
}