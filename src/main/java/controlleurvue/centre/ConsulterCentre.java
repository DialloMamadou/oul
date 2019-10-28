package controlleurvue.centre;

import basededonnee.DBconnexion;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mysql.jdbc.PreparedStatement;
import controlleurvue.Vue;
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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsulterCentre  implements Initializable, Vue {
    public JFXTextField search_text2;
    public JFXTextField search_text3;
    /**
     * Initializes the controller class.
     */

    private Controlleur controlleur;



    String status=null;
    @FXML
    private JFXTreeTableView<Centre> treeView;
    @FXML
    private JFXTextField search_text;


    @FXML
    private StackPane stackepane;





    public void loadallcentre(String sql){


        JFXTreeTableColumn<Centre,String> room_id=new JFXTreeTableColumn<>("Centre Id");
        room_id.setPrefWidth(100);
        room_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Centre, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Centre, String> param) {
                return param.getValue().getValue().id;
            }
        });


        JFXTreeTableColumn<Centre,String> room_type=new JFXTreeTableColumn<>("nom du centre");
        room_type.setPrefWidth(110);
        room_type.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Centre, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Centre, String> param) {
                return param.getValue().getValue().nom_centre;
            }
        });



        ObservableList<Centre> rooms = FXCollections.observableArrayList();
        Connection connection= DBconnexion.getConnection();
        try {
            PreparedStatement ps=(PreparedStatement)connection.prepareStatement(sql);

            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                rooms.add(new Centre(rs.getInt(1)+"",rs.getString(2)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsulterCentre.class.getName()).log(Level.SEVERE, null, ex);
        }


        final TreeItem<Centre> root = new RecursiveTreeItem<Centre>(rooms, RecursiveTreeObject::getChildren);

        treeView.getColumns().setAll(room_id,room_type);
        treeView.setRoot(root);
        treeView.setShowRoot(false);


    }









    public void initialize(URL location, ResourceBundle resources) {

        loadallcentre("SELECT * FROM centre");
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

    public void setController(Controlleur controller) {
        this.controlleur=controller;
    }

    public void cherchecentreparid(MouseEvent mouseEvent) {


            //loadAllRooms("SELECT * FROM chambre WHERE numero ='"+search_text.getText().toString().trim()+"'");


        String sql="SELECT * FROM centre WHERE id_centre ='"+search_text.getText().toString().trim()+"'";
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

        String sql="DELETE FROM centre WHERE id_centre=?";
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
                    .text("centre supprimer avec succss")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_LEFT)
                    .graphic(new ImageView(image));
            notification.darkStyle();
            notification.show();
            loadallcentre("SELECT * FROM `centre` WHERE 1");

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
