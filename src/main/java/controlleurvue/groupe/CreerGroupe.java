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
import modele.Groupe;
import notification.Notification;
import org.controlsfx.control.Notifications;
import principale.Controlleur;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreerGroupe implements Vue {
    public StackPane stackepane;
    public JFXTextField nom;
    public JFXTextField tiers;
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

    private void messageErreur(String txt){
        Image image=new Image("img/delete.png");
        Notifications notification=Notifications.create()
                .title("Error")
                .text(txt)
                .hideAfter(Duration.seconds(3))
                .position(Pos.BOTTOM_LEFT)
                .graphic(new ImageView(image));
        notification.darkStyle();
        notification.show();

    }
    public void book(MouseEvent mouseEvent) {

        int res=0;
        if(!nom.getText().isEmpty() && !tiers.getText().isEmpty()) {
            Groupe findGrpByName = groupeDao.trouverGroupeParNomGroupe(nom.getText());
            Groupe findGrpByTiers = groupeDao.trouverGroupeParCodeTiers(tiers.getText());
            if (findGrpByName == null && findGrpByTiers == null) {
                res = groupeDao.inserrerGroupe(nom.getText(), tiers.getText());
                if(res>0){
                    messageSuccess();
                }else{
                    messageErreur("Il y a eu une erreur lors de la creation");
                }
            }else{
                messageErreur("Le groupe exsiste dejà");

            }

        }else {

            messageErreur("Veuillez remplir le(s) champ(s) vide(s)");
        }

    }

    private GroupeDao groupeDao;


    public void setController(Controlleur controller) {

        groupeDao=new GroupeDaoImpl(DBconnexion.getConnection());
        this.controlleur=controller;
    }
}
