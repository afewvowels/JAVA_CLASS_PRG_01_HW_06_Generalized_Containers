/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podapp;

import java.util.*;

/**
 * This class is used by PodApp to store its Pod objects that move across the
 * game board, PodList uses an ArrayList<Pod> to store the Pod objects.
 * @author bluebackdev
 */
public class PodList {
    /**
     * Main ArrayList used to store Objects of type Pod.
     */
    ArrayList<Pod> podList = new ArrayList();
    
    /**
     * Game board Width and Height passed in for use in constructing Pod objects.
     */
    final int boardWidth;
    final int boardHeight;
    
    /**
     * This Random object is used for generating new Pod objects in the main
     * pod array per turn. For neatness, a single Random object is created as
     * there is only one PodList object in the PodApp game, this static
     * designation would need to be removed if multiple instances of PodList
     * were to be created.
     */
    static Random rand = new Random();

    /**
     * Main PodList constructor, initializes the game board Width and Height
     * dimensions as passed through by PodApp and initializes the ArrayList
     * with four Pods at pre-determined locations.
     * @param width
     * @param height 
     */
    public PodList(int width, int height) {
        this.boardWidth = width;
        this.boardHeight = height;
        
        this.podList.add(new Pod(1, 5, "east", this.boardWidth, this.boardHeight));
        this.podList.add(new Pod(2, 1, "north", this.boardWidth, this.boardHeight));
        this.podList.add(new Pod(12, 2, "west", this.boardWidth, this.boardHeight));
        this.podList.add(new Pod(13, 6, "south", this.boardWidth, this.boardHeight));
    }
    
    /**
     * Used by PodApp to determine if a pod exists at a location passed in as
     * x and y
     * @param x X-Coordinate to check for Pod presence
     * @param y Y-Coordinate to check for Pod presence
     * @return 
     */
    public boolean isPod(int x, int y) {
        if(podList.contains(new Pod(x, y))) {
            return true;
        }
        
        return false;
    }
    
    /**
     * Main move method moves all the Pod objects in the main ArrayList,
     * iterate through ArrayList and call .move() method.
     */
    public void moveAll() {
        podList.forEach((p)->p.move());
    }
    
    /**
     * Similar to .isPod() in that this method compares a passed in x and y
     * parameters to check against all Pods objects in the ArrayList, if
     * a match is found, that Pod object is removed from the ArrayList, a loop
     * is used to make sure that the identified pod (i) is the pod that is
     * removed (i).
     * @param playerX
     * @param playerY 
     */
    public void playerAt(int playerX, int playerY) {
        for(int i = 0; i < podList.size(); i++) {
            if(podList.get(i).equals(new Pod(playerX, playerY))) {
                podList.remove(i);
            }
        }
    }
    
    /**
     * Randomly generates new Pod objects to append to the ArrayList with a
     * 1 in 10 chance each time the method is called (once per turn in PodApp),
     * uses a switch statement to randomly select a direction.
     */
    public void generate() {
        if(rand.nextInt(10) == 7) {
            switch(rand.nextInt(4)) {
                case 0: 
                    podList.add(new Pod(rand.nextInt(this.boardHeight),
                                        rand.nextInt(this.boardWidth),
                                        "north", this.boardWidth, this.boardHeight));
                    break;
                case 1: 
                    podList.add(new Pod(rand.nextInt(this.boardHeight),
                                        rand.nextInt(this.boardWidth),
                                        "south", this.boardWidth, this.boardHeight));
                    break;
                case 2: 
                    podList.add(new Pod(rand.nextInt(this.boardHeight),
                                        rand.nextInt(this.boardWidth),
                                        "east", this.boardWidth, this.boardHeight));
                    break;
                case 3: 
                    podList.add(new Pod(rand.nextInt(this.boardHeight),
                                        rand.nextInt(this.boardWidth),
                                        "west", this.boardWidth, this.boardHeight));
                    break;
                default:
                    break;
            }
        }
    }
}
