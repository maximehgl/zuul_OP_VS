package src;
import java.util.ArrayList;

/**
 * Classe Room - un lieu du jeu d'aventure Zuul.
 *
 * @author votre nom
 */
public class Room
{
    private String aDescription = "not available";
    private String[] aExitDirections = {};
    
    public String getDescription()
    {
        return this.aDescription;
    } 
    
    public String[] getExitDirections()
    {
        return this.aExitDirections;
    }
    
    public Room(final String pDescription)
    {
        this.aDescription = pDescription;
    }
    
    public Room aNorthExit;
    public Room aSouthExit;
    public Room aEastExit;
    public Room aWestExit;
    
    
    public void setExits( final Room pRoomN, final Room pRoomS, final Room pRoomE, final Room pRoomW)
    {
    
        this.aNorthExit = pRoomN;
        this.aSouthExit = pRoomS;
        this.aEastExit = pRoomE;
        this.aWestExit = pRoomW;
        
        ArrayList<String> dirs = new ArrayList<String>();
        if (this.aNorthExit!= null) { dirs.add("North"); }
        if (this.aSouthExit!= null) { dirs.add("South"); }
        if (this.aEastExit!= null) { dirs.add("East"); }
        if (this.aWestExit!= null) { dirs.add("West"); }
        this.aExitDirections = dirs.toArray(this.aExitDirections);
    }
    
    
} // Room
