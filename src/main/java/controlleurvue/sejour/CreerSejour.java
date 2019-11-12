package controlleurvue.sejour;

import basededonnee.DBconnexion;
import com.mysql.jdbc.PreparedStatement;
import controlleurvue.Vue;
import controlleurvue.centre.ConsulterCentre;
import daos.CentreDao;
import daos.SejourDao;
import daos.impl.CentreDaoImpl;
import daos.impl.SejourDaoImpl;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import modele.Centre;
import modele.Sejour;
import notification.Notification;
import org.controlsfx.control.Notifications;
import principale.Controlleur;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreerSejour implements Initializable, Vue {


    public StackPane stack;
    public TextField type;
    public DatePicker dateD;
    public DatePicker dateF;
    public ComboBox centre;
    public TextField duree;
    public TextField capacite;
    public TextField agemax;
    public TextField agemin;
    public TextField prix;

    private SejourDao sejourDao;
    private CentreDao centreDao;

    private  Controlleur controlleur;
    @Override
    public void setController(Controlleur controller) {
        this.controlleur=controller;
        sejourDao=new SejourDaoImpl(DBconnexion.getConnection());
        centreDao=new CentreDaoImpl(DBconnexion.getConnection());
        Connection connection= DBconnexion.getConnection();
        List<Centre> centres=centreDao.listeCentres();
        for(Centre centre:centres){
            this.centre.getItems().add(centre.nom_centre.get());
        }
    }

    public void back(MouseEvent mouseEvent) {
        this.controlleur.lancerPageSejour();
    }

    public void close(MouseEvent mouseEvent) {
    }




    public void book(MouseEvent mouseEvent) {


        Centre centre=centreDao.trouverParNomCentre(this.centre.getValue().toString());
        String id=centre.id.get();
        String duree= this.duree.getText().toString();
        String datedebut= this.dateD.getValue().toString();
       String datefin= this.dateF.getValue().toString();
        String type=this.type.getText().toString();
        String max=this.agemax.getText().toString();
        String min=this.agemin.getText().toString();
        String prix=this.prix.getText().toString();
        String capacite=this.capacite.getText().toString();
        Sejour sejour=new Sejour(duree,datedebut,datefin,type,id,max,min,capacite,prix);
        int res=sejourDao.insererSejour(sejour);
        if(res>0){
            Notification.affichageSucces("succes","Sejour creer avec succes");

        }else{
            Notification.affichageEchec("echec","echec dans la creation du sejour");

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
