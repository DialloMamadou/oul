package controlleurvue.centre;

import basededonnee.DBconnexion;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.PreparedStatement;
import controlleurvue.Vue;
import daos.CentreDao;
import daos.impl.CentreDaoImpl;
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
        if(nom.getText().length()!=0) {
         res= centreDaoImpl.inserrerCentre(nom.getText());
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

    private CentreDao centreDaoImpl;

    public void setController(Controlleur controller) {

        this.centreDaoImpl=new CentreDaoImpl(DBconnexion.getConnection());
        this.controlleur=controller;
    }
}
