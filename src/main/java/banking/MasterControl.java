package banking;

import java.util.List;

public class MasterControl {
    private ParentCommandValidator commandValidator;
    private ParentCommandProcessor commandProcessor;
    private CommandStorage commandStorage;

    public MasterControl(ParentCommandValidator parentCommandValidator, ParentCommandProcessor parentCommandProcessor, CommandStorage commandStorage) {
        this.commandValidator = parentCommandValidator;
        this.commandProcessor = parentCommandProcessor;
        this.commandStorage = commandStorage;

    }

    public List<String> start(List<String> input) {
        for (String command : input) {
            if(commandValidator.validate(command)) {
                commandProcessor.processCommand(command);
            } else {
                commandStorage.addInvalidCommand(command);
            }
        }
        return commandStorage.getInvalidCommands();

    }
}
