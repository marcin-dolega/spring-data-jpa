package pl.dolega.sdjpaintro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dolega.sdjpaintro.domain.AuthorUuid;

import java.util.UUID;

public interface AuthorUuidRepository extends JpaRepository<AuthorUuid, UUID> {
}
