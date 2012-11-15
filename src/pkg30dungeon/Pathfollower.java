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
public class Pathfollower {
    
   private boolean running;
    
    private Path path;  
   
    int next;
    
    Pathfollower()
    {
        running = false; 
        path = new Path();
        next = 0;
    }
    
    void cancel()
    {
        setRunning(false); 
        path = null;
        next = 0;
    }
    
    void start(Path path)
    {
        if(path==null) return;
        if(path.getLength()==0) return;
        setRunning(true); 
        setPath(path);
        next = 0; 
    }
  
    
    public boolean getRunning() {
        return running;
    }

    /**
     * @param want the want to set
     */
    public void setRunning(boolean run) {
        this.running = run;
    }

    /**
     * @return the path
     */
    public Path getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(Path path) {
        this.path = path;
    }

    void next() {
         next++;
         if(path == null) cancel();
         
         if(next>=path.getLength())
             cancel();
    }
 
     int getnextX()
    {
     return path.getX(next);   
    }

    int getnextY()
    {
     return path.getY(next);     
    }

    void savePath(List<String> sLine) {
       if(this.running==false) sLine.add("null");
       else sLine.add(this.path.saveSteps( ));
    }
    
    void loadPath(String pathstring)
     {
     if("null".equals(pathstring)) return;    
     Path tpath = new Path(pathstring);
     this.start(tpath);
     }
}
