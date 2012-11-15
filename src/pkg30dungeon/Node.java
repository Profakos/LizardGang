/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg30dungeon;

/**
 *
 * @author Akos
 */
public class Node {
    
   private int y;
   private int x;
    
    
    
    private float cost;
    private int depth;
    
    private Node parent;
     
    Node(int y, int x)
    {
        this.y = y;
        this.x = x;
        
        
        parent = null;
    };

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

   

    /**
     * @return the cost
     */
    public float getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(float cost) {
        this.cost = cost;
    }

    /**
     * @return the depth
     */
    public int getDepth() {
        return depth;
    }

    /**
     * @param depth the depth to set
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }

    /**
     * @return the parent
     */
    public Node getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(Node parent) {
        this.parent = parent;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

     
     

  
    
    
}
