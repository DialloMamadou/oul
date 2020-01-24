package controlleurvue.groupe;

import basededonnee.DBconnexion;
import controlleurvue.Vue;
import daos.*;
import daos.impl.*;
import enumerations.Depart;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import modele.*;
import notification.Notification;
import principale.Controlleur;

import java.net.URL;
import java.util.ResourceBundle;

public class AjoutSejourMairieClient implements Vue, Initializable {
    public Label lgrouped;
    public Label sejour;
    public Label date;
    public Label centre;
    public Label groupecomplet;


    
    public TextField portable;
    public TextField observation;
    public TextField email;
    public TextField poste;
    public DatePicker annee;
    public TextField adresse;
    public TextField nom;
    public TextField prenom;
    public Label prix_unitaire;
    public ComboBox depart;


    private Controlleur controlleur;

    public static String sejourId;
    public   static  String assocId;
    public  static  String groupe;



    public void back(MouseEvent mouseEvent) {
        this.controlleur.lancerPageConsulterGroupeSejour();
    }

    @Override
    public void setController(Controlleur controller) {
        this.controlleur=controller;

    }

    private AssociationGroupeSejourDao associationGroupeSejourDao;
    private SejourDao sejourDao;
    private CentreDao centreDao;
    private GroupeDao groupeDao;
    private ClientDao clientDao;
    private GroupeSejourClientDao groupeSejourClientDao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        associationGroupeSejourDao=new AssociationGroupeSejourDaoImpl(DBconnexion.getConnection());
        sejourDao=new SejourDaoImpl(DBconnexion.getConnection());
        centreDao=new CentreDaoImpl(DBconnexion.getConnection());
        groupeDao=new GroupeDaoImpl(DBconnexion.getConnection());
        clientDao=new ClientDaoImpl(DBconnexion.getConnection());
        this.groupeSejourClientDao=new GroupeSejourClientDaoImpl(DBconnexion.getConnection());
        for(Depart depart:Depart.values()){
            this.depart.getItems().add(depart);

        }
        remplirData();
    }

    private void remplirData() {
        Sejour sejour=sejourDao.getSejourParId(AjoutSejourMairieClient.sejourId);
        Centre centre=centreDao.getCentreParId(sejour.nom_centre.get());
        this.date.setText(sejour.date_debut.get()+" "+sejour.date_fin.get());
        this.sejour.setText(sejour.type.get());

        this.centre.setText(centre.nom_centre.get());
        this.lgrouped.setText(groupe);
        this.groupecomplet.setText(groupe);
        Associationgroupesejour associationgroupesejour=associationGroupeSejourDao.getById(AjoutSejourMairieClient.assocId);
        this.prix_unitaire.setText(associationgroupesejour.prix_unitaire.get());

    }


    public void book(MouseEvent mouseEvent) {

        Groupe groupe=groupeDao.trouverGroupeParNomGroupe(AjoutSejourMairieClient.groupe);

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



        Client client=new Client("",nom,prenom,id_group,portable,observation,email,adresse,poste,dateNaissance);

        int[] res=this.clientDao.insererClientMairie(client);

        if(res[0]>0){
            System.out.println("rows inserer "+res[1]);
            groupeSejourClientDao.insererGroupeSejourClient(new GroupeSejourClient(id_group,sejourId,String.valueOf(res[1]),this.depart.getValue().toString()));

           // groupeSejourClientDao.insererGroupeSejourClient(new GroupeSejourClient(id_group,sejourId,))
            Notification.affichageSucces("succes","client creer avec succes");

        }else{
            Notification.affichageEchec("erreur","il y a eu une erreur au moment de la creation");

        }
    }
}
