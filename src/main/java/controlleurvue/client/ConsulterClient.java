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
        JFXTreeTableColumn<Client,String> room_id=new JFXTreeTableColumn<>("Id");
        room_id.setPrefWidth(30);
        room_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
                return param.getValue().getValue().id;
            }
        });
        return room_id;

    }

    public JFXTreeTableColumn<Client ,String> genererNom(){
        JFXTreeTableColumn<Client,String> duree=new JFXTreeTableColumn<>("nom");
        duree.setPrefWidth(100);
        duree.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
                return param.getValue().getValue().nom_client;
            }
        });
        return duree;
    }


    public JFXTreeTableColumn<Client ,String> genererPrenom(){

        JFXTreeTableColumn<Client,String> date_debut=new JFXTreeTableColumn<>("prenom");
        date_debut.setPrefWidth(110);
        date_debut.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
                return param.getValue().getValue().prenom_client;
            }
        });

        return date_debut;
    }


    public JFXTreeTableColumn<Client ,String> genererAge(){

        JFXTreeTableColumn<Client,String> date_fin=new JFXTreeTableColumn<>("age");
        date_fin.setPrefWidth(30);
        date_fin.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
                return param.getValue().getValue().age_client;
            }
        });
        return date_fin;
    }


    public JFXTreeTableColumn<Client ,String> genererGroupe(){

        JFXTreeTableColumn<Client,String> type=new JFXTreeTableColumn<>(" groupe");
        type.setPrefWidth(110);
        type.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
                return param.getValue().getValue().groupe;
            }
        });
        return type;
    }


    public JFXTreeTableColumn<Client ,String> genererDateNaissance(){

        JFXTreeTableColumn<Client,String> type=new JFXTreeTableColumn<>(" groupe");
        type.setPrefWidth(110);
        type.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
                return param.getValue().getValue().groupe;
            }
        });
        return type;
    }



    public void loadAllClient(String sql){
        JFXTreeTableColumn<Client,String> room_id=this.genererId();
        JFXTreeTableColumn<Client,String> duree=this.genererNom();
        JFXTreeTableColumn<Client,String> date_debut= this.genererPrenom();
        JFXTreeTableColumn<Client,String> date_fin=this.genererAge();
        JFXTreeTableColumn<Client,String> type=this.genererGroupe();
        JFXTreeTableColumn<Client,String> date=this.genererDateNaissance();
        ObservableList<Client> rooms = FXCollections.observableArrayList();
        List<Client> liste=clientDao.listeClient();
        for(Client client:liste){
            rooms.add(client);
        }
        final TreeItem<Client> root = new RecursiveTreeItem<Client>(rooms, RecursiveTreeObject::getChildren);
        treeView.getColumns().setAll(room_id,duree,date_debut,date_fin,type,date);
        treeView.setRoot(root);
        treeView.setShowRoot(false);



    }

    public void initialize(URL location, ResourceBundle resources) {

        clientDao=new ClientDaoImpl(DBconnexion.getConnection());
        loadAllClient("SELECT * FROM client");
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

            loadAllClient("SELECT * FROM `client` WHERE 1");

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
