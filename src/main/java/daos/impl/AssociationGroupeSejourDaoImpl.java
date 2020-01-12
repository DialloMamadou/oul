package daos.impl;

import com.mysql.jdbc.PreparedStatement;
import controlleurvue.centre.CreerCentre;
import controlleurvue.groupe.AssocierGroupeSejour;
import daos.AssociationGroupeSejourDao;
import daos.GroupeDao;
import modele.Associationgroupesejour;
import modele.Client;
import modele.Groupe;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AssociationGroupeSejourDaoImpl extends Dao<Associationgroupesejour> implements AssociationGroupeSejourDao {

    public AssociationGroupeSejourDaoImpl(Connection conn) {
        super(conn);
    }

    @Override
    public int inserrerAssociation(Associationgroupesejour associerGroupeSejour) {
        int res=0;
        String sql="INSERT INTO associationgroupesejour (prix_unitaire,groupe,sejour,nbplace) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ps.setString(1,associerGroupeSejour.prix_unitaire.get());
            ps.setString(2,associerGroupeSejour.groupe.get());
            ps.setString(3,associerGroupeSejour.sejour.get());

            ps.setString(4,associerGroupeSejour.nbPlace.get());

            res=ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CreerCentre.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;

    }

    @Override
    public List<Associationgroupesejour> getListes() {
        GroupeDao groupeDao=new GroupeDaoImpl(connect);


        String sql="SELECT * FROM associationgroupesejour ";
        List<Associationgroupesejour>liste=new ArrayList<>();

        try{
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();


            while(rs.next()){

                System.out.println("xxxx1");
                liste.add(new Associationgroupesejour(rs.getString(1),rs.getString(2),rs.getString(3),
                       rs.getString(4),rs.getString(5)));



            }


        }catch (Exception e){

        }

        return liste;
    }

    @Override
    public Associationgroupesejour getById(String s) {
        String sql="SELECT * FROM associationgroupesejour WHERE id ='"+s+"'";
        Associationgroupesejour associationgroupesejour=null;
        try{
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                associationgroupesejour=new Associationgroupesejour(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                       );

            }


        }catch (Exception e){

        }

        return associationgroupesejour;
    }
}