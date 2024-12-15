package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the drop command, allowing the player to drop an item or equipment from their inventory into the current room.
 * 
 * <p>
 * This command checks if the player possesses the specified item or equipment and, if so, removes it from their inventory
 * and adds it to the current room. If the player does not have the item or equipment, an error message is returned.
 * </p>
 */
public class Drop extends Command {

    public Drop(String item) {
        this.commandType = CommandType.DROP;
        this.value = item;
    }

    @Override
    public String toString() {
        return "Drop " + value;
    }

    public String execute(GameState gameState) {
        Player player = gameState.getPlayer();
        Room currentRoom = gameState.getMap().getCurrentRoom();

        // Check if the player has the item
        Item itemToDrop = player.getItem(value);
        if (itemToDrop != null) {
            player.getInventory().remove(itemToDrop);
            currentRoom.addItem(itemToDrop);
            return "You drop: " + value;
        }

        // Check if the player has the equipment
        Equipment equipmentToDrop = player.getEquipment(value);
        if (equipmentToDrop != null) {
            player.getEquipment().remove(equipmentToDrop);
            currentRoom.addEquipment(equipmentToDrop);
            return "You drop: " + value;
        }

        // If neither item nor equipment is found
        return "You cannot drop nonexistent";
    }
}