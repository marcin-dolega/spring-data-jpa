package pl.dolega.sdjpaintro.author;

import java.util.List;

public interface AuthorDao {

    Author getAuthorById(Long id);
    Author findAuthorByName(String firstName, String lastName);
    Author saveAuthor(Author author);
    Author updateAuthor(Author author);
    void deleteById(Long id);
    List<Author> listAuthorByLastNameLike(String lastName);
    List<Author> findAll();

}
