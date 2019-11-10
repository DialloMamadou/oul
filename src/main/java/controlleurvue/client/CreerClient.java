package controlleurvue.client;

import basededonnee.DBconnexion;
import com.mysql.jdbc.PreparedStatement;
import controlleurvue.Vue;
import controlleurvue.centre.ConsulterCentre;
import controlleurvue.sejour.CreerSejour;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import principale.Controlleur;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreerClient  implements Initializable, Vue {


    public StackPane stack;
    public TextField nom;

    public ComboBox groupe;
    public TextField prenom;
    public TextField age;
    public TextField portable;
    public TextField observation;
    public TextField email;
    public TextField poste;
    public DatePicker annee;
    public TextField adresse;

    private Controlleur controlleur;
    @Override
    public void setController(Controlleur controller) {
        this.controlleur=controller;



        Connection connection= DBconnexion.getConnection();
        try {
            String sql="SELECT * FROM groupe";
            PreparedStatement ps=(PreparedStatement)connection.prepareStatement(sql);

            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                this.groupe.getItems().add(rs.getString(2));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsulterCentre.class.getName()).log(Level.SEVERE, null, ex);
        }





    }

    public void back(MouseEvent mouseEvent) {
        this.controlleur.lancerPageClient();
    }

    public void close(MouseEvent mouseEvent) {
    }




    public void book(MouseEvent mouseEvent) {


        String sql2 = "SELECT * FROM groupe WHERE nom_groupe ='" + this.groupe.getValue().toString().trim() + "'";



        int res=0;
        String sql="INSERT INTO client (nom_client,prenom_client,age_client,groupe_client,numero,observation,email,adresse,code_postale,datenaissance) VALUES (?,?,?,?,?,?,?,?,?,?)";
        Connection connection= DBconnexion.getConnection();
        try {

            PreparedStatement preparedStatement2=(PreparedStatement)connection.prepareStatement(sql2);
            ResultSet rs=preparedStatement2.executeQuery();
            int s=0;
            while(rs.next()){
                s=rs.getInt(1);

            }

            PreparedStatement ps=(PreparedStatement)connection.prepareStatement(sql);
            ps.setString(1, this.prenom.getText().toString());
            ps.setString(2, this.nom.getText().toString());
            ps.setString(3, this.age.getText().toString());
            ps.setInt(4, s);
            ps.setString(5,this.portable.getText().toString());
            ps.setString(6,this.observation.getText().toString());
            ps.setString(7,this.email.getText().toString());
            ps.setString(8,this.adresse.getText().toString());
            ps.setString(9,this.poste.getText().toString());
            ps.setString(10,this.annee.getValue().toString());


            res=ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CreerSejour.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(res>0){
            Image image=new Image("img/mooo.png");
            Notifications notification=Notifications.create()
                    .title("Done")
                    .text("Sejour creer avec succes")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_LEFT)
                    .graphic(new ImageView(image));
            notification.darkStyle();
            notification.show();
            //   updateStatus();
        }else{
            Image image=new Image("img/delete.png");
            Notifications notification=Notifications.create()
                    .title("Error")
                    .text("echec dans la creation du centre")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_LEFT)
                    .graphic(new ImageView(image));
            notification.darkStyle();
            notification.show();
        }









    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
