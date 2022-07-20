package pl.dolega.sdjpaintro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dolega.sdjpaintro.domain.composite.AuthorEmbedded;
import pl.dolega.sdjpaintro.domain.composite.NameId;

public interface AuthorEmbeddedRepository extends JpaRepository<AuthorEmbedded, NameId> {
}
