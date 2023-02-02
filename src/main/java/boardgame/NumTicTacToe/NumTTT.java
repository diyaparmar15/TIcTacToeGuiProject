

package boardgame.numtictactoe;

//import boardgame.numtictactoe.NumTTTGrid;
import boardgame.ui.GameGrid;

/*
 * This class is reposnible for all the logical rules of numerical tic tac toe 
 * that will be implemented in the GUI
 */
public class NumTTT extends boardgame.ui.BoardGame{ //implements boardgame.Saveable
 
 private int currentPlayer = 1;
 private int play = 1;
 private String gameStateMessage;
 private boolean done = false;
 private String[]eList = {"0","2", "4", "6", "8"};
 private String[]oList = {"1","3", "5", "7", "9"};


/*
 * Constructor isntantiates the width anf height of the baord
  @param wide
  @param height
  */ 
public NumTTT(int wide, int tall){
        super(wide, tall);
    }


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
 * This method takes the turn of the next plauer bt going through the board to see
 */
/* overridden methods from BoardGame*/
@Override
public  boolean takeTurn(int across, int down, String input){
    if(getGrid().getValue(across, down) == " "){
        setValue(across, down, input);
        return true;
    }
return false;
}

/* really don't need this method for the design we're using 
so it can be left as a stub*/
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

/* I needed this method to be public for 
this design */

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
        setGameStateMessage("Odd Player Wins!");
        return true;
    } else if (getWinner() == 2) {
        setGameStateMessage("Even Player Wins!");
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
    if (winnerCheck() &&  checkMove() == "Odd") {
        return 1;
    } else if (winnerCheck() && checkMove() == "Even") {
        return 2;
    } else if (play > 9) {
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
public boolean winnerCheck() {
    if ((Integer.parseInt(getCell(1, 1)) + Integer.parseInt(getCell(2, 1)) + Integer.parseInt(getCell(3, 1)) == 15)
        ||
        (Integer.parseInt(getCell(1, 2)) + Integer.parseInt(getCell(2, 2)) + Integer.parseInt(getCell(3, 2)) == 15)
        ||
        (Integer.parseInt(getCell(1, 3)) +  Integer.parseInt(getCell(2, 3)) +  Integer.parseInt(getCell(3, 3)) == 15)
        ||
        (Integer.parseInt(getCell(1, 1)) +  Integer.parseInt(getCell(1, 2)) +  Integer.parseInt(getCell(1, 3)) == 15)
        ||
        (Integer.parseInt(getCell(2, 1)) +  Integer.parseInt(getCell(2, 2)) +  Integer.parseInt(getCell(2, 3)) == 15)
        ||
        (Integer.parseInt(getCell(3, 1)) +  Integer.parseInt(getCell(3, 2)) +  Integer.parseInt(getCell(3, 3)) == 15)
        ||
        (Integer.parseInt(getCell(1, 1)) +  Integer.parseInt(getCell(2, 2)) +  Integer.parseInt(getCell(3, 3)) == 15)
        ||
        (Integer.parseInt(getCell(3, 1)) +  Integer.parseInt(getCell(2, 2)) +  Integer.parseInt(getCell(1, 3)) == 15)) {
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

/* The gameStateMessage can be used by both the GUI
and the TextUI to easily communicate the current state
to the user.  Private methods to compose the desired message promote
encapsulation */

/*
 * Mutator method for the game state message
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

/*
 * @return if its a tie, or a win the game is over
 */
private String gameOverMessage(){
    /*should compose a nice string about who won and/or tie game*/
    return("TIE! GAME OVER!");
}

/*
 * checks move and determines if it was odd or even performing th e modulus
 * @return string odd or even
 */
protected String checkMove(){
    if (play % 2 == 1){
        return "Odd";
    } else {
        return "Even";
    }
}

}