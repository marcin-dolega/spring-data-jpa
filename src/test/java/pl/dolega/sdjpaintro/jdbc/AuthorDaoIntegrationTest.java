package pl.dolega.sdjpaintro.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import pl.dolega.sdjpaintro.author.Author;
import pl.dolega.sdjpaintro.author.AuthorDao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"pl.dolega.sdjpaintro.author"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AuthorDaoIntegrationTest {

    @Autowired
    AuthorDao authorDao;

    @Test
    void getAuthorByIdTest() {
        Author author = authorDao.getById(1L);
        assertThat(author).isNotNull();
    }

    @Test
    void findAuthorByNameTest() {
        Author author = authorDao.findAuthorByName("Craig", "Walls");
        assertThat(author).isNotNull();
    }

    @Test
    void saveAuthorTest() {
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Thompson");
        Author saved = authorDao.saveNewAuthor(author);
        assertThat(saved).isNotNull();
    }

    @Test
    void updateAuthorTest() {
        Author author = new Author("Kido", "Dog");
        Author saved = authorDao.saveNewAuthor(author);
        saved.setFirstName("John");
        saved.setLastName("Thompson");
        Author updated = authorDao.updateAuthor(saved);
        assertEquals("John", updated.getFirstName());
    }

    @Test
        void deleteAuthorTest() {
        Author author = new Author("John", "Thompson");
        Author saved = authorDao.saveNewAuthor(author);
        authorDao.deleteAuthorById(saved.getId());
        Author deleted = authorDao.getById(saved.getId());
        assertThat(deleted).isNull();
    }
}
