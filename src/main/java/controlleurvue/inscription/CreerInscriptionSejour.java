package controlleurvue.inscription;

import basededonnee.DBconnexion;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mysql.jdbc.PreparedStatement;
import controlleurvue.Vue;
import controlleurvue.centre.ConsulterCentre;
import controlleurvue.sejour.CreerSejour;
import dto.CentreDto;
import dto.ClientDto;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import javafx.util.Duration;
import modele.Client;
import org.controlsfx.control.Notifications;
import principale.Controlleur;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreerInscriptionSejour implements Initializable, Vue {




    public JFXTreeTableView<ClientDto> clients;
    public StackPane stackepane;
    public JFXTextField chercheClient;
    public JFXTextField chercheCentre;
    public JFXTreeTableView<CentreDto> vueCentre;
    public JFXButton valider;
    public Pane paneclient;
    public Label lnom;
    public Label nom;
    public Label lprenom;
    public Label prenom;
    public Label lage;
    public Label age;
    public Label lgroupe;
    public Label groupe;
    public Pane panesejour;
    public Label lcentre;
    public Label centre;
    public ComboBox type;
    public Label ltype;
    public Label lduree;
    public ComboBox duree;
    public Label ldate;
    public ComboBox date;
    public Label ldepart;
    public ComboBox depart;
    public Label lprix;
    public Label prix;
    public Label laccompte;
    public JFXTextField accompte;

    private Controlleur controlleur;


    private void chargementClients(){

    }



    @Override
    public void setController(Controlleur controller) {





        this.controlleur=controller;









        JFXTreeTableColumn<ClientDto,String> duree=new JFXTreeTableColumn<>("nom");
        duree.setPrefWidth(90);
        duree.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ClientDto, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ClientDto, String> param) {
                return param.getValue().getValue().nom_client;
            }
        });



        JFXTreeTableColumn<ClientDto,String> date_debut=new JFXTreeTableColumn<>("prenom");
        date_debut.setPrefWidth(90);
        date_debut.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ClientDto, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ClientDto, String> param) {
                return param.getValue().getValue().prenom_client;
            }
        });


        JFXTreeTableColumn<ClientDto,String> date_fin=new JFXTreeTableColumn<>("age");
        date_fin.setPrefWidth(90);
        date_fin.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ClientDto, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ClientDto, String> param) {
                return param.getValue().getValue().age_client;
            }
        });




        JFXTreeTableColumn<ClientDto,String> type=new JFXTreeTableColumn<>(" groupe");
        type.setPrefWidth(90);
        type.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ClientDto, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ClientDto, String> param) {
                return param.getValue().getValue().groupe;
            }
        });






        ObservableList<ClientDto> rooms = FXCollections.observableArrayList();
        Connection connection= DBconnexion.getConnection();
        String sql="SELECT * FROM client";
        try {
            PreparedStatement ps=(PreparedStatement)connection.prepareStatement(sql);

            ResultSet rs=ps.executeQuery();

            while(rs.next()){


                String sqql=             "SELECT * FROM groupe WHERE id_groupe ='" + rs.getString(5) + "'";

                PreparedStatement pss=(PreparedStatement)connection.prepareStatement(sqql);

                ResultSet res=pss.executeQuery();
                String s="";
                while(res.next()){

                    s=res.getString(2);


                }



                rooms.add(new ClientDto(rs.getString(2),rs.getString(3),
                        rs.getString(4),s));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsulterCentre.class.getName()).log(Level.SEVERE, null, ex);
        }


        final TreeItem<ClientDto> root = new RecursiveTreeItem<ClientDto>(rooms, RecursiveTreeObject::getChildren);

        clients.getColumns().setAll(duree,date_debut,date_fin,type);

        clients.setRoot(root);
        clients.setShowRoot(false);





        this.chercheClient.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                clients.setPredicate(new Predicate<TreeItem<ClientDto>>() {

                    @Override
                    public boolean test(TreeItem<ClientDto> t) {

                        boolean flag = t.getValue().age_client.getValue().contains(newValue)
                                || t.getValue().nom_client.getValue().contains(newValue)
                                || t.getValue().prenom_client.getValue().contains(newValue)
                                || t.getValue().groupe.getValue().contains(newValue)
                               ;
                        if(flag)
                        System.out.println("trouve");

                        return flag;


                    }
                });
            }

        });


        clients.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                showDetails(newValue)
        );








        chargementCentre();


















    }

    private void chargementCentre() {



        JFXTreeTableColumn<CentreDto,String> type=new JFXTreeTableColumn<>(" centre");
        type.setPrefWidth(90);
        type.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CentreDto, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<CentreDto, String> param) {
                return param.getValue().getValue().nom_centre;
            }
        });






        ObservableList<CentreDto> rooms = FXCollections.observableArrayList();
        Connection connection= DBconnexion.getConnection();
        String sql="SELECT * FROM centre";
        try {
            PreparedStatement ps=(PreparedStatement)connection.prepareStatement(sql);

            ResultSet rs=ps.executeQuery();

            while(rs.next()){





                rooms.add(new CentreDto(rs.getString(2)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsulterCentre.class.getName()).log(Level.SEVERE, null, ex);
        }


        final TreeItem<CentreDto> root = new RecursiveTreeItem<CentreDto>(rooms, RecursiveTreeObject::getChildren);

        vueCentre.getColumns().setAll(type);

        vueCentre.setRoot(root);
        vueCentre.setShowRoot(false);





        this.chercheCentre.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                vueCentre.setPredicate(new Predicate<TreeItem<CentreDto>>() {

                    @Override
                    public boolean test(TreeItem<CentreDto> t) {

                        boolean flag = t.getValue().nom_centre.getValue().contains(newValue);
                                if(flag){
                                    remplirCombo(t.getValue().nom_centre.getValue());
                                }
                                ;

                        return flag;


                    }
                });
            }

        });






        vueCentre.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                showCentre(newValue)
        );


    }

    private void remplirCombo(String value) {

       String sql = "SELECT * FROM centre WHERE nom_centre ='" + value + "'";

int id=-1;
        Connection connection= DBconnexion.getConnection();
        try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                 id=rs.getInt(1);

            }
        }catch (Exception e){

        }





        String sql2 = "SELECT * FROM sejour WHERE id_centre ='" + id + "'";

        List<String> listeSejour=new ArrayList<>();

        try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql2);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                listeSejour.add(rs.getString(2));
            }
        }catch (Exception e){

        }




    }

    private void showCentre(TreeItem<CentreDto> newValue) {
        centre.setText(newValue.getValue().nom_centre.getValue());
    }

    public void showDetails(TreeItem<ClientDto> pModel) {
        nom.setText(pModel.getValue().nom_client.getValue());
        prenom.setText(pModel.getValue().prenom_client.getValue());
        age.setText(pModel.getValue().age_client.getValue());
        groupe.setText(pModel.getValue().groupe.getValue());
    }

    public void back(MouseEvent mouseEvent) {
        this.controlleur.lancerPageInscription();
    }

    public void close(MouseEvent mouseEvent) {
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
