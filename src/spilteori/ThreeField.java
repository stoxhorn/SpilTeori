/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spilteori;

/**
 *
 * @author jonas
 */
public class ThreeField implements Field{

    int value;
    int row;
    int coloumn;
    
    public ThreeField(int newValue, int newRow, int newColoumn)
    {
        value = newValue;
        coloumn = newColoumn;
        row = newRow;
    }
    
    
    @Override
    public int getRow() {
        System.out.println("Du har valgt row: " + row);
        return row;
    }

    
    public int getColoumn() {
        System.out.println("Du har valgt coloumn: " + coloumn);
        return row;
    }
}