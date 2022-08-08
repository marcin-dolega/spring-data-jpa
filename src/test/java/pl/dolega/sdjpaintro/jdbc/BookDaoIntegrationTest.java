package pl.dolega.sdjpaintro.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import pl.dolega.sdjpaintro.book.Book;
import pl.dolega.sdjpaintro.book.BookDao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"pl.dolega.sdjpaintro.book"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookDaoIntegrationTest {

    @Autowired
    BookDao bookDao;

    @Test
    void getByIdTest() {
        Book book = bookDao.getById(1L);
        System.out.println(book.getTitle());
        assertThat(book).isNotNull();
    }

    @Test
    void findByTitleTest() {
        Book book = bookDao.findByTitle("Clean Code");
        assertThat(book).isNotNull();
    }

    @Test
    void saveBookTest() {
        Book book = new Book();
        book.setTitle("Kido");
        book.setIsbn("Dog");
        book.setPublisher("Tiga");
        Book saved = bookDao.save(book);
        assertThat(saved).isNotNull();
    }

    @Test
    void updateBookTest() {
        Book book = new Book("Kido", "Dog", "Tiga");
        Book saved = bookDao.save(book);
        saved.setTitle("Brudas");
        saved.setIsbn("666");
        saved.setPublisher("Szajba");
        Book updated = bookDao.update(saved);
        assertEquals("Brudas", updated.getTitle());
    }

    @Test
    void deleteBookTest() {
        Book book = new Book("Kido", "Dog", "Tiga");
        Book saved = bookDao.save(book);
        bookDao.deleteById(saved.getId());
        assertThrows(EmptyResultDataAccessException.class, () -> bookDao.getById(saved.getId()));
//        assertThat(deleted).isNull();
    }
}