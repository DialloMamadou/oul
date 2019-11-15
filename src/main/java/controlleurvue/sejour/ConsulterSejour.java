package controlleurvue.sejour;

import basededonnee.DBconnexion;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controlleurvue.Vue;
import daos.SejourDao;
import daos.impl.SejourDaoImpl;
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
import modele.Sejour;
import notification.Notification;
import principale.Controlleur;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ConsulterSejour implements Initializable, Vue {
    public JFXTextField search_text2;
    public JFXTextField search_text3;
    /**
     * Initializes the controller class.
     */

    private Controlleur controlleur;



    String status=null;
    @FXML
    private JFXTreeTableView<Sejour> treeView;
    @FXML
    private JFXTextField search_text;


    @FXML
    private StackPane stackepane;





    public void chargerTousLesSejours(){


        JFXTreeTableColumn<Sejour,String> sejour_id=new JFXTreeTableColumn<>("sejour Id");
        sejour_id.setPrefWidth(100);
        sejour_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().id;
            }
        });




        JFXTreeTableColumn<Sejour,String> sejour_duree =new JFXTreeTableColumn<>("sejour_duree");
        sejour_duree.setPrefWidth(100);
        sejour_duree.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().duree;
            }
        });



        JFXTreeTableColumn<Sejour,String> sejour_datedebut=new JFXTreeTableColumn<>("date debut");
        sejour_datedebut.setPrefWidth(110);
        sejour_datedebut.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().date_debut;
            }
        });


        JFXTreeTableColumn<Sejour,String> sejour_datefin=new JFXTreeTableColumn<>("date fin");
        sejour_datefin.setPrefWidth(110);
        sejour_datefin.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().date_fin;
            }
        });




        JFXTreeTableColumn<Sejour,String> sejour_type=new JFXTreeTableColumn<>(" type");
        sejour_type.setPrefWidth(110);
        sejour_type.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().type;
            }
        });


        JFXTreeTableColumn<Sejour,String> sejour_centre=new JFXTreeTableColumn<>(" centre");
        sejour_centre.setPrefWidth(110);
        sejour_centre.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().nom_centre;
            }
        });






        ObservableList<Sejour> rooms = FXCollections.observableArrayList();
        List<Sejour> liste=sejourDao.listeSejour();
        for(Sejour sejour:liste){
            System.out.println("hihihihihihi");
            rooms.add(sejour);
        }

        final TreeItem<Sejour> root = new RecursiveTreeItem<Sejour>(rooms, RecursiveTreeObject::getChildren);

        treeView.getColumns().setAll(sejour_id, sejour_duree,sejour_datedebut,sejour_datefin,sejour_type,sejour_centre);

        treeView.setRoot(root);
        treeView.setShowRoot(false);


    }









    private SejourDao sejourDao;
    public void initialize(URL location, ResourceBundle resources) {
        sejourDao=new SejourDaoImpl(DBconnexion.getConnection());
        chargerTousLesSejours();
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
        this.controlleur.lancerPageSejour();

    }

    public void setController(Controlleur controller) {
        this.controlleur=controller;
    }

    public void cherchecentreparid(MouseEvent mouseEvent) {
        chargerTousLesSejours();

    }

    public void EditerCentre(MouseEvent mouseEvent) {
    }

    public void SupprimerCentre(MouseEvent mouseEvent) {
        int res=sejourDao.supprimerSejourParid(search_text2.getText().toString());
        if(res>0){
            Notification.affichageSucces("succes","suces dans la supression du sejour");
            chargerTousLesSejours();
        }else{
            Notification.affichageEchec("echec","il y a eu un probleme lors de la supresion du sejour" );

        }
    }

    public void registAction(ActionEvent actionEvent) {
    }

    public void hideSignupPane(ActionEvent actionEvent) {
    }
}
