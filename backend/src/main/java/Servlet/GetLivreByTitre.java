package Servlet;


import com.google.gson.Gson;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DataBase.DataBaseHandler;
import Model.Livre;

public class GetLivreByTitre extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String titre = req.getParameter("titrelivre"); // attention
        Livre livre = new DataBaseHandler().getLivreByTitre(titre);
        Gson gson = new Gson();
        resp.getWriter().print(gson.toJson(livre));

    }
}
