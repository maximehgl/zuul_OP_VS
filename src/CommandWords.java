package src;
 


/**
 * Write a description of class CommandWords here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CommandWords
{
    private final String[] aValidCommands = {"go","quit","help"};
    
    public boolean isCommand(final String pCommand)
    {
        for(int i=0;i<aValidCommands.length;i++)
        {
            if(aValidCommands[i].equals(pCommand))
            {
                return true;
            }
        }
        
        return false;
    }
}
