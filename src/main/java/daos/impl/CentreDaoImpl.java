package daos.impl;

import com.mysql.jdbc.PreparedStatement;
import controlleurvue.centre.CreerCentre;
import daos.CentreDao;
import modele.Centre;
import modele.Groupe;

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

    public int inserrerCentre(String nom_centre,String capacite) {
        int res=0;
        String sql="INSERT INTO centre (nom_centre,capacite) VALUES (?,?)";
        try {
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
                ps.setString(1, nom_centre);
                ps.setString(2,capacite);


            res=ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CreerCentre.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;

    }




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



    public List<Centre> listeCentres() {

        String sql="SELECT * FROM centre";
        List<Centre>liste=new ArrayList<Centre>();
        try{
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                liste.add(new Centre(rs.getInt(1)+"",rs.getString(2),rs.getString(3)));

            }


        }catch (Exception e){

        }

        return liste;
    }

    @Override
    public Centre trouverParNomCentre(String nom) {
        String sql="SELECT * FROM centre WHERE nom_centre ='"+nom+"'";
        Centre liste=null;
        try{
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                liste=new Centre(rs.getInt(1)+"",rs.getString(2),rs.getString(3));

            }


        }catch (Exception e){

        }

        return liste;
    }

    public Centre getCentreParId(String id) {
        String sql="SELECT * FROM centre WHERE id_centre ='"+id+"'";
        Centre liste=null;
        try{
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){

               liste=new Centre(rs.getInt(1)+"",rs.getString(2),rs.getString(3));

            }


        }catch (Exception e){

        }

        return liste;

    }


}
