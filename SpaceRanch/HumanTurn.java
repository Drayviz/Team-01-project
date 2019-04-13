/*DONE*/

import java.util.InputMismatchException;
import java.util.Scanner;

/*package text;*/

/**
 * HUMAN PLAYER
 * 
 * Text based version of human interface, contains all of the prompts but
 * depends on the logic methods from turn.
 */

public class HumanTurn extends Turn{
	
	/**
	 * Instance variables
	 */
    private MapClass map = new MapClass();
    private int temp = 0;
    
    /**
     * Constructor for the class HumanTurn
     * Method interacts with metagame to obtain information from the player. 
     * Prompts the human pieces to interact with the map and its surroundings 
     * @param map takes 
     * @param pieceLists
     */
    HumanTurn(MapClass map, PieceLibrary pieceLists)
    {
        super(new MapClass(map), new PieceLibrary(pieceLists));
    }
    /**
     * Method allows the user to move, attack or heal a selected piece.
     * If no actions are used an exit option is provided for selected piece 
     * @return if actions to move, attack, and heal are valid they will execute 
     */
    public int piecePromptResponses()
    {
        Scanner input = new Scanner(System.in);
        int checker = 0;
        int end = 0;
        String Response = "";
        
        boolean viable = true;
        while(viable == true)
        {
            map.displayMap();
            System.out.print("======================\n" + "m: Mooooove\n" + "a: Attack\n" + "h: Heal\n" + "e: End Piece Turn\n" + "======================\n");
            Response = input.next();

            viable = super.checkPieceApAndHealth();
            
                
                if(Response.equals("m")) 
                {
                    System.out.print("Where would you like to move? ");
                    end = input.nextInt();
                    super.movePiece(end);
                    viable = super.checkPieceApAndHealth();
                   
                }
                else if(Response.equals("a"))
                {
                    System.out.print("Which space is your target standing on? ");
                    end = input.nextInt();
                    super.attackPiece(end);
                    viable = super.checkPieceApAndHealth();
                   
                }
            
                else if(Response.equals("h"))
                {
                    super.healPiece();
                }

                else if(Response.equals("e"))
                {
                    viable = false;
                    checker = 1;
                }

                if(viable == false)
                {
                    checker = 1;
                }
            
            
        }
        return new Integer(checker);
    }
    
    /**
     * Method prompts a check to see if selected piece is valid to be selected 
     * @param r passes an argument to prompt control or exit  of selected piece 
     * @return piece selection or exit 
     */
    public int turnPromptResponses(int r)
    {
        Scanner input = new Scanner(System.in);
        boolean viable = false;
        int turndone = 0;
        if(r == 999)
        {
            turndone = 1;
        }
        else
        {
            viable = isValidSelection(r);
            while(viable == false)
            { 
                try
                {
                    r = input.nextInt();
                    input.nextLine();
                    viable = isValidSelection(r);
                }
                catch(Exception e)
                {
                    System.out.println("invalid selection");
                }            
            }
            super.selectPiece(r);
        }
        return new Integer(turndone);
    }
    /**
     * Retrieves enemy turn from super to avoid having to make a 40 line class
     */
    public void enemyTurn()
    {
        super.aiTurn();
    }
    
    /**
     * Method Interactions during the players turn, takes player input (location and turn action) and takes action accordingly. 
     * References piecepromptresponse, which further breaksdown the details of the players action
     */
    public void playerTurnFrameWork() 
    {

        int turndone = 0;
        Scanner input = new Scanner(System.in);
        
        super.resetTurn();
       
        while(turndone == 0)
        {
            
            int piecedone = 0;
            map.displayMap();
            System.out.print("======================\n" + "Location: tile# with desired piece\n" + "999: End Turn\n"+ "======================\n" );
            
            try
            {
                temp = input.nextInt();
                input.nextLine();
        
                turndone = turnPromptResponses(temp);
                if(super.checkPieceApAndHealth() == false)
                {
                    piecedone = 1;
                }

                if(piecedone == 0 && turndone == 0)
                { 
                    while (super.checkPieceApAndHealth() == true && piecedone == 0) 
                    {   
                        System.out.print("======================\n" + "m: Move\n" + "a: Attack\n" + "h: Heal\n" + "e: End Piece Turn\n" + "======================\n" );
                        piecedone = piecePromptResponses();
                        if(super.checkPieceApAndHealth() == false)
                        {
                            piecedone = 1;
                        }
                    }
                }
                   
            }
            catch(InputMismatchException e)
            {
                System.out.println("Please pick a valid Tile");
                input.next();
            }
        }   
    }     
}