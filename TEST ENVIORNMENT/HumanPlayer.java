import java.util.Scanner;


public class HumanPlayer extends Turn{
    private Map map = new Map();
    private int temp = 0;

    HumanPlayer(Map map, Pieces pieceLists)
    {
        super(map, pieceLists);
        this.map = map;
    

    }

    public int piecepromptresponses()
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
        return checker;
    }

    public int turnpromptresponses(int r)
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
                r = input.nextInt();
                input.nextLine();
                viable = isValidSelection(r);
                        
            }
            super.selectpiece(r);
        }
        return turndone;
    }

    public void PlayerTurnFrameWork() 
    {

        int turndone = 0;
        Scanner input = new Scanner(System.in);
        
        super.resetTurn();
       
        while(turndone == 0)
        {
            
            int piecedone = 0;
            map.displayMap();
            System.out.print("======================\n" + "Location: tile# with desired piece\n" + "999: End Turn\n"+ "======================\n" );
            
            temp = input.nextInt();
            input.nextLine();
     
            turndone = turnpromptresponses(temp);
            if(super.checkPieceApAndHealth() == false)
            {
                piecedone = 1;
            }

            if(piecedone == 0 && turndone == 0)
            {
                
                
                while (super.checkPieceApAndHealth() == true && piecedone == 0) 
                {   
                    System.out.print("======================\n" + "m: Move\n" + "a: Attack\n" + "h: Heal\n" + "e: End Piece Turn\n" + "======================\n" );
                    piecedone = piecepromptresponses();
                    if(super.checkPieceApAndHealth() == false)
                    {
                        piecedone = 1;
                    }
                }
            }
        }
        
    }
        
}