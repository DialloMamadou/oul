package daos.impl;

import basededonnee.DBconnexion;
import com.mysql.jdbc.PreparedStatement;
import controlleurvue.sejour.CreerSejour;
import daos.CentreDao;
import daos.SejourDao;
import modele.Centre;
import modele.Groupe;
import modele.Sejour;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SejourDaoImpl extends Dao<Sejour> implements SejourDao {
    public SejourDaoImpl(Connection conn) {
        super(conn);
    }

    @Override
    public int insererSejour(Sejour sejour) {
        int res=0;

        String sql="INSERT INTO sejour (duree,date_debut,date_fin,type_sejour,centre_id,prix,age_min,age_max,capacite) VALUES (?,?,?,?,?" +
                ",?,?,?,?)";
        try {

            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ps.setString(1, sejour.duree.get());
            ps.setString(2,sejour.date_debut.get());
            ps.setString(3, sejour.date_fin.get());
            ps.setString(4, sejour.type.get());
            ps.setString(6,sejour.prix.get() );
            ps.setString(7,sejour.ageMin.get() );
            ps.setString(8,sejour.ageMax.get() );
            ps.setString(9,sejour.capacite.get() );
            ps.setString(5,sejour.nom_centre.get() );




            res=ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CreerSejour.class.getName()).log(Level.SEVERE, null, ex);
        }

        return  res;
    }

    @Override
    public List<Sejour> listeSejour() {
        CentreDao centreDao=new CentreDaoImpl(DBconnexion.getConnection());
        String sql="SELECT * FROM sejour";
        List<Sejour>liste=new ArrayList<>();

        try{
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                Centre centre=centreDao.getCentreParId(rs.getString(6));
                liste.add(new Sejour(rs.getString(1),rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                ,rs.getString(5),
                        centre.nom_centre.get(),
                        rs.getString(7),
                        rs.getString(8),
                      rs.getString(9),
                        rs.getString(10)  ));

            }


        }catch (Exception e){

        }

        return liste;
    }
}
