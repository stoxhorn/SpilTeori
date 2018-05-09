/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spilteori;

import java.util.ArrayList;

/**
 * A gameTree for Three in a row
 * 
 */
public final class ThreeRowGameTree implements GameTree {

    
    // The arraylist containing all the Nodes
    private final ArrayList<GameNode> Tree;
    
    // A cursor that points at the current index of the tree, with the index being of the arraylist "Tree"
    private int Cursor;
    
    // The game this tree belongs to, barely used
    private final Game MainGame;
    
    // Root node containing a field that cannot exist, and a Node that is not inside the array
    // has a value of -1, as it does not have a value yet
    private final ThreeNode RootNode; 
    
    public ThreeRowGameTree(Game newGame)
    {
        // initialize the arraylist tree
        this.Tree = new ArrayList<>();
        
        // Set the cursor at start
        Cursor = 0;
        
        // Store the game from the constructor
        MainGame = newGame;
        
        // Creates the rootnode
        RootNode = new ThreeNode(new ThreeField(-1,0,0,0), 0, null);
        
        // Creates the tree 
        this.createTree(newGame);
        
        // Calcutales the minmax, and provides a winvalue for all the nodes in the tree
        calculateMinMax(RootNode);

        
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
        Board posBoard = new ThreeBoard(newGame.getBoard());
        
        // Fills out the Tree
        addNodes(Tree.get(0), posBoard);
    }
    
    /**
     * Recursively adds a new move, as long as there is moves left, and the game has not been lost yet
     * 
     * Takes a Node as the current node, which it bases the tree from, as this will be the starting node.
     * Adds the children Nodes, And their respective Fields
     * 
     * @param moveLeft
     * @param parenttheir
     * @param posGame 
     */
    private void addNodes(GameNode currentNode, Board posBoard)
    {
        // Copy the given board
        Board tmpBoard = new ThreeBoard(posBoard);
        
        // Getting the depth of the currentNode 
        int depth = currentNode.getDepth();
        
        // Getting the player's who's turn it was for the currentNode
        int player = currentNode.getPlayer();
        
        // get the amount of moves left
        int moveLeft = posBoard.getEmptyFields().size();
        
        // Create an int[] of the same length of the amount of players
        int[] newWinValue = new int[MainGame.getPlayerAmount()];
        
        
        // If the given game contains a winner for the given player:
        if(posBoard.checkWin(player))
        {
            
            // Sets the win value to the currentPlayer
            newWinValue[0] = player;
            
            // adds the depth to the winvalue array
            newWinValue[1] = depth;
            
            // adds the winvalue to the actual node in Tree
            Tree.get(Cursor).setWinValue(newWinValue);
            
            // returns, as the game has won, and no more nodes needs to be added.
            return;
        }
        
        // If no more fields are left, and there has not been found a winner:
        else if(moveLeft < 1)
        {
            // Add zero as win value, as it's a draw
            newWinValue[0] = 0;
            
            // and set the depth
            newWinValue[1] = depth;
            
            // Sets the winvalue to the actual node in the tree
            Tree.get(Cursor).setWinValue(newWinValue);
            
            // returns as there are no more possible moves left
            return;
        }
        
        // The array of children, to be added to the given node
        GameNode[] newChildren = new GameNode[moveLeft];
        
        // Getting the empty fields which also equates to possible moves
        ArrayList<Field> emptyFields = posBoard.getEmptyFields();
        
        // A loop that adds an empty field to a new board, that is used for another call of addNodes
        // such that a new node represents a change in the board
        int i = 0;
        for(Field newField : emptyFields)
        {
           
            
            
            // Incrementing the cursor, as a recursive call will continue all the way to the first time a game ends
            // and when it goes a call depth up, will add to the next index in the array
            // Point is, Cursor points to index in array, and not to a depth or place in abstract tree, which the list is supposed to represent
            Cursor ++;
            
            // Constructing a new node, with the parameters gotten so far
            // the empty field representing it's move
            // the cursor to represents it's placement
            // and it's parent which is the currentNode
            ThreeNode child = new ThreeNode(newField, Cursor, currentNode);
           
            // Sets the depth of the node to be +1 from the current depth
            child.setDepth(depth+1);
            
            // Adds the child to the tree
            Tree.add(child);
            
            // Adds the child to the array of children, and increments i
            newChildren[i] = child;      
            i++;
            
            // copies the given board, so that the given board can be reused for the next loop
            Board tmp = new ThreeBoard(tmpBoard);
            
            // adds a new move of the current player to the new child created in this loop
            tmp.newMove(player, child.getField());
            
            // Does a recursive call to finish a branch of the tree
            addNodes(child, tmp);
        }
        
        // As the loop has now created all the children for currentNode, they can be added to the currentNode
        currentNode.setChildren(newChildren);
    }
   
    
    
