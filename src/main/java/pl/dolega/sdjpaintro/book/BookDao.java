package pl.dolega.sdjpaintro.book;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookDao {

    Book getById(Long id);
    Book findByTitle(String title);
    Book saveNewBook(Book book);
    Book updateBook(Book book);

    void deleteById(Long id);

    List<Book> findAll();
    List<Book> findAll(int pageSize, int offset);
    List<Book> findAll(Pageable pageable);
    List<Book> findAllSortByTitle(Pageable pageable);

}
