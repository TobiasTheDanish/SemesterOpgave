package dat.backend.model.services;

import dat.backend.model.entities.Material;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.Status;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.UserFacade;
import org.javatuples.Pair;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class MaterialListAlgorithmTest {

    private final static String USER = "dev";
    private final static String PASSWORD = "3r!DE32*/fDe";
    private final static String URL = "jdbc:mysql://206.81.31.86:3306/semesteropgave_test";

    private static ConnectionPool connectionPool;

    private Order order;

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

                stmt.execute("delete from semesteropgave_test.user");
                stmt.execute("delete from semesteropgave_test.orderLinking");
                stmt.execute("delete from semesteropgave_test.order");
                stmt.execute("delete from semesteropgave_test.material");


                stmt.execute("insert into semesteropgave_test.user (email, password, role) " +
                        "values ('user','1234',1),('admin','1234',0), ('ben','1234',1)");
                stmt.execute("INSERT INTO semesteropgave_test.material (name, width, height, length, description, pricePrMeter) " +
                        "VALUES ('Spær',5,20,360,'',54.95), ('Spær',5,20,390,'',54.95),('Spær',5,20,420,'',54.95), ('Spær',5,20,450,'',54.95), " +
                        "('Spær',5,20,480,'',54.95), ('Spær',5,20,510,'',54.95), ('Spær',5,20,540,'',54.95), ('Spær',5,20,570,'',54.95), ('Spær',5,20,600,'',54.95), " +
                        "('Stolpe',9,9,208,'',39.95), ('Stolpe',9,9,238,'',39.95), ('Stolpe',9,9,268,'',39.95), ('Stolpe',9,9,298,'',39.95), " +
                        "('Stolpe',9,9,369,'',39.95), ('Rem',5,20,360,'',54.95), ('Rem',5,20,390,'',54.95), ('Rem',5,20,420,'',54.95), ('Rem',5,20,450,'',54.95), " +
                        "('Rem',5,20,480,'',54.95), ('Rem',5,20,510,'',54.95), ('Rem',5,20,540,'',54.95), ('Rem',5,20,570,'',54.95), ('Rem',5,20,600,'',54.95)");
                User user = UserFacade.login("user", "1234", connectionPool);
                order = new Order(user, Status.BESTILT, 400, 290, 550);
            }
        }
        catch (SQLException | DatabaseException throwables)
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
    void calcMaterialList() {
        Order notAnActualOrder = new Order(null, Status.BESTILT, 10, 20, 30);
        notAnActualOrder.setId(-1);
        assertThrows(DatabaseException.class, () -> MaterialListAlgorithm.calcMaterialList(notAnActualOrder, connectionPool));
    }

    @Test
    void getPurlins() throws DatabaseException {
        Pair<Material, Integer> expected = new Pair<>(new Material("Rem", 5, 20, 570, null), 2);
        Pair<Material, Integer> actual = MaterialListAlgorithm.getPurlins(connectionPool, order.getLength());
        assertEquals(expected.getValue0(), actual.getValue0());
        assertEquals(expected.getValue1(), actual.getValue1());
    }

    @Test
    void getRafters() throws DatabaseException {
        Pair<Material, Integer> expected = new Pair<>(new Material("Spær", 5, 20, 420, null), 10);
        Pair<Material, Integer> actual = MaterialListAlgorithm.getRafters(connectionPool, order.getLength(), order.getWidth());
        assertEquals(expected.getValue0(), actual.getValue0());
        assertEquals(expected.getValue1(), actual.getValue1());
    }

    @Test
    void getPosts() throws DatabaseException {
        Pair<Material, Integer> expected = new Pair<>(new Material("Stolpe", 9, 9, 298, null), 6);
        Pair<Material, Integer> actual = MaterialListAlgorithm.getPosts(connectionPool, order.getLength(), order.getHeight());
        assertEquals(expected.getValue0(), actual.getValue0());
        assertEquals(expected.getValue1(), actual.getValue1());
    }
}