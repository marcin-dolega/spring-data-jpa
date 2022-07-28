package pl.dolega.sdjpaintro.jdbc.book;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class BookDaoImpl implements BookDao {

    private final DataSource source;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public BookDaoImpl(DataSource source) {
        this.source = source;
    }

    @Override
    public Book getById(Long id) {
        try {
            ps = source.getConnection().prepareStatement("SELECT * FROM book WHERE id = ?");
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return getBookFromRS();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeTransaction();
        }
        return null;
    }


    @Override
    public Book findByTitle(String title) {
        try {
            ps = source.getConnection().prepareStatement("SELECT * FROM book WHERE title = ?");
            ps.setString(1, title);
            rs = ps.executeQuery();
            if (rs.next()) {
                return getBookFromRS();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeTransaction();
        }
        return null;
    }

    @Override
    public Book saveNewBook(Book book) {
        try {
            con = source.getConnection();
            ps = con.prepareStatement("INSERT INTO book (title, isbn, publisher) VALUES (?, ?, ?)");
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getIsbn());
            ps.setString(3, book.getPublisher());
            ps.execute();

            Statement statement = con.createStatement();
            rs = statement.executeQuery("SELECT LAST_INSERT_ID()");
            if (rs.next()) {
                Long savedId = rs.getLong(1);
                return this.getById(savedId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeTransaction();
        }
        return null;
    }


    @Override
    public Book updateBook(Book book) {
        try {
            ps = source.getConnection().prepareStatement("UPDATE book SET title = ?, isbn = ?, publisher = ? WHERE book.id = ?");
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getIsbn());
            ps.setString(3, book.getPublisher());
            ps.setLong(4, book.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeTransaction();
        }
        return this.getById(book.getId());
    }

    @Override
    public void deleteBook(Long id) {
        try {
            con = source.getConnection();
            ps = con.prepareStatement("DELETE FROM book where id = ?");
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeTransaction();
        }
    }

    public Book getBookFromRS() throws SQLException {
        return new Book(rs.getLong("id"), rs.getString("title"), rs.getString("isbn"), rs.getString("publisher"), rs.getLong("author_id"));
    }

    private void closeTransaction() {
        try {
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
