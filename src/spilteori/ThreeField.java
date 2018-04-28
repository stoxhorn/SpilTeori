package spilteori;

/**
 *
 * @author jonas
 */
public class ThreeField implements Field{

    int value;
    int row;
    int coloumn;
    int pos;
    
    public ThreeField(int newValue, int newRow, int newColoumn, int newPos)
    {
        value = newValue;
        coloumn = newColoumn;
        row = newRow;
        pos = newPos;
    }
    
    @Override
    public int getPos()
    {
        return pos;
    }
    
    @Override
    public int getRow() {
        return row;
    }
    
    @Override
    public int getColoumn() {        
        return row;
    }
    
    @Override
    public int getValue() {
        return value;
    }
    
    @Override
    public void setValue(int newVal)
    {
        value = newVal;
    }
    
}
