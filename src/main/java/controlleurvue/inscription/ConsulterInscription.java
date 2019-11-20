package controlleurvue.inscription;

import basededonnee.DBconnexion;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controlleurvue.Vue;
import daos.ClientDao;
import daos.InscriptionDao;
import daos.SejourDao;
import daos.impl.ClientDaoImpl;
import daos.impl.InscriptionDaoImpl;
import daos.impl.SejourDaoImpl;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import modele.Client;
import modele.Inscription;
import modele.Sejour;
import notification.Notification;
import principale.Controlleur;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ConsulterInscription implements Initializable, Vue {
    public JFXTextField search_text2;
    public JFXTextField search_text3;
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


    public JFXTreeTableColumn<Inscription,String> genererInscriptionObservation(){
        JFXTreeTableColumn<Inscription,String> inscription_observation=new JFXTreeTableColumn<>("observation");
        inscription_observation.setPrefWidth(110);
        inscription_observation.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Inscription, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Inscription, String> param) {
                return param.getValue().getValue().depart;
            }
        });
        return inscription_observation;
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
        JFXTreeTableColumn<Inscription,String> inscription_observation=this.genererInscriptionObservation();
        JFXTreeTableColumn<Inscription,String> inscription_dateinscription=this.genererDataInscriptioninscription();
        JFXTreeTableColumn<Inscription,String> inscription_client=this.genererInscriptionClient();
        JFXTreeTableColumn<Inscription,String> inscription_sejour=this.genererInscriptionSejour();
        ObservableList<Inscription> inscriptions = FXCollections.observableArrayList();
        List<Inscription> inscription=inscriptionDao.getInscriptions();
        for(Inscription inscription1: inscription){


            Client client=clientDao.getClientParId(inscription1.code_client.get());
            System.out.println("id sejour :"+inscription1.id_sejour.get());
            Sejour sejour=sejourDao.getSejourParId(inscription1.id_sejour.get());
            String nom_client=client.nom_client.get()+" "+client.prenom_client.get();
            String id_sejour=sejour.id.get();
         
            Inscription inscription2=new Inscription(inscription1.id.get(),inscription1.paiement.get(),
                    inscription1.dateinscription.get(),  nom_client,id_sejour,  inscription1.depart.get()
            );

            inscriptions.add(inscription2);
        }
        final TreeItem<Inscription> root = new RecursiveTreeItem<Inscription>(inscriptions, RecursiveTreeObject::getChildren);
        treeView.getColumns().setAll(inscription_id,inscription_paiement,inscription_observation,inscription_dateinscription,inscription_client,inscription_sejour);
        treeView.setRoot(root);
        treeView.setShowRoot(false);


    }







    private ClientDao clientDao;
    private InscriptionDao inscriptionDao;
    private SejourDao sejourDao;


    public void initialize(URL location, ResourceBundle resources) {
        clientDao=new ClientDaoImpl(DBconnexion.getConnection());
        sejourDao=new SejourDaoImpl(DBconnexion.getConnection());
        inscriptionDao=new InscriptionDaoImpl(DBconnexion.getConnection());
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
        int res=inscriptionDao.supperimerParId(search_text2.getText().toString());
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
