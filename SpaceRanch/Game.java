import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.InputMismatchException;

/**
 * GAME
 * 
 * Metagame Takes the party of ai, building, and player, along with the map and starts a new game session
 * Controls the individual state of battles, and since the war can be lost
 * based on the outcome of a battle (all pieces are dead or powergrid is 0)
 * it communicates with metagame to decide if the entire game is lost.
 * contains the gameplay loops
 * 
 */

public class Game extends MetaGame{
    
	/**
	 * Instance variables
	 */
    private MapClass map = new MapClass();
    private PieceLibrary pieceLists = new PieceLibrary();
    private int turncounter = map.getTurns();
    
    private int count = 0;
    private int gamedone = 1;
    private Random r = new Random();

    /**
     * Default Constructor for the class Game
    */
    Game() {}
   
    /**
     * Constructor for the class Game
     * Game class is main class that essentially initializes and runs the game
     * Creates a copy of the map and piecelists
     * @param map passing in class map
     * @param pieceLists passing in class pieceLists
     */

    Game(MapClass map, PieceLibrary pieceLists)
    {
        this.map = new MapClass(map);
        turncounter = map.getTurns();
        this.pieceLists = new PieceLibrary(pieceLists);
    }

    /**
     * Reduces turn counter by one
     */
    public void oneLessTurn()
    {
        turncounter--;
    }
    
    /**
     * Method checks state of game and all conditions where player can win the game
     * If conditions are met won variable is changed
     * @return distinct integer that specifies type of win or loss
     */
    public int hasWon() 
    {
        int won = 1;
        int enemyCount = 0;
        int count = 0;
        int sum = 0;

        for(Entity e:pieceLists.getPlayerParty()) { //COUNTS DEAD HUMAN PIECES
            if(e.getState() == 0) {
                count += 1;
            }
        }
        for(Entity e:pieceLists.getAIParty()) { //COUNTS LIVE ENEMY PIECES
            if(e.getState() == 1) {
                enemyCount += 1;
            }
        }
        for(Entity t:pieceLists.getBuildingList()) { //TOTALS DAMAGE TO BUILDINGS, FOR TOTAL POWERGRID DAMAGE
            sum += t.getMaxhp() - t.getHp();
        }
        if(super.getPowerGrid() - sum <= 0)
        {
            System.out.println("Powergrid has failed");
            won = 3;
        }
        else if(count == pieceLists.getPlayerParty().size()) 
        {
            if ((turncounter == 0)) { //no human pieces, turns left = 0,win;
                System.out.println("Heavy victory...");
                won = 2;
            }
            else {
                System.out.println("ur party is ded");//party dies, loss
                won = 3;
            }
        }
        else if (enemyCount == 0 && (turncounter == 0)) {
            System.out.println("You won and killed all the enemies just in time!");//all enemies dead, win
            won = 4;
        }
        else if ((turncounter == 0)) { //when you time out, win
            System.out.println("You have defended yourself from the enemies!");
            won = 5;
        }
        else if (enemyCount == 0) {
            System.out.println("You won and killed all the enemies!");//all enemies dead, win
            won = 6;
        }
        return won;
        
    }
    /**
     * Getter for map
     * @return powergrid value
     */
    public int getPowerGrid()
    {
        return super.getPowerGrid();
    }

    /**
     * Getter for map
     * @return map of the current state of the game.
     */
    public MapClass getMap() {
        return map;
    }
    
    /**
     * Getter for pieces
     * @return array list containing pieces
     */
    public PieceLibrary getPieces()
    {
        return pieceLists;
    }

    /**
     * Getter for GUI turn counter
     * @return GUIturnCounter, number of turns remaining for the GUI.
     */
    public int getTurnCounter() {
        return new Integer(this.turncounter);
    }

    /**
     * Getter for gameDone
     * @return gameDone, whether the game is finished or not.
     */
    public int getGameDone() {
        return new Integer(this.gamedone);
    }

    /**
     * Setter for gameDone, changes if the game is done or not based on hasWon().
     */
    public void setGameDone(int gameDone) {
        this.gamedone = new Integer(gameDone);
    }

