/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spilteori;

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
    
    
    /**The int representing position of the Field
     * 
     * @return int representing position
     */
    public int getPos();
    
    /**The int Representing the Row of the Field
     * 
     * @return int Representing Row 
     */
    public int getRow();
    
    /**The int Representing the coloumn of the Field
     * 
     * @return int Representing coloumn 
     */
    public int getColoumn();
    
    /**The int representing the value of the field
     * 
     * @return int[] representing value, indice = player
     */
    public int[] getValue();
    
    /**A method that sets a new Value for a player
     * 
     * @param player int that represents the player
     * @param newVal int that represents the value to be inserted for the new player
     */
    public void setValue(int player, int newVal);
    
    
    
    // Potentially not needed, as it is stored in the GameNode Interface more efficiently ------------ ------------ ------------ ------------ ------------ ------------ ------------ 
    // ------------ ------------ ------------ ------------ ------------ ------------ ------------ ------------ ------------ ------------ ------------ ------------ ------------ 
    /**The int[] representing the score or worth of the field
     * 
     * Index represents which player has which score
     * 
     * If the field gets a value above -1, set the score of this Field to -1, for the same player
     * 
     * @return int[] representing score
     */
    public int[] getScore();
    
    /**Sets the score or worth of the field
     * If the field gets a Score above -1, set teh value of this field to be -1
     * 
     */
    public void setScore();
    
    
}
