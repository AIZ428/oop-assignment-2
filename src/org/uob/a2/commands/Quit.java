package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the quit command, allowing the player to exit the game.
 * 
 * <p>
 * The quit command signals the end of the game session and provides a summary of the player's
 * current status before termination.
 * </p>
 */
public class Quit extends Command {

    public Quit(){
        this.commandType = CommandType.QUIT;
    }

    public String execute(GameState gameState) {
        Player player = gameState.getPlayer();
        StringBuilder result = new StringBuilder("Game over:\n");

        // Add player status
        result.append("Player Status:\n");
        result.append("Name: ").append(player.getName()).append("\n");

        // Add player inventory
        result.append("Inventory:\n");
        for (Item item : player.getInventory()) {
            result.append("- ").append(item.getName()).append(": ").append(item.getDescription()).append("\n");
        } for (Equipment equipment : player.getEquipment()) {
            result.append("- ").append(equipment.getName()).append(": ").append(equipment.getDescription()).append("\n");
        }

        

        result.append("Score: ").append(gameState.getScore()).append("\n");

        return result.toString();
    }
}