    /**
     * Method in which places human pieces
     * @param place is location desired to place piece
     */
    public void placeHumanPieces(int place) 
    {
        if(map.getPiece(place) == 0 && place < map.getDimensions() * map.getDimensions() - (map.getDimensions() * map.getDimensions()/ 2)
        && map.getTerrain(place) != 6 && map.getTerrain(place) != 8 && map.getTerrain(place) != 20 
        && map.getTerrain(place) != 30 && map.getTerrain(place) != 40 && map.getTerrain(place) != 50 && map.getTerrain(place) != 60)
        {
            count ++;
            map.setState(place, 1, count); //Placing the entity itself in the <piece> index of the map
        } 
    }
    
    /**
     * Method that invokes placeHumanPieces and places all of them onto the board
     * @param place is location desired to place piece
     */
    public void placeAllHumanPieces(int place) {
        for (int i = 0; i < pieceLists.getPlayerParty().size(); i++) {
            placeHumanPieces(place);
        }
    }

    /**
     * @param place
     * @param thing
     * places ai piece
     */
    public void placeAIPiece(int place,int thing) 
    {
        map.setState(place,1, thing);
    }

    /** places all ai pieces
    */
    public void placeAIPieces() 
    {
        int th = 0;
        int h = 0;
        int test = map.getDimensions() * map.getDimensions() - 1;
        while(h < pieceLists.getAIParty().size())
        {
            th = h + pieceLists.getPlayerParty().size() + 1;
            if(map.getPiece(test) == 0 && map.getTerrain(definiteTarget - map.getDimensions()) != 6 
            && map.getTerrain(definiteTarget - map.getDimensions()) != 8 
            && map.getTerrain(definiteTarget - map.getDimensions()) != 20 
            && map.getTerrain(definiteTarget - map.getDimensions()) != 30 
            && map.getTerrain(definiteTarget - map.getDimensions()) != 40 
            && map.getTerrain(definiteTarget - map.getDimensions()) != 50 
            && map.getTerrain(definiteTarget - map.getDimensions()) != 60)
            
            {
                placeAIPiece(test,th);
                h += 1;
            }
            else
            {
                test --;
            }
            
        }
    }
    /** Game loop used for the GUI; doesn't use Scanner nor while-loops.*/
   public void play() {
       HumanTurnGUI human = new HumanTurnGUI(this.map,this.pieceLists);
       //Turn turn = new Turn(this.map, this.pieceLists);
       if (gamedone == 1) {
            //turn.aiTurn();
           human.enemyTurn();
           gamedone = hasWon();
       }
       else {
           endGameUpdate();
           super.updatePieceStates(pieceLists);
       }
}
    /**
     * Method displays text based game to player
     */
    public void playText() 
    {
        HumanTurn turns = new HumanTurn(this.map, this.pieceLists);
        Turn turn = new Turn(this.map, this.pieceLists);
        Scanner s = new Scanner(System.in);
        
        while (gamedone == 1) {
            if(turncounter != map.getTurns())
            {
                System.out.println("==========AI TURN===============");
                turns.enemyTurn();
                map.displayMap();
                System.out.println("==============HUMAN TURN==============");
              
                System.out.println("=============");
                System.out.println("Turn Counter:");
                System.out.println(turncounter);
                System.out.println("=============");
                gamedone = this.hasWon();
                if(gamedone == 1)
                {
                    turns.playerTurnFrameWork();
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
                    try
                    {
                        int test = s.nextInt();
                        placeAllHumanPieces(test);
                    }
                    catch(InputMismatchException e)
                    {
                        System.out.println("invalid tile request");
                        s.next();
                    }
                }
                
            }
            oneLessTurn(); 
            
        }
        endGameUpdate();
        super.updatePieceStates(pieceLists);    
    } 

    /**
     * Method that restores all stats of existing entities
     * transfers data to metagame
     */
    public void endGameUpdate()
    {
        //updates states of player pieces
        int sum = 0;
        for(Entity e:pieceLists.getPlayerParty())
        {
            int count = -1;
            e.checkState();

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
        //updates states of ai pieces
        for(Entity e:pieceLists.getAIParty())
        {
            int count = -1;
            e.checkState();

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
        //updates state of powergrid going into the next level
        for(Entity t:pieceLists.getBuildingList()) { //TOTALS DAMAGE TO BUILDINGS, FOR TOTAL POWERGRID DAMAGE
            if(t.getMaxhp() == t.getHp() && t.getParty() == 4)
            {
                super.addMoney(1);
            }
            else
            {
                super.damagePowerGrid(t.getMaxhp() - t.getHp());
            }
        }
        super.updatePieceStates(pieceLists);
        
    }


}

