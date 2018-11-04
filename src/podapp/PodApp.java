/*
* This class represents a visual application for the "pod chase" game.
*/

package podapp;

/**
*
* @author jrsullins
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PodApp extends JFrame implements ActionListener {
    // Member variables for visual objects.
    private JLabel[][] board; // 2D array of labels. Displays either # for player,
                              // * for pod, or empty space
    private JButton northButton, // player presses to move up
                    southButton, // player presses to move down
                    eastButton,  // player presses to move right
                    westButton;  // player presses to move left
    // Current width and height of board
    private int width = 15;
    private int height = 9;
    // Current location of player
    private int playerX = 7;
    private int playerY = 4;
    // PodList object
    private PodList pods;
    public PodApp() {
        // Construct a panel to put the board on and another for the buttons
        JPanel boardPanel = new JPanel(new GridLayout(height, width));
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
        // Use a loop to construct the array of labels, adding each to the
        // board panel as it is constructed. Note that we create this in
        // "row major" fashion by making the y-coordinate the major
        // coordinate. We also make sure that increasing y means going "up"
        // by building the rows in revers order.
        board = new JLabel[height][width];
        for (int y = height-1; y >= 0; y--) {
            for (int x = 0; x < width; x++) {
                // Construct a label to represent the tile at (x, y)
                board[y][x] = new JLabel(" ", JLabel.CENTER);
                // Add it to the 2D array of labels representing the visible board
                boardPanel.add(board[y][x]);
                }
            }
            // Construct the buttons, register to listen for their events,
            // and add them to the button panel
            northButton = new JButton("N");
            southButton = new JButton("S");
            eastButton = new JButton("E");
            westButton = new JButton("W");
            // Listen for events on each button
            northButton.addActionListener(this);
            southButton.addActionListener(this);
            eastButton.addActionListener(this);
            westButton.addActionListener(this);
            // Add each to the panel of buttons
            buttonPanel.add(northButton);
            buttonPanel.add(southButton);
            buttonPanel.add(eastButton);
            buttonPanel.add(westButton);
            // Add everything to a main panel attached to the content pane
            JPanel mainPanel = new JPanel(new BorderLayout());
            getContentPane().add(mainPanel);
            mainPanel.add(boardPanel, BorderLayout.CENTER);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);
            // Size the app and make it visible
            setSize(300, 200);
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Construct the PodList with 4 pods initially
            pods = new PodList(width, height);
            // Draw the initial board
            drawBoard();
        }
    // Auxiliary method to display player and pods in labels.
    public void drawBoard() {
        // Determine what to display in each square
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Is the player at this (x, y) location?
                if (playerX == x && playerY == y) {
                    board[y][x].setText("#");
                }
                // Is there a pod at this (x, y) location?
                else if (pods.isPod(x, y)) {
                    board[y][x].setText("*");
                }
                // Otherwise, draw a space there.
                else {
                    board[y][x].setText(" ");
                }
            }
        }
    }
    public void actionPerformed(ActionEvent e) {
        // Move the pods
        pods.moveAll();
        // Determine which button was pressed, and move player in that
        // direction (making sure they don't leave the board).
        if (e.getSource() == southButton && playerY > 0) {
            playerY--;
        }        
        if (e.getSource() == northButton && playerY < height-1) {
            playerY++;
        }
        if (e.getSource() == eastButton && playerX < width-1) {
            playerX++;
        }
        if (e.getSource() == westButton && playerX > 0) {
            playerX--;
        }
        // Notify the pod list about player location
        pods.playerAt(playerX, playerY);
        // Possibly generate another pod
        pods.generate();
        // Redraw the board
        drawBoard();
    }
    /**
    * @param args the command line arguments
    */
    public static void main(String[] args) {
        PodApp a = new PodApp();
    }
}
