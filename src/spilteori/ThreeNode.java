package spilteori;

import java.util.ArrayList;
import java.util.Arrays;

public class ThreeNode implements GameNode{
    
    private int depth;

    private GameNode parent;
    
    private Field field;
    
    private int index;
    
    private int winValue = -1;
    
    private GameNode[] children;
    
    //ThreeNode Object
    public ThreeNode(Field newField, int newIndex, GameNode newParent)
    {
        this.field = new ThreeField(newField);
        this.index = newIndex;
        this.depth = 0;
        this.parent = newParent; 
        children = null;        //Null is default and is changed when calling setChildren as we most likely wont know the children when creating the Node
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
        depth++;            //Updates the depth when a new move is made
    }
    
    //setter for winValue
    @Override
    public void setWinValue(int newWinValue) {
        winValue = newWinValue;
    }
    
    //getter for winValue
    @Override
    public int getWinValue() {
        return winValue;
    }
    
    @Override
    public GameNode getOptimal() {
        int player = (depth%2)+1;
        GameNode bestVal = getChildren()[0];
        for(GameNode child : getChildren())
        {
            if((child.getWinValue() == player) || (bestVal.getWinValue() == player))
            {
                return child; 
            }
            else if(child.getWinValue() == 0)
            {
                bestVal = child;
            }
            else if(bestVal.getWinValue() == 0)
            {
            }
            else
            {
                bestVal = child;   
            }
        }
        return bestVal;
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
