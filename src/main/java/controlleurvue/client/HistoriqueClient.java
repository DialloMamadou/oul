package controlleurvue.client;

import basededonnee.DBconnexion;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controlleurvue.Vue;
import daos.ClientDao;
import daos.InscriptionDao;
import daos.ReservationDao;
import daos.SejourDao;
import daos.impl.ClientDaoImpl;
import daos.impl.InscriptionDaoImpl;
import daos.impl.ReservationDaoImpl;
import daos.impl.SejourDaoImpl;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import modele.Client;
import modele.Inscription;
import modele.Reservation;
import modele.Sejour;
import principale.Controlleur;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HistoriqueClient implements Initializable, Vue {

    public JFXTreeTableView annulations;
    public JFXTreeTableView <Reservation>reservations;
    public JFXTreeTableView <Inscription>inscriptions;
    public JFXRadioButton btnannulation;
    public JFXRadioButton btnreservation;
    public JFXRadioButton btninscription;
    private Controlleur controlleur;
    public  static  int id;
    public Label lnom;
    public Label lprenom;
    public Label ldatenaissance;
    public Label lgroupe;
    public Label lnumero;
    public Label lobservation;
    public Label lemail;
    public Label ladresse;
    public Label lcodepostale;
    private ClientDao clientDao;

    private ReservationDao reservationDao;
    private InscriptionDao inscriptionDao;
    private SejourDao sejourDao;



    @Override
    public void setController(Controlleur controller) {
        this.controlleur=controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clientDao=new ClientDaoImpl(DBconnexion.getConnection());
        sejourDao=new SejourDaoImpl(DBconnexion.getConnection());
        inscriptionDao=new InscriptionDaoImpl(DBconnexion.getConnection());
        reservationDao=new ReservationDaoImpl(DBconnexion.getConnection());

        System.out.println("id = "+id);
        Client client=clientDao.getClientParId(String.valueOf(id));
        this.lnom.setText(client.nom_client.get());
        this.lprenom.setText(client.prenom_client.get());
        this.ladresse.setText(client.adresse.get());
        this.lcodepostale.setText(client.codePostale.get());
        this.ldatenaissance.setText(client.datenaissance.get());
        this.lnumero.setText(client.numero.get());
        this.lgroupe.setText(client.groupe.get());
        this.lobservation.setText(client.observation.get());
        this.lemail.setText(client.email.get());


        this.btninscription.setSelected(true);
        this.btnannulation.setSelected(false);
        this.btnreservation.setSelected(false);

        btninscription.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btnannulation.setSelected(false);
                btnreservation.setSelected(false);
                reservations.setVisible(false);
                inscriptions.setVisible(true);
                annulations.setVisible(false);
            }
        });



        btnreservation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btnannulation.setSelected(false);
                btninscription.setSelected(false);
                reservations.setVisible(true);
                inscriptions.setVisible(false);
                annulations.setVisible(false);
            }
        });


        btnannulation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btninscription.setSelected(false);
                btnreservation.setSelected(false);
                inscriptions.setVisible(false);
                annulations.setVisible(true);
                reservations.setVisible(false);
            }
        });




        remplirInscription();
        remplirReservation();


    }


    public JFXTreeTableColumn<Reservation,String>genererInscriptionIdR(){

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




    public JFXTreeTableColumn<Reservation,String> genererDepartR(){
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





    public JFXTreeTableColumn<Reservation,String> genererDataInscriptioninscriptionR(){
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


    public JFXTreeTableColumn<Reservation,String> genererInscriptionClientR(){
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

    public  JFXTreeTableColumn<Reservation,String> genererInscriptionSejourR() {

        JFXTreeTableColumn<Reservation, String> inscription_sejour = new JFXTreeTableColumn<>(" Sejour");
        inscription_sejour.setPrefWidth(110);
        inscription_sejour.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Reservation, String> param) {
                return param.getValue().getValue().id_sejour;
            }
        });
        return inscription_sejour;
    }

    private void remplirReservation() {

        JFXTreeTableColumn<Reservation,String> inscription_id=this.genererInscriptionIdR();
        JFXTreeTableColumn<Reservation,String> inscription_dateinscription=this.genererDataInscriptioninscriptionR();
        JFXTreeTableColumn<Reservation,String> inscription_client=this.genererInscriptionClientR();
        JFXTreeTableColumn<Reservation,String> inscription_sejour=this.genererInscriptionSejourR();
        JFXTreeTableColumn<Reservation,String> inscription_depart=this.genererDepartR();
        ObservableList<Reservation> inscriptions = FXCollections.observableArrayList();
        List<Reservation> reservations=reservationDao.getReservationsParIdClient(HistoriqueClient.id);
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
        this.reservations.getColumns().setAll(inscription_id,inscription_dateinscription,inscription_client,inscription_sejour,inscription_depart);
        this.reservations.setRoot(root);
        this.reservations.setShowRoot(false);


    }

    private void remplirInscription() {

        JFXTreeTableColumn<Inscription,String> inscription_id=this.genererInscriptionId();
        JFXTreeTableColumn<Inscription,String> inscription_paiement=this.genererInscriptionPaiement();
        JFXTreeTableColumn<Inscription,String> inscription_dateinscription=this.genererDataInscriptioninscription();
        JFXTreeTableColumn<Inscription,String> inscription_client=this.genererInscriptionClient();
        JFXTreeTableColumn<Inscription,String> inscription_sejour=this.genererInscriptionSejour();
        JFXTreeTableColumn<Inscription,String> inscription_depart=this.genererDepart();
        ObservableList<Inscription> inscriptions = FXCollections.observableArrayList();
        List<Inscription> inscription=inscriptionDao.getInscriptiosnParIdClient(HistoriqueClient.id);
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
        this.inscriptions.getColumns().setAll(inscription_id,inscription_paiement,inscription_dateinscription,inscription_client,inscription_sejour,inscription_depart);
        this.inscriptions.setRoot(root);
        this.inscriptions.setShowRoot(false);




    }






    public JFXTreeTableColumn<Inscription,String> genererInscriptionId(){

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



    public void retourmenuclient(MouseEvent mouseEvent) {
        controlleur.consulterClient();
    }

    public void visualiserinscription(MouseEvent mouseEvent) {
    }

    public void visualiserReservation(MouseEvent mouseEvent) {
    }

    public void visualiserannulation(MouseEvent mouseEvent) {
    }
}
