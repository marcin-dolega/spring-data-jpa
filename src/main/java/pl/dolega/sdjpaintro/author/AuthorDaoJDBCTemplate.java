package pl.dolega.sdjpaintro.author;

import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AuthorDaoJDBCTemplate implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoJDBCTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Author getById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM author where id = ?", getAuthorMapper(), id);
    }

    @Override
    public Author findByName(String firstName, String lastName) {
        return jdbcTemplate.queryForObject("SELECT a FROM Author a WHERE a.firstName = ? and a.lastName = ?", getAuthorMapper(), firstName, lastName);
    }

    @Override
    public Author saveAuthor(Author author) {
        return null;
    }

    @Override
    public Author updateAuthor(Author author) {
        jdbcTemplate.update(("INSERT INTO author (name, last_name) VALUES (?, ?)"),
                author.getFirstName(),
                author.getLastName());
        Long createdId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        return this.getById(createdId);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Author> findAllByLastName(String lastName) {
        String sql = "SELECT * FROM author where last_name = ?";
        return jdbcTemplate.query(sql, getAuthorMapper(), lastName);
    }

    @Override
    public List<Author> findAllByLastName(String lastName, Pageable pageable) {
        String sql = "SELECT * FROM author where last_name = ? order by first_name limit ? offset ?";
        System.out.println(sql);
        return jdbcTemplate.query(sql, getAuthorMapper(), lastName, pageable.getPageSize(), pageable.getOffset());
    }

    @Override
    public List<Author> findAll() {
        return null;
    }

    private AuthorMapper getAuthorMapper() {
        return new AuthorMapper();
    }

}
