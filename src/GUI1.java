/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author ceran
 */
public class GUI1 extends Thread {
    
    JFrame frame = new JFrame("LOGS");
    String[] columns = new String[] {"Zeitpunkt", "Aktion", "AZ"};
     
//TODO:////////// HERE YOU CAN ADD YOUR DATA!!!////////////////////////////////////////////////////////////////////////
    
    List <Patlog> myPatlogs = Datenbank.readPatlog();
    
    Object [][] data = initPatlogs(myPatlogs);
    JTable tableModel = new JTable(data, columns);
    JPanel panel = new JPanel(); // the panel is not visible in output
    JPanel panel2 = new JPanel(); // the panel is not visible in output
    JLabel label = new JLabel("Alle");
    JLabel label2 = new JLabel("Nur Akt");
    JButton refresh = new JButton("Refresh");
    
    JRadioButton radioButton1 = new JRadioButton();
    JRadioButton radioButton2 = new JRadioButton();
    
    public Object [][] initPatlogs(List <Patlog> patlogs){
        Object [][] data = new Object[patlogs.size()][];
        
        int i = 0;
        for(Patlog p : patlogs){
            data[i] = new Object[]{p.getModdat(), p.getAktion(), p.getAz()};
            i = i + 1;
        }
        return data;
    }
    
    public void setObject(Object[][] dataFromOut) {
        data = dataFromOut;
    }
   
    public GUI1() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        
        JScrollPane js = new JScrollPane(tableModel);

        tableModel.setPreferredSize(new Dimension(700,1500));
        tableModel.setFillsViewportHeight(true);
        
        if (tableModel.getPreferredSize().getHeight() < js.getPreferredSize().getHeight()){
            tableModel.setPreferredSize(js.getPreferredSize());
        }
        
        tableModel.setEnabled(false);
        radioButton1.setSelected(true);
        
        js.setVisible(true);
        
//TODO:////////// HERE YOU CAN FILTER YOUR DATA!!!//////////////////////////////////////////////////////////////////////
        radioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                radioButton2.setSelected(false); 
            }
        });
        
        radioButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                radioButton1.setSelected(false);
            }
        });

//TODO://///////////// HERE YOU CAN ADD DATA FROM YOUR DATA BASE ///////////////////////////////////////////////////////
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
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
}