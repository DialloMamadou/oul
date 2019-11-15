package controlleurvue.centre;

import basededonnee.DBconnexion;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.PreparedStatement;
import controlleurvue.Vue;
import daos.CentreDao;
import daos.impl.CentreDaoImpl;
import daos.impl.Dao;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import modele.Centre;
import notification.Notification;
import org.controlsfx.control.Notifications;
import principale.Controlleur;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreerCentre implements Vue {

    public StackPane stackepane;
    public JFXTextField nom;
    public JFXTextField capacite;
    private Controlleur controlleur;
    public void back(MouseEvent mouseEvent) {
        this.controlleur.lancerPageCentre();
    }
    public void close(MouseEvent mouseEvent) {
    }
    public void book(MouseEvent mouseEvent) {
        int res=0;
        if(nom.getText().length()!=0) {
            res=centreDaoImpl.inserrerCentre(nom.getText(),capacite.getText());
        }
        if(res>0){
            Notification.affichageSucces("succes","centre creer avec succes");
        }else{
            Notification.affichageEchec("echec","echec dans la creation du centre");
        }
    }
    private CentreDao centreDaoImpl;
    public void setController(Controlleur controller) {
        this.centreDaoImpl=new CentreDaoImpl(DBconnexion.getConnection());
        this.controlleur=controller;

    }
}
