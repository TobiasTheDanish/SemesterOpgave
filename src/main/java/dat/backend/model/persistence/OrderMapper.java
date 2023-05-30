package dat.backend.model.persistence;

import dat.backend.model.entities.Material;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.Status;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import org.javatuples.Pair;

import java.sql.*;
import java.util.*;

import static dat.backend.model.entities.Status.*;

import java.util.ArrayList;
import java.util.List;

class OrderMapper {
    protected static List<Order> getAllOrders(ConnectionPool connectionPool) throws DatabaseException {
        List<Order> result = new ArrayList<>();
        Map<Integer, List<Pair<Material, Integer>>> orderLinkMap = MaterialFacade.getOrderLinkMap(connectionPool);

        String sql = "SELECT * FROM semesteropgave.order";

        try (Connection connection = connectionPool.getConnection()) {
            Map<Integer, User> userMap = UserMapper.getAllUsers(connection);

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("order_id");
                    int userId = rs.getInt("user_id");
                    Status status = Status.values()[rs.getInt("status")];
                    int width = rs.getInt("width");
                    int height = rs.getInt("height");
                    int length = rs.getInt("length");
                    boolean isInactive = rs.getBoolean("isInactive");

                    User user = userMap.get(userId);

                    Order order = new Order(user, status, width, height, length);
                    order.setId(id);
                    order.setInactive(isInactive);
                    order.setMaterials(orderLinkMap.get(id));

                    result.add(order);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }


        return result;
    }


    protected static boolean createOrder(Order order, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "INSERT INTO semesteropgave.order (user_id, status, width, height, length, isInactive) values (?, ?, ?, ?, ?, ?)";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, order.getUser().getId());
                ps.setInt(2, order.getStatus().ordinal());
                ps.setInt(3, order.getWidth());
                ps.setInt(4, order.getHeight());
                ps.setInt(5, order.getLength());
                ps.setBoolean(6, order.isInactive());

                int rowsAffected = ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    order.setId(rs.getInt(1));
                }
                //Returns true if the order was created, otherwise returns false.
                return rowsAffected == 1;
            }

        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }

    }

    protected static List<Order> viewOrder(User user, ConnectionPool connectionPool) throws DatabaseException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM `order` WHERE user_id = ?";
        try (Connection connection = connectionPool.getConnection()) {

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, user.getId());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {

                    int id = rs.getInt("order_id");
                    int width = rs.getInt("width");
                    int height = rs.getInt("height");
                    int length = rs.getInt("length");
                    boolean isInactive = rs.getBoolean("isInactive");
                    int statusOrdinal = rs.getInt("status");
                    Order order = new Order(user, Status.values()[statusOrdinal], width, height, length);
                    order.setId(id);
                    order.setInactive(isInactive);
                    orders.add(order);
                }
                return orders;
            }
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    protected static boolean removeOrder(int orderId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "UPDATE semesteropgave.order o SET o.isInactive = 1 WHERE o.order_id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderId);
                int rowsAffected = ps.executeUpdate();
                return rowsAffected == 1;
            }
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }


    protected static boolean updateStatus(int statusOrdinal, int orderId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "UPDATE semesteropgave.order o SET o.status = ? WHERE o.order_id = ?";
        try (Connection connection = connectionPool.getConnection()){
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setInt(1, statusOrdinal);
                ps.setInt(2, orderId);
                int rowsAffected = ps.executeUpdate();
                return rowsAffected == 1;
            }
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }


    protected static boolean editOrder(Order order, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "UPDATE semesteropgave.order o SET o.width = ?, o.height = ?, o.length = ? WHERE o.order_id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, order.getWidth());
                ps.setInt(2, order.getHeight());
                ps.setInt(3, order.getLength());
                ps.setInt(4, order.getId());
                int rowsAffected = ps.executeUpdate();
                return rowsAffected == 1;
            }
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
