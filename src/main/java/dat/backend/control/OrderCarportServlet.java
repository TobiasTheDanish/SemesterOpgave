package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.Status;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;
import dat.backend.model.persistence.UserFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ordercarportservlet", value = "/ordercarportservlet")
public class OrderCarportServlet extends HttpServlet {

    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if(user.getUsername() == null || user.getLastName() == null || user.getPhoneNr() == 0 || user.getZipCode() == 0){
            request.setAttribute("errormessage", "Du skal oprette din profil før du kan bestille en carport.");
            request.getRequestDispatcher("WEB-INF/profileSite.jsp").forward(request,response);
        }
        request.getRequestDispatcher("WEB-INF/orderCarport.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        try {
            int userId = UserFacade.getId(user.getUsername(), connectionPool);
            user.setId(userId);
            int width = Integer.parseInt(request.getParameter("width"));
            int height = Integer.parseInt(request.getParameter("height"));
            int length = Integer.parseInt(request.getParameter("length"));
            Order order = new Order(user, Status.BESTILT, width, height, length);
            if (OrderFacade.createOrder(order, connectionPool)) {

                request.setAttribute("order", order);
                request.getRequestDispatcher("WEB-INF/orderConfirmation.jsp").forward(request, response);
            } else {
                request.setAttribute("errormessage", "Kunne ikke oprette ordre.");
            }

        } catch (DatabaseException e) {
            e.printStackTrace();
            request.getRequestDispatcher("WEB-INF/orderCarport.jsp").forward(request, response);
        }



    }
}
