package seedu.nuke;

import seedu.nuke.command.CommandResult;
import seedu.nuke.command.ExitCommand;
import seedu.nuke.data.ModuleLoader;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.ScreenShotManager;
import seedu.nuke.data.storage.StorageManager;
import seedu.nuke.directory.Root;
import seedu.nuke.ui.TextUi;
import seedu.nuke.ui.Ui;
import seedu.nuke.util.Message;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class Nuke {
    private Root root;
    private CommandResult commandResult;
    private ModuleManager moduleManager;
    private HashMap<String, String> modulesMap;
    private Ui ui;
    private StorageManager storageManager;

    /**
     * constructor of nuke.
     *
     * @throws FileNotFoundException if file cannot be found when loading jSon file
     */
    public Nuke() throws FileNotFoundException {
        root = new Root();
        ui = new Ui();
        modulesMap = ModuleLoader.load("moduleList.json");
        //storageManager = new StorageManager("data.json");
        storageManager = new StorageManager("save.txt");
        moduleManager = ModuleManager.getInstance(root, modulesMap);
        //ModuleManager.setModuleList(storageManager.load());
        storageManager.load2();
        ScreenShotManager.saveScreenShot();
    }

    /**
     * ScreenShot entry-point for the java.duke.Duke application.
     *
     * @param args arguments passed to the programme.
     * @throws FileNotFoundException exception is thrown if the file is not found.
     */
    public static void main(String[] args) throws FileNotFoundException {
        new Nuke().run();
    }

    /**
     * run method for Nuke class.
     */
    public void run() {
        welcomeUser();
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Method to print the welcome message to the user.
     */
    public void welcomeUser() {
        TextUi.clearScreen();
        TextUi.displayLogo();
        TextUi.showWelcomeMessage();
    }

    /**
     * Method to print the exit message to the user.
     */
    public void exit() {
        ui.showSystemMessage(Message.DIVIDER);
        storageManager.save2();
    }

    /**
     * Method to run the command from the user's input until exit command is received.
     */
    private void runCommandLoopUntilExitCommand() {
        do {
            String userInput = ui.getInput();

            commandResult = Executor.executeCommand(userInput);
            ui.showResult(commandResult);

            ScreenShotManager.saveScreenShot();
            //storageManager.save();
            storageManager.save2();
        } while (!ExitCommand.isExit());
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public CommandResult getCommandResult() {
        return commandResult;
    }
}
