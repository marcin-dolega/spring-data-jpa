package pl.dolega.sdjpaintro.book;

public interface BookDao {

    Book getById(Long id);
    Book findByTitle(String title);
    Book save(Book book);
    Book update(Book book);
    void deleteById(Long id);
}
