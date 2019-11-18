package daos.impl;

import basededonnee.DBconnexion;
import com.mysql.jdbc.PreparedStatement;
import controlleurvue.centre.CreerCentre;
import controlleurvue.sejour.CreerSejour;
import daos.InscriptionDao;
import daos.ReservationDao;
import modele.Inscription;
import modele.Reservation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservationDaoImpl  extends Dao<Reservation> implements ReservationDao {

    public ReservationDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<Reservation> getReservations() {
        String sql="SELECT * FROM reservation";
        List<Reservation>liste=new ArrayList<>();

        try{
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                liste.add(new Reservation(rs.getInt(1)+"",rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5)));

            }


        }catch (Exception e){

        }

        return liste;
    }

    @Override
    public int insererReservation(Reservation reservation) {
        int res=0;

        String sql="INSERT INTO reservation (date_reservation,code_client,id_sejour,depart)" +
                " VALUES (?,?,?,?)";
        try {

            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ps.setString(1,reservation.dateinscription.get());
            ps.setString(2, reservation.code_client.get());
            ps.setString(3,reservation.id_sejour.get());
            ps.setString(4,reservation.depart.get() );
            res=ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CreerSejour.class.getName()).log(Level.SEVERE, null, ex);
        }

        return  res;
    }

    @Override
    public int supprimerParId(String toString) {

        int res=0;
        String sql="DELETE FROM reservation WHERE id_reservation=?";
        Connection connection= DBconnexion.getConnection();
        try {
            PreparedStatement ps=(PreparedStatement)connection.prepareStatement(sql);
            if(toString.length()!=0) {
                ps.setString(1, toString);
            }

            res=ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CreerCentre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
}
