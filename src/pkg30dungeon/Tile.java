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
public class Tile {
    
    private int width = 50;
    private int height = 50;
    
    private boolean walkable; 
    private int tilehealth; 
    private int tiletype; 
    private boolean explored; 
    private boolean opaque; 
    
    private List<Integer> creature;  
    private List<Integer> item;  
    
    private TaskEnum designate = null;
    private int actualTask = -1; 
    
    //0= rockwall; 1
    
    Tile(int type){ 
        
        this.tiletype=type;
       
        switch(type)
        {
            case(0): tilehealth=50; walkable = false; opaque = true; break;//rockwall
            case(1): tilehealth=10; walkable = false; opaque = true; break;//builtwall
            case(2): tilehealth=0; walkable = true; opaque = false; break;//floor
            case(3): tilehealth=0; walkable = true; opaque = false; break;//grass
        }
        
        explored = false;
        
        creature = new ArrayList<>(); 
        item = new ArrayList<>(); 
    }

    
     public void flipWalkable() {
        if(walkable ==false) walkable = true;
        else walkable = false;
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
     * @return the color
     */
    public boolean isWalkable() {
        return walkable;
    }

    /**
     * @param color the color to set
     */
    public void setWalkable(boolean color) {
        this.walkable = color;
    }

    /**
     * @return the entity
     */
    public List<Integer> getCreature() {
        return creature;
    }

    /**
     * @param entity the entity to set
     */
    public void setCreatureID(List<Integer> entity) {
        this.creature = entity;
    }

  
    
   void movingCreature(boolean away, int id)
   {
   if(away == true){
      for(int i=0; i<creature.size(); i++)
      {
          if(creature.get(i)==id) creature.remove(i);
      }
                   }
   else
        {
        this.creature.add(id); 
        }
    
   }

    void designateMine() {
         /*
          * The order to be mined.
          */
      //  if(this.walkable==false)
     //   {
        if(this.walkable==false || this.explored==false)
            setDesignate(TaskEnum.digging); //includes fake ones
      //  }
    }

    /**
     * @return the designatemine
     */
    public boolean isDesignatemine() {
        return getDesignate()==TaskEnum.digging;
    }

    /**
     * @param designatemine the designatemine to set
     */
    public void setDesignate(TaskEnum type) {
        this.designate = type;
    }

    boolean mineTile() {
        this.setTilehealth(this.getTilehealth() - 10);

        if(getTilehealth()<=0)
        {
         this.setDesignate(null);
         this.actualTask=-1;
         switchType(2); 
         return true;
        }
        return false;
    }
    
     

    /**
     * @return the designate
     */
    public TaskEnum getDesignate() {
        return designate;
    }

    void designateWall() {
        if(walkable==true && explored==true)
             this.setDesignate(TaskEnum.walling);
    }

    boolean wallTile() {
          
        if(this.creature.isEmpty()==false) return false;
        
         this.setDesignate(null);
         this.actualTask=-1;
         switchType(1); 
         return true;
         
         
    }

    /**
     * @return the actualTask
     */
    public int getActualTask() {
        return actualTask;
    }

    /**
     * @param actualTask the actualTask to set
     */
    public void setActualTask(int actualTask) {
        this.actualTask = actualTask;
    }

    /**
     * @return the tilehealth
     */
    public int getTilehealth() {
        return tilehealth;
    }

    /**
     * @param tilehealth the tilehealth to set
     */
    public void setTilehealth(int tilehealth) {
        this.tilehealth = tilehealth;
    }

    /**
     * @return the tiletype
     */
    public int getTiletype() {
        return tiletype;
    }

    /**
     * @param tiletype the tiletype to set
     */
    public void setTiletype(int tiletype) {
        this.tiletype = tiletype;
    }

    void switchType(int type){
         this.tiletype=type;
       
        switch(type)
        {
            case(0): tilehealth=50; walkable = false; opaque = true; break;//rockwall
            case(1): tilehealth=10; walkable = false; opaque = true; break;//builtwall
            case(2): tilehealth=0; walkable = true; opaque = false; break;//floor
            case(3): tilehealth=0; walkable = true; opaque = false; break;//grass
        }
    }

    /**
     * @return the explored
     */
    public boolean isExplored() {
        return explored;
    }

    /**
     * @param explored the explored to set
     */
    public void setExplored(boolean explored) {
        this.explored = explored;
    }

    /**
     * @return the opaque
     */
    public boolean isOpaque() {
        return opaque;
    }

    /**
     * @param opaque the opaque to set
     */
    public void setOpaque(boolean opaque) {
        this.opaque = opaque;
    }

    /**
     * @return the item
     */
    public List<Integer> getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(List<Integer> item) {
        this.item = item;
    }

    void saveTile(List<String> sLine) {
        String s =""+ this.tiletype;
        s += "\t" + this.explored;
        s += "\t" + this.tilehealth;
        s += "\t" + this.designate;
        s += "\t" + this.actualTask;
        
        
        if(creature.isEmpty()) s+= "\t@";
        else
        {
        String cr="";
        for(int i = 0; i<this.creature.size(); i++)
            {
        cr+= creature.get(i) + "@"; 
            }
        
        s+= "\t" + cr;
        }
        
        if(item.isEmpty()) s+= "\t@";
        else
        {
        String cr="";
        for(int i = 0; i<this.item.size(); i++)
            {
        cr+= item.get(i) + "@";
            }
        
        s+= "\t" + cr;
        }
        
        sLine.add(s);
        
        //actual task and item IDs are not saved here, and will be done seperately
    }

    void loadTile(String[] split)
    {
    
      setExplored(Boolean.valueOf(split[1]));
      setTilehealth(Integer.valueOf(split[2]));
            
      if(!split[3].equals("null"))
      setDesignate(TaskEnum.valueOf(split[3]));
      
      setActualTask(Integer.valueOf(split[4]));
    /*
     * TODO: split[5] creatures ints and split[6]item ints has to be added
     */
      if(split[5].equals("@")==false)
      {   
          String[] splitint = split[5].split("@");
          for(int i=0; i<splitint.length; i++)
            {
            this.creature.add(Integer.valueOf(splitint[i]));
            }
      }
      
      if(split[6].equals("@")==false)
      {   
          String[] splitint = split[6].split("@");
           for(int i=0; i<splitint.length; i++)
            {
             this.item.add(Integer.valueOf(splitint[i]));
            }
      }
      
    }
 
    
}
