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
import java.util.List;
import java.util.NoSuchElementException;

@WebServlet(name = "adminvieworderservlet", value = "/adminvieworderservlet")
public class AdminViewOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order = null;
        try {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            List<Order> orders = (List<Order>) request.getSession().getAttribute("orders");

            for (Order o : orders) {
                if (o.getId() == orderId) {
                    order = o;
                }
            }
            if (order == null) {
                throw new NoSuchElementException();
            }

            request.setAttribute("order", order);
            request.getRequestDispatcher("WEB-INF/adminViewOrderInfo.jsp").forward(request, response);
        } catch (NoSuchElementException e) {
            request.setAttribute("errormessage", "Kunne ikke finde en ordre med dette id");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
