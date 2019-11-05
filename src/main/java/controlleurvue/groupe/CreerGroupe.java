package controlleurvue.groupe;

import basededonnee.DBconnexion;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.PreparedStatement;
import controlleurvue.Vue;
import controlleurvue.centre.CreerCentre;
import daos.GroupeDao;
import daos.impl.GroupeDaoImpl;
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

public class CreerGroupe implements Vue {
    public StackPane stackepane;
    public JFXTextField nom;
    private Controlleur controlleur;

    public void back(MouseEvent mouseEvent) {
        this.controlleur.lancerPageGroupe();
    }

    public void close(MouseEvent mouseEvent) {
    }


    private void messageSuccess(){
        Image image=new Image("img/mooo.png");
        Notifications notification=Notifications.create()
                .title("finit")
                .text("groupe creer avec succss ")
                .hideAfter(Duration.seconds(3))
                .position(Pos.BOTTOM_LEFT)
                .graphic(new ImageView(image));
        notification.darkStyle();
        notification.show();

    }

    private void messageErreur(){
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
    public void book(MouseEvent mouseEvent) {

        int res=0;
        if(nom.getText().length()!=0) {
            res= groupeDao.inserrerGroupe(nom.getText());
        }

        if(res>0){
            messageSuccess();
        }else{
            messageErreur();
        }


    }

    private GroupeDao groupeDao;


    public void setController(Controlleur controller) {

        groupeDao=new GroupeDaoImpl(DBconnexion.getConnection());
        this.controlleur=controller;
    }
}
