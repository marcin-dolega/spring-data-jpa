package pl.dolega.sdjpaintro.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import pl.dolega.sdjpaintro.jdbc.book.Book;
import pl.dolega.sdjpaintro.jdbc.book.BookDao;
import pl.dolega.sdjpaintro.jdbc.book.BookDaoImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("local")
@DataJpaTest
@Import(BookDaoImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookDaoIntegrationTest {

    @Autowired
    BookDao bookDao;

    @Test
    void getByIdTest() {
        Book book = bookDao.getById(1L);
        assertThat(book).isNotNull();
    }

    @Test
    void findByTitle() {
        Book book = bookDao.findByTitle("Clean Code");
        assertThat(book).isNotNull();
    }

    @Test
    void saveNewBookTest() {
        Book book = new Book("Clean Code", "666", "O'Reilly");
        Book saved = bookDao.saveNewBook(book);
        assertThat(saved).isNotNull();
    }

    @Test
    void updateBookTest() {
        Book book = new Book("Kido", "777", "Tiga");
        Book saved = bookDao.saveNewBook(book);
        saved.setTitle("Clean Code");
        Book updated = bookDao.updateBook(saved);
        assertEquals("Clean Code", updated.getTitle());
    }

    @Test
    void deleteBookTest() {
        Book book = new Book("Kido", "777", "Tiga");
        Book saved = bookDao.saveNewBook(book);
        bookDao.deleteBook(saved.getId());
        Book deleted = bookDao.updateBook(saved);
        assertThat(deleted).isNull();
    }
}
