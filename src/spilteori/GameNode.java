/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spilteori;

/** GameNode contains Variables describing the information needed at each Node of the GameTree
 * 
 * Information at each node:
 * Field, Which represents the possible move of this GameNode.
 * In other words a Field with values representing a normal Field, but is not connected to naything in any way
 * 
 * int, representing the index of this node in the ArrayList it is to be inserted into
 * 
 * int[], Which holds the percentage chance of winning for each player, in their respective index.
 * Chance represented as 10000 = 100
 * 
 * Field[], Containing all possible GameNodes, reachable from here
 * 
 */
public interface GameNode {
    
    /**Returns the possible move of the current possibility
     * 
     * @return Field Representing this move.
     */
    public Field getPosMove();
    
    /**Sets the possible move value of this GameNode
     * 
     * @param newField Field that is to be inserted
     */
    public void setPosMove(Field newField);
    
    
    
    
}
