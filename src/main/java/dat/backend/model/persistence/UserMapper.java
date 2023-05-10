package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class UserMapper
{
    static User login(String username, String password, ConnectionPool connectionPool) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");

        User user = null;

        String sql = "SELECT * FROM semesteropgave.user WHERE email = ? AND password = ?";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    int role = rs.getInt("role");
                    user = new User(username, password, role);
                } else
                {
                    throw new DatabaseException("Wrong username or password");
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
        return user;
    }

    static User createUser(String username, String password, int role, ConnectionPool connectionPool) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");
        User user;
        String sql = "insert into semesteropgave.user (email, password, role) values (?,?,?)";
        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setInt(3, role);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1)
                {
                    user = new User(username, password, role);
                } else
                {
                    throw new DatabaseException("The user with username = " + username + " could not be inserted into the database");
                }
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Could not insert username into database");
        }
        return user;
    }


    protected static boolean doesUserExist(String email, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT COUNT(user_id) FROM semesteropgave.user WHERE email=?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, email);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1) > 0 ;
                }
            }
        } catch (SQLException throwables) {
            throw new DatabaseException(throwables.getMessage());
        }

        return false;
    }

    static User updateUserProfile(User user, ConnectionPool connectionPool) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");
        String sql = "update semesteropgave.user set firstName=?, lastName=?, phoneNr=?, zipCode=? where email=?";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setInt(3, user.getPhoneNr());
                ps.setInt(4, user.getZipCode());
                ps.setString(5, user.getUsername());
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1)
                {
                    user.setFirstName(user.getFirstName());
                    user.setLastName(user.getLastName());
                    user.setPhoneNr(user.getPhoneNr());
                    user.setZipCode(user.getZipCode());
                    return user;
                } else
                {
                    throw new DatabaseException("The user with username = " + user.getUsername() + " could not be inserted into the database");
                }
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Could not insert information into database");
        }
    }
}
