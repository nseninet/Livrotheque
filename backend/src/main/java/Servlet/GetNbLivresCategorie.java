package Servlet;

import com.google.gson.Gson;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DataBase.DataBaseHandler;
import Model.User;


public class GetNbLivresCategorie extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categorie = req.getParameter("categorie");
        int count = new DataBaseHandler().getNbLivresCategorie(categorie);
        Gson gson = new Gson();
        resp.getWriter().print(gson.toJson(count));
    }
}