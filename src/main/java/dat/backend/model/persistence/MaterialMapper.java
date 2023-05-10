package dat.backend.model.persistence;

import dat.backend.model.entities.Material;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
