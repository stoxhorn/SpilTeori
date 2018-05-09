
package spilteori;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/** GameTree contains a Tree representing the chance each player has of winning.
 * 
 * Contains a tree representing all possible moves of a game
 * Constructed in such a way, that each Node links to all Possible next moves, But has only one Parent
 * Therefore eachs Game has only One Parent Node
 *
 * Each node contains the winchance for each person
 * 
 * A variable Cursor, an int, points to the current index of the CurrentGame,
 * which leads to the index of the appropriate GameNode
 * 
 */
public interface GameTree {
    
    /**
     * Returns the ArrayList containing all the GameNodes of the game
     * 
     * @return ArrayList<GameNode> GameTree of the Gamed
     */
    public ArrayList<GameNode> getTree();
    
    /**
     * The Parent of all Nodes, is always an Empty Board, so there is only One Parent.
     * 
     * @return GameNode that is the Parent for all Nodes in this Tree
     */
    public GameNode getParent();
    
    /**
     * Creates the Tree for the respective game
     * 
     * @param newGame
     */
    public void createTree(Game newGame);
    
    /**
     * Figures out the best move, for the current cursor of the Tree
     * 
     * Returns an exact copy of the next Optimal Move,
     * Meaning position is the same as the Field that is to be inserted into,
     * And it's value is that of the Optimal Move
     * 
     * @return Field A Field object, identical to the optimal move
     */
    public Field getBestMove();
    
    /**
     * Getter for the int pointing to the current index 
     * 
     * @return int pointing to current index
     */
    public int getCursor();
    
    /**
     * Points the Cursor to the new given Field
     * 
     * @param newCursor
     */
    public void setCursor(int newCursor);
    
    /**
     * gets the node stored at the given index
     * @param index
     * @return 
     */
    public GameNode getNode(int index);
    
    
    
    
}
