package fabrique;

import controlleurvue.*;
import controlleurvue.centre.CentreScreen;
import controlleurvue.centre.CentreVueSpe;
import controlleurvue.centre.ConsulterCentre;
import controlleurvue.client.ClientScreen;
import controlleurvue.client.CreerClient;
import controlleurvue.groupe.ConsulterGroupe;
import controlleurvue.groupe.CreerGroupe;
import controlleurvue.groupe.GroupeScreen;
import controlleurvue.inscription.ConsulterInscription;
import controlleurvue.inscription.CreerInscriptionSejour;
import controlleurvue.inscription.InscriptionScreen;
import controlleurvue.sejour.ConsulterSejour;
import controlleurvue.sejour.CreerSejour;
import controlleurvue.sejour.SejourScreen;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import principale.Controlleur;

import java.io.IOException;
import java.net.URL;

public class FabriqueVue{

        private Stage stage;
        private Controlleur controlleur;

        public FabriqueVue(Stage stage, Controlleur controller){
            this.stage = stage;
            this.controlleur = controller;
        }

       public void createConnexionView(){
          load(ConnexionView.class.getResource("/vue/connexion.fxml"), "Login");
     }



    public void creerLoginVue(){
        load(Loginscreen.class.getResource("/vue/loginscreen.fxml"), "LOGIN.");
    }



        private Vue load(URL url, String title){
            FXMLLoader loader = new FXMLLoader(url);

            Parent root = null;

            try{
                root = loader.load();
            }
            catch(IOException e){
                e.printStackTrace();
                return null;
            }

            Vue view = loader.getController();
            view.setController(controlleur);

            stage.setTitle(title);
            Scene scene=new Scene(root,800,700);

            stage.setScene(scene);
            stage.show();


            return view;
        }

    public void creerPageAcceuil() {
        load(AccueilScreen.class.getResource("/vue/pageAcceuil.fxml"), "LOGIN.");

    }

    public void creerEcranVue() {
        load(AccueilScreen.class.getResource("/vue/centre.fxml"), "ecran.");

    }

    public void creerCentreVue() {
        load(CentreScreen.class.getResource("/vue/creerCentre.fxml"), "ecran.");

    }

    public void creerConsulterCentreVue() {
        load(ConsulterCentre.class.getResource("/vue/consulterCentre.fxml"), "ecran.");

    }

    public void creerVueCentreSpe() {
        load(CentreVueSpe.class.getResource("/vue/centreVueSpe.fxml"), "ecran.");

    }


    public void creerVueGroupe() {
            System.out.println("2");
        load(GroupeScreen.class.getResource("/vue/groupeScreen.fxml"), "ecran.");

    }

    public void creerVueCreeGroupe(Controlleur c) {
        //load(GroupeScreen.class.getResource("/vue/creerGroupe.fxml"), "ecran.");


        FXMLLoader loader = new FXMLLoader(GroupeScreen.class.getResource("/vue/creerGroupe.fxml"));

        Parent root = null;

        try{
            root = loader.load();
            Vue view = loader.getController();
            view.setController(c);

            stage.setTitle("ehi");
            Scene scene=new Scene(root,800,700);

            stage.setScene(scene);
            stage.show();

        }
        catch(IOException e){
            e.printStackTrace();
        }


    }

    public void creervuecreationgroupe() {
        load(CreerGroupe  .class.getResource("/vue/creerGroupe.fxml"), "ecran.");

    }

    public void creerConsulterGroupeVue() {
        load(ConsulterGroupe.class.getResource("/vue/consulterGroupe.fxml"), "ecran.");

    }

    public void creerVueSejour() {
        load(SejourScreen.class.getResource("/vue/sejourScreen.fxml"), "ecran.");

    }

    public void creerVuecreerSejour() {
        load(CreerSejour.class.getResource("/vue/creerSejour.fxml"), "ecran.");

    }

    public void creerConsulterSejourVue() {
        load(ConsulterSejour.class.getResource("/vue/consulterSejour.fxml"), "ecran.");

    }

    public void creerVueClient() {
        load(ClientScreen.class.getResource("/vue/clientScren.fxml"), "ecran.");

    }

    public void creerVueCreerClient() {
        load(CreerClient.class.getResource("/vue/creerClient.fxml"), "ecran.");

    }

    public void creerConsulterClientVue() {
        load(CreerClient.class.getResource("/vue/consulterClient.fxml"), "ecran.");

    }

    public void creerVueInscription() {
        load(InscriptionScreen.class.getResource("/vue/inscriptionScreen.fxml"), "ecran.");

    }

    public void creerVueInscriptionSejour() {
        load(CreerInscriptionSejour.class.getResource("/vue/creerInsriptionSejourClient.fxml"), "inscription sejour.");

    }

    public void creerConsulterInscriptionVue() {
        load(ConsulterInscription.class.getResource("/vue/consulterInscription.fxml"), "ecran.");

    }
}
