package controlleurvue.groupe;

import basededonnee.DBconnexion;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controlleurvue.Vue;
import daos.AssociationGroupeSejourDao;
import daos.GroupeDao;
import daos.SejourDao;
import daos.impl.AssociationGroupeSejourDaoImpl;
import daos.impl.GroupeDaoImpl;
import daos.impl.SejourDaoImpl;
import enumerations.Paiement;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.Pair;
import modele.*;
import notification.Notification;
import principale.Controlleur;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class consulterAssociationGroupeSejour implements Initializable, Vue {

    public JFXTreeTableView<Associationgroupesejour> association;
    public JFXTextField chercherAssoc;
    public Label nomgroupe;
    public Label typesejour;
    public Label date;
    public Label duree;
    public Label age;
    public Label nombre_place;
    public Label prix_unitaire;
    public Label resteapayer;
    public Label idgroupe;
    public Label idsejour;
    public Label idassoc;
    public StackPane stackepane;

    private  Controlleur controlleur;
    @Override
    public void setController(Controlleur controller) {
        this.controlleur=controller;

    }

    private JFXTreeTableColumn<Associationgroupesejour,String> creerGroupeId(){
        JFXTreeTableColumn<Associationgroupesejour,String> groupe_id=new JFXTreeTableColumn<>(" Id");
        groupe_id.setPrefWidth(100);
        groupe_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Associationgroupesejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Associationgroupesejour, String> param) {
                return param.getValue().getValue().id;
            }
        });
        return groupe_id;
    }

    private JFXTreeTableColumn<Associationgroupesejour,String> creerAssocPrixUnitaire(){
        JFXTreeTableColumn<Associationgroupesejour,String> groupe_id=new JFXTreeTableColumn<>(" prix");
        groupe_id.setPrefWidth(100);
        groupe_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Associationgroupesejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Associationgroupesejour, String> param) {
                return param.getValue().getValue().prix_unitaire;
            }
        });
        return groupe_id;
    }

    private JFXTreeTableColumn<Associationgroupesejour,String> creerAssocSejour(){
        JFXTreeTableColumn<Associationgroupesejour,String> groupe_id=new JFXTreeTableColumn<>("sejour");
        groupe_id.setPrefWidth(100);
        groupe_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Associationgroupesejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Associationgroupesejour, String> param) {
                return param.getValue().getValue().sejour;
            }
        });
        return groupe_id;
    }

    private JFXTreeTableColumn<Associationgroupesejour,String> creerAssocNbPlace(){
        JFXTreeTableColumn<Associationgroupesejour,String> groupe_id=new JFXTreeTableColumn<>("nb place");
        groupe_id.setPrefWidth(100);
        groupe_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Associationgroupesejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Associationgroupesejour, String> param) {
                return param.getValue().getValue().nbPlace;
            }
        });
        return groupe_id;
    }

    private JFXTreeTableColumn<Associationgroupesejour,String> creerAssocGroupe(){
        JFXTreeTableColumn<Associationgroupesejour,String> groupe_id=new JFXTreeTableColumn<>("groupe");
        groupe_id.setPrefWidth(100);
        groupe_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Associationgroupesejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Associationgroupesejour, String> param) {
                return param.getValue().getValue().groupe;
            }
        });
        return groupe_id;
    }





    public void loadallassociation() {


        JFXTreeTableColumn<Associationgroupesejour, String> assoc_id = this.creerGroupeId();
        JFXTreeTableColumn<Associationgroupesejour, String> assoc_nbplace = this.creerAssocNbPlace();
        JFXTreeTableColumn<Associationgroupesejour, String> assoc_sejour = this.creerAssocSejour();
        JFXTreeTableColumn<Associationgroupesejour, String> assoc_groupe = this.creerAssocGroupe();
        JFXTreeTableColumn<Associationgroupesejour, String> assoc_prixunitaire = this.creerAssocPrixUnitaire();
        ObservableList<Associationgroupesejour> groupes = FXCollections.observableArrayList();
        List<Associationgroupesejour> liste = this.associationGroupeSejourDao.getListes();
        for (Associationgroupesejour associationgroupesejour : liste) {
            Groupe groupe = groupeDao.getGroupeParId(associationgroupesejour.groupe.get());
            Sejour sejour = sejourDao.getSejourParId(associationgroupesejour.sejour.get());

            if (associationgroupesejour.id == null) {
                Notification.affichageEchec("null", "variables a null");
            }
            groupes.add(new Associationgroupesejour(associationgroupesejour.id.get(), associationgroupesejour.prix_unitaire.get(),
                    groupe.nom_groupe.get(), sejour.type.get(), associationgroupesejour.nbPlace.get()));
        }
        final TreeItem<Associationgroupesejour> root = new RecursiveTreeItem<Associationgroupesejour>(groupes, RecursiveTreeObject::getChildren);
        this.association.getColumns().setAll(assoc_id, assoc_nbplace, assoc_sejour, assoc_groupe, assoc_prixunitaire);
        association.setRoot(root);
        association.setShowRoot(false);


        optimiserRechercheSejour();
        this.association.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                showDetailsSejour(newValue)
        );

    }





        private void showDetailsSejour(TreeItem< Associationgroupesejour > newValue) {
            if(newValue!=null) {
                //this.l
                Associationgroupesejour associationgroupesejour=associationGroupeSejourDao.getById(newValue.getValue().id.get());
                Sejour sejour=sejourDao.getSejourParId(associationgroupesejour.sejour.get());
                this.age.setText(sejour.ageMin.get()+" "+sejour.ageMax.get());
                this.duree.setText(sejour.duree.get());
                this.date.setText(sejour.date_debut.get()+" "+sejour.date_fin.get());
                this.typesejour.setText(sejour.type.get());
                this.idsejour.setText(associationgroupesejour.sejour.get());
                System.out.println("groupe id "+newValue.getValue().groupe.get());
                this.idassoc.setText(newValue.getValue().id.get());

                this.prix_unitaire.setText(newValue.getValue().prix_unitaire.get());
                Groupe groupe=groupeDao.getGroupeParId(associationgroupesejour.groupe.get());
                this.idgroupe.setText(groupe.id.get());
                this.nomgroupe.setText(newValue.getValue().groupe.get());
                this.prix_unitaire.setText(newValue.getValue().prix_unitaire.get());
                this.nombre_place.setText(newValue.getValue().nbPlace.get());

            }
        }

        private void optimiserRechercheSejour() {
            this.chercherAssoc.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                    association.setPredicate(new Predicate<TreeItem<Associationgroupesejour>>() {

                        @Override
                        public boolean test(TreeItem<Associationgroupesejour> t) {

                            boolean flag =
                                    t.getValue().groupe.getValue().toLowerCase().contains(newValue.toLowerCase())
                                            || t.getValue().sejour.getValue().toLowerCase().contains(newValue.toLowerCase())
                                            || t.getValue().nbPlace.getValue().toLowerCase().contains(newValue.toLowerCase())
                                            || t.getValue().id.getValue().toLowerCase().equals(newValue.toLowerCase())
                            ||t.getValue().prix_unitaire.getValue().toLowerCase().equals(newValue.toLowerCase());


                            return flag;


                        }
                    });
                }

            });
        }







    @Override
    public void initialize(URL location, ResourceBundle resources) {

        associationGroupeSejourDao=new AssociationGroupeSejourDaoImpl(DBconnexion.getConnection());
        groupeDao=new GroupeDaoImpl(DBconnexion.getConnection());
        sejourDao=new SejourDaoImpl(DBconnexion.getConnection());
        loadallassociation();
    }

    private GroupeDao groupeDao;
    private SejourDao sejourDao;
    private AssociationGroupeSejourDao associationGroupeSejourDao;

    public void retour(MouseEvent mouseEvent) {
        this.controlleur.lancerPageGroupe();
    }

    public void close(MouseEvent mouseEvent) {
    }

    public void validerinscription(MouseEvent mouseEvent) {
    }

    public void ajoutenfant(MouseEvent mouseEvent) {

        this.controlleur.ajouterEnfantMairie(this.nomgroupe.getText(),this.idsejour.getText(),this.idassoc.getText());
    }

    public void paiement(MouseEvent mouseEvent) {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("paiement");

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 10, 10, 10));

        Label label=new Label("somme paye");
        Label label2=new Label("methode");

        TextField from = new TextField();
        from.setPromptText("From");
        TextField to = new TextField();
        to.setPromptText("To");

        ComboBox comboBox=new ComboBox();
        for(Paiement paiement:Paiement.values()){
            comboBox.getItems().add(paiement);
        }
/*        gridPane.add(from, 0, 0);
        gridPane.add(new Label("To:"), 1, 0);
        gridPane.add(to, 2, 0);
        gridPane*/
gridPane.add(label,0,0);
gridPane.add(from,1,0);
gridPane.add(comboBox,1,3);
        gridPane.add(label2,0,3);


        dialog.getDialogPane().setContent(gridPane);

        // Request focus on the username field by default.
        Platform.runLater(() -> from.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(from.getText(), comboBox.getValue().toString());
            }
            return null;
        });



        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(pair -> {
            System.out.println("From=" + pair.getKey() + ", To=" + pair.getValue());
        });




    }

    private void annulerReservation(MouseEvent mouseEvent, JFXDialog dialog) {
    }
}
