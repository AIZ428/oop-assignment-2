package org.uob.a2.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import org.uob.a2.gameobjects.*;

/**
 * Utility class for parsing a game state from a file.
 * 
 * <p>
 * This class reads a structured text file to create a {@code GameState} object,
 * including the player, map, rooms, items, equipment, features, and exits.
 * </p>
 */
public class GameStateFileParser {

    public GameStateFileParser() {
    }

    public static GameState parse(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        GameState gameState = new GameState();
        Map map = new Map();
        Room currentRoom = null;
        String currentRoomName = null;

        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.startsWith("player:")) {
                String playerName = line.substring(7).trim();
                gameState.setPlayer(new Player(playerName));
            } else if (line.startsWith("map:")) {
                currentRoomName = line.substring(4).trim();
            } else if (line.startsWith("room:")) {
                String[] roomData = line.substring(5).split(",");
                currentRoom = new Room(roomData[0], roomData[1], roomData[2], Boolean.parseBoolean(roomData[3]));
                map.addRoom(currentRoom);
            } else if (line.startsWith("equipment:")) {
                if (currentRoom != null) {
                    String[] equipmentData = line.substring(10).split(",");
                    UseInformation useInfo = new UseInformation(false, equipmentData[4], equipmentData[5], equipmentData[6], equipmentData[7]);
                    Equipment equipment = new Equipment(equipmentData[0], equipmentData[1], equipmentData[2], Boolean.parseBoolean(equipmentData[3]), useInfo);
                    currentRoom.addEquipment(equipment);
                }
            } else if (line.startsWith("container:")) {
                if (currentRoom != null) {
                    String[] containerData = line.substring(10).split(",");
                    Container container = new Container(containerData[0], containerData[1], containerData[2], Boolean.parseBoolean(containerData[3]));
                    currentRoom.addFeature(container);
                }
            } else if (line.startsWith("item:")) {
                if (currentRoom != null) {
                    String[] itemData = line.substring(5).split(",");
                    Item item = new Item(itemData[0], itemData[1], itemData[2], Boolean.parseBoolean(itemData[3]));
                    currentRoom.addItem(item);
                }
            } else if (line.startsWith("exit:")) {
                if (currentRoom != null) {
                    String[] exitData = line.substring(5).split(",");
                    Exit exit = new Exit(exitData[0], exitData[1], exitData[2], exitData[3], Boolean.parseBoolean(exitData[4]));
                    currentRoom.addExit(exit);

                }
            }
        }

        if (currentRoomName != null) {
            map.setCurrentRoom(currentRoomName);
        }


        gameState.setMap(map);
        reader.close();
        return gameState;
    }
}