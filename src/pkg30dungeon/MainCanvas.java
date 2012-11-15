/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg30dungeon;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author Akos
 */
public class MainCanvas extends Canvas{
    
    int curx = 0;
    int cury = 0;
    
    Viewport vport;
    
    
    World world;
    ShopMenu shopMenu;
    ExamineMenu examineMenu;
    
    ImageRegistry imageregistry;
    
    
    MainCanvas(World world, ShopMenu shopMenu, ExamineMenu examineMenu)
      {
      this.setBackground(Color.black);
       
      vport = new Viewport(0, 0, 50, world.getMap().getHeight(), world.getMap().getWidth());
      
      this.world = world;
      this.shopMenu = shopMenu;
      this.examineMenu = examineMenu;
       
      imageregistry = new ImageRegistry();
      }
    
    
     @Override
    public void update(Graphics g)
    { 
       Graphics offgc;
	Image offscreen;
	  
	offscreen = createImage(this.getWidth(), this.getHeight());
	offgc = offscreen.getGraphics(); 
	paint(offgc); 
	g.drawImage(offscreen, 0, 0, this);    
             
  //  paint(g);
    g.dispose();
    offgc.dispose();
    };
    
    @Override
    public void paint(Graphics g)
    {
     g.clearRect(0, 0, this.getWidth(), this.getHeight());
     
     
     if(examineMenu.isDisplaying())
     {  examineMenu.draw(g);
     return;
     }
     
      if(shopMenu.isDisplaying())
     {  shopMenu.draw(g);
     return;
     }
     
      world.draw(g, this.vport);
 
      try{
          int cx = curx/vport.getTilesize();
          int cy = cury/vport.getTilesize();  
   g.drawImage(ImageRegistry.getImage("cursor"), cx*vport.getTilesize(), cy*vport.getTilesize(), vport.getTilesize(), vport.getTilesize(), this);
      }catch(NullPointerException e){}
     
      
     
    };
    
    void setCurx(int i){curx = i;}
    void setCury(int i){cury = i;}

    void zoom() {
        
    vport.zoom();
    }
    
    

}
