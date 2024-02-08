package src;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe Room - un lieu du jeu d'aventure Zuul.
 *
 * @author votre nom
 */
public class Room
{
    private String aDescription = "not available";
    private HashMap<String, Room> exits;
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
        exits = new HashMap<String, Room>();
    }

    public Room getExit(final String pDirection)
    {
        return exits.get(pDirection);
    }
    

    
    
    public void setExits( final String pDirection, final Room pNeighbor)
    {
    
        exits.put(pDirection, pNeighbor);

        
        ArrayList<String> directions = new ArrayList<String>();
        for (String direction: exits.keySet())
        {
            directions.add(direction);
        }
        this.aExitDirections = directions.toArray(this.aExitDirections);
    }
    
    
} // Room
