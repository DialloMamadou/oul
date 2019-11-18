package controlleurvue.sejour;

import basededonnee.DBconnexion;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controlleurvue.Vue;
import controlleurvue.inscription.CreerInscriptionSejour;
import daos.SejourDao;
import daos.impl.SejourDaoImpl;
import dto.ClientDto;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
import java.util.function.Predicate;

public class ConsulterSejour implements Initializable, Vue {


    public JFXTextField search_text2;
    public JFXTextField search_text3;
    public Label lcentre;
    public Label lsejour;
    public Label ldate;
    public Label lage;
    public Label lprix;
    public Label lcapacite;
    /**
     * Initializes the controller class.
     */

    private Controlleur controlleur;



    String status=null;
    @FXML
    private JFXTreeTableView<Sejour> treeView;
    @FXML
    private JFXTextField cherchersejour;


    @FXML
    private StackPane stackepane;



    public JFXTreeTableColumn<Sejour,String> genererSejourId(){
        JFXTreeTableColumn<Sejour,String> sejour_id=new JFXTreeTableColumn<>("sejour Id");
        sejour_id.setPrefWidth(100);
        sejour_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().id;
            }
        });
        return  sejour_id;

    }



    public JFXTreeTableColumn<Sejour,String> genererSejourDuree(){
        JFXTreeTableColumn<Sejour,String> sejour_duree =new JFXTreeTableColumn<>("sejour_duree");
        sejour_duree.setPrefWidth(100);
        sejour_duree.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().duree;
            }
        });
        return sejour_duree;
    }





    public JFXTreeTableColumn<Sejour,String> genererDateDebut(){

        JFXTreeTableColumn<Sejour,String> sejour_datedebut=new JFXTreeTableColumn<>("date debut");
        sejour_datedebut.setPrefWidth(110);
        sejour_datedebut.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().date_debut;
            }
        });
        return  sejour_datedebut;
    }



    public JFXTreeTableColumn<Sejour,String> genererDateFin(){

        JFXTreeTableColumn<Sejour,String> sejour_datefin=new JFXTreeTableColumn<>("date fin");
        sejour_datefin.setPrefWidth(110);
        sejour_datefin.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().date_fin;
            }
        });
        return sejour_datefin;
    }




    public JFXTreeTableColumn<Sejour,String> genererSejourType(){
        JFXTreeTableColumn<Sejour,String> sejour_type=new JFXTreeTableColumn<>(" type");
        sejour_type.setPrefWidth(110);
        sejour_type.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().type;
            }
        });

        return sejour_type;
    }



    public JFXTreeTableColumn<Sejour,String> genererCentre(){
        JFXTreeTableColumn<Sejour,String> sejour_centre=new JFXTreeTableColumn<>(" centre");
        sejour_centre.setPrefWidth(110);
        sejour_centre.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().nom_centre;
            }
        });

        return sejour_centre;
    }



    public void chargerTousLesSejours(){
        JFXTreeTableColumn<Sejour,String> sejour_id=this.genererSejourId();
        JFXTreeTableColumn<Sejour,String> sejour_duree =this.genererSejourDuree();
        JFXTreeTableColumn<Sejour,String> sejour_datedebut=this.genererDateDebut();
        JFXTreeTableColumn<Sejour,String> sejour_datefin=this.genererDateFin();
        JFXTreeTableColumn<Sejour,String> sejour_type=this.genererSejourType();
        JFXTreeTableColumn<Sejour,String> sejour_centre=this.genererCentre();
        ObservableList<Sejour> sejours = FXCollections.observableArrayList();
        List<Sejour> liste=sejourDao.listeSejour();
        for(Sejour sejour:liste){
            System.out.println("hihihihihihi");
            sejours.add(sejour);
        }

        final TreeItem<Sejour> root = new RecursiveTreeItem<Sejour>(sejours, RecursiveTreeObject::getChildren);

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

        this.treeView.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                showDetails(newValue)
        );
        optimiserRecherche();
    }

    private void showDetails(TreeItem<Sejour> newValue) {
        this.lage.setText(newValue.getValue().ageMin.get()+" - "+newValue.getValue().ageMax.get());
        this.lcapacite.setText(newValue.getValue().capacite.get());
        this.lcentre.setText(newValue.getValue().nom_centre.get());
        this.lprix.setText(newValue.getValue().prix.get());
        this.lsejour.setText(newValue.getValue().type.get());
        this.lsejour.setText(newValue.getValue().date_debut.get()+" au "+newValue.getValue().date_fin.get());
    }


    private void optimiserRecherche() {
        this.cherchersejour.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                ConsulterSejour.this.treeView.setPredicate(new Predicate<TreeItem<Sejour>>() {

                    @Override
                    public boolean test(TreeItem<Sejour> t) {

                        boolean flag =t.getValue().type.get().contains(newValue)
                                ||t.getValue().nom_centre.get().contains(newValue)
                                ||t.getValue().date_fin.get().contains(newValue)
                                || t.getValue().date_debut.get().contains(newValue)
                               || t.getValue().duree.get().contains(newValue)
                                        ||t.getValue().prix.get().contains(newValue);
                               /* t.getValue().nom_client.getValue().contains(newValue)
                                        || t.getValue().prenom_client.getValue().contains(newValue)
                                        || t.getValue().groupe.getValue().contains(newValue)
                                        || t.getValue().datenaissance.getValue().equals(newValue)
                                        ||t.getValue().id_client.getValue().equals(newValue);
                        ;*/
                        if(flag)
                            System.out.println("trouve");

                        return flag;


                    }
                });
            }

        });
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

    public void genereliste(MouseEvent mouseEvent) {


    }
}
