package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.UserFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "createprofileservlet", value = "/createprofileservlet")
public class CreateProfileServlet extends HttpServlet {
    private ConnectionPool connectionPool;

    @Override
    public void init()
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("WEB-INF/profileSite.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if(user == null){
            response.sendError(500, "Server error: User is null.");
        }

        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setPhoneNr(Integer.parseInt(request.getParameter("phoneNr")));
        user.setZipCode(Integer.parseInt(request.getParameter("zipCode")));


        try {
            UserFacade.updateUserProfile(user,connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        session.setAttribute("user", user);
        request.getRequestDispatcher("WEB-INF/profileSite.jsp").forward(request,response);
    }
}
