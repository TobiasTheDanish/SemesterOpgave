package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.Status;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "adminupdatestatusservlet", value = "/adminupdatestatusservlet")
public class AdminUpdateStatusServlet extends HttpServlet {
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
        List<Order> orders = (List<Order>) request.getSession().getAttribute("orders");
        int id = Integer.parseInt(request.getParameter("orderId"));
       Status status = Status.valueOf(request.getParameter("status"));

        try {
            if (request.getParameter("action").equalsIgnoreCase("plus")){
                if (status.ordinal() < 2 && OrderFacade.updateStatus(status.ordinal() + 1, id, connectionPool)) {
                    for (Order order : orders) {
                        if (order.getId() == id) {
                            order.setStatus(Status.values()[status.ordinal() + 1]);
                            break;
                        }
                    }
                }
                request.getRequestDispatcher("WEB-INF/adminOrders.jsp").forward(request, response);

            } else if (request.getParameter("action").equalsIgnoreCase("minus")){
                if (status.ordinal() > 0 && OrderFacade.updateStatus(status.ordinal() - 1, id, connectionPool)){
                    for (Order order : orders){
                        if (order.getId() == id) {
                            order.setStatus(Status.values()[status.ordinal() - 1]);
                            break;
                        }
                    }
                }
                request.getRequestDispatcher("WEB-INF/adminOrders.jsp").forward(request, response);
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("WEB-INF/adminOrders.jsp").forward(request, response);

    }
}
