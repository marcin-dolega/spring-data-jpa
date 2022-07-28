package pl.dolega.sdjpaintro.jdbc.author;

public interface AuthorDao {

    Author getById(Long id);
    Author findByName(String firstName, String lastName);
    Author saveNewAuthor(Author author);
    Author updateAuthor(Author author);
    void deleteAuthor(Long id);
}
