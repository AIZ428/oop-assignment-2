package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the use command, allowing the player to use equipment on a specific target in the game.
 * 
 * <p>
 * The use command checks if the player has the specified equipment and whether it can interact with
 * the target. The target can be a feature, item, or the current room, depending on the game context.
 * </p>
 */
public class Use extends Command {
    
    private String target;

    public Use(String equipmentName, String target) {
        this.commandType = CommandType.USE;
        this.value = equipmentName;
        this.target = target;
    }

    @Override
    public String toString() {
        return "Use " + value + " on " + target;
    }

    public String execute(GameState gameState){
        Player player = gameState.getPlayer();
        Equipment equipment = player.getEquipment(value);


        if (equipment == null) {
            return "You do not have " + value;
        }

        Room currentRoom = gameState.getMap().getCurrentRoom();
        UseInformation useInfo = equipment.getUseInformation();

        if (useInfo.getAction().equalsIgnoreCase("reveal")) {
            GameObject targetObject = currentRoom.getExit(useInfo.getResult());
            if (targetObject != null) {
                targetObject.setHidden(false);
                gameState.addPoints(10); // 10 points after successful use
                return useInfo.getMessage();
            } else {
                return "Invalid use target";
            }
        } else {
            
            GameObject targetObject = currentRoom.getFeatureByName(target);
            if (targetObject == null) {
                targetObject = currentRoom.getItemByName(target);
            }
            if (targetObject == null) {
                targetObject = currentRoom.getExitByName(target);
            }

            if (targetObject == null || !useInfo.getTarget().equalsIgnoreCase(targetObject.getId())) {
                return "Invalid use target";
            }

            if (useInfo.isUsed()) {
                return "You have already used " + value;
            } else {
                useInfo.setUsed(true);
                String actionOfUse = useInfo.getAction().toLowerCase();

                if (actionOfUse.equals("open")) {
                    Equipment resultEquipment = currentRoom.getEquipment(useInfo.getResult());
                    if (resultEquipment != null) {
                        resultEquipment.setHidden(false);
                        
                    }
                    Item resultItem = currentRoom.getItem(useInfo.getResult());
                    if (resultItem != null) {
                        resultItem.setHidden(false);
                    }
                    Container resultContainer = currentRoom.getContainer(useInfo.getResult());
                    if (resultContainer != null) {
                        resultContainer.setHidden(false);
                    }
                }

                gameState.addPoints(10);
                return useInfo.getMessage(); // 10 points after successful use
            }
        }
    }
}