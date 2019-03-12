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
   
    Game()
    {

    }
    Game(Map map, MapInfo level, Pieces pieceLists)
    {
        super(map,level,pieceLists);
        human = new HumanPlayer();
        ai = new AIPlayer();
       
        this.map = new Map(map);
        this.level = new MapInfo(level);
        this.pieceLists = new Pieces(pieceLists);
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
        else if (enemyCount == 0 || turncounter == 0) 
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

    public void placeHumanPieces() 
    {
        boolean done = false;
        Scanner input = new Scanner(System.in);
        int place = 0;
        for (int i = 0; i < pieceLists.getPlayerParty().size(); i++)  
        {
            count ++;
            
            //System.out.println(i);
            done = false;
           
            while(done == false && i < pieceLists.getPlayerParty().size())
            {
                System.out.println("Where would you like to place " + pieceLists.getPlayerParty().get(i).getName() + "?");
                map.displayMap(); //so they can see the board
                
            
                place = input.nextInt();
                input.nextLine();
                
                
            
                if(map.getPiece(place) == 0 && place < map.getDimensions() * map.getDimensions() - map.getDimensions() * 3)
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
        for(int count = pieceLists.getMasterList().size() - pieceLists.getAIParty().size(); count < pieceLists.getMasterList().size(); count++)
        {
            map.setState(map.getDimensions() * map.getDimensions() - count *2, 1, count);
            //indicating that on that piece of the map, there is an enemy piece
        }
        
    }

    public void play() 
    {
        while (gamedone == 1) {
            if(turncounter != level.getTurns())
            {
                System.out.println("==========AI ATTACKING===============");
                ai.getEnemyTurn2();
                map.displayMap();
                System.out.println("==========AI SETUP===============");
                ai.getEnemyTurn1();
                map.displayMap();
                System.out.println("==============HUMAN TURN==============");
                map.displayMap();
                gamedone = this.hasWon();
                human.PlayerTurnFrameWork();
                map.displayMap();

            }
            else
            {
                placeAIPieces();
                placeHumanPieces();
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



