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

        List<Order> orders = (List<Order>) request.getSession().getAttribute("orders");
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        int width = Integer.parseInt(request.getParameter("width"));
        int height = Integer.parseInt(request.getParameter("height"));
        int length = Integer.parseInt(request.getParameter("length"));
        try {
            for (Order o : orders) {
                if (o.getId() == orderId ) {
                    o.setWidth(width);
                    o.setHeight(height);
                    o.setLength(length);
                    if (!OrderFacade.editOrder(o, connectionPool)) {
                        request.setAttribute("errormessage", "Kunne ikke gemme ændring.");
                    }
                    break;
                }
            }
            request.getRequestDispatcher("WEB-INF/viewOrders.jsp").forward(request, response);
        } catch (DatabaseException e) {
            e.printStackTrace();
            request.setAttribute("errormessage", e.getMessage());
        }
    }
}
