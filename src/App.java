


import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Katharina
 */
public class App {

    /**
     * @param args the command line arguments
     */
   
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        /////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////Programm 1 Patqueue Eingabe/////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////////////////////
        
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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PatqueueEingabe().setVisible(true);
            }
        });
        
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////Programm 2a Datenbank ein- und auslesen///////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
       
        
        Datenbank.dbVerbinden();
        
        Thread t = new RunDB();
        t.start();
        
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////Programm 2b GUI Masken//////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        Datenbank.dbVerbinden();
        Thread g1 = new GUI1();
        Thread g2 = new GUI2();
        g1.start();
        g2.start();

        //myDB.dbSchliessen();
    } 
}
