package pl.dolega.sdjpaintro.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import pl.dolega.sdjpaintro.author.Author;
import pl.dolega.sdjpaintro.author.AuthorDao;
import pl.dolega.sdjpaintro.author.AuthorDaoJDBCTemplate;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorDaoJDBCTemplateTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    AuthorDao authorDao;

    @BeforeEach
    void setUp() {
        authorDao = new AuthorDaoJDBCTemplate(jdbcTemplate);
    }

    @Test
    void findAllByLastName() {
        List<Author> authors = authorDao.findAllByLastName("Smith");
        assertThat(authors).isNotNull();
    }

    @Test
    void findAllByLastNameSortedByFirstNameRec1Pg0() {
        List<Author> authors = authorDao.findAllByLastName("Smith", PageRequest.of(0, 10));
        assertThat(authors).isNotNull();
        assertThat(authors.get(0).getFirstName()).isEqualTo("Ahmed");
    }

    @Test
    void findAllByLastNameSortedByFirstNameRec1Pg1() {
        List<Author> authors = authorDao.findAllByLastName("Smith", PageRequest.of(1, 10));
        assertThat(authors).isNotNull();
        assertThat(authors.get(0).getFirstName()).isEqualTo("Dinesh");
    }
}
