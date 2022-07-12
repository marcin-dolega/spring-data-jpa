package pl.dolega.sdjpaintro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dolega.sdjpaintro.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
