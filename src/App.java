

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
        
        Datenbank myDB = new Datenbank();
        
        myDB.dbVerbinden();
        
        List <Patienten> patienten = myDB.patqueueAuslesen();
        List <Aufenthalte> aufenthaltListe = myDB.patientToAufenthalt(patienten);
        System.out.println("Patienten in Patqueue" + patienten);
        
        List <Aufenthalte> myDBaufenthalte = myDB.readAufenthalteDB();
        System.out.println("Meine Aufenthalte aus der DB" + myDBaufenthalte);
        
        List <Patlog> meinePatlogListe = myDB.patientToAufenthaltDB(aufenthaltListe);
        myDB.insertPatlogDB(meinePatlogListe);
        
        List <Patlog> myPatlogDB = myDB.readPatlog();
        System.out.println("Meine Patlogs aus der DB: " + myPatlogDB);
        
        myDB.dbSchliessen();
        
        ///////////////////////////////////////////////
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI2();
                new GUI1();
            }
        });
        
    } 
}
