package iitu.kz.product.servlet;

import iitu.kz.product.db.DbManager;
import iitu.kz.product.entity.Cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/details")
public class DetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = null;
        try{

            Long id = Long.parseLong(request.getParameter("id"));
            cookie = DbManager.getCookie(id);

        }catch (Exception e){

        }

        if(cookie!=null){
            request.setAttribute("cookie", cookie);
            request.getRequestDispatcher("/details.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("/404.jsp").forward(request, response);
        }
    }
}
