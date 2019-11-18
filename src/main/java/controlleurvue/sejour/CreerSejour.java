package controlleurvue.sejour;

import basededonnee.DBconnexion;
import com.mysql.jdbc.PreparedStatement;
import controlleurvue.Vue;
import controlleurvue.centre.ConsulterCentre;
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
import modele.Centre;
import org.controlsfx.control.Notifications;
import principale.Controlleur;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreerSejour implements Initializable, Vue {


    public StackPane stack;
    public TextField type;
    public DatePicker dateD;
    public DatePicker dateF;
    public ComboBox centre;
    public TextField duree;

    private  Controlleur controlleur;
    @Override
    public void setController(Controlleur controller) {
        this.controlleur=controller;

        Connection connection= DBconnexion.getConnection();
        try {
            String sql="SELECT * FROM centre";
            PreparedStatement ps=(PreparedStatement)connection.prepareStatement(sql);

            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                this.centre.getItems().add(rs.getString(2));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsulterCentre.class.getName()).log(Level.SEVERE, null, ex);
        }





    }

    public void back(MouseEvent mouseEvent) {
        this.controlleur.lancerPageSejour();
    }

    public void close(MouseEvent mouseEvent) {
    }




    public void book(MouseEvent mouseEvent) {


        String sql2 = "SELECT * FROM centre WHERE nom_centre ='" + this.centre.getValue().toString().trim() + "'";



        int res=0;
        String sql="INSERT INTO sejour (duree,date_debut,date_fin,type_sejour,centre_id) VALUES (?,?,?,?,?)";
        Connection connection= DBconnexion.getConnection();
        try {

            PreparedStatement preparedStatement2=(PreparedStatement)connection.prepareStatement(sql2);
            ResultSet rs=preparedStatement2.executeQuery();
            int s=0;
            while(rs.next()){
                s=rs.getInt(1);

            }            PreparedStatement ps=(PreparedStatement)connection.prepareStatement(sql);
            ps.setString(1, this.duree.getText().toString());
            ps.setString(2, this.dateD.getValue().toString());
            ps.setString(3, this.dateF.getValue().toString());
            ps.setString(4, this.type.getText().toString());
            ps.setInt(5,s );


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





        System.out.println("data =>"+ this.dateD.getValue().toString());




    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
