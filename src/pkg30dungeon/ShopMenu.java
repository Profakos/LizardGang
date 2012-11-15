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
public class ShopMenu implements MenuInterface {

    private List<String> shoplist;
    World world;
    
    int selectedNum;
    
    private boolean displaying;
      
    ShopMenu(World world)
    {
    displaying = false;
    
    this.shoplist = new ArrayList<>();
    this.world = world;
    
    shoplist.add("Comfy bed");
    shoplist.add("Cocolime");
    shoplist.add("Bookcase");
    shoplist.add("Digbox");
    shoplist.add("Door");
    shoplist.add("Counter");
    shoplist.add("Chair");
    shoplist.add("Stove");
    }
    
    
     public void placeonTile(int y, int x, int sid) {
          
      if(sid!=-1)
            world.addItem(y, x, shoplist.get(sid));
               
    }
    
    
    @Override
    public void selectThing(int i) {
        selectedNum = i;
    }

    @Override
    public void draw(Graphics g) {
         g.setColor(Color.gray);
         g.fillRect(0, 0, 600, 600);       
         g.drawImage(ImageRegistry.getImage(shoplist.get(selectedNum)), 0, 0, 300, 300, null);
         g.setColor(Color.white);
         g.drawString(shoplist.get(selectedNum), 300, 30);
    }

    @Override
    public List<String> listThings() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @return the shoplist
     */
    public List<String> getShoplist() {
        return shoplist;
    }

    /**
     * @param shoplist the shoplist to set
     */
    public void setShoplist(List<String> shoplist) {
        this.shoplist = shoplist;
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
