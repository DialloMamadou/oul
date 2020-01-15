package controlleurvue.client;

import basededonnee.DBconnexion;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mysql.jdbc.PreparedStatement;
import controlleurvue.Vue;
import controlleurvue.centre.ConsulterCentre;
import controlleurvue.sejour.CreerSejour;
import daos.ClientDao;
import daos.GroupeDao;
import daos.impl.ClientDaoImpl;
import daos.impl.GroupeDaoImpl;
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
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import javafx.util.Duration;
import modele.Client;
import modele.Groupe;
import notification.Notification;
import org.controlsfx.control.Notifications;
import principale.Controlleur;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreerClient  implements Initializable, Vue {


    public StackPane stack;
    public TextField nom;

    private String id_Groupe;
    public TextField prenom;
    public TextField portable;
    public TextField observation;
    public TextField email;
    public TextField poste;
    public DatePicker annee;
    public TextField adresse;
    public Label groupelabel;
    public JFXTreeTableView<Groupe> tablegroupe;
    public JFXTextField chercher;

    private Controlleur controlleur;

    private GroupeDao groupeDao;
    private ClientDao clientDao;


    @Override
    public void setController(Controlleur controller) {
        groupeDao =new GroupeDaoImpl(DBconnexion.getConnection());
        clientDao=new ClientDaoImpl(DBconnexion.getConnection());
        this.controlleur=controller;
        List<Groupe> liste=groupeDao.listeGroupes();

    }
    public void back(MouseEvent mouseEvent) {
        this.controlleur.lancerPageClient();
    }
    public void close(MouseEvent mouseEvent) {
    }




    public void book(MouseEvent mouseEvent) {
    //    Groupe groupe=groupeDao.trouverGroupeParNomGroupe(this.groupe.getValue().toString().trim());

        String prenom=this.prenom.getText();
        String nom=this.nom.getText();
      //  String id_group=groupe.id.get();
        String portable=this.portable.getText();
        String observation=this.observation.getText();
        String email=this.email.getText();
        String adresse=this.adresse.getText();
        String poste=this.poste.getText();
        System.out.println("poste =="+poste);
        String dateNaissance=this.annee.getValue().toString();



        String groupeL=this.groupelabel.getText();

        Client client=new Client("",nom,prenom,id_Groupe,portable,observation,email,adresse,poste,dateNaissance);

        int res=this.clientDao.insererClient(client);

        if(res>0){
            Notification.affichageSucces("succes","client creer avec succes");

        }else{
            Notification.affichageEchec("erreur","il y a eu une erreur au moment de la creation");

        }

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


    private JFXTreeTableColumn<Groupe,String> creerGroupeId(){
        JFXTreeTableColumn<Groupe,String> groupe_nom=new JFXTreeTableColumn<>("id");
        groupe_nom.setPrefWidth(110);
        groupe_nom.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Groupe, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Groupe, String> param) {
                return param.getValue().getValue().id;
            }
        });

        return groupe_nom;
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        groupeDao=new GroupeDaoImpl(DBconnexion.getConnection());
        remplirGroupe();

    }

    private void remplirGroupe() {

        JFXTreeTableColumn<Groupe,String> groupe_nom=this.creernomgroupe();
        JFXTreeTableColumn<Groupe,String> groupe_id=this.creerGroupeId();

        ObservableList<Groupe> groupes = FXCollections.observableArrayList();
        List<Groupe> liste=groupeDao.listeGroupes();
        for(Groupe groupe:liste){
            groupes.add(groupe);
        }
        final TreeItem<Groupe> root = new RecursiveTreeItem<Groupe>(groupes, RecursiveTreeObject::getChildren);
        this.tablegroupe.getColumns().setAll(groupe_id,groupe_nom);
        tablegroupe.setRoot(root);
        tablegroupe.setShowRoot(false);

        optimiserRecherClient();
        tablegroupe.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                showDetailsClient(newValue));


    }



    private void showDetailsClient(TreeItem<Groupe> newValue) {
        if(newValue!=null) {
            this.groupelabel.setText(newValue.getValue().nom_groupe.get());
            this.id_Groupe=newValue.getValue().id.get();
        }
    }
    private void optimiserRecherClient() {
        this.chercher.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                tablegroupe.setPredicate(new Predicate<TreeItem<Groupe>>() {

                    @Override
                    public boolean test(TreeItem<Groupe> t) {
                        boolean flag=t.getValue().nom_groupe.getValue().toLowerCase().contains(newValue.toLowerCase())
                              ;


                        return flag ;


                    }
                });
            }

        });
    }



}
