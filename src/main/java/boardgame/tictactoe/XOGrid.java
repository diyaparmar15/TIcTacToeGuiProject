package boardgame.tictactoe;

import boardgame.ui.GameGrid;

public class XOGrid extends GameGrid{

    private static  String[] symbols ={"X","O"};
    
    public XOGrid(int across, int down){
        super(across,down);
    }
        
    public boolean horizontalWin(){
    
    return false;
    }
    
    public boolean verticalWin(){
    
    return false;
        
    }
    
    public boolean diagonalWin(){
    
    return false;
        
    }
    
    @Override
    public void validateInput(String input) throws Exception{ 
    
    }
    
    @Override
    public void validateLocation(int across, int down)throws Exception{
    
    /* method should check to see if positions are out of bounds
    as well as if the position is already full*/
        
    }
    
    /* private helper methods*/
    
    private boolean rowCheck(int row){
         boolean match = false;
    
        return match;
    }
    
    private boolean columnCheck(int col){
        return false;
    
    }
    
    private boolean diagonalCheck(){
        return false;
    
    }
    
    
    
    
    }