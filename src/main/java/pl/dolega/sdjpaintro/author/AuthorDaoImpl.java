package pl.dolega.sdjpaintro.author;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

@Component
public class AuthorDaoImpl implements AuthorDao{

    private final EntityManagerFactory emf;

    public AuthorDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Author getAuthorById(Long id) {
        EntityManager em = getEntityManager();
        Author author = em.find(Author.class, id);
        return author;
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        TypedQuery<Author> query = getEntityManager().createQuery(
                "SELECT a FROM Author a WHERE a.firstName = :first_name and a.lastName = :last_name", Author.class
        );
        query.setParameter("first_name", firstName);
        query.setParameter("last_name", lastName);
        return query.getSingleResult();
    }

    @Override
    public Author saveAuthor(Author author) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(author);
        em.flush();
        em.getTransaction().commit();
        em.close();
        return author;
    }

    @Override
    public Author updateAuthor(Author author) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(author);
        em.flush();
        em.clear();
        em.close();
        return em.find(Author.class, author.getId());
    }

    @Override
    public void deleteById(Long id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Author author = em.find(Author.class, id);
        em.remove(author);
        em.flush();
        em.getTransaction().commit();
        em.close();
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
