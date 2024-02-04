package src;
/**
 * Classe Game - le moteur du jeu d'aventure Zuul.
 *
 * @author votre nom
 */
public class Game
{
    private Room aCurrentRoom;
    private Parser aParser;
    
    public Game()
    {
        this.createRooms();
        this.aParser = new Parser();
    } 

    private void printWelcome()
    {
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println(" ");
        this.printCurrentRoomDescription();
    }
 
    private void createRooms()
    {
        Room vOutside = new Room("outside the main entrance of the university");
        Room vTheatre = new Room("in a lecture theatre");
        Room vPub = new Room("in the campus pub");
        Room vLab = new Room("in a computing lab");
        Room vOffice = new Room("in the computing admin office");

        vPub.setExits( null, null, vOutside, null);
        vOutside.setExits( null, vLab, vTheatre, vPub);
        vTheatre.setExits( null, null, null, vOutside);
        vLab.setExits( vOutside, null, vOffice, null);
        vOffice.setExits( null, null, null, vLab);

        this.aCurrentRoom = vOutside;
    }
     
    private void goRoom(final Command pDirection)
    {
        if (pDirection.hasSecondWord()==false){
            System.out.println("Go where ?");
            return;
        }
        
        Room vNextRoom = null;
        String vDirection = pDirection.getSecondWord();
        
        switch(vDirection) {
            case "North":
                vNextRoom = this.aCurrentRoom.aNorthExit;
                break;
            case "South":
                vNextRoom = this.aCurrentRoom.aSouthExit;
                break;
            case "East":
                vNextRoom = this.aCurrentRoom.aEastExit;
                break;
            case "West":
                vNextRoom = this.aCurrentRoom.aWestExit;
                break;            
            default:
                System.out.println("Unknown direction !");
                return;
        }
        
        
        if (vNextRoom == null)
        {
            System.out.println("There is no door !");
            return;
        }
        
        this.aCurrentRoom = vNextRoom;
        this.printCurrentRoomDescription();
    }

    private static void printRoomDescription(Room pRoom)
    {
        System.out.print("You are ");
        System.out.println(pRoom.getDescription());
        System.out.print("Exits: ");
        System.out.println(String.join(" ", pRoom.getExitDirections()));
    }
    
    private void printCurrentRoomDescription()
    {
        printRoomDescription(this.aCurrentRoom);
    }
    
    private void printHelp()
    {
        System.out.println("You are lost. You are alone.");
        System.out.println("You wander around at the university.");
        System.out.println(" ");
        System.out.println("Your command words are:");
        System.out.println("  go quit help");
    }
    
    private boolean quit(final Command pDirection)
    {
        if(pDirection.hasSecondWord())
        {
            System.out.println("Quit what ?");
            return false;
        }
        
        return true;
    }
    
    private boolean processCommand( final Command pCommand)
    {
        if(pCommand.isUnknown()){
            System.out.println("I don't know what you mean...");
            return false;
        }else if(pCommand.getCommandWord().equals("go")){
            goRoom(pCommand);
            return false;
        }else if(pCommand.getCommandWord().equals("quit")){
            return quit(pCommand);
        }else{
            System.out.println("Erreur du programmeur : commande non reconnue !");
            return false;
        }
    }
    
    public void play()
    {
       this.printWelcome();
       
       boolean vFinished = false;
       while(!vFinished)
       {
           Command vCommand = this.aParser.getCommand();
           vFinished = this.processCommand(vCommand);
       }
       System.out.println("Thank you for playing.  Good bye.");
    }
}

 // Game
