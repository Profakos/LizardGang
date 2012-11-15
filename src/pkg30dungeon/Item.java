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
public class Item implements Drawable{
    private int id; 
    private int iy;
    private int ix;
    private String name;
    
    Item(int iy, int ix, int i, String name)
    {
    id = i; 
    this.iy = iy;
    this.ix = ix;
    this.name = name;
    };

    @Override
    public void draw(Graphics g, Viewport vport) {
        int tilesize = vport.getTilesize();
         g.drawImage(ImageRegistry.getImage(getName()), (getIx()-vport.getOffx())*tilesize, (getIy()-vport.getOffy())*tilesize, tilesize, tilesize, null);
      
    }
    
       @Override
    public void drawThing(Graphics g) { 
           g.drawImage(ImageRegistry.getImage(name), 0, 0, 300, 300, null);
            g.setColor(Color.white);
         g.drawString(getName(), 300, 30);
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the iy
     */
    public int getIy() {
        return iy;
    }

    /**
     * @param iy the iy to set
     */
    public void setIy(int iy) {
        this.iy = iy;
    }

    /**
     * @return the ix
     */
    public int getIx() {
        return ix;
    }

    /**
     * @param ix the ix to set
     */
    public void setIx(int ix) {
        this.ix = ix;
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
    
     void saveItem(List<String> sLine) {
         String s="";
         s+= this.id;
         s+= "\t" + this.name;
         s+= "\t" + this.iy;
         s+= "\t" + this.ix;
       sLine.add(s); 
    }
}
