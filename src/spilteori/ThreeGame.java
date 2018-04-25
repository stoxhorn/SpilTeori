/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spilteori;

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
    
    private final int playerAmount = 2;
    
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
    
    //checks if the current player has won
    //should be called after every move is made
    //afterwards playerturn should be updated
    @Override
    public boolean checkWin() {
        Field[] t = currentBoard.getBoard();
        return  t[0].getValue() == playerTurn && t[1].getValue() == playerTurn && t[2].getValue() == playerTurn ||
                t[3].getValue() == playerTurn && t[4].getValue() == playerTurn && t[5].getValue() == playerTurn ||
                t[6].getValue() == playerTurn && t[7].getValue() == playerTurn && t[8].getValue() == playerTurn ||
                
                t[0].getValue() == playerTurn && t[3].getValue() == playerTurn && t[6].getValue() == playerTurn ||
                t[1].getValue() == playerTurn && t[4].getValue() == playerTurn && t[7].getValue() == playerTurn ||
                t[2].getValue() == playerTurn && t[5].getValue() == playerTurn && t[8].getValue() == playerTurn ||
                
                t[0].getValue() == playerTurn && t[4].getValue() == playerTurn && t[8].getValue() == playerTurn ||
                t[2].getValue() == playerTurn && t[4].getValue() == playerTurn && t[6].getValue() == playerTurn;
    }
    
    //makes the most optimal play in the given situation
    @Override
    public void makeMoveAI() {
        Board t = currentBoard;
        t.newMove(playerTurn, getBestMove(playerTurn));
    }
    
}
