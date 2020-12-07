

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kirchsteiger
 */


import java.util.Date;
import java.text.SimpleDateFormat; 

public class Aufenthalte extends Patienten {
    
    private String moddat;
    
    public Aufenthalte() {
        this.moddat = moddat;
    }
    
    public String createModdat() {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);
        return currentTime;
    }
    
    public String getModdat() {
        return moddat;
    }

    public void setModdat(String moddat) {
        this.moddat = moddat;
    }
    
    @Override
    public String toString(){
        return super.toString() + " | letzte Änderung: " + moddat; 
    }
  
}