    /**
     * A recursive method that adds the winValue to the given node, by going through each node below this one
     * 
     * Adds a winValue to the given node, by going through each node below it, down to every leaf, and calculated each winValue,
     * using minMax, and adding to each value, making sure that each node below the first call has a winValue
     * 
     * @param givenNode
     * @return 
     */
    public int[] calculateMinMax(GameNode givenNode)
    {
       
        // Create copy of given Node
        GameNode localNode = givenNode;
        
        // If the given node is a leaf, and as such is already given a winValue from the createTree method
        if (localNode.getWinValue()[0] != -1)
        {
            // Return the currently already stored winValue
            return localNode.getWinValue();
        }
            
        // Gets the children of the given node
        GameNode[] childArray = localNode.getChildren();
        
        // Initializes a 2d array to contain the new chances of the children, with a size of chridren and 2
        // as winValue contains 2 values to describe and priritize a winValue
        int[][] childrenChances = new int[childArray.length][2];
        
        
        // The recursive call on each children
        // adding to the return to the next index for each loop
        // as this node does not have a winValue already
        int i = 0;
        for(GameNode x : childArray)
        {
           
            childrenChances[i] = calculateMinMax(x);
            i++;
        }
        
        // The method that calculates the current chances of winning for this array
        int[] newWinValue = minMax(childrenChances, givenNode.getPlayer());
        
        // Getting the index of the currentNode
        // so that i can add the result to the ACTUAL node in the Tree.
        int index = localNode.getIndex();
        
        // And finally i set the chances for the appropriate GameNode
        Tree.get(index).setWinValue(newWinValue);
        
        // and finally i return the chances
        return newWinValue;
    }
    
    // Returns the value the player taking a turn at the given depth will prioritize, 
    public int[] minMax(int[][] childrenChances, int player) {
        
        // initializing the three arayLists for getting optimal depth
        ArrayList<int[]> List1 = new ArrayList<>();
        ArrayList<int[]> List2 = new ArrayList<>();
        ArrayList<int[]> List3 = new ArrayList<>();
        
        
        
        // If given player is player 1
        if (player%2 == 1) {
            // The loop adding the amount of each winValue of the children to the appropriate lists
            for (int[] chance : childrenChances) {                
                switch (chance[0]) {
                    case 1:
                        List1.add(chance);
                        break;
                    case 0:
                        List2.add(chance);
                        break;
                    default:
                        List3.add(chance);
                        break;
                }
            }
        }
        // If the given player is player 2
        else {
            // The loop adding the amount of each winValue of the children to the appropriate lists
            for (int[] chance : childrenChances) {                
                switch (chance[0]) {
                    case 2:
                        List1.add(chance);
                        break;
                    case 0:
                        List2.add(chance);
                        break;
                    default:
                        List3.add(chance);
                        break;
                }
            }
        }
        
        // Initializing the value to be returned, null as default
        int[] returnValue = null;
        
        // If the list of the most important value is not empty
        if(!List1.isEmpty()){
            // set returnValue to be the one with the lowest depth of the list
            for(int[] x : List1){
                if (returnValue == null || x[1] < returnValue[1]){
                    returnValue = x;
                }
            }
            return returnValue;
        }
        // Else if the list of the second most important value is not empty
        else if(!List2.isEmpty()){
            // set returnValue to be the one with the lowest depth of the list
            for(int[] x : List2){
                if (returnValue == null || x[1] < returnValue[1]){
                    returnValue = x;
                }
            }
            
            return returnValue;
        }
        // Else if the list of the second most important value is not empty
        else if(!List3.isEmpty()){
            // set returnValue to be the one with the lowest depth of the list
            for(int[] x : List3){
                if (returnValue == null || x[1] < returnValue[1]){
                    returnValue = x;
                }
            }
            return returnValue;
        }
        return returnValue;
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
