
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
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
public class Patienten {
    
    private int id;
    private String vorname;
    private String nachname;
    private String az;
    private LocalDate aufdat; 
    public static Connection con;
    
    public Patienten() {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.az = az;
        this.aufdat = aufdat;
    }

    public int getId() {
        return id;
    }
    
    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getAz() {
        return az;
    }

    public LocalDate getAufdat() {
        return aufdat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setAz(String az) {
        this.az = az;
    }

    public void setAufdat(LocalDate aufdat) {
        this.aufdat = aufdat;
    }
    
    @Override
    public String toString (){
        String ausgabe =   "\n" + 
                "Patient: " + id + 
                " | Name: " + vorname + " " + nachname +
                " | AZ: " + az + " | Aufnahmedatum: " + aufdat;  
        
        return ausgabe;
    }
}
