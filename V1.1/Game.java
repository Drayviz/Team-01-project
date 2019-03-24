import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Game extends MetaGame{
    private HumanPlayer human = new HumanPlayer();
    private AIPlayer ai = new AIPlayer();
    private Map map = new Map();
    private MapInfo level = new MapInfo();
    private Pieces pieceLists = new Pieces();

    private int turncounter = level.getTurns();
    private GameState gameState = new GameState();

    private int count = 0;
    private int gamedone = 1;

    Random r = new Random();

    Game(Map map, MapInfo level, Pieces pieceLists)
    {
        this.map = map;
        this.level = level;
        this.pieceLists = pieceLists;

    }

    /* If all is good, updates changes to the map (obv by creating a new one, because privacy!!!) */
    
    /* This method considers whether a move is valid based on where the piece is and where the piece wants to go.
    first if-SM: considers if the piece wants to move to the same spot; invalid
    second if-SM: only allows piece to move left, right, up, down (not diagonally, if necessary will implement later), respectively
    third if-SM: doesn't allow one to move to a space if it's already occupied.
    returns true if move is valid.  */
    public boolean isValidMove(int start, int end, Entity piece) 
    {
        boolean viable = false;
    
        if(start == end + piece.getMovement() || start == end - piece.getMovement() || start == end + map.getDimensions() || start == end - map.getDimensions())
        {
            viable = true;
        }

        return viable;

    }
    /* This method considers whether an attack is valid based on where the piece is and where the target is.
    first if-SM: considers if the target is to the left, right, above, or below the attacker(not diagonally, if necessary will implement later), respectively
    second if-SM: the two for-loops are used to obtain the <party> of the pieces. If the pieces are in the same party, the attack is invalid (no friendly-fire)
        NOTE: I have not considered an error if the spot they want to attack is unoccupied
    returns true if attack is valid.  */    
    public boolean isValidAtk(int start, int end,Entity piece) 
    {
        boolean viable = true;

        if(start == end + piece.getMovement() || start == end - piece.getMovement() || start == end + map.getDimensions() || start == end - map.getDimensions())
        {
            viable = true;
        }

        return viable;
    }

    public int hasWon() 
    {
        int won = 1;
        int friendlycount = 0;
        int enemyCount = 0;
        int count = -1;

        for (ArrayList<Integer> a:map.getMaparray()) 
        {  
            count += 1;
            if (a.get(1) != 0) 
            {
                if (pieceLists.getMasterList().get(count).getParty() == 2) 
                {
                    enemyCount = enemyCount + 1;
                }
                else if (pieceLists.getMasterList().get(count).getParty() == 1) 
                {
                    friendlycount = friendlycount + 1;
                }
            }
        
            if (enemyCount == 0) {
                won = 2;
            }
            if (friendlycount == 0 || turncounter == 0) 
            {
                won = 3;
            }
        }
        turncounter -= 1;
        return won;

    }

    public void placeHumanPieces() {
        boolean done = false;
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < pieceLists.getPlayerParty().size(); i++)  
        {
            while(done == false)
            {
                System.out.println("Where would you like to place your pieces? ");
                map.displayMap(); //so they can see the board
                int place = input.nextInt();
                if(map.getPiece(place) == 0)
                    {
                    done = true;
                    map.setState(place, 1, count); //Placing the entity itself in the <piece> index of the map
                    }
            }
        }
        input.close();
    }

    
    //THIS IS TEMPORARY. THIS IS JUST THE EASIEST WAY TO PLACE AI PIECES; WE WILL PLACE THEM STRATEGICALLY IN THE FUTURE
    public void placeAIPieces() 
    {
        for (int i = 0; i < pieceLists.getAIParty().size(); i++) 
        {
            map.setState(r.nextInt((map.getDimensions() * map.getDimensions()) - ( map.getDimensions() * map.getDimensions()) - (map.getDimensions() * 2)) + ( map.getDimensions() * map.getDimensions() - map.getDimensions() * 2), 1, count);
            //indicating that on that piece of the map, there is an enemy piece
        }
    }
    

    public void setup() 
    {
        if (level.getTurns() == turncounter) //only runs when it's the first turn
        {   
            //initializemap(filename);
            this.initializepieces();   
            gameState = new GameState(map,level,pieceLists);
        }  
    }


    public void play() {
        while (gamedone == 1) {
            map.displayMap();
            System.out.println("==========AI TURN===============");
            ai.getEnemyTurn();
            //GameState gameState = new GameState(map);
            map.displayMap();
            System.out.println("==============HUMAN TURN==============");
            //return gameState = new GameState(map);
            turncounter = turncounter - 1;
            gamedone = gameState.hasWon();
        }
            if (gamedone == 1 || gamedone == 3) {
                System.out.println("==================CONGRATULATIONS!================");
            }
            else {
                System.out.println("==============GAME OVER=========================");
            }
    }


}



/* Note: this enitre code was built on the assumption that each Array within each coordinate
in the ArrayList map assumes that the parameters are (simpleReference, typeOfPiece, ...)  */