package pl.dolega.sdjpaintro.book;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDaoImpl implements BookDao {

    private final EntityManagerFactory emf;

    public BookDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Book getById(Long id) {
        EntityManager em = getEntityManager();
        Book book = em.find(Book.class, id);
        em.close();
        return book;
    }

    @Override
    public Book findByTitle(String title) {
        TypedQuery<Book> query = getEntityManager().createQuery(
                "SELECT a FROM Book a WHERE a.title =:title", Book.class
        );
        query.setParameter("title", title);
        return query.getSingleResult();
    }

    @Override
    public Book saveNewBook(Book book) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(book);
        em.flush();
        em.getTransaction().commit();
        em.close();
        return book;
    }

    @Override
    public Book updateBook(Book book) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(book);
        em.flush();
        em.clear();
        Book savedBook = em.find(Book.class, book.getId());
        em.getTransaction().commit();
        em.close();
        return savedBook;
    }

    @Override
    public void deleteById(Long id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Book book = em.find(Book.class, id);
        em.remove(book);
        em.flush();
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Book> findAll() {
        EntityManager em = getEntityManager();

        try {
            TypedQuery<Book> typedQuery = em.createNamedQuery("book_find_all", Book.class);
            return typedQuery.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Book> findAll(int pageSize, int offset) {
        return null;
    }

    @Override
    public List<Book> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Book> findAllSortByTitle(Pageable pageable) {
        return null;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
