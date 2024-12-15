package org.uob.a2.gameobjects;

/**
 * Represents the current state of the game, including the map and the player.
 * 
 * <p>
 * The game state contains all necessary information about the game's progress, such as
 * the player's status and the state of the game map.
 * </p>
 */
public class GameState extends Object {
   

    public Map map;
    public Player player;
    private int score;

    

    public GameState(Map map, Player player) {
        this.map = map;
        this.player = player;
        this.score = 0;
    }

    public GameState(){
        this.map = null;
        this.player = null;
        this.score = 0;
    }

    public void setPlayer(Player player) {
        this.player = player;

    }

    public void setMap(Map map) {
        this.map = map;
    }

    /**
     * Returns a string representation of the game state, including the map and player details.
     *
     * @return a string describing the game state
     */
    
    public Map getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }

    public int getScore() {
        // add points based on inventory, each item being 1 point, and each equipment being 2 points
        int itemScore = player.getInventory().size(); 
        int equipmentScore = player.getEquipment().size() * 2; 
        int totalScore = itemScore + equipmentScore;
        return totalScore + score; // adds that to the additional points from other places like use/combine etc
    }

    public void addPoints(int points) {
        this.score += points;
    }

    public void subtractPoints(int points) {
        this.score -= points;
    }
    

    
    @Override
    public String toString() {
        return "GameState {" +
               "map=" + (map != null ? map.toString() : "null") + ", " +
               "player=" + (player != null ? player.toString() : "null") +
               '}';
    }
}
