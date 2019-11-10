package controlleurvue.inscription;

import basededonnee.DBconnexion;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mysql.jdbc.PreparedStatement;
import controlleurvue.Vue;
import controlleurvue.centre.ConsulterCentre;
import controlleurvue.centre.CreerCentre;
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
import modele.Inscription;
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





    public void loadallcentre(String sql){


        JFXTreeTableColumn<Inscription,String> room_id=new JFXTreeTableColumn<>(" Id");
        room_id.setPrefWidth(100);
        room_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Inscription, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Inscription, String> param) {
                return param.getValue().getValue().id;
            }
        });




        JFXTreeTableColumn<Inscription,String> duree=new JFXTreeTableColumn<>("paiement");
        duree.setPrefWidth(100);
        duree.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Inscription, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Inscription, String> param) {
                return param.getValue().getValue().paiement;
            }
        });



        JFXTreeTableColumn<Inscription,String> date_debut=new JFXTreeTableColumn<>("observation");
        date_debut.setPrefWidth(110);
        date_debut.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Inscription, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Inscription, String> param) {
                return param.getValue().getValue().observation;
            }
        });


        JFXTreeTableColumn<Inscription,String> date_fin=new JFXTreeTableColumn<>("date inscription");
        date_fin.setPrefWidth(110);
        date_fin.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Inscription, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Inscription, String> param) {
                return param.getValue().getValue().dateinscription;
            }
        });




        JFXTreeTableColumn<Inscription,String> type=new JFXTreeTableColumn<>(" Client");
        type.setPrefWidth(110);
        type.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Inscription, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Inscription, String> param) {
                return param.getValue().getValue().code_client;
            }
        });


        JFXTreeTableColumn<Inscription,String> centre=new JFXTreeTableColumn<>(" Sejour");
        centre.setPrefWidth(110);
        centre.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Inscription, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Inscription, String> param) {
                return param.getValue().getValue().id_sejour;
            }
        });





        ObservableList<Inscription> rooms = FXCollections.observableArrayList();
        Connection connection= DBconnexion.getConnection();
        try {
            PreparedStatement ps=(PreparedStatement)connection.prepareStatement(sql);

            ResultSet rs=ps.executeQuery();

            while(rs.next()){



                PreparedStatement pss=(PreparedStatement)connection.prepareStatement(
                        "SELECT * FROM client where id ="+rs.getString(5)
                );

                ResultSet res=pss.executeQuery();
                String client="";
                while(res.next()){

                    client=res.getString(2)+" "+res.getString(3);


                }

                System.out.println("type "+rs.getInt(6));


                PreparedStatement ps2=(PreparedStatement)connection.prepareStatement(
                        "SELECT * FROM sejour where id_sejour ="+rs.getInt(6)
                );

                ResultSet rest=ps2.executeQuery();
                String sejour="";
                while(rest.next()){

                    sejour=rest.getString(5);


                }






                rooms.add(new Inscription(String.valueOf(rs.getInt(1)),
                        String.valueOf(rs.getString(2))
                        ,rs.getString(3),
                        rs.getString(4)
                        ,client,sejour));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsulterCentre.class.getName()).log(Level.SEVERE, null, ex);
        }


        final TreeItem<Inscription> root = new RecursiveTreeItem<Inscription>(rooms, RecursiveTreeObject::getChildren);

        treeView.getColumns().setAll(room_id,duree,date_debut,date_fin,type,centre);

        treeView.setRoot(root);
        treeView.setShowRoot(false);


    }









    public void initialize(URL location, ResourceBundle resources) {

        loadallcentre("SELECT * FROM inscription");
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


        //loadAllRooms("SELECT * FROM chambre WHERE numero ='"+search_text.getText().toString().trim()+"'");
        String sql="";
        if(search_text.getText().toString().length()==0){
            sql="SELECT * FROM `inscription` WHERE 1";
        }else {

            sql = "SELECT * FROM inscription WHERE id_inscription ='" + search_text.getText().toString().trim() + "'";
        }
        loadallcentre(sql);
    }

    public void EditerCentre(MouseEvent mouseEvent) {
    }

    public void SupprimerCentre(MouseEvent mouseEvent) {

        int res=0;

        String sql="DELETE FROM inscription WHERE id_inscription=?";
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
            loadallcentre("SELECT * FROM `inscription` WHERE 1");

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
