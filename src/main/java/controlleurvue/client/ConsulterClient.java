package controlleurvue.client;

import basededonnee.DBconnexion;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mysql.jdbc.PreparedStatement;
import controlleurvue.Vue;
import controlleurvue.centre.ConsulterCentre;
import controlleurvue.centre.CreerCentre;
import daos.ClientDao;
import daos.impl.ClientDaoImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.Duration;
import modele.Client;
import modele.Groupe;
import modele.Sejour;
import notification.Notification;
import org.controlsfx.control.Notifications;
import principale.Controlleur;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsulterClient implements Initializable, Vue {


    public JFXTextField search_text2;
    public JFXTextField search_text3;
    /**
     * Initializes the controller class.
     */
    private Controlleur controlleur;
    @FXML
    private JFXTreeTableView<Client> treeView;
    @FXML
    private JFXTextField search_text;
    @FXML
    private StackPane stackepane;

    private ClientDao clientDao;


    public JFXTreeTableColumn<Client ,String> genererId(){
        JFXTreeTableColumn<Client,String> client_id=new JFXTreeTableColumn<>("Id");
        client_id.setPrefWidth(30);
        client_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
                return param.getValue().getValue().id;
            }
        });
        return client_id;

    }

    public JFXTreeTableColumn<Client ,String> genererNom(){
        JFXTreeTableColumn<Client,String> nom_client=new JFXTreeTableColumn<>("Nom");
        nom_client.setPrefWidth(100);
        nom_client.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
                return param.getValue().getValue().nom_client;
            }
        });
        return nom_client;
    }


    public JFXTreeTableColumn<Client ,String> genererPrenom(){

        JFXTreeTableColumn<Client,String> prenom_client=new JFXTreeTableColumn<>("Prenom");
        prenom_client.setPrefWidth(110);
        prenom_client.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
                return param.getValue().getValue().prenom_client;
            }
        });

        return prenom_client;
    }


    public JFXTreeTableColumn<Client ,String> genererDateNaissance(){

        JFXTreeTableColumn<Client,String> client_Date_naiss=new JFXTreeTableColumn<>(" Date_naiss");
        client_Date_naiss.setPrefWidth(110);
        client_Date_naiss.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
                return param.getValue().getValue().datenaissance;
            }
        });
        return client_Date_naiss;
    }

    public JFXTreeTableColumn<Client ,String> genererAge(){

        JFXTreeTableColumn<Client,String> client_age=new JFXTreeTableColumn<>(" Age(ans)");
        client_age.setPrefWidth(110);
        client_age.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
                ObservableValue<String> d_n = param.getValue().getValue().datenaissance;
                String dt=d_n.getValue().toString();
                LocalDate date_naiss= LocalDate.parse(dt);
                int age = Period.between(date_naiss, LocalDate.now()).getYears();
                StringProperty agee= new SimpleStringProperty(String.valueOf(age));
                return agee;
            }
        });
        return client_age;
    }


    public JFXTreeTableColumn<Client ,String> genererGroupe(){

        JFXTreeTableColumn<Client,String> client_groupe=new JFXTreeTableColumn<>(" Groupe");
        client_groupe.setPrefWidth(110);
        client_groupe.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
                return param.getValue().getValue().groupe;
            }
        });
        return client_groupe;
    }




    public void loadAllClient(){
        JFXTreeTableColumn<Client,String> client_id=this.genererId();
        JFXTreeTableColumn<Client,String> client_nom=this.genererNom();
        JFXTreeTableColumn<Client,String> client_prenom= this.genererPrenom();
        JFXTreeTableColumn<Client,String> client_groupe=this.genererGroupe();
        JFXTreeTableColumn<Client,String> client_datenaissance=this.genererDateNaissance();
        JFXTreeTableColumn<Client,String> client_age=this.genererAge();

        ObservableList<Client> clients = FXCollections.observableArrayList();
        List<Client> liste=clientDao.listeClient();
        System.out.println("nb clients "+liste.size());
        for(Client client:liste){
            clients.add(client);
        }
        final TreeItem<Client> root = new RecursiveTreeItem<Client>(clients, RecursiveTreeObject::getChildren);
        treeView.getColumns().setAll(client_id,client_nom,client_prenom,client_groupe,client_datenaissance,client_age);
        treeView.setRoot(root);
        treeView.setShowRoot(false);



    }

    public void initialize(URL location, ResourceBundle resources) {

        clientDao=new ClientDaoImpl(DBconnexion.getConnection());
        loadAllClient();
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
        this.controlleur.lancerPageClient();
    }

    public void setController(Controlleur controller) {
        this.controlleur=controller;
        clientDao=new ClientDaoImpl(DBconnexion.getConnection());
    }

    public void cherchecentreparid(MouseEvent mouseEvent) {


    }

    public void EditerCentre(MouseEvent mouseEvent) {
    }

    public void SupprimerCentre(MouseEvent mouseEvent) {

        int res=clientDao.supprimerClient(search_text2.getText().toString());

        if(res>0){
            Notification.affichageSucces("succes","client supprimer avec succes");

            loadAllClient();

            //updateStatus();
        }else{
            Notification.affichageEchec("echec","il y a eu erreur dans la suppression");

        }
    }

    public void registAction(ActionEvent actionEvent) {
    }

    public void hideSignupPane(ActionEvent actionEvent) {
    }

}
