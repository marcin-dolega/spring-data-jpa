package pl.dolega.sdjpaintro.author;

public interface AuthorDao {

    Author getById(Long id);
    Author findByName(String firstName, String lastName);
    Author saveAuthor(Author author);
    Author updateAuthor(Author author);
    void deleteById(Long id);
}
