package boardgame.tictactoe;
import java.util.Scanner;

/*
 * This is the textUI class, it plays yhe tic tac toe game on the console
 * ensuring that all the rules and regulations are working correctly
 */
public class TextUI {

    //Create a tic tac toe game
    private TicTacToe game = new TicTacToe(3,3);    
    private Scanner input = new Scanner(System.in);
    private int acrossVal;
    private int downVal;
    private boolean win;

    /*
     * Constructor creates new game 
     * @param gameType
     */
    public TextUI(int gameType){
        game = new TicTacToe(3,3);
        /*could print out a welcome message here or set a gameType
        variable that let us decide which message to print */
        game.setGrid(TicTacToe.newGrid(1,3,3));
    }

    /*
     * This method starts the game prints the results to the console
     */
    public void play(){
        System.out.println(game.getGameStateMessage());
        while(!win){
            getPosition();
            if(acrossVal == 0){
                game.setGameOver(true);
                break;
            }
            try{
                game.takeTurn(acrossVal,downVal,getToken());
                System.out.println(game);
            }catch(RuntimeException e){
                System.out.println(game.getGameStateMessage());
                /* this only works if the game sets the gameStateMessage when
                there is an exception caught*/
            }
            win = game.isDone();
            System.out.println(game.getGameStateMessage());
        }
    }

    /*
     * This method gets the position and prompts the user
     * where they want to put their token
     */
    private void getPosition(){
        /*this method needs some validation and error checking*/
        System.out.println("across? (0 to quit)");
        acrossVal = input.nextInt();

        input.nextLine(); //to get rid of hard return
        System.out.println("down?");
        downVal = input.nextInt(); //to get rid or hard return;
    }
    /*
     *  Acessor method for the token
     * @return string either O or X
     */
    private String getToken(){
        if (game.getCurrentPlayer() == 1) {
            return "X";
        }
        return "O";
    }

    /*
     * Main method to run the GUI
     */
    public static void main(String[] args){
        TextUI tester = new TextUI(1); //textUI for XO game
        tester.play();
    }

}
