/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import java.sql.Connection;

/**
 *
 * @author ceran
 */
public class GUI1 {
    
    JFrame frame = new JFrame("LOGS");
    String[] columns = new String[] {"Zeitpunkt", "Aktion", "AZ"};

//TODO:////////// HERE YOU CAN ADD YOUR DATA!!!////////////////////////////////////////////////////////////////////////
    Object[][] data = new Object[][] {
            {1, "John", false },
            {2, "Rambo", false },
            {3, "Rocky", true },
    };

    JTable tableModel = new JTable(data, columns);
    JPanel panel = new JPanel(); // the panel is not visible in output
    JPanel panel2 = new JPanel(); // the panel is not visible in output
    JLabel label = new JLabel("Alle");
    JLabel label2 = new JLabel("Nur Akt");
    JButton refresh = new JButton("Refresh");

    JRadioButton radioButton1 = new JRadioButton();
    JRadioButton radioButton2 = new JRadioButton();
    
    public void setObject(Object[][] dataFromOut) {
        data = dataFromOut;
    }


    public GUI1() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);

//        JTable table = new JTable(data, columns);
        tableModel.setPreferredSize(new Dimension(400,350));
        tableModel.setPreferredScrollableViewportSize(tableModel.getPreferredSize());
        tableModel.setFillsViewportHeight(true);
        radioButton1.setSelected(true);
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
                System.out.println("BLBLBLBLBLBLBLBL");
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

