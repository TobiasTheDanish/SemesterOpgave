package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.Status;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "editorderservlet", value = "/editorderservlet")
public class EditOrderServlet extends HttpServlet {
    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        int width = Integer.parseInt(request.getParameter("width"));
        int height = Integer.parseInt(request.getParameter("height"));
        int length = Integer.parseInt(request.getParameter("length"));
        try {
            List<Order> userOrders = OrderFacade.viewOrder(user, connectionPool);
            for (Order o : userOrders) {
                if (o.getId() == orderId ) {
                    System.out.println("krelles mor er lækker");
                    o.setWidth(width);
                    o.setHeight(height);
                    o.setLength(length);
                    OrderFacade.editOrder(o, connectionPool);
                    request.getRequestDispatcher("viewordersservlet");

                }
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
            request.setAttribute("errormessage", "Kunne ikke gemme ændring.");
        }
        request.getRequestDispatcher("WEB-INF/viewOrders.jsp").forward(request, response);
    }
}
