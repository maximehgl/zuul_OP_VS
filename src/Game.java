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
    private World vWorld;
    private int mapSize = 20;

    public Game()
    {
        this.vWorld = new World(this.createWorld());
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

    public int getmapSize()
    {
        return mapSize;
    }

    private Room[][] createWorld()
    {
        Room vPort1 = new Room("at the port of the first Island");
        Room vPort2 = new Room("at the port of the second Island");
        Room vPort3 = new Room("at the port of the third Island");
        Room vPort4 = new Room("at the port of the fourth Island");
        Room vPort5 = new Room("at the port of the fifth Island");

        Room vHouse = new Room("in your house");
        Room vShipyard = new Room("at the sipyard");
        Room vCemetery = new Room("in the cemetery");

       

        vPort1.setExits( "North", vHouse);
        vPort1.setExits( "South", vShipyard);
        vShipyard.setExits( "North", vPort1);
        vHouse.setExits( "South", vPort1);


        vPort2.setExits( "East", vCemetery);
        vCemetery.setExits( "West", vPort2);

        
    

        this.aCurrentRoom = vHouse;

        
        Room[][] tabWorld = new Room[mapSize][mapSize];
        
        for (int i= 0; i<mapSize; i++)
        {
            for (int j= 0; j<mapSize; j++)
            {
                
                tabWorld[i][j] =  new Room("in the ocean");

            } 
        }

        tabWorld[0][0] = vPort1;
        tabWorld[mapSize-1][mapSize-1] = vPort2;

        return tabWorld;
    }
 
    private void navigate(final Command pDirection)
    {

        if (this.aCurrentRoom.equals(vWorld.getZone(this.aCurrentRoom)))
        {
            if (pDirection.hasSecondWord()==false){
                System.out.println("navigate where ?");
                return;
            }
            
            Room vNextIsland = null;
            String vDirection = pDirection.getSecondWord();
    
            if (!commandWords.isDirection(vDirection))
            {
                System.out.println("Unknown direction !");
                return;
            }
            else
            {
                vNextIsland = vWorld.getZoneExit(aCurrentRoom,vDirection);
            }
            
            if (vNextIsland == null)
            {
                System.out.println("erreur: la command navigate n'aurait pas du etre possible");
                return;
            }
            
            this.aCurrentRoom = vNextIsland;
            this.printCurrentRoomLocationInfo();
        }else
        {
            System.out.println("You need to go to the port to navigate");
        }

        
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
        System.out.print("you are ");
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
        }else if(pCommand.getCommandWord().equals("navigate")){
            navigate(pCommand);
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
