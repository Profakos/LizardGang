/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg30dungeon;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Akos
 */
 
abstract public class Creature implements Drawable
{
   protected String name; 
   
    
   protected int numid;
    
   protected int cx;
   protected int cy;
   
   protected int gender; //0 male 1 female -1: n/a
   protected List<Directions> possibleDir;
   
   protected Pathfollower pathfollow;
   
   protected Task task;
   
   protected boolean canwork;
   
   protected int stuntime;
   
   protected boolean alive;
   
     Creature(int numid, int cy, int cx, AIenum type)
   {
   this.numid = numid;
   this.cy = cy;
   this.cx = cx;
   possibleDir = new ArrayList<>();
   pathfollow = new Pathfollower();
   task = null;
   
   if(type==AIenum.gangmember)canwork= true; 
   else
   canwork=false;
   
   stuntime = 0;
   alive = true;
   }
   
   abstract void update(World l);
     
   
   abstract void think(World l);
   
   
   
   void stepOne(Directions dir, World l)
   {
  // System.out.println("Stepping...");
       int mx = this.cx;
       int my = this.cy;
       
     switch(dir)
     {
         case N: my -= 1; break;
         case NE: my -= 1; mx += 1; break;
         case E: mx += 1; break;
         case SE: my += 1; mx += 1; break;
         case S: my += 1; break;
         case SW: my += 1; mx -= 1; break; 
         case W: mx -= 1; break;
         case NW: my -=1; mx -= 1; break;
         case stop: break;
         case up: break;
         case down: break;
     }
      
      if(mx<0) mx = 0;
     if(my<0) my = 0;
     if(mx>(l.getWidth()-1)) mx = l.getWidth()-1;
     if(my>(l.getHeight()-1)) my = l.getHeight()-1;
     
     
    if(l.getTile(my, mx).isWalkable()==true)
            { 
            setCx(mx);
            setCy(my);
            } 
   
   }
   
      
   void randomWander(World l)
   {  
      if(l.isBlocked(cy, cx))
            { 
       return;
            }
       
        getPossibleDir().clear();
     
     if(l.Checkdir(getCy(), getCx(), Directions.N)) getPossibleDir().add(Directions.N);
     if(l.Checkdir(getCy(), getCx(), Directions.E)) getPossibleDir().add(Directions.E);
     if(l.Checkdir(getCy(), getCx(), Directions.W)) getPossibleDir().add(Directions.W);
     if(l.Checkdir(getCy(), getCx(), Directions.S)) getPossibleDir().add(Directions.S);
     
     if(getPossibleDir().contains(Directions.N)==false ^ getPossibleDir().contains(Directions.E)==false)
     if(l.Checkdir(getCy(), getCx(), Directions.NE)) getPossibleDir().add(Directions.NE);
     if(getPossibleDir().contains(Directions.S)==false ^ getPossibleDir().contains(Directions.E)==false)
     if(l.Checkdir(getCy(), getCx(), Directions.SE)) getPossibleDir().add(Directions.SE);
     if(getPossibleDir().contains(Directions.S)==false ^ getPossibleDir().contains(Directions.W)==false)
     if(l.Checkdir(getCy(), getCx(), Directions.SW)) getPossibleDir().add(Directions.SW); 
     if(getPossibleDir().contains(Directions.N)==false ^ getPossibleDir().contains(Directions.W)==false)
     if(l.Checkdir(getCy(), getCx(), Directions.NW)) getPossibleDir().add(Directions.NW);
     
        getPossibleDir().add(Directions.stop);
     
    stepOne(getPossibleDir().get(l.getGen().nextInt(getPossibleDir().size())), l);
   }
   
    @Override
    public abstract void drawThing(Graphics g);
    
    @Override
   public abstract void draw(Graphics g, Viewport viewport);

    
    
   
    /**
     * @return the numid
     */
    public int getNumid() {
        return numid;
    }

    /**
     * @param numid the numid to set
     */
    public void setNumid(int numid) {
        this.numid = numid;
    }

    /**
     * @return the cx
     */
    public int getCx() {
        return cx;
    }

    /**
     * @param cx the cx to set
     */
    public void setCx(int cx) {
        this.cx = cx;
    }

    /**
     * @return the cy
     */
    public int getCy() {
        return cy;
    }

    /**
     * @param cy the cy to set
     */
    public void setCy(int cy) {
        this.cy = cy;
    }

    /**
     * @return the possibleDir
     */
    public List<Directions> getPossibleDir() {
        return possibleDir;
    }

    /**
     * @param possibleDir the possibleDir to set
     */
    public void setPossibleDir(List<Directions> possibleDir) {
        this.possibleDir = possibleDir;
    }

    /**
     * @return the task
     */
    public Task getTask() {
        return task;
    }

    /**
     * @param task the task to set
     */
    public void setTask(Task task) {
        this.task = task;
    }

    /**
     * @return the pathfollow
     */
    public Pathfollower getPathfollow() {
        return pathfollow;
    }

    /**
     * @param pathfollow the pathfollow to set
     */
    public void setPathfollow(Pathfollower pathfollow) {
        this.pathfollow = pathfollow;
    }

    /**
     * @return the canwork
     */
    public boolean isCanwork() {
        return canwork;
    }

    /**
     * @param canwork the canwork to set
     */
    public void setCanwork(boolean canwork) {
        this.canwork = canwork;
    }

    protected void gotoRandomplace(World l) {
        //go to a random place
        int my = l.getGen().nextInt(l.getHeight());
        int mx = l.getGen().nextInt(l.getWidth());
        do {
            my = l.getGen().nextInt(l.getHeight());
            mx = l.getGen().nextInt(l.getWidth());
        } while (l.getMap().isBlocked(my, mx));
        getPathfollow().start(l.getPathfinder().findPath(this.getCy(), this.getCx(), my, mx));
    }

    protected void cancelJob(World l) {
        l.getTaskmanager().cancelTask(this.getTask());
        this.setTask(null);
        this.pathfollow.cancel();
    }

    /**
     * @return the stuntime
     */
    public int getStuntime() {
        return stuntime;
    }

    /**
     * @param stuntime the stuntime to set
     */
    public void setStuntime(int stuntime) {
        this.stuntime = stuntime;
    }

    /**
     * @return the alive
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * @param alive the alive to set
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    protected void freeJob(World l) {
        l.getTaskmanager().removeTask(this.getTask());
        this.setTask(null); 
        this.pathfollow.cancel();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the gender
     */
    public int getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(int gender) {
        this.gender = gender;
    }

    
    
    void saveCreature(List<String> sLine)
    { 
    String s = "";
    s+= this.name;
    s+= "\t" + this.numid;
    s+= "\t" + this.cy;
    s+= "\t" + this.cx;
    s+= "\t" + this.gender;
    s+= "\t" + this.canwork;
    s+= "\t" + this.stuntime;
    s+= "\t" + this.alive;
    sLine.add(s);
    
    saveSpecificCreature(sLine);
    
    if(this.task==null) sLine.add("null"); 
    else sLine.add(""+task.getTaskid()); 
    
    this.getPathfollow().savePath(sLine);
    
    }
    
    
    abstract void saveSpecificCreature(List<String> sLine);

    abstract void loadSpecific(String[] tsplit) ;
}
