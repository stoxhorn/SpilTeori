package spilteori;

import java.util.ArrayList;
import java.util.Arrays;

public class ThreeNode implements GameNode{
    
    private int depth;

    private GameNode parent;
    
    private Field field;
    
    private int index;
    
    private int[] winValue = new int[] {-1, 12};
    
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
    public void setWinValue(int[] newWinValue) {
        winValue = newWinValue;
    }
    
    //getter for winValue
    @Override
    public int[] getWinValue() {
        return winValue;
    }
    
    @Override
    public GameNode getOptimal() {
        
        ArrayList<GameNode> List1 = new ArrayList<>();
        ArrayList<GameNode> List2 = new ArrayList<>();
        ArrayList<GameNode> List3 = new ArrayList<>();
        
        if ((depth % 2) + 1 == 1) {
            for (GameNode child : children) {
                System.out.print(child.getWinValue()[0] + " - ");
                if (child.getWinValue()[0] == 1) {
                    System.out.println("works");
                    List1.add(child);
                }else if (child.getWinValue()[0] == 0) {
                    List2.add(child);
                }else 
                if(child.getWinValue()[0] == 2){
                    List3.add(child);
                }
            }
            
        }
        else {
            for (GameNode child : children) {
                System.out.print(child.getWinValue()[0] + " - ");
                if (child.getWinValue()[0] == 2) {
                    System.out.println("works");
                    List1.add(child);
                }else if (child.getWinValue()[0] == 0) {
                    List2.add(child);
                }else if(child.getWinValue()[0] == 1){
                    List3.add(child);
                }
            }

        }
        System.out.println("");
        int[] returnValue = null;
        GameNode returnNode = null;
        
        if(!List1.isEmpty()){
            for(GameNode x : List1){
                if (returnValue == null || x.getWinValue()[1] < returnValue[1]){
                    returnNode = x;
                    returnValue = x.getWinValue();
                    System.out.println(Arrays.toString(returnValue));
                }
            }
            return returnNode;
        }else if(!List2.isEmpty()){
            for(GameNode x : List2){
                    if (returnValue == null || x.getWinValue()[1] < returnValue[1]){
                    returnNode = x;
                    returnValue = x.getWinValue();
                    System.out.println(Arrays.toString(returnValue));
                }
            }
            return returnNode;
        }else if(!List3.isEmpty()){
            for(GameNode x : List3){
                    if (returnValue == null || x.getWinValue()[1] < returnValue[1]){
                    returnNode = x;
                    returnValue = x.getWinValue();
                }
            }
            return returnNode;            
        }
        
        return returnNode;
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
