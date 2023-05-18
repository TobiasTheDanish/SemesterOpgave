package dat.backend.model.persistence;

import dat.backend.model.entities.Material;
import dat.backend.model.entities.Order;
import dat.backend.model.exceptions.DatabaseException;
import org.javatuples.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

class MaterialMapper {

    protected static Material getMaterialById(int id, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT * FROM semesteropgave.material WHERE material_id=?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    String name = rs.getString("name");
                    int width = rs.getInt("width");
                    int height = rs.getInt("height");
                    int length = rs.getInt("length");
                    String description = rs.getString("description");

                    Material material = new Material(name, width, height, length, description);
                    material.setPricePrMeter(rs.getDouble("pricePrMeter"));
                    return material;
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return null;
    }

    protected static Material getMaterialByNameAndLength(String name, int length, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT * FROM material WHERE name = ? AND length >= ? ORDER BY length ASC limit 1";
        try (Connection connection = connectionPool.getConnection()){
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setString(1, name);
                ps.setInt(2, length);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    int width = rs.getInt("width");
                    int height = rs.getInt("height");
                    int newLength = rs.getInt("length");
                    String description = rs.getString("description");
                    Material material = new Material(name, width, height, newLength, description);
                    material.setId(rs.getInt("material_id"));
                    material.setPricePrMeter(rs.getDouble("pricePrMeter"));
                    return material;
                }

            }
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return null;
    }

    protected static void createOrderLink(Order order, List<Pair<Material, Integer>> materials, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "INSERT INTO orderLinking (order_id, material_id, material_amount) VALUES (?, ?, ?)";
        try (Connection connection = connectionPool.getConnection()){
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                for (Pair<Material, Integer> pair : materials){
                    ps.setInt(1, order.getId());
                    ps.setInt(2, pair.getValue0().getId());
                    ps.setInt(3, pair.getValue1());
                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected < 1) {
                        throw new DatabaseException("Didn't create an order linking record! Check if the order exists in the db.");
                    }
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }



    protected static Map<Integer, List<Pair<Material, Integer>>> getOrderLinkMap(ConnectionPool connectionPool) throws DatabaseException {
        Map<Integer, List<Pair<Material, Integer>>> orderLinkMap = new HashMap<>();
        try (Connection connection = connectionPool.getConnection()){
            Map<Integer, Material> materialMap = getMaterialMap(connection);
            String sql = "SELECT * FROM orderLinking";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int orderId = rs.getInt(2);
                    int materialId = rs.getInt(3);
                    int materialAmount = rs.getInt(4);

                    Pair<Material, Integer> materialPair = new Pair<>(materialMap.get(materialId), materialAmount);
                    List<Pair<Material, Integer>> materialPairs = new ArrayList<>();

                    if (orderLinkMap.containsKey(orderId)) {
                        materialPairs = orderLinkMap.get(orderId);
                    }

                    materialPairs.add(materialPair);

                    orderLinkMap.put(orderId, materialPairs);
                }
            }


        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }

        return orderLinkMap;
    }

    private static Map<Integer, Material> getMaterialMap(Connection connection) throws DatabaseException {
        Map<Integer, Material> materialMap = new HashMap<>();
        String sql = "SELECT * FROM material";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int width = rs.getInt(3);
                int height = rs.getInt(4);
                int length = rs.getInt(5);
                String description = rs.getString(6);
                double price = rs.getDouble(7);

                Material m = new Material(name, width, height, length, description);
                m.setId(id);
                m.setPricePrMeter(price);

                materialMap.put(id, m);
            }
        } catch (SQLException throwables) {
            throw new DatabaseException(throwables.getMessage());
        }

        return materialMap;
    }
}
