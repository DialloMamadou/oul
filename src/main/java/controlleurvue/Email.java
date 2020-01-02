package controlleurvue;

import basededonnee.DBconnexion;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import daos.ClientDao;
import daos.impl.ClientDaoImpl;
import facade.facadeEmail;
import facade.impl.FacadeEmailImpl;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modele.Client;
import principale.Controlleur;

import java.net.URL;
import java.util.ResourceBundle;

public class Email implements Initializable ,Vue{
    public  static Stage stage;
    public static Label emeteur;
    public static Label recepteur;
   public static  String idclient;
    public static JFXTextField sujet;
    public  static JFXTextArea message;
    private Controlleur controlleur;
    private  static facadeEmail facadeEmail;

    @Override
    public void setController(Controlleur controller) {
        this.controlleur=controller;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ClientDao clientDao=new ClientDaoImpl(DBconnexion.getConnection());
        Client client=clientDao.getClientParId(idclient);
       Email.emeteur.setText("malikabdallah75019@gmail.com");
        Email.recepteur.setText(client.adresse.get());

        stage.close();

    }

    public static void envoieEmail(MouseEvent mouseEvent) {
        facadeEmail=new FacadeEmailImpl();
        String[]tab=new String[1];
        tab[0]=Email.recepteur.getText();
        Email.facadeEmail.sendFromGMail("malikabdallah75019","Selamwait04",tab,sujet.getText(),message.getText());

    }

    public void envoyerEmail(MouseEvent mouseEvent) {
        facadeEmail=new FacadeEmailImpl();
        String[]tab=new String[1];
        tab[0]=Email.recepteur.getText();
        Email.facadeEmail.sendFromGMail("malikabdallah75019","Selamwait04",tab,sujet.getText(),message.getText());
    }

}
