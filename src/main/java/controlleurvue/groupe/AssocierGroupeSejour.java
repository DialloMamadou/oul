package controlleurvue.groupe;

import basededonnee.DBconnexion;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controlleurvue.Vue;
import daos.*;
import daos.impl.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import modele.*;
import notification.Notification;
import principale.Controlleur;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class AssocierGroupeSejour implements Vue, Initializable {


    public StackPane stackepane;
    public JFXTreeTableView sejour;
    public JFXTextField chercherSejour;
    public JFXTextField chercherGroupe;
    public Label lduree;
    public Label lprenom;
    public Label type;
    public Label lgroupe;
    public Label centre;
    public int capaciteCentre;

    public Label prix;
    public Label id;
    public Label date;
    public Label lnomGroupe;
    public JFXTextField prixfixe;
    public JFXTreeTableView<Groupe> groupe;
    public JFXTreeTableView<Sejour> sejourarbre;
    public Label lage;
    public Label idgroupe;
    public JFXTextField nbplace;
    private Controlleur controlleur;
    
    
    private CentreDao centreDao;
    private GroupeDao groupeDao;
    private SejourDao sejourDao;
    private AssociationGroupeSejourDao associationGroupeSejourDao;

    private InscriptionDao inscriptionDao;
    private ReservationDao reservationDao;
    
    @Override
    public void setController(Controlleur controller) {
        this.controlleur=controller;

    }



    public void retour(MouseEvent mouseEvent) {
        this.controlleur.lancerPageGroupe();
    }

    public void validerAssociation(MouseEvent mouseEvent) {

        System.out.println("##################Clic bouton Valider######################\n");

        if (!this.prixfixe.getText().isEmpty() && !this.lduree.getText().isEmpty() && !this.lnomGroupe.getText().isEmpty() && !this.nbplace.getText().isEmpty()) {

            int nbTotalResev_Insc =0;

            List<String> listeIdSejour = associationGroupeSejourDao.testCapaciteCentre(this.id.getText());

            for (String id:listeIdSejour){
                System.out.println("id sejour MCD :"+id);
                int nb0 = associationGroupeSejourDao.nbReservationGroupSejourForId(id);
                int nb1 = reservationDao.nbReservationForId(id);
                int nb2 = inscriptionDao.nbInscriptionForId(id);
                nbTotalResev_Insc+=nb1+nb2+nb0;
            }

            if (Integer.parseInt(this.nbplace.getText())+nbTotalResev_Insc > this.capaciteCentre) {
                int dif =this.capaciteCentre - nbTotalResev_Insc;
                System.out.println("nbPlace="+Integer.parseInt(this.nbplace.getText())+"; nbResev_Insc="+ nbTotalResev_Insc +"; CapCentre="+this.capaciteCentre);
                JFXDialogLayout dialogLayout = new JFXDialogLayout();
                dialogLayout.setHeading(new Text("Attention !"));
                if (dif < 0) {
                    dialogLayout.setBody(new Text("Vous souhaitez inscrire " + this.nbplace.getText() + " client(s) alors que vous avez dejà depassé la capacite total du centre ( " + this.capaciteCentre +
                            " places) de " +(-1)*dif+ " places \npour tous les sejours ne finissants pas avant la date du debut de ce sejour y compris ce sejour.\nVoulez vous quand meme valider ?"));
                }else {
                    dialogLayout.setBody(new Text("Vous souhaitez inscrire "+this.nbplace.getText()+" clients alors qu'il ne vous reste plus que "
                            +dif+" places sur la capacite du centre de "+this.capaciteCentre +" \n"+
                            "pour tous les sejours ne finissants pas avant la date du debut de ce sejour y compris ce sejour.\nVoulez vous quand meme valider ?"));
                }
                JFXButton ok = new JFXButton("oui");
                JFXButton cancel = new JFXButton("non");

                final JFXDialog dialog = new JFXDialog(stackepane, dialogLayout, JFXDialog.DialogTransition.CENTER);

                ok.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(javafx.event.ActionEvent event) {
                        creerAssociationGroupeSejour();

                        dialog.close();


                    }
                });
                cancel.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
                    public void handle(javafx.event.ActionEvent event) {
                        dialog.close();

                    }
                });
                dialogLayout.setActions(ok, cancel);
                dialog.show();
            }else {
                creerAssociationGroupeSejour();
            }

            }else{
            Notification.affichageEchec("Donnees manquantes","veuillez saisir le(s) champ(s) vide(s) ");
            }
        System.out.println("##################fin bouton Valider######################\n");

    }
    public void creerAssociationGroupeSejour() {

        Associationgroupesejour associationgroupesejour = new Associationgroupesejour(this.prixfixe.getText(), this.idgroupe.getText(), this.id.getText(), this.nbplace.getText());

        int res =0;
        res = associationGroupeSejourDao.inserrerAssociation(associationgroupesejour);
        if (res == 0) {
            Notification.affichageSucces("echec", "L ajout de l association n a pu se faire ");

        } else {
            Notification.affichageSucces("succes insertion", "l  association entre groupe sejour a ete fait");
            this.controlleur.lancerPageAssocierSejourGroupe();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        groupeDao=new GroupeDaoImpl(DBconnexion.getConnection());
        sejourDao=new SejourDaoImpl(DBconnexion.getConnection());
        centreDao=new CentreDaoImpl(DBconnexion.getConnection());
        associationGroupeSejourDao=new AssociationGroupeSejourDaoImpl(DBconnexion.getConnection());
        reservationDao=new ReservationDaoImpl(DBconnexion.getConnection());
        inscriptionDao= new InscriptionDaoImpl(DBconnexion.getConnection());
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



    public JFXTreeTableColumn<Groupe,String> genererCodeTiers(){

        JFXTreeTableColumn<Groupe,String> groupenom=new JFXTreeTableColumn<>("code tiers");
        groupenom.setPrefWidth(100);
        groupenom.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Groupe, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Groupe, String> param) {
                return param.getValue().getValue().code_tiers;
            }
        });
        return groupenom;
    }


    private void genererLesGroupes() {

        JFXTreeTableColumn<Groupe,String> groupe_id=this.genererGroupeId();
        JFXTreeTableColumn<Groupe,String> groupe_nom=this.genererGroupeNom();
        JFXTreeTableColumn<Groupe,String> groupeCodeTiers=this.genererCodeTiers();

        ObservableList<Groupe> inscriptions = FXCollections.observableArrayList();
        List<Groupe> reservations=groupeDao.listeGroupes();
        for(Groupe groupe: reservations){


            inscriptions.add(groupe);
        }
        final TreeItem<Groupe> root = new RecursiveTreeItem<Groupe>(inscriptions, RecursiveTreeObject::getChildren);
        groupe.getColumns().setAll(groupe_id,groupe_nom,groupeCodeTiers);
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
                                || t.getValue().code_tiers.get().toLowerCase().contains(newValue.toLowerCase())
;

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
            Sejour sejour =sejourDao.getSejourParId(this.id.getText());
            Centre centre=centreDao.getCentreParId(sejour.nom_centre.get());
            this.centre.setText(centre.nom_centre.get());
            this.capaciteCentre= Integer.parseInt(centre.capacite_centre.get());
            System.out.println("capacite_centre="+this.capaciteCentre);

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

    public void back(MouseEvent mouseEvent) {
        this.controlleur.lancerPageGroupe();
    }
}
