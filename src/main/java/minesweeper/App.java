package minesweeper;

import minesweeper.command.Command;
import minesweeper.command.CommandFactory;
import minesweeper.exception.command.CommandException;
import minesweeper.exception.rule.RuleException;
import minesweeper.game.GameEngine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class App {

    private final Logger log = LogManager.getLogger(this.getClass());

    private Scanner scanner;
    private CommandFactory factory;
    private GameEngine engine;

    public App() {
        scanner = new Scanner(System.in);
        factory = new CommandFactory();
        engine = new GameEngine();
    }

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        log.info("Minesweeper game has started.");

        while (true) {
            log.info("Select a command:");
            String input = scanner.nextLine();
            try {
                Command command = factory.create(input);
                command.execute(engine);
                engine.display();
            } catch (CommandException | NumberFormatException e) {
                log.warn("Command failed. Reason: {}", e.getMessage(), e);
            } catch (RuleException e) {
                log.warn("Rule violation: {}", e.getMessage(), e);
            }
        }
    }
}