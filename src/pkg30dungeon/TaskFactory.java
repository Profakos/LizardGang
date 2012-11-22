/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg30dungeon;

import java.util.List;

/**
 *
 * @author Akos
 */
public class TaskFactory {
     /*
     * 
     * Assigns a new ID to a newly made Entity. 
     * 
     */
    
     static private int  nextid;
     
     TaskFactory(){ 
         nextid = 0;  
     }
     
     TaskFactory(int n){ 
         nextid = n;  
     }
     
     public static Task newCompositeTask(int id, int ty, int tx, TaskEnum tasktype)
        {
          if(id==-1){id = TaskFactory.nextid;   TaskFactory.nextid += 1;   }  
            
          CompositeTask newComposite =  new CompositeTask(id, ty, tx, tasktype);
          
          newComposite.tasks.add(newPrimitiveTask(ty,tx, TaskEnum.walking));
          newComposite.tasks.add(newPrimitiveTask(ty,tx, tasktype)); 
          
        
          
          return newComposite;
        }
     
     
     public static Task newPrimitiveTask(int ty, int tx, TaskEnum tasktype)
        {
          PrimitiveTask newtask =  new PrimitiveTask(TaskFactory.nextid, ty, tx, tasktype); 
          
          switch(tasktype)
          {
              case digging: newtask.worldObjectRecquire.add("Digbox");
          
          }
          
          return newtask;
        }
     
     public static CompositeTask loadCompTask(List<String> tLines)
     {
     String[] split = tLines.get(0).split("\t");
     
     int id = Integer.valueOf(split[0]);
     int ty = Integer.valueOf(split[2]);
     int tx = Integer.valueOf(split[3]);
     TaskEnum ttype = TaskEnum.valueOf(split[1]);
     int workerid =  Integer.valueOf(split[4]);
     
     CompositeTask ntask =  (CompositeTask) newCompositeTask(id, ty, tx, ttype); 
     
     ntask.setWorkerid(workerid);
     
     if(id>=nextid) nextid = id+1;
     
     return ntask;
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
        TaskFactory.nextid = nextid;
    }
     
}
