package seedu.nuke.command.listcommand;

import seedu.nuke.command.CommandResult;
import seedu.nuke.directory.DirectoryLevel;
import seedu.nuke.data.ModuleManager;

import java.util.ArrayList;

import static seedu.nuke.util.Message.MESSAGE_NO_TASK_IN_LIST;
import static seedu.nuke.util.Message.MESSAGE_TASK_SUCCESSFULLY_LIST;

/**
 * sort all tasks of all modules according to deadline of task and print it out to the user.
 */
public class ListAllTasksDeadlineCommand extends ListCommand {

    private ArrayList<String> deadlines;

    public static final String COMMAND_WORD = "lsdt";
    public static final String MESSAGE_USAGE = COMMAND_WORD;
    public static final int EMPTY = 0;


    @Override
    public CommandResult execute() {
        //get the large task list
        if (ModuleManager.countAllTasks() == EMPTY) {
            return new CommandResult(MESSAGE_NO_TASK_IN_LIST);
        }
        assert ModuleManager.countAllTasks() != EMPTY : "make sure there are some tasks in the list";
        deadlines = ModuleManager.checkDeadline();
        return new CommandResult(MESSAGE_TASK_SUCCESSFULLY_LIST(ModuleManager.countAllTasks()) + deadlines);
    }
}
