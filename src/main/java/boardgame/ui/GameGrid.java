package boardgame.ui;

import java.util.Iterator;

public abstract class GameGrid extends boardgame.ui.Grid{

public GameGrid(int across, int down){
    super(across,down);
}   
public abstract boolean horizontalWin();

public abstract boolean verticalWin();

public abstract boolean diagonalWin();

public  abstract void validateInput(String input) throws Exception;

public  abstract void validateLocation(int across, int down)throws Exception;

/*
 * Method to check if the board is full
 * @returns a boolean, true if the board is full, otherwise false
 */
public boolean isFull(){

    Iterator<String> iter = iterator();
    int count = 0;
        while(iter.hasNext()){
            if(!iter.next().equals(" ")){
            count++;
            }
        }
    if(count == getWidth()*getHeight()){
        return true;
    }
    return false;
}




}