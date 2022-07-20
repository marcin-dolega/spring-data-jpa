package pl.dolega.sdjpaintro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dolega.sdjpaintro.domain.BookNatural;

public interface BookNaturalRepository extends JpaRepository<BookNatural, String> {
}
