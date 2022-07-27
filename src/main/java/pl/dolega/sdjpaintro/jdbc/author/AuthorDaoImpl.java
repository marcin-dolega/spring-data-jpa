package pl.dolega.sdjpaintro.jdbc.author;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final DataSource source;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public AuthorDaoImpl(DataSource source) {
        this.source = source;
    }

    @Override
    public Author getById(Long id) {
        try {
            ps = source.getConnection().prepareStatement("SELECT * FROM author WHERE id = ?");
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return getAuthorFromRS();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeTransaction();
        }
        return null;
    }

    @Override
    public Author getByName(String firstName, String lastName) {
        try {
            ps = source.getConnection().prepareStatement("SELECT * FROM author WHERE first_name = ? AND last_name = ?");
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            rs = ps.executeQuery();
            if (rs.next()) {
                return getAuthorFromRS();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeTransaction();
        }
        return null;
    }

    @Override
    public Author saveNewAuthor(Author author) {
        try {
            con = source.getConnection();
            ps = con.prepareStatement("INSERT INTO author (first_name, last_name) VALUES (?, ?)");
            ps.setString(1, author.getFirstName());
            ps.setString(2, author.getLastName());
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
    public Author updateAuthor(Author author) {
        try {
            ps = source.getConnection().prepareStatement("UPDATE author SET first_name = ?, last_name = ? WHERE author.id = ?");
            ps.setString(1, author.getFirstName());
            ps.setString(2, author.getLastName());
            ps.setLong(3, author.getId());
            ps.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeTransaction();
        }
        return this.getById(author.getId());
    }

    @Override
    public void deleteAuthor(Long id) {

        try {
            con = source.getConnection();
            ps = con.prepareStatement("DELETE FROM author where id = ?");
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeTransaction();
        }
    }

    public Author getAuthorFromRS() throws SQLException {
        return new Author(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"));
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
