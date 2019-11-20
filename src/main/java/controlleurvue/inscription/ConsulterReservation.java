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
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import modele.*;
import notification.Notification;
import principale.Controlleur;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class ConsulterReservation implements Initializable, Vue {
    public JFXTextField search_text2;
    public JFXTextField search_text3;
    public Label nom;
    public Label prenom;
    public Label lprenom;
    public Label lnom;
    public Label age;
    public Label lgroupe;
    public Label groupe;
    public Label datenaissance;
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



    public JFXTreeTableColumn<Reservation,String> genererInscriptionId(){

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



    public JFXTreeTableColumn<Reservation,String> genererInscriptionObservation(){
        JFXTreeTableColumn<Reservation,String> inscription_observation=new JFXTreeTableColumn<>("depart");
        inscription_observation.setPrefWidth(110);
        inscription_observation.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Reservation, String> param) {
                return param.getValue().getValue().depart;
            }
        });
        return inscription_observation;
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
        JFXTreeTableColumn<Reservation,String> inscription_observation=this.genererInscriptionObservation();
        JFXTreeTableColumn<Reservation,String> inscription_dateinscription=this.genererDataInscriptioninscription();
        JFXTreeTableColumn<Reservation,String> inscription_client=this.genererInscriptionClient();
        JFXTreeTableColumn<Reservation,String> inscription_sejour=this.genererInscriptionSejour();
        ObservableList<Reservation> reservationsLi = FXCollections.observableArrayList();
        List<Reservation> reservations=reservationDao.getReservations();
        for(Reservation reservation: reservations){


            Client client=clientDao.getClientParId(reservation.code_client.get());
            System.out.println("id sejour :"+reservation.id_sejour.get());
            Sejour sejour=sejourDao.getSejourParId(reservation.id_sejour.get());
            String nom_client=client.nom_client.get()+" "+client.prenom_client.get();
            String id_sejour=sejour.id.get();


            Reservation reservation1=new Reservation(reservation.id.get(),reservation.dateinscription.get(),client.nom_client.get()+" "+client.prenom_client.get(),sejour.type.get(),reservation.depart.get());


            reservationsLi.add(reservation1);
        }
        final TreeItem<Reservation> root = new RecursiveTreeItem<Reservation>(reservationsLi, RecursiveTreeObject::getChildren);
        treeView.getColumns().setAll(inscription_id,inscription_observation,inscription_dateinscription,inscription_client,inscription_sejour);
        treeView.setRoot(root);
        treeView.setShowRoot(false);
        optimiserRechercheReservation();


        this.treeView.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                showClient(newValue)
        );


    }

    private void showClient(TreeItem<Reservation> newValue) {
        String date=(String)newValue.getValue().code_client.get();
        String[] args = date.split(" ");
        Client client=clientDao.getClientParNomEtPrenom(args[0],args[1]);
        if(client==null){
            System.out.println("null");
        }
        System.out.println("id groupe:"+client.groupe.get());
        Groupe groupe=groupeDao.getGroupeParId(client.groupe.get());
        nom.setText(client.nom_client.get());
        prenom.setText(client.prenom_client.get());
        this.groupe.setText(groupe.nom_groupe.get());
        datenaissance.setText(client.datenaissance.get());
    }


    private void optimiserRechercheReservation() {
        this.search_text.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                treeView.setPredicate(new Predicate<TreeItem<Reservation>>() {

                    @Override
                    public boolean test(TreeItem<Reservation> t) {

                        boolean flag =
                                t.getValue().depart.getValue().contains(newValue)
                                ||t.getValue().code_client.getValue().contains(newValue)
                                ||t.getValue().dateinscription.getValue().contains(newValue)
                                ||t.getValue().id_sejour.getValue().contains(newValue)
                                ;


                        return flag;


                    }
                });
            }

        });

    }






    private ClientDao clientDao;
    private SejourDao sejourDao;
    private ReservationDao reservationDao;
    private GroupeDao groupeDao;


    public void initialize(URL location, ResourceBundle resources) {
        clientDao=new ClientDaoImpl(DBconnexion.getConnection());
        sejourDao=new SejourDaoImpl(DBconnexion.getConnection());
        reservationDao=new ReservationDaoImpl(DBconnexion.getConnection());
        groupeDao=new GroupeDaoImpl(DBconnexion.getConnection());
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

    public void SupprimerCentre(MouseEvent mouseEvent) {
        int res=reservationDao.supprimerParId(search_text2.getText().toString());
        if(res>0){
            Notification.affichageSucces("sucess","inscription supprimer avec succes");
            chargertouslesinscriptions();
        }else{
            Notification.affichageEchec("echec","il y a eu un erreur dans la supresion de lisncription");

        }
    }

    public void registAction(ActionEvent actionEvent) {
    }

    public void hideSignupPane(ActionEvent actionEvent) {
    }
}
