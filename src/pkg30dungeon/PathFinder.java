/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg30dungeon;

import java.util.ArrayList;

/**
 *
 * @author Akos
 */
public class PathFinder {
    
    
     private TileMap map;
             
    private Node[][] nodes;
    
    
private ArrayList<Node> closed = new ArrayList ();

 private ArrayList<Node> open = new ArrayList ();
    
    PathFinder(TileMap map)
    {
    this.map = map;
    nodes = new Node[map.getHeight()][map.getWidth()];
    
    for(int i = 0; i<this.map.getHeight(); i++)
        {
            for(int j = 0; j<this.map.getWidth(); j++)
            {
                nodes[j][i] = new Node(j,i);    
            }
        }
    
    }
    
   
    
      public Path findPath(int sy, int sx, int ty, int tx)
      {
             
          if (map.isValidCord(ty, tx)==false) {
			return null;
		}
          
		if (map.isBlocked(ty, tx)) {
			return null;
		}
                
               
                if(sy==ty && sx == tx)
                {
               Path path = new Path();
                path.prependStep(sy,sx); 

		return path;
                }
                
            
		nodes[sy][sx].setCost(0);
		nodes[sy][sx].setDepth(0);
		closed.clear();
		open.clear();
		open.add(nodes[sy][sx]);
		
		nodes[ty][tx].setParent(null);
		 
           
		while ((!open.isEmpty())) { 
                     
			Node current = getFirstInOpen();
			if (current == nodes[ty][tx]) {
				break;
			}
			
			removeFromOpen(current);
			addToClosed(current); 

			for (int x=-1;x<2;x++) {
				for (int y=-1;y<2;y++) { 

					if ((x == 0) && (y == 0)) {
						continue;
					}
					 
                                        int xp = x + current.getX();
					int yp = y + current.getY();
					 
                                        
					if (map.isValidCord(yp, xp)==true && map.isBlocked(yp, xp)==false && map.isBlockDiag(current.getY(), current.getX(), yp, xp)==false) 
                                        {
					 
                                            float nextStepCost = current.getCost() + getMovementCost(current.getY(), current.getX(), yp, xp);
                                            Node neighbour = nodes[yp][xp];
						
                                            if (nextStepCost < neighbour.getCost()) {
							if (inOpenList(neighbour)) {
								removeFromOpen(neighbour);
							}
							if (inClosedList(neighbour)) {
								removeFromClosed(neighbour);
							}
						}
                                            
                                            
                                            if (!(inOpenList(neighbour)) && !(inClosedList(neighbour))) { 
                                                 neighbour.setCost(nextStepCost) ;       
                                                neighbour.setParent(current);
							addToOpen(neighbour); 
						}
					}
				}
			}
		}
 
/*
 * Returns with null when it is unreachable
 */
		if (nodes[ty][tx].getParent() == null) {
			return null;
		}
		 
 /*
  * Search ended
  * Building path
  */
		Path path = new Path();
		Node target = nodes[ty][tx];
		while (target != nodes[sy][sx]) {
                    
                  path.prependStep(target.getY(), target.getX());  
                  target = target.getParent();     
		}
		path.prependStep(sy,sx); 

		return path;
         
      }
      
      
      public float getMovementCost(int y, int x, int ty, int tx)
      {
      return 1;
      }

    private Node getFirstInOpen() {
         return open.get(0);
    }

    private void removeFromOpen(Node current) {
         open.remove(current);
    }

    private void removeFromClosed(Node current) {
         closed.remove(current);
    }
    private void addToClosed(Node current) {
        closed.add(current);
    }

     private void addToOpen(Node current) {
        open.add(current);
    }
     
    private boolean inOpenList(Node node) {
        return open.contains(node);
    }
    
    private boolean inClosedList(Node node) {
        return closed.contains(node);
    }
}
