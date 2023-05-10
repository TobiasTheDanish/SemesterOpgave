package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Material;
import dat.backend.model.entities.Order;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.regex.MatchResult;

@WebServlet(name = "adminordersservlet", value = "/adminordersservlet")
public class AdminOrdersServlet extends HttpServlet {
    private ConnectionPool connectionPool;
    private long lastGetOrdersCall;

    @Override
    public void init()
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
        this.lastGetOrdersCall = System.currentTimeMillis();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders = (List<Order>) request.getSession().getAttribute("orders");

        if (orders == null || orders.size() == 0 || shouldGetOrders()) {
            try {
                orders = OrderFacade.getAllOrders(connectionPool);
                lastGetOrdersCall = System.currentTimeMillis();

                request.getSession().setAttribute("orders", orders);
                request.getRequestDispatcher("WEB-INF/adminOrders.jsp").forward(request, response);
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private boolean shouldGetOrders() {
        return lastGetOrdersCall + 1000 >= System.currentTimeMillis();
    }
}
