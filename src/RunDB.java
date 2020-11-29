
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Katharina
 */
public class RunDB extends Thread{
    
    
     @Override
     public void run() {
        
        Datenbank.dbVerbinden(); 
        
        System.out.println("r u doing???");
        List <Patienten> patienten = new ArrayList<>();
        
        try{
           // do{
                System.out.println("r u doing now??");
                Thread.sleep(30);
                patienten = Datenbank.patqueueAuslesen();
                System.out.println(patienten);
                
                List <Aufenthalte> aufenthaltListe = Datenbank.patientToAufenthalt(patienten);
                System.out.println("Patienten in Patqueue" + patienten);

                List <Aufenthalte> myDBaufenthalte = Datenbank.readAufenthalteDB();
                //System.out.println("Meine Aufenthalte aus der DB" + myDBaufenthalte);

                List <Patlog> meinePatlogListe = Datenbank.patientToAufenthaltDB(aufenthaltListe);
                Datenbank.insertPatlogDB(meinePatlogListe);

                //List <Patlog> myPatlogDB = myDB.readPatlog();
                //System.out.println("Meine Patlogs aus der DB: " + myPatlogDB);  
                
            //}while (!patienten.isEmpty()); 
     
        }catch(Exception exe){
            System.out.println(exe);
        }
        
        
    }  
}
