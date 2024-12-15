package org.uob.a2.parser;

import java.util.ArrayList;
import org.uob.a2.commands.*;

/**
 * The {@code Parser} class processes a list of tokens and converts them into {@code Command} objects
 * that can be executed by the game.
 * 
 * <p>
 * The parser identifies the type of command from the tokens and creates the appropriate command object.
 * If the command is invalid or incomplete, a {@code CommandErrorException} is thrown.
 * </p>
 */
public class Parser extends Object{

    public Parser() {

    }

    public Command parse(ArrayList<Token> tokens) throws CommandErrorException{


        Token firstToken = tokens.get(0);
        switch (firstToken.getTokenType()) {
            case MOVE:
                return parseMove(tokens);
            case USE:
                return parseUse(tokens);
            case GET:
                return parseGet(tokens);
            case DROP:
                return parseDrop(tokens);
            case LOOK:
                return parseLook(tokens);
            case STATUS:
                return parseStatus(tokens);
            case HELP:
                return parseHelp(tokens);
            case COMBINE:
                return parseCombine(tokens);    
            case QUIT:
                return parseQuit(tokens);
            default:
                throw new CommandErrorException("Invalid command.");
        }
        
    }

    private Command parseMove(ArrayList<Token> tokens) throws CommandErrorException {
        if (tokens.size() < 2) {
            throw new CommandErrorException("Incomplete move command.");
        }
        String direction = tokens.get(1).getValue();
        return new Move(direction);
    }

    private Command parseUse(ArrayList<Token> tokens) throws CommandErrorException {

        if (tokens.size() == 3){
            String equipment = tokens.get(1).getValue();
            String target = "room";
            return new Use(equipment, target);
        } else if (tokens.size() == 4 || tokens.get(2).getTokenType().equals(TokenType.PREPOSITION)) {
            String equipment = tokens.get(1).getValue();
            String target = tokens.get(3).getValue();
            return new Use(equipment, target);
            
        } else {
            throw new CommandErrorException("Incomplete use command.");
        }
    }

    private Command parseGet(ArrayList<Token> tokens) throws CommandErrorException {
        if (tokens.size() < 2) {
            throw new CommandErrorException("Incomplete get command.");
        }
        String item = tokens.get(1).getValue();
        return new Get(item);
    }

    private Command parseDrop(ArrayList<Token> tokens) throws CommandErrorException {
        if (tokens.size() < 2) {
            throw new CommandErrorException("Incomplete drop command.");
        }
        String item = tokens.get(1).getValue();
        return new Drop(item);
    }

    private Command parseCombine(ArrayList<Token> tokens) throws CommandErrorException {
        if (tokens.size() < 4) {
            throw new CommandErrorException("Incomplete combine command.");
        }
        String item1 = tokens.get(1).getValue();
        String item2 = tokens.get(3).getValue();
        Combine combine = new Combine(item1, item2);
        return combine;
    }

    private Command parseLook(ArrayList<Token> tokens) throws CommandErrorException {
        if (tokens.size() < 2) {
            throw new CommandErrorException("Incomplete look command.");
        }
        String target = tokens.get(1).getValue();
        return new Look(target);
    }

    private Command parseStatus(ArrayList<Token> tokens) throws CommandErrorException {
        if (tokens.size() < 2) {
            throw new CommandErrorException("Incomplete status command.");
        }
        String topic = tokens.get(1).getValue();
        return new Status(topic);
    }

    private Command parseHelp(ArrayList<Token> tokens) {
        String topic = tokens.get(1).getValue();
        return new Help(topic);
    }

    private Command parseQuit(ArrayList<Token> tokens) {
        return new Quit();
    }

 
}
