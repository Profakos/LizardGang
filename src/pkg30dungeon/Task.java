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
abstract public class Task {
    
    private int taskid;
    private TaskEnum tasktype;
    private int y;
    private int x;
    private int workerid;
    
     boolean done;  
    
    Task(int id, int y, int x, TaskEnum tasktype)
    {
    this.taskid = id;
    this.y = y;
    this.x = x;
    this.tasktype = tasktype; //digging 
    
    workerid = -1;
    
    done = false;
    }
    
    
   abstract boolean canPerform(Creature cc, World w);
    
   abstract int getUtility(Creature cc, World w);
    /**
     * @return the taskid
     */
    public int getTaskid() {
        return taskid;
    }

    /**
     * @param taskid the taskid to set
     */
    public void setTaskid(int taskid) {
        this.taskid = taskid;
    }


    /**
     * @return the tasktype
     */
    public TaskEnum getTasktype() {
        return tasktype;
    }

    /**
     * @param tasktype the tasktype to set
     */
    public void setTasktype(TaskEnum tasktype) {
        this.tasktype = tasktype;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the workerid
     */
    public int getWorkerid() {
        return workerid;
    }

    /**
     * @param workerid the workerid to set
     */
    public void setWorkerid(int workerid) {
        this.workerid = workerid;
    }

    abstract void performTask(World l, GangmemberCreature aThis);

   abstract boolean isArrived(GangmemberCreature aThis, World w);

   abstract void cancel(World l);

       void saveTask(List<String> sLine)
    { 
    String s = "";
    s += this.taskid;
    s += "\t" + this.tasktype;
    s += "\t" + this.y;
    s += "\t" + this.x;
    s += "\t" + this.workerid;
    sLine.add(s);
    saveSpecificTask(sLine);
    }
    
    
    abstract void saveSpecificTask(List<String> sLine);
    
}
