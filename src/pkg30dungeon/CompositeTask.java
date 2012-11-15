/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg30dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Akos
 */
public class CompositeTask extends Task {

    List<Task> tasks;
    int iterator;
    
     CompositeTask(int id, int y, int x, TaskEnum tasktype)
    {
    super( id, y, x,tasktype);
    tasks = new ArrayList<>();
    iterator = 0;
    }
     
    @Override
    boolean canPerform(Creature cc, World w) {
       if(tasks.isEmpty()) return false;
       else
        for(int i= 0; i<tasks.size(); i++)
        { if(tasks.get(i).canPerform(cc, w)==false) 
            return false; }
       
       return true;
    }

    @Override
    void performTask(World world, GangmemberCreature aThis) {
        if(!tasks.isEmpty())
        {
         tasks.get(iterator).performTask(world, aThis);
        
         if(tasks.get(iterator).done)
         {
             iterator++;
             if(iterator>=tasks.size())
             aThis.freeJob(world);
         }
        }
        
    }

    @Override
    boolean isArrived(GangmemberCreature aThis, World w) {
       if(tasks.isEmpty()) return false;
       else
         return tasks.get(iterator).isArrived(aThis, w);
    }

    @Override
    void cancel(World l) {
        
        if(!tasks.isEmpty()) 
         tasks.get(iterator).cancel(l);
    }

    @Override
    void saveSpecificTask(List<String> sLine) {
        
         sLine.add("#STARTCOMP");
         if(!tasks.isEmpty()) 
         {
           for(int i = 0; i<tasks.size(); i++)
           { sLine.add("@"); tasks.get(i).saveTask(sLine);}
         }
         sLine.add("#ENDCOMP");
    }

    @Override
    int getUtility(Creature cc, World w) {
       
        
        if(tasks.isEmpty()) 
         {
         return -1000;
         }
         int util = 0;
         
        for(int i = 0; i<tasks.size(); i++)
        {
        util+=tasks.get(i).getUtility(cc, w);
        }
        
        return util;
    }
    
}
