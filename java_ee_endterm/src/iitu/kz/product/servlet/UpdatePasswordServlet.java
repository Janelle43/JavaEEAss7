package iitu.kz.product.servlet;




import iitu.kz.product.db.DbManager;
import iitu.kz.product.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/updatepassword")
public class UpdatePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User)request.getSession().getAttribute("USER");
        String redirect = "/login";

        if(user!=null){

            redirect = "/profile?oldpasserror";

            String oldPass = request.getParameter("old_password");
            String newPass = request.getParameter("new_password");
            String reNewPass = request.getParameter("re_new_password");

            if(user.getPassword().equals(oldPass)){

                redirect = "/profile?newpasserror";

                if(newPass.equals(reNewPass)){

                    user.setPassword(newPass);

                    if(DbManager.updateUserPassword(user)){
                        request.getSession().setAttribute("USER", user);
                        redirect = "/profile?success";
                    }

                }

            }

        }

        response.sendRedirect(redirect);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
