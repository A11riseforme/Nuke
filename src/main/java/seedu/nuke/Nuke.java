package seedu.nuke;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.command.ExitCommand;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.parser.Parser;
import seedu.nuke.ui.TextUi;
import seedu.nuke.ui.Ui;


import static org.fusesource.jansi.Ansi.ansi;
import static seedu.nuke.ui.TextUi.printDivider;
import static seedu.nuke.util.ExceptionMessage.*;

public class Nuke {
    private CommandResult commandResult;
    public ModuleManager moduleManager;
    private Ui ui;

    public Nuke() {
        moduleManager = new ModuleManager();
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        TextUi.clearScreen();
        TextUi.displayLogo();
        new Nuke().run();
    }

    private void run() {
        this.ui = new Ui();

        TextUi.showWelcomeMessage();
        runCommandLoopUntilExitCommand();
    }

    private void runCommandLoopUntilExitCommand() {
        do {
            String userCommandText = ui.getInput();
            try {
                Command command = new Parser().parseInput(userCommandText);
                CommandResult result = executeCommand(command);
                ui.showResult(result);
            } catch (Parser.InputLengthExceededException e) {
                System.out.println(MESSAGE_INPUT_LENGTH_EXCEEDED);
            } catch (Parser.EmptyInputException e) {
                System.out.println(MESSAGE_EMPTY_INPUT);
            } catch (Parser.InvalidCommandException e) {
                System.out.println(MESSAGE_INVALID_COMMAND);
            }
        } while (!ExitCommand.isExit());
    }

    /**
     * initialize the taskManager system, execute command and save the list to the file
     * @param command the parsed Command object
     * @return commandResult that contains the execute output information
     */
    private CommandResult executeCommand(Command command) {
        try {
            // supplies the data the command will operate on.
            // if there is no file to load or the file is empty, setData will initialize a new taskManager system
            //command.setData(moduleManager);
            // Execute according to the command itself
            commandResult = command.execute();
            // save the taskManager to a file
            //moduleManager.getStorager().save(taskManager);
            //StorageFile.saveJson(taskManager);
        } catch (Exception ex) {
            // the out layer exception handler
            System.out.println(ex);
        }
        return commandResult;
    }
}