package pl.dolega.sdjpaintro.author;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class AuthorDaoHibernate implements AuthorDao {

    private final EntityManagerFactory emf;

    public AuthorDaoHibernate(EntityManagerFactory emf) {
        this.emf = emf;
    }

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

    @Override
    public List<Author> findAllByLastName(String lastName) {
        return null;
    }

    @Override
    public List<Author> findAllByLastName(String lastName, Pageable pageable) {
        EntityManager em = getEntityManager();
        try {
            String sql = "SELECT a FROM Author a where a.lastName = :lastName order by a.firstName";
            TypedQuery<Author> query = em.createQuery(sql, Author.class);
            query.setParameter("lastName", lastName);
            query.setFirstResult(Math.toIntExact(pageable.getOffset()));
            query.setMaxResults(pageable.getPageSize());
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Author> findAll() {
        return null;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

}
