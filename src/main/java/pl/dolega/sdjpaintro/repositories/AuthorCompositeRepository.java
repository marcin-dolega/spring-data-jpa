package pl.dolega.sdjpaintro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dolega.sdjpaintro.domain.composite.AuthorComposite;
import pl.dolega.sdjpaintro.domain.composite.NameId;

public interface AuthorCompositeRepository extends JpaRepository<AuthorComposite, NameId> {
}
