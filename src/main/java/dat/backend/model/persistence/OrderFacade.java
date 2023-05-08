package dat.backend.model.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;

public class OrderFacade {
    public static List<Order> getAllOrders(ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.getAllOrders(connectionPool);
    }

    public static Order getOrderById(int id, ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.getOrderById(id, connectionPool);
    }
}
