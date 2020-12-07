/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author ceran
 */
public class GUI2 extends Thread{
    
    JFrame frame = new JFrame("AUFENTHALTE");
    String[] columns = new String[] {"Aufnahme Datum", "Nachname", "Vorname", "AZ", "Mod.At"};
    
    // neue List <Aufenthalte erzeugen, in Object Array 'data' übergeben mit initAufenthalte();>
    List <Aufenthalte> myAufenthalte = Datenbank.readAufenthalteDB();
    Object [][] data = initAufenthalte(myAufenthalte);
    static Object [][] refreshData;
    
    
    JTable tableModel = new JTable(data, columns);
    JPanel panel = new JPanel(); 
    JPanel panel2 = new JPanel(); 
    JLabel label = new JLabel("Sortierung");
    JLabel label2 = new JLabel("AZ");
    JLabel label3 = new JLabel("Name");
    JButton refresh = new JButton("Refresh");
    
    JRadioButton radioButton1 = new JRadioButton();
    JRadioButton radioButton2 = new JRadioButton();
    
    /**
     * @ author Kirchsteiger
     */
    public Object [][] initAufenthalte(List <Aufenthalte> aufenthalte){
        //neues Objekt Array erzeugen
        Object [][] data = new Object[aufenthalte.size()][];
        
        //Hilfscounter erzeugen
        int i = 0;
        
        //durch List <Aufenthalte> iterieren, jedes Aufenthalte-Objekt in data speichern
        for(Aufenthalte a : aufenthalte){
            data[i] = new Object[]{a.getAufdat(), a.getNachname(), a.getVorname(),
                                    a.getAz(), a.getModdat()};
            i = i + 1;
        }
        return data;
    }

/**
 * @ author Erdem
 */    
   
    public GUI2() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 950);
        frame.setResizable(true);
        
        JScrollPane js = new JScrollPane(tableModel);

        tableModel.setPreferredScrollableViewportSize(new Dimension(650, 700));
        tableModel.setFillsViewportHeight(true);
        tableModel.setRowHeight(25);
        tableModel.setAutoCreateRowSorter(true);
        
        TableColumnModel columnModel = tableModel.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(20);
        columnModel.getColumn(1).setPreferredWidth(30);
        columnModel.getColumn(2).setPreferredWidth(25);
        columnModel.getColumn(3).setPreferredWidth(30);
        
        tableModel.setPreferredSize(new Dimension(650,2800));
        tableModel.setAutoCreateRowSorter(true);
        tableModel.setFillsViewportHeight(true);
        
        if (tableModel.getPreferredSize().getHeight() < js.getPreferredSize().getHeight()){
            tableModel.setPreferredSize(js.getPreferredSize());
        }
        
        tableModel.setEnabled(false);
        radioButton1.setSelected(false);
        
        js.setVisible(true);

        
        radioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                
                /**
                *@ author Kirchsteiger
                */
                Collections.sort(myAufenthalte, new Comparator<Aufenthalte>(){
                  
                    public int compare(Aufenthalte a1, Aufenthalte a2){
                        int c = extractInt(a1.getAz()) - extractInt(a2.getAz());
                        return c;
                    }
                    int extractInt(String s) {
                        return s.isEmpty() ? 0 : Integer.parseInt(s);
                    }
                });
                
                if (data.length >= refreshData.length){
                    data = initAufenthalte(myAufenthalte);
                    tableModel.setModel(new DefaultTableModel(data, columns));
                
                }else if (data.length < refreshData.length){
                    refreshData = initAufenthalte(myAufenthalte);
                    tableModel.setModel(new DefaultTableModel(refreshData, columns));
                
                }
                
               radioButton2.setSelected(false);
            }
        });
        
        /**
         * @ author Erdem
         */
        radioButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                /**
                 * @author Kirchsteiger
                 */
                
                Collections.sort(myAufenthalte, new Comparator<Aufenthalte> () {
                
                    public int compare(Aufenthalte a1, Aufenthalte a2){
                        int c = a1.getNachname().compareTo(a2.getNachname());
                        
                        if (c == 0){
                            c = a1.getVorname().compareTo(a2.getVorname());
                        } 
                        
                        if (c == 0) {
                           c = a1.getAufdat().compareTo(a2.getAufdat());
                        }
                        return c;
                    } 
                });
                if (data.length >= refreshData.length){
                    data = initAufenthalte(myAufenthalte);
                    tableModel.setModel(new DefaultTableModel(data, columns));
                
                }else if (data.length < refreshData.length){
                    refreshData = initAufenthalte(myAufenthalte);
                    tableModel.setModel(new DefaultTableModel(refreshData, columns));
                
                }
                radioButton1.setSelected(false);
            }
        });
        
        /**
         * @ author Erdem
         */
        
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                /**
                 * @ author Kirchsteiger
                 */
                myAufenthalte = Datenbank.readAufenthalteDB();
                refreshData = initAufenthalte(myAufenthalte);
                
                tableModel.setModel(new DefaultTableModel(refreshData, columns)); 
                radioButton1.setSelected(false);
                radioButton1.setSelected(false);
            }
        });
        
        /**
         * @author Erdem
         */
        
        panel.add(label); //Components Added using Flow Layout
        panel.add(radioButton1);
        panel.add(label2);
        panel.add(radioButton2);
        panel.add(label3);

        panel.add(refresh);
        panel2.add(new JScrollPane(tableModel));
        
        //Adding Components to the frame
        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, panel2);
        frame.setVisible(true);
    }
}