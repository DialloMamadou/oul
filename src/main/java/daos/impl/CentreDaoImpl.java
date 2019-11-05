package daos.impl;

import com.mysql.jdbc.PreparedStatement;
import controlleurvue.centre.CreerCentre;
import daos.CentreDao;
import modele.Centre;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CentreDaoImpl extends Dao<Centre> implements CentreDao {


    public CentreDaoImpl(Connection conn) {
        super(conn);
    }

    @Override
    public int inserrerCentre(String nom_centre) {
        int res=0;
        String sql="INSERT INTO centre (nom_centre) VALUES (?)";
        try {
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
                ps.setString(1, nom_centre);


            res=ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CreerCentre.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;

    }


    private  Centre map( ResultSet rs ) throws SQLException {


        return         new Centre(rs.getInt(1)+"",rs.getString(2));

    }

    @Override
    public int supprimerCentre(String id) {
        int res=0;
        String sql="DELETE FROM centre WHERE id_centre=?";

        try {
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
                ps.setString(1, id);


            res=ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CreerCentre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }



    @Override
    public List<Centre> listeCentres() {

        String sql="SELECT * FROM centre";
        List<Centre>liste=new ArrayList<>();
        try{
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                liste.add(new Centre(rs.getInt(1)+"",rs.getString(2)));

            }


        }catch (Exception e){

        }

        return liste;
    }

    @Override
    public Centre getCentreParId(String id) {
        String sql="SELECT * FROM centre WHERE id_centre ='"+id+"'";
        Centre liste=null;
        try{
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                liste=new Centre(rs.getInt(1)+"",rs.getString(2));

            }


        }catch (Exception e){

        }

        return liste;

    }

    @Override
    public boolean create(Centre obj) {
        return false;
    }

    @Override
    public boolean delete(Centre obj) {
        return false;
    }

    @Override
    public boolean update(Centre obj) {
        return false;
    }

    @Override
    public Centre find(int id) {
        return null;
    }
}
