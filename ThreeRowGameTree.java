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

    private ArrayList<GameNode> Tree;
    
    private int Cursor;
    
    private final Game MainGame;
    
    // An empty Field
    // empty Field
    private ThreeNode RootNode;
    
    public ThreeRowGameTree(Game newGame)
    {
        Cursor = 0;
        
        RootNode = null;
        
        MainGame = newGame;
        
        this.createTree(newGame);
        
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
    public void createTree(Game newGame) 
    {
        // Create the RootNode node
        Tree.add(RootNode);
        
        // Resets the cursor to first index
        Cursor = 0;
        
        // Creates a new game object identical to the given one
        Game posGame = newGame;
        
        // Fills out the Tree
        addNodes(Tree.get(0), posGame);
    }
    
    /**Recursively adds a new move, as long as there is moves left, and the game has not been lost yet
     * 
     * Takes a Node as a parnet.
     * Adds the children Nodes, And their respective Fields
     * 
     * @param moveLeft
     * @param parenttheir
     * @param posGame 
     */
    private void addNodes(GameNode parent, Game posGame)
    {
        // Copy the given Game
        Game tmpGame = posGame;
        
        // Getting the depth of the parent 
        int depth = parent.getDepth();
        
        // get the amount of moves left
        int moveLeft = posGame.getEmptyFields().size();
        
        // Create an int[] of the same length of the amount of players
        int newWinValue;
        
        // Inserting a number to represent that it is an unfilled chance
        newWinValue = -1;
        
        // Need to account for depth
        // If the given game contains a winner:
        if(posGame.checkWin())
        {
            // Winner of GameNode parent is the player = checkWin()
            // Add a winning chance of 10000 to the corresponding winner from checkWin()
            
            // Uses depth to determine player
            // 1 for a win
            if (depth % 2 == 0) {
                newWinValue = 1;
            }
            else {
                newWinValue = 2;
            }
            
            // Adds the chances to the Node at the current index
            Tree.get(Cursor).setWinValue(newWinValue);
            
            // and then return
            return;
        }
        
        // If no more fields are left, and there has not been found a winner:
        else if(moveLeft < 1)
        {
            newWinValue = 0;
            // Add zero for losing as nobody won 
            // int[] are initialized with 0 in each index, and i've jsut changed the first index back to 0
            Tree.get(Cursor).setWinValue(newWinValue);
            
            // since this block only activates, if no possible winner is found, therefore return
            return;
        }
        
        // The array of children, to be added to the given node
        GameNode[] newChildren = new GameNode[moveLeft];
        
        // need to store the current index represented by Cursor
        // As new nodes require the index they are stored in in the Tree
        int newCursor = Cursor;
        
        // Creating the array of Fields to add as children
        ArrayList<Field> emptyFields = posGame.getBoard().getEmptyFields();
        
        // Needs to loop as many times as moves left
        // setChildren takes an array of nodes, initialized before loop
        // Need an int that increments for each loop to add to the array of newChildren
        int i = 0;
        for(Field newField : emptyFields)
        {
            // Each field from emptyFields gets called in the loop
            
            // Each loop increments the newCursor, as it gets added to the tree
            // Without moving the Cursor to a new index
            newCursor ++;
            
            // The new node
            ThreeNode child = new ThreeNode(newField, newCursor, parent);
           
            // Sets the depth of the node to be +1 from the current depth
            child.setDepth(depth+1);
            
            // Adds the child to the tree
            Tree.add(child);
            
            // Adds the child to the new children, and increments i
            newChildren[i] = child;            
            i++;
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
        
        // Will add a move to the board for now, until a method is implemented 
        // Currently returns an arraylist, which is added to, and as such nothing happens
        tmpGame.newMove(parent.getPosMove());
        
        // A loop that calls itself on each child, iterating through the array
        // This should create each new branch
        for(GameNode x : parent.getChildren())
        {
            // Note:==========================================================================================================================================================================================================
            // I need a getter for node, to know which index it is stored at
            addNodes(Tree.get(x.getIndex()),tmpGame);
        }
        
        
    }
   
    
    
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
    public int calculateMinMax(GameNode givenNode)
    {
        // Create copy of given Node
        GameNode localNode = givenNode;
        

        // Needs to be redone so that it accounts for depth
        
        // this is depth x
        // if depth x+2 contains a win for opponent
        // make x+1 a loss
        // if depth x+1 contains a win for self, make x-1 a win
        
        if (localNode.getWinValue() != -1)
        {
            return localNode.getWinValue();
        }
        else{
        // Getting an array of children
        GameNode[] childArray = localNode.getChildren();
        
        // Initializing a 2d int array
        int[] childrenChances = null;
        
        
        // Calling this method on each child
        // and setting the return value to the apropriate indexes in the 2d array
        // this is essentially trhe recursive part
        int i = 0;
        for(GameNode x : childArray)
        {
            childrenChances[i] = calculateMinMax(x);
            i++;
        }
        
        
        // A method that takes a depth and a set of chances
        // the 2d array, needs to end as a single array, that shows the win chances for the current depth
        int newWinValue = minMax(childrenChances, givenNode.getDepth());
        
        // i get the index to set the appropriate node in the tree
        int index = localNode.getIndex();
        
        // And finally i set the chances for the appropriate GameNode
        Tree.get(index).setWinValue(newWinValue);
        
        // and finally i return the chances
        return newWinValue;
        }
        
    }
    
    //returns the best chance for the player who will play on the given turn
    public int minMax(int[] childrenChances, int depth) {
        int player1 = 0;    //takes 1 as win
        int player2 = 0;    //takes 2 as win
        
        for (int chance : childrenChances) {
            if (depth % 2 == 0) {
                if (chance == 1) {
                    player1 = chance;
                }
                else if (chance == 0) {
                    player1 = chance;
                }
            }
            else {
                if (chance == 2) {
                    player2 = chance;
                }
                else if (chance == 0) {
                    player2 = chance;
                }
            }
        }
        
        if (depth % 2 == 0) {
            return player1;
        }
        else {
            return player2;
        }
    }

    @Override
    public Field getBestMove()
    {
        Field bestMove;
        
        GameNode thisNode = Tree.get(Cursor);
        
        GameNode chances = thisNode.getOptimal();
        
        // NOTE:==========================================================================================================================================================================================================
        // Temporary solution to getting an int relating to players, this just gets the amount of players, and as such always the last player
        int player = MainGame.getPlayerAmount();
        
        // Sets the bestMove to be the optimal move of the given player
        bestMove = chances.getPosMove();
        
        return bestMove;
}

    @Override
    public int getCursor()
    {
        int tmp = Cursor;
        
        return tmp;
    }

    @Override
    public void setCursor(int newCursor) {
        Cursor = newCursor;
    }

    @Override
    public GameNode getNode(int index) {
        GameNode tmp = Tree.get(index);
        return tmp;
    }

}
