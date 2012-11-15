/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg30dungeon;

import java.awt.Graphics;

/**
 *
 * @author Akos
 */
interface Drawable {
    public void draw(Graphics g, Viewport vport);
    
    public void drawThing(Graphics g);
}
