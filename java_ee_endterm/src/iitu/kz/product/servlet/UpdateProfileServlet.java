package iitu.kz.product.servlet;




import iitu.kz.product.db.DbManager;
import iitu.kz.product.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/updateprofile")
public class UpdateProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User)request.getSession().getAttribute("USER");

        if(user!=null){

            String fullName = request.getParameter("full_name");
            user.setFullName(fullName);
            if(DbManager.updateUserProfile(user)){
                request.getSession().setAttribute("USER", user);
            }

            response.sendRedirect("/profile");

        }else{
            response.sendRedirect("/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
