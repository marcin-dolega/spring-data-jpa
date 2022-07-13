package pl.dolega.sdjpaintro;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.dolega.sdjpaintro.domain.Book;
import pl.dolega.sdjpaintro.repositories.BookRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class SpringBootTestSplice {

    @Autowired
    BookRepository bookRepository;

    @Test
    void testJPATestSplice() {
        long countBefore = bookRepository.count();

        bookRepository.save(new Book("My Book", "666", "Self"));

        long countAfter = bookRepository.count();

        assertThat(countBefore).isLessThan(countAfter);
    }
}
