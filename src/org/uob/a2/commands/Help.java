package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the help command, providing the player with instructions or information
 * about various topics related to the game.
 * 
 * <p>
 * The help command displays information on how to play the game, including details about 
 * available commands, their syntax, and their purpose.
 * </p>
 */
public class Help extends Command {

    public Help(String topic) {
        this.commandType = CommandType.HELP;
        this.value = topic;
    }

    @Override
    public String toString() {
        return "HELP " + value;
    }

    public String execute(GameState gameState) {
            return getHelp(value);
    }

    private String getGeneralHelp() {
        return "╭─────────────────────────────────────────────────────────────────────────╮\n" +
               "│                              Welcome to the game!                       │\n" +
               "│                            Available commands:                          │\n" +
               "╰─────────────────────────────────────────────────────────────────────────╯\n" +
               "  - MOVE <exit name>: Move to a different location as defined by an exit’s name (e.g., 'move north').\n" +
               "  - LOOK <room|exit|features>|<item name>|<equipment name>|<feature name>: Look around the current room, at an exit, at a feature, or at a specific item, equipment, or feature.\n" +
               "  - GET <item name|equipment name>: Pick up an item or equipment from the current room (e.g., 'get key').\n" +
               "  - DROP <item name|equipment name>: Drop an item or equipment from your inventory (e.g., 'drop key').\n" +
               "  - USE <equipment name> on|with <feature|item>: Use an item in your inventory on its own, or on a feature or item (e.g., 'use lamp' or 'use key on chest').\n" +
               "  - STATUS <inventory|player|item name|equipment name|map|score>: Check your current status, or inventory; or get more information about a specific item or equipment in your inventory (e.g., 'status player', 'status inventory', 'status key'). Also able to display the map and your score.\n" +
               "  - HELP <topic>: Display this help information or get help on a specific topic (e.g., 'help move' or 'help').\n" +
               "  - COMBINE <item1> and <item2>: Combine two items into a new item or equipment (e.g., 'combine stick and rock' or 'combine egg and flour').\n" +
               "  - QUIT: Exit the game.\n" + "\n" +
               "Additional guides :\n" +
               " 'Status crafting' : Open's up a crafting guide on what you can craft by combining items\n" +
               "'Status score' : Open's up a guide on how the score system works\n" +
               "Consider using the 'help' command with a specific topic to get more information about it.";

    }

    private String getHelp(String topic) {
        if (topic == null || topic.trim().isEmpty()) {
            return getGeneralHelp();
        }
        switch (topic.toLowerCase()) {

            case "move":
            return "╭─────────────────────────╮\n" +
                   "│      MOVE Command:      │\n" +
                   "╰─────────────────────────╯\n" +
                   "Use the 'move' command with direction / exit name.\n" + 
                   "move <exit name>: Move to a different location as defined by an exit’s name or direction (e.g., 'move north').";
        case "look":
            return "╭─────────────────────────╮\n" +
                   "│      LOOK Command:      │\n" +
                   "╰─────────────────────────╯\n" +
                   "look <room|exit|features>|<item name>|<equipment name>|<feature name>: Look around the current room, at an exit, at a feature, or at a specific item, equipment, or feature.";
        case "get":
            return "╭─────────────────────────╮\n" +
                   "│      GET Command:       │\n" +
                   "╰─────────────────────────╯\n" +
                   "get <item name|equipment name>: Pick up an item or equipment from the current room (e.g., 'get key').";
        case "drop":
            return "╭─────────────────────────╮\n" +
                   "│      DROP Command:      │\n" +
                   "╰─────────────────────────╯\n" +
                   "drop <item name|equipment name>: Drop an item or equipment from your inventory (e.g., 'drop key').";
        case "use":
            return "╭─────────────────────────╮\n" +
                   "│      USE Command:       │\n" +
                   "╰─────────────────────────╯\n" +
                   "use <equipment name> on|with <feature|item>: Use an item in your inventory on its own, or on a feature or item (e.g., 'use lamp' or 'use key on chest').";
        case "status":
            return "╭─────────────────────────╮\n" +
                   "│     STATUS Command:     │\n" +
                   "╰─────────────────────────╯\n" +
                   "status <inventory|player|item name|equipment name|map|score>: Check your current status, or inventory; or get more information about a specific item or equipment in your inventory (e.g., 'status player', 'status inventory', 'status key'). Also able to display the map and your score.";
        case "crafting":
            return "╭─────────────────────────╮\n" +
                   "│    CRAFTING GUIDE:      │\n" +
                   "╰─────────────────────────╯\n" +
                   "rope + anchor = anchoredrope\n" + 
                   "fragment + shard = steelkey\n" + 
                   "blade + handle = dagger";
        case "help":
            return "╭─────────────────────────╮\n" +
                   "│      HELP Command:      │\n" +
                   "╰─────────────────────────╯\n" +
                   "help <topic>: Display this help information or get help on a specific topic (e.g., 'help move' or 'help').";
        
        case "score":
            return "╭─────────────────────────╮\n" +
                   "│      SCORE GUIDE:       │\n" +
                   "╰─────────────────────────╯\n" +
                   "+10 points for using an equipment successfully\n" + 
                   "+5 points for combing 2 items\n" +
                   "+2 point for each equipment you have/pick up\n" +
                   "+1 points for each item you have/pick up\n" +
                   "-1 point every 5 seconds on select rooms\n";
        case "combine":
            return "╭─────────────────────────╮\n" +
                   "│    COMBINE Command:     │\n" +
                   "╰─────────────────────────╯\n" +
                   "combine <item1> with <item2>: Combine two items in your inventory to create a new item (e.g., 'combine stick with cloth').";
        case "quit":
            return "╭─────────────────────────╮\n" +
                   "│      QUIT Command:      │\n" +
                   "╰─────────────────────────╯\n" +
                   "quit: Exit the game.";
            default:
                return "No help available for the topic: " + topic;
        }
    }
  

}
