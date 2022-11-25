package pl.dolega.sdjpaintro.dao.author;

import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import pl.dolega.sdjpaintro.author.Author;
import pl.dolega.sdjpaintro.author.AuthorDao;
import pl.dolega.sdjpaintro.author.AuthorDaoHibernate;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorDaoHibernateTest {

    @Autowired
    EntityManagerFactory emf;

    AuthorDao authorDao;

    @BeforeEach
    void setUp() {
        authorDao = new AuthorDaoHibernate(emf);
    }

    @Test
    void findAllAuthorsByLastNameSortedByFirstName() {
        List<Author> authors = authorDao.findAllByLastName("Smith", PageRequest.of(0, 10));
        assertThat(authors).isNotNull();
        assertThat(authors.get(0).getFirstName()).isEqualTo("Ahmed");
    }

    @Test
    void findAllAuthorsByLastNameSortFirstNameAsc() {
        List<Author> authors = authorDao.findAllByLastName("Smith",
                PageRequest.of(0, 10, Sort.by(Sort.Order.asc("firstname"))));
        assertThat(authors).isNotNull();
        assertThat(authors.size()).isEqualTo(10);
        assertThat(authors.get(0).getFirstName()).isEqualTo("Ahmed");
    }

    @Test
    void findAllAuthorsByLastNameSortFirstNameDesc() {
        List<Author> authors = authorDao.findAllByLastName("Smith",
                PageRequest.of(0, 10, Sort.by(Sort.Order.desc("firstname"))));
        assertThat(authors).isNotNull();
        assertThat(authors.size()).isEqualTo(10);
        assertThat(authors.get(0).getFirstName()).isEqualTo("Yugal");
    }

}
