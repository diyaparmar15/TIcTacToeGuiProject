//package tictactoe;

package boardgame.tictactoe;

import boardgame.numtictactoe.NumTTTGrid;
import boardgame.ui.GameGrid;

/*
 * This class is reposnible for all the logical rules of tic tac toe 
 * that will be implemented in the GUI
 */
public class TicTacToe extends boardgame.ui.BoardGame{ 

 private int currentPlayer = 1;
 private String gameStateMessage;
 private boolean done = false;

 /*
  * Constructor isntantiates the width anf height of the baord
  @param wide
  @param height
  */
public TicTacToe(int wide, int height){
        super(wide, height);
    }


/* methods added that aren't in BoardGame*/

/*
 * Acessor method for get current player
 * @returns current player
 */
public int getCurrentPlayer(){
    return currentPlayer;
}

/*
 * Mutator method for setting the game as over
 * @param boolean state true if the game is over false if it is not
 */
public void setGameOver(boolean state){
    done = state;
}

/* overridden methods from BoardGame*/
/*
 * This method takes the turn of the next player bt going through the board to see
 */
@Override
public  boolean takeTurn(int across, int down, String input){
    if(getGrid().getValue(across, down) == " "){
        setValue(across, down, input);
        return true;
    }
return false;
}

/* really don't need this method for the design we're using 
so it can be left as a stub -> note from skeleton*/
/*
 * @param int cross
 * @param int down
 * @param input
 * @return boolean false
 */
@Override
public  boolean takeTurn(int across, int down, int input){
    return false;
}

/*
 * Mutator method for the grid
 * @param boardgame.ui.Grid grid
 */
@Override
public void setGrid(boardgame.ui.Grid grid){ //used full package name instead of import
    super.setGrid(grid);
    //gameOver(false); //resets done boolean
}


/*
 * This method does a 'win check' every time it is called.
 * In this design it is used by the user interfaces to determine what
 * to do next 
 * @return a boolean true if there is a win or a tie, false if it is neither 
*/
@Override
public boolean isDone(){
    if (getWinner() == 1){
        setGameStateMessage("Player X wins!");
        return true;
    } else if (getWinner() == 2) {
        setGameStateMessage("Player O wins!");
        return true;
    }else if (getWinner() == 0) {
        setGameStateMessage(gameOverMessage());
        return true;
    }
    switchPlayer();
    setGameStateMessage(nextPlayerMessage());
    return false;
 }


/*
 * Acessor method for winner
 * @return an integer depwnding on the winning condition
 */
@Override
public int getWinner(){
    if (winnerCheck("X")) {
        return 1;
    } else if (winnerCheck("O")) {
        return 2;
    } else if (fullCheck()) {
        return 0;
    }
    return -1;
  }

/**
 * Check board is full condition
 * @return bool, false if not full, true if it is full
 */
public boolean fullCheck() {
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            if (getCell(j + 1, i + 1) == " ") {
                return false;
            }
        }
    }
    return true;
}

/**
 * This method checks the win conditions (horizontal, vertical, diagonal)
 * @param symb
 * @return bool
 */
public boolean winnerCheck(String symb) {
    if (getCell(1, 1) == symb && getCell(2, 1) == symb && getCell(3, 1) == symb
        ||
        getCell(1, 2) == symb && getCell(2, 2) == symb && getCell(3, 2) == symb
        ||
        getCell(1, 3) == symb && getCell(2, 3) == symb && getCell(3, 3) == symb
        ||
        getCell(1, 1) == symb && getCell(1, 2) == symb && getCell(1, 3) == symb
        ||
        getCell(2, 1) == symb && getCell(2, 2) == symb && getCell(2, 3) == symb
        ||
        getCell(3, 1) == symb && getCell(3, 2) == symb && getCell(3, 3) == symb
        ||
        getCell(1, 1) == symb && getCell(2, 2) == symb && getCell(3, 3) == symb
        ||
        getCell(3, 1) == symb && getCell(2, 2) == symb && getCell(1, 3) == symb) {
            return true;
    }
    return false;
}

/*
 * Acessor method for get game state message
 */
@Override
public String getGameStateMessage(){
    return gameStateMessage;
}
/* private helper methods */


/*
 * Switching player turns after each time
 */
private void switchPlayer(){
    if(getCurrentPlayer() == 1){
        currentPlayer = 2;
    }else{
        currentPlayer = 1;
    }
}


private GameGrid grid(){
  
    return (GameGrid) getGrid();
}

/*
 * Mutator method for set game state message
 * @param msg
 */
private void setGameStateMessage(String msg){
    gameStateMessage = msg;
}

/*
 * Method to get the message for the next player
 * @return string highlighting whos turn it is and prompting that 
 * user to put their toke nwhere they want
 */
private String nextPlayerMessage(){
    String player = "Player 1";
    if(currentPlayer == 2){
        player = "Player 2";
    }
    return(player + " Please indicate where you would like to put your token.");
}
private String gameOverMessage(){
    /*should compose a nice string about who won and/or tie game*/
    return("TIE! GAME OVER!");
}


/*
 * This method is to create a new XO grid for tic tac toe
 * @param kind
 * @param wide
 * @param tall
 * @return new XO Grib
 * @return new NumTTT grid
 */
public static GameGrid newGrid(int kind, int wide, int tall){
    if(kind == 1){
        return new XOGrid(wide,tall);
    }else{
        return new NumTTTGrid(wide,tall);
    }
}

}