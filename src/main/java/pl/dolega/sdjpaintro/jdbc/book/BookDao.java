package pl.dolega.sdjpaintro.jdbc.book;

public interface BookDao {

    Book saveNewBook(Book book);
    Book getById(Long id);
    Book findByTitle(String title);
    Book updateBook(Book book);
    void deleteBook(Long id);
}
