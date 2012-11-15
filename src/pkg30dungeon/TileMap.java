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
class TileMap
{
    private int width = 36;
    private int height = 36;
    private List<Tile> tiles; 
    
    TileMap()
    {
    
        tiles = new ArrayList<>();
        
       for(int i=0; i<height; i++)
       {
           for(int j=0; j<width; j++)
           {
             if(j<11)
       tiles.add(new Tile(3));
             else
             {if(Math.random()<0.2)
                tiles.add(new Tile(2));
             else tiles.add(new Tile(0));
             }
           }
       }
        makeHouse(5, 5, 7, 7);
    
        makeHouse(20, 20, 7, 7);
    }

    
    TileMap(List<String> readLines) { //when loading
        tiles = new ArrayList<>();
        String[] split;
        
        this.height = Integer.valueOf(readLines.get(0));
        this.width = Integer.valueOf(readLines.get(1));
        
        for(int i=2; i<readLines.size(); i++)
            {
            if(readLines.get(i).equals("#ENDBLOCK#")) return;    
                
            split = readLines.get(i).split("\t");   
            tiles.add(new Tile(Integer.valueOf(split[0]))) ; 
            
            tiles.get(i-2).loadTile(split);
             
            }
        
    }

    private void makeHouse(int y, int x, int h, int w) {
        
       for(int i=y; i<y+h; i++)
        {
        for(int j=x; j<x+w; j++)
                { 
            if(i==y || j==x || i==y+h-1 || j==x+w-1)
            { 
                if(i==y+h/2)
                tiles.get(j+i*width).switchType(2); 
                else
                tiles.get(j+i*width).switchType(1); 
                continue;
            }
                
            tiles.get(j+i*width).switchType(2); 
                }
        }
    }

    
    public Tile getTile(int y, int x)
    { 
    return tiles.get(y*this.width+x);
    }
    
    
     public boolean isBlocked(int y, int x) //for pathfinding
   {     
   if(this.getTile(y, x).isWalkable()==true) return false;
   return true;
   }
     
    boolean isValidCord(int y, int x)
    { 
         
              if((y)<0) {  
                  return false; }
         if(y>=this.getHeight()) 
         {  
             return false;
         }
         if((x)<0) 
         { 
             return false;
         }
         if(x>=this.getWidth()) 
         { 
             return false;
         } 
         
       return true;
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

    /**
     * @return the tiles
     */
    public List<Tile> getTiles() {
        return tiles;
    }

    /**
     * @param tiles the tiles to set
     */
    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }

    boolean isBlockDiag(int y, int x, int yp, int xp) {
        int dy = yp-y;
        int dx  = xp-x;
        if(Math.abs(dy)==1 && Math.abs(dx)==1)
        {
          if(this.isValidCord(y+dy, x) && this.isValidCord(y, x+dx))
          {
              if(this.isBlocked(y+dy, x) && this.isBlocked(y, x+dx))
              return true;
          }
        }
        return false;
    }

    boolean isReachableNeighbour(int cy, int cx, int y, int x) {
       
        int dy = Math.abs(cy-y) ;
        int dx = Math.abs(cx-x);
        
        if((dx+dy)==1) return true;
        
        if(dx==1 && dy == 1 && this.isBlockDiag(cy, cx, y, x)==false) return true;
        else return false;
        
    }

    void exploreMap(int y, int x, boolean starttile){
         
        
        if(getTile(y,x).isWalkable() && getTile(y,x).getDesignate()==TaskEnum.digging)
            getTile(y,x).setDesignate(null);
        
        if(starttile==false)
        if(getTile(y,x).isExplored()) return;
        
        getTile(y,x).setExplored(true);
        
        if(getTile(y,x).isOpaque()==true) return;
         
        /*
         * TODO fix this
         */
        if(this.isValidCord(y-1, x)) exploreMap(y-1, x,false);
        if(this.isValidCord(y+1, x)) exploreMap(y+1, x,false);
        if(this.isValidCord(y, x-1)) exploreMap(y, x-1,false);
        if(this.isValidCord(y, x+1)) exploreMap(y, x+1,false);
        
        /*
         if(this.isValidCord(y-1, x-1) && this.isBlockDiag(y, x, y-1, x-1)==false) exploreMap(y-1, x-1);
        if(this.isValidCord(y-1, x+1) && this.isBlockDiag(y, x, y-1, x+1)==false) exploreMap(y-1, x+1);
        if(this.isValidCord(y+1, x-1) && this.isBlockDiag(y, x, y+1, x-1)==false) exploreMap(y+1, x-1);
        if(this.isValidCord(y+1, x+1) && this.isBlockDiag(y, x, y+1, x+1)==false) exploreMap(y+1, x+1);
         */
    }

    void saveMap(List<String> sLine) {
        sLine.add(""+this.getHeight());
        sLine.add(""+this.getWidth());
         for(int i = 0; i<this.getTiles().size(); i++)
         {
         this.getTiles().get(i).saveTile(sLine);
         }
         sLine.add("#ENDBLOCK#");
    }
    
   
    
}
