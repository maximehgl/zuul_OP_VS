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
    private CommandWords commandWords;
    
    public Game()
    {
        this.createRooms();
        this.aParser = new Parser();
        this.commandWords = new CommandWords();
    } 

    private void printWelcome()
    {
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println(" ");
        this.printCurrentRoomLocationInfo();
    }
 
    private void createRooms()
    {
        Room vOutside = new Room("outside the main entrance of the university");
        Room vTheatre = new Room("in a lecture theatre");
        Room vPub = new Room("in the campus pub");
        Room vLab = new Room("in a computing lab");
        Room vOffice = new Room("in the computing admin office");

        vPub.setExits( "East", vOutside);

        vOutside.setExits( "West", vPub);
        vOutside.setExits( "East", vTheatre);
        vOutside.setExits( "South", vLab);

        vTheatre.setExits( "West", vOutside);

        vLab.setExits( "North", vOutside);
        vLab.setExits( "Down", vOffice);

        vOffice.setExits( "Up", vLab);

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

        if (!commandWords.isDirection(vDirection))
        {
            System.out.println("Unknown direction !");
            return;
        }
        else
        {
            vNextRoom = aCurrentRoom.getExit(vDirection);
        }
        
        if (vNextRoom == null)
        {
            System.out.println("There is no door !");
            return;
        }
        
        this.aCurrentRoom = vNextRoom;
        this.printCurrentRoomLocationInfo();
    }

    private static void printLocationInfo(Room pRoom)
    {
        System.out.println(pRoom.getDescription());
        System.out.print("Exits: ");
        System.out.println(pRoom.getExitString());
    }
    
    private void printCurrentRoomLocationInfo()
    {
        System.out.print("You are ");
        printLocationInfo(this.aCurrentRoom);
    }

    private void look(final Command pLookCommand)
    {
        if (pLookCommand.hasSecondWord())
        {
            System.out.println("I don't know how to look at something in particular yet.");
        }
        else
        {
            this.printCurrentRoomLocationInfo();
        }
        
    }

    private void eat()
    {
        System.out.println("You have eaten now and you are not hungry any more.");
    }
    
    private void printHelp()
    {
        System.out.println("You are lost. You are alone.");
        System.out.println("You wander around at the university.");
        System.out.println(" ");
        System.out.println("Your command words are:");
        aParser.showCommands();
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
        }else if(pCommand.getCommandWord().equals("eat")){
            eat();
            return false;
        }else if(pCommand.getCommandWord().equals("look")){
            look(pCommand);
            return false;
        }else if(pCommand.getCommandWord().equals("quit")){
            return quit(pCommand);
        }else if(pCommand.getCommandWord().equals("help")){
            this.printHelp();
            return false;
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
