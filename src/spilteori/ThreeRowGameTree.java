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
    // empty Field
    ThreeNode RootNode;
    
    public ThreeRowGameTree(Game newGame)
    {
        Cursor = 0;
        
        RootNode = null;
        
        MainGame = newGame;
        
        this.createTree(newGame.getBoard(), newGame);
        
    }
    
    @Override
    public ArrayList<GameNode> getTree() {
        ArrayList tmp = Tree;
        return tmp;
    }

    @Override
    public GameNode getParent() {
        GameNode tmp = RootNode;
        return tmp;
    }

    @Override
    public void createTree(ArrayList<Field> GameBoard, Game newGame)
    {
        // Create the RootNode node
        Tree.add(RootNode);
        
        // Gets the amount of possible moves left, in other words, the amount of empty fields
        // NOTE: ==========================================================================================================================================================================================================
        // Needs changing if the game can go on forever!
        int movesLeft = newGame.getEmpty();
        
        // Resets the cursor to first index
        Cursor = 0;
        
        // Creates a new game object identical to the given one
        Game posGame = newGame;
        
        // Fills out the Tree
        addNodes(movesLeft, Tree.get(0), posGame);
    }
    
    /**Recursively adds a new move, as long as there is moves left, and the game has not been lost yet
     * 
     * Takes a Node as a parnet.
     * Adds the children Nodes, And their respective Fields
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
            
            // NOTE: ==========================================================================================================================================================================================================
            // Needs change, as we do not work with chances with minmax
            newChances[checkWin(posGame)] = 10000;
            
            // Adds the chances to the Node at the current index
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
        
        // The array of children, to be added to the given node
        GameNode[] newChildren = new GameNode[moveLeft];
        
        // need to store the current index represented by Cursor
        // As new nodes require the index they are stored in in the Tree
        int newCursor = Cursor;
        
        // Needs to loop as many times as moves left
        // setChildren takes an array of nodes, initialized before loop
        for(int i = 0; i < moveLeft; i++)
        {
            // Add a New Node to the newChildren array
            // i equates to the index of the children array
            
        // Creating the new GameNode Object
            
            // Requires getting the next empty field from the board
            // NOTE: ==========================================================================================================================================================================================================
            // creating an emptyfield here,
            // since there is currently no way to get a list of currently empty field on the board
            // I take the last Field on the Board
            Field emptyField = posGame.getBoard().get(posGame.getBoard().size()-1);
        
            // I need to increment Curor, as i am adding a node to Tree
        
            
            
            // NOTE: ==========================================================================================================================================================================================================
            // the game interface, returns an arraylist, and not an array of fields
            // NOTE: ==========================================================================================================================================================================================================
            // constructor takes a ThreeNode, and not a GameNode
            ThreeNode child = new ThreeNode(emptyField, Cursor, parent);
            
            // Adds the child to the tree
            Tree.add(child);
            
            // Adds the child to the new children
            newChildren[i] = child;            
        }
        
        // sets the new children as the children of the given Parent Node
        parent.setChildren(newChildren);
        
        // Increments the cursor
        Cursor ++;
        
        // Decrements the amount of moves left
        moveLeft --;
        
        // Adds the move of the parent to the possible board,
        // in order to keep track of which Fields has been added,
        // so that a field that has already been added, does not get added again
        
        // NOTE:==========================================================================================================================================================================================================
        // Need a method to add a move on the game, instead of only on the Board
        
        // Will add a move to the board for now, until a method is implemented 
        // Currently returns an arraylist, which is added to, and as such nothing happens
        tmpGame.getBoard().add(parent.getPosMove());
        
        // A loop that calls itself on each child, iterating through the array
        // This should create each new branch
        for(GameNode x : parent.getChildren())
        {
            // Note:==========================================================================================================================================================================================================
            // I need a getter for node, to know which index it is stored at
            addNodes(moveLeft, Tree.get(x.getIndex()),tmpGame);
        }
        
        
    }
   
    
    //NOTE: ==========================================================================================================================================================================================================
    // have to decide if we are going to call this method on each Node, and using a setter for the return value, or make it a void method
    // Current implementation will essentially be a void recursive method, that returns the chances of the given Node, but will not go deeper than a GameNode with already established chances
    
    
    /** returns the chance of the given childNodes chances, of the corresponding player index
     * 
     * Currently:
     *      Takes a GameNode, as these point to subnodes, this method iterates through these to calculate chances
     *      Can be used as a void method on the RooteNode, or on a given Node, and it will return and/or calculate the given nodes chance, and it's children if necessary
     *      
     *      in short:
     *          If given node contains chances, return them.
     *          Otherwise it will call this method on children Nodes, until nodes with chances are found for each branch of calls.
     *          And these recursions will be used to calculate the given Nodes chacnes, and return them
     * 
     * @param givenNode
     * @return 
     */
    public int[] calculateChances(GameNode givenNode)
    {
        // Create copy of given Node
        GameNode localNode = givenNode;
        

        // NOTE:==========================================================================================================================================================================================================
        // Need some way to determine an empty chance array, this takes two rules, either each index has -1 value, or has a length of 0
        // Maybe just have a value that tells if this is an "endNode. "
        
        // Basecase; returns the chances of the given Node
        if (localNode.getChances().length != 0 || localNode.getChances()[0] != -1)
        {
            return localNode.getChances();
        }
        
        // Getting an array of children
        GameNode[] childArray = localNode.getChildren();
        
        // Initializing a 2d int array
        int[][] childrenChances = null;
        
        
        // Calling this method on each child
        // and setting the return value to the apropriate indexes in the 2d array
        int i = 0;
        for(GameNode x : childArray)
        {
            childrenChances[i] = calculateChances(x);
            i++;
        }
        
        
        // Need some way to calculate the chances
        // NOTE: ==========================================================================================================================================================================================================
        // Currently i just make up some random-ass method, that returns an int[], as there is no existing method
        int[] newChances = assChanceMethod(childrenChances);
        
        // i get the index to set the appropriate node in the tree
        // NOTE: ==========================================================================================================================================================================================================
        // I need a getter for index of node
        int index = localNode.getIndex();
        
        // And finally i set the chances for the appropriate GameNode
        // NOTE: ==========================================================================================================================================================================================================
        // No setter for chances in gameNode
        Tree.get(index).setChances(newChances);
        
        // and finally i return the chances
        return newChances;
    }

    @Override
    public Field getBestMove()
    {
        // NOTE:==========================================================================================================================================================================================================
        // The current implementation will return the best move to take, from the current index
        Field bestMove;
        
        GameNode thisNode = Tree.get(Cursor);
        
        // NOTE:==========================================================================================================================================================================================================
        // Currently takes an array of GameNodes, with index equalling player, this is subject to change, as player decider has not been made yet
        GameNode[] chances = thisNode.getOptimal();
        
        // NOTE:==========================================================================================================================================================================================================
        // Temporary solution to getting an int relating to players, this just gets the amount of players, and as such always the last player
        int player = MainGame.getPlayerAmount();
        
        // Sets the bestMove to be the optimal move of the given player
        bestMove = chances[player].getPosMove();
        
        return bestMove;
    }

    @Override
    public int getCursor()
    {
        int tmp = Cursor;
        
        return tmp;
    }

    @Override
    public void setCursor(Field newMove) {
        // NOTE:==========================================================================================================================================================================================================
        // Cannot be done without a new method in Fields
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
        // I need the board that i need to check on before i can check it's win.
        // NOTE:==========================================================================================================================================================================================================
        // The rule for winning is stated in Game class?
        // NOTE:==========================================================================================================================================================================================================
        // Then whats the fucking point of having this method?
        posGame.getBoard();
    }
    
}
