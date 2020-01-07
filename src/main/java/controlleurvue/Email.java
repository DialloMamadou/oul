package controlleurvue;

import basededonnee.DBconnexion;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import daos.ClientDao;
import daos.InscriptionDao;
import daos.SejourDao;
import daos.impl.ClientDaoImpl;
import daos.impl.InscriptionDaoImpl;
import daos.impl.SejourDaoImpl;
import facade.facadeEmail;
import facade.impl.FacadeEmailImpl;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import modele.Client;
import modele.Inscription;
import notification.Notification;
import principale.Controlleur;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Email implements Initializable ,Vue{
    public Label emeteur;
    public Label recepteur;
   public static  String idclient;
   public static boolean envoieGroupe;
   public static String idsejour;
    public JFXTextField sujet;
    public JFXTextArea message;
    public StackPane pane;
    public Button joindre;
    public Label doc;
    private Controlleur controlleur;
    private facadeEmail facadeEmail;
    private File file;
    private SejourDao sejourDao;
    private InscriptionDao inscriptionDao;
    private ClientDao clientDao;

    @Override
    public void setController(Controlleur controller) {
        this.controlleur=controller;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ClientDao clientDao=new ClientDaoImpl(DBconnexion.getConnection());
        sejourDao=new SejourDaoImpl(DBconnexion.getConnection());
        inscriptionDao=new InscriptionDaoImpl(DBconnexion.getConnection());
        clientDao=new ClientDaoImpl(DBconnexion.getConnection());
        Client client=clientDao.getClientParId(idclient);
        if(envoieGroupe){
            List<Inscription>list=inscriptionDao.getInscriptionsParIdSejour(Email.idsejour);
            String[]tab=new String[list.size()];
            int cpt=0;
            for(Inscription inscription:list){
                Client client2=clientDao.getClientParId(inscription.code_client.get());
                tab[cpt]=client2.email.get();
                cpt++;

            }
            String s="";
            for(int i=0;i<tab.length-1;i++){
                if(i==2){
                    break;
                }
                s=s+tab[i]+",";

            }
            s=s+"...";

            this.recepteur.setText(s);

        }else{
            this.recepteur.setText(client.email.get());

        }
        this.emeteur.setText("malikabdallah75019@gmail.com");
        facadeEmail=new FacadeEmailImpl();


        FileChooser fileChooser = new FileChooser();

        this.joindre.setOnAction(e -> {
             file = fileChooser.showOpenDialog(pane.getScene().getWindow());
            System.out.println("chemin "+file.getPath());
            Notification.affichageSucces("file choisit ","file choisit");
            this.doc.setText(file.getName());
        });


    }

    public void envoieEmail(MouseEvent mouseEvent) {
        if(Email.idsejour!="") {
            String[] tab = new String[1];
            tab[0] = this.recepteur.getText();
            System.out.println("recepteur email " + tab[0]);
            this.facadeEmail.sendFromGMail("malikabdallah75019", "Selamwait04", tab, sujet.getText(), message.getText(), file);
            Scene scene = pane.getScene();
        }else{
            List<Inscription>list=inscriptionDao.getInscriptionsParIdSejour(Email.idsejour);
            String[]tab=new String[list.size()];
            int cpt=0;
            for(Inscription inscription:list){
                Client client=clientDao.getClientParId(inscription.code_client.get());
                tab[cpt]=client.email.get();
                cpt++;

            }
            this.facadeEmail.sendFromGMail("malikabdallah75019", "Selamwait04", tab, sujet.getText(), message.getText(), file);

        }

    }
}
