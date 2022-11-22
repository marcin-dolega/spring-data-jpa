package pl.dolega.sdjpaintro.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Object> findByTitle(String title);
}
