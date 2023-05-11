package dat.backend.model.persistence;

import dat.backend.model.entities.Material;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import org.javatuples.Pair;

import java.util.List;

public class OrderFacade {
    public static List<Order> getAllOrders(ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.getAllOrders(connectionPool);
    }

    public static Order getOrderById(int id, ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.getOrderById(id, connectionPool);
    }

    public static boolean createOrder(Order order, ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.createOrder(order, connectionPool);
    }

    public static List<Order> viewOrder(User user, ConnectionPool connectionPool) throws DatabaseException{
        return OrderMapper.viewOrder(user, connectionPool);
    }

    public static boolean removeOrder(int orderId, ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.removeOrder(orderId, connectionPool);
    }

    public static boolean updateStatus(int status, int orderId, ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.updateStatus(status, orderId, connectionPool);
    }

    public static List<Order> getAllOrdersWithoutMaterials(ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.getAllOrdersWithoutMaterials(connectionPool);
    }

    public static List<Pair<Material, Integer>> getOrderMaterials(int id, ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.getOrderMaterials(id, connectionPool);
    }
}
