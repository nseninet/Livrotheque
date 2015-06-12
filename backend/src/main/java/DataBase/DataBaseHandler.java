package DataBase;

import Model.Livre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DataBaseHandler {
    public static final String className = "com.mysql.jdbc.Driver";
    public static final String chaine
            = "jdbc:mysql://localhost:3306/biblioDB";
    static final String username = "root";
    static final String password = "";

    public Connection connecter() {
        Connection con = null;
        try {
            Class.forName(className);
            con = DriverManager.getConnection(chaine, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public List<Livre> getAllLivres() {
        List<Livre> list = new ArrayList<>();
        String query = "select * from livre";
        try {
            Connection con = connecter();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Livre livre = new Livre();

                livre.set_id(rs.getInt(0));
                livre.set_titre(rs.getString(1));
                livre.set_auteur(rs.getString(2));
                livre.set_resume(rs.getString(3));
                livre.set_dateParution(rs.getString(4));
                livre.set_categorie(rs.getString(5));
                livre.set_cover(rs.getBytes(6));

                list.add(livre);
            }
            con.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public Livre getLivreByTitre(String titre) {
        String query = "select * from livre where titre=?";
        Connection con = connecter();
        Livre livre= null;
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, titre);
            ResultSet rs = pst.executeQuery();
            if (rs.first()) {
                livre = new Livre();
                livre.set_titre(rs.getString(1));
                livre.set_auteur(rs.getString(2));
                livre.set_resume(rs.getString(3));
                livre.set_dateParution(rs.getString(4));
                livre.set_categorie(rs.getString(5));
                livre.set_cover(rs.getBytes(6));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livre;
    }
}
