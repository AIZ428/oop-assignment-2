package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the look command, allowing the player to examine various elements of the game world.
 * 
 * <p>
 * The look command can provide details about the current room, its exits, features, or specific items and equipment.
 * Hidden objects are not included in the output unless explicitly revealed.
 * </p>
 */
public class Look extends Command {

    public Look(String target){
        this.commandType = CommandType.LOOK;
        this.value = target;
    }

    @Override
    public String toString(){
        return "CommandType: " + commandType + ", Target: " + value;
    }

    public String execute(GameState gameState){
        Room currentRoom = gameState.getMap().getCurrentRoom();
        StringBuilder result = new StringBuilder();

        switch (value.toLowerCase()) {
            case "room":
            result.append("╭─────────────────────────╮\n");
            result.append("│       Room Details      │\n");
            result.append("╰─────────────────────────╯\n");
            result.append(currentRoom.getDescription()).append("\n\n");
            result.append("In the room there is:\n");
            for (Item item : currentRoom.getItems()) {
                if (!item.getHidden()) {
                    result.append("  • ").append(item.getDescription()).append("\n");
                }
            }
            for (Equipment equipment : currentRoom.getEquipments()) {
                if (!equipment.getHidden()) {
                    result.append("  • ").append(equipment.getDescription()).append("\n");
                }
            }
            for (Feature feature : currentRoom.getFeatures()) {
                if (!feature.getHidden()) {
                    result.append("  • ").append(feature.getDescription()).append("\n");
                }
            }
            result.append("═══════════════════════════\n");
            break;
            case "exit":
                result.append("╭─────────────────────────╮\n");
                result.append("│       Available Exits   │\n");
                result.append("╰─────────────────────────╯\n");
                result.append("The available exits are:\n");
                for (Exit exit : currentRoom.getExits()) {
                    if (!exit.getHidden()) {
                        result.append("  • ").append(exit.getDescription()).append("\n");
                    }
                }
                result.append("═══════════════════════════\n");
                break;

            case "exits":
                result.append("╭─────────────────────────╮\n");
                result.append("│       Available Exits   │\n");
                result.append("╰─────────────────────────╯\n");
                result.append("The available exits are:\n");
                for (Exit exit : currentRoom.getExits()) {
                    if (!exit.getHidden()) {
                        result.append("  • ").append(exit.getDescription()).append("\n");
                    }
                }
                result.append("═══════════════════════════\n");
                break;

            case "features":
                result.append("╭─────────────────────────╮\n");
                result.append("│       Features          │\n");
                result.append("╰─────────────────────────╯\n");
                result.append("You also see:\n");
                for (Feature feature : currentRoom.getFeatures()) {
                    if (!feature.getHidden()) {
                        result.append("  • ").append(feature.getDescription()).append("\n");
                    }
                }
                result.append("═══════════════════════════\n");
                break;
            default:
            Item item = currentRoom.getItemByName(value);
            if (item != null && !item.getHidden()) {
                result.append("╭─────────────────────────╮\n");
                result.append("│       Item Details      │\n");
                result.append("╰─────────────────────────╯\n");
                result.append(item.getDescription()).append("\n");
                result.append("═══════════════════════════\n");
                break;
            }
            Equipment equipment = currentRoom.getEquipmentByName(value);
            if (equipment != null && !equipment.getHidden()) {
                result.append("╭─────────────────────────╮\n");
                result.append("│    Equipment Details    │\n");
                result.append("╰─────────────────────────╯\n");
                result.append(equipment.getDescription()).append("\n");
                result.append("═══════════════════════════\n");
                break;
            }
            Feature feature = currentRoom.getFeatureByName(value);
            if (feature != null && !feature.getHidden()) {
                result.append("╭─────────────────────────╮\n");
                result.append("│     Feature Details     │\n");
                result.append("╰─────────────────────────╯\n");
                result.append(feature.getDescription()).append("\n");
                result.append("═══════════════════════════\n");
                break;
                }
                break;
        }

        return result.toString();

   
}
}