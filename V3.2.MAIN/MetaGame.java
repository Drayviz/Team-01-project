import java.util.Scanner;

import org.w3c.dom.NameList;

import java.util.Random;

public class MetaGame 
{
    private Map map = new Map();
    private Pieces pieceLists = new Pieces();
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

    MetaGame()
    {

    }
    MetaGame(Map map,Pieces pieceLists)
    {
        this.map = map;
        this.pieceLists = pieceLists;
    }
 
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
        for(int i = 0;i < map.getNumofEnemies(); i++)
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
    

    public Game startgame(String world,String lvl)
    {
        pickstartingparty(0);
        initializeEnemy();
        pieceLists.compileMasterList();
        selectworld(world);
        selectlevel(lvl);
        displayPlayerPartyPieces();
        Game level = new Game(map,pieceLists);
        level.playText();
        return level;
    }

    public void updatePieceStates(Pieces r)
    {
        pieceLists = new Pieces(r);
    }

    public void endgamestuff() {}

    public static void main(String[] args) {
        Scanner b = new Scanner(System.in);
        MetaGame a = new MetaGame();
        a.mapEditor();
        System.out.println("========enter world and level to play===========");
        String fileinput = b.nextLine();
        String[] Value = fileinput.split(" ");
        fileinput = Value[0];
        String fileinput2 = Value[1];
        a.startgame(fileinput,fileinput2);
    }
}