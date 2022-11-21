package pl.dolega.sdjpaintro.book;

import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class BookDaoJDBCTemplate implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDaoJDBCTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Book getById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM book where id = ?", getBookMapper(), id);
    }

    @Override
    public Book findBookByTitle(String title) {
        return jdbcTemplate.queryForObject("SELECT * FROM book where title = ?", getBookMapper(), title);
    }

    @Override
    public Book saveNewBook(Book book) {
        jdbcTemplate.update("INSERT INTO book (isbn, publisher, title, author_id) VALUES (?, ?, ?, ?)",
        book.getIsbn(),
        book.getPublisher(),
        book.getTitle(),
        book.getAuthorId());
        Long createdId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        return this.getById(createdId);
    }

    @Override
    public Book updateBook(Book book) {
        jdbcTemplate.update("UPDATE book set isbn = ?, publisher = ?, title = ?, author_id = ? where id = ?",
                book.getIsbn(), book.getPublisher(), book.getTitle(), book.getAuthorId(), book.getId());
        return this.getById(book.getId());
    }

    @Override
    public void deleteBookById(Long id) {
        jdbcTemplate.update("DELETE from book where id = ?", id);
    }

    @Override
    public Book findByISBN(String isbn) {
        return null;
    }

    @Override
    public Book findBookByTitleNQ(String title) {
        return null;
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM book", getBookMapper());
    }

    @Override
    public List<Book> findAll(int pageSize, int offset) {
        return jdbcTemplate.query("SELECT * FROM book limit ? offset ?", getBookMapper(), pageSize, offset);
    }

    @Override
    public List<Book> findAll(Pageable pageable) {
        return jdbcTemplate.query("SELECT * FROM book limit ? offset ?", getBookMapper(), pageable.getPageSize(), pageable.getOffset());
    }

    @Override
    public List<Book> findALlSortByTitle(Pageable pageable) {
        String sql = "SELECT * FROM book order by title "
                + pageable.getSort().getOrderFor("title").getDirection().name()
                + " limit ? offset ?";
        System.out.println(sql);
        return jdbcTemplate.query(sql, getBookMapper(), pageable.getPageSize(), pageable.getOffset());
    }

    @Override
    public Book findBookByTitleCriteria(String title) {
        return null;
    }

    @Override
    public Book findBookByTitleNative(String title) {
        return null;
    }

    private BookMapper getBookMapper() {
        return new BookMapper();
    }
}
