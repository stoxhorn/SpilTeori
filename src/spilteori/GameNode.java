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
 * int[], Each fist index contains the chances of winning for the corresponding player,
 * Chance represented as 10000 = 100
 * 
 * Field[], Containing all possible GameNodes, reachable from here, children
 * 
 * GameNode[], Contains the optimal GameNode for the player corresponding to index
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
    
    /**Returns int[] containing chances of the players in each respective index
     * 
     * @return int[] of size 0 to 10000, representing percentage chances with 100% = 10000
     */
    public int[] getChances();
    
    /** Adds the chances of this Node, as an int[]
     * 
     * @param newChances int[][] containing numbers between 0-10000
     */
    public void setChances(int[] newChances);
    
    /**Calculates the chances of winning at for this GameNode
     * 
     */
    public void calculateChances();
    
    /**Calculates the optimal move for each player
     * 
     * Places the index of the optimal child in the index corresponding to player number.
     */
    public void calculateOptimal();
    
    /**Returns the array of optimal moves
     * 
     * @return GameNode[] each index contains the optimal Node for player corresponding to index
     */
    public GameNode[] getOptimal();
    
    /**Returns an array containing all the children of this GameNode
     * 
     * 
     * @return GameNode[] an array of nodes with this node as parent
     */
    public GameNode[] getChildren();
    
    /**Sets the array of children of this node, to be the given array
     * 
     * @param ChildrenArray the array containing the new children
     */
    public void setChildren(GameNode[] ChildrenArray);
    

    /**Returns the parent of this GameNode
     * 
     * @return GameNode representing the Parent of this Node
     */
    public GameNode getParent();
    
    /**Sets the parent of this GameNode to be the given GameNode
     * 
     * @param NewGameNode GameNode representing the new Parent
     */
    public void setParent(GameNode NewGameNode);
    
}