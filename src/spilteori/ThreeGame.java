/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spilteori;

// Need a method to add a move on the game, instead of only on the Board

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;



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
        playerTurn = 1;
        currentBoard = new ThreeBoard(9,3,3);
        Tree = new ThreeRowGameTree(this);
        
        currentNode = Tree.getNode(0);
    }
    
    @Override
    public int getPlayerAmount() {
        int tmp = playerAmount;
        return tmp;
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
        
        // Modulo playerTurn
        playerTurn = playerTurn%2;
        
        // increment playerTurn
        playerTurn ++;
        
        // return storage
        return tmp;
    }
    // kræver viden om hvilken node vi er kommet til
    // kald nodens egen metode, getOptimal()
    // Skal rykkes til Game klassen
    // Mangler metode til at få den nuværende node, getNode
    public Field getBestMove(int player) {
        GameNode t = currentNode;
        currentNode = t.getOptimal();
        //System.out.println("WinValue: " + currentNode.getWinValue()[0]);
        //System.out.println("position: " + currentNode.getField().getPos());
        System.out.println(Arrays.toString(currentNode.getWinValue()));
        Field f = currentNode.getField();
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
        
        
        return  currentBoard.checkWin(playerTurn);
    }
    
    
    
    //makes the most optimal play in the given situation
    @Override
    public void makeMoveAI() {
        // currentBoard.newMove(playerTurn, getBestMove(playerTurn));
        if (!newMove(getBestMove(playerTurn)))
        {
            getTurn();
            System.out.println(currentBoard.toString());
            System.out.println("Pick a legal move, please.\n");
            makeMoveAI();
        }
        
    }
    
    
    @Override
    public boolean newMove(Field newMove)
    {

        
        
        System.out.println("position: " + newMove.getPos());
        System.out.println("value: " + newMove.getValue());
        return currentBoard.newMove(newMove, getTurn());
            
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
            /*if ("n".equals(answer))
            {*/
                System.out.println("Have a nice day");
            /*}
            System.out.println("Please press n or y, moron");
            newGame();*/
        }
        
    }
    
    @Override
    public void startGame(int players) {
        // Hierachily calls the methods of incrementing player size
        this.printBoard();
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
        // A loop that breaks if the game is won or if the Board is filled        

        
        while(true)
        {
            // Applies the next turn, keeps track of player by itself
            getTurn();
            if(checkWin() || currentBoard.getEmptyFields().size() < 1)
            {
                break;
            }
            getTurn();
            playerMove();
        }
        // Announces the end of the game and potential winners
        announceEnd();
    
    }

    @Override
    public void gameLoopOnePlayer() {
        
        // int used to decide which method to call in loop
        int iterator = 0;
        
        int inp;
        
        System.out.println("Who starts? player or ai?\n1 for player 0 for AI.");
        
        // Getting the relevant user input
        Scanner input = new Scanner(System.in);
        
        // Try statement to catch exceptions
        try
        {
            inp = input.nextInt();
        }
        catch(Exception e)
        {
            System.err.println(e);
            gameLoopOnePlayer();
            return;
        }
        // checks if proper input was given
        if(inp > 1 || inp < 0)
        {
            System.out.println("please press either 1 or 0");
            gameLoopOnePlayer();
            return;
        }
        // increments the iterator or not depending on input
        else
        {
            if(inp == 1)
            {
                iterator ++;
            }
        }
        
        // Game loop that does a player mnove if the iterator is an odd number, and ai move if number is even.
        while(true)
        {
            getTurn();
            if(checkWin() || currentBoard.getEmptyFields().size() < 1)
            {
                break;
            }
            getTurn();
            if(iterator%2 == 1)
            {
                playerMove();
                iterator++;
                        
            }
            else{
                this.makeMoveAI();
                iterator++;
            }
            
        }
        announceEnd();
    }

    @Override
    public void gameLoopTwoAI() {
        
        // A loop that breaks if the game is won or if the Board is filled        
        while(true)
        {
            // Applies the next turn, keeps track of player by itself
            getTurn();
            if(checkWin() || currentBoard.getEmptyFields().size() < 1)
            {
                break;
            }
            getTurn();
            makeMoveAI();
            
            

        }
        
        
        // Announces the end of the game and potential winners
        announceEnd();
    }

    
    
    @Override
    public void printBoard() {
        System.out.println(currentBoard.toString());
    }

    @Override
    public void playerMove() {
        // Announce the current player
        System.out.println("It is now player " + playerTurn +"'s turn");
    
        // ask for for which field to make a move on
        Field newField = getPlayerMove();
        
        System.out.println(newField.getValue());
        
        GameNode[] tmp = currentNode.getChildren();
        if(currentNode.getDepth()<9)
        {
            for(GameNode x : tmp)
            {
                if(x.getField().getPos() == newField.getPos())
                {
                    currentNode = x;
                }

            }
        }
        // add the move to the board
        if (!newMove(newField))
        {
            System.out.println("Pick a legal move, please.\n");
            getTurn();
            playerMove();
        }
        
    }

    @Override
    public void announceEnd() {
        System.out.println("The game has ended");
        if (checkWin())
        {
            System.out.println(playerTurn + " has won the game");
        }
        else
        {
            System.out.println("No winner has been found");
        }
    }

    @Override
    public Field getPlayerMove() {
        System.out.println("Which row would you like to move to?");
        Scanner inp1 = new Scanner(System.in);
        int row = inp1.nextInt();
        
        System.out.println("Which Coloumn would you like to move to?");
        Scanner inp2 = new Scanner(System.in);
        int coloumn = inp2.nextInt();
        
        ThreeField tmp = new ThreeField(playerTurn, row, coloumn, (3*row + coloumn) );
        return tmp;
    }

    @Override
    public void createBoard() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean legalMove(Field checkField) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    

    
    
}
