package pl.dolega.sdjpaintro.author;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final AuthorRepo authorRepo;

    public AuthorDaoImpl(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public Author getById(Long id) {
        return authorRepo.getReferenceById(id);
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        return authorRepo.findAuthorByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Author saveNewAuthor(Author author) {
        return authorRepo.save(author);
    }

    @Transactional
    @Override
    public Author updateAuthor(Author author) {
        Author foundAuthor = authorRepo.getReferenceById(author.getId());
        foundAuthor.setFirstName(author.getFirstName());
        foundAuthor.setLastName(author.getLastName());
        return authorRepo.save(foundAuthor);
    }

    @Override
    public void deleteAuthorById(Long id) {
        authorRepo.deleteById(id);
    }
}