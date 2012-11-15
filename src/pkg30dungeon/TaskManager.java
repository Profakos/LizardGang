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
public class TaskManager {
     private List<Task> tasks;
     private World world;
     
     TaskManager(World world)
     {
     tasks = new ArrayList<>();
     this.world = world;
     }
     
     void addTask(Task task)
     {
        getTasks().add(task);
     }
     
     void removeTask(Task task)
     {
     if(getTasks().contains(task))    
        getTasks().remove(task);
     }

    String getJobs() {
        String s = "";
        if(getTasks().isEmpty()) return s;
        
        for(int i = 0; i<getTasks().size(); i++)
        {
        s += getTasks().get(i).getTasktype();
        
        int wid = getTasks().get(i).getWorkerid();
        
        if(wid!=-1)
                {
        s +=    getWorld().getBeastcollection().getKeyID(wid).getName(); 
                }
        
        
        }
        
        return s;
    }

    void assignTasks(Creature creature){
          
        if(creature.getTask()==null && creature.getStuntime()==0) //if creature is not busy
        {
            
            int foundid = -1;
           
            if(getTasks().isEmpty()) return; //there is nothing to do, return
            
            int util = -1000;
             
            findpath: for(int i = 0; i<getTasks().size(); i++)//examine all the tasks
            {
                 
                if(getTasks().get(i).getWorkerid()==-1 && getTasks().get(i).canPerform(creature, world)) //if nobody is doing the task
                {
                 int tutil = getTasks().get(i).getUtility(creature, world);   
                   if(tutil>util)
                   {
                   util = tutil;    
                   foundid =  getTasks().get(i).getTaskid(); 
                   }
                }
        
            }    
            
            if(foundid != -1)
            {
            for(int i = 0; i<getTasks().size(); i++)
                {
                    if(getTasks().get(i).getTaskid()==foundid)
                    {
                        getTasks().get(i).setWorkerid(creature.getNumid());
            creature.setTask(getTasks().get(i));
             
            return;
                    }
                }
            }
        
        }
        
    }

    private Path findTaskPath(Creature creature, int i, int yi, int xi) {
        Path path;
        path = getWorld().getPathfinder().findPath(creature.getCy(), creature.getCx(), 
            getTasks().get(i).getY()+yi, getTasks().get(i).getX()+xi);
        if(getWorld().getMap().isBlockDiag(getTasks().get(i).getY(), getTasks().get(i).getX(), getTasks().get(i).getY()+yi, getTasks().get(i).getX()+xi)==true) path=null;
        return path;
    }
    
    
    

    void cancelTask(Task task) {
        if(task==null) return;
        getTasks().remove(task);
        task.cancel(getWorld());
        getTasks().add(task); 
    }

    void undesignateTask(int id, CreatureCollection beasts){
        
        for(int i=0; i<getTasks().size(); i++)
        {
        if( getTasks().get(i).getTaskid()==id)
        {
         int wi = getTasks().get(i).getWorkerid();
         /*incase someone was already working on it
          * 
          */
         if(beasts.hasKey(wi))
         {
         beasts.getKeyID(wi).freeJob(getWorld());
         }
         else
         {
                    getTasks().remove(i);
         }
             
        return;
        }
        
        }
        
    }

    void saveTasks(List<String> sLine) {
        for(int i = 0; i<this.getTasks().size(); i++)
        {
            getTasks().get(i).saveTask(sLine);
        }
        sLine.add("#ENDBLOCK#");
    }

    void loadTasks(List<String> taskLines) {
        
        if(taskLines.isEmpty()) return;
        
        List<String> tLines = new ArrayList<>();
         
        
        for(int i = 0; i<taskLines.size(); i++)
            {
            
                tLines.add(taskLines.get(i));
                
                if(taskLines.get(i).equals("#ENDCOMP"))
                {
               Task task = TaskFactory.loadCompTask(tLines);
                addTask(task);
                tLines.clear();
                }
                
            }
    }

    /**
     * @return the tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * @param tasks the tasks to set
     */
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * @return the world
     */
    public World getWorld() {
        return world;
    }

    /**
     * @param world the world to set
     */
    public void setWorld(World world) {
        this.world = world;
    }

    Task getTaskByID(int tid) {
        if(this.tasks.isEmpty()==false)
        {
        
            for(int i = 0; i<tasks.size();i++)
            {
            if(tasks.get(i).getTaskid()==tid) return tasks.get(i); 
            }
        
        
        }
        
        return null;
    }
     
}
