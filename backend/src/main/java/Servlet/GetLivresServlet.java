package Servlet;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DataBase.DataBaseHandler;
import Model.Livre;


public class GetLivresServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Livre> livreList = new DataBaseHandler().getAllLivres();
        Gson gson = new Gson();
        resp.getWriter().print(gson.toJson(livreList));

    }
}