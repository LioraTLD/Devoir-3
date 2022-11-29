package Controlers;

import Entities.DatasGraph;
import Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CtrlGraphique
{
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public CtrlGraphique() {
        cnx = ConnexionBDD.getCnx();
    }

    public HashMap<Integer,Integer> GetDatasGraphique1()
    {
        HashMap<Integer, Integer> datas = new HashMap();
        try {
            cnx = ConnexionBDD.getCnx();
            ps = cnx.prepareStatement("select ageEmp, AVG(salaireEmp) as Moyenne_salaire from employe group by ageEmp");
            rs = ps.executeQuery();
            while(rs.next())
            {
                datas.put(rs.getInt(1), rs.getInt(2));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlGraphique.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datas;
    }

    public HashMap<String,Integer> GetDataGraphique2()
    {
        HashMap<String, Integer> datas = new HashMap();
        try {
            cnx = ConnexionBDD.getCnx();
            ps = cnx.prepareStatement("select sexe, ageEmp, count(sexe) from employe group by ageEmp, sexe UNION select ageEmp, sexe, count(sexe) from employe group by ageEmp, sexe");
            rs = ps.executeQuery();
            while(rs.next())
            {
                datas.put(rs.getString(1), rs.getInt(2));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlGraphique.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datas;
    }
    public HashMap<Integer, String[]> GetDataGrpahique4()
    {
        HashMap<Integer, String[]> datas = new HashMap();
        try {
            cnx = ConnexionBDD.getCnx();
            ps = cnx.prepareStatement("select nomSemestre, nomMagasin, sum(montant) as montant from vente group by nomMagasin, nomSemestre order by nomSemestre, nomMagasin");
            rs = ps.executeQuery();
            while(rs.next())
            {
                datas.put(rs.getInt(3), new String[]{rs.getString(1),rs.getString(2)});
            }
            rs.close();
            System.out.println(datas);
        } catch (SQLException ex) {
            Logger.getLogger(CtrlGraphique.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datas;
    }


}
