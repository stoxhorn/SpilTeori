package spilteori;

import java.util.ArrayList;

/** A class that creates an object representing a Field, in a game.
 * Contains variables describing it's position, it's value used for the game, and a score representing it's possible worth for a player.
 * 
 * Rules:
 * 
 * A field contains these variables to describe position:
 * int Pos
 * int Row
 * int Coloumn
 * 
 * It's value variable, represents a value that represents some meaning, depending on game. Like 1 for X for three in a row, and 0 for O
 * 
 * Potentially not useful
 * It's Score represents the worth of this field
 * 
 */
public interface Field {
    
    public int getPos();
    
    /**The int representing position of the Field
     * 
     * @return int representing position
     */
    
    public int getRow();
    /**The int Representing the Row of the Field
     * 
     * @return int Representing Row 
     */
    
    public int getColoumn();
    /**The int Representing the coloumn of the Field
     * 
     * @return int Representing coloumn 
     */
   public int getValue();
   /** a value
    * yes it is 
    */
}
