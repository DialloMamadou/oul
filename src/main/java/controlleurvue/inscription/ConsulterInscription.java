package controlleurvue.inscription;

import basededonnee.DBconnexion;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controlleurvue.Vue;
import daos.*;
import daos.impl.*;
import dto.CentreDto;
import dto.ClientDto;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Callback;
import modele.*;
import notification.Notification;
import principale.Controlleur;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class ConsulterInscription implements Initializable, Vue {
    public JFXTextField search_text2;
    public JFXTextField search_text3;

    public Label ldates;
    public Label ltype;
    public Label lcentre;
    public Label lcapacite;
    public Label lprix;
    public Label lnom;
    public Label lprenom;
    public Label ldate;
    public Label lnumero;
    public Label lemail;
    public Label lreste;
    public Label idinscription;
    public Label idclient;
    public Label idsejour;
    /**
     * Initializes the controller class.
     */


    private Controlleur controlleur;



    String status=null;
    @FXML
    private JFXTreeTableView<Inscription> treeView;
    @FXML
    private JFXTextField search_text;


    @FXML
    private StackPane stackepane;



    public JFXTreeTableColumn<Inscription,String>genererInscriptionId(){

        JFXTreeTableColumn<Inscription,String> inscription_id=new JFXTreeTableColumn<>(" Id");
        inscription_id.setPrefWidth(100);
        inscription_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Inscription, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Inscription, String> param) {
                return param.getValue().getValue().id;
            }
        });
        return inscription_id;
    }

    public JFXTreeTableColumn<Inscription,String> genererInscriptionPaiement(){
        JFXTreeTableColumn<Inscription,String> inscription_paiement=new JFXTreeTableColumn<>("paiement");
        inscription_paiement.setPrefWidth(100);
        inscription_paiement.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Inscription, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Inscription, String> param) {
                return param.getValue().getValue().paiement;
            }
        });
        return inscription_paiement;


    }


    public JFXTreeTableColumn<Inscription,String> genererDepart(){
        JFXTreeTableColumn<Inscription,String> inscription_paiement=new JFXTreeTableColumn<>("depart");
        inscription_paiement.setPrefWidth(100);
        inscription_paiement.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Inscription, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Inscription, String> param) {
                return param.getValue().getValue().depart;
            }
        });
        return inscription_paiement;


    }





    public JFXTreeTableColumn<Inscription,String> genererDataInscriptioninscription(){
        JFXTreeTableColumn<Inscription,String> inscription_dateinscription=new JFXTreeTableColumn<>("date inscription");
        inscription_dateinscription.setPrefWidth(110);
        inscription_dateinscription.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Inscription, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Inscription, String> param) {
                return param.getValue().getValue().dateinscription;
            }
        });
        return  inscription_dateinscription;
    }


    public JFXTreeTableColumn<Inscription,String> genererInscriptionClient(){
        JFXTreeTableColumn<Inscription,String> inscription_client=new JFXTreeTableColumn<>(" Client");
        inscription_client.setPrefWidth(110);
        inscription_client.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Inscription, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Inscription, String> param) {
                return param.getValue().getValue().code_client;
            }
        });
        return inscription_client;
    }

    public  JFXTreeTableColumn<Inscription,String> genererInscriptionSejour(){

        JFXTreeTableColumn<Inscription,String> inscription_sejour=new JFXTreeTableColumn<>(" Sejour");
        inscription_sejour.setPrefWidth(110);
        inscription_sejour.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Inscription, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Inscription, String> param) {
                return param.getValue().getValue().id_sejour;
            }
        });
        return  inscription_sejour;
    }

    public void chargertouslesinscriptions(){
        JFXTreeTableColumn<Inscription,String> inscription_id=this.genererInscriptionId();
        JFXTreeTableColumn<Inscription,String> inscription_paiement=this.genererInscriptionPaiement();
        JFXTreeTableColumn<Inscription,String> inscription_dateinscription=this.genererDataInscriptioninscription();
        JFXTreeTableColumn<Inscription,String> inscription_client=this.genererInscriptionClient();
        JFXTreeTableColumn<Inscription,String> inscription_sejour=this.genererInscriptionSejour();
        JFXTreeTableColumn<Inscription,String> inscription_depart=this.genererDepart();
        ObservableList<Inscription> inscriptions = FXCollections.observableArrayList();
        List<Inscription> inscription=inscriptionDao.getInscriptions();
        for(Inscription inscription1: inscription){


            Client client=clientDao.getClientParId(inscription1.code_client.get());
            System.out.println("id sejour :"+inscription1.id_sejour.get());
            Sejour sejour=sejourDao.getSejourParId(inscription1.id_sejour.get());
            String nom_client=client.nom_client.get()+" "+client.prenom_client.get();
            String id_sejour=sejour.id.get();

            Sejour sejour1=sejourDao.getSejourParId(id_sejour);
            Inscription inscription2=new Inscription(inscription1.id.get(),inscription1.paiement.get(),
                    inscription1.dateinscription.get(),  nom_client,sejour1.type.get(),  inscription1.depart.get()
            );

            inscription2.setTriche(sejour1.id.get());
            inscription2.setTriche2(client.id.get());
            inscriptions.add(inscription2);
        }
        final TreeItem<Inscription> root = new RecursiveTreeItem<Inscription>(inscriptions, RecursiveTreeObject::getChildren);
        treeView.getColumns().setAll(inscription_id,inscription_paiement,inscription_dateinscription,inscription_client,inscription_sejour,inscription_depart);
        treeView.setRoot(root);
        treeView.setShowRoot(false);


        optimiserRechercheSejour();
        treeView.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                showDetailsSejour(newValue)
        );

    }

    private void showDetailsSejour(TreeItem<Inscription> newValue) {

        this.idinscription.setText(newValue.getValue().id.get());
        Sejour sejour=sejourDao.getSejourParId(newValue.getValue().getTriche());
        this.idsejour.setText(sejour.id.get());
        System.out.println(sejour.toString());
        Centre centre=centreDao.getCentreParId(sejour.nom_centre.get());
        this.lprix.setText(sejour.prix.get());
        this.ldates.setText(sejour.date_debut.get()+" "+sejour.date_fin.get());
        this.ltype.setText(sejour.type.get());
        this.lcentre.setText(centre.nom_centre.get());
        this.lcapacite.setText(centre.capacite_centre.get());

        System.out.println("code client "+newValue.getValue().code_client.get());

        Client client=clientDao.getClientParId(newValue.getValue().getTriche2());
        this.idclient.setText(client.id.get());

        this.lnom.setText(client.nom_client.get());
        this.lprenom.setText(client.prenom_client.get());
        this.ldate.setText(client.datenaissance.get());
        this.lemail.setText(client.email.get());
        this.lnumero.setText(client.numero.get());

        int x=Integer.parseInt(sejour.prix.get());
        x=x-Integer.parseInt(newValue.getValue().paiement.get());
        this.lreste.setText(String.valueOf(x));
        if(x>0){
            this.lreste.setTextFill(Color.web("#ff0000"));
        }else if(x==0){
            this.lreste.setTextFill(Color.web("#00ff00"));

        }
    }

    private void optimiserRechercheSejour() {
        search_text.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                treeView.setPredicate(new Predicate<TreeItem<Inscription>>() {

                    @Override
                    public boolean test(TreeItem<Inscription> t) {

                        boolean flag =
                                t.getValue().depart.getValue().contains(newValue)
                                        || t.getValue().code_client.getValue().contains(newValue)
                                        || t.getValue().dateinscription.getValue().contains(newValue)
                                        || t.getValue().id.getValue().equals(newValue)
                                        ||t.getValue().paiement.getValue().equals(newValue);
                        ;
                        if(flag)
                            remplirGrideSejour(t);
                            System.out.println("trouve");


                        return flag;


                    }
                });
            }

        });
    }

    private void remplirGrideSejour(TreeItem<Inscription> t) {
    }


    private ClientDao clientDao;
    private InscriptionDao inscriptionDao;
    private SejourDao sejourDao;
    private CentreDao centreDao;
    private AnnulationDao annulationDao;
    private EvenementDao evenementDao;


    public void initialize(URL location, ResourceBundle resources) {
        clientDao=new ClientDaoImpl(DBconnexion.getConnection());
        sejourDao=new SejourDaoImpl(DBconnexion.getConnection());
        inscriptionDao=new InscriptionDaoImpl(DBconnexion.getConnection());
        centreDao=new CentreDaoImpl(DBconnexion.getConnection());
        annulationDao=new AnnulationDaoImpl(DBconnexion.getConnection());
        evenementDao=new EvenementDaoImpl(DBconnexion.getConnection());
        chargertouslesinscriptions();
    }






    public void close(javafx.scene.input.MouseEvent mouseEvent) {
        JFXDialogLayout dialogLayout=new JFXDialogLayout();
        dialogLayout.setHeading(new Text("ferme"));
        dialogLayout.setBody(new Text("vous voulez partir ?"));

        JFXButton ok=new JFXButton("ok");
        JFXButton cancel=new JFXButton("annule");

        final JFXDialog dialog=new JFXDialog(stackepane,dialogLayout, JFXDialog.DialogTransition.CENTER);

        ok.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(javafx.event.ActionEvent event) {
                System.exit(0);
            }
        });
        cancel.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            public void handle(javafx.event.ActionEvent event) {
                dialog.close();
            }
        });
        dialogLayout.setActions(ok,cancel);
        dialog.show();
    }

    public void goBack(javafx.scene.input.MouseEvent mouseEvent) {
        this.controlleur.lancerPageInscription();

    }

    public void setController(Controlleur controller) {
        this.controlleur=controller;
    }

    public void cherchecentreparid(MouseEvent mouseEvent) {
        chargertouslesinscriptions();
    }

    public void EditerCentre(MouseEvent mouseEvent) {
    }

    public void paiement(MouseEvent mouseEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("barre de paiement");
        dialog.setHeaderText("mettre a jour paiement ");
        dialog.setContentText("somme paye:");

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {

            int x = inscriptionDao.mettreAjourPaiement(this.idinscription.getText(), result.get());
            if (x != 0) {
                Evenement evenement = new Evenement("1", this.idclient.getText(), this.idsejour.getText(), "paiement-reservation", result.get(), new Date().toString());
                evenementDao.insererEvenement(evenement);
                String s = this.lreste.getText();
                System.out.println("valeur paye " + s);
                System.out.println("valeur vient paye " + result.get());
                int valeur = Integer.parseInt(s) - Integer.parseInt(result.get());
                System.out.println("valeur valeur = " + valeur);
                this.lreste.setText(String.valueOf(valeur));
                genererBis();


            }
        }
    }


        public void genererBis () {

            JFXTreeTableColumn<Inscription,String> inscription_id=this.genererInscriptionId();
            JFXTreeTableColumn<Inscription,String> inscription_paiement=this.genererInscriptionPaiement();
            JFXTreeTableColumn<Inscription,String> inscription_dateinscription=this.genererDataInscriptioninscription();
            JFXTreeTableColumn<Inscription,String> inscription_client=this.genererInscriptionClient();
            JFXTreeTableColumn<Inscription,String> inscription_sejour=this.genererInscriptionSejour();
            JFXTreeTableColumn<Inscription,String> inscription_depart=this.genererDepart();
            ObservableList<Inscription> inscriptions = FXCollections.observableArrayList();
            List<Inscription> inscription=inscriptionDao.getInscriptions();
            for(Inscription inscription1: inscription){


                Client client=clientDao.getClientParId(inscription1.code_client.get());
                System.out.println("id sejour :"+inscription1.id_sejour.get());
                Sejour sejour=sejourDao.getSejourParId(inscription1.id_sejour.get());
                String nom_client=client.nom_client.get()+" "+client.prenom_client.get();
                String id_sejour=sejour.id.get();

                Sejour sejour1=sejourDao.getSejourParId(id_sejour);
                Inscription inscription2=new Inscription(inscription1.id.get(),inscription1.paiement.get(),
                        inscription1.dateinscription.get(),  nom_client,sejour1.type.get(),  inscription1.depart.get()
                );

                inscription2.setTriche(sejour1.id.get());
                inscription2.setTriche2(client.id.get());
                inscriptions.add(inscription2);
            }
            final TreeItem<Inscription> root = new RecursiveTreeItem<Inscription>(inscriptions, RecursiveTreeObject::getChildren);
            treeView.getColumns().setAll(inscription_id,inscription_paiement,inscription_dateinscription,inscription_client,inscription_sejour,inscription_depart);
            treeView.setRoot(root);
            treeView.setShowRoot(false);
        }

