package controlleurvue.inscription;

import basededonnee.DBconnexion;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controlleurvue.Vue;
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
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import modele.*;
import notification.Notification;
import principale.Controlleur;

import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;

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
        optimiserrechercheclient();

        //qd on clique sur element arbre les donnes sont automatiquements remplis
        this.clients.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                showDetails(newValue)
        );

        chargementCentre();

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



        /*this.lduree//.valueProperty().addListener((obs, oldItem, newItem) -> {
            remplirtemp((String)newItem);
        });*/
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


    private void optimiserrechercheclient() {
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
        Set<String> set = new LinkedHashSet<String>();
        set.addAll(listeSejour);
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
    private ReservationDao reservationDao;
    private EvenementDao evenementDao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        evenementDao=new EvenementDaoImpl(DBconnexion.getConnection());
        clientDao=new ClientDaoImpl(DBconnexion.getConnection());
        groupeDao=new GroupeDaoImpl(DBconnexion.getConnection());
        inscriptionDao=new InscriptionDaoImpl(DBconnexion.getConnection());
        centreDao=new CentreDaoImpl(DBconnexion.getConnection());
        sejourDao=new SejourDaoImpl(DBconnexion.getConnection());
        reservationDao=new ReservationDaoImpl(DBconnexion.getConnection());

    }


    public void retour(MouseEvent mouseEvent) {
        this.controlleur.lancerPageInscription();
    }

    public void validerinscription(MouseEvent mouseEvent) {

        String montant=this.accompte.getText();
        System.out.println("accompte :"+montant);
        int x=Integer.parseInt(montant);
        if(x>0){
            lancerDemandeInscription();
        }else{
            lancerDemandeReservation();
        }


    }

    private void lancerDemandeReservation() {
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
    }

    private void enregistrerReservation() {
        String date=(String)this.date.getValue();
        String[] args = date.split(" au ");
        Sejour sejour=sejourDao.getSejourPartypeetdureeetdate(this.type.getValue(),this.duree.getValue(),args[0],args[1]);
        Client client=clientDao.getClientParId(iduser.getText());
        System.out.println("client :"+client.prenom_client.get()+" "+client.nom_client.get());
        System.out.println("sejour :"+sejour.type.get()+" "+sejour.capacite.get());
        String aujourdhui = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String depart=(String)this.depart.getValue().toString();

        Reservation reservation=new Reservation( aujourdhui,
                client.id.get(),sejour.id.get(),depart) ;


        int res=reservationDao.insererReservation(reservation);
        if(res>0){
            Notification.affichageSucces("succes","reservation faite avec succes");

        }else{
            Notification.affichageEchec("erreur","echec dans la creation de la reservation");

        }

    }

    private void lancerDemandeInscription() {

        JFXDialogLayout dialogLayout=new JFXDialogLayout();
        dialogLayout.setHeading(new Text("ferme"));
        dialogLayout.setBody(new Text("vous voulez finaliser cette inscription  ?"));

        JFXButton ok=new JFXButton("ok");
        JFXButton cancel=new JFXButton("annule");

        final JFXDialog dialog=new JFXDialog(stackepane,dialogLayout, JFXDialog.DialogTransition.CENTER);

        ok.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(javafx.event.ActionEvent event) {
                enrergistrerInscription();
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
    }


    public void enrergistrerInscription(){
        String date=(String)this.date.getValue();
        String[] args = date.split(" au ");
        Sejour sejour=sejourDao.getSejourPartypeetdureeetdate(this.type.getValue(),this.duree.getValue(),args[0],args[1]);
        System.out.println("sejour ="+sejour);
       Client client=clientDao.getClientParId(iduser.getText());
       System.out.println("client :"+client.prenom_client.get()+" "+client.nom_client.get());
        System.out.println("sejour :"+sejour.type.get()+" "+sejour.capacite.get());
        String aujourdhui = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String depart=(String)this.depart.getValue().toString();
        Inscription inscription=new Inscription(this.accompte.getText().toString(), aujourdhui,
        client.id.get(),sejour.id.get(),depart) ;
        int res=inscriptionDao.insererInscription(inscription);
        if(res>0){

            Evenement evenement=new Evenement(client.id.get(),sejour.id.get(),"paiement inscription",this.accompte.getText(),aujourdhui);
            evenementDao.insererEvenement(evenement);
            Notification.affichageSucces("succes","inscription faite avec succes");
            confirmationInscriptionPDF(client,sejour);

        }else{
            Notification.affichageEchec("erreur","echec dans la creation de la reservation");
        }
    }
    public void confirmationInscriptionPDF(Client client,Sejour sejour){
        Document doc = new Document();

        try {
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("src/main/resources/docs/Inscription_"+client.prenom_client.get()+".pdf"));
            doc.open();

            //Add Image
            Image img = Image.getInstance("src/main/resources/img/oul.jpg");
            //Fixed Positioning
            img.setAbsolutePosition(30f, 700f);
            //Scale to new height and new width of image
            img.scaleAbsolute(100, 80);
            //Add to document
            doc.add(img);
            //doc.add(Chunk.SPACETABBING);
            doc.add(new Paragraph("\n\n\n\n\n"));
            Font font = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
            font.setColor(BaseColor.BLUE);
            Font font1 =FontFactory.getFont(FontFactory.HELVETICA, 10);
            font1.setColor(BaseColor.BLUE);

            Font font2 =FontFactory.getFont(FontFactory.HELVETICA, 8);


            doc.add(new Phrase("ŒUVRE UNIVERSITAIRE DU LOIRET\n",font));
            doc.add(new Phrase("2  rue des Deux Ponts \nCS 30724 \n45017 ORLEANS CEDEX 1 \nTél : 02.38.53.38.61\n",font2));
            doc.add(new Phrase("siege.asso@ouloiret.fr\nwww.ouloiret.fr \n",font1));
            doc.add(new Phrase("SIRET : 77550821100072 \nAPE : 552 E",font2));

            DateFormat fullDateFormat = DateFormat.getDateInstance(DateFormat.FULL);
            Paragraph jour = new Paragraph("Le "+fullDateFormat.format(new Date()));
            jour.setAlignment(Element.ALIGN_CENTER);
            doc.add(jour);

            Paragraph dest = new Paragraph("Mme ou M. "+client.observation.get()+"\n\n"+client.codePostale.get()+" "+client.adresse.get()+"\n");
            dest.setAlignment(Element.ALIGN_RIGHT);
            doc.add(dest);

            doc.add(new Paragraph("Référence à rappeler :( 159297 / 1112 - 8V0)"));
            Image alerte = Image.getInstance("src/main/resources/img/alerte.jpg");

            alerte.setAbsolutePosition(30f, 492f);

            alerte.scaleAbsolute(10, 10);
            doc.add(alerte);

            font.setColor(BaseColor.BLACK);
            doc.add(new Paragraph("    (votre dossier ne pourra être pris en compte sans cette référence)\n\n",font));
            Paragraph titre = new Paragraph("CONFIRMATION D’INSCRIPTION",font);
            titre.setAlignment(Element.ALIGN_CENTER);
            doc.add(titre);

            Paragraph par = new Paragraph("       Madame, Monsieur,\n\n " +
                    "     Nous avons le plaisir de vous confirmer l’inscription de votre enfant " + client.prenom_client.get()+" "+client.nom_client.get()+"\n"+
                    "     •  au centre de vacances de CV INGRANNES,\n     •  pour le séjour du"+date.getValue()+"\n     •  pour une durée de 6  jours.\n\n"+
                    "     Les frais de séjour et de voyage s’élèvent  à : "+sejour.prix.get()+" €, auxquels s’ajoute le montant de l’adhésion, soit 3,00 € par famille et par année.\n\n"+
                    "     Vous avez versé des arrhes pour un montant de  50 € ainsi que l’adhésion de 3 € (sauf si elle a déjà été réglée lors d’un précédent séjour). Le solde est à régler entre trois et quatre semaines avant le départ. Nous vous rappelons que notre association est habilitée pour accepter les paiements par Chèques-Vacances.\n");

                  /*
    •
                  Deux à trois semaines avant le départ, une fois le séjour soldé, nous vous ferons parvenir les documents suivants :
    ► la convocation avec les lieux et heures de départ et retour
    ► le dossier du jeune ou de l’enfant à compléter
    ► la lettre du directeur vous donnant des informations sur le déroulement du séjour.
                    Nous vous rappelons que pour toutes les activités nautiques (voile, kayak, canyoning, surf, plongée…) le test de natation est obligatoire (le brevet de 25 ou 50 m est non valable).
            Attention : Pour la plongée un certificat médical est également demandé.
                    Si vous êtes en possession de Bons-Vacances CAF ou MSA et si vous ne nous les avez pas encore transmis, veuillez nous les adresser dans les meilleurs délais (ou votre numéro d’allocataire pour les VACAF).
                    En souhaitant que votre enfant passe un agréable séjour en Centre de Vacances, nous vous adressons nos respectueuses salutations.
            ");
                    Le directeur.
                    M. JOBERT

            PAPILLON à DETACHER ET à RETOURNER A L’O.U.L.AVEC VOTRE REGLEMENT
                    (si séjour NON SOLDÉ ou non pris en charge)
            NOM : AUDIER ISAAC
            REFERENCE : 159297 /  1112 - 8V0 /  CV INGRANNES du 20/08/2018 au 25/08/2018
            (votre dossier ne pourra être pris en compte sans cette référence)
            Le transport n’est pas compris pour les centres du Loiret, (INGRANNES, LES CAILLETTES, L’ETANG DU PUITS)
            vous devez conduire votre enfant sur place. Cependant un départ d’ORLEANS est possible (20 € ALLER/RETOUR),        le nombre de places étant limité, veuillez nous en informer par téléphone ou par courrier.
*/
            doc.add(par);
            doc.close();
            writer.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
