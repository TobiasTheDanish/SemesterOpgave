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
        this.lastGetOrdersCall = 0;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders = (List<Order>) request.getSession().getAttribute("orders");
        HttpSession session = request.getSession();

        if (orders == null || orders.size() == 0 || shouldGetOrders()) {
            try {
                orders = OrderFacade.getAllOrdersWithoutMaterials(connectionPool);
                lastGetOrdersCall = System.currentTimeMillis();

                session.setAttribute("orders", orders);
                request.getRequestDispatcher("WEB-INF/adminOrders.jsp").forward(request, response);
                for (Order order : orders) {
                    order.setMaterials(OrderFacade.getOrderMaterials(order.getId(), connectionPool));
                }
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
        } else {
            request.getRequestDispatcher("WEB-INF/adminOrders.jsp").forward(request, response);
            System.out.println("else block");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private boolean shouldGetOrders() {
        return lastGetOrdersCall + 5000 <= System.currentTimeMillis();
    }
}
