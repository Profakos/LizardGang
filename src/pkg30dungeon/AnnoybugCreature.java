/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg30dungeon;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

/**
 *
 * @author Akos
 */
public class AnnoybugCreature extends Creature {

     AnnoybugCreature(int id, int y, int x)
    {
    super(id, y,x, AIenum.annoybug);
    setName("Annoying Bug");
    if(Math.random()<0.5) setGender(0);
    else setGender(1);
    }
    
    @Override
    void update(World l) {
        
        if(this.isAlive()==false) return;
        
       l.getTile(this.getCy(), this.getCx()).movingCreature(true, this.getNumid()); 
        think(l); 
       l.getTile(this.getCy(), this.getCx()).movingCreature(false, this.getNumid());
         
       
    }

    @Override
    void think(World l) {
      
       Tile t = l.getTile(getCy(), getCx());
       
       if(t.getCreature().isEmpty()==false)
        for(int i = 0; i<t.getCreature().size(); i++)
            {
                int bid = t.getCreature().get(i);
            if(getNumid()!=bid)
                {
                 l.getBeastcollection().getKeyID(bid).setStuntime(10);
                 l.getBeastcollection().getKeyID(bid).cancelJob(l);
                 this.setAlive(false);
                 return;
                } 
       
            }
       
         this.randomWander(l);
    }

    @Override
   public void draw(Graphics g, Viewport vport) {
       //  g.setColor(Color.white);
       int tilesize = vport.getTilesize();
        
        g.drawImage(ImageRegistry.getImage("annoybug"), (getCx()-vport.getOffx())*tilesize, (getCy()-vport.getOffy())*tilesize, tilesize, tilesize, null);
      
    }

    @Override
    public void drawThing(Graphics g) {
       g.drawImage(ImageRegistry.getImage("annoybug"), 0, 0, 300, 300, null);
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
       
       }

    @Override
    void saveSpecificCreature(List<String> sLine) {
        
       sLine.add("annoybug");
    }

    @Override
    void loadSpecific(String[] tsplit) { 
         
    }
    
}
