package dat.backend.model.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;

public class OrderFacade {

    public static boolean createOrder(Order order, ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.createOrder(order, connectionPool);
    }

    public static List<Order> viewOrder(User user, ConnectionPool connectionPool) throws DatabaseException{
        return OrderMapper.viewOrder(user, connectionPool);
    }

}
