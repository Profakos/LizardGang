/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg30dungeon;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Akos
 */
public class ExamineMenu implements MenuInterface {

    private boolean displaying;
    World world;
    List<Integer> selectedCreatureIDs;
    List<String> selectedThingNames; 
    
    List<Integer> selectedItemIDs;
    List<String> selectedItemNames; 
    
    int selectedID;
    int selecttype = 0;
    
    ExamineMenu(World w)
    {
    this.world = w;    
    displaying=false; 
    selectedCreatureIDs = new ArrayList<>();
    selectedItemIDs = new ArrayList<>(); 
    
    
    selectedThingNames = new ArrayList<>();
    
    selectedID=-1; 
    };
    
    
    public List<String> selectTile(int y, int x) {
         selectedCreatureIDs.clear();
         selectedItemIDs.clear();
         
         if(world.getMap().isValidCord(y, x))
         {
         for(int i = 0; i<world.getTile(y, x).getCreature().size(); i++)
            {
            selectedCreatureIDs.add(world.getTile(y, x).getCreature().get(i)); 
            }
         
                  for(int i = 0; i<world.getTile(y, x).getItem().size(); i++)
            {
            selectedItemIDs.add(world.getTile(y, x).getItem().get(i)); 
            }
          
         }
         return listThings();
         
    }
    
    
    @Override
    public List<String> listThings() {
        selectedThingNames.clear();
        
        for(int i=0; i<selectedCreatureIDs.size(); i++)
        selectedThingNames.add(world.getBeastcollection().getKeyID(selectedCreatureIDs.get(i)).getName());
        
         for(int i=0; i<selectedItemIDs.size(); i++)
        selectedThingNames.add(world.getItemcollection().getKeyID(selectedItemIDs.get(i)).getName());
        
         return selectedThingNames;
    }

    @Override
    public void selectThing(int i) {
        if(i<this.selectedCreatureIDs.size())
        {
         selecttype = 0;
         selectedID=this.selectedCreatureIDs.get(i);
        }
        else
        {
            selecttype = 1;
            selectedID=this.selectedItemIDs.get(i-this.selectedCreatureIDs.size());
        }  
    }

    @Override
    public void draw(Graphics g) {
         if(isDisplaying())
         {
         g.setColor(Color.gray);
         g.fillRect(0, 0, 600, 600);
        //  g.setColor(Color.white);
       //  g.drawRect(0, 0, 600, 600);
         g.setColor(Color.white);
        // g.drawString("TESTING", 40, 40);
         if(selecttype == 0)
            world.getBeastcollection().getKeyID(selectedID).drawThing(g);
         else
            world.getItemcollection().getKeyID(selectedID).drawThing(g); 
         }
    }

    /**
     * @return the displaying
     */
    public boolean isDisplaying() {
        return displaying;
    }

    /**
     * @param displaying the displaying to set
     */
    public void setDisplaying(boolean displaying) {
        this.displaying = displaying;
    }
    
}
