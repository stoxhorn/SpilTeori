package spilteori;

/**
 *
 * 
 */
public class ThreeField implements Field{

    int value;
    int row;
    int coloumn;
    int pos;
    
    /**
     * Constructor for copying another field
     * 
     * @param copyField 
     */
    public ThreeField(Field copyField)
    {
        int tmp = copyField.getValue();
        value = tmp;
        int tmp2 = copyField.getColoumn();
        coloumn = tmp2;
        int tmp3 = copyField.getRow();
        row = tmp3;
        int tmp4 = copyField.getPos();
        pos = tmp4;
    }
    
    /**
     * Simple constructor that sets all the value 
     * 
     * @param newValue
     * @param newRow
     * @param newColoumn
     * @param newPos 
     */
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
        int tmp = pos;
        return tmp;
    }
    
    @Override
    public void setPos(int newPos)
    {
        int tmp = newPos;
        pos = tmp;
    }
    
    @Override
    public int getRow() {
        int tmp = row;
        return tmp;
    }
    
    @Override
    public void setRow(int newRow) {
        int tmp = newRow;
        pos = tmp;
    }
    
    @Override
    public int getColoumn() {        
        int tmp = coloumn;
        return tmp;
    }
    
     @Override
    public void setCol(int newCol) {        
        int tmp = newCol;
        pos = tmp;
    }
    
    
    @Override
    public int getValue() {
        int tmp = value;
        return tmp;
    }
    
    @Override
    public void setVal(int newVal)
    {
        int tmp = newVal;
        value = tmp;
    }
    
    @Override
    public String toString()
    {
        String tmp = String.valueOf(value);
        return tmp;
    }
    
}
