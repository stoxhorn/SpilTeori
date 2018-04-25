/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spilteori;

import java.util.ArrayList;

// The rule for winning is stated in Game class?

// Need a method to add a move on the game, instead of only on the Board


/**
 * It contains following variables to describe the game:
 * AmountOfPlayers -
 * CurrentBoard - 
 * PlayerTurn - 
 * 
 * It contains an array variable calles GameTrees
 * GamesTree Tree; -
 *
 * 
 * it's assumed that Player 1 always starts first, and player 2 is next, and so on.
 * 
 */
public class ThreeGame implements Game {
    
    private GameNode currentNode;
    
    private ThreeRowGameTree Tree;
    
    private Board currentBoard;
    
    private int playerAmount = 2;
    
    private int playerTurn;
    
    int[] chances;
    
    public ThreeGame()
    {
        playerTurn = 0;
        Tree.createTree(GameBoard, this);
        currentNode = Tree.getNode(0);
    }
    
    @Override
    public int getPlayerAmount() {
        int tmp = playerAmount;
        return tmp;
    }

    
    // Skal have fundet ud af
    @Override
    public void createBoard() {
        currentBoard.createBoard(9,3,3);
    }

    @Override
    public Board getBoard() {
        Board tmp = currentBoard;
        return tmp;
    }
    
    @Override
    public ArrayList<Field> getEmptyFields()
    {
        return currentBoard.getEmptyFields();
    }
    
    @Override
    public int getTurn()
    {
        // Store playerTurn
        int tmp = playerTurn;
        
        // increment playerTurn
        playerTurn ++;
        
        // Modulo playerTurn
        playerTurn = playerTurn%2;
        
        // return storage
        return tmp;
    }
    // kræver viden om hvilken node vi er kommet til
    // kald nodens egen metode, getOptimal()
    // Skal rykkes til Game klassen
    // Mangler metode til at få den nuværende node, getNode
    public Field getBestMove(int player) {
        GameNode t = currentNode;
        Field f = t.getOptimal()[player].getField();
        return f;
    }
    
    
    // returner antallet af tomme felter
    @Override
    public int getEmpty() {
        return currentBoard.getEmptyFields().size();
    }
    
}
