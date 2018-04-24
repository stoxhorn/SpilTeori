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
    
    public int calcPos()
    {
        System.out.println("Du har valgt position: " + pos);
        return pos;
    }
    
    public int getRow() {
        System.out.println("Du har valgt row: " + row);
        return row;
    }

    
    public int getColoumn() {
        System.out.println("Du har valgt coloumn: " + coloumn);
        return row;
    }
    public int getValue() {
        return value;
    }
}
