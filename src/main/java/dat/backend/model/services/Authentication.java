package dat.backend.model.services;

import dat.backend.model.entities.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Authentication
{
    public static boolean isRoleAllowed(int role, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null)
        {
            return user.getRole() == role;
        }
        return false;
    }
}
