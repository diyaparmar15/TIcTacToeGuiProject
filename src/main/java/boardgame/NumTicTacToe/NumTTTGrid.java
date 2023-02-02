package boardgame.numtictactoe;

import boardgame.ui.GameGrid;

public class NumTTTGrid extends GameGrid{



    public NumTTTGrid(int across, int down){
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
    
    public void validateInput(String input)throws Exception{ 
    
    
    }
    
    public void validateLocation(int across, int down)throws Exception{
    
    
        
    }
}
    
    
    
    