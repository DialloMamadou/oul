package controlleurvue.sejour;

import basededonnee.DBconnexion;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controlleurvue.Vue;
import controlleurvue.inscription.CreerInscriptionSejour;
import daos.*;
import daos.impl.*;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Callback;
import modele.Centre;
import modele.Client;
import modele.Inscription;
import modele.Sejour;
import notification.Notification;
import principale.Controlleur;

import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
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
    public JFXTreeTableView<Client> listeClientSejour;
    public Label idsejour;
    public Label prenomnom;
    public Label idclient;
    public Label dateclient;
    public Label numeroclient;
    public Label emailclient;
    public Label reste;
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


    private CentreDao centreDao;
    private GroupeDao groupeDao;


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
    private InscriptionDao inscriptionDao;
    private ClientDao clientDao;
    public void initialize(URL location, ResourceBundle resources) {
        sejourDao=new SejourDaoImpl(DBconnexion.getConnection());
        inscriptionDao=new InscriptionDaoImpl(DBconnexion.getConnection());
        clientDao=new ClientDaoImpl(DBconnexion.getConnection());
        groupeDao=new GroupeDaoImpl(DBconnexion.getConnection());
        centreDao=new CentreDaoImpl(DBconnexion.getConnection());
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
        this.ldate.setText(newValue.getValue().date_debut.get()+" au "+newValue.getValue().date_fin.get());
        this.idsejour.setText(newValue.getValue().id.get());
        RemplirClientSejour(newValue.getValue());
    }




    public JFXTreeTableColumn<Client,String> genererClientNom(){
        JFXTreeTableColumn<Client,String> nom_client=new JFXTreeTableColumn<>(" nom");
        nom_client.setPrefWidth(30);
        nom_client.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
                return param.getValue().getValue().nom_client;
            }
        });

        return nom_client;
    }



    public JFXTreeTableColumn<Client,String> genererIdClient(){
        JFXTreeTableColumn<Client,String> nom_client=new JFXTreeTableColumn<>(" id");
        nom_client.setPrefWidth(30);
        nom_client.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
                return param.getValue().getValue().id;
            }
        });

        return nom_client;
    }



    private void RemplirClientSejour(Sejour value) {
        List<Inscription>listeInscription=this.inscriptionDao.getInscriptionsParIdSejour(value.id.get());
        List<Client>listeClient=new ArrayList<>();
        for(Inscription inscription:listeInscription){
            Client client=clientDao.getClientParId(inscription.code_client.get());
            listeClient.add(client);
        }


        JFXTreeTableColumn<Client,String> idclient=this.genererIdClient();
        JFXTreeTableColumn<Client,String> nomclient =this.genererClientNom();
        ObservableList<Client>clients=FXCollections.observableArrayList();
        for(Client client:listeClient){
            clients.add(client);
        }


        final TreeItem<Client> root = new RecursiveTreeItem<Client>(clients, RecursiveTreeObject::getChildren);

        listeClientSejour.getColumns().setAll(idclient, nomclient);

        listeClientSejour.setRoot(root);
        listeClientSejour.setShowRoot(false);





        this.listeClientSejour.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                showDetailsClient(newValue)
        );
      //  omptimiserRechercheClient();



    }

    private void showDetailsClient(TreeItem<Client> newValue) {

        System.out.println("client "+newValue);
        Client client=clientDao.getClientParId(newValue.getValue().id.get());


        this.idclient.setText(client.id.get());
        this.dateclient.setText(client.datenaissance.get());
        this.prenomnom.setText(client.prenom_client.get()+" "+client.nom_client.get());
        this.emailclient.setText(client.email.get());
        this.numeroclient.setText(client.numero.get());

        Inscription inscription=inscriptionDao.getInscriptionsParIdSejourEtIdClient(this.idsejour.getText(),client.id.get());
       int reste=Integer.parseInt(this.lprix.getText())-Integer.parseInt(inscription.paiement.get());

       this.reste.setText(String.valueOf(reste));

        if(reste>0){
            this.reste.setTextFill(Color.web("#ff0000"));
        }else if(reste==0){
            this.reste.setTextFill(Color.web("#00ff00"));

        }

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
        Sejour sejour=sejourDao.getSejourParId(this.idsejour.getText());
        List<Inscription>inscriptions=inscriptionDao.getInscriptionsParIdSejour(sejour.id.get());
        List<Client>clients=new ArrayList<>();
        for(Inscription inscription:inscriptions){
            Client client=clientDao.getClientParId(inscription.code_client.get());
            clients.add(client);
        }


        Centre centre=centreDao.getCentreParId(sejour.nom_centre.get());

        System.out.println("centre ");
        System.out.println(centre.nom_centre.get()+" avec capacite "+centre.capacite_centre.get());
        System.out.println("********************************");
        System.out.println(" sejour");
        System.out.println(sejour.type.get()+" age "+sejour.ageMin.get()+" "+sejour.ageMax.get());
        System.out.println("*************************************");
        for(Client client:clients){
            System.out.println(client.prenom_client.get()+" "+client.nom_client.get());
        }

        Document doc = new Document();

        try {
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("src/main/resources/docs/DocCentre.pdf"));
            doc.open();

            //Add Image
            Image img = Image.getInstance("src/main/resources/img/oul.jpg");
            //Fixed Positioning
            img.setAbsolutePosition(30f, 700f);
            //Scale to new height and new width of image
            img.scaleAbsolute(100, 100);
            //Add to document
            doc.add(img);
            //doc.add(Chunk.SPACETABBING);
            doc.add(new Paragraph("\n\n\n\n\n"));
            Font font =FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
            font.setColor(BaseColor.BLUE);

            doc.add(new Phrase("ŒUVRE UNIVERSITAIRE DU LOIRET\n",font));
            doc.add(new Phrase("2  rue des Deux Ponts \nCS 30724 \n45017 ORLEANS CEDEX 1 \nTél : 02.38.53.38.61"+
                    "\n"+"siege.asso@ouloiret.fr\n www.ouloiret.fr \nSIRET : 77550821100072 \nAPE : 552 E"));

            //doc.add(new Paragraph("\n\n\n\n\n\n"));

            Paragraph pCentre = new Paragraph("CENTRE"+"    " +centre.nom_centre.get()+" avec capacite "+centre.capacite_centre.get());
            Paragraph pdateSejour = new Paragraph("DATES DU SEJOUR"+"     "+ldate.getText());
            pCentre.setAlignment(Element.ALIGN_CENTER);
            pdateSejour.setAlignment(Element.ALIGN_CENTER);

            doc.add(pCentre);
            doc.add(pdateSejour);


            Paragraph titre =new Paragraph(" \nListe des enfants inscritent à ce sejour:\n");
            titre.setAlignment(Element.ALIGN_CENTER);
            doc.add(titre);

            doc.add(new Paragraph(" "));


            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);

            PdfPCell cell;

            cell = new PdfPCell(new Phrase("id", FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("nom", FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("prenom", FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("groupe", FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);


            /////////////////////////////////////////////////////////////////////////////////////////////////////


            for (Client client : clients){
                cell = new PdfPCell(new Phrase(client.id.get(), FontFactory.getFont("Arial", 12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(client.nom_client.get(), FontFactory.getFont("Arial", 12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(client.prenom_client.get(), FontFactory.getFont("Arial", 12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(client.groupe.get(), FontFactory.getFont("Arial", 12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            doc.add(table);

            doc.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
