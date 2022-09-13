package pl.dolega.sdjpaintro.book;

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


}
