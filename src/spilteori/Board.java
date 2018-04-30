/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spilteori;

import java.util.ArrayList;

/** 
 * This class represents the board of a game, using an array of Fields
 *
 * Rules:
 * Has a variable Field[], which is used to represent the board
 * 
 * has two ints defining rows and coloumn
 * 
 * 
 */
public interface Board {

    /**
     * Returns the Field[] that contains the this Board's Fields
     * 
     * @return a Field[] representing the this Board
     */
    public Field[] getBoard();


    /**
     * Create a new Board
     * 
     * If either value is 0, figure out this value, from the other given values
     * 
     * If width and height is 0, create a Board with only one row, and one height
     * 
     * @param fieldAmount
     * @param width
     * @param height 
     * @return  
     */
    public Field[] createBoard(
            int fieldAmount,
            int width,
            int height
    );

    /**
     * Overloaded method, that takes only an amount of fields to create a board of one row
     * 
     * @param nullField
     * @param fieldAmount 
     * @return  
     */
    public Field[] createBoard(Field nullField, int fieldAmount);

    /**
     * Overloaded method that only takes width and height into acount
     * 
     * @param nullField
     * @param width
     * @param height 
     */
    public void createBoard(
            Field nullField,
            int width,
            int height
    );


    /**
     * Adds a move to the Board, uhsing a Field parameter
     * 
     * @param player
     * @param newField 
     */
    public void newMove(
            int player,
            Field newField
            );



    /**
     *  Returns the Field with the highest score
     * 
     * @param player int representing the player's score wished for
     * @return Field the Field that has the highest score of this board
     */
    public Field getBestMove(int player);

    /** Returns an array of empty Fields
     * 
     * @return an array of Fields containing no value
     */
    public ArrayList<Field> getEmptyFields();
    
    /**
     * Adds a new move to the board
     */
    public boolean newMove(Field newMove, int turn);
            
    /**
     * To string method returning the Board as a strnig, with each row on their own line
     * @return 
     */
            
    @Override
    public String toString();
        /**
     * returns true if the given representation of lines has three in a row
     * 
     * @param x
     * @param y
     * @param z
     * @return 
     */
    public boolean checkLine(int x, int y, int z, int turn);
    
    /**
     * returns true if all fields has a value higher than 0
     * @param x
     * @param y
     * @param z
     * @return 
     */
    public boolean checkLineExist(int x, int y, int z);
    
    /**
     * returns true if the game has a winner
     * @param x
     * @param y
     * @param z
     * @return 
     */
    public boolean check(int x, int y, int z,  int turn);
    
    /**
     * check for a win and returns true if a win exists
     * @param turn
     * @return 
     */
    public boolean checkWin(int turn);

    /**
     * Checks if a given move is legal
     * @param newField
     * @return 
     */
    public boolean legalMove(Field newField);
}
