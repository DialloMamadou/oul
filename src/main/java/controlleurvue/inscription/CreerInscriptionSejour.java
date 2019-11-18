package controlleurvue.inscription;

import basededonnee.DBconnexion;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mysql.jdbc.PreparedStatement;
import controlleurvue.Vue;
import controlleurvue.centre.ConsulterCentre;
import controlleurvue.sejour.CreerSejour;
import daos.*;
import daos.impl.*;
import dto.CentreDto;
import dto.ClientDto;
import enumerations.Depart;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.Duration;
import modele.*;
import notification.Notification;
import org.controlsfx.control.Notifications;
import principale.Controlleur;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
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
    public Label datenaissance;
    public Label id;
    public Label iduser;

    private Controlleur controlleur;


    private void chargementClients(){


    }


    public JFXTreeTableColumn<ClientDto,String> genererNom(){
        JFXTreeTableColumn<ClientDto,String> nom=new JFXTreeTableColumn<>("nom");
        nom.setPrefWidth(90);
        nom.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ClientDto, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ClientDto, String> param) {
                return param.getValue().getValue().nom_client;
            }
        });
        return nom;
    }



    public JFXTreeTableColumn<ClientDto,String> genererPrenom(){
        JFXTreeTableColumn<ClientDto,String> prenom=new JFXTreeTableColumn<>("prenom");
        prenom.setPrefWidth(90);
        prenom.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ClientDto, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ClientDto, String> param) {
                return param.getValue().getValue().prenom_client;
            }
        });
        return prenom;
    }






    public JFXTreeTableColumn<ClientDto,String> genererDateDenaissance(){
        JFXTreeTableColumn<ClientDto,String> datenaissance=new JFXTreeTableColumn<>("date de naissance");
        datenaissance.setPrefWidth(90);
        datenaissance.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ClientDto, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ClientDto, String> param) {
                return param.getValue().getValue().datenaissance;
            }
        });
        return datenaissance;
    }




    public JFXTreeTableColumn<ClientDto,String> genererId(){
        JFXTreeTableColumn<ClientDto,String> id=new JFXTreeTableColumn<>("id");
        id.setPrefWidth(90);
        id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ClientDto, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ClientDto, String> param) {
                return param.getValue().getValue().id_client;
            }
        });
        return id;
    }




    public JFXTreeTableColumn<ClientDto,String> genererGroupe(){
        JFXTreeTableColumn<ClientDto,String> groupe=new JFXTreeTableColumn<>(" groupe");
        groupe.setPrefWidth(90);
        groupe.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ClientDto, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ClientDto, String> param) {
                return param.getValue().getValue().groupe;
            }
        });


        return groupe;
    }



    @Override
    public void setController(Controlleur controller) {
        this.controlleur=controller;
        JFXTreeTableColumn<ClientDto,String>id=this.genererId();
        JFXTreeTableColumn<ClientDto,String> nom=this.genererNom();
        JFXTreeTableColumn<ClientDto,String> prenom=this.genererPrenom();
        JFXTreeTableColumn<ClientDto,String> datenaissance=this.genererDateDenaissance();
        JFXTreeTableColumn<ClientDto,String> groupe=this.genererGroupe();
        ObservableList<ClientDto> clients = FXCollections.observableArrayList();
        Connection connection= DBconnexion.getConnection();
        List<Client>liste= clientDao.listeClient();
        for(Client client:liste){
            System.out.println("nommm "+client.prenom_client.get());
            System.out.println("iddd "+client.id.get());
            clients.add(new ClientDto(client.id.get(),client.prenom_client.get(),client.nom_client.get(),
                    client.groupe.get(),client.datenaissance.get()));
        }

        final TreeItem<ClientDto> root = new RecursiveTreeItem<ClientDto>(clients, RecursiveTreeObject::getChildren);
        this.clients.getColumns().setAll(id,nom,prenom,groupe,datenaissance);
        this.clients.setRoot(root);
        this.clients.setShowRoot(false);

        //qd utilisateur tape dans barre recherche element de l arbre se selectionne
        optimiserRechercher();

        //qd on clique sur element arbre les donnes sont automatiquements remplis
        this.clients.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                showDetails(newValue)
        );
        chargementCentre();

    }

    private void optimiserRechercher() {
        this.chercheClient.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                CreerInscriptionSejour.this.clients.setPredicate(new Predicate<TreeItem<ClientDto>>() {

                    @Override
                    public boolean test(TreeItem<ClientDto> t) {

                        boolean flag =
                                t.getValue().nom_client.getValue().contains(newValue)
                                        || t.getValue().prenom_client.getValue().contains(newValue)
                                        || t.getValue().groupe.getValue().contains(newValue)
                                        || t.getValue().datenaissance.getValue().equals(newValue)
                                ||t.getValue().id_client.getValue().equals(newValue);
                        ;
                        if(flag)
                            System.out.println("trouve");

                        return flag;


                    }
                });
            }

        });
    }


    public JFXTreeTableColumn<CentreDto,String> genererCentre(){
        JFXTreeTableColumn<CentreDto,String> centre=new JFXTreeTableColumn<>(" centre");
        centre.setPrefWidth(90);
        centre.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CentreDto, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<CentreDto, String> param) {
                return param.getValue().getValue().nom_centre;
            }
        });

        return centre;
    }









    private void chargementCentre() {

        JFXTreeTableColumn<CentreDto,String> type=this.genererCentre();
        ObservableList<CentreDto> centres = FXCollections.observableArrayList();
        List<Centre>liste=centreDao.listeCentres();
        for(Centre centre:liste){
            centres.add(new CentreDto(centre.nom_centre.get()));
        }
        final TreeItem<CentreDto> root = new RecursiveTreeItem<CentreDto>(centres, RecursiveTreeObject::getChildren);
        vueCentre.getColumns().setAll(type);
        vueCentre.setRoot(root);
        vueCentre.setShowRoot(false);
        optimiserRechercheCentre();

        vueCentre.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                showCentre(newValue)
        );



        this.type.valueProperty().addListener((obs, oldItem, newItem) -> {
            remplirtemp((String)newItem);
            });

        this.duree.valueProperty().addListener((obs, oldItem, newItem) -> {
            remplirDate((String)newItem);
        });

        this.duree.valueProperty().addListener((obs, oldItem, newItem) -> {
            remplirDepart();
        });

        this.depart.valueProperty().addListener((obs, oldItem, newItem) -> {
            remplirPrix();
        });


    }

    private void optimiserRechercheCentre() {
        this.chercheCentre.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                vueCentre.setPredicate(new Predicate<TreeItem<CentreDto>>() {

                    @Override
                    public boolean test(TreeItem<CentreDto> t) {

                        boolean flag = t.getValue().nom_centre.getValue().contains(newValue);
                        if(flag){
                            System.out.println("combox");
                            remplirCombo(t.getValue().nom_centre);
                        }
                        ;

                        return flag;


                    }
                });
            }

        });

    }





    private void remplirPrix() {
        String date=(String)this.date.getValue();
        String[] args = date.split(" au ");

        List<Sejour>liste=sejourDao.getSejourParTypeEtDate((String)this.type.getValue(),args[0],args[1]);
        /*String sql="SELECT * FROM sejour where type_sejour ='"+this.type.getValue()+"' AND date_debut='"+args[0]+"' AND date_fin='"+
                args[1]+"'";

        System.out.println("requete sql "+sql);


        Connection connection= DBconnexion.getConnection();
        try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println("right here right now");
            this.prix.setText(String.valueOf(rs.getInt(7)));

            }
        }catch (Exception e){

        }
        System.out.println("right here right now");
*/

        for(Sejour sejour:liste){
            this.prix.setText(sejour.prix.get());
        }

    }

    private void remplirDepart() {
        this.depart.getItems().clear();
        for(Depart depart:Depart.values()){
            this.depart.getItems().add(depart);
        }
    }

    private void remplirDate(String newItem) {
        this.date.getItems().clear();

        List<Sejour>liste=sejourDao.getSejourParTypeEtDuree(type.getValue(),newItem);
       // String sql="SELECT * FROM sejour where type_sejour ='"+this.type.getValue()+"' AND duree ='"+newItem+"'";
/*
        System.out.println("sql "+sql);
        Connection connection= DBconnexion.getConnection();
        try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {


                this.date.getItems().add(rs.getString(3)+" au "+rs.getString(4));
            }
        }catch (Exception e){

        }*/
for(Sejour sejour:liste){
    this.date.getItems().add(sejour.date_debut.get()+" au "+sejour.date_fin.get());
}

    }


    public void remplirtemp(String nom){

        this.duree.getItems().clear();
        this.date.getItems().clear();
        this.prix.setText("");
        List<Sejour> sejour=this.sejourDao.getSejourParType(nom);
        System.out.println("taille liste"+sejour.size());
        List<String>listeS=new ArrayList<>();

        for(Sejour sejour1:sejour){
            System.out.println("une deux trois");
            listeS.add(sejour1.duree.get());
        }
        Set<String> set = new LinkedHashSet<String>();
        set.addAll(listeS);
        listeS.clear();
        listeS.addAll(set);
        for(String dures:listeS){
            this.duree.getItems().add(dures);
        }
    }



    private void remplirCombo(StringProperty value) {
        this.type.getItems().clear();
        this.date.getItems().clear();
        this.prix.setText("");
        this.duree.getItems().clear();
        Centre centre=centreDao.trouverParNomCentre(value.get());
        System.out.println("centre ici "+centre.nom_centre);
        List<Sejour> sejour=sejourDao.getSejourParCentre(centre.id.get());
        System.out.println("liste sejour "+sejour.size());
        List<String> listeSejour=new ArrayList<>();

        for(Sejour sejour1:sejour){
            listeSejour.add(sejour1.type.get());
        }
      /* String sql = "SELECT * FROM centre WHERE nom_centre ='" + value + "'";

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





        String sql2 = "SELECT * FROM sejour WHERE centre_id ='" + id + "'";

        List<String> listeSejour=new ArrayList<>();

        try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql2);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                listeSejour.add(rs.getString(5));
            }
        }catch (Exception e){

        }



        System.out.println(listeSejour.get(0));
        System.out.println("combox box remplit");




*/
        Set<String> set = new LinkedHashSet<String>();

        // Add the elements to set
        set.addAll(listeSejour);

        // Clear the list
        listeSejour.clear();

        // add the elements of set
        // with no duplicates to the list
        listeSejour.addAll(set);








        for(String chaine:listeSejour){

            this.type.getItems().add(chaine);

        }
    }

    private void showCentre(TreeItem<CentreDto> newValue) {
        centre.setText(newValue.getValue().nom_centre.getValue());
        remplirCombo(newValue.getValue().nom_centre);
    }

    public void showDetails(TreeItem<ClientDto> pModel) {

        nom.setText(pModel.getValue().nom_client.getValue());
        prenom.setText(pModel.getValue().prenom_client.getValue());
        groupe.setText(pModel.getValue().groupe.getValue());
        datenaissance.setText(pModel.getValue().datenaissance.getValue());
        iduser.setText(pModel.getValue().id_client.getValue());

    }

    public void back(MouseEvent mouseEvent) {
        this.controlleur.lancerPageInscription();
    }

    public void close(MouseEvent mouseEvent) {
    }



    private ClientDao clientDao;
    private GroupeDao groupeDao;
    private InscriptionDao inscriptionDao;
    private CentreDao centreDao;
    private SejourDao sejourDao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clientDao=new ClientDaoImpl(DBconnexion.getConnection());
        groupeDao=new GroupeDaoImpl(DBconnexion.getConnection());
        inscriptionDao=new InscriptionDaoImpl(DBconnexion.getConnection());
        centreDao=new CentreDaoImpl(DBconnexion.getConnection());
        sejourDao=new SejourDaoImpl(DBconnexion.getConnection());

    }


    public void retour(MouseEvent mouseEvent) {
        this.controlleur.lancerPageInscription();
    }

    public void validerinscription(MouseEvent mouseEvent) {

        String montant=this.accompte.getText();
        System.out.println("accompte :"+montant);

        int x=Integer.parseInt(montant);

        if(x<=0){


            JFXDialogLayout dialogLayout=new JFXDialogLayout();
            dialogLayout.setHeading(new Text("ferme"));
            dialogLayout.setBody(new Text("vous voulez finaliser cette reservation  ?"));

            JFXButton ok=new JFXButton("ok");
            JFXButton cancel=new JFXButton("annule");

            final JFXDialog dialog=new JFXDialog(stackepane,dialogLayout, JFXDialog.DialogTransition.CENTER);

            ok.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(javafx.event.ActionEvent event) {
                    enregistrerReservation();
                    dialog.close();

                }
            });
            cancel.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
                public void handle(javafx.event.ActionEvent event) {
                    dialog.close();
                }
            });
            dialogLayout.setActions(ok,cancel);
            dialog.show();

        }else{

        }


    }


    public void enregistrerReservation(){

        String date=(String)this.date.getValue();
        String[] args = date.split(" au ");

        String sql="SELECT * FROM sejour where type_sejour ='"+this.type.getValue()+"' AND duree='"+this.duree.getValue()+
                "' AND date_debut='"+args[0]+"' AND date_fin ='"+args[1]+"'";



        Sejour sejour=sejourDao.getSejourPartypeetdureeetdate(this.type.getValue(),this.duree.getValue(),args[0],args[1]);
       /* int id_sejour=-1;
        int id_client=-1;


        try {
            PreparedStatement ps = (PreparedStatement) DBconnexion.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                id_sejour=rs.getInt(1);
                System.out.println("id "+rs.getString(1));

            }
        }catch (Exception e){

        }



*/


       /* String sql2="SELECT * FROM client WHERE  nom_client ='"+this.nom.getText()+"' AND prenom_client='"+this.prenom.getText()+
                "' AND datenaissance='"+this.datenaissance.getText()+"'";


        try {
            PreparedStatement ps = (PreparedStatement) DBconnexion.getConnection().prepareStatement(sql2);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                id_client=rs.getInt(1);
                System.out.println("id  depuis "+rs.getString(1));

            }
        }catch (Exception e){

        }
        System.out.println("ici:"+sql2);
*/
       Client client=clientDao.getClientParId(iduser.getText());
       System.out.println("client :"+client.prenom_client.get()+" "+client.nom_client.get());

        String aujourdhui = new SimpleDateFormat("dd-MM-yyyy").format(new Date());


        Inscription inscription=new Inscription();




/*
        String sql3="INSERT INTO inscription ( paiement, date_inscription, code_client, id_sejour, depart) VALUES (?,?,?,?,?)";



        Connection connection= DBconnexion.getConnection();
        int res=0;
        try {

            PreparedStatement ps=(PreparedStatement)connection.prepareStatement(sql3);
            ps.setString(1, this.accompte.getText().toString());
            ps.setString(2, aujourdhui);
            ps.setString(3, String.valueOf(id_client));
            ps.setString(4, String.valueOf(id_sejour));
            ps.setString(5,(String)this.depart.getValue().toString());


            res=ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CreerSejour.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(res>0){
            Notification.affichageSucces("succes","reservation faite avec succes");

        }else{
            Notification.affichageEchec("erreur","echec dans la creation de la reservation");

        }*/

    }
}
