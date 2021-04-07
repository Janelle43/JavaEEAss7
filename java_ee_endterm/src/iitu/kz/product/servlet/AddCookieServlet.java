package iitu.kz.product.servlet;



import iitu.kz.product.db.DbManager;
import iitu.kz.product.entity.Cookie;
import iitu.kz.product.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/addcookie")
public class AddCookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User currentUser = (User)request.getSession().getAttribute("USER");

        if(currentUser!=null){

            request.setCharacterEncoding("utf8");

            String name = request.getParameter("name");
            String recipe = request.getParameter("recipe");
            int rating = Integer.parseInt(request.getParameter("rating"));
            int price = Integer.parseInt(request.getParameter("price"));

            Cookie cookie = new Cookie(null, name, currentUser, recipe, rating, price, null);
            if(DbManager.addCookie(cookie)){
                response.sendRedirect("/addcookie?success");
            }else{
                response.sendRedirect("/addcookie?error");
            }

        }else{
            response.sendRedirect("/login");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User currentUser = (User)request.getSession().getAttribute("USER");
        if(currentUser!=null){
            request.getRequestDispatcher("/addcookie.jsp").forward(request, response);
        }else{
            response.sendRedirect("/login");
        }

    }
}
