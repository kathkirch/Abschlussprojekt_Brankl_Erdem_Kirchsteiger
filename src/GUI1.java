/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ceran
 */
public class GUI1 extends Thread {
    
    JFrame frame = new JFrame("LOGS");
    String[] columns = new String[] {"Zeitpunkt", "Aktion", "AZ"};
    
    //List<Patlog> erzeugen danach in Object Array data übergeben mit initPatlogs();
    List <Patlog> myPatlogs = Datenbank.readPatlog();
    Object [][] data = initPatlogs(myPatlogs);
    static Object [][] refreshdata;
    
    List<Patlog> filteredList = new ArrayList<>();
    
    JTable tableModel = new JTable(data, columns);
    JPanel panel = new JPanel(); 
    JPanel panel2 = new JPanel(); 
    JLabel label = new JLabel("Alle");
    JLabel label2 = new JLabel("Nur Akt");
    JButton refresh = new JButton("Refresh");
    
    JRadioButton radioButton1 = new JRadioButton();
    JRadioButton radioButton2 = new JRadioButton();

///////////////////////////////////////////////////////////////////////////////
    /**
     * @author Kirchsteiger
     */
    public Object [][] initPatlogs(List <Patlog> patlogs){
        //Neues Object Array wird erzeugt
        Object [][] data = new Object[patlogs.size()][];
        
        //Hilfscounter
        int i = 0;
        
        //durch die List<Patlog> patlogs iterieren, jedes Patlog Objekt in data speichern
        for(Patlog p : patlogs){
            data[i] = new Object[]{p.getModdat(), p.getAktion(), p.getAz()};
            i = i + 1;
        }
        return data;
    }
    
    /**
     * @author Erdem
     */
    
   
    public GUI1() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 950);
        frame.setResizable(true);
        
        JScrollPane js = new JScrollPane(tableModel);

        tableModel.setPreferredScrollableViewportSize(new Dimension(650, 700));
        tableModel.setFillsViewportHeight(true);
        tableModel.setRowHeight(25);
        tableModel.setAutoCreateRowSorter(true);
        
        tableModel.setPreferredSize(new Dimension(650,3800));
        tableModel.setAutoCreateRowSorter(true);
        tableModel.setFillsViewportHeight(true);
        
        if (tableModel.getPreferredSize().getHeight() < js.getPreferredSize().getHeight()){
            tableModel.setPreferredSize(js.getPreferredSize());
        }
        
        tableModel.setEnabled(false);
        radioButton1.setSelected(true);
        
        js.setVisible(true);
        

        radioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                
                /**
                 * @author Brankl
                 */
                radioButton2.setSelected(false);
                if (data.length >= refreshdata.length){
                    data = initPatlogs(myPatlogs);
                    tableModel.setModel(new DefaultTableModel(data, columns));
                } else if (data.length < refreshdata.length){
                    refreshdata = initPatlogs(myPatlogs);
                    tableModel.setModel(new DefaultTableModel(refreshdata, columns));
                }
            }
        });
        
        /**
         * @author Erdem
         */
        radioButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                /**
                 * @author Brankl
                 */
                radioButton1.setSelected(false);
                
                updatedList();
                
                 if (data.length >= refreshdata.length){
                    data = initPatlogs(filteredList);
                    tableModel.setModel(new DefaultTableModel(data, columns));
                 } else if (data.length < refreshdata.length){
                    refreshdata = initPatlogs(filteredList);
                    tableModel.setModel(new DefaultTableModel(refreshdata, columns));
                 }                
            }
        });

          /**
         * @author Erdem
         */
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                /**
                 * @author Brankl
                 */
                myPatlogs = Datenbank.readPatlog();
                refreshdata = initPatlogs(myPatlogs);
                
                tableModel.setModel(new DefaultTableModel(refreshdata, columns));
                radioButton1.setSelected(true);
                radioButton2.setSelected(false);
            }
        });

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        panel.add(label); // Components Added using Flow Layout
        panel.add(radioButton1);
        panel.add(label2);
        panel.add(radioButton2);
        panel.add(refresh);
        panel2.add(new JScrollPane(tableModel));

        // Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, panel2);
        frame.setVisible(true);
    }
///////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @author Brankl
     */
   
    public List<Patlog> updatedList() {

        Statement st;
        ResultSet rs;
        filteredList.clear();

        try {
            Connection con = Datenbank.getConnection();
            st = con.createStatement();
            String filterQuery = "SELECT * FROM branklth19.patlog WHERE Aktion = 'update'";
            rs = st.executeQuery(filterQuery);

            while (rs.next()) {
                int plID = rs.getInt("ID");
                String plZp = rs.getString("Zeitpunkt");
                String plAktion = rs.getString("Aktion");
                String plAZ = rs.getString("AZ");

                Patlog patlog = new Patlog();

                patlog.setId(plID);
                patlog.setModdat(plZp);
                patlog.setAktion(plAktion);
                patlog.setAz(plAZ);

                filteredList.add(patlog);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

        return filteredList;

    }    
}