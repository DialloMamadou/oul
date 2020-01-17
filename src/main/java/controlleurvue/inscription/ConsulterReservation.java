package controlleurvue.inscription;

import basededonnee.DBconnexion;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controlleurvue.Vue;
import daos.*;
import daos.impl.*;
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

public class ConsulterReservation implements Initializable, Vue {

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
    public Label lidclient;
    public Label lidsejour;
    public Label lmotif;
    public Label reservationgroupe;
    public Label groupe;
    private GroupeSejourClientDao groupeSejourClientDao;
    private Controlleur controlleur;
    @FXML
    private JFXTreeTableView<Reservation> treeView;
    @FXML
    private JFXTextField search_text;
    @FXML
    private StackPane stackepane;

    public JFXTreeTableColumn<Reservation,String> genererReservationId(){

        JFXTreeTableColumn<Reservation,String> reservation_id=new JFXTreeTableColumn<>(" Id");
        reservation_id.setPrefWidth(100);
        reservation_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Reservation, String> param) {
                return param.getValue().getValue().id;
            }
        });
        return reservation_id;
    }




    public JFXTreeTableColumn<Reservation,String> genererDepart(){
        JFXTreeTableColumn<Reservation,String> inscription_paiement=new JFXTreeTableColumn<>("depart");
        inscription_paiement.setPrefWidth(100);
        inscription_paiement.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Reservation, String> param) {
                return param.getValue().getValue().depart;
            }
        });
        return inscription_paiement;


    }





    public JFXTreeTableColumn<Reservation,String> genererDataReservation(){
        JFXTreeTableColumn<Reservation,String> inscription_dateinscription=new JFXTreeTableColumn<>("date inscription");
        inscription_dateinscription.setPrefWidth(110);
        inscription_dateinscription.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Reservation, String> param) {
                return param.getValue().getValue().dateinscription;
            }
        });
        return  inscription_dateinscription;
    }


    public JFXTreeTableColumn<Reservation,String> genererReservationClient(){
        JFXTreeTableColumn<Reservation,String> inscription_client=new JFXTreeTableColumn<>(" Client");
        inscription_client.setPrefWidth(110);
        inscription_client.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Reservation, String> param) {
                return param.getValue().getValue().code_client;
            }
        });
        return inscription_client;
    }

    public  JFXTreeTableColumn<Reservation,String> genererReservationSejour(){

        JFXTreeTableColumn<Reservation,String> inscription_sejour=new JFXTreeTableColumn<>(" Sejour");
        inscription_sejour.setPrefWidth(110);
        inscription_sejour.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Reservation, String> param) {
                return param.getValue().getValue().id_sejour;
            }
        });
        return  inscription_sejour;
    }

    public void genererTouteslesreservations(){
        JFXTreeTableColumn<Reservation,String> inscription_id=this.genererReservationId();
        JFXTreeTableColumn<Reservation,String> inscription_dateinscription=this.genererDataReservation();
        JFXTreeTableColumn<Reservation,String> inscription_client=this.genererReservationClient();
        JFXTreeTableColumn<Reservation,String> inscription_sejour=this.genererReservationSejour();
        JFXTreeTableColumn<Reservation,String> inscription_depart=this.genererDepart();
        ObservableList<Reservation> inscriptions = FXCollections.observableArrayList();
        List<Reservation> reservations=reservationDao.getReservations();
        for(Reservation reservation: reservations){
            Client client=clientDao.getClientParId(reservation.code_client.get());
            Sejour sejour=sejourDao.getSejourParId(reservation.id_sejour.get());
            String nom_client=client.nom_client.get()+" "+client.prenom_client.get();
            String id_sejour=sejour.id.get();
            Sejour sejour1=sejourDao.getSejourParId(id_sejour);
            Reservation reservation1=new Reservation(reservation.id.get(),reservation.dateinscription.get(),
                    nom_client,sejour1.type.get(),reservation.depart.get());
            reservation1.setTriche(sejour1.id.get());
            reservation1.setTriche2(client.id.get());
            inscriptions.add(reservation1);
        }
        final TreeItem<Reservation> root = new RecursiveTreeItem<Reservation>(inscriptions, RecursiveTreeObject::getChildren);
        treeView.getColumns().setAll(inscription_id,inscription_dateinscription,inscription_client,inscription_sejour,inscription_depart);
        treeView.setRoot(root);
        treeView.setShowRoot(false);
        optimiserRechercheSejour();
        treeView.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                showDetailsSejour(newValue)
        );

    }

    private void showDetailsSejour(TreeItem<Reservation> newValue) {
        if(newValue!=null) {
            this.idinscription.setText(newValue.getValue().id.get());
            System.out.println("id inscription " + newValue.getValue().id.get());
            Sejour sejour = sejourDao.getSejourParId(newValue.getValue().getTriche());
            Centre centre = centreDao.getCentreParId(sejour.nom_centre.get());
            this.lprix.setText(sejour.prix.get());



            this.ldates.setText(sejour.date_debut.get() + " " + sejour.date_fin.get());
            this.ltype.setText(sejour.type.get());
            this.lcentre.setText(centre.nom_centre.get());
            this.lcapacite.setText(centre.capacite_centre.get());
            Client client = clientDao.getClientParId(newValue.getValue().getTriche2());
            this.lnom.setText(client.nom_client.get());
            this.lprenom.setText(client.prenom_client.get());
            this.ldate.setText(client.datenaissance.get());
            this.lemail.setText(client.email.get());
            this.lnumero.setText(client.numero.get());
            this.lidclient.setText(client.id.get());
            this.lidsejour.setText(sejour.id.get());
            int x = Integer.parseInt(sejour.prix.get());
            this.lreste.setText(String.valueOf(x));
            if (x > 0) {
                this.lreste.setTextFill(Color.web("#ff0000"));
            } else if (x == 0) {
                this.lreste.setTextFill(Color.web("#00ff00"));

            }


            this.groupe.setText(client.groupe.get());

            GroupeSejourClient groupeSejourClient=groupeSejourClientDao.getGroupeSejourClient(client.groupe.get(),
                    sejour.id.get(),client.id.get());
            if(groupeSejourClient==null){
                this.reservationgroupe.setText("faux");
            }else{
                this.reservationgroupe.setText("true");
            }
        }
    }

    private void optimiserRechercheSejour() {
        search_text.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                treeView.setPredicate(new Predicate<TreeItem<Reservation>>() {

                    @Override
                    public boolean test(TreeItem<Reservation> t) {

                        boolean flag =
                                t.getValue().depart.getValue().toLowerCase().contains(newValue.toLowerCase())
                                        || t.getValue().code_client.getValue().toLowerCase().contains(newValue.toLowerCase())
                                        || t.getValue().dateinscription.getValue().toLowerCase().contains(newValue.toLowerCase())
                                        || t.getValue().id.getValue().toLowerCase().equals(newValue.toLowerCase());


                        return flag;


                    }
                });
            }

        });
    }


    private ClientDao clientDao;
    private InscriptionDao inscriptionDao;
    private ReservationDao reservationDao;
    private SejourDao sejourDao;
    private CentreDao centreDao;
    private AnnulationDao annulationDao;
    private EvenementDao evenementDao;


    public void initialize(URL location, ResourceBundle resources) {
        groupeSejourClientDao=new GroupeSejourClientDaoImpl(DBconnexion.getConnection());
        clientDao=new ClientDaoImpl(DBconnexion.getConnection());
        sejourDao=new SejourDaoImpl(DBconnexion.getConnection());
        inscriptionDao=new InscriptionDaoImpl(DBconnexion.getConnection());
        reservationDao=new ReservationDaoImpl(DBconnexion.getConnection());
        centreDao=new CentreDaoImpl(DBconnexion.getConnection());
        annulationDao=new AnnulationDaoImpl(DBconnexion.getConnection());
        evenementDao=new EvenementDaoImpl(DBconnexion.getConnection());
        genererTouteslesreservations();
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
        genererTouteslesreservations();
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

            Evenement evenement = new Evenement("1", this.lidclient.getText(), this.lidsejour.getText(), "paiement-reservation", result.get(), new Date().toString());
            evenementDao.insererEvenement(evenement);
            Reservation reservation = reservationDao.getReservationParId(this.idinscription.getText());
            Inscription inscription = new Inscription(result.get(), reservation.dateinscription.get(), this.lidclient.getText(),
                    this.lidsejour.getText(), reservation.depart.get());
            reservationDao.supprimerParId(this.idinscription.getText());

            inscriptionDao.insererInscription(inscription);
            System.out.println("tout est bien ");
            Notification.affichageSucces("paiement succes", "le paiement a bien ete pris en comtpe");
            //genererTouteslesreservations();
            genererBis();


        }
    }


        public void genererBis(){
            JFXTreeTableColumn<Reservation,String> inscription_id=this.genererReservationId();
            JFXTreeTableColumn<Reservation,String> inscription_dateinscription=this.genererDataReservation();
            JFXTreeTableColumn<Reservation,String> inscription_client=this.genererReservationClient();
            JFXTreeTableColumn<Reservation,String> inscription_sejour=this.genererReservationSejour();
            JFXTreeTableColumn<Reservation,String> inscription_depart=this.genererDepart();
            ObservableList<Reservation> inscriptions = FXCollections.observableArrayList();
            List<Reservation> reservations=reservationDao.getReservations();
            for(Reservation reservation: reservations){
                Client client=clientDao.getClientParId(reservation.code_client.get());
                Sejour sejour=sejourDao.getSejourParId(reservation.id_sejour.get());
                String nom_client=client.nom_client.get()+" "+client.prenom_client.get();
                String id_sejour=sejour.id.get();
                Sejour sejour1=sejourDao.getSejourParId(id_sejour);
                Reservation reservation1=new Reservation(reservation.id.get(),reservation.dateinscription.get(),
                        nom_client,sejour1.type.get(),reservation.depart.get());
                reservation1.setTriche(sejour1.id.get());
                reservation1.setTriche2(client.id.get());
                inscriptions.add(reservation1);
            }
            final TreeItem<Reservation> root = new RecursiveTreeItem<Reservation>(inscriptions, RecursiveTreeObject::getChildren);
            treeView.getColumns().setAll(inscription_id,inscription_dateinscription,inscription_client,inscription_sejour,inscription_depart);
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
        dialogLayout.setBody(new Text("vous voulez vraiment annuler cette reservation ?"));

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

            Annulation annulation=new Annulation(result.get(),this.lidsejour.getText(),this.lidclient.getText());
            int res=annulationDao.insererAnnulation(annulation);
            if(res==0){
                Notification.affichageEchec("echec annulation ", "il y a eu une erreur ");

            }else{



                Evenement evenement = new Evenement("1", this.lidclient.getText(), this.lidsejour.getText(), "anulation-reservation", String.valueOf(0), new Date().toString());
                int x=evenementDao.insererEvenement(evenement);
                if(x==0){
                    System.out.println("evenenement non enregistre");
                }else{
                    System.out.println("evenenment enregistre");
                }
                Notification.affichageSucces("annulation","l annulation a bien ete effectue");
                int bis=reservationDao.supprimerParId(this.idinscription.getText());
                this.genererTouteslesreservations();

            }
        }


        dialogLayout.close();


    }
}
