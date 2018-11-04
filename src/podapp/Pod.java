/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podapp;

import java.util.Random;
/**
 *
 * @author kbsmith01
 * This class contains all the information that the Pod class requires to be
 * drawn on the board. Keeps track of information like Pod position, current
 * direction of movement, and whether or not the Pod object has contacted
 * the player object in the game.
 * 
 * 
 */
public class Pod {
    /**
     * Keeps track of the Pod object's X position (horizontal)
     * Keeps track of the Pod object's Y position (vertical)
     */
    private int posX;
    private int posY;
    
    /**
     * Temporary variable that updates with a new player X Position
     * Temporary variable that updates with a new player Y position
     */
    private int playerX;
    private int playerY;
    
    /**
     * Static variables keeping track of the board's width and height for
     * comparing current Pod position to for switching directions and direction
     * of movement
     */
    private int boardHeight;
    private int boardWidth;
    
    /**
     * Keeps track of Pod's current direction with a string for use with enum
     */
    private String currentDirection;
    
    /**
     * This is the default Pod object constructor
     * @param x X Position of Pod object passed in on creation
     * @param y Y Position of Pod object passed in on creation
     * @param direction Direction of movement of Pod object
     * @param width Defines width of board
     * @param height Defines height of board
     */
    public Pod(int x, int y, String direction, int width, int height) {
        this.posX = x;
        this.posY = y;
        
        this.currentDirection = direction;
        
        this.boardWidth = width;
        this.boardHeight = height;
    }
    
    /**
     * This constructor is for use with the .equals method, it only creates a
     * pod with an x and a y coordinate as the temporary pod does not move
     * (no need for direction) and is passed in x and y variables within the
     * limits of boardHeight and boardWidth.
     * @param x X-Coordinate for comparison
     * @param y Y-Coordinate for comparison
     */
    public Pod(int x, int y) {
        this.posX = x;
        this.posY = y;
    }
    
    /**
     * This method uses the currentDirection variable to move the pod object
     * in a direction or changes the direction of movement to its opposite
     * (north to south, east to west, etc.) if its position is along
     * the boards edge is at a min/max value
     */
    public void move() {
        switch(this.currentDirection) {
            case "north":
                if (this.posY < this.boardHeight -1) {
                    this.posY++;
                }
                else if (this.posY == this.boardHeight - 1) {
                    this.currentDirection = "south";
                }
                break;
            case "south":
                if (this.posY > 0) {
                    this.posY--;
                }
                else if (posY == 0) {
                    this.currentDirection = "north";
                }
                break;
            case "east":
                if (this.posX < this.boardWidth -1) {
                    this.posX++;
                }
                else if (this.posX == this.boardWidth - 1) {
                    this.currentDirection = "west";
                }
                break;
            case "west":
                if (this.posX > 0) {
                    this.posX--;
                }
                else if (this.posX == 0) {
                    this.currentDirection = "east";
                }
                break;
            default:
                break;
        }
    }
    
    /**
     * Equals method takes in a Pod object to compare to existing Pod object's
     * x and y coordinates.
     * @param x Pod object x for comparison to this object.
     * @return 
     */
    public boolean equals(Object x) {
        if(x instanceof Pod) {
            Pod p = (Pod)x;

            return((posX == p.posX) && (posY == p.posY));
        }
        else {
            return false;
        }
    }
}
