
import javax.swing.SwingUtilities;





/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kirchsteiger
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
        
        Runnable runnable = new PatqueueEingabe();
        Thread thread = new Thread(runnable);
        thread.start();
        
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////Programm 2a Datenbank ein- und auslesen///////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        Datenbank.dbVerbinden();
        //Datenbank.deleteDB();
        
        
        Thread t = new RunDB();
        t.start();
        
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////Programm 2b GUI Masken//////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        /**
         * @ author Erdem
         */
        
        //Thread g1 = new GUI1();
        //Thread g2 = new GUI2();
        //g1.start();
        //g2.start();
        
        
        SwingUtilities.invokeLater(new Runnable() {
              @Override
              public void run() {
                new GUI1();
                new GUI2();
              }
        });
    } 
}
