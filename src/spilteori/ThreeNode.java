package spilteori;

import java.util.ArrayList;
import java.util.Arrays;

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
    public int getPlayer() {
        int tmp = (depth % 2) + 1;
        return tmp;
    }
    
    public GameNode getOptimalP1(int player) {
        ArrayList<GameNode> childrenArray = new ArrayList<>();
        
        for (GameNode child : children) {
            //Checks if the middle is a child and takes it as a move for player 1 as it is the optimal start. The AI wont pick it itself as it sees a draw no matter what from the starting position given that both players play optimally.
            if( child.getField().getPos() == 4) {
                return child;
            }
            //C
            if (child.getWinValue()[0] == player) {
                childrenArray.add(child);
            }
            else if (child.getWinValue()[0] == 0 && childrenArray.get(0).getWinValue()[0] == player){
                
            }
        }
    }
    
    private GameNode getOptimalP2() {
        
    }
    
    //Returns the child with the lowest depth. Should only be called on the winning value for a player.
    private GameNode getBestChild(ArrayList<GameNode> childrenArray) {
        GameNode bestChild = null;
        for (GameNode child : childrenArray) {
            //For the first for loop run
            if (bestChild == null) {
                bestChild = child;
            }
            else {
                if (bestChild.getWinValue()[1] > child.getWinValue()[1]) {
                    bestChild = child;
                }
            }
        }
        return bestChild;
    }
    
    @Override
    public GameNode getOptimal() {
        ArrayList<GameNode> List1 = new ArrayList<>();
        ArrayList<GameNode> List2 = new ArrayList<>();
        ArrayList<GameNode> List3 = new ArrayList<>();
        
        if ((depth % 2) + 1 == 1) {
            for (GameNode child : children) {
                if (child.getField().getPos() == 4) {
                    List1.add(child);
                }
                switch (child.getWinValue()[0]) {
                    case 1:
                        List1.add(child);
                        break;
                    case 0:
                        List2.add(child);
                        break;
                    case 2:
                        List3.add(child);
                        break;
                    default:
                        break;
                }
            }
            
        }
        else {
            for (GameNode child : children) {
                //System.out.print(child.getWinValue()[0] + " - ");
                switch (child.getWinValue()[0]) {
                    case 2:
                        System.out.println("works");
                        List1.add(child);
                        break;
                    case 0:
                        List2.add(child);
                        break;
                    case 1:
                        List3.add(child);
                        break;
                    default:
                        break;
                }
            }

        }
        //System.out.println("");
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
