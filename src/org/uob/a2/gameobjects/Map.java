package org.uob.a2.gameobjects;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents the game map, which consists of a collection of rooms and the current room the player is in.
 * 
 * <p>
 * The map allows for navigation between rooms, adding new rooms, and managing the current room context.
 * </p>
 */
public class Map extends Object{
    private Room currentRoom;
    private ArrayList<Room> rooms;
  
    /**
     * Returns a string representation of the map, including all rooms.
     *
     * @return a string describing the map and its rooms
     */

    public Map() {
        this.rooms = new ArrayList<>();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    public void setCurrentRoom(String roomId){
        for (Room r : this.rooms) {
            if (r.getId().equals(roomId)) {
                this.currentRoom = r;
                break;
            } 
        }
    }


    public String displayMap() {
        int gridSize = 10; // 10x10 grid to have enough room for all rooms
        // make 2d array called grid with 19x19 dimensions to allow for a central room with space for exits and connected rooms around it
        String[][] grid = new String[gridSize * 2 - 1][gridSize * 2 - 1];
        // hashmap is used here to store the position of rooms on the grid itself
        HashMap<String, int[]> roomPositions = new HashMap<>();
        
        // simple for loop to fill the grid with .'s
        for (int i = 0; i < gridSize * 2 - 1; i++) {
            for (int j = 0; j < gridSize * 2 - 1; j++) {
                grid[i][j] = ".";
            }
        }
        
        if (currentRoom != null) {
            // currentroom position is set to center of grid 9,9 and is marked with X
            int[] currentPosition = new int[]{gridSize - 1, gridSize - 1};
            grid[currentPosition[0]][currentPosition[1]] = "X";
            // store current room in roomPositions map
            roomPositions.put(currentRoom.getId(), currentPosition);
            
            // loop through exits of current room to draw the corresponding exits on the grid (basically based on game.txt exits)
            for (Exit e : currentRoom.getExits()) {
                
                if (!e.getHidden()) {
                    // for each exit that isnt hidden it calculates the new position of the room based on the direction of the exit
                    int[] newPosition = new int[]{currentPosition[0], currentPosition[1]};
                    switch (e.getDirection().toLowerCase()) {
                        case "north":
                            newPosition[0] -= 2;
                            grid[currentPosition[0] - 1][currentPosition[1]] = "|";
                            break;
                        case "south":
                            newPosition[0] += 2;
                            grid[currentPosition[0] + 1][currentPosition[1]] = "|";
                            break;
                        case "east":
                            newPosition[1] += 2;
                            grid[currentPosition[0]][currentPosition[1] + 1] = "-";
                            break;
                        case "west":
                            newPosition[1] -= 2;
                            grid[currentPosition[0]][currentPosition[1] - 1] = "-";
                            break;
                    } 
                    // if the new position is within the grid bounds it sets the room id in the grid and stores the position in the roomPositions map
                    if (newPosition[0] >= 0 && newPosition[0] < gridSize * 2 - 1 && newPosition[1] >= 0 && newPosition[1] < gridSize * 2 - 1) {
                        // get the next room based on the exit
                        String nextRoomId = e.getNextRoom();
                        Room nextRoom = null;

                        // find room object corresponding to the next room id
                        for (Room r : this.rooms) {
                            if (r.getId().equals(nextRoomId)) {
                                nextRoom = r;
                                break;
                            }
                        }
                        // if next room was found
                        if (nextRoom != null) {
                            // place the next room's id on the grid and store its position
                            grid[newPosition[0]][newPosition[1]] = nextRoom.getId();
                            roomPositions.put(nextRoom.getId(), newPosition);
                        }
                    }
                }
            }
        }
        
        // Build the map string
        StringBuilder out = new StringBuilder("Map ('X' marks current room):\n");
        for (int i = 0; i < gridSize * 2 - 1; i++) {
            for (int j = 0; j < gridSize * 2 - 1; j++) { 
                out.append(grid[i][j]); // apend the grid element to the output string sb
            }
            out.append("\n");
        }
        
        return out.toString(); 
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("Map:\n");
        for (Room r : this.rooms) {
            out.append(r.toString()).append("\n");
        }
        return out.toString();
    }
}

