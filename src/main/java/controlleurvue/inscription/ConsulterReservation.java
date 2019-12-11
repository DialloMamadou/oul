package controlleurvue.inscription;

import basededonnee.DBconnexion;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controlleurvue.Vue;
import daos.*;
import daos.impl.*;
import dto.CentreDto;
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
    /**
     * Initializes the controller class.
     */


    private Controlleur controlleur;



    String status=null;
    @FXML
    private JFXTreeTableView<Reservation> treeView;
    @FXML
    private JFXTextField search_text;


    @FXML
    private StackPane stackepane;



    public JFXTreeTableColumn<Reservation,String>genererInscriptionId(){

        JFXTreeTableColumn<Reservation,String> inscription_id=new JFXTreeTableColumn<>(" Id");
        inscription_id.setPrefWidth(100);
        inscription_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Reservation, String> param) {
                return param.getValue().getValue().id;
            }
        });
        return inscription_id;
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





    public JFXTreeTableColumn<Reservation,String> genererDataInscriptioninscription(){
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


    public JFXTreeTableColumn<Reservation,String> genererInscriptionClient(){
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

    public  JFXTreeTableColumn<Reservation,String> genererInscriptionSejour(){

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

    public void chargertouslesinscriptions(){
        JFXTreeTableColumn<Reservation,String> inscription_id=this.genererInscriptionId();
        JFXTreeTableColumn<Reservation,String> inscription_dateinscription=this.genererDataInscriptioninscription();
        JFXTreeTableColumn<Reservation,String> inscription_client=this.genererInscriptionClient();
        JFXTreeTableColumn<Reservation,String> inscription_sejour=this.genererInscriptionSejour();
        JFXTreeTableColumn<Reservation,String> inscription_depart=this.genererDepart();
        ObservableList<Reservation> inscriptions = FXCollections.observableArrayList();
        List<Reservation> reservations=reservationDao.getReservations();
        for(Reservation reservation: reservations){


            Client client=clientDao.getClientParId(reservation.code_client.get());
            System.out.println("id sejour :"+reservation.id_sejour.get());
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

        this.idinscription.setText(newValue.getValue().id.get());
        Sejour sejour=sejourDao.getSejourParId(newValue.getValue().getTriche());
        System.out.println(sejour.toString());
        Centre centre=centreDao.getCentreParId(sejour.nom_centre.get());
        this.lprix.setText(sejour.prix.get());
        this.ldates.setText(sejour.date_debut.get()+" "+sejour.date_fin.get());
        this.ltype.setText(sejour.type.get());
        this.lcentre.setText(centre.nom_centre.get());
        this.lcapacite.setText(centre.capacite_centre.get());

        System.out.println("code client "+newValue.getValue().code_client.get());

        Client client=clientDao.getClientParId(newValue.getValue().getTriche2());
        this.lnom.setText(client.nom_client.get());
        this.lprenom.setText(client.prenom_client.get());
        this.ldate.setText(client.datenaissance.get());
        this.lemail.setText(client.email.get());
        this.lnumero.setText(client.numero.get());

        this.lidclient.setText(client.id.get());
        this.lidsejour.setText(sejour.id.get());

        int x=Integer.parseInt(sejour.prix.get());

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

                treeView.setPredicate(new Predicate<TreeItem<Reservation>>() {

                    @Override
                    public boolean test(TreeItem<Reservation> t) {

                        boolean flag =
                                t.getValue().depart.getValue().contains(newValue)
                                        || t.getValue().code_client.getValue().contains(newValue)
                                        || t.getValue().dateinscription.getValue().contains(newValue)
                                        || t.getValue().id.getValue().equals(newValue);
                        if(flag)
                            remplirGrideSejour(t);
                        System.out.println("trouve");


                        return flag;


                    }
                });
            }

        });
    }

    private void remplirGrideSejour(TreeItem<Reservation> t) {
    }


    private ClientDao clientDao;
    private InscriptionDao inscriptionDao;
    private ReservationDao reservationDao;
    private SejourDao sejourDao;
    private CentreDao centreDao;


    public void initialize(URL location, ResourceBundle resources) {
        clientDao=new ClientDaoImpl(DBconnexion.getConnection());
        sejourDao=new SejourDaoImpl(DBconnexion.getConnection());
        inscriptionDao=new InscriptionDaoImpl(DBconnexion.getConnection());
        reservationDao=new ReservationDaoImpl(DBconnexion.getConnection());
        centreDao=new CentreDaoImpl(DBconnexion.getConnection());
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
        if (result.isPresent()){

            Reservation reservation=reservationDao.getReservationParId(this.idinscription.getText());
            Inscription inscription=new Inscription(result.get(),reservation.dateinscription.get(),this.lidclient.getText(),
                    this.lidsejour.getText(),reservation.depart.get());
            reservationDao.supprimerParId(this.idinscription.getText());

            inscriptionDao.insererInscription(inscription);
            System.out.println("tout est bien ");
            chargertouslesinscriptions();


        }

// The Java 8 way to get the response value (with lambda expression).
    }

    public void registAction(ActionEvent actionEvent) {
    }

    public void hideSignupPane(ActionEvent actionEvent) {
    }
}