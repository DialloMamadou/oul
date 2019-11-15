package controlleurvue.client;

import basededonnee.DBconnexion;
import com.mysql.jdbc.PreparedStatement;
import controlleurvue.Vue;
import controlleurvue.centre.ConsulterCentre;
import controlleurvue.sejour.CreerSejour;
import daos.ClientDao;
import daos.GroupeDao;
import daos.impl.ClientDaoImpl;
import daos.impl.GroupeDaoImpl;
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
import modele.Client;
import modele.Groupe;
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

public class CreerClient  implements Initializable, Vue {


    public StackPane stack;
    public TextField nom;

    public ComboBox groupe;
    public TextField prenom;
    public TextField portable;
    public TextField observation;
    public TextField email;
    public TextField poste;
    public DatePicker annee;
    public TextField adresse;

    private Controlleur controlleur;

    private GroupeDao groupeDao;
    private ClientDao clientDao;


    @Override
    public void setController(Controlleur controller) {
        groupeDao =new GroupeDaoImpl(DBconnexion.getConnection());
        clientDao=new ClientDaoImpl(DBconnexion.getConnection());
        this.controlleur=controller;
        List<Groupe> liste=groupeDao.listeGroupes();
        for(Groupe groupe:liste){
            this.groupe.getItems().add(groupe.nom_groupe.get());

        }
    }
    public void back(MouseEvent mouseEvent) {
        this.controlleur.lancerPageClient();
    }
    public void close(MouseEvent mouseEvent) {
    }




    public void book(MouseEvent mouseEvent) {
        Groupe groupe=groupeDao.trouverGroupeParNomGroupe(this.groupe.getValue().toString().trim());

        String prenom=this.prenom.getText();
        String nom=this.nom.getText();
        String id_group=groupe.id.get();
        String portable=this.portable.getText();
        String observation=this.observation.getText();
        String email=this.email.getText();
        String adresse=this.adresse.getText();
        String poste=this.poste.getText();
        System.out.println("poste =="+poste);
        String dateNaissance=this.annee.getValue().toString();


        Client client=new Client(nom,prenom,id_group,dateNaissance,portable,observation,email,adresse,poste);

        int res=this.clientDao.insererClient(client);

        if(res>0){
            Notification.affichageSucces("succes","client creer avec succes");

        }else{
            Notification.affichageEchec("erreur","il y a eu une erreur au moment de la creation");

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
