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
public class PrimitiveTask extends Task{

   List<String> worldObjectRecquire;
    
    PrimitiveTask(int id, int y, int x, TaskEnum tasktype)
    {
    super( id, y, x,tasktype);
     worldObjectRecquire  = new ArrayList<>();
    }
    
     

    @Override
    boolean canPerform(Creature cc, World w) {
        if(cc.canwork && cc.isAlive() && cc.stuntime==0)
        {
         
            if(w.getItemcollection().isRecqExist(worldObjectRecquire)==false) return false;
            
            if(this.getTasktype()==TaskEnum.walking)
            {
               for(int i = -1; i<2; i++)
               {
                   for(int j = -1; j<2; j++)
                   {   
                  
    
                   if(w.getPathfinder().findPath(cc.getCy(), cc.getCx(),this.getY()+i, this.getX()+j)!=null)
                   {  
                     if(w.getMap().isBlockDiag( getY(), getX(),  getY()+i,  getX()+j)==true) continue;
                     
                        return true;
                   }
                   
                   
                   }
               }
               return false;
            } 
         
            return true;
        }
    
        return false;
    }

    @Override
    void performTask(World world, GangmemberCreature aThis) {
          
        if( getTasktype()==TaskEnum.walking)
            { 
                if(world.getMap().isReachableNeighbour(aThis.getCy(), aThis.getCx(), getY(), getX()))
                    done = true;
            }
         
        
        if( getTasktype()==TaskEnum.digging)
          if(world.getTile(this.getY(), this.getX()).mineTile())
                {
                  world.getMap().exploreMap(this.getY(), this.getX(), true);
                  done = true;
                }
        
        if( getTasktype()==TaskEnum.walling)
        {
            if(world.getTile(this.getY(), this.getX()).wallTile())
                {
                 done = true;
                }
        }
    }

    @Override
    boolean isArrived(GangmemberCreature aThis, World w) {
        
        if(this.getTasktype()==TaskEnum.walking && aThis.getPathfollow().getRunning()==false)
        {  
             
             Path finalpath=null;  
             Path path ;
             for (int xi=-1;xi<2;xi++) {
				for (int yi=-1;yi<2;yi++) 
                                            { 
                      if(xi==0 && yi==0)   continue;  
           
             
            path = w.getPathfinder().findPath(aThis.getCy(), aThis.getCx(), getY()+yi, getX()+xi);
            
            if(w.getMap().isBlockDiag(this.getY(), this.getX(), getY()+yi, getX()+xi)) path=null;
             
            if(path!=null)
            {
            
            
                
            if(finalpath==null || path.getLength()<finalpath.getLength()) 
                finalpath = path; 
            }
               }
                                       
             }
             
            aThis.getPathfollow().start(finalpath);  
            
            return true;
        }
        
        return w.getMap().isReachableNeighbour(aThis.getCy(), aThis.getCx(), getY(), getX());
               // return;
    }

    @Override
    void cancel(World l) {
       setWorkerid(-1);
    }

    @Override
    void saveSpecificTask(List<String> sLine) { 
        sLine.add("-");
        
        
    }

    @Override
    int getUtility(Creature cc, World w) { 
        
        
        switch(this.getTasktype())
        {
            case walking: return Math.abs(cc.getCx()-this.getX()) - Math.abs(cc.getCy()-this.getY());
            case walling: return 20;
            case digging: return 10;
        }
        
        return -1000;
   }
    
    
}