// The Java 8 way to get the response value (with lambda expression).


    public void registAction(ActionEvent actionEvent) {
    }

    public void hideSignupPane(ActionEvent actionEvent) {
    }

    public void annuler(MouseEvent mouseEvent) {

        JFXDialogLayout dialogLayout=new JFXDialogLayout();
        dialogLayout.setHeading(new Text("ferme"));
        dialogLayout.setBody(new Text("vous voulez vraiment annuler cette inscription ?"));

        JFXButton ok=new JFXButton("oui");
        JFXButton cancel=new JFXButton("non");

        final JFXDialog dialog=new JFXDialog(stackepane,dialogLayout, JFXDialog.DialogTransition.CENTER);

        ok.setOnAction(MouseEvent ->annulerReservation(mouseEvent,dialog));
        cancel.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            public void handle(javafx.event.ActionEvent event) {
                dialog.close();
            }
        });
        dialogLayout.setActions(ok,cancel);
        dialog.show();

    }


    private void annulerReservation(MouseEvent mouseEvent,JFXDialog dialogLayout) {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("annulation reservation");
        dialog.setHeaderText("indiquer la raison ");
        dialog.setContentText("motif:");

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if(result.isPresent()){


            System.out.println("motif "+result.get());

            Annulation annulation=new Annulation(result.get(),this.idsejour.getText(),this.idclient.getText());
            int res=annulationDao.insererAnnulation(annulation);
            if(res==0){
                Notification.affichageEchec("echec annulation ", "il y a eu une erreur ");

            }else{

                Evenement evenement = new Evenement("1", this.idclient.getText(), this.idsejour.getText(), "annulation inscription",String.valueOf(0), new Date().toString());
                evenementDao.insererEvenement(evenement);
                Notification.affichageSucces("annulation","l annulation a bien ete effectue");
                int bis=inscriptionDao.supperimerParId(this.idinscription.getText());
                this.chargertouslesinscriptions();

            }
        }


        dialogLayout.close();


    }

    public void back(MouseEvent mouseEvent) { this.controlleur.lancerPageInscription();

    }
}

