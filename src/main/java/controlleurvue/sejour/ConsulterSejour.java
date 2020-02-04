package controlleurvue.sejour;

import basededonnee.DBconnexion;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controlleurvue.Email;
import controlleurvue.Vue;
import controlleurvue.inscription.CreerInscriptionSejour;
import daos.*;
import daos.impl.*;
import gestiondocuments.*;
import dto.ClientDto;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import modele.Centre;
import modele.Client;
import modele.Inscription;
import modele.Sejour;
import notification.Notification;
import principale.Controlleur;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;

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
    public JFXTextField chercheclient;
    public JFXRadioButton tous;
    public JFXRadioButton regle;
    public JFXRadioButton retard;


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

    private GestionDocs gestionDocs;
    private Client client = null;


    public JFXTreeTableColumn<Sejour,String> genererSejourId(){
        JFXTreeTableColumn<Sejour,String> sejour_id=new JFXTreeTableColumn<>("Id");
        sejour_id.setPrefWidth(20);
        sejour_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().id;
            }
        });
        return  sejour_id;

    }



    public JFXTreeTableColumn<Sejour,String> genererSejourDuree(){
        JFXTreeTableColumn<Sejour,String> sejour_duree =new JFXTreeTableColumn<>("duree");
        sejour_duree.setPrefWidth(50);
        sejour_duree.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().duree;
            }
        });
        return sejour_duree;
    }




    public JFXTreeTableColumn<Sejour,String> genererDateDebut(){

        JFXTreeTableColumn<Sejour,String> sejour_datedebut=new JFXTreeTableColumn<>("debut");
        sejour_datedebut.setPrefWidth(80);
        sejour_datedebut.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().date_debut;
            }
        });
        return  sejour_datedebut;
    }



    public JFXTreeTableColumn<Sejour,String> genererDateFin(){

        JFXTreeTableColumn<Sejour,String> sejour_datefin=new JFXTreeTableColumn<>("fin");
        sejour_datefin.setPrefWidth(80);
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






        this.retard.setSelected(true);
        this.regle.setSelected(false);
        this.tous.setSelected(false);

        retard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                regle.setSelected(false);
                tous.setSelected(false);
              //  remplirListeClient();
                    if(idsejour.getText()!=""){
                        Sejour sejour=sejourDao.getSejourParId(idsejour.getText());
                        RemplirClientSejour(sejour);


                    }
            }
        });



        tous.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                retard.setSelected(false);
                regle.setSelected(false);
                if(idsejour.getText()!=""){
                    Sejour sejour=sejourDao.getSejourParId(idsejour.getText());
                    RemplirClientSejour(sejour);
                }




            }
        });


        regle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                retard.setSelected(false);
                tous.setSelected(false);
                if(idsejour.getText()!=""){
                    Sejour sejour=sejourDao.getSejourParId(idsejour.getText());
                    RemplirClientSejour(sejour);
                }

            }
        });
        chargerTousLesSejours();


    }

    private void remplirListeClient() {
    }

    private void changerValue(boolean b) {
        retard.setSelected(b);
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
        if(newValue!=null) {
            this.lage.setText(newValue.getValue().ageMin.get() + " - " + newValue.getValue().ageMax.get());
            this.lcapacite.setText(newValue.getValue().capacite.get());
            this.lcentre.setText(newValue.getValue().nom_centre.get());
            this.lprix.setText(newValue.getValue().prix.get());
            this.lsejour.setText(newValue.getValue().type.get());
            this.ldate.setText(newValue.getValue().date_debut.get() + " au " + newValue.getValue().date_fin.get());
            this.idsejour.setText(newValue.getValue().id.get());
            RemplirClientSejour(newValue.getValue());

        }
    }




    public JFXTreeTableColumn<Client,String> genererClientNom(){
        JFXTreeTableColumn<Client,String> nom_client=new JFXTreeTableColumn<>(" nom");
        nom_client.setPrefWidth(60);
        nom_client.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
                return param.getValue().getValue().nom_client;
            }
        });

        return nom_client;
    }


    public JFXTreeTableColumn<Client,String> genererPrenomClient(){
        JFXTreeTableColumn<Client,String> nom_client=new JFXTreeTableColumn<>(" prenom");
        nom_client.setPrefWidth(60);
        nom_client.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
                return param.getValue().getValue().prenom_client;
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



    public void RemplirClientSejour(Sejour value) {
        System.out.println("on remplit liste");
        List<Inscription>listeInscription=this.inscriptionDao.getInscriptionsParIdSejour(value.id.get());
        List<Client>listeClient=new ArrayList<>();
        for(Inscription inscription:listeInscription){
            if(tous.isSelected()){
                System.out.println("retard non selectex");
                Client client = clientDao.getClientParId(inscription.code_client.get());
                listeClient.add(client);
            }else{
                if(regle.isSelected()){
                    int payer=Integer.parseInt(inscription.paiement.get());
                    int prixTotal=Integer.parseInt(lprix.getText());


                    System.out.println("selected regle");
                    if(payer==prixTotal){
                        Client client=clientDao.getClientParId(inscription.code_client.get());
                        listeClient.add(client);

                    }

                }else{
                    int payer=Integer.parseInt(inscription.paiement.get());
                    int prixTotal=Integer.parseInt(lprix.getText());


                    System.out.println("selected retard");
                    if(payer<prixTotal){
                        Client client=clientDao.getClientParId(inscription.code_client.get());
                        listeClient.add(client);

                    }
                }
            }




            }



        JFXTreeTableColumn<Client,String> idclient=this.genererIdClient();
        JFXTreeTableColumn<Client,String> nomclient =this.genererClientNom();
        JFXTreeTableColumn<Client,String> prenomclient =this.genererPrenomClient();

        ObservableList<Client>clients=FXCollections.observableArrayList();
        for(Client client:listeClient){
            clients.add(client);
        }


        final TreeItem<Client> root = new RecursiveTreeItem<Client>(clients, RecursiveTreeObject::getChildren);

        listeClientSejour.getColumns().setAll(idclient, nomclient,prenomclient);

        listeClientSejour.setRoot(root);
        listeClientSejour.setShowRoot(false);





        this.listeClientSejour.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                showDetailsClient(newValue)
        );
          omptimiserRechercheClient();



    }
















    private void omptimiserRechercheClient() {

        this.chercheclient.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                ConsulterSejour.this.listeClientSejour.setPredicate(new Predicate<TreeItem<Client>>() {

                    @Override
                    public boolean test(TreeItem<Client> t) {

                        boolean flag =t.getValue().id.get().toLowerCase().contains(newValue.toLowerCase())||
                                t.getValue().prenom_client.get().toLowerCase().contains(newValue.toLowerCase()) ||
                                t.getValue().nom_client.get().toLowerCase().contains(newValue.toLowerCase());
                        if(flag)
                            System.out.println("trouve" + t.getValue().id.get());

                        return flag;


                    }
                });
            }

        });
    }

    private void showDetailsClient(TreeItem<Client> newValue) {

        if(newValue!=null) {
            System.out.println("client alscma " + newValue);
            client = clientDao.getClientParId(newValue.getValue().id.get());


            this.idclient.setText(client.id.get());
            this.dateclient.setText(client.datenaissance.get());
            this.prenomnom.setText(client.prenom_client.get() + " " + client.nom_client.get());
            this.emailclient.setText(client.email.get());
            this.numeroclient.setText(client.numero.get());

            Inscription inscription = inscriptionDao.getInscriptionsParIdSejourEtIdClient(this.idsejour.getText(), client.id.get());
            int reste = Integer.parseInt(this.lprix.getText()) - Integer.parseInt(inscription.paiement.get());

            this.reste.setText(String.valueOf(reste));

            if (reste > 0) {
                this.reste.setTextFill(Color.web("#ff0000"));
            } else if (reste == 0) {
                this.reste.setTextFill(Color.web("#00ff00"));

            }
        }

    }


    private void optimiserRecherche() {
        this.cherchersejour.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                ConsulterSejour.this.treeView.setPredicate(new Predicate<TreeItem<Sejour>>() {

                    @Override
                    public boolean test(TreeItem<Sejour> t) {

                        boolean flag =t.getValue().id.get().toLowerCase().contains(newValue.toLowerCase())||
                                t.getValue().duree.get().toLowerCase().contains(newValue.toLowerCase()) ||
                        t.getValue().type.get().toLowerCase().contains(newValue.toLowerCase())
                                ||t.getValue().prix.get().toLowerCase().contains(newValue.toLowerCase())

                                ||t.getValue().nom_centre.get().toLowerCase().contains(newValue.toLowerCase())
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

    public void genererattestation(MouseEvent mouseEvent){
        //gestionDocs.genereAttestationFacture(client,sejour);
        Sejour sejour=sejourDao.getSejourParId(this.idsejour.getText());

        if(client != null){
            Document doc = new Document();

            try {
                PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("src/main/resources/docs/Inscription_"+client.prenom_client.get()+".pdf"));
                doc.open();

                //Add Image
                Image img = Image.getInstance("src/main/resources/img/oul.jpg");
                //Fixed Positioning
                img.setAbsolutePosition(25f, 750f);
                //Scale to new height and new width of image
                img.scaleAbsolute(80, 80);
                //Add to document
                doc.add(img);
                //doc.add(Chunk.SPACETABBING);
                doc.add(new Paragraph("\n\n"));
                Font font = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
                font.setColor(BaseColor.BLUE);
                Font font1 =FontFactory.getFont(FontFactory.HELVETICA, 10);
                font1.setColor(BaseColor.BLUE);

                Font font0 =FontFactory.getFont(FontFactory.HELVETICA, 8);

                Font font2 =FontFactory.getFont(FontFactory.HELVETICA, 10);


                doc.add(new Phrase("ŒUVRE UNIVERSITAIRE DU LOIRET\n",font));
                doc.add(new Phrase("2  rue des Deux Ponts \nCS 30724 \n45017 ORLEANS CEDEX 1 \nTél : 02.38.53.38.61\n",font0));
                doc.add(new Phrase("siege.asso@ouloiret.fr\nwww.ouloiret.fr \n",font1));
                doc.add(new Phrase("SIRET : 77550821100072 \nAPE : 552 E",font0));

                Paragraph dest = new Paragraph("Mme ou M. "+client.observation.get()+"              \n"+client.codePostale.get()+" "+client.adresse.get()+"             ",font2);
                dest.setAlignment(Element.ALIGN_RIGHT);
                doc.add(dest);

                Paragraph p = new Paragraph("\n\n");

                doc.add(p);
                Paragraph titre = new Paragraph("ATTESTATION / FACTURE",font);
                titre.setAlignment(Element.ALIGN_CENTER);
                doc.add(titre);
                doc.add(p);


                String lettre = "Je soussigné, M. JOBERT, Directeur de l’Œuvre Universitaire du Loiret, certifie que :\n\n"+
                        "L’enfant : " + client.prenom_client.get()+" "+client.nom_client.get()+"\n\n"+
                         "Né(e) le : " + client.datenaissance.get()+"\n"+
                         "A participé à l'activité "+sejour.type.get()+" de l’Œuvre Universitaire du Loiret  à : "+sejour.nom_centre.get()+"  du  date.getValue()      soit :"+sejour.duree.get()+" jours.\n\n"+
                        "Le coût du séjour  pour la famille s’élève à : "+sejour.prix.get()+" €       EUROS.\n\n";

                        String numDec = "";
                        if(sejour.type.get() == "classe de découverte") {
                            numDec = "N°de Déclaration de l’Inspection Académique :" ;
                        }else{
                            numDec = "N° de Déclaration à la D.D.C.S du Loiret :";
                        }
                        lettre+=numDec;

                Paragraph par = new Paragraph(lettre);

                doc.add(par);
                doc.add(p);
                Paragraph st = new Paragraph(" FACTURE ACQUITTEE",font);
                st.setAlignment(Element.ALIGN_CENTER);
                doc.add(st);

                DateFormat fullDateFormat = DateFormat.getDateInstance(DateFormat.FULL);
                Paragraph dt = new Paragraph("Orléans, le "+fullDateFormat.format(new Date()),font2);

                dt.setAlignment(Element.ALIGN_CENTER);
                doc.add(dt);

                Paragraph dir = new Paragraph(" Le directeur: \nM. JOBERT",font);
                dir.setAlignment(Element.ALIGN_CENTER);
                doc.add(dir);


                /*Paragraph par = new Paragraph("       Madame, Monsieur,\n " +
                        "     Nous avons le plaisir de vous confirmer l’inscription de votre enfant " + client.prenom_client.get()+" "+client.nom_client.get()+"\n"+
                        "     •  au centre de vacances de CV "+sejour.nom_centre.get()+",\n     •  pour le séjour du "+date.getValue()+"\n     •  pour une durée de "+sejour.duree.get()+" jours.\n"+
                        "     Les frais de séjour et de voyage s’élèvent  à : "+sejour.prix.get()+" €, auxquels s’ajoute le montant de l’adhésion, soit 3,00 € par famille et par année.\n\n"+
                        "     Vous avez versé des arrhes pour un montant de  50 € ainsi que l’adhésion de 3 € (sauf si elle a déjà été réglée lors d’un précédent séjour). Le solde est à régler entre trois et quatre semaines avant le départ. Nous vous rappelons que notre association est habilitée pour accepter les paiements par Chèques-Vacances.\n\n"+
                        "     Deux à trois semaines avant le départ, une fois le séjour soldé, nous vous ferons parvenir les documents suivants :\n"+
                        "     •  la convocation avec les lieux et heures de départ et retour\n"+
                        "     •  le dossier du jeune ou de l’enfant à compléter\n"+
                        "     •  la lettre du directeur vous donnant des informations sur le déroulement du séjour.\n"+
                        "     Nous vous rappelons que pour toutes les activités nautiques (voile, kayak, canyoning, surf, plongée…) le test de natation est obligatoire (le brevet de 25 ou 50 m est non valable).\n"+
                        "     Attention : Pour la plongée un certificat médical est également demandé.\n"+
                        "     Si vous êtes en possession de Bons-Vacances CAF ou MSA et si vous ne nous les avez pas encore transmis, veuillez nous les adresser dans les meilleurs délais (ou votre numéro d’allocataire pour les VACAF).\n"+
                        "     En souhaitant que votre enfant passe un agréable séjour en Centre de Vacances, nous vous adressons nos respectueuses salutations.",font2);

                doc.add(par);
                Paragraph dir = new Paragraph(" Le directeur: \nM. JOBERT",font);
                dir.setAlignment(Element.ALIGN_CENTER);
                doc.add(dir);


                doc.add(new Phrase("-------------------------------------------------------------------------------------------------------------------- "));
                Paragraph p = new Paragraph(" PAPILLON à DETACHER ET à RETOURNER A L’O.U.L.AVEC VOTRE REGLEMENT\n (si séjour NON SOLDÉ ou non pris en charge)",font);
                p.setAlignment(Element.ALIGN_CENTER);
                doc.add(p);
                doc.add(new Phrase("NOM :",font2));
                doc.add(new Phrase(client.nom_client.get()+"\n",font));
                doc.add(new Phrase("REFERENCE :",font2));
                font.setSize(8);
                doc.add(new Phrase("159297 /  1112 - 8V0 /  CV "+sejour.nom_centre.get()+" du "+sejour.duree.get()+"\n",font));
                doc.add(new Phrase("     (votre dossier ne pourra être pris en compte sans cette référence)\n\n",font2));
                Image alerte1 = Image.getInstance("src/main/resources/img/alerte.jpg");

                alerte1.setAbsolutePosition(40f, 90f);

                alerte1.scaleAbsolute(10, 10);
                doc.add(alerte1);

                doc.add(new Paragraph("Le transport n’est pas compris pour les centres du Loiret, (INGRANNES, LES CAILLETTES, L’ETANG DU PUITS)\n"+
                        "vous devez conduire votre enfant sur place. Cependant un départ d’ORLEANS est possible (20 € ALLER/RETOUR),\n"+
                        "        le nombre de places étant limité, veuillez nous en informer par téléphone ou par courrier.",font));
                */doc.close();
                writer.close();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }else{
            Notification.affichageEchec("erreur", "Veuillez selectionner le client");
        }

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
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("src/main/resources/docs/"+sejour.nom_centre.get()+".pdf"));
            doc.open();

            //Add Image
            Image img = Image.getInstance("src/main/resources/img/oul.jpg");
            //Fixed Positioning
            img.setAbsolutePosition(25f, 750f);
            //Scale to new height and new width of image
            img.scaleAbsolute(80, 80);
            //Add to document
            doc.add(img);
            //doc.add(Chunk.SPACETABBING);
            doc.add(new Paragraph("\n\n"));
            Font font = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
            font.setColor(BaseColor.BLUE);
            Font font1 =FontFactory.getFont(FontFactory.HELVETICA, 10);
            font1.setColor(BaseColor.BLUE);

            Font font0 =FontFactory.getFont(FontFactory.HELVETICA, 8);

            Font font2 =FontFactory.getFont(FontFactory.HELVETICA, 10);


            doc.add(new Phrase("ŒUVRE UNIVERSITAIRE DU LOIRET\n",font));
            doc.add(new Phrase("2  rue des Deux Ponts \nCS 30724 \n45017 ORLEANS CEDEX 1 \nTél : 02.38.53.38.61\n",font0));
            doc.add(new Phrase("siege.asso@ouloiret.fr\nwww.ouloiret.fr \n",font1));
            doc.add(new Phrase("SIRET : 77550821100072 \nAPE : 552 E",font0));

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


            PdfPTable table = new PdfPTable(8);
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

            cell = new PdfPCell(new Phrase("date de nais", FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("groupe", FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("adresse", FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("code postale", FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("observation", FontFactory.getFont("Comic Sans MS", 12)));
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

                cell = new PdfPCell(new Phrase(client.datenaissance.get(), FontFactory.getFont("Arial", 12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(client.groupe.get(), FontFactory.getFont("Arial", 12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(client.adresse.get(), FontFactory.getFont("Arial", 12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(client.codePostale.get(), FontFactory.getFont("Arial", 12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(client.observation.get(), FontFactory.getFont("Arial", 12)));
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


    public void envoieEmail(MouseEvent mouseEvent) {
        Email.idclient="-1";
        Email.idSejour=this.idsejour.getText();
        Notification.affichageSucces("ici","ici");
        System.out.println("id sejour "+this.idsejour.getText());
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/vue/email.fxml"));
            /*
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);

            FileChooser fileChooser = new FileChooser();



            Stage stage = new Stage();

            stage.setTitle("email");
            stage.setScene(scene);
            // Email.stage=stage;
            System.out.println("sejour id "+Email.idSejour);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }
}

