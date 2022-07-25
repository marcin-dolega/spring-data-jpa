package pl.dolega.sdjpaintro.jdbc.author;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final DataSource source;
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
                return new Author(id, rs.getString("first_name"), rs.getString("last_name"));
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
                return new Author(rs.getLong("id"), firstName, lastName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeTransaction();
        }
        return null;
    }

    private void closeTransaction() {
        try {
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
