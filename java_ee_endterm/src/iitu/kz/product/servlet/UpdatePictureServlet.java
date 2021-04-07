package iitu.kz.product.servlet;

import iitu.kz.product.db.DbManager;
import iitu.kz.product.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/updatepicture")
public class UpdatePictureServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User)request.getSession().getAttribute("USER");

        if(user!=null){

            String url = request.getParameter("url");
            user.setPicture(url);
            if(DbManager.updateUserPicture(user)){
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
