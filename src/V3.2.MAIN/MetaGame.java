import java.util.Scanner;

import org.w3c.dom.NameList;

import java.util.Random;

public class MetaGame {
	
	/**
	 * Instance variables
	 */
    private Map map = new Map();
    private PieceLibrary pieceLists = new PieceLibrary();
    private Random r = new Random();
    private String directory = "";
    
    private String level1 = "";
    private String level2 = "";
    private String level3 = "";
    private String level4 = "";
    private String level5 = "";
    private String level6 = "";
    private String level7 = "";
    private String level8 = "";
    private String level9 = "";
    
    /**
     * Empty constructor for the claas MetaGame
     */
    MetaGame()
    {

    }
    
    /**
     * Constructor for the class MetaGame
     * MetaGame is the main class that initalizes the core foundation of the game and runs it 
     * Customization of maps, player party size, and enemies
     * Loads world, level, map, terrain, player pieces, and enemies
     * 
     * @param map passing in class map 
     * @param pieceLists passing in pieceLists as arugment 
     */
    MetaGame(Map map, PieceLibrary pieceLists)
    {
        this.map = map;
        this.pieceLists = pieceLists;
    }
    
    /**
     * Method displays main menu of the game onto the terminal 
     * User is prompt with options to create map, load map, and exit from main menu 
     * User is promt with options to create map
     * Setup includes: map size, world level, world name, terrains on map and number of turns, enemies, and party size.
     */
    public void mapEditor()
    {   
        Scanner userinput = new Scanner(System.in);
        int doneindicator = 0;
        String fileinput;
        String fileinput2;
        int inputt;
        int inputt2;
        int inputt3;
        int done2 = 0;
        while (doneindicator == 0)
        {   
            done2 = 0;
            System.out.println("===============");
            System.out.println("1: Create New Map");
            System.out.println("2: Load Existing Map");
            System.out.println("3: Exit");
            System.out.println("=================");

            inputt = userinput.nextInt();
            userinput.nextLine();
            if(inputt == 1)
            {
                System.out.println("========please indicate dimensions and type=========");
                inputt = userinput.nextInt();
                inputt2 = userinput.nextInt();
                System.out.println("==========================");
              

                map = new Map(inputt, inputt2);
            }

            else if(inputt == 2)
            {
                System.out.println("please indicate world and level name (same line)");
                fileinput = userinput.nextLine();
                String[] Value = fileinput.split(" ");
                fileinput = Value[0];
                fileinput2 = Value[1];

                selectworld(fileinput);
                System.out.println(fileinput2);
                selectlevel(fileinput2);
            }

            else if(inputt == 3)
            {
                doneindicator = 1;
                done2 = 1;
            }

            while(done2 == 0)
            {
                map.displayMap();
                System.out.println("\n =============================");
                System.out.println("1: Edit Terrain");
                System.out.println("2: Edit Turns");
                System.out.println("3: Edit Number of Enemies");
                System.out.println("999: Save and Go back to main menu");
                System.out.println("==========================");
                inputt = userinput.nextInt();
                userinput.nextLine();
                

                if(inputt == 1)
                {   
                    map.displayMap();
                    System.out.println("\n ==Set Terrain: Location, Slot, What you want (put space between numbers without comma)==\n");
                    inputt = userinput.nextInt();
                    inputt2 = userinput.nextInt();
                    inputt3 = userinput.nextInt();
                    map.setState(inputt,inputt2,inputt3);
                    System.out.println("==========================");
            
                }
                else if(inputt == 2)
                {   
                    System.out.print("How Many Turns?");
                    map.setTurns(userinput.nextInt());
                    done2 = 1;
                }
                else if(inputt == 3)
                {   
                    System.out.print("How Many Enemies?");
                    map.setNumenemies(userinput.nextInt()); 
                    done2 = 1;
                }
                else if(inputt == 999)
                {   
                    System.out.println("What would you like to name it?");
                    String filename = userinput.nextLine();
                    System.out.println("What world?");
                    String world = userinput.nextLine();
                    map.saveMap(filename,world);
                    done2 = 1;
                }
                
            }
        }      
    }
    
    /**
     * Method will check to see if inputted party size is valid 
     * If party size choosen is valid it will be added into a list
     * @param party passing the input of party size 
     */
    public void pickstartingparty(int party)
    {
        int counter = 0;
        for(int prefab = 3 * party + 1; prefab < (3 * party + 4); prefab++)
        {
            pieceLists.addHumanPieces(prefab);
            //System.out.println(prefab);
            //System.out.println(pieceLists.getHumanPieces());
            pieceLists.addtoPlayerParty(counter);
            counter ++;
        }
    }
    
    /**
     * Method takes in the users selected world and prompts the game to open file corresponding to the choosen world 
     * @param passes the inputted world as an argument 
     */
    public void selectworld(String world)
    {   
        directory = System.getProperty("user.dir") + System.getProperty("file.separator") + world + System.getProperty("file.separator");

        level1 = directory + "one.txt";
        level2 = directory + "two.txt";
        level3 = directory + "three.txt";
        level4 = directory + "four.txt";
        level5 = directory + "five.txt";
        level6 = directory + "six.txt";
        level7 = directory + "seven.txt";
        level8 = directory + "eight.txt";
        level9 = directory + "nine.txt";
    }
    
