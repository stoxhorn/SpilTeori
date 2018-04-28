package spilteori;

public class ThreeNode implements GameNode{
    
    private int depth;

    private GameNode parent;
    
    private Field field;
    
    private int index;
    
    private int[] chances;
    
    private GameNode[] children;
    
    private GameNode[] bestNode;
    
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
    
    //setter for chances
    @Override
    public void setChances(int[] newChances) {
        chances = newChances;
    }
    
    //getter for chances
    @Override
    public int[] getChances() {
        return chances;
    }

    @Override
    public void calculateChances() {
        //use minimax with winCheck from GameTree
        //TODO
    }
    
    //Adds values to bestNode
    @Override
    public void calculateOptimal() {
        //Initialize values
        int player1Chance = 0;
        int player2Chance = 0;
        
        int player1Index = 0;
        int player2Index = 0;
        
        //Scans through all values from chances and stores the index and value of the best child for each player
        for (int i = 0; i < chances.length; i++) {
            int c = chances[i];
            if (c > player1Chance) {
                player1Chance = c;
                player1Index = i;
            }
            if (c < player2Chance) {
                player2Chance = c;
                player2Index = i;
            }
        }
        //The children with the best value for each player is put into bestNode
        bestNode[0] = children[player1Index];
        bestNode[1] = children[player2Index];
    }
    
    //getter for bestNode
    @Override
    public GameNode[] getOptimal() {
        return bestNode;
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
    
    @override
    public int getDepth()
    {
        int tmp = depth;
        return tmp;
    }
    
    @Override
    public void setDepth(int newDepth)
    {
        Depth = newDepth;
    }
    
}
