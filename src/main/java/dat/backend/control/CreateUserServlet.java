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
import java.net.URL;

@WebServlet(name = "createuserservlet", value = "/createuserservlet")
public class CreateUserServlet extends HttpServlet {
    private ConnectionPool connectionPool;

    @Override
    public void init()
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("createUser.jsp");
    }

    /**
     * This function expects 3 parameters on the request (email, password, confirmPassword).
     * If any of these aren't present, the servlet will send an error through the response.
     *
     * If the user already exist in the database, or the passwords did not match, an 'errormessage' attribute will be send back to the
     * @param request of type HttpServletRequest.
     * @param response of type HttpServletResponse.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmation = request.getParameter("confirmPassword");

        if (email == null || password == null || confirmation == null) {
            response.sendError(500, "Server error: Missing attribute(s) when attempting to create a new user.\nNeeds 'email', 'password' and 'confirmPassword'.");
        }

        try {
            if (!UserFacade.doesUserExist(email, connectionPool)) {
                if (password != null && password.equals(confirmation)) {
                    User user = UserFacade.createUser(email, password, 1, connectionPool);

                    if (user != null) {
                        request.setAttribute("email", email);
                        request.setAttribute("password", password);
                        request.getRequestDispatcher("/login").forward(request, response);
                    } else {
                        request.setAttribute("errormessage", "Error creating a new user.");
                        request.getRequestDispatcher("error.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("errormessage", "Passwords didn't match, please try again");
                    request.setAttribute("email", email);
                    request.getRequestDispatcher("createUser.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errormessage", "A user with that email, already exist. Go <a href=\"login.jsp\">here</a> to login.");
                request.getRequestDispatcher("createUser.jsp").forward(request, response);
            }
        } catch (DatabaseException e) {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
