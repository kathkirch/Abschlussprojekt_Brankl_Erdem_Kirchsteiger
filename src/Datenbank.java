

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kirchsteiger
 */
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.format.DateTimeFormatter;


public class Datenbank {
    
    private static Connection con; 
    
    public static void dbVerbinden(){
        
        con = null; 
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("JDBC Treiber geladen");
            con = DriverManager.getConnection("jdbc:mysql://10.25.2.145:3306/branklth19?user=branklth19&password=geb19&serverTimezone=CET");
            System.out.println("Verbindung zur Datenbank hergestellt");
        
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
            System.out.println("Verbindung zum Server fehlgeschlagen");
            System.exit(1);
        }
    } // verwendet in class App
    
    public static void dbSchliessen() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    } // verwendet in class APP
    
    
    
    public static List <Patienten> patqueueAuslesen() { 
        
        List <Patienten> patListe = new ArrayList<>();
        ResultSet rs = null;
        String dbTbl = "patqueue";
        Statement stmt = null;
        
        try{
            stmt = con.createStatement();
            rs = stmt. executeQuery("SELECT * FROM " + dbTbl );

            while(rs.next()){
                
                // Werte von Tabelle lesen und in Variablen speichern
                int dbid = rs.getInt("id");
                String dbVname = rs.getString("Vorname");
                String dbNname = rs.getString("Nachname");
                String dbAz = rs.getString("AZ");
                Date dbAufdat = rs.getDate("Aufdat");

                // neues PatientenObjekt erzeugen mit Werten von Tabelle
                Patienten p = new Patienten();
                p.setId(dbid);
                p.setVorname(dbVname);
                p.setNachname(dbNname);
                p.setAz(dbAz);
                p.setAufdat(dbAufdat.toLocalDate());

                //PatientenObjekt in patListe speichern
                patListe.add(p);
            }
        }catch(SQLException ex) {
            System.out.println(ex);
        }
        return patListe; 
    } // verwendet in class RunDB
    
    public static List <Aufenthalte> readAufenthalteDB () {
        
        Statement stmt = null;
        ResultSet rs = null;
        List <Aufenthalte> aufenthalteDB = new ArrayList<>();
        
        try {
            stmt = con.createStatement();
            String dbTbl = "aufenthalte";
            rs = stmt.executeQuery("SELECT * FROM " + dbTbl);
            
            while(rs.next()){
                int aID = rs.getInt("ID");
                String aModdat = rs.getString("Moddat");
                String aVname = rs.getString("Vorname");
                String aNname = rs.getString("Nachname");
                String aAz = rs.getString("AZ");
                Date aAufdat = rs.getDate("Aufdat");
                
                Aufenthalte a = new Aufenthalte();
                a.setId(aID);
                a.setModdat(aModdat);
                a.setVorname(aVname);
                a.setNachname(aNname);
                a.setAz(aAz);
                a.setAufdat(aAufdat.toLocalDate());
                
                aufenthalteDB.add(a);
            }
        }catch(SQLException ex){
            System.out.println("Tabelle 'Aufenthalte' aus DB konnte nicht gelesen werden!");
        } finally {
            if (stmt != null) {
                try { 
                    stmt.close();
                } catch (SQLException ex){
                    System.out.println(ex);
                }
            }  
        }
        return aufenthalteDB;
    }  //verwendet in GUI2
     
    public static List <Aufenthalte> patientToAufenthalt (List<Patienten> patListe) { //verwendet deletePatQueue();
        
        //erstellt neue Liste vom Typ Aufenthalte
        List <Aufenthalte> aufenthaltListe = new ArrayList<>();
        
        //for-Schleife die jedes PatientenObjekt in 'meinePat-Liste' ausliest
        
        if(!patListe.isEmpty()){
            for (Patienten p : patListe){
                
                //Werte von Patient lesen und in neue Variable speichern
                int a_id = p.getId();
                String a_Vname = p.getVorname();
                String a_Nname = p.getNachname();
                String a_az = p.getAz();
                LocalDate a_Aufdat = p.getAufdat();

                //erzeugt neues AufenthaltObjekt
                Aufenthalte a = new Aufenthalte();

                //Methode um Moddat zu erhalten
                String a_moddat = a.createModdat();

                //Werte in AufenthaltObjekt speichern
                a.setId(a_id);
                a.setVorname(a_Vname);
                a.setNachname(a_Nname);
                a.setAz(a_az);
                a.setAufdat(a_Aufdat);
                a.setModdat(a_moddat);

                //AufenthaltObjekt zur AufenthaltListe hinzufügen
                aufenthaltListe.add(a);
            }
            deletePatQueue(patListe);
            patListe.clear();
        }        
        return aufenthaltListe;
    } //verwndet in class RunDB
    
    public static List <Patlog> patientToAufenthaltDB (List<Aufenthalte> aufenthaltListe ){ //verwendet updateAufenthalte
        
        Statement stmt = null; 
        List <Patlog> myPatlogs = new ArrayList<>();
       
        // durch List <Aufenthalte> aufenthaltListe iterieren, jedes Aufenthalte-Objekt als ein Patlog Objekt speichern
        for (Aufenthalte a : aufenthaltListe){
            Patlog p_log = new Patlog();
            try{
                stmt = con.createStatement();
                String s = "insert into aufenthalte (Moddat, Vorname, Nachname, "
                            + "AZ, Aufdat) values ('" + a.getModdat() + "', '" +
                        a.getVorname() + "', '" + a.getNachname() + "', '" + 
                        a.getAz() + "', '" + a.getAufdat() + "');";
                stmt.executeUpdate(s);
                System.out.println(s);
                p_log.setAktion(PatlogAktion.insert);
            
            //Fehlerbehebung falls AZ schon in DB vorhanden
            }catch(SQLIntegrityConstraintViolationException icve  ){
                System.out.println("Eintrag mit AZ bereits vorhanden!" + 
                                    " Daten werden akutalisiert!");
                
                //Methode um Aufenthalte zu aktualisieren wird aufgerufen
                updateAufenthalte(aufenthaltListe); 
                
                p_log.setAktion(PatlogAktion.update);
                 
            }catch(SQLException ex){
                System.out.println(ex); 
            }finally {
                if (stmt != null) {
                    try { 
                        stmt.close();
                    } catch (SQLException ex){
                        System.out.println(ex);
                    }
                }     
            }
            p_log.setAz(a.getAz());
            p_log.setModdat(a.getModdat());
            //Patlog Element der List <Patlog> myPatlogs hinzufügen
            myPatlogs.add(p_log);
        }
        return myPatlogs;
    } //verwendet in class RunDB
    
    public static void updateAufenthalte (List <Aufenthalte> aufenthaltListe) {
         
        Statement stmt = null;
        ResultSet rs = null;
        
        //iteriert durch List <Aufenthalte> aufenthaltListe
        for (Aufenthalte a : aufenthaltListe){
            try{
                stmt = con.createStatement();
                String dbTbl = "aufenthalte";
                rs = stmt.executeQuery("SELECT * FROM " + dbTbl + " WHERE AZ= '"
                                               + a.getAz() + "'" );
                
                while(rs.next()){   
                    String s = "update " + dbTbl +" set Moddat = ?, Vorname = ?, "
                                    + "Nachname = ?, Aufdat = ? where AZ =?";
                    PreparedStatement prepStat = con.prepareStatement(s);
                    prepStat.setString(1, a.getModdat());
                    prepStat.setString(2, a.getVorname());
                    prepStat.setString(3, a.getNachname());
                    prepStat.setString(4, a.getAufdat().format(DateTimeFormatter.ISO_DATE));
                    prepStat.setString(5, a.getAz());
                    System.out.println(s);
                    prepStat.executeUpdate();
                }
            }catch(SQLException ex){
                System.out.println("Update hat nicht funktioniert");
            } finally {
                if (stmt != null) {
                    try { 
                        stmt.close();
                    } catch (SQLException ex){
                        System.out.println(ex);
                    }
                }  
            } 
        }   
    } //verwendet in patientToAufenthaltDB();
    
    public static void insertPatlogDB (List <Patlog> myPatlogs){
        
        Statement stmt = null; 
        
        //iteriert durch List<Patlog> myPatlogs speichert die Attribute in einen String
        //danach werden die Strings in der DB eingefügt 
        for (Patlog pl : myPatlogs) {
            String plDateDB = pl.createModdat();
            String plAktionDB = pl.getAktion();
            String plAzDB = pl.getAz();
            
            try {
                stmt = con.createStatement();
                String s = "insert into patlog (Zeitpunkt, Aktion, AZ) values "
                        + "('" + plDateDB +  "', '" + plAktionDB +
                         "', '" + plAzDB + "');";
                System.out.println(s);
                stmt.executeUpdate(s);
            }catch (SQLException ex){
                System.out.println(ex);
                System.out.println("Eintrag in 'patlog' hat nicht funktioniert");
            }finally {
                if (stmt != null) {
                    try { 
                        stmt.close();
                    } catch (SQLException ex){
                        System.out.println(ex);
                    }
                }  
            }
        }
    } //Verwendet in class RunDB
    
    public static void deletePatQueue (List <Patienten> meinePat){
        
        Statement stmt = null;
        String dbTbl = "patqueue";
        
        try {
            stmt = con.createStatement();
            String delString = "DELETE FROM " + dbTbl + ";";
            System.out.println(delString);
            int dbresponse = stmt.executeUpdate(delString);
            System.out.println("Löschen: " + dbresponse);   
        }catch(SQLException ex ){
            System.out.println(ex);
        }
    }  //verwendet in PatientToAufenthalt();
    
    public static List <Patlog> readPatlog(){

        List <Patlog> patlogsDB = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;

        try{
            String dbTbl = "patlog";
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM " + dbTbl);

            while (rs.next()){
                int plID = rs.getInt("ID");
                String plZp = rs.getString("Zeitpunkt");
                String plAktion = rs.getString("Aktion");
                String plAZ = rs.getString("AZ");

                Patlog patlog = new Patlog();

                patlog.setId(plID);
                patlog.setModdat(plZp);
                patlog.setAktion(plAktion);
                patlog.setAz(plAZ);

                patlogsDB.add(patlog);
            }    
        }catch(SQLException ex){
            System.out.println(ex);
            System.out.println("Tabelle 'Patlogs' in DB konnte nicht gelesen werden!");
        }finally {
            if (stmt != null) {
                try { 
                    stmt.close();
                } catch (SQLException ex){
                    System.out.println(ex);
                }
            }  
        }
        return patlogsDB;
    } //verwendet in class GUI1
    
    /**
     * @ author Brankl
    */
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://10.25.2.145:3306/branklth19?user=branklth19&password=geb19&serverTimezone=CET");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return con;
    }
}    
        
        

