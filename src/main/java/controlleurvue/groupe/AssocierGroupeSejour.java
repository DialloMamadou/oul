package controlleurvue.groupe;

import basededonnee.DBconnexion;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controlleurvue.Vue;
import controlleurvue.sejour.ConsulterSejour;
import daos.AssociationGroupeSejourDao;
import daos.CentreDao;
import daos.GroupeDao;
import daos.SejourDao;
import daos.impl.AssociationGroupeSejourImpl;
import daos.impl.CentreDaoImpl;
import daos.impl.GroupeDaoImpl;
import daos.impl.SejourDaoImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import modele.*;
import notification.Notification;
import principale.Controlleur;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class AssocierGroupeSejour implements Vue, Initializable {

    public JFXTreeTableView sejour;
    public JFXTextField chercherSejour;
    public JFXTextField chercherGroupe;
    public Label lduree;
    public Label lprenom;
    public Label type;
    public Label lgroupe;
    public Label centre;
    public Label prix;
    public Label id;
    public Label date;
    public Label lnomGroupe;
    public JFXTextField prixfixe;
    public JFXTreeTableView<Groupe> groupe;
    public JFXTreeTableView<Sejour> sejourarbre;
    public Label lage;
    public Label idgroupe;
    private Controlleur controlleur;
    
    
    private CentreDao centreDao;
    private GroupeDao groupeDao;
    private SejourDao sejourDao;
    private AssociationGroupeSejourDao associationGroupeSejourDao;
    
    
    @Override
    public void setController(Controlleur controller) {
        this.controlleur=controller;

    }



    public void retour(MouseEvent mouseEvent) {
        this.controlleur.lancerPageGroupe();
    }

    public void validerAssociation(MouseEvent mouseEvent) {
        if(this.prixfixe.getText()!="" && this.lduree.getText()!="" && this.lnomGroupe.getText()!=""){
            Associationgroupesejour associationgroupesejour=new Associationgroupesejour(this.prixfixe.getText(),this.idgroupe.getText(),this.id.getText());

            int res=associationGroupeSejourDao.inserrerAssociation(associationgroupesejour);
            if(res==0){
                Notification.affichageSucces("echec","L ajout de l association n a pu se faire ");

            }else{
                Notification.affichageSucces("succes insertion","l  association entre groupe sejour a ete fait");
            }
        }else{
            Notification.affichageEchec("donnees manquantes","veuillez saisir un prix , un sejour et un groupe");
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        groupeDao=new GroupeDaoImpl(DBconnexion.getConnection());
        sejourDao=new SejourDaoImpl(DBconnexion.getConnection());
        centreDao=new CentreDaoImpl(DBconnexion.getConnection());
        associationGroupeSejourDao=new AssociationGroupeSejourImpl(DBconnexion.getConnection());
        genererLesSejours();
        genererLesGroupes();
    }


    public JFXTreeTableColumn<Groupe,String> genererGroupeId(){

        JFXTreeTableColumn<Groupe,String> groupe_id=new JFXTreeTableColumn<>(" Id");
        groupe_id.setPrefWidth(100);
        groupe_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Groupe, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Groupe, String> param) {
                return param.getValue().getValue().id;
            }
        });
        return groupe_id;
    }

    public JFXTreeTableColumn<Groupe,String> genererGroupeNom(){

        JFXTreeTableColumn<Groupe,String> groupenom=new JFXTreeTableColumn<>("groupe nom");
        groupenom.setPrefWidth(100);
        groupenom.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Groupe, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Groupe, String> param) {
                return param.getValue().getValue().nom_groupe;
            }
        });
        return groupenom;
    }


    private void genererLesGroupes() {

        JFXTreeTableColumn<Groupe,String> inscription_id=this.genererGroupeId();
        JFXTreeTableColumn<Groupe,String> inscription_dateinscription=this.genererGroupeNom();

        ObservableList<Groupe> inscriptions = FXCollections.observableArrayList();
        List<Groupe> reservations=groupeDao.listeGroupes();
        for(Groupe groupe: reservations){


            inscriptions.add(groupe);
        }
        final TreeItem<Groupe> root = new RecursiveTreeItem<Groupe>(inscriptions, RecursiveTreeObject::getChildren);
        groupe.getColumns().setAll(inscription_id,inscription_dateinscription);
        groupe.setRoot(root);
        groupe.setShowRoot(false);
        optimiserRechercheGroupe();
        groupe.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                showDetailsGroupe(newValue)
        );
    }

    private void showDetailsGroupe(TreeItem<Groupe> newValue) {
        if(newValue!=null){
            this.lnomGroupe.setText(newValue.getValue().nom_groupe.get());
            this.idgroupe.setText(newValue.getValue().id.get());

        }
    }

    private void optimiserRechercheGroupe() {
        this.chercherGroupe.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                groupe.setPredicate(new Predicate<TreeItem<Groupe>>() {

                    @Override
                    public boolean test(TreeItem<Groupe> t) {

                        boolean flag =t.getValue().id.get().toLowerCase().contains(newValue.toLowerCase())||
                                t.getValue().nom_groupe.get().toLowerCase().contains(newValue.toLowerCase())
;
                        if(flag)
                            System.out.println("trouve" + t.getValue().id.get());

                        return flag;


                    }
                });
            }

        });
    }


    public JFXTreeTableColumn<Sejour,String> genererSejourId(){

        JFXTreeTableColumn<Sejour,String> sejour_id=new JFXTreeTableColumn<>(" Id");
        sejour_id.setPrefWidth(100);
        sejour_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().id;
            }
        });
        return sejour_id;
    }

    public JFXTreeTableColumn<Sejour,String> genererDateDebut(){

        JFXTreeTableColumn<Sejour,String> datedebutsejour=new JFXTreeTableColumn<>(" date debut");
        datedebutsejour.setPrefWidth(100);
        datedebutsejour.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().date_debut;
            }
        });
        return datedebutsejour;
    }


    public JFXTreeTableColumn<Sejour,String> genererDateFin(){

        JFXTreeTableColumn<Sejour,String> datefinsejour=new JFXTreeTableColumn<>(" date fin");
        datefinsejour.setPrefWidth(100);
        datefinsejour.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().date_fin;
            }
        });
        return datefinsejour;
    }


    public JFXTreeTableColumn<Sejour,String> genererTypeSejour(){

        JFXTreeTableColumn<Sejour,String> typesejour=new JFXTreeTableColumn<>(" type");
        typesejour.setPrefWidth(100);
        typesejour.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().type;
            }
        });
        return typesejour;
    }



    public JFXTreeTableColumn<Sejour,String> genererCentreSejour(){

        JFXTreeTableColumn<Sejour,String> typesejour=new JFXTreeTableColumn<>(" centre");
        typesejour.setPrefWidth(100);
        typesejour.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().nom_centre;
            }
        });
        return typesejour;
    }



    private void genererLesSejours() {
        JFXTreeTableColumn<Sejour,String> inscription_id=this.genererSejourId();
        JFXTreeTableColumn<Sejour,String> inscription_dateinscription=this.genererDateDebut();
        JFXTreeTableColumn<Sejour,String> inscription_client=this.genererDateFin();
        JFXTreeTableColumn<Sejour,String> inscription_sejour=this.genererTypeSejour();
        ObservableList<Sejour> inscriptions = FXCollections.observableArrayList();
        List<Sejour> reservations=sejourDao.listeSejour();
        for(Sejour Sejour: reservations){


            inscriptions.add(Sejour);
        }
        final TreeItem<Sejour> root = new RecursiveTreeItem<Sejour>(inscriptions, RecursiveTreeObject::getChildren);
        sejourarbre.getColumns().setAll(inscription_id,inscription_dateinscription,inscription_client,inscription_sejour);
        sejourarbre.setRoot(root);
        sejourarbre.setShowRoot(false);
        optimiserRechercheSejour();
        sejourarbre.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                showDetailsSejour(newValue)
        );
    }

    private void showDetailsSejour(TreeItem<Sejour> newValue) {

        if(newValue!=null) {
            this.id.setText(newValue.getValue().id.get());
            id.setStyle("-fx-font-weight: bold");
            Centre centre=centreDao.getCentreParId(this.id.getText());
            this.centre.setText(centre.nom_centre.get());

            this.date.setText(newValue.getValue().date_debut.get()+" "+newValue.getValue().date_fin.get());
            date.setStyle("-fx-font-weight: bold");

            this.lduree.setText(newValue.getValue().duree.get());
            lduree.setStyle("-fx-font-weight: bold");
            this.type.setText(newValue.getValue().type.get());
            type.setStyle("-fx-font-weight: bold");

            this.lage.setText(newValue.getValue().ageMin.get()+""+newValue.getValue().ageMax.get() );
            lage.setStyle("-fx-font-weight: bold");

            this.prix.setText(newValue.getValue().prix.get());
            prix.setStyle("-fx-font-weight: bold");


        }
    }

    private void optimiserRechercheSejour() {
        this.chercherSejour.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                sejourarbre.setPredicate(new Predicate<TreeItem<Sejour>>() {

                    @Override
                    public boolean test(TreeItem<Sejour> t) {

                        boolean flag =t.getValue().id.get().toLowerCase().contains(newValue.toLowerCase())||
                                t.getValue().type.get().toLowerCase().contains(newValue.toLowerCase())

                                ||t.getValue().date_fin.get().toLowerCase().contains(newValue.toLowerCase())
                                || t.getValue().date_debut.get().toLowerCase().contains(newValue.toLowerCase())
                                ;
                        if(flag)
                            System.out.println("trouve" + t.getValue().id.get());

                        return flag;


                    }
                });
            }

        });
    }
}
