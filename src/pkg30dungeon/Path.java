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
public class Path {
    List<Step> steps;
    
    Path(){ steps = new ArrayList<>();}

    Path(String pathstring) {
         
         steps = new ArrayList<>();
         
         String[] stringsteps = pathstring.split("\t");
         for(int i=0; i<stringsteps.length; i++)
         {
         String[] scords = stringsteps[i].split("@");
         steps.add(new Step(Integer.valueOf(scords[0]), Integer.valueOf(scords[1])));
         }
    }
    
    	public int getLength()
        {
        if(steps.isEmpty()) return 0;
        return steps.size();
        }
        
	public Step getStep(int index)
        {
          return steps.get(index);   
        }	
	public int getX(int index)
        {
            return getStep(index).getX();
        };
	public int getY(int index)
        {
            return getStep(index).getY();
        };
	public void appendStep(int y, int x)
        {
         this.steps.add(new Step(y, x));
        };
        
	public void prependStep(int y, int x)
        {
        this.steps.add(0, new Step(y, x));
        };
        
	public boolean contains(int y, int x)
        {
            for(int i = 0; i<this.getLength(); i++)
            {
                if(this.getY(i)==y && this.getX(i)==x) return true;
            }
            return false;
        };
        
        public void clear(){ steps.clear(); }

    String saveSteps() {
        
        if(this.steps.isEmpty()) return "null";
        
        String s ="";
        
        for(int i=0; i<steps.size(); i++)
            {
            s+=steps.get(i).getY() + "@" + steps.get(i).getX() + "\t";
            }
        
        return s;
    }

   
}
