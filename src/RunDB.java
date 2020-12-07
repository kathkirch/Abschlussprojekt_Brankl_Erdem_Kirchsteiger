
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kirchsteiger
 */
public class RunDB extends Thread{
    
    
     @Override
     public void run() {
        
        List <Patienten> patienten = new ArrayList<>();
        
        try{
            do{
                Thread.sleep(15000);
                patienten = Datenbank.patqueueAuslesen();
                
                List <Aufenthalte> aufenthaltListe = Datenbank.patientToAufenthalt(patienten);
                System.out.println("Patienten in Patqueue" + patienten);

                List <Patlog> meinePatlogListe = Datenbank.patientToAufenthaltDB(aufenthaltListe);
                Datenbank.insertPatlogDB(meinePatlogListe);  
            }while (true);
            
        }catch(Exception exe){
            System.out.println(exe);
        }
    }  
}
