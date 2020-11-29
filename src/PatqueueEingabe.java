/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import javax.swing.*;


public class PatqueueEingabe extends javax.swing.JFrame  {

    private static Connection con;
    
    public PatqueueEingabe() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton_AddPatient = new javax.swing.JButton();
        jLabel_Vname = new javax.swing.JLabel();
        jLabel_Nname = new javax.swing.JLabel();
        jLabel_AZ = new javax.swing.JLabel();
        jLabel_Aufdat = new javax.swing.JLabel();
        jTextField_Vname = new javax.swing.JTextField();
        jTextField_AZ = new javax.swing.JTextField();
        jTextField_Nname = new javax.swing.JTextField();
        jComboBox_Aufdat_dd = new javax.swing.JComboBox<>();
        jComboBox_Aufdat_mm = new javax.swing.JComboBox<>();
        jComboBox_Aufdat_yyyy = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jButton_AddPatient.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jButton_AddPatient.setText("Patient hinzufügen");
        jButton_AddPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AddPatientActionPerformed(evt);
            }
        });

        jLabel_Vname.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jLabel_Vname.setText("Vorname:");

        jLabel_Nname.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jLabel_Nname.setText("Nachname:");

        jLabel_AZ.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jLabel_AZ.setText("AZ (Aufnahmezahl):");

        jLabel_Aufdat.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jLabel_Aufdat.setText("Aufnahmedatum:");

        jTextField_Vname.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jTextField_Vname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_VnameActionPerformed(evt);
            }
        });

        jTextField_AZ.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N

        jTextField_Nname.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N

        jComboBox_Aufdat_dd.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jComboBox_Aufdat_dd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jComboBox_Aufdat_mm.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jComboBox_Aufdat_mm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jComboBox_Aufdat_yyyy.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jComboBox_Aufdat_yyyy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2021", "2022" }));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel1.setText("Patientenaufnahme");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_AddPatient)
                .addGap(33, 33, 33))
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_Vname, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_Nname, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_AZ, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_Aufdat, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jComboBox_Aufdat_dd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox_Aufdat_mm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox_Aufdat_yyyy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField_AZ)
                            .addComponent(jTextField_Nname)
                            .addComponent(jTextField_Vname))))
                .addContainerGap(218, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton_AddPatient, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_Vname, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_Vname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_Nname)
                    .addComponent(jTextField_Nname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_AZ, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_AZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_Aufdat)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox_Aufdat_dd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox_Aufdat_mm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox_Aufdat_yyyy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_VnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_VnameActionPerformed
       
    }//GEN-LAST:event_jTextField_VnameActionPerformed
 

    private static void dbVerbinden2() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Jdbc Treiber geladen");
            con = DriverManager.getConnection("jdbc:mysql://10.25.2.145:3306/branklth19?user=branklth19&password=geb19&serverTimezone=CET");
            System.out.println("Verbindung zur DB hergestellt");
        }catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
            System.exit(1);
        }
    }
    
    private void initPatqueue () {
        
        dbVerbinden2();
        
        try { 
        
            Statement stmt = con.createStatement();

            String Aufdat_Tag = String.valueOf(jComboBox_Aufdat_dd.getSelectedItem());
            String Aufdat_Monat = String.valueOf(jComboBox_Aufdat_mm.getSelectedItem());
            String Aufdat_Jahr = String.valueOf(jComboBox_Aufdat_yyyy.getSelectedItem());
            String Aufdat = Aufdat_Jahr + "-" + Aufdat_Monat + "-" + Aufdat_Tag;

            System.out.println(Aufdat);

            String patqueueEntry = "INSERT INTO patqueue (Vorname, Nachname, AZ, Aufdat) VALUES "
                    + "('"+jTextField_Vname.getText()+"',"
                    + " '"+jTextField_Nname.getText()+"',"
                    + " '"+jTextField_AZ.getText()+"',"
                    + " '"+Aufdat+"')";
        
        stmt.execute(patqueueEntry);
        
        JOptionPane.showMessageDialog (null, "Der Patient wurde hinzugefügt.");
        
        jTextField_Vname.setText(null);
        jTextField_Nname.setText(null);
        jTextField_AZ.setText(null);
       
       }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    
    }

    private void jButton_AddPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AddPatientActionPerformed

        initPatqueue();
    }//GEN-LAST:event_jButton_AddPatientActionPerformed

    
    /*
    public static void main(String args[]) {
    
        //dbVerbinden2();
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PatqueueEingabe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PatqueueEingabe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PatqueueEingabe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PatqueueEingabe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PatqueueEingabe().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_AddPatient;
    private javax.swing.JComboBox<String> jComboBox_Aufdat_dd;
    private javax.swing.JComboBox<String> jComboBox_Aufdat_mm;
    private javax.swing.JComboBox<String> jComboBox_Aufdat_yyyy;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_AZ;
    private javax.swing.JLabel jLabel_Aufdat;
    private javax.swing.JLabel jLabel_Nname;
    private javax.swing.JLabel jLabel_Vname;
    private javax.swing.JTextField jTextField_AZ;
    private javax.swing.JTextField jTextField_Nname;
    private javax.swing.JTextField jTextField_Vname;
    // End of variables declaration//GEN-END:variables

 




}


