package spilteori;

public class ThreeNode implements GameNode{
    
    private int depth;

    private GameNode parent;
    
    private Field field;
    
    private int index;
    
    private int winValue;
    
    private GameNode[] children;
    
    //ThreeNode Object
    public ThreeNode(Field newField, int newIndex, GameNode newParent)
    {
        this.field = newField;
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
        GameNode best = null;
        if (depth % 2 == 0) {
            for (GameNode child : children) {
                if (child.getWinValue() == 1) {
                    best = child;
                }
                else if (best == null) {
                    best = child;
                }
            }
        }
        
        else {
            for (GameNode child : children) {
                if (child.getWinValue() == 2) {
                    best = child;
                }
                else if (best == null) {
                    best = child;
                }
            }
        }
        return best;
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
