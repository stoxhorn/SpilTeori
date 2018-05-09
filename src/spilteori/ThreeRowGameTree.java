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
    
    /**Recursively adds a new move, as long as there is moves left, and the game has not been lost yet
     * 
     * Takes a Node as a parent.
     * Adds the children Nodes, And their respective Fields
     * 
     * @param moveLeft
     * @param parenttheir
     * @param posGame 
     */
    private void addNodes(GameNode currentNode, Board posBoard)
    {
        // Copy the given Game
        Board tmpBoard = new ThreeBoard(posBoard);
        
        // Getting the depth of the parent 
        int depth = currentNode.getDepth();
        
        // get the amount of moves left
        int moveLeft = posBoard.getEmptyFields().size();
        //System.out.println("moveLeft: " +moveLeft);
        
        // Create an int[] of the same length of the amount of players
        int[] newWinValue = new int[2];
        
        // Need to account for depth
        // If the given game contains a winner:
        if(posBoard.checkWin((depth+1)%2+1))
        {
            //System.out.println("checkwin");
            // Winner of GameNode parent is the player = checkWin()
            // Add a winning chance of 10000 to the corresponding winner from checkWin()
            
            // Uses depth to determine player
            // 1 for a win
            if ((depth % 2)+1 == 1) {
                newWinValue[0] = 2;
            }
            else {
                newWinValue[0] = 1;
            }
            newWinValue[1] = depth;
            // Adds the chances to the Node at the current index
            Tree.get(Cursor).setWinValue(newWinValue);
            
            // and then return
            return;
        }
        
        // If no more fields are left, and there has not been found a winner:
        else if(moveLeft < 1)
        {
            newWinValue[0] = 0;
            newWinValue[1] = depth;
            // Add zero for losing as nobody won 
            Tree.get(Cursor).setWinValue(newWinValue);
            
            // since this block only activates, if no possible winner is found, therefore return
            return;
        }
        
        // The array of children, to be added to the given node
        GameNode[] newChildren = new GameNode[moveLeft];
        
        // Creating the array of Fields to add as children
        ArrayList<Field> emptyFields = posBoard.getEmptyFields();
        
        // Needs to loop as many times as moves left
        // setChildren takes an array of nodes, initialized before loop
        // Need an int that increments for each loop to add to the array of newChildren
        int i = 0;
        for(Field newField : emptyFields)
        {
            // Each field from emptyFields gets called in the loop
            
            // Each loop increments the newCursor, as it gets added to the tree
            // Without moving the Cursor to a new index
            Cursor ++;
            
            // The new node
            ThreeNode child = new ThreeNode(newField, Cursor, currentNode);
           
            // Sets the depth of the node to be +1 from the current depth
            child.setDepth(depth+1);
            
            // Adds the child to the tree
            Tree.add(child);
            
            // Adds the child to the new children, and increments i
            newChildren[i] = child;      
            //System.out.println("depth: " +depth);
            Board tmp = new ThreeBoard(tmpBoard);
            
            //System.out.println("Position: " + child.getField().getPos());
            //System.out.println("Value: " + child.getField().getValue());
            tmp.newMove((depth%2)+1, child.getField());
            
            //System.out.println(currentNode.getPosMove().getValue());
            //System.out.println(tmp.toString());
 
            addNodes(child, tmp);
            
            i++;
        }
        
        // sets the new children as the children of the given Parent Node
        currentNode.setChildren(newChildren);
        
        // Increments the cursor
        
        
        
        // Adds the move of the parent to the possible board,
        // in order to keep track of which Fields has been added,
        // so that a field that has already been added, does not get added again
        
        // Will add a move to the board for now, until a method is implemented 
        // Currently returns an arraylist, which is added to, and as such nothing happens
        
        
        
        
        
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
    public int[] calculateMinMax(GameNode givenNode)
    {
        // Create copy of given Node
        GameNode localNode = givenNode;
        

        // Needs to be redone so that it accounts for depth
        
        // this is depth x
        // if depth x+2 contains a win for opponent
        // make x+1 a loss
        // if depth x+1 contains a win for self, make x-1 a win
        
        if (localNode.getWinValue()[0] != -1)
        {
            return localNode.getWinValue();
        }
        else{
        // Getting an array of children
        GameNode[] childArray = localNode.getChildren();
        
        // Initializing a 2d int array
        int[][] childrenChances = new int[childArray.length][2];
        
        
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
        int[] newWinValue = minMax(childrenChances, givenNode.getDepth());
        
        // i get the index to set the appropriate node in the tree
        int index = localNode.getIndex();
        
        // And finally i set the chances for the appropriate GameNode
        Tree.get(index).setWinValue(newWinValue);
        
        // and finally i return the chances
        return newWinValue;
        }
        
    }
    
    //returns the best chance for the player who will play on the given turn
    public int[] minMax(int[][] childrenChances, int depth) {
        ArrayList<int[]> List1 = new ArrayList<>();
        ArrayList<int[]> List2 = new ArrayList<>();
        ArrayList<int[]> List3 = new ArrayList<>();
        
        
        
        
        if ((depth%2)+1 == 1) {
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
        else {
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
        
        int[] returnValue = null;
        
        if(!List1.isEmpty()){
            for(int[] x : List1){
                if (returnValue == null || x[1] < returnValue[1]){
                    returnValue = x;
                }
            }
            return returnValue;
        }else if(!List2.isEmpty()){
            for(int[] x : List2){
                if (returnValue == null || x[1] < returnValue[1]){
                    returnValue = x;
                }
            }
            
            return returnValue;
        }else if(!List3.isEmpty()){
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
