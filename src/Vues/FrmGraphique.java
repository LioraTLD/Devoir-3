package Vues;

import Controlers.CtrlGraphique;
import Tools.ConnexionBDD;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultKeyedValues2DDataset;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Map;

public class FrmGraphique extends JFrame{
    private JPanel pnlGraph1;
    private JPanel pnlGraph2;
    private JPanel pnlGraph3;
    private JPanel pnlGraph4;
    private JPanel pnlRoot;
    private JLabel lblGraphique1;
    private JLabel lblGraphique2;
    private JLabel lblGraphique4;
    private JButton btnGraphique1;
    CtrlGraphique ctrlGraphique;

    public FrmGraphique() throws SQLException, ClassNotFoundException {
        this.setTitle("Devoir graphique");
        this.setContentPane(pnlRoot);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        ConnexionBDD cnx = new ConnexionBDD();
        ctrlGraphique = new CtrlGraphique();

        pnlGraph1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // UNE COURBE
                DefaultCategoryDataset donnees = new DefaultCategoryDataset();

                for (Map.Entry valeur : ctrlGraphique.GetDatasGraphique1().entrySet())
                {
                    donnees.setValue(Double.parseDouble(valeur.getValue().toString()), valeur.getValue().toString(), valeur.getKey().toString());
                    System.out.println(valeur);
                }
                JFreeChart chart1 = ChartFactory.createLineChart("Moyenne des salaires par age", "Age", "Salaire", donnees);
                ChartFrame fra = new ChartFrame("Graphique n°1", chart1);
                fra.pack();
                fra.setVisible(true);
            }
        });

        pnlGraph2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // UNE COURBE
                DefaultKeyedValues2DDataset donneesGraph2 = new DefaultKeyedValues2DDataset();

                for (Map.Entry valeur : ctrlGraphique.GetDataGraphique2().entrySet())
                {
                    donneesGraph2.setValue((Double.parseDouble(valeur.getValue().toString())), valeur.getKey().toString(), valeur.getValue().toString());
                    System.out.println(valeur);
                }
                JFreeChart chart2 = ChartFactory.createStackedBarChart("Pourcentage des femmes et d hommes ", "", "", donneesGraph2);
                ChartPanel fra = new ChartPanel(chart2);
                fra.setChart(chart2);
                fra.setVisible(true);
            }
        });

        pnlGraph4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                        // HISTOGRAMME
                        DefaultCategoryDataset donnees = new DefaultCategoryDataset();
                        for (Map.Entry valeur : ctrlGraphique.GetDataGrpahique4().entrySet())
                        {
                            Double montant = Double.parseDouble(((String[])valeur.getValue())[1].toString());
                            String semestre = ((String[])valeur.getValue())[0].toString();
                            String magasin  = ((String[])valeur.getValue())[2].toString();
                            //donnees.setValue(prix,nomAction,nomTrader);
                            donnees.setValue(montant,semestre,magasin);
                            System.out.println(donnees);
                        }
                        JFreeChart chart1 = ChartFactory.createBarChart(
                                "Montant des ventes par magasin",
                                "Magasin",
                                "Montant des ventes ",
                                donnees,
                                PlotOrientation.VERTICAL,
                                true, true, false);
                        ChartFrame frame = new ChartFrame("Graphique n°4", chart1);
                        frame.pack();
                        frame.setVisible(true);
                    }
                });
    }
}
