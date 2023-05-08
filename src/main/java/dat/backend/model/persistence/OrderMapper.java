package dat.backend.model.persistence;

import dat.backend.model.entities.Material;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import org.javatuples.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class OrderMapper {
    protected static List<Order> getAllOrders(ConnectionPool connectionPool) throws DatabaseException {
        List<Order> result = new ArrayList<>();

        String sql = "SELECT * FROM semesteropgave.order";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("order_id");

                    result.add(getOrderFromResultSet(rs, id, connectionPool));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }

        return result;
    }

    protected static Order getOrderById(int id, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT * FROM semesteropgave.order WHERE order_id=?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    return getOrderFromResultSet(rs, id, connectionPool);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }

        return null;
    }

    private static Order getOrderFromResultSet(ResultSet rs, int id, ConnectionPool connectionPool) throws SQLException, DatabaseException {
        int userId = rs.getInt("user_id");
        int status = rs.getInt("status");
        int width = rs.getInt("width");
        int height = rs.getInt("height");
        int length = rs.getInt("length");

        User user = UserMapper.getUserById(userId, connectionPool);

        Order order = new Order(user, status, width, height,length);
        order.setId(id);
        order.setMaterials(getOrderMaterials(id, connectionPool));

        return order;
    }

    protected static List<Pair<Material, Integer>> getOrderMaterials(int id, ConnectionPool connectionPool) throws DatabaseException {
        List<Pair<Material, Integer>> materials = new ArrayList<>();

        String sql = "SELECT * FROM semesteropgave.orderLinking WHERE order_id=?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int materialId = rs.getInt("material_id");
                    int amount = rs.getInt("material_amount");
                    Material m = MaterialMapper.getMaterialById(materialId, connectionPool);

                    materials.add(new Pair<>(m, amount));
                }
            }
            return materials;
        }

    protected static boolean createOrder(Order order, ConnectionPool connectionPool) throws DatabaseException {
            String sql = "INSERT INTO semesteropgave.order (user_id, status, width, height, length) values (?, ?, ?, ?, ?)";
            try (Connection connection = connectionPool.getConnection()) {
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
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
}
