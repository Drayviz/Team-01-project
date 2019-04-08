import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.control.Label; //PART OF GUI MODIFICATIONS


public class Game extends MetaGame{
    
	/**
	 * Instance variables
	 */
    private AIPlayer ai; 
    private Map map = new Map();
    private PieceLibrary pieceLists = new PieceLibrary();

    private int turncounter = map.getTurns();
    private int GUIturnCounter = map.getTurns() - 1;
    //private Label toPlayer;

    private int count = 0;
    private int gamedone = 1;

    Random r = new Random();
   
    /**
     * Constructor for the class Game
     * Game class is main class that essentially initializes and runs the game
     * @param map passing in class map
     * @param pieceLists passing in class pieceLists
     */

    Game(Map map, PieceLibrary pieceLists)
    {
        this.map = new Map(map);
        this.pieceLists = new PieceLibrary(pieceLists);
        ai = new AIPlayer(this.map, this.pieceLists);
    }

    /* BEGIN GUI MODIFICATIONS*/

    /** New variables to accommodate the GUI.*/
    private int turnCount = 0;

    /** The 3 following methods are basic getters and setters used to accommodate the GUI.*/
    public int getGUIturnCounter() {
        return new Integer(this.GUIturnCounter);
    }
    public void setGUIturnCounter() {
        this.GUIturnCounter--;
    }
    public void setturncounter() {
        this.turncounter--;
    }
/*    public int getGUIturnCounter() {
        return this.GUIturnCounter;
    }*/
    public int getGameDone() {
        return new Integer(this.gamedone);
    }
    public int getTotalTurns() {
        return new Integer(this.turncounter);
    }
    public void setGameDone(int gameDone) {
        this.gamedone = new Integer(gameDone);
    }



    /*public void placeHumanPieces() {} is gone.*/

    /** play() is different.
     * This is different in the sense that loops are being run more carefully to accommodate the GUI.*/
   public void play() {
        HumanTurnGUI human = new HumanTurnGUI(this.map,this.pieceLists);
        if (gamedone == 1) {
                ai.getEnemyTurn2();
                ai.getEnemyTurn1();
                gamedone = hasWon();
                System.out.println("Turn from G: " + this.GUIturnCounter);
                System.out.println("GameDone from G: " + this.gamedone);
        }
        else {
            endgameupdate();
            super.updatePieceStates(pieceLists);
        }
    }

    public void oneLessTurn()
    {
        turncounter -= 1;
        GUIturnCounter--;
    }
    
    /**
     * Method checks state of game and all conditions where player can win the game
     * If conditions are met won variable is changed
     * @return distinct integer that specifies type of win or loss
     */
    public int hasWon() {
        int won = 1;
        int enemyCount = 0;
        int count = 0;

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
        if(count == pieceLists.getPlayerParty().size()) {
            if ((turncounter == 0 || GUIturnCounter == 0)) { //no human pieces, turns left = 0; isn't this a loss?
                System.out.println("Heavy victory...");
                won = 2;
            }
            else {
                System.out.println("ur party is ded");
                won = 3;
            }
        }
        else if (enemyCount == 0 && (turncounter == 0 || GUIturnCounter == 0)) {
            System.out.println("You won and killed all the enemies just in time!");
            won = 4;
        }
        else if ((turncounter == 0 || GUIturnCounter == 0)) { //when you time out, isn't it a loss?
            System.out.println("You have defended yourself from the enemies!");
            won = 5;
        }
        else if (enemyCount == 0) {
            System.out.println("You won and killed all the enemies!");
            won = 6;
        }


        return won;
    }

    /*END GUI MODIFICATIONS SECTION*/

    /* If all is good, updates changes to the map (obv by creating a new one, because privacy!!!) */
    
    /* This method considers whether a move is valid based on where the piece is and where the piece wants to go.
    first if-SM: considers if the piece wants to move to the same spot; invalid
    second if-SM: only allows piece to move left, right, up, down (not diagonally, if necessary will implement later), respectively
    third if-SM: doesn't allow one to move to a space if it's already occupied.
    returns true if move is valid.  */
    public Map getMap() {
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
     * Method in which places human pieces
     * @param place is location desired to play piece
     */
    public void placeHumanPieces(int place) 
    {
        if(map.getPiece(place) == 0 && place < map.getDimensions() * map.getDimensions() - map.getDimensions() * 3)
        {
            count ++;
            map.setState(place, 1, count); //Placing the entity itself in the <piece> index of the map
        } 
    }
    
    public void placeAllHumanPieces(int place) {
        for (int i = 0; i < pieceLists.getPlayerParty().size(); i++) {
            placeHumanPieces(place);
        }
    }

    //THIS IS TEMPORARY. THIS IS JUST THE EASIEST WAY TO PLACE AI PIECES; WE WILL PLACE THEM STRATEGICALLY IN THE FUTURE

    public void placeAIPiece(int place,int thing) 
    {
        map.setState(place,1, thing + 1);
    }
    public void placeAIPieces() 
    {
        int th = 0;
        for(int h = 0; h < pieceLists.getAIParty().size(); h++)
        {
            th = h + pieceLists.getAIParty().size();
            placeAIPiece(map.getDimensions() * map.getDimensions() - map.getDimensions() / 3 - h,th);
        }
    }
    
    /**
     * Method displays text based game to player
     */
    public void playText() 
    {
        HumanPlayer human2 = new HumanPlayer(this.map, this.pieceLists);
        Scanner s = new Scanner(System.in);
        while (gamedone == 1) {
            if(turncounter != map.getTurns())
            {
                System.out.println("==========AI ATTACKING===============");
                ai.getEnemyTurn2();
                map.displayMap();
                System.out.println("==========AI SETUP===============");
                ai.getEnemyTurn1();
                System.out.println("==============HUMAN TURN==============");
              
                System.out.println("=============");
                System.out.println("Turn Counter:");
                System.out.println(turncounter);
                System.out.println("=============");
                gamedone = this.hasWon();
                if(gamedone == 1)
                {
                    human2.PlayerTurnFrameWork();
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
                    placeAllHumanPieces(test);
                }
                
            }
            oneLessTurn();
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

