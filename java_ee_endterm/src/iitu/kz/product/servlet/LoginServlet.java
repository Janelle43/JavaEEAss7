package iitu.kz.product.servlet;

import iitu.kz.product.db.DbManager;
import iitu.kz.product.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        String redirect = "/login?usererror&login="+(login!=null?login:"");

        User user = DbManager.getUserByLogin(login);

        if(user!=null){

            redirect = "/login?passworderror&login="+(login!=null?login:"");

            if(user.getPassword().equals(password)){

                HttpSession session = request.getSession();
                session.setAttribute("USER", user);
                redirect = "/";

            }

        }

        response.sendRedirect(redirect);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
