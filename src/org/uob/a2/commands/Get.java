package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the get command, allowing the player to pick up an item from the current room and add it to their inventory.
 * 
 * <p>
 * This command checks if the specified item is present in the current room. If the item exists and the player
 * does not already have it, the item is added to the player's inventory and removed from the room. Otherwise,
 * an appropriate message is returned.
 * </p>
 */
public class Get extends Command {
    
    public Get(String item){
        this.commandType = CommandType.GET;
        this.value = item;
    }

    @Override
    public String toString(){
        return "CommandType: " + commandType + ", Item: " + value;
    }

    public String execute(GameState gameState) {
        Room currentRoom = gameState.getMap().getCurrentRoom();
        Item itemToGet = currentRoom.getItemByName(value);
        Equipment equipmentToGet = currentRoom.getEquipmentByName(value);

        if ("ancientcrown".equals(value)) {
            return "";
        }
        else{
        if (itemToGet != null) {
            if (!gameState.getPlayer().hasItem(value)) {
                gameState.getPlayer().addItem(itemToGet);
                currentRoom.removeItem(itemToGet);
                return "You pick up: " + value;
            } else {
                return "You already have " + value;
            }
        } else if (equipmentToGet != null) {
            if (!gameState.getPlayer().hasEquipment(value)) {
                gameState.getPlayer().addEquipment(equipmentToGet);
                currentRoom.removeEquipment(equipmentToGet);
                return "You pick up: " + value;
            } else {
                return "You already have " + value;
            }    
        } else { 
            return "No " + value + " to get.";
        }
    }
}
   
}
