package pl.dolega.sdjpaintro.book;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class BookDaoImpl implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Book getById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM book WHERE id = ?", getRowMapper(), id);
    }

    @Override
    public Book findByTitle(String title) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM book WHERE title = ?",
                getRowMapper(), title
        );
    }

    @Override
    public Book save(Book book) {
        jdbcTemplate.update("INSERT INTO book (title, isbn, publisher) VALUES (?, ?, ?)",
                book.getTitle(), book.getIsbn(), book.getPublisher());
        Long createdId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        return this.getById(createdId);
    }

    @Override
    public Book update(Book book) {
        jdbcTemplate.update("UPDATE book SET title = ?, isbn = ?, publisher = ? WHERE id = ?",
                book.getTitle(), book.getIsbn(), book.getPublisher(), book.getId());
        return this.getById(book.getId());
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM book WHERE id = ?", id);
    }

    private RowMapper<Book> getRowMapper() {
        return new BookMapper();
    }
}
