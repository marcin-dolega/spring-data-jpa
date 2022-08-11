package pl.dolega.sdjpaintro.author;

import org.springframework.stereotype.Component;

@Component
public class AuthorDaoImpl implements AuthorDao{

    @Override
    public Author getById(Long id) {
        return null;
    }

    @Override
    public Author findByName(String firstName, String lastName) {
        return null;
    }

    @Override
    public Author saveAuthor(Author author) {
        return null;
    }

    @Override
    public Author updateAuthor(Author author) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
