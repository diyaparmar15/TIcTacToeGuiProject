package boardgame.ui;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.BorderFactory;
import java.awt.Color;

import boardgame.tictactoe.TTTview;

import boardgame.numtictactoe.NumTTTView;

/*
 * This class is the Main GUI class which is responsible for loading the GUI windows and 
 * calling other UI classes to dispay the appropriate window
 */
public class GameUI extends JFrame{
    private JPanel mainPanel;
    private JPanel gameSelectPanel;


    public static void main(String[] args){

        //Create the gui window
        GameUI window = new GameUI("*Game*");
        window.setVisible(true);
    
    }

    /*
     * Constructor takes care of formatting the window with border layout and dimensions
     * @param label
     */
    public GameUI(String label){
        super();
        this.setSize(500, 400);
        this.setTitle(label);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        this.add(mainPanel, BorderLayout.CENTER);
        initialize();

    }

    //Initialize the panel details for the window to show up
    public void initialize(){
        mainPanel.add(rootPanel());
        getContentPane().repaint();
        getContentPane().revalidate();
        pack();
    }

    /*
     * Creates a JPanel for the main window of the GUI using layoyt manager
     * @return a Jpanel root, which is the final set window
     */
    private JPanel rootPanel() {
        JPanel root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBorder(BorderFactory.createLineBorder(Color.black));
        //Add the welcome message to tho window
        root.add(welcomeMessage());
        root.add(createGameSelect());

        return root;
    }

    /*
     * Creates a welcome message and adds it into the window 
     * @return a JPanel message that updates the window with the message
     */
    private JPanel welcomeMessage() {
        JPanel message = new JPanel();
        message.setLayout(new BorderLayout());
        message.add(new JLabel("Welcome to Diya's Project"));
        message.setBorder(BorderFactory.createLineBorder(Color.black));
        return message;
    }

    /*
     * Adds the buttons created onto the window
     * @return a JPanel which is the updated window with the buttons
     */
    private JPanel createGameSelect() {
        gameSelectPanel = new JPanel();
        gameSelectPanel.setLayout(new BoxLayout(gameSelectPanel, BoxLayout.Y_AXIS));
        gameSelectPanel.add(tttButtonStart());
        gameSelectPanel.add(numButtonStart());
        gameSelectPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        return gameSelectPanel;
    }

    /*
     * Creates a JButton for selecting the tic tac toe game
     * @return a JButton that was created
     */
    private JButton tttButtonStart() {
        JButton selectTTT = new JButton("Play Tic Tac Toe");
        selectTTT.setBorder(BorderFactory.createLineBorder(Color.red));
        selectTTT.setForeground(Color.BLUE);
        selectTTT.addActionListener(e -> playTTT());
        return selectTTT;
    }

    /*
     * Creates another JButton for selecting the numerical tic tac toe game
     * @returns a JButton that was created
     */
    private JButton numButtonStart(){
        JButton selectNTTT = new JButton("Play Numerical Tic Tac Toe");
        selectNTTT.setBorder(BorderFactory.createLineBorder(Color.red));
        selectNTTT.setForeground(Color.MAGENTA);
        selectNTTT.addActionListener(e -> playNumTTT());
                                
        return selectNTTT;
    }

    /*
     * Adds a new window of the selected button, this button is for Tic Tac Toe
     */
    protected void playTTT() {
        mainPanel.removeAll();
        mainPanel.add(new TTTview(3, 3, this));
        getContentPane().repaint();
        getContentPane().revalidate();
        pack();
    }
    /*
     * Adds a new window of the selected button, this button is for Numerical Tic Tac Toe
     */
    protected void playNumTTT() {
        mainPanel.removeAll();
        mainPanel.add(new NumTTTView(3, 3, this));
        getContentPane().repaint();
        getContentPane().revalidate();
        pack();
    }


}
