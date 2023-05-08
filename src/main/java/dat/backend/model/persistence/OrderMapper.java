package dat.backend.model.persistence;

import dat.backend.model.entities.Material;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    public static List<Order> getAllOrders(ConnectionPool connectionPool) throws DatabaseException {
        List<Order> result = new ArrayList<>();

        String sql = "SELECT * FROM semesteropgave.order";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("order_id");
                    int userId = rs.getInt("user_id");
                    String status = rs.getString("status");
                    int width = rs.getInt("width");
                    int height = rs.getInt("height");
                    int length = rs.getInt("length");

                    User user = UserMapper.getUserById(userId, connectionPool);

                    Order order = new Order(user, status, width, height,length);
                    order.setId(id);
                    order.setMaterials(getOrderMaterials(id, connectionPool));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }

        return result;
    }

    private static List<Material> getOrderMaterials(int id, ConnectionPool connectionPool) throws DatabaseException {
        List<Material> materials = new ArrayList<>();

        String sql = "SELECT * FROM semesteropgave.orderLinking WHERE order_id=?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int materialId = rs.getInt("material_id");
                    int amount = rs.getInt("material_amount");
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }

        return materials;
    }
}
