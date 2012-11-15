/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg30dungeon;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Akos
 */
public class GangmemberCreature extends Creature{
    
    int hatnum;
    int headhandnum;
    int tailnum;
    int suitnum;
    
    
    GangmemberCreature(int id, int y, int x)
    {
     super(id, y,x, AIenum.gangmember);
     
     if(Math.random()<0.5) setGender(0);
    else setGender(1);
     
     String tname = "";
     Random gen = new Random();
     
     if(getGender()==1)
        switch(gen.nextInt(3))
            {   
            case 0: tname+="Anna"; break;
            case 1: tname+="Bertha"; break;
            case 2: tname+="Caroline"; break;
            }
     else
         switch(gen.nextInt(3))
            {   
            case 0: tname+="Albert"; break;
            case 1: tname+="Bartholomew"; break;
            case 2: tname+="Cecil"; break;
            }
     tname+=" ";
     
      switch(gen.nextInt(3))
            {   
            case 0: tname+="Smith"; break;
            case 1: tname+="Smithsmith"; break;
            case 2: tname+="S. Mith"; break;
            }
     setName(tname);
        
    hatnum = gen.nextInt(3);
    headhandnum = gen.nextInt(3);
    tailnum = gen.nextInt(3);
    suitnum = gen.nextInt(3);
     
     
    }
    
    @Override
    void update(World l)
   {
   
       l.getTile(this.getCy(), this.getCx()).movingCreature(true, this.getNumid());
       
       if(getStuntime()==0)
        think(l);
      
       if(getStuntime()!=0)
        setStuntime(getStuntime()-1);
           
       l.getTile(this.getCy(), this.getCx()).movingCreature(false, this.getNumid());
   
   }
     
   
    @Override
   void think(World w)
   {
        /*
         * Needs to be remade: instead make task do an AreWeThereYet() function
         */
   if(  getPathfollow().getRunning()==false) //if we are at the end of a scheduled route
    { 
          if(this.getTask()!=null) //if we have to do something
            {
                
             if(this.getTask().isArrived(this, w)) getTask().performTask(w, this);
             else
                 this.cancelJob(w);
              
            } 
   }
   else //if we are still going
   {
   
       if(this.getTask()!=null) //if we have to do something
            {    
             if(this.getTask().isArrived(this, w)) 
             {   
                 
                 
                 
                 getTask().performTask(w, this);
                  return;
             }
             
            }  
       
    int mx = getPathfollow().getnextX();
    int my = getPathfollow().getnextY();
    
     
    if(w.isBlocked(my, mx)==true) //if interrupted by a suddenly appearing wall
    {
         getPathfollow().cancel(); 
         cancelJob(w);
         return;
    } 
        this.setCy(my);
        this.setCx(mx);
        getPathfollow().next(); 
        
     }
   
   
    
            if(getTask()==null)
            w.getTaskmanager().assignTasks(this);
            if(getTask()==null)
            this.randomWander(w);
   
   }
    
  
    

    @Override
    public void draw(Graphics g, Viewport vport) {
        
        int tilesize = vport.getTilesize();
         
        g.drawImage(ImageRegistry.getImage("tails"), (getCx()-vport.getOffx())*tilesize, (getCy()-vport.getOffy())*tilesize, 
           (getCx()-vport.getOffx()+1)*tilesize, (getCy()-vport.getOffy()+1)*tilesize, 50*tailnum, 0, 50*(tailnum+1), 50, null);
        
         g.drawImage(ImageRegistry.getImage("suits"), (getCx()-vport.getOffx())*tilesize, (getCy()-vport.getOffy())*tilesize, 
           (getCx()-vport.getOffx()+1)*tilesize, (getCy()-vport.getOffy()+1)*tilesize, 50*suitnum, 0, 50*(suitnum+1), 50, null);
        
         g.drawImage(ImageRegistry.getImage("heads"), (getCx()-vport.getOffx())*tilesize, (getCy()-vport.getOffy())*tilesize, 
           (getCx()-vport.getOffx()+1)*tilesize, (getCy()-vport.getOffy()+1)*tilesize, 50*headhandnum, 0, 50*(headhandnum+1), 50, null);
         
        g.drawImage(ImageRegistry.getImage("hats"), (getCx()-vport.getOffx())*tilesize, (getCy()-vport.getOffy())*tilesize, 
           (getCx()-vport.getOffx()+1)*tilesize, (getCy()-vport.getOffy()+1)*tilesize, 50*hatnum, 0, 50*(hatnum+1), 50, null);
       
    }
     
    @Override
    public void drawThing(Graphics g) {
        
         
         g.drawImage(ImageRegistry.getImage("tails"), 0, 0, 300, 300, 50*tailnum, 0, 50*(tailnum+1), 50, null);
        
         g.drawImage(ImageRegistry.getImage("suits"), 0,  0, 300, 300, 50*suitnum, 0, 50*(suitnum+1), 50, null);
        
         g.drawImage(ImageRegistry.getImage("heads"), 0,  0, 300, 300, 50*headhandnum, 0, 50*(headhandnum+1), 50, null);
         
        g.drawImage(ImageRegistry.getImage("hats"), 0,  0, 300, 300, 50*hatnum, 0, 50*(hatnum+1), 50, null);
       
        
         g.setColor(Color.white);
       g.drawString(getName(), 300, 30);
       switch(this.getGender())
        {
           case 0:
       g.drawString("Male", 300, 60);
           break;
           case 1:
       g.drawString("Female", 300, 90);
       }
       
       if(this.isAlive()==false)
       {
           g.drawString("DEAD", 300, 120);
       } 
        
       if(this.getTask()!=null) //this.getTask().getTasktype()
               g.drawString(this.getTask().getTasktype().toString(), 300, 150);
       else
          g.drawString("No job", 300, 150); 
    }

     
    @Override
    void saveSpecificCreature(List<String> sLine) {
       String s = "gangmember";
       s +=  "\t" + hatnum;
        s += "\t" + headhandnum;
         s += "\t" + tailnum;
        s += "\t" +  suitnum;
       sLine.add(s);
    }

    @Override
    void loadSpecific(String[] tsplit) {
         hatnum = Integer.valueOf(tsplit[1]);
         headhandnum=Integer.valueOf(tsplit[2]);
         tailnum=Integer.valueOf(tsplit[3]);
         suitnum=Integer.valueOf(tsplit[4]);
    }

    
    
}
