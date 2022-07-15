package pl.dolega.sdjpaintro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dolega.sdjpaintro.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
