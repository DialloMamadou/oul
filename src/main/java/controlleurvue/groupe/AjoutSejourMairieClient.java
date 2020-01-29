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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javafx.scene.input.KeyCode.Z;
import static javax.print.attribute.standard.MediaSizeName.A;

public class AjoutSejourMairieClient implements Vue, Initializable {
    public Label lgrouped;
    public Label sejour;
    public Label date;
    public Label centre;
    public TextField groupecomplet;
    
    
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

    public String dD;
    public String dF;

    public int aMin;
    public int aMax;

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

        this.dD = sejour.date_debut.get();
        this.dF = sejour.date_fin.get();
        this.aMin = Integer.parseInt(sejour.ageMin.get());
        this.aMax = Integer.parseInt(sejour.ageMax.get());

        this.date.setText(dD+" "+dF);
        this.sejour.setText(sejour.type.get());
        this.centre.setText(centre.nom_centre.get());
        this.lgrouped.setText(groupe);
        this.groupecomplet.setText(groupe);
        Associationgroupesejour associationgroupesejour=associationGroupeSejourDao.getById(AjoutSejourMairieClient.assocId);
        this.prix_unitaire.setText(associationgroupesejour.prix_unitaire.get());

    }


    public void creer(MouseEvent mouseEvent) {
        try {
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
            //lml;
            controleDate(dateNaissance,this.dD, this.dF);

            Client client=new Client("",nom,prenom,id_group,portable,observation,email,adresse,poste,dateNaissance);

            int[] res=this.clientDao.insererClientMairie(client);

            if(res[0]>0){
                System.out.println("rows inserer "+res[1]);
                groupeSejourClientDao.insererGroupeSejourClient(new GroupeSejourClient(id_group,sejourId,String.valueOf(res[1]),this.depart.getValue().toString()));

               // groupeSejourClientDao.insererGroupeSejourClient(new GroupeSejourClient(id_group,sejourId,))
                Notification.affichageSucces("succes","client creer avec succes n bjbhj");

            }else{
                Notification.affichageEchec("erreur","il y a eu une erreur au moment de la creation");

            }
        }catch (NullPointerException | NumberFormatException e){
            Notification.affichageEchec("Problème de donnees","veuillez saisir de(s) champ(s) non vides et valide(s) ");

        }
    }

    public boolean controleDate(String dN, String dateDebut, String dateFin){

       /* if (ChronoUnit.YEARS.between(LocalDate.parse(dN),LocalDate.parse(dD) >= this.aMin){

        }

        Boolean b = true;
            if(!(dD.after(new Date()))){
                Notification.affichageEchec("echec","la date du debut est incorrecte");
                b=false;
                return b;
            }else if(!(dF.after(dD))){
                Notification.affichageEchec("echec","la date de fin doit etre apres celle du debut");
                b=false;
                return b;
            }else{
                return b;
            }
        }catch(ParseException e){
            Notification.affichageEchec("echec","echec le format de la date incorrect");
        }
        return b;*/
       return false;

    }

    public static boolean isEmailAdress(String email){
        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
        Matcher m;
        m = p.matcher(email.toUpperCase());
        return m.matches();
    }

    public static boolean isCodePostale(String email){
        Pattern p = Pattern.compile("^(0[1-9]{0,1}|[1-9][0-9]{0,1})[0-9]{0,3}$");
        Matcher m;
        m = p.matcher(email.toUpperCase());
        return m.matches();
    }
}
    