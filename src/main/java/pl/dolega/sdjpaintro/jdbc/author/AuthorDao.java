package pl.dolega.sdjpaintro.jdbc.author;

public interface AuthorDao {

    Author getById(Long id);
    Author getByName(String firstName, String lastName);
    Author saveNewAuthor(Author author);
    Author updateAuthor(Author author);
    void deleteAuthor(Long id);
}
