package src;

/**
 * Write a description of class CommandWords here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CommandWords {
    private final String[] aValidCommands = { "go", "quit", "help", "look", "eat", "navigate" };
    private final String[] aValidDirections = { "North", "South", "East", "West", "Up", "Down" };

    public boolean isCommand(final String pCommand) {
        for (int i = 0; i < aValidCommands.length; i++) {
            if (aValidCommands[i].equals(pCommand)) {
                return true;
            }
        }

        return false;
    }

    public boolean isDirection(final String pDirection) {
        for (int i = 0; i < aValidDirections.length; i++) {
            if (aValidDirections[i].equals(pDirection)) {
                return true;
            }
        }

        return false;
    }

    public void showAll() {
        for (String command : aValidCommands) {
            System.out.print(command + " ");
        }
        System.out.println();
    }
}
