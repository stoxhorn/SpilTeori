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

    Field[] board;
    
    @Override
    public Field[] getBoard() {
        Field[] tmp = board;
        return tmp;
    }
    
    // loop der indsætter et tomt felt i hvert index
    // Fow now it ádds 1 to row and coloumn value,
    // in other words, it's not a 0 index array for those values
    @Override
    public void createBoard(int fieldAmount, int width, int height) {
        board = new Field[9];
        
        // Every time i passes a 3x row is incremented once, and coloumn is reset
        for(int i = 0; i < 9; i++)
        {
            board[i] = new ThreeField(0, i/3 +1, i%3+1, i);
        }
        
    }    
    
    @Override
    public void newMove(int player, Field newField) {
        // get position
        int pos = newField.getPos();
        
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
    public void createBoard(Field nullField, int fieldAmount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createBoard(Field nullField, int width, int height) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    @Override
    public void newMove(int player, int newFieldValue, int newFieldPos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void newMove(int player, int newFieldValue, int coloumn, int row) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
