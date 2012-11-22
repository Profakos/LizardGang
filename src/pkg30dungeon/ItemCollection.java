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
public class ItemCollection {
      
    HashMap<Integer, Item> things;
    
    ItemCollection()
    { 
        things = new HashMap<>(); 
    }
      
    Item getKeyID(int i){ return things.get(i);}
    
    boolean hasKey(int i){
        if(things.containsKey(i)) return true; else return false;
    }

    void saveItems(List<String> sLine) {
             for(Item item : things.values())
             {
              item.saveItem(sLine);
             }
        sLine.add("#ENDBLOCK#");
    }

    void loadItems(List<String> itemLines){
        
        if(itemLines.isEmpty()) return;
        
        for(int i =0; i<itemLines.size(); i++)
        {
        String[] split = itemLines.get(i).split("\t");    
        Item newitem = ItemFactory.loadItem(Integer.valueOf(split[2]), Integer.valueOf(split[3]), Integer.valueOf(split[0]), split[1]);
        things.put(newitem.getId(), newitem);
        }
        
    }

    boolean isRecqExist(List<String> worldObjectRecquire) {
       if(worldObjectRecquire.isEmpty()) return true;
       
       outer: for(String name: worldObjectRecquire)
       {
           for(Item item: things.values())
            {
            if(item.getName().equals(name)) continue outer; 
            }
        return false;
       }
       
       return true;
    }
}
