/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spilteori;

import java.util.ArrayList;

/**
 *
 * @author Stoxhorn
 */
public class Tree implements GameTree {

    ArrayList<GameNode> Tree = new ArrayList<>();
    
    @Override
    public ArrayList<GameNode> getTree() {
        ArrayList<GameNode> tmp = Tree;
        return tmp;
    }

    @Override
    public GameNode getParent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Tree(Game newGame)
    {
        newGame.createBoard();
        createTree(newGame.getBoard());
    }

    @Override
    public void createTree(ArrayList<Field> GameBoard)
    {
        GameBoard.forEach(x -> Tree.add(new ThreeNode(x)));
    }
    
    @Override
    public Field getBestMove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getCursor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    @Override
    public void setCursor(Field newMove) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
