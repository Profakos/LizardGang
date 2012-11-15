/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg30dungeon;

/**
 *
 * @author Akos
 */
public class ItemFactory {
    
    static private int  nextid;
     
     ItemFactory(){ 
         nextid = 0;  
     }
     
     ItemFactory(int n){ 
         nextid = n;  
     }
     
  
     static public Item newItem(int ty, int tx, Tile t, String name)
        {
          Item newitem;
          
          if(t.isExplored()==false || t.isWalkable()==false) return null;
          
          newitem = new Item(ty, tx, ItemFactory.nextid, name);
          
          ItemFactory.nextid+=1;
          return newitem;
        }
     
     static public Item loadItem(int ty, int tx, int id, String name)
        {
          Item newitem  = new Item(ty, tx, id, name);
          
          if(nextid<=id)nextid=id+1;
          
          return newitem;
        }

    /**
     * @return the nextid
     */
     
    public int getNextid() {
        return nextid;
    }

    /**
     * @param nextid the nextid to set
     */
    
    public void setNextid(int nextid) {
        ItemFactory.nextid = nextid;
    }
     
    
    
}
