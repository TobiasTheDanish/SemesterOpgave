package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Material;
import dat.backend.model.entities.Order;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.MaterialFacade;
import dat.backend.model.persistence.OrderFacade;
import org.javatuples.Pair;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "adminordersservlet", value = "/adminordersservlet")
public class AdminOrdersServlet extends HttpServlet {
    private ConnectionPool connectionPool;

    @Override
    public void init()
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Order> orders = (List<Order>) session.getAttribute("orders");

        try {
            orders = OrderFacade.getAllOrdersWithoutMaterials(connectionPool);

            session.setAttribute("orders", orders);

            request.getRequestDispatcher("WEB-INF/adminOrders.jsp").forward(request, response);
        } catch (DatabaseException e) {
            e.printStackTrace();
            request.setAttribute("errormessage", "Der skete en fejl under hentningen af ordrer fra databasen:\n" + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

        try {
            Map<Integer, List<Pair<Material, Integer>>> orderLinkMap = MaterialFacade.getOrderLinkMap(connectionPool);

            for (Order order : orders) {
                order.setMaterials(orderLinkMap.get(order.getId()));
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }
}
