package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the move command, allowing the player to navigate to a different room in the game world.
 * 
 * <p>
 * The move command checks if the specified direction corresponds to an available exit in the current room.
 * If a matching exit is found, the player's location is updated to the connected room.
 * </p>
 */
public class Move extends Command {

    public Move(String direction){
        this.commandType = CommandType.MOVE;
        this.value = direction;
    }

    @Override
    public String toString(){
        return "CommandType: " + commandType + ", Direction: " + value;
    }


    public String execute(GameState gameState) {

        for(Exit i : gameState.getMap().getCurrentRoom().getExits()){
            if(i.getDirection().equalsIgnoreCase(value)){
                String id = i.getId();
                if (!gameState.getMap().getCurrentRoom().getExit(id).getHidden()) {
                    Room nextRoom = gameState.getMap().getCurrentRoom().getExit(id).getNextRoom();
                    gameState.getMap().setCurrentRoom(nextRoom);

                    if (!nextRoom.isVisited()){
                        gameState.addPoints(10);
                        nextRoom.setVisited(true);
                    }

                    return "Moving towards " + value + "\n";
                } else {
                    return "No exit found in that direction.";
                }

            }
        } return "No exit found in that direction.";


    }

  
}
