import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;


public class Game extends MetaGame{
    private HumanPlayer human;
    private AIPlayer ai; 
    private Map map = new Map();
    private MapInfo level = new MapInfo();
    private Pieces pieceLists = new Pieces();

    private int turncounter = level.getTurns();

    private int count = 0;
    private int gamedone = 1;

    Random r = new Random();

    /*BEGIN GUI MODIFICATIONS*/

    /** New variables to accommodate the GUI.*/
    private int turnCount = 0;
    private boolean loopRun = false;

    /** The 3 following methods are basic getters and setters used to accommodate the GUI.*/
    public int getTurnCounter() {
        return new Integer(turnCount);
    }
    public void setTurnCounter() {
        this.turnCount++;
    }
    public void setLoopRun(boolean run) {
        this.loopRun = run;
    }

    /*public void placeHumanPieces() {} is gone.*/

    /** play() is different.
     * This is different in the sense that loops are being run more carefully to accommodate the GUI.*/
/*    public void play()
    {
        while (gamedone == 1 && loopRun == true) {
            if(turnCount < turncounter) {
                loopRun = false;
                System.out.println("==========AI ATTACKING===============");
                ai.getEnemyTurn2();
                System.out.println("==========AI SETUP===============");
                ai.getEnemyTurn1();
                gamedone = this.hasWon();
            }
            if(turnCount < turncounter) {
                System.out.println("==============HUMAN TURN==============");
                //human.PlayerTurnFrameWork();
                System.out.println("Human turn proceeds");
                //turnCount++;
                gamedone = this.hasWon();
            }
        }
        endgameupdate();
        super.updatePieceStates(pieceLists);
    }*/

    /*END GUI MODIFICATIONS SECTION*/

    Game()
    {

    }

    Game (Game game) {
        this.human = game.human;
        this.ai = game.ai;
        this.map = game.map;
        this.level = game.level;
        this.pieceLists = game.pieceLists;
        this.turncounter = game.turncounter;
        this.count = game.count;
        this.gamedone = game.gamedone;
        this.r = game.r;
        this.turnCount = game.turnCount;
        this.loopRun = game.loopRun;
    }
    Game(Map map, MapInfo level, Pieces pieceLists)
    {
        
       
        this.map = new Map(map);
        this.level = new MapInfo(level);
        this.pieceLists = new Pieces(pieceLists);

        human = new HumanPlayer(this.map,this.level,this.pieceLists);
        ai = new AIPlayer(this.map,this.level,this.pieceLists);
    }

    /* If all is good, updates changes to the map (obv by creating a new one, because privacy!!!) */
    
    /* This method considers whether a move is valid based on where the piece is and where the piece wants to go.
    first if-SM: considers if the piece wants to move to the same spot; invalid
    second if-SM: only allows piece to move left, right, up, down (not diagonally, if necessary will implement later), respectively
    third if-SM: doesn't allow one to move to a space if it's already occupied.
    returns true if move is valid.  */
    public Map getMap()
    {   

        return map;
    }

    public Pieces getPieces()
    {
        return pieceLists;
    }

    
    public int hasWon() 
    {
        int won = 1;
        int enemyCount = 0;
        int count = 0;
        
        turncounter -= 1;
  
        for(Entity e:pieceLists.getPlayerParty())
        {   
            if(e.getState() == 0)
            {
                count += 1;
            }

        }
        for(Entity e:pieceLists.getAIParty())
        {   
            if(e.getState() == 1)
            {
                enemyCount += 1;
            }
            
        }
        if(count == pieceLists.getPlayerParty().size())
        {
            if (turncounter == 0) 
            {
                System.out.println("Heavy Victory...");
                won = 2;
            }
            else
            {
                System.out.println("ur party is ded");
                won = 2;
            }
        }
        else if (enemyCount == 0 && turncounter == 0) 
        {
            System.out.println("You won and killed all the enemies!");
            won = 2;
        }
        
        else if (turncounter == 0) 
        {
            System.out.println("Victory!");
            won = 2;
        }
        
        
        return won;
    }

    public void placeHumanPieces(int place) 
    {
        if(map.getPiece(place) == 0 && place < map.getDimensions() * map.getDimensions() - map.getDimensions() * 3)
        //for (int i = 0; i < pieceLists.getPlayerParty().size(); i++)  
        {
            count ++;
            map.setState(place, 1, count); //Placing the entity itself in the <piece> index of the map
        }
        
        
    }

    
    //THIS IS TEMPORARY. THIS IS JUST THE EASIEST WAY TO PLACE AI PIECES; WE WILL PLACE THEM STRATEGICALLY IN THE FUTURE
    public void placeAIPieces() 
    {
        for(int count = pieceLists.getMasterList().size() - pieceLists.getAIParty().size(); count < pieceLists.getMasterList().size(); count++)
        {
            map.setState(map.getDimensions() * map.getDimensions() - count *2, 1, count);
            //indicating that on that piece of the map, there is an enemy piece
        }
        
    }

    public void play() 
    {
        Scanner s = new Scanner(System.in);
        while (gamedone == 1) {
            if(turncounter != level.getTurns())
            {
                System.out.println("=========================");
                System.out.println(turncounter);
                System.out.println("=========================");
                System.out.println("==========AI ATTACKING===============");
                ai.getEnemyTurn2();
                map.displayMap();
                System.out.println("==========AI SETUP===============");
                ai.getEnemyTurn1();
                map.displayMap();
                System.out.println("==============HUMAN TURN==============");
                map.displayMap();
                gamedone = this.hasWon();
                if(gamedone == 1)
                {
                    human.PlayerTurnFrameWork();
                    map.displayMap();
                }
            }
            else
            {
                placeAIPieces();
                while(count < pieceLists.getPlayerParty().size())
                {   
                    map.displayMap();
                    System.out.println("==============ENTER TILE ==============");
                    int test = s.nextInt();
                    placeHumanPieces(test);
                }
                turncounter --;
            }
        }
        endgameupdate();
        super.updatePieceStates(pieceLists);    
    }


    public void endgameupdate()
    {
        for(Entity e:pieceLists.getPlayerParty())
        {
            int count = -1;

            for(Entity f:pieceLists.getHumanPieces())
            {
                count ++;
                if(f.getUniqueId() == e.getUniqueId())
                {   
                    e.rejuvinate();
                    pieceLists.setHumanPieces(count, e);
                }
            }
        }

        for(Entity e:pieceLists.getAIParty())
        {
            int count = -1;

            for(Entity f:pieceLists.getAIPieces())
            {
                count ++;
                if(f.getUniqueId() == e.getUniqueId())
                {   
                    e.rejuvinate();
                    pieceLists.setAIPieces(count, e);
                }
            }
        }
    }


}



