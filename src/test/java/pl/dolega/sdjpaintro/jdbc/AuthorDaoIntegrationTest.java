package pl.dolega.sdjpaintro.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;
import pl.dolega.sdjpaintro.jdbc.author.Author;
import pl.dolega.sdjpaintro.jdbc.author.AuthorDao;
import pl.dolega.sdjpaintro.jdbc.author.AuthorDaoImpl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("local")
@DataJpaTest
@Import(AuthorDaoImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorDaoIntegrationTest {

    @Autowired
    AuthorDao authorDao;

    @Test
    void testGetAuthorById() {
        Author author = authorDao.getById(1L);
        assertThat(author).isNotNull();
    }

    @Test
    void testGetAuthorByName() {
        Author author = authorDao.getByName("Craig", "Walls");
        assertThat(author).isNotNull();
    }

    @Test
    void testSaveAuthor() {
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Thompson");
        Author saved = authorDao.saveNewAuthor(author);

        assertThat(saved).isNotNull();
    }

    @Test
    void testUpdateAuthor() {
        Author author = new Author();
        author.setFirstName("Kido");
        author.setLastName("Dog");

        Author saved = authorDao.saveNewAuthor(author);

        saved.setFirstName("John");
        saved.setLastName("Thompson");

        Author updated = authorDao.updateAuthor(saved);

        assertEquals("John", updated.getFirstName());
    }

}
