package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the status command, allowing the player to retrieve information
 * about their inventory, specific items, or their overall status.
 * 
 * <p>
 * The status command can display a list of items in the player's inventory, 
 * provide details about a specific item, or show the player's general status.
 * </p>
 */
public class Status extends Command {
    
        public Status(String topic){
            this.commandType = CommandType.STATUS;
            this.value = topic;
        }

        @Override
        public String toString(){
            return "CommandType: " + commandType + ", Topic: " + value;
        }
    
    public String execute(GameState gameState) {
        Player player = gameState.getPlayer();
        switch (value.toLowerCase()) {
            case "inventory":
                return getInventoryStatus(player);
            case "player":
                return getPlayerStatus(player);
            case "map":
                return getMapStatus(gameState);

            case "score":
                return getScoreStatus(gameState);

            default:
                // Check if the value corresponds to an item or equipment
                String itemStatus = getItemStatus(player, value);
                if (!itemStatus.isEmpty()) {
                    return itemStatus;
                }
                return getEquipmentStatus(player, value);
        }
    }

    private String getInventoryStatus(Player player) {
        StringBuilder result = new StringBuilder();
        result.append("╭─────────────────────────╮\n");
        result.append("│       Inventory         │\n");
        result.append("╰─────────────────────────╯\n");
    
        // Add items to the inventory status
        result.append("Items:\n");
        if (player.getInventory().isEmpty()) {
            result.append("  (none)\n");
        } else {
            for (Item item : player.getInventory()) {
                result.append("  • ").append(item.getName()).append("\n");
            }
        }
    
        // Add equipment to the inventory status
        result.append("Equipment:\n");
        if (player.getEquipment().isEmpty()) {
            result.append("  (none)\n");
        } else {
            for (Equipment equipment : player.getEquipment()) {
                result.append("  • ").append(equipment.getName()).append("\n");
            }
        }
    
        result.append("═══════════════════════════\n");
        return result.toString();
    }

    private String getPlayerStatus(Player player) {
        return player.toString();
    }

    private String getMapStatus(GameState gameState) {
        return gameState.getMap().displayMap();
    }

    private String getScoreStatus(GameState gameState) {
        StringBuilder result = new StringBuilder();
        result.append("╭─────────────────────────╮\n");
        result.append("│         Score           │\n");
        result.append("╰─────────────────────────╯\n");
        result.append("Current Score: ").append(gameState.getScore()).append("\n");
        result.append("═══════════════════════════\n");
        return result.toString();
    }

    private String getItemStatus(Player player, String itemName) {
        Item item = player.getItem(itemName);
        if (item != null) {
            StringBuilder result = new StringBuilder();
            result.append("╭─────────────────────────╮\n");
            result.append("│         Item            │\n");
            result.append("╰─────────────────────────╯\n");
            result.append(item.getDescription()).append("\n");
            result.append("═══════════════════════════\n");
            return result.toString();
        } else {
            return "";
        }
    }

    private String getEquipmentStatus(Player player, String equipmentName) {
        Equipment equipment = player.getEquipment(equipmentName);
        if (equipment != null) {
            StringBuilder result = new StringBuilder();
            result.append("╭─────────────────────────╮\n");
            result.append("│       Equipment         │\n");
            result.append("╰─────────────────────────╯\n");
            result.append(equipment.getDescription()).append("\n");
            result.append("═══════════════════════════\n");
            return result.toString();
        } else {
            return "";
        }
    }
    

  
}
