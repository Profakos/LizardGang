/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg30dungeon;

/**
 *
 * @author Akos
 */
public class Main {

    private GameWindow game;
    
    Main(){game = new GameWindow();}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         Main game1 = new Main();
         game1.game.run();
    }
}
