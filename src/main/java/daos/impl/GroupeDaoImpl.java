package daos.impl;

import com.mysql.jdbc.PreparedStatement;
import controlleurvue.centre.CreerCentre;
import daos.GroupeDao;
import java.sql.Array;
import modele.Centre;
import modele.Groupe;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GroupeDaoImpl extends Dao<Groupe> implements GroupeDao {

    public GroupeDaoImpl(Connection conn) {
        super(conn);
    }


    @Override
    public int inserrerGroupe(String nom_groupe, String tiers) {

        int res = 0;
        String sql = "INSERT INTO groupe (nom_groupe,tiers) VALUES (?,?)";
        try {
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(sql);
            ps.setString(1, nom_groupe);
            ps.setString(2, tiers);


            res = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CreerCentre.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }

    @Override
    public int supprimerGroupe(String id) {
        String sql = "DELETE FROM groupe WHERE id_groupe=?";

        int res = 0;

        try {


            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(sql);
            ps.setString(1, id);



            res = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CreerCentre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;

    }

    @Override
    public List<Groupe> listeGroupes() {
        String sql = "SELECT * FROM groupe";
        List<Groupe> liste;
        liste = new ArrayList<>() ;

        try {
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                liste.add(new Groupe(rs.getInt(1) + "", rs.getString(2),rs.getString(3)));

            }


        } catch (Exception e) {

        }

        return liste;
    }

    @Override
    public Groupe trouverGroupeParNomGroupe(String nom_groupe) {
        String sql = "SELECT * FROM groupe WHERE nom_groupe ='" + nom_groupe + "'";
        Groupe liste = null;
        try {
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                liste = new Groupe(rs.getInt(1) + "", rs.getString(2),rs.getString(3));

            }
            return liste;


        } catch (Exception e) {

        }

        return null;
    }

    @Override
    public Groupe trouverGroupeParCodeTiers(String tiers) {
        String sql = "SELECT * FROM groupe WHERE tiers ='" + tiers + "'";
        Groupe liste = null;
        try {
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                liste = new Groupe(rs.getInt(1) + "", rs.getString(2),
                        rs.getString(3));

            }

            return liste;

        } catch (Exception e) {

        }
        return null;
    }



    @Override
    public Groupe getGroupeParId(String id) {
        String sql="SELECT * FROM groupe WHERE id_groupe ='"+id+"'";
        Groupe liste=null;
        try{
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                liste=new Groupe(rs.getInt(1)+"",rs.getString(2),
                        rs.getString(3));

            }


        }catch (Exception e){

        }

        return liste;    }
}
