package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "viewordersservlet", value = "/viewordersservlet")
public class ViewOrdersServlet extends HttpServlet {
    private ConnectionPool connectionPool;

    @Override
    public void init() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");


        try {
            List<Order> userOrders = OrderFacade.viewOrder(user, connectionPool);
            request.getSession().setAttribute("orders", userOrders);
            request.getRequestDispatcher("WEB-INF/viewOrders.jsp").forward(request, response);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        int order =  Integer.parseInt(request.getParameter("vieworder"));
        List<Order> orders = (List<Order>) session.getAttribute("orders");

        if (action != null && action.equals("Remove")) {
            try {
             if(OrderFacade.removeOrder(order,connectionPool)){
                 for(Order o:orders){
                     if(o.getId() == order){
                         o.setInactive(true);
                         break;
                     }
                 }
                 request.getRequestDispatcher("WEB-INF/viewOrders.jsp").forward(request,response);

             } else{
                 System.out.println("Christians mor er lækker");
             }
            } catch (DatabaseException e) {
                e.printStackTrace();
            }

        }

    }

}