    /**
     * Method loads levels between 1-9 and their corresponding map features 
     * @param lvl passes the inputed level as an argument 
     */
    public void selectlevel(String lvl)
    {
        if(lvl.equals( "one"))
        {
            map.loadPath(level1);
        }
        if(lvl.equals("two"))
        {
            map.loadPath(level2);
        }
        if(lvl.equals("three"))
        {
            map.loadPath(level3);
        }
        if(lvl.equals( "four"))
        {
            map.loadPath(level4);
        }
        if(lvl.equals( "five"))
        {
            map.loadPath(level5);
        }
        if(lvl.equals( "six"))
        {
            map.loadPath(level6);
        }
        if(lvl.equals( "seven"))
        {
            map.loadPath(level7);
        }
        if(lvl.equals( "eight"))
        {
            map.loadPath(level8);
        }
        if(lvl.equals( "nine"))
        {
            map.loadPath(level9);
        }
    }
    
    /**
     * 
     */
    public void editparty()
    {
         
    }
    
    /**
     * Method displays name and the stats of attack, defense, and movement of the users pieces
     */
    public void displayPlayerPieces()
    {
        int counter = 1;
        for(Entity e:pieceLists.getHumanPieces())
        {
            System.out.println("========================");
            System.out.println("========ALL PIECES======");
            System.out.println("========================");
            System.out.println(counter);
            System.out.println("Name: " + e.getName());
            System.out.println("Attack: "+ e.getAtk());
            System.out.println("Defence: "+ e.getDef());
            System.out.println("Movement: " + e.getMovement());
            System.out.println("========================");
            counter ++;
        }
    }
    
    /**
     * Method displays name and the stats of attack, defense, and movement of the users 3 party pieces
     */
    public void displayPlayerPartyPieces()
    {
        int counter = 1;
        for(Entity e:pieceLists.getPlayerParty())
        {
            System.out.println("========================");
            System.out.println("========PARTY===========");
            System.out.println("========================");
            System.out.println(counter);
            System.out.println("Name: " + e.getName());
            System.out.println("Attack: "+ e.getAtk());
            System.out.println("Defence: "+ e.getDef());
            System.out.println("Movement: " + e.getMovement());
            System.out.println("========================");
            counter ++;
        }
    }
    
    /**
     * Method generates the enemies stats and adds onto map 
     */
    public void initializeEnemy()
    {
        Random a = new Random();
        int counters = -1;
        for(int i = 0;i < map.getNumofEnemies(); i++)
        {
            counters ++;
            pieceLists.addAIPieces((a.nextInt(4) + 1)* 10);
            pieceLists.addtoEntityParty(counters);
        }
        
    }
    
    /**
     * Method checks the conditions of the game to determine if the game has been lost 
     * Conditions include: health of pieces and pieces that remain on map 
     * @return If game has been lost 
     */
    public boolean megalose()
    {
        int counter = 0;
        boolean test = false;
        for(Entity e:pieceLists.getHumanPieces())
        {
            if(e.getState() == 0)
            {
                counter ++;
            }
        }
        if(counter == pieceLists.getHumanPieces().size())
        {
            test = true;
        }
        return test;
    }

    // public void shop()
    // {

    // }

    // public void displayPlayerWeapons()
    // {

    // }
    
    /**
     * Method startgame initalizes the users party, enemies, world, level, and the map 
     * @param world passes in selected world as an argument 
     * @param lvl passes in selected level as an argument
     */
    public void startgame(String world,String lvl)
    {
        pickstartingparty(0);
        initializeEnemy();
        pieceLists.compileMasterList();
        selectworld(world);
        selectlevel(lvl);
        displayPlayerPartyPieces();
        Game level = new Game(map,pieceLists);
        level.playText();
    }
    
    /**
     * Method startGUIGame initalizes the users party, enemies, world, level, and the map to a visual interface 
     * @param world passes in selected world as an argument 
     * @param lvl passes in selected level as an argument
     */
    public Game startGUIGame(String world,String lvl)
    {
        pickstartingparty(0);
        initializeEnemy();
        pieceLists.compileMasterList();
        selectworld(world);
        selectlevel(lvl);
        displayPlayerPartyPieces();
        Game level = new Game(map,pieceLists);
        level.play();
        return level;
    }
    
    /**
     * Method updates the stats of the pieces 
     * @param passes the selected piece from a list as an argument 
     */
    public void updatePieceStates(PieceLibrary r)
    {
        pieceLists = new PieceLibrary(r);
    }

    public void endgamestuff() {}
    
    /**
     * Method is the command centre of all the functions and initalizes the entire game 
     * @param args contains the command-line arguments as an array of String objects.
     */
    public static void main(String[] args) {
        Scanner b = new Scanner(System.in);
        MetaGame a = new MetaGame();
        a.mapEditor();
        System.out.println("========enter world and level to play===========");
        String fileinput = b.nextLine();
        String[] Value = fileinput.split(" ");
        fileinput = Value[0];
        String fileinput2 = Value[1];
        a.startGUIGame(fileinput,fileinput2);
    }
}