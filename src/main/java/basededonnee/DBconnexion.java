package basededonnee;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBconnexion {



    public static Connection connexion=null;

    static Connection connection=null;
    public static Connection getConnection(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/reservation";
            connection=DriverManager.getConnection(url,"root","");
            System.out.println("connexion a la base de donne reussi");

        } catch (Exception e) {
        }

        return connection;
    }



}
