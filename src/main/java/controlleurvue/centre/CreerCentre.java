package controlleurvue.centre;

import basededonnee.DBconnexion;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.PreparedStatement;
import controlleurvue.Vue;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import principale.Controlleur;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreerCentre implements Vue {
    public StackPane stackepane;
    public JFXTextField nom;
    private Controlleur controlleur;

    public void back(MouseEvent mouseEvent) {
        this.controlleur.lancerPageCentre();
    }

    public void close(MouseEvent mouseEvent) {
    }

    public void book(MouseEvent mouseEvent) {

        int res=0;
        String sql="INSERT INTO centre (nom_centre) VALUES (?)";
        Connection connection= DBconnexion.getConnection();
        try {
            PreparedStatement ps=(PreparedStatement)connection.prepareStatement(sql);
            if(nom.getText().length()!=0) {
                ps.setString(1, nom.getText().toString());
            }

            res=ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CreerCentre.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(res>0){
            Image image=new Image("img/mooo.png");
            Notifications notification=Notifications.create()
                    .title("finit")
                    .text("centre creer avec succss")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_LEFT)
                    .graphic(new ImageView(image));
            notification.darkStyle();
            notification.show();
            //updateStatus();
        }else{
            Image image=new Image("img/delete.png");
            Notifications notification=Notifications.create()
                    .title("Error")
                    .text("il y a eu une erreur dans la creation")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_LEFT)
                    .graphic(new ImageView(image));
            notification.darkStyle();
            notification.show();
        }


    }

    public void setController(Controlleur controller) {

        this.controlleur=controller;
    }
}
