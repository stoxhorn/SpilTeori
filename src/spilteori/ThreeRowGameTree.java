/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spilteori;

import java.util.ArrayList;

/**
 *
 * @author Stoxhorn
 */
public class ThreeRowGameTree implements GameTree {

    ArrayList<GameNode> Tree;
    
    int Cursor;
    
    Game MainGame;
    
    // An empty Field
    ThreeNode RootNode = ThreeNode(new Field());
    
    public ThreeRowGameTree(Game newGame)
    {
        Cursor = 0;
        
        MainGame = newGame;
        
        this.createTree(newGame.getBoard(), newGame);
        
    }
    
    @Override
    public ArrayList<GameNode> getTree() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GameNode getParent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createTree(ArrayList<Field> GameBoard, Game newGame)
    {
        // Create the RootNode node
        Tree.add(RootNode);
        int movesLeft = newGame.getEmpty();
        Cursor = 0;
        Game posGame = newGame;
        addNodes(movesLeft, Tree.get(0), posGame);
        
        
        
        
        
    }
    
    /**Recursively adds a new move, as long as there is moves left, and the game has not been lost yet
     * 
     * @param moveLeft
     * @param parent
     * @param posGame 
     */
    private void addNodes(int moveLeft, GameNode parent, Game posGame)
    {
        // Copy the given Game
        Game tmpGame = posGame;
        
        // Create an int[] of the same length of the amount of players
        int[] newChances = new int[tmpGame.getPlayerAmount()];
        
        // If the given game contains a winner:
        if(checkWin(posGame) > 0)
        {
            // Winner of GameNode parent is the player = checkWin()
            // Add a winning chance of 10000 to the corresponding winner from checkWin()
            newChances[checkWin(posGame)] = 10000;
            
            // Remember to call the parent node from the ArrayList, using cursor
            Tree.get(Cursor).setChances(newChances);
            
            // and then return
            return;
        }
        
        // If no more fields are left, and there has not been found a winner:
        else if(moveLeft < 1)
        {
            // Add zero chance of winning for every player to parent, int[] are initialized with 0 in each index
            Tree.get(Cursor).setChances(newChances);
            
            // since this block only activates, if no possible winner is found, therefore return
            return;
        }
        Cursor++;
        
        
        // Remember each node contains information for every player
        // Take parent, for each moveLeft, add a new Node as child
            // NodeX corresponds to FieldX
            // add this into the 
        

// Add a Node as next child to parent, remember this cannot be called if there is no room on the GameBoard
        // 

// Add Fields amounting to moveLeft, each addition in the next possible field, to tmpGame
        // Add a Node to Tree, corresponding to Field
        // with the RootNode set as the given RootNode
        // Index being the int Cursor
            // After each addition, Call recursively, but with moveLeft being one number lower,
            // parentNode being the recently addNode

    }
    
    /** returns the chance of the given childNodes chances, of the corresponding player index
     * 
     * Idea:
     * Recursively calculate the winChances of each node, Iterate through Nodes using Cursor,
     * has to be 0, when called the first time
     * 
     * Problem:
     * Relies on global variables, and is as such not properly resursive, and this can cause problem
     *      SubProblem:
     *      Cursor has to be global, making it potentially dumb to require parameters?
     * 
     * @return 
     */
    public int[] calculateChances()
    {
        // If Node at index of cursor has no children, simply return the chances of this Node
        // Remember to subtract from cursor before returning
        
        // If Node at index of cursor has Children, call this method recursivle on each child,
        // and calculate the chances of each player on this Node
        
        
    }

    @Override
    public Field getBestMove()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getCursor()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCursor(Field newMove) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**Needs to check if the game has a winner at the possible sequence at the given index
     * 
     * Idea:
     * Take a Game as parameter, and run a checkWin method from Game interface, and check fro winner
     * 
     * @return 
     */
    private int checkWin(Game posGame) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
