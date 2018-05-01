/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spilteori;

import java.util.ArrayList;
/**
 *
 * Rules:
 * Has a variable Field[], which is used to represent the board
 * 
 * has two ints defining rows and coloumn
 * 
 * 
 */
public class ThreeBoard implements Board {

    private Field[] board;
    
    private final int rows = 3;
    
    private final int coloumns = 3;
            
    
    @Override
    public Field[] getBoard() {
        Field[] tmp = board;
        return tmp;
    }
    
    public ThreeBoard(Field[] newArray)
    {
        Field[] tmp = new Field[newArray.length];
        int i = 0;
        for(Field x : newArray)
        {
            Field z = new ThreeField(x);
            tmp[i] = z;
        }
        
        board = tmp;
    }
    
    public ThreeBoard(Board newBoard)
    {
        Field[] tmp = newBoard.getBoard();
        tmp = ThreeBoard(tmp);
        board = tmp;
    }
    
    
    public Field[] ThreeBoard(Field[] newArray)
    {
        Field[] tmp = new Field[newArray.length];
        int i = 0;
        for(Field x : newArray)
        {
            Field z = new ThreeField(x);
            tmp[i] = z;
            i++;
        }
        
        return tmp;
    }
    
    
    public ThreeBoard(int fieldAmount, int width, int height)
    {   
        board = createBoard(fieldAmount, width, height);
    }
    
    // loop der indsætter et tomt felt i hvert index
    // Fow now it ádds 1 to row and coloumn value,
    // in other words, it's not a 0 index array for those values
    @Override
    public Field[] createBoard(int fieldAmount, int width, int height)
    {
        Field[] newBoard = new Field[9];
        
        // Every time i passes a 3x row is incremented once, and coloumn is reset
        for(int i = 0; i < 9; i++)
        {
            newBoard[i] = new ThreeField(0, i/3, i%3, i);
        }
        return newBoard;
    }    
    
    @Override
    public void newMove(int player, Field newField) {
        
        // get position
        int pos = newField.getPos();
        // Adding the player number as value,
        // to represent the players' move
        newField.setValue(player);
        
        // set Field into the field
        board[pos] = newField;        
    }

    // kræver viden om hvilken node vi er kommet til
    // kald nodens egen metode, getOptimal()
    // Skal rykkes til Game klassen
    @Override
    public Field getBestMove(int player) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public ArrayList<Field> getEmptyFields() {
        ArrayList<Field> output = new ArrayList<>();
        
        for (Field value : board) {
            
            if (value.getValue() == 0) {    //getValue fra ThreeField skal give en int
                output.add(value);
            }
        }
        return output;
    }


    @Override
    public void createBoard(Field nullField, int width, int height) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //newMove for playing the game
    @Override
    public boolean newMove(Field newMove, int turn)
    {
        // check if legalmove
        if(!legalMove(newMove))
        {
            System.out.println("Wrong move");
            return false;
        }else
        {
            newMove(turn, newMove);
            System.out.println(toString());
            return true;
        }
    }
    
    @Override
    public boolean legalMove(Field newField) {
        int x = getBoard()[newField.getPos()].getValue();
        // returns true, only if the given field has no value
        return x == 0;
    }


    
    @Override
    public String toString()
    {
        String tmp = "";
        
        int i = 0;
        for(Field x : board)
        {
            tmp += x.getValue() + " ";
            i++;
            if(i%coloumns == 0)
            {
                tmp += "\n";
            }
        }
        
        return tmp;
    }
    
    @Override
    public boolean checkWin(int turn) {
        
        //System.out.println(turn);
        return  check(0,1,2, turn)||
                check(3,4,5, turn)||
                check(6,7,8, turn)||
                check(0,3,6, turn)||
                check(1,4,7, turn)||
                check(2,5,8, turn)||
                check(0,4,8, turn)||
                check(2,4,6, turn);
    }
    
    @Override
    public boolean check(int x, int y, int z, int turn)
    {
        boolean tmp = (checkLine(x , y, z, turn) && checkLineExist(x,y,z) && true);
        
        
        return tmp;
    }
    
    @Override
    public boolean checkLine(int x, int y, int z, int turn) {
        Field[] t = getBoard();
        boolean tmp = t[x].getValue() == turn  && t[y].getValue() == turn && t[z].getValue() == turn;
        
        return tmp;
    }
    
    
    @Override
    public boolean checkLineExist(int x, int y, int z)
    {
        Field[] t = getBoard();
        x = t[x].getValue();
        y = t[y].getValue();
        z = t[z].getValue();
        
        if (x > 0)
        {
            if (y > 0)
            {
                if(z > 0)
                {
                    return true;
                }
            }
        }
        return false;
    }
    

    @Override
    public Field[] createBoard(Field nullField, int fieldAmount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
