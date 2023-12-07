package banking;

import java.util.ArrayList;

public class CommandStorage {
    private ArrayList<String> storage = new ArrayList<>();

     void addInvalidCommand(String command) {
        storage.add(command);
    }

    public ArrayList getInvalidCommands() {
        return storage;
    }
}
