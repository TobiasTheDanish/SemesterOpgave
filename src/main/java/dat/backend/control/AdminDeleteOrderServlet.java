package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "admindeleteorderservlet", value = "/admindeleteorderservlet")
public class AdminDeleteOrderServlet extends HttpServlet {
    private ConnectionPool connectionPool;

    @Override
    public void init() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("orderId"));
        List<Order> orders = (List<Order>) request.getSession().getAttribute("orders");
        try {
            if (OrderFacade.removeOrder(id, connectionPool)) {

                for (Order order : orders) {
                    if (order.getId() == id) {
                        order.setInactive(true);
                        break;
                    }
                }
                request.getRequestDispatcher("WEB-INF/adminOrders.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "Kunne ikke fjerne ordre.");
                request.getRequestDispatcher("WEB-INF/adminOrders.jsp").forward(request, response);
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }
}
