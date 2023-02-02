package boardgame.numtictactoe;

import boardgame.ui.GameUI;
import boardgame.ui.PositionAwareButton;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import java.awt.Color;

public class NumTTTView extends JPanel{

     //Instantiate objects from all other classes being used
    private GameUI tttui;
    private Boolean valid = true;
    private NumTTT ntttgame;
    private JPanel tttboard;
    private JPanel content;
    private JLabel gameState;
    private PositionAwareButton[][] buttonArray;

    /*
     * Constructor to initialize the GUI Frame 
     * @param width - of the board
     * @param height - of the baord
     * @param tttFrame - the GameUI window 
     */
    public NumTTTView(int width, int height, GameUI tttFrame){
        super();
        tttui = tttFrame;
        this.ntttgame = new NumTTT(3, 3);
        add(createContent());
    }

    /*
     * Create new JPanel that consists of the tic tac toe baord, 
     * and message for the player.
     * @return content which is a JPanel window with the updated changes
     */
    private JPanel createContent(){
        content = new JPanel();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        content.setBackground(Color.PINK);
        content.add(createPanelButtons(3, 3), BorderLayout.CENTER);
        gameState = new JLabel("Player " + ntttgame.getCurrentPlayer() 
                    + " please indicate where you would like to put your token.");
        content.add(gameState, BorderLayout.WEST);
        gameState.setVisible(true);
        return content;
    }

     /*
     * Creates the 9 buttons for the grid (3x3) on the tic tac toe board
     * @param width of the board
     * @param height height of the board
     * @return the updated board
     */
    private JPanel createPanelButtons(int width, int height) {

        JPanel ntttboard = new JPanel();
        //tttboard.setVisible(false);
        ntttboard.setBackground(Color.ORANGE);
        buttonArray = new PositionAwareButton[width][height];
        ntttboard.setLayout(new GridLayout(width, height));
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                buttonArray[i][j] = new PositionAwareButton();
                buttonArray[i][j].setAcross(j + 1);
                buttonArray[i][j].setDown(i + 1);
                buttonArray[i][j].addActionListener(e ->{
                                                    enterNumber(e);
                                                    validateCurrentState();
                                                    });
                ntttboard.add(buttonArray[i][j]);
            }
        }
        return ntttboard;
    }

    /*
     * User Play function checks if button pressed has already been taken before 
     * @param ActionEvent e to hold the value of if the button has been clicked or not
     * @returns a boolean true if it has not been clicked otherwise false and display error
     * This method was inspird by the kakuro program given as an example
     */
    private void userPlay(ActionEvent e) {
        String user = getToken();

        PositionAwareButton click = ((PositionAwareButton)(e.getSource()));
        if (ntttgame.takeTurn(click.getAcross(), click.getDown(), user)) {
            click.setText(ntttgame.getCell(click.getAcross(), click.getDown()));
            valid = true;
        } else {
            JOptionPane.showMessageDialog(null, "Error! Invalid Choice!", "Invalid Choice",
                                            JOptionPane.ERROR_MESSAGE);
            valid = false;
        }
    }

     /*
     * Gets the current status of the game
     */
    private void validateCurrentState(){
        if (valid) {
            if (ntttgame.isDone()) {
                playAgain(ntttgame.getGameStateMessage());
            }
        }
    }


     /*
     * @param ActionEvent e to hold the value of if the button has been clicked or not
     * This method is there to prompt the user what inot values they wouldlike to keep on the baord 
     * This method was inspird by the kakuro program given as an example
     */
    private void enterNumber(ActionEvent e){
        //get input from user
        String num = JOptionPane.showInputDialog("Please input a number to place in this spot"); 
        
        //send input to game and update view
        PositionAwareButton clicked = ((PositionAwareButton)(e.getSource()));
        if(ntttgame.takeTurn(clicked.getAcross(), clicked.getDown(),num)){
            clicked.setText(ntttgame.getCell(clicked.getAcross(),clicked.getDown()));
        }
    }

     /*
     * Play again method, prompts the user if they would like to play again 
     * @param status, where the current game state message is passed through
     */
    private void playAgain(String status) {
        JOptionPane end = new JOptionPane();
        int chosen;
        chosen = end.showConfirmDialog(null, status,
                                "Player Another Game?", JOptionPane.YES_NO_OPTION);
        if (chosen == JOptionPane.NO_OPTION) {
            tttui.initialize();
            content.setVisible(false);
            tttboard.setVisible(false);
        } else {
            newGame();
            clearBoard();
            this.gameState.setText("Player " + ntttgame.getCurrentPlayer() 
            + " please indicate where you would like to put your token.");
            
        }
    }

    /*
     * Accessor method to access the current token either X or O
     * @return a string X or O
     */
    private String getToken(){
        if (ntttgame.getCurrentPlayer() == 1) {
            return "Odd";
        }
        return "Even";
    }


     /*
     * Creates a new tic tac toe game
     */
    private void newGame() {
        ntttgame = new NumTTT(3, 3);
    }


    /*
     * Clears the full board so it can be used in a new game
     */
    private void clearBoard() {
        for (int i = 0; i < ntttgame.getWidth(); i++){
            for (int j = 0; j < ntttgame.getHeight(); j++){
                buttonArray[i][j].setText(ntttgame.getCell(buttonArray[i][j].getAcross(), buttonArray[i][j].getDown()));
            }
        }
    }
}