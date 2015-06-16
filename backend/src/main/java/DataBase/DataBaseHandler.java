package DataBase;

import Model.Livre;
import Model.User;

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

                livre.set_id(rs.getInt(1));
                livre.set_titre(rs.getString(2));
                livre.set_auteur(rs.getString(3));
                livre.set_resume(rs.getString(4));
                livre.set_dateParution(rs.getString(5));
                livre.set_categorie(rs.getString(6));
                livre.set_cover(rs.getBytes(7));

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
                livre.set_id(rs.getInt(1));
                livre.set_titre(rs.getString(2));
                livre.set_auteur(rs.getString(3));
                livre.set_resume(rs.getString(4));
                livre.set_dateParution(rs.getString(5));
                livre.set_categorie(rs.getString(6));
                livre.set_cover(rs.getBytes(7));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livre;
    }

    /**
     * Récuperer liste livres par categorie
     */
    public List<Livre> getLivresByCategorie(String categorie) {
        List<Livre> list = new ArrayList<>();
        String query = "select * from livre where categorie=?";
        try {
            Connection con = connecter();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, categorie);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Livre livre = new Livre();
                livre.set_id(rs.getInt(1));
                livre.set_titre(rs.getString(2));
                livre.set_auteur(rs.getString(3));
                livre.set_resume(rs.getString(4));
                livre.set_dateParution(rs.getString(5));
                livre.set_categorie(rs.getString(6));
                livre.set_cover(rs.getBytes(7));

                list.add(livre);
            }
            con.close();}
            catch (SQLException e) {
                e.printStackTrace();
            }
            return list;
    }


    public User getLoginUser(String username, String password) {
        String query = "select * from utilisateur where username=? and password=?";
        Connection con = connecter();
        User user= null;
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.first()) {
                user = new User();
                user.set_username(rs.getString(1));
                user.set_password(rs.getString(2));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    public int getNbLivresCategorie(String categorie) {
        String query = "select count(*) from livre where categorie=?";
        Connection con = connecter();
        int count = -1;
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, categorie);
            ResultSet rs = pst.executeQuery();
            if (rs.first()) {
                count = rs.getInt(1);
                System.out.println("count ="+count+" categorie ="+categorie);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }




}
