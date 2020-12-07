

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kirchsteiger
 */
public class Patlog extends Aufenthalte implements PatlogAktion {
    
    String aktion;
    
    public Patlog () {
        this.aktion = aktion;  
    }

    public String getAktion() {
        return aktion;
    }

    public void setAktion(String aktion) {
        this.aktion = aktion;
    }
    
    @Override
    public String toString(){
        return " \n" + "ID: " + super.getId() + "| Zeitpunkt: " + super.getModdat()
                + " | Aktion: " + aktion + " | AZ: " + super.getAz();
    
    }
    
}
