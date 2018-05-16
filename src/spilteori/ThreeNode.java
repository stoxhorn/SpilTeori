package spilteori;

import java.util.ArrayList;

public class ThreeNode implements GameNode{
    
    private int depth;

    private GameNode parent;
    
    private Field field;
    
    private final int index;
    
    private int[] winValue = new int[] {-1, 12};
    
    private GameNode[] children;
    
    //ThreeNode Object created through constructor
    public ThreeNode(Field newField, int newIndex, GameNode newParent)
    {
        this.field = new ThreeField(newField);  //Field given from constructor
        this.index = newIndex;                  //Index given from constructor
        this.depth = 0;                         //Default is 0
        this.parent = newParent;                //Parent given from constructor
        children = null;                        //Default is no children
    }


    
    //getter for field
    @Override
    public Field getPosMove() {
        return field;
    }

    //setter for field
    @Override
    public void setPosMove(Field newField) {
        field = newField;
        depth++;
    }
    
    //setter for winValue
    @Override
    public void setWinValue(int[] newWinValue) {
        winValue = newWinValue;
    }
    
    //getter for winValue
    @Override
    public int[] getWinValue() {
        return winValue;
    }
    
    //getter for player at current depth
    @Override
    public int getPlayer() {
        int tmp = (depth % 2) + 1;
        return tmp;
    }
    
    @Override
    public GameNode getOptimal(int player) {
        ArrayList<GameNode> childrenArray1 = new ArrayList<>();
        ArrayList<GameNode> childrenArray2 = new ArrayList<>();
        ArrayList<GameNode> childrenArray3 = new ArrayList<>();
        
        for (GameNode child : children) {
            if (child.getWinValue()[0] == player) {
                childrenArray1.add(child);
            }
            else if (child.getWinValue()[0] == 0){
                childrenArray2.add(child);
            }
            else if (child.getWinValue()[0] == (player % 2 + 1)) {
                childrenArray3.add(child);
            }
        }
        if (!childrenArray1.isEmpty()) {
            return getBestChild(childrenArray1, player);
        }
        else if (!childrenArray2.isEmpty()) {
            return getBestChild(childrenArray2, player);
        }
        else {
            return getBestChild(childrenArray3, player);
        }
    }
    
    //Returns the child with the lowest depth. Should only be called on the winning value for a player.
    private GameNode getBestChild(ArrayList<GameNode> childrenArray, int player) {
        GameNode bestChild = null;
        for (GameNode child : childrenArray) {
            //For the first for loop run
            if (bestChild == null) {
                bestChild = child;
            }
            else {
                if (bestChild.getWinValue()[0] == player && bestChild.getWinValue()[1] > child.getWinValue()[1]) {
                    bestChild = child;
                }
                else if (bestChild.getWinValue()[0] != player && bestChild.getWinValue()[1] < child.getWinValue()[1]) {
                    bestChild = child;
                }
            }
        }
        return bestChild;
    }   
    
    //getter for children
    @Override
    public GameNode[] getChildren() {
        return children;
    }
    
    //setter for children
    @Override
    public void setChildren(GameNode[] childrenArray) {
        children = childrenArray;
    }
    
    //getter for parent
    @Override
    public GameNode getParent() {
        return parent;
    }
    
    //setter for parent
    @Override
    public void setParent(GameNode newGameNode) {
        parent = newGameNode;
    }
    
    //getter for index
    @Override
    public int getIndex() {
        return index;
    }
    
    //getter for field
    @Override
    public Field getField() {
        return field;
    }
    
    @Override
    public int getDepth()
    {
        int tmp = depth;
        return tmp;
    }
    
    @Override
    public void setDepth(int newDepth)
    {
        depth = newDepth;
    }
    
}
