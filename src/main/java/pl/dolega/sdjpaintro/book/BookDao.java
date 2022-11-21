package pl.dolega.sdjpaintro.book;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookDao {

    Book getById(Long id);
    Book findBookByTitle(String title);
    Book saveNewBook(Book book);
    Book updateBook(Book book);
    void deleteBookById(Long id);
    Book findByISBN(String isbn);
    Book findBookByTitleNQ(String title);
    List<Book> findAll();
    List<Book> findAll(int pageSize, int offset);
    List<Book> findAll(Pageable pageable);
    List<Book> findALlSortByTitle(Pageable pageable);
    Book findBookByTitleCriteria(String title);
    Book findBookByTitleNative(String title);
}
