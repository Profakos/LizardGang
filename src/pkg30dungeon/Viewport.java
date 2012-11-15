/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg30dungeon;

/**
 *
 * @author Akos
 */
public class Viewport {

    
    private int offy;
    private int offx;
    private int tilesize;
    private int width;
    private int height;
    
    private int tilenumy;
    private int tilenumx;
    
    Viewport(int offy, int offx, int tilesize, int tilenumy, int tilenumx) {
        
        this.offy = offy;
        this.offx = offx;
        this.tilesize = tilesize;
        
        
        
        this.tilenumy = tilenumy;
        this.tilenumx = tilenumx;
        
    }
    
    
    void scroll(Directions dir)
    {
    //  if(tilesize==25)
    //      return;
          
    switch(dir)
        {
        case N: this.offy = offy-1; break;
        case S: this.offy = offy+1; break;
        case E: this.offx = offx+1; break;
        case W: this.offx = offx-1; break;
        }
    if(this.offy<0) offy=0;
    if(this.offx<0) offx = 0;
     
    if(offx>(tilenumx-600/tilesize)) offx-=1;
    if(offy>(tilenumy-600/tilesize)) offy-=1;
    
    }

    /**
     * @return the offy
     */
    public int getOffy() {
        return offy;
    }

    /**
     * @param offy the offy to set
     */
    public void setOffy(int offy) {
        this.offy = offy;
    }

    /**
     * @return the offx
     */
    public int getOffx() {
        return offx;
    }

    /**
     * @param offx the offx to set
     */
    public void setOffx(int offx) {
        this.offx = offx;
    }

    /**
     * @return the tilesize
     */
    public int getTilesize() {
        return tilesize;
    }

    /**
     * @param tilesize the tilesize to set
     */
    public void setTilesize(int tilesize) {
        this.tilesize = tilesize;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    void zoom() {
         
        if( getTilesize()==50) 
            {
             setTilesize(25); 
          //   offx = 0;
         //    offy = 0;
            } 
        else
            {
             setTilesize(50);   
            }
        
    if(this.offy<0) offy=0;
    if(this.offx<0) offx = 0;
     
    if(offx>(tilenumx-600/tilesize)) offx=tilenumx-600/tilesize;
    if(offy>(tilenumy-600/tilesize)) offy=tilenumy-600/tilesize;
       
    }

    /**
     * @return the tilenumy
     */
    public int getTilenumy() {
        return tilenumy;
    }

    /**
     * @param tilenumy the tilenumy to set
     */
    public void setTilenumy(int tilenumy) {
        this.tilenumy = tilenumy;
    }
    
        /**
     * @return the tilenumx
     */
    public int getTilenumx() {
        return tilenumx;
    }

    /**
     * @param tilenumx the tilenumy to set
     */
    public void setTilenumx(int tilenumx) {
        this.tilenumx = tilenumx;
    }
}
