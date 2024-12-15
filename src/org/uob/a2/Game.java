package org.uob.a2;

import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import org.uob.a2.commands.*;
import org.uob.a2.gameobjects.*;
import org.uob.a2.parser.*;
import org.uob.a2.utils.*;

/**
 * Main class for the game application. Handles game setup, input parsing, and game execution.
 * 
 * <p>
 * This class initializes the game state, reads user input, processes commands, and maintains the game loop.
 * </p>
 */
public class Game extends Object {

    private static Parser parser;
    private static Scanner scanner;
    private static Tokeniser tokeniser;
    private static GameState gameState;
    private static Timer timer;

    public Game() {
    } // empty because all intialization is alr done in setup()

    public static void main(String[] args) {
        setup(); // sets up everything / initializes the game
        start(); 
        
    }

    public static void setup(){

        // Initialize the game state, scanner, parser, and tokeniser
        
        scanner = new Scanner(System.in);
        parser = new Parser();
        tokeniser = new Tokeniser();

        

        // Load game data from file and prepare the initial state
        try {
            gameState = GameStateFileParser.parse("/jupyter/work/aiz428/data/game.txt");
        } catch (IOException e) {
            System.out.println("Error loading game data: " + e.getMessage());
            System.exit(1);
        }

        System.out.println("Welcome to the game!"); // welcome messages
        System.out.println("Type 'help' for a list of commands.");
 


    }

    public static void start(){
        startScoreDeductionTimer(); // Starts the timer for score subtraction every 5s on room9 (my additional feature)

        Command command = null; // holds current command
        
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            tokeniser.tokenise(input); 


            // parse input of user into a command
            try {
                command = parser.parse(tokeniser.getTokens());
            } catch (CommandErrorException e) {
                System.out.println("Error: " + e.getMessage());
                continue;
            }

            // check if the command user inputted is quit, in that case exits the game loop and quits the game
            if (command.getCommandType() == CommandType.QUIT) {
                break;
            } else { 

                // otherwise execute the command and print the result
                System.out.println(command.execute(gameState));
            }

            

            // if user has ancientcrown he basically won the game, it breaks off the main loop and gives winning msg + score and some player info
            if (gameState.getPlayer().hasItem("ancientcrown")) {
                System.out.println("Congratulations! You have found the ancient crown and won the game.\n" + gameState.getPlayer() + "\nScore: " + gameState.getScore());
                break;
            }
        }
        scanner.close();

        turn(command); // executes given command and shows result, its called within game loop to process each command
    }


    public static void turn(Command command){

        String result = command.execute(gameState); //execute cmd

        System.out.println(result); // show result

    }

    private static void startScoreDeductionTimer() {
        timer = new Timer(true); // make new timer that schedules a task to run every 5 seconds
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override // this is the actual  task that runs every 5 seconds
            public void run() {
                if ("r9".equals(gameState.getMap().getCurrentRoom().getId())) {
                    gameState.subtractPoints(1);
                }
            }
        }, 0, 5000); // Schedule task to run every 5 seconds specified here (5000ms is 5s)
    }


}
