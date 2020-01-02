package controlleurvue;

import basededonnee.DBconnexion;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import daos.ClientDao;
import daos.impl.ClientDaoImpl;
import facade.facadeEmail;
import facade.impl.FacadeEmailImpl;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import modele.Client;
import principale.Controlleur;

import java.net.URL;
import java.util.ResourceBundle;

public class Email implements Initializable ,Vue{
    public Label emeteur;
    public Label recepteur;
   public static  String idclient;
    public JFXTextField sujet;
    public JFXTextArea message;
    private Controlleur controlleur;
    private facadeEmail facadeEmail;

    @Override
    public void setController(Controlleur controller) {
        this.controlleur=controller;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ClientDao clientDao=new ClientDaoImpl(DBconnexion.getConnection());
        Client client=clientDao.getClientParId(idclient);
        this.emeteur.setText("malikabdallah75019@gmail.com");
        this.recepteur.setText(client.adresse.get());
        facadeEmail=new FacadeEmailImpl();


    }

    public void envoieEmail(MouseEvent mouseEvent) {
        String[]tab=new String[1];
        tab[0]=this.recepteur.getText();
        this.facadeEmail.sendFromGMail("malikabdallah75019","Selamwait04",tab,sujet.getText(),message.getText());
    }
}
