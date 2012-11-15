package pkg30dungeon;

import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Akos
 */
public class CreatureFactory {
    
    /*
     * 
     * Assigns a new ID to a newly made Entity. 
     * 
     */
    
     static private int  nextid;

    static Creature loadCreature(List<String> subList, TaskManager taskmanager) {
       String[] tsplit = subList.get(0).split("\t");
       
       String name = tsplit[0];
       int numid = Integer.valueOf(tsplit[1]);
       int ty = Integer.valueOf(tsplit[2]);
       int tx = Integer.valueOf(tsplit[3]);
       int gender = Integer.valueOf(tsplit[4]);
       boolean canwork = Boolean.valueOf(tsplit[5]);
       int stuntime = Integer.valueOf(tsplit[6]);
       boolean alive = Boolean.valueOf(tsplit[7]);
       
       tsplit = subList.get(1).split("\t"); 
       
       AIenum type = AIenum.valueOf(tsplit[0]);
       
       
       
       Creature newent = null;
         switch(type)
          {
              case gangmember: newent =  new GangmemberCreature(numid, ty, tx); 
                  break;
              case annoybug: newent =  new AnnoybugCreature(numid, ty, tx); 
          }
         
         
         newent.setGender(gender);
         newent.setCanwork(canwork);
         newent.setStuntime(stuntime);
         newent.setAlive(alive);
         
         newent.loadSpecific(tsplit); 
         newent.pathfollow.loadPath(subList.get(3));
       
       if("null".equals(subList.get(2))==false)
       {
       int tid = Integer.valueOf(subList.get(2)); 
       newent.setTask(taskmanager.getTaskByID(tid));
       }
         
         
         if(numid>=nextid) nextid = numid+1;
         
         return newent;
    }
     
     CreatureFactory(){ 
         nextid = 0;  
     }
     
     CreatureFactory(int n){ 
         nextid = n;  
     }
     
  
     static public Creature newEntity(int ty, int tx, AIenum type)
        {
          Creature newent = null;
          switch(type)
          {
              case gangmember: newent =  new GangmemberCreature(CreatureFactory.nextid, ty, tx); 
                  break;
              case annoybug: newent =  new AnnoybugCreature(CreatureFactory.nextid, ty, tx); 
          }          
          
          
          CreatureFactory.nextid+=1;
          return newent;
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
        CreatureFactory.nextid = nextid;
    }
     
    
      
}
