package controlleurvue.sejour;

import basededonnee.DBconnexion;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mysql.jdbc.PreparedStatement;
import controlleurvue.Vue;
import controlleurvue.centre.ConsulterCentre;
import controlleurvue.centre.CreerCentre;
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
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.Duration;
import modele.Centre;
import modele.Sejour;
import org.controlsfx.control.Notifications;
import principale.Controlleur;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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





    public void loadallcentre(String sql){


        JFXTreeTableColumn<Sejour,String> room_id=new JFXTreeTableColumn<>("sejour Id");
        room_id.setPrefWidth(100);
        room_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().id;
            }
        });




        JFXTreeTableColumn<Sejour,String> duree=new JFXTreeTableColumn<>("duree");
        duree.setPrefWidth(100);
        duree.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().duree;
            }
        });



        JFXTreeTableColumn<Sejour,String> date_debut=new JFXTreeTableColumn<>("date debut");
        date_debut.setPrefWidth(110);
        date_debut.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().date_debut;
            }
        });


        JFXTreeTableColumn<Sejour,String> date_fin=new JFXTreeTableColumn<>("date fin");
        date_fin.setPrefWidth(110);
        date_fin.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().date_fin;
            }
        });




        JFXTreeTableColumn<Sejour,String> type=new JFXTreeTableColumn<>(" type");
        type.setPrefWidth(110);
        type.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().type;
            }
        });


        JFXTreeTableColumn<Sejour,String> centre=new JFXTreeTableColumn<>(" centre");
        centre.setPrefWidth(110);
        centre.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().nom_centre;
            }
        });





        ObservableList<Sejour> rooms = FXCollections.observableArrayList();
        Connection connection= DBconnexion.getConnection();
        try {
            PreparedStatement ps=(PreparedStatement)connection.prepareStatement(sql);

            ResultSet rs=ps.executeQuery();

            while(rs.next()){



                PreparedStatement pss=(PreparedStatement)connection.prepareStatement("SELECT * FROM centre where " +
                        "id_centre = "+rs.getInt(6));

                ResultSet res=pss.executeQuery();
                String s="";
                while(res.next()){

                    s=res.getString(2);


                }



                rooms.add(new Sejour(String.valueOf(rs.getInt(1)),String.valueOf(rs.getInt(2)),rs.getString(3),
                        rs.getString(4),rs.getString(5),s));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsulterCentre.class.getName()).log(Level.SEVERE, null, ex);
        }


        final TreeItem<Sejour> root = new RecursiveTreeItem<Sejour>(rooms, RecursiveTreeObject::getChildren);

        treeView.getColumns().setAll(room_id,duree,date_debut,date_fin,type,centre);

        treeView.setRoot(root);
        treeView.setShowRoot(false);


    }









    public void initialize(URL location, ResourceBundle resources) {

        loadallcentre("SELECT * FROM sejour");
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


        //loadAllRooms("SELECT * FROM chambre WHERE numero ='"+search_text.getText().toString().trim()+"'");
        String sql="";
        if(search_text.getText().toString().length()==0){
            sql="SELECT * FROM `sejour` WHERE 1";
        }else {

            sql = "SELECT * FROM sejour WHERE id_sejour ='" + search_text.getText().toString().trim() + "'";
        }
        loadallcentre(sql);
       /* System.out.println(search_text.getText().toString());

        Connection connection= DBconnexion.getConnection();
        try {
            PreparedStatement ps=(PreparedStatement)connection.prepareStatement(sql);

            ResultSet rs=ps.executeQuery();

            int cpt=0;
            while(rs.next()){
                cpt++;
            }
            if(cpt==0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("centre recherche");
                alert.setHeaderText("Information Dialog");
                alert.setContentText("aucun centre avec cette id!");
                alert.showAndWait();
            }else{
                this.controlleur.lancerCentreVueSpe(search_text.getText());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsulterCentre.class.getName()).log(Level.SEVERE, null, ex);
        }
*/
    }

    public void EditerCentre(MouseEvent mouseEvent) {
    }

    public void SupprimerCentre(MouseEvent mouseEvent) {

        int res=0;

        String sql="DELETE FROM sejour WHERE id_sejour=?";
        Connection connection= DBconnexion.getConnection();
        try {
            PreparedStatement ps=(PreparedStatement)connection.prepareStatement(sql);
            if(search_text2.getText().length()!=0) {
                ps.setString(1, search_text2.getText().toString());
            }

            res=ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CreerCentre.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(res>0){
            Image image=new Image("img/mooo.png");
            Notifications notification=Notifications.create()
                    .title("finit")
                    .text("sejour supprimer avec succss")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_LEFT)
                    .graphic(new ImageView(image));
            notification.darkStyle();
            notification.show();
            loadallcentre("SELECT * FROM `sejour` WHERE 1");

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
