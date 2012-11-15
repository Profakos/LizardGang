/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg30dungeon;

import java.awt.Graphics;
import java.util.List;

/**
 *
 * @author Akos
 */
public interface MenuInterface {
    
    void draw(Graphics g);
    
    List<String> listThings();
    
    void selectThing(int i);
}
