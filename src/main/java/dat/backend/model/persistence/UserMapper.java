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
                    String fName = rs.getString("firstName");
                    String lName = rs.getString("lastName");
                    int phoneNum = rs.getInt("phoneNr");
                    int zip = rs.getInt("zipCode");
                    int id = rs.getInt("user_id");
                    user = new User(username, password, role);
                    user.setId(id);
                    user.setFirstName(fName);
                    user.setLastName(lName);
                    user.setPhoneNr(phoneNum);
                    user.setZipCode(zip);
                    return user;
                } else
                {
                    throw new DatabaseException("Wrong username or password");
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
    }

    static User createUser(String username, String password, int role, ConnectionPool connectionPool) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");
        User user;

        String sql = "INSERT INTO semesteropgave.user (email, password, role) values (?,?,?)";
        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setInt(3, role);
                ps.setString(4, "");
                ps.setString(5, "");
                ps.setInt(6, 0);
                ps.setInt(7, 0);
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


    //TODO check this method. UserID/Email idk what to use.
    public static User getUser(int id, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT * FROM user WHERE user_id=?";

        try (Connection connection = connectionPool.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String email = rs.getString("email");
                String pw = rs.getString("password");
                int role = rs.getInt("role");

                return new User(email, pw, role);
            }
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return null;
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

    protected static int getId(String email, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT user_id FROM semesteropgave.user WHERE email=?";

        try (Connection connection = connectionPool.getConnection()){
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        //This should never happen
        return -1;
    }


    static User getUserById(int userId, ConnectionPool connectionPool) throws DatabaseException {
        User user = null;

        String sql = "SELECT * FROM semesteropgave.user WHERE user_id=?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, userId);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    int role = rs.getInt("role");
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    int phoneNr = rs.getInt("phoneNr");
                    int zipCode = rs.getInt("zipCode");

                    user = new User(email, password, role);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setPhoneNr(phoneNr);
                    user.setZipCode(zipCode);

                    return user;
                }
            }
        } catch (SQLException throwables) {
            throw new DatabaseException(throwables.getMessage());
        }

        return null;
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
