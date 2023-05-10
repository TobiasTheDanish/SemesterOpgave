package dat.backend.model.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.entities.Status;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dat.backend.model.entities.Status.*;

public class OrderMapper {

    protected static boolean createOrder(Order order, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "INSERT INTO semesteropgave.order (user_id, status, width, height, length) values (?, ?, ?, ?, ?)";
        try (Connection connection = connectionPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, order.getUser().getId());
                ps.setInt(2, order.getStatus().ordinal());
                ps.setInt(3, order.getWidth());
                ps.setInt(4, order.getHeight());
                ps.setInt(5, order.getLength());

                int rowsAffected = ps.executeUpdate();

                //Returns true if the order was created, otherwise returns false.
                return rowsAffected == 1;
            }

        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }

    }

    protected static List<Order> viewOrder(User user, ConnectionPool connectionPool) throws DatabaseException{
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM `order` WHERE user_id = ?";
        try(Connection connection = connectionPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
               ps.setInt(1, user.getId());
               ResultSet rs = ps.executeQuery();
               while (rs.next()){

                   int id = rs.getInt("order_id");
                   int width = rs.getInt("width");
                   int height = rs.getInt("height");
                   int length = rs.getInt("length");
                   int statusOrdinal = rs.getInt("status");
                   Order order = new Order(user, Status.values()[statusOrdinal], width, height, length);
                   order.setId(id);
                   orders.add(order);
               }
               return orders;
            }
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
