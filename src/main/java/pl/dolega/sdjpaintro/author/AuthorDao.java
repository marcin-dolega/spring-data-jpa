package pl.dolega.sdjpaintro.author;

public interface AuthorDao {

    Author getAuthorById(Long id);
    Author findAuthorByName(String firstName, String lastName);
    Author saveAuthor(Author author);
    Author updateAuthor(Author author);
    void deleteById(Long id);
}
