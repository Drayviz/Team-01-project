import java.util.Scanner;

public class MetaGame
{
    private Map map = new Map();
    private MapInfo mapInfo = map.getMapInfo();
    private Pieces pieceLists = new Pieces();


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
                        mapInfo.setTurns(userinput.nextInt());
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
                        System.out.print("What would you like to name it?");
                        String filename = userinput.nextLine();
                        map.saveMap(filename);
                        done2 = 1;
                    }
                    
                    

                }
            }

            else if(inputt == 2)
            {
                System.out.println("please indicate file name");
                fileinput = userinput.nextLine();

                map.loadMap(fileinput);
            }

            else if(inputt == 3)
            {
                doneindicator = 1;
            }
                

        }
        userinput.close();
    }









    public Map getMap()
    {   
        return new Map(map);
    }






    











public static void main(String[] args) 
{
    Game game = new Game();
    game.mapEditor();
    game.setup();
    game.play();
}

}