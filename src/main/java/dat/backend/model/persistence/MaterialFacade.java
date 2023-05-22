package dat.backend.model.persistence;

import dat.backend.model.entities.Material;
import dat.backend.model.entities.Order;
import dat.backend.model.exceptions.DatabaseException;
import org.javatuples.Pair;

import java.util.List;

public class MaterialFacade {
    public static Material getMaterialById(int id, ConnectionPool connectionPool) throws DatabaseException {
       return MaterialMapper.getMaterialById(id, connectionPool);
    }

    public static Material getMaterialByNameAndLength(String name, int length, ConnectionPool connectionPool) throws DatabaseException {
        return MaterialMapper.getMaterialByNameAndLength(name, length, connectionPool);
    }
    public static void createOrderLink(Order order, List<Pair<Material, Integer>> materials, ConnectionPool connectionPool) throws DatabaseException {
        MaterialMapper.createOrderLink(order, materials, connectionPool);
    }
}
