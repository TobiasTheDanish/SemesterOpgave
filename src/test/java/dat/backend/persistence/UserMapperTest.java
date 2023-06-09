package dat.backend.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.UserFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest
{
    private final static String USER = "dev";
    private final static String PASSWORD = "3r!DE32*/fDe";
    private final static String URL = "jdbc:mysql://206.81.31.86:3306/semesteropgave_test";

    private static ConnectionPool connectionPool;

    @BeforeAll
    public static void setUpClass()
    {
        connectionPool = new ConnectionPool(USER, PASSWORD, URL, true);

        try (Connection testConnection = connectionPool.getConnection())
        {
            try (Statement stmt = testConnection.createStatement())
            {
                // Create test database - if not exist
                stmt.execute("CREATE DATABASE IF NOT EXISTS semesteropgave_test;");

                stmt.execute("CREATE TABLE IF NOT EXISTS semesteropgave_test.user LIKE semesteropgave.user");
                stmt.execute("CREATE TABLE IF NOT EXISTS semesteropgave_test.order LIKE semesteropgave.order");
                stmt.execute("CREATE TABLE IF NOT EXISTS semesteropgave_test.orderLinking LIKE semesteropgave.orderLinking");
                stmt.execute("CREATE TABLE IF NOT EXISTS semesteropgave_test.material LIKE semesteropgave.material");
            }
        }
        catch (SQLException throwables)
        {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }

    @BeforeEach
    void setUp()
    {
        try (Connection testConnection = connectionPool.getConnection())
        {
            try (Statement stmt = testConnection.createStatement())
            {
                // TODO: Remove all rows from all tables - add your own tables here
                stmt.execute("delete from semesteropgave_test.user");

                // TODO: Insert a few users - insert rows into your own tables here
                stmt.execute("insert into semesteropgave_test.user (email, password, role) " +
                        "values ('user','1234',1),('admin','1234',0), ('ben','1234',1)");
            }
        }
        catch (SQLException throwables)
        {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }

    @Test
    void testConnection() throws SQLException
    {
        Connection connection = connectionPool.getConnection();
        assertNotNull(connection);
        connection.close();
    }

    @Test
    void login() throws DatabaseException
    {
        User expectedUser = new User("user", "1234", 1);
        User actualUser = UserFacade.login("user", "1234", connectionPool);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void invalidPasswordLogin() throws DatabaseException
    {
        assertThrows(DatabaseException.class, () -> UserFacade.login("user", "123", connectionPool));
    }

    @Test
    void invalidUserNameLogin() throws DatabaseException
    {
        assertThrows(DatabaseException.class, () -> UserFacade.login("bob", "1234", connectionPool));
    }

    @Test
    void createUser() throws DatabaseException
    {
        User newUser = UserFacade.createUser("jill", "1234", 1, connectionPool);
        User logInUser = UserFacade.login("jill", "1234", connectionPool);
        User expectedUser = new User("jill", "1234", 1);
        assertEquals(expectedUser, newUser);
        assertEquals(expectedUser, logInUser);

    }
}