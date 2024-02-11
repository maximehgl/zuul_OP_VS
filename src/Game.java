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
       

        Room vHouse1 = new Room("in your house");
        Room vShipyard1 = new Room("at the shipyard");
        Room vMarket1 = new Room("at the market");

        Room vCemetery2 = new Room("in a cemetery");
        Room vBar2 = new Room("in a bar");
        Room vShop2 = new Room("in a shop");

        Room vMarket3 = new Room("in a shop");
        Room vField3 = new Room("in a shop");
        
        Room vVillage4 = new Room("in a shop");
        Room vTree4 = new Room("in a shop");

       

        
        vPort1.setExits( "South", vShipyard1);
        vShipyard1.setExits( "North", vPort1);
        vShipyard1.setExits( "west", vMarket1);
        vMarket1.setExits( "East", vShipyard1);
        vMarket1.setExits( "South", vHouse1);
        vHouse1.setExits( "North", vMarket1);

        
        vPort2.setExits( "East", vCemetery2);
        vCemetery2.setExits( "West", vPort2);
        vCemetery2.setExits( "East", vShop2);
        vShop2.setExits( "West", vCemetery2);
        vShop2.setExits( "up", vBar2);
        vBar2.setExits( "Down", vShop2);

        vPort3.setExits( "North", vMarket3);
        vMarket3.setExits( "South", vPort3);
        vMarket3.setExits( "west", vField3);
        vField3.setExits( "East", vMarket3);

        vPort4.setExits( "North", vVillage4);
        vVillage4.setExits( "South", vPort4);
        vVillage4.setExits( "North", vTree4);
        vTree4.setExits( "South", vVillage4);

    

        this.aCurrentRoom = vHouse1;

        
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
        tabWorld[mapSize-4][mapSize-10] = vPort3;
        tabWorld[mapSize-2][mapSize-14] = vPort4;

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
