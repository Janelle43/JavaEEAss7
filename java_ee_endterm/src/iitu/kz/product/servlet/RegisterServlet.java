package iitu.kz.product.servlet;




import iitu.kz.product.db.DbManager;
import iitu.kz.product.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("re_password");
        String fullName = request.getParameter("full_name");

        String redirect = "/register?passworderror&login="+(login!=null?login:"")+"&full_name="+(fullName!=null?fullName:"");

        if(rePassword.equals(password)) {

            redirect =  "/register?usererror&login="+(login!=null?login:"")+"&full_name="+(fullName!=null?fullName:"");

            User user = DbManager.getUserByLogin(login);

            if (user == null) {

                User newUser = new User(null, login, password, fullName, "https://i.pinimg.com/originals/2a/a3/96/2aa396fd5d914d15923499ba32d5c529.png");
                DbManager.addUser(newUser);

                redirect = "/register?success";

            }

        }

        response.sendRedirect(redirect);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }
}
