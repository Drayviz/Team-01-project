import java.util.Scanner;

import org.w3c.dom.NameList;

import java.util.Random;

public class MetaGame 
{
    private Map map = new Map();
    private MapInfo mapinfo = new MapInfo(map.getMapInfo());
    private Pieces pieceLists = new Pieces();
    private Random r = new Random();
    private String directory = "";
    private String level1 = "";
    private String level2 = "";
    private String level3 = "";
    private String level4 = "";
    private String level5 = "";

    MetaGame()
    {

    }
    MetaGame(Map map, MapInfo level, Pieces pieceLists)
    {
        this.map = map;
        this.mapinfo = level;
        this.pieceLists = pieceLists;
    }
 
    public void mapEditor()
    {   
        Scanner userinput = new Scanner(System.in);
        int doneindicator = 0;
        String fileinput;
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

                while(done2 == 0)
                {
                    map.displayMap();
                    System.out.println("\n =============================");
                    System.out.println("1: Edit Terrain");
                    System.out.println("2: Edit Turns");
                    System.out.println("3: Edit Number of Enemies");
                    System.out.println("999: Save and Go back to main menu");
                    //System.out.println("2: Edit Settings");
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
                        mapinfo.setTurns(userinput.nextInt());
                        done2 = 1;
                    }
                    else if(inputt == 3)
                    {   
                        System.out.print("How Many Enemies?");
                        mapinfo.setNumenemies(userinput.nextInt()); 
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

            else if(inputt == 2)
            {
                System.out.println("please indicate file name and world");
                fileinput = userinput.nextLine();

                map.loadMap(fileinput);
            }

            else if(inputt == 3)
            {
                doneindicator = 1;
            }
        }
    }

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

    public void selectworld(String world)
    {
        directory = System.getProperty("user.dir") + "\\" + world + "\\" ;
        level1 = directory + "one.txt";
        level2 = directory + "two.txt";
        level3 = directory + "three.txt";
        level4 = directory + "four.txt";
        level5 = directory + "five.txt";


    }
    public void selectlevel(String levelnumber)
    {
        map.loadMap(directory + levelnumber);

    }

    public void editparty()
    {
         
    }
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
    
    public void initializeEnemy()
    {
        Random a = new Random();
        int counters = -1;
        for(int i = 0;i < mapinfo.getNumofEnemies(); i++)
        {
            counters ++;
            pieceLists.addAIPieces((a.nextInt(4) + 1)* 10);
            pieceLists.addtoEntityParty(counters);
        }
        
    }

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
    

    public void startgame(String world)
    {
        pickstartingparty(0);
        initializeEnemy();
        pieceLists.compileMasterList();
        selectworld("one");
        selectlevel(world);
        displayPlayerPartyPieces();
        Game level = new Game(map, mapinfo, pieceLists);
        level.play();
    }

    public void updatePieceStates(Pieces r)
    {
        pieceLists = new Pieces(r);
    }

    public void endgamestuff()
    {

    }


public static void main(String[] args) 
{
    MetaGame game = new MetaGame();
    game.startgame("five");
}

}