package dat.backend.model.persistence;

import dat.backend.model.entities.Material;
import dat.backend.model.exceptions.DatabaseException;
import org.graalvm.compiler.replacements.IntrinsicGraphBuilder;
import org.javatuples.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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

                    return new Material(name, width, height, length, description);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return null;
    }

    protected static Map<Integer, Pair<Integer, Material>> getOrderLinkMap(ConnectionPool connectionPool) throws DatabaseException {
        try {
            getMaterialMap(connectionPool.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static Map<Integer, Material> getMaterialMap(Connection connection) throws DatabaseException {
        Map<Integer, Material> materialMap = new HashMap<>();
        String sql = "SELECT * FROM semesteropgave.material";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);

                System.out.println("ID: " + id + ", Name: " + name);
            }
        } catch (SQLException throwables) {
            throw new DatabaseException(throwables.getMessage());
        }

        return materialMap;
    }
}
