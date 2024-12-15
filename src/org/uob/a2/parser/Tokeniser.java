package org.uob.a2.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code Tokeniser} class is responsible for converting a string input into a list of tokens
 * that can be parsed into commands by the game.
 * 
 * <p>
 * The tokeniser identifies keywords, variables, and special symbols, assigning each a {@code TokenType}.
 * </p>
 */
public class Tokeniser extends Object{

    private Map<String, TokenType> keywords;
    private ArrayList<Token> tokens;

    public Tokeniser() {
        keywords = new HashMap<>();
        keywords.put("move", TokenType.MOVE);
        keywords.put("use", TokenType.USE);
        keywords.put("get", TokenType.GET);
        keywords.put("drop", TokenType.DROP);
        keywords.put("look", TokenType.LOOK);
        keywords.put("status", TokenType.STATUS);
        keywords.put("help", TokenType.HELP);
        keywords.put("quit", TokenType.QUIT);
        keywords.put("on", TokenType.PREPOSITION);
        keywords.put("with", TokenType.PREPOSITION);
        keywords.put("using", TokenType.PREPOSITION);
        keywords.put("and", TokenType.PREPOSITION);
        keywords.put("combine", TokenType.COMBINE);
        tokens = new ArrayList<>();
    } 
    
    public ArrayList<Token> getTokens() {
        return tokens;
    }

    public String sanitise(String s) {
        return s.trim().toLowerCase();
    }

    public void tokenise(String s) {
        tokens.clear();
        String sanitisedInput = sanitise(s);
        String[] words = sanitisedInput.split("\\s+"); // splits by spaces

        for (String word : words) {
            TokenType type = keywords.getOrDefault(word, TokenType.VAR);
            tokens.add(new Token(type, word));
        }
        tokens.add(new Token(TokenType.EOL, ""));
    }
}