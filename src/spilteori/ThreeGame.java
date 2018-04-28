/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spilteori;

// Need a method to add a move on the game, instead of only on the Board

import java.util.ArrayList;
import java.util.Scanner;



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
        createBoard(); 
        Tree.createTree(this);
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
        currentBoard.newMove(playerTurn, getBestMove(playerTurn));
    }
    
    
    @Override
    public void newMove(Field newMove)
    {
        currentBoard.newMove(playerTurn, newMove);
    }
    
    
    @Override
    public GameNode getNode(int Index) {
        return Tree.getNode(Index); 
    }

    @Override
    public void runGame() {
        System.out.println("Please specify amount of players, from 0 - 2:");
        
        // Starts the game with the amount of player specified in the scanenr input
        Scanner in = new Scanner(System.in);
        int playerAmnt = in.nextInt();
        startGame(playerAmnt);
        
        // Asks if a new game is to be started
        newGame();
    }

    @Override
    public void newGame() {
        System.out.println("Would you like to try again? \n(y/n)");
        
        // Starts the game if answer given is Y
        Scanner getAnswer = new Scanner(System.in);
        String answer = getAnswer.next();
        if("y".equals(answer))
        {
            runGame();
        }
        else 
        {
            if ("n".equals(answer))
            {
                System.out.println("Have a nice day");
            }
            System.out.println("Please press n or y, moron");
            newGame();
        }
        
    }
    
    @Override
    public void startGame(int players) {
        // I'm supposed to call the proper gameloops
        if(players != 0)
            if(players != 1)
            {
                gameLoopTwoPlayers();
            }
            else
            {
                gameLoopOnePlayer();
            }
            
        else
        {
            gameLoopTwoAI();
        }
    }

    @Override
    public void gameLoopTwoPlayers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void gameLoopOnePlayer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void gameLoopTwoAI() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printBoard() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
