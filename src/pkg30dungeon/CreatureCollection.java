/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg30dungeon;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Akos
 */
public class CreatureCollection {
    /*
     * Wrapper class
     * Stores entities in a hashmap
     * Performs cleanup duty on the dead.
     */
    
    
    HashMap<Integer, Creature> beasts;
    
    CreatureCollection()
    { 
        beasts = new HashMap<>(); 
    }
    
     
    
    Creature getKeyID(int i){ return beasts.get(i);}
    
    boolean hasKey(int i){
        if(beasts.containsKey(i)) return true; else return false;
    }

    void saveCreatures(List<String> sLine) {
        
         for(Creature beast : beasts.values())
             {
              beast.saveCreature(sLine);
             }
        sLine.add("#ENDBLOCK#");
        
    }
    
    void loadCreatures(List<String> sLine, TaskManager taskmanager)
    {
    
        for(int i = 0; i<sLine.size(); i+=4)
        {
        Creature tcreature = CreatureFactory.loadCreature(sLine.subList(i, i+4), taskmanager);
        
        this.beasts.put(tcreature.numid, tcreature);
        }
    
    }
}
