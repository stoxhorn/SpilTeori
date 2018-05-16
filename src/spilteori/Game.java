package spilteori;

import java.util.ArrayList;

/** This is a class that creates an ew object describing a new game and it's gametree for it's respective players
 * 
 * It contains following variables to describe the game:
 * AmountOfPlayers
 * CurrentBoard
 * PlayerTurn
 * Pretty slf explanatory
 * 
 * It contains an array variable calles GameTrees
 * GamesTree[] GameTrees;
 * 
 * each indice represents the GamesTree for each Player
 * 
 * it's assumed that Player 1 always starts first, and player 2 is next, and so on.
 *
 * 
 */
public interface Game {
    
    /**
     * Gets the amount of players required for this game
     * @return int represnts amount of players
     */
    public int getPlayerAmount();
    
    /**
     * Returns an ArrayList containing the Fields of the Board
     * @return 
     */
    public Board getBoard();
    
    /** 
     * Returns the amount of field left, that has not been moved to
     * @return 
     */
    public int getEmpty();
    
    /**
     * Returns an array consisting only of empty fields
     * @return 
     */
    public ArrayList<Field> getEmptyFields();

    
    /**
     * Returns the player who's turn it currently is
     * 
     * will change turn after call
     * 
     * @return 
     */
    public int getTurn();
    

    /**
     * Returns the Node representing the current game
     * 
     * @param Index
     * @return 
     */
    public GameNode getNode(int Index);

    /**
     * Returns true if a player has won
     * 
     * @return 
     */
    public boolean checkWin();
    
    /**
     * Makes a move for the ai
     * 
     */
    public void makeMoveAI();
    
    /**
     * Adds a new move to the game, identical to the new Field, but with a new value
     * 
     * @param newMove 
     * @return  
     */
    public boolean newMove(Field newMove);
    
    /**
     * A method that starts the game and asks for another round or end
     */
    public void runGame();
    
    /**
     * Starts the game with the set amount of players, filling AI for the rest, from 0 to 2
     * @param players 
     */
    public void startGame(int players);
    
    /**
     * The game loop with two players
     */
    public void gameLoopTwoPlayers();
    
    /**
     * The game loop with one player and one AI, asks first for who starts
     */
    public void gameLoopOnePlayer();
    
    /**
     * The game loop with no players
     */
    public void gameLoopTwoAI();
    
    /**
     * Prints the current board out to console
     */
    public void printBoard();
    
    /**
     * A method that asks if a new game is to be started or not
     */
    public void newGame();
    
    /**
     * Method that starts a move for the current player
     */
    public void playerMove();
    
    /**
     * A method that gets the move wished for by the player
     * @return 
     */
    public Field getPlayerMove();
    
    /**
     * Announces the game has ended and a winner if any
     */
    public void announceEnd();
    

}
