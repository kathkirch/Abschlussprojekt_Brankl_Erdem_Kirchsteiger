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
public class GUI2 {
    
    JFrame frame = new JFrame("AUFENTHALTE");
    String[] columns = new String[] {"Aufnahme Datum", "Vorname", "Nachname", "AZ", "Mod.At"};
    
    List <Aufenthalte> myAufenthalte = Datenbank.readAufenthalteDB();

    /*Object[][] data = new Object[][] {
            {1, "John", "Rambo", "1", "1.0.1"},
            {2, "John", "Rambo", "1", "1.0.1"},
            {3, "John", "Rambo", "1", "1.0.1"},
    };*/
    Object [][] data = initAufenthalte(myAufenthalte);
    JTable tableModel = new JTable(data, columns);
    JPanel panel = new JPanel(); 
    JPanel panel2 = new JPanel(); 
    JLabel label = new JLabel("Sortierung");
    JLabel label2 = new JLabel("AZ");
    JLabel label3 = new JLabel("Name");
    JButton refresh = new JButton("Refresh");

    JRadioButton radioButton1 = new JRadioButton();
    JRadioButton radioButton2 = new JRadioButton();
    
    public Object [][] initAufenthalte(List <Aufenthalte> aufenthalte){
        Object [][] data = new Object[aufenthalte.size()][];
        
        int i = 0;
        for(Aufenthalte a : aufenthalte){
            data[i] = new Object[]{a.getAufdat(), a.getVorname(), a.getNachname(),
                                    a.getAz(), a.getModdat()};
            i = i + 1;
        }
        return data;
    }
    
    public void setObject(Object[][] dataFromOut) {
        data = dataFromOut;
    }

    public GUI2() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);


        tableModel.setPreferredSize(new Dimension(400,350));
        tableModel.setPreferredScrollableViewportSize(tableModel.getPreferredSize());
        tableModel.setFillsViewportHeight(true);
        radioButton1.setSelected(true);

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



        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                System.out.println("BLBLBLBLBLBLBLBL");
            }
        });


        panel.add(label); 
        panel.add(radioButton1);
        panel.add(label2);
        panel.add(radioButton2);
        panel.add(label3);

        panel.add(refresh);
        panel2.add(new JScrollPane(tableModel));

        
        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, panel2);
        frame.setVisible(true);

    }

}



        
        
    
    
