/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    /**Gets the amount of players required for this game
     * 
     * @return int represnts amount of players
     */
    public int getPlayerAmount();
   
    /**Creates a new currentBoard
     * 
     */
    public void createBoard();
    
    /**Returns an ArrayList containing the Fields of the Board
     * 
     * @return 
     */
    public ArrayList<Field> getBoard();
    
    /** Returns the amount of field left, that has not been moved to
     * 
     * @return 
     */
    public int getEmpty();
    
}
