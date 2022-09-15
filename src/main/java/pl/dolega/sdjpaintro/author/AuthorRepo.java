package pl.dolega.sdjpaintro.author;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author, Long> {

    Author findAuthorByFirstNameAndLastName(String firstName, String lastName);
}
