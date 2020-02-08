package controlleurvue.groupe;

import basededonnee.DBconnexion;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controlleurvue.Vue;
import daos.GroupeDao;
import daos.impl.GroupeDaoImpl;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.Duration;
import modele.Groupe;
import org.controlsfx.control.Notifications;
import principale.Controlleur;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ConsulterGroupe implements Initializable, Vue {
    public JFXTextField search_text2;
    public JFXTextField search_text3;
    public JFXTextField nbplace;
    public Label lnommairie;
    /**
     * Initializes the controller class.
     */


    private Controlleur controlleur;



    String status=null;
    @FXML
    private JFXTreeTableView<Groupe> treeView;
    @FXML
    private JFXTextField search_text;


    @FXML
    private StackPane stackepane;


    private JFXTreeTableColumn<Groupe,String> creerGroupeId(){
        JFXTreeTableColumn<Groupe,String> groupe_id=new JFXTreeTableColumn<>("groupe Id");
        groupe_id.setPrefWidth(100);
        groupe_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Groupe, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Groupe, String> param) {
                return param.getValue().getValue().id;
            }
        });
        return groupe_id;
    }


    private JFXTreeTableColumn<Groupe,String> creernomgroupe(){
        JFXTreeTableColumn<Groupe,String> groupe_nom=new JFXTreeTableColumn<>("nom du groupe");
        groupe_nom.setPrefWidth(110);
        groupe_nom.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Groupe, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Groupe, String> param) {
                return param.getValue().getValue().nom_groupe;
            }
        });

        return groupe_nom;
    }



    public void loadallgroupe(){


        JFXTreeTableColumn<Groupe,String> groupe_id=this.creerGroupeId();
        JFXTreeTableColumn<Groupe,String> groupe_nom=this.creernomgroupe();
        ObservableList<Groupe> groupes = FXCollections.observableArrayList();
        List<Groupe> liste=groupeDao.listeGroupes();
        for(Groupe groupe:liste){
            groupes.add(groupe);
        }

        final TreeItem<Groupe> root = new RecursiveTreeItem<Groupe>(groupes, RecursiveTreeObject::getChildren);
        treeView.getColumns().setAll(groupe_id,groupe_nom);
        treeView.setRoot(root);
        treeView.setShowRoot(false);


    }






    private GroupeDao groupeDao;



    public void initialize(URL location, ResourceBundle resources) {
        this.groupeDao=new GroupeDaoImpl(DBconnexion.getConnection());

        loadallgroupe();
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
        this.controlleur.lancerPageGroupe();

    }

    public void setController(Controlleur controller) {
        this.controlleur=controller;
    }

    public void cherchecentreparid(MouseEvent mouseEvent) {


        if(search_text.getText().length()==0){
            loadallgroupe();
        }
        loadAllgroupeParId();


    }

    private void loadAllgroupeParId() {
        JFXTreeTableColumn<Groupe,String> groupe_id=this.creerGroupeId();
        JFXTreeTableColumn<Groupe,String> groupe_nom=this.creernomgroupe();
        ObservableList<Groupe> groupes = FXCollections.observableArrayList();
        Groupe groupe=this.groupeDao.getGroupeParId(search_text.getText().toString());

        if(groupe!=null) {
            groupes.add(groupe);

            final TreeItem<Groupe> root = new RecursiveTreeItem<Groupe>(groupes, RecursiveTreeObject::getChildren);
            treeView.getColumns().setAll(groupe_id, groupe_nom);
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
            loadallgroupe();

        }
    }

    public void EditerCentre(MouseEvent mouseEvent) {
    }

    public void SupprimerCentre(MouseEvent mouseEvent) {

        int res=0;

        if(search_text2.getText().length()!=0) {

            res=groupeDao.supprimerGroupe(search_text2.getText().toString());
        }




        if(res>0){
            Image image=new Image("img/mooo.png");
            Notifications notification=Notifications.create()
                    .title("finit")
                    .text("groupe supprimer avec succss")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_LEFT)
                    .graphic(new ImageView(image));
            notification.darkStyle();
            notification.show();
            loadallgroupe();

            //updateStatus();
        }else{
            Image image=new Image("img/delete.png");
            Notifications notification=Notifications.create()
                    .title("Error " +
                            "")
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
