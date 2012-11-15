/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg30dungeon;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author Akos
 */
public class GameWindow extends Frame {
      
     MainCanvas maincanvas; 
     Panel buttonPanel;
     Panel canvasPanel;
     Panel devpanel;
     Panel gamePanel; 
     Panel listPanel; 
     Button stepButton;
     Button pauseButton;
     Button designateButton; 
     Button spawnButton;
     Button bugspawnButton; 
     Button zoomButton;  
     TextArea jobbox; 
     List thinglist;
     Button examineButton; 
     Button shopButton; 
     
     Button loadButton; 
     Button saveButton; 
     
     World world;     
     boolean exit = false;
     boolean running;
     boolean notdesignating;
     boolean currdesignateType;
     boolean paused; 
     Timer timer1;
     ShopMenu shopMenu;
     ExamineMenu examineMenu;
     
     boolean shopmode;
     
     int updatenow = 0;
     
    private void listJobs() {
        jobbox.setText(this.world.getJobs());
    }
     
      class TimerListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
             update();
        }
    
    
    }
      
    class ZoomListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
           
          maincanvas.zoom();
         // maincanvas.repaint();
        }
    }
    
     
    private void update() {
        
        if(paused==false)
        {
        if(updatenow==4)
        {this.world.Update();
            listJobs();
        updatenow = 0;
        }else 
        {
            updatenow++;
        }
                        
        }
        
        try{
        if(maincanvas.getMousePosition()!=null)
        {
        maincanvas.setCurx(maincanvas.getMousePosition().x);
        maincanvas.setCury(maincanvas.getMousePosition().y);
        } 
        }catch(Exception e){}
        
        maincanvas.repaint();
        
    }
    
    class MainWindowAdapter extends WindowAdapter
    {
     
        @Override
           public void windowClosing(WindowEvent e) {
      //  ablak.canvas.screenshot(null);
        
        Window ee = e.getWindow();
        terminateLoop();
         ee.dispose();
         }
        
    
    }
    
    class MyMouseListener extends MouseAdapter
    {

        @Override
        public void mousePressed(MouseEvent e) {
              
            
            if(examineMenu.isDisplaying()==true)
            {
                examineMenu.setDisplaying(false);
              //  maincanvas.repaint();
                return;
            }
            
             if(shopMenu.isDisplaying()==true)
            {
                shopMenu.setDisplaying(false);
              //  maincanvas.repaint();
                return;
            }
            
            if(e.getButton()== MouseEvent.BUTTON1) 
            {
               
                
            if(shopmode)
            {
            maincanvas.setCurx(e.getX());
            maincanvas.setCury(e.getY());
            
            shopMenu.placeonTile(e.getY()/maincanvas.vport.getTilesize()+maincanvas.vport.getOffy(), e.getX()/maincanvas.vport.getTilesize()+maincanvas.vport.getOffx(), thinglist.getSelectedIndex());
           
            
            return;
            } 
            
            if(notdesignating==true) return;
                
            maincanvas.setCurx(e.getX());
            maincanvas.setCury(e.getY());
            
            if(currdesignateType == true)
            {  
            world.designateTask(e.getY()/maincanvas.vport.getTilesize()+maincanvas.vport.getOffy(), e.getX()/maincanvas.vport.getTilesize()+maincanvas.vport.getOffx(), TaskEnum.digging);
            }
            else
            { 
            world.designateTask(e.getY()/maincanvas.vport.getTilesize()+maincanvas.vport.getOffy(), e.getX()/maincanvas.vport.getTilesize()+maincanvas.vport.getOffx(), TaskEnum.walling);
            }
             listJobs();
            maincanvas.repaint();
            
            }
            
            if(e.getButton()==MouseEvent.BUTTON3)
            {
            notdesignating=true;
            designateButton.setLabel("No designation");
            
           paused = true;
           pauseButton.setLabel("Pause " + paused);
           
           Tile tile =  world.getTile(e.getY()/maincanvas.vport.getTilesize()+maincanvas.vport.getOffy(), e.getX()/maincanvas.vport.getTilesize()+maincanvas.vport.getOffx());
          
           if(shopmode==false)
           {
            thinglist.removeAll();
           if(tile.getCreature().isEmpty()==false || tile.getItem().isEmpty()==false) 
                {
      ArrayList<String> tlist = (ArrayList<String>) examineMenu.selectTile(e.getY()/maincanvas.vport.getTilesize()+maincanvas.vport.getOffy(), e.getX()/maincanvas.vport.getTilesize()+maincanvas.vport.getOffx());
        
           for(int i = 0; i<tlist.size(); i++)
              thinglist.add( tlist.get(i));
           
                }
           }
            
            
            }
               
        }
        
    }
    
    class DesignateButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
          
            shopmode = false;
            shopButton.setLabel("Buy Stuff");
            thinglist.removeAll();
            
            if(notdesignating==false)
            {
           if(currdesignateType==true) currdesignateType = false;
           else currdesignateType = true;
           
           if(currdesignateType==true)
           designateButton.setLabel("Mining mode");
           else
           designateButton.setLabel("Walling mode");
            }
            else
            {
            notdesignating = false;
            designateButton.setLabel("Mining mode");
            currdesignateType = true;
            }
        }
    }
    
        class PauseButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
           pause();
        }
    }
        
         class StepButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            System.out.println("pressed step");
           if(paused==true) 
               update();
           
          
        }
    }
         
            class SpawnButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            
          world.addRandomEntity(AIenum.gangmember);
          update();
        }
    }
            
      class BugSpawnButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            
          world.addRandomEntity(AIenum.annoybug);
          update();
        }
    }
      
      class ScrollListener implements KeyListener
      {

        @Override
        public void keyTyped(KeyEvent e) {
           // throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void keyPressed(KeyEvent e) {
            
            switch(e.getKeyCode())
            {
                case KeyEvent.VK_W:
                case KeyEvent.VK_UP: maincanvas.vport.scroll(Directions.N); break;
                case KeyEvent.VK_S:    
                case KeyEvent.VK_DOWN: maincanvas.vport.scroll(Directions.S);  break;
                case KeyEvent.VK_A:    
                case KeyEvent.VK_LEFT: maincanvas.vport.scroll(Directions.W); break;
                case KeyEvent.VK_D:    
                case KeyEvent.VK_RIGHT: maincanvas.vport.scroll(Directions.E); break;
                case KeyEvent.VK_SPACE: pause(); break;
            }
            maincanvas.repaint();
           // throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void keyReleased(KeyEvent e) {
           // throw new UnsupportedOperationException("Not supported yet.");
        }
      
      
      
      
      }
      
      
         class SelectButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
         if(thinglist.getItemCount()!=0)
            {
                int sel = thinglist.getSelectedIndex();
                if(sel==-1) return;
                
                if(shopmode==false)
                { 
                examineMenu.setDisplaying(true); 
                examineMenu.selectThing(sel);
                }
                else
                {
                shopMenu.setDisplaying(true); 
                shopMenu.selectThing(sel);
                }
            }
        }
    }
         
               class ShopButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
              
           if(shopmode==false) 
           {
               notdesignating = true;
               designateButton.setLabel("No designation");
               
               shopmode=true;
               paused = true;
               shopButton.setLabel("Stop buying stuff");
               thinglist.removeAll();
               
              ArrayList<String> tlist =  (ArrayList<String>) shopMenu.getShoplist();
           for(int i = 0; i<tlist.size(); i++)
              thinglist.add( tlist.get(i));
               
           }
           else
           {
               shopButton.setLabel("Buy stuff");
               paused = false;
               shopmode=false;
               thinglist.removeAll();
           }
           pauseButton.setLabel("Pause " + paused);
        }
    }
    
        class SaveButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) { 
            world.saveWorld();
        }
    }           
     
         class LoadButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) { 
            world.loadWorld();  
        }
    } 
               
    GameWindow()
        {
        
            init();
        
        this.addWindowListener(new MainWindowAdapter());
        
        this.setLayout(new BorderLayout());
        
            canvasPanel = new Panel();
             canvasPanel.setFocusable(false);
            buttonPanel = new Panel(); 
            buttonPanel.setFocusable(false);
            gamePanel = new Panel();
            listPanel = new Panel();
           listPanel.setFocusable(false);
            
           
            
            pauseButton = new Button("Pause " + paused);
            pauseButton.setFocusable(false);
            stepButton = new Button("Step");
            stepButton.setFocusable(false); 
            
             devpanel = new Panel();
             devpanel.setFocusable(false);
              
            designateButton = new Button("No designation");
            designateButton.setFocusable(false);
            spawnButton = new Button("Spawn testbeast");
            spawnButton.setFocusable(false);
             bugspawnButton = new Button("Spawn bug"); 
             bugspawnButton.setFocusable(false);
             
             zoomButton = new Button("Zoom in/out");
             zoomButton.setFocusable(false); 
            
            this.add("Center", gamePanel); 
             
            gamePanel.setLayout(new BorderLayout());
            gamePanel.add("Center", canvasPanel);
            
            maincanvas = new MainCanvas(world, shopMenu, examineMenu);
            maincanvas.setFocusable(true);
            
            
            canvasPanel.add(maincanvas);
            maincanvas.setSize(600, 600);
            maincanvas.vport.setWidth(600);
            maincanvas.vport.setHeight(600);
            
            gamePanel.add("West", buttonPanel);
            buttonPanel.setLayout(new GridLayout(0,1));
            buttonPanel.add(pauseButton);
            buttonPanel.add(stepButton);
            buttonPanel.add(designateButton);
            buttonPanel.add(spawnButton); 
            buttonPanel.add(bugspawnButton);
            buttonPanel.add(zoomButton);
            
            saveButton = new Button("SAVE");
            saveButton.setFocusable(false);
            loadButton = new Button("LOAD");
            loadButton.setFocusable(false);
            buttonPanel.add(saveButton);
            buttonPanel.add(loadButton);
            loadButton.addActionListener(new LoadButtonListener());
            saveButton.addActionListener(new SaveButtonListener());
            
            thinglist = new List();
            thinglist.setFocusable(false);
            examineButton = new Button("Examine selected");
            examineButton.setFocusable(false);
            
            shopButton = new Button("Buy Stuff");
            shopButton.setFocusable(false);
             
            listPanel.setLayout(new GridLayout(0,1));
            listPanel.add(shopButton);
            listPanel.add(thinglist);
            listPanel.add(examineButton);
            gamePanel.add("East", listPanel);
            
            
          //   this.add("East", devpanel);
            
            devpanel.setLayout(new BorderLayout());
           
            jobbox = new TextArea("");
            jobbox.setFocusable(false);
            devpanel.add("Center", jobbox);
            devpanel.setBounds(0, 0, 30, 620);
            
            this.setBounds(100, 0, 620, 620);
            
            this.setResizable(false);
            this.setVisible(true);
            this.pack();
            
          maincanvas.addMouseListener(new MyMouseListener());  
          
           designateButton.addActionListener(new DesignateButtonListener());
          pauseButton.addActionListener(new PauseButtonListener());
          stepButton.addActionListener(new StepButtonListener());
          
           spawnButton.addActionListener(new SpawnButtonListener());
          bugspawnButton.addActionListener(new BugSpawnButtonListener());
          
          examineButton.addActionListener(new SelectButtonListener());
          shopButton.addActionListener(new ShopButtonListener());
          
          zoomButton.addActionListener(new ZoomListener());
          
          maincanvas.addKeyListener(new ScrollListener());
          
          
        }
    
     private void init()
    {
     running= false;      
     world = new World(); 
     currdesignateType = true;
     paused = false; 
     notdesignating = true; 
     timer1 = new Timer(50, new TimerListener()); 
     shopMenu = new ShopMenu(world);
     examineMenu = new ExamineMenu(world);
     
     shopmode = false;
    };
    
        
      public void run()
    {  
   timer1.start();
     }
     
     
    /*
     * Terminates the game loop
     */
      public void  terminateLoop()
    {   
       exit= true;
       timer1.stop();
      // System.out.println("Bye");
    };
      
       void pause()
       {
        
           if(paused==true){
               paused = false;
               if(shopmode==true)
               {
               shopmode=false;
               shopButton.setLabel("Buy stuff");
               examineMenu.setDisplaying(false); 
               }
               
               if(thinglist.getItemCount()!=0)
                thinglist.removeAll();
           }
           else
           {
               paused = true;
              
           }
           pauseButton.setLabel("Pause " + paused);
       
       }
}
