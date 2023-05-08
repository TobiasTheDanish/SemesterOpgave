package dat.backend.model.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.exceptions.DatabaseException;

public class OrderFacade {

    public static boolean createOrder(Order order, ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.createOrder(order, connectionPool);
    }

}
