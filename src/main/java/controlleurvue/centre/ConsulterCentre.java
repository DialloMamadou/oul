package controlleurvue.centre;

import basededonnee.DBconnexion;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mysql.jdbc.PreparedStatement;
import controlleurvue.Vue;
import daos.CentreDao;
import daos.impl.CentreDaoImpl;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.Duration;
import modele.Centre;
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

public class ConsulterCentre  implements Initializable, Vue {
    public JFXTextField search_text2;
    public JFXTextField search_text3;
    private Controlleur controlleur;



    String status=null;
    @FXML
    private JFXTreeTableView<Centre> treeView;
    @FXML
    private JFXTextField search_text;


    @FXML
    private StackPane stackepane;



    private JFXTreeTableColumn<Centre,String>creationTableColumCentreI(){

        JFXTreeTableColumn<Centre,String> table=new JFXTreeTableColumn<>("Centre Id");
        table.setPrefWidth(100);
        table.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Centre, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Centre, String> param) {
                return param.getValue().getValue().id;
            }
        });
        return table;
    }




    private JFXTreeTableColumn<Centre,String>creationTableColumnomcentre(){
        JFXTreeTableColumn<Centre,String> room_type=new JFXTreeTableColumn<>("nom du centre");
        room_type.setPrefWidth(110);
        room_type.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Centre, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Centre, String> param) {
                return param.getValue().getValue().nom_centre;
            }
        });

        return room_type;

    }






    public void loadallcentre(){

        JFXTreeTableColumn<Centre,String> room_id=this.creationTableColumCentreI();
        JFXTreeTableColumn<Centre,String> room_type=this.creationTableColumnomcentre();
        ObservableList<Centre> rooms = FXCollections.observableArrayList();
        List<Centre> liste=centreDao.listeCentres();
        for(Centre centre:liste){
            rooms.add(centre);
        }
        final TreeItem<Centre> root = new RecursiveTreeItem<Centre>(rooms, RecursiveTreeObject::getChildren);
        treeView.getColumns().setAll(room_id,room_type);
        treeView.setRoot(root);
        treeView.setShowRoot(false);


    }









    public void loadallcentreParId(){

        JFXTreeTableColumn<Centre,String> room_id=this.creationTableColumCentreI();
        JFXTreeTableColumn<Centre,String> room_type=this.creationTableColumnomcentre();
        ObservableList<Centre> rooms = FXCollections.observableArrayList();

        Centre centre=centreDao.getCentreParId(search_text.getText().toString());

        if(centre!=null) {
            rooms.add(centre);

            final TreeItem<Centre> root = new RecursiveTreeItem<Centre>(rooms, RecursiveTreeObject::getChildren);
            treeView.getColumns().setAll(room_id, room_type);
            treeView.setRoot(root);
            treeView.setShowRoot(false);
        }else{
            Image image=new Image("img/delete.png");
            Notifications notification=Notifications.create()
                    .title("Error")
                    .text("aucun centre avec cette id en base")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_LEFT)
                    .graphic(new ImageView(image));
            notification.darkStyle();
            notification.show();
            loadallcentre();

        }


    }



    public void initialize(URL location, ResourceBundle resources) {
        this.centreDao=new CentreDaoImpl(DBconnexion.getConnection());

        loadallcentre();
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
        this.controlleur.lancerPageCentre();

    }

    private CentreDao centreDao;

    public void setController(Controlleur controller) {

        this.centreDao=new CentreDaoImpl(DBconnexion.getConnection());
        this.controlleur=controller;
    }

    public void cherchecentreparid(MouseEvent mouseEvent) {

        if(search_text.getText().length()==0){
            loadallcentre();
        }
        loadallcentreParId();

    }

    public void EditerCentre(MouseEvent mouseEvent) {
    }






    public void SupprimerCentre(MouseEvent mouseEvent) {

        int res=0;

        if(search_text2.getText().length()!=0) {

            res=centreDao.supprimerCentre(search_text2.getText().toString());
        }




        if(res>0){
            Image image=new Image("img/mooo.png");
            Notifications notification=Notifications.create()
                    .title("finit")
                    .text("centre supprimer avec succss")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_LEFT)
                    .graphic(new ImageView(image));
            notification.darkStyle();
            notification.show();
            loadallcentre();

            //updateStatus();
        }else{
            Image image=new Image("img/delete.png");
            Notifications notification=Notifications.create()
                    .title("Error")
                    .text("il y a eu une erreur dans la suppression")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_LEFT)
                    .graphic(new ImageView(image));
            notification.darkStyle();
            notification.show();
        }
    }

    public void registAction(ActionEvent actionEvent) {
    }

    public void hideSignupPane(ActionEvent actionEvent) {
    }
}
