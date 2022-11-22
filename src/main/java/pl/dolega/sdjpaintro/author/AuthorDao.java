package pl.dolega.sdjpaintro.author;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorDao {

    Author getById(Long id);
    Author findByName(String firstName, String lastName);
    Author saveAuthor(Author author);
    Author updateAuthor(Author author);
    void deleteById(Long id);
    List<Author> findAllByLastName(String lastName);
    List<Author> findAllByLastName(String lastName, Pageable pageable);
    List<Author> findAll();
    Author findAuthorByNameCriteria(String firstName, String lastName);
    Author findAuthorByNameNative(String firstName, String lastName);

    List<Author> listAuthorByLastNameLike(String lastName);
}
