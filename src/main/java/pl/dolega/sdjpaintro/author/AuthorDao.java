package pl.dolega.sdjpaintro.author;


public interface AuthorDao {

    Author getById(Long id);
    Author findByName(String firstName, String lastName);
    Author saveNewAuthor(Author author);
    Author updateAuthor(Author author);
    void deleteAuthorById(Long id);
}
