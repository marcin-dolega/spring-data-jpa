package pl.dolega.sdjpaintro;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import pl.dolega.sdjpaintro.book.Book;
import pl.dolega.sdjpaintro.book.BookRepo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"pl.dolega.sdjpaintro.book", "pl.dolega.sdjpaintro.author"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

    @Autowired
    BookRepo bookRepo;

    @Test
    void testEmptyResultException() {
        assertThrows(EmptyResultDataAccessException.class, () -> {
            Book book = bookRepo.readByTitle("Szajba");
        });
    }

    @Test
    void testNullParam() {
        assertNull(bookRepo.getByTitle(null));
    }

    @Test
    void testNoException() {
        assertNull(bookRepo.getByTitle("Groch"));
    }

    @Test
    void testBookStream() {
        AtomicInteger count = new AtomicInteger();

        bookRepo.findAllByTitleNotNull().forEach(book -> {
            count.incrementAndGet();
        });

        assertThat(count.get()).isGreaterThan(0);
    }

    @Test
    void testBookFuture() throws ExecutionException, InterruptedException {
        Future<Book> bookFuture = bookRepo.queryByTitle("Clean Code");
        Book book = bookFuture.get();
        assertNotNull(book);
    }

    @Test
    void testBookQuery() {
        Book book = bookRepo.findBookByTitleWithQuery("Clean Code");
        assertNotNull(book);
    }

    @Test
    void testBookQueryNamed() {
        Book book = bookRepo.findBookByTitleWithQueryNamed("Clean Code");
        assertNotNull(book);
    }

    @Test
    void testBookQueryNative() {
        Book book = bookRepo.findBookByTitleWithQueryNative("Clean Code");
        assertNotNull(book);
    }

    @Test
    void testBookQueryJPANamed() {
        Book book = bookRepo.jpaNamed("Clean Code");
        assertNotNull(book);
    }


}
