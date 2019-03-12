import java.util.Scanner;

import java.util.ArrayList;


public class HumanPlayer extends Game{
    private ArrayList<Entity> humanPieces = new ArrayList<Entity>();
    private ArrayList<Entity> masterlist = new ArrayList<Entity>();
    private Map map = new Map();
    private Entity e = new Entity();
    private int temp = 0;

    HumanPlayer()
    {   
        this.map = super.getMap();
        this.masterlist = super.getPieces().getMasterList();
        this.humanPieces = super.getPieces().getPlayerParty();

    }

    public int piecepromptresponses(Entity e,int startLocation)
    {
        Scanner input = new Scanner(System.in);
        int checker = 0;
        map.displayMap();
        boolean viable = false;
        while(viable == false)
        {
            String Response = input.nextLine();
            
            if(Response == "M") 
            {
                System.out.print("Where would you like to move? "); 
                int end = input.nextInt();
                input.nextLine();
                viable = super.isValidMove(startLocation, end ,e); //first, checking to see if move is valid
                if (viable == true) 
                { 
                    map.moveState(startLocation, end);
                    e.ActionTakes(1); //ap is reduced          
                } 
            }
            if(Response == "A")
            {
                System.out.print("Which space is your target standing on? ");
                int end = input.nextInt();
                input.nextLine();
                viable = super.isValidAtk(startLocation, end,e);
                if (viable == true) 
                {
                    e.setHp(masterlist.get(map.getPiece(end)));
                    e.ActionTakes(2); //ap is reduced    
                }
            }
        
            if(Response == "H")
            {
                System.out.println("Piece has been healed by 1 hp");
                e.heal();
                e.ActionTakes(2);
            }

            if(Response == "E")
            {
                viable = true;
                checker = 1;
            }
            else
            {
                System.out.println("invalid response");
                Response = input.nextLine();
            }
        }
        input.close();
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
            int piece = map.getPiece(r);
            while(viable == false)
            {
                if(piece != 0)
                {
                    if(piece == 999)
                    {
                        turndone = 1;
                        viable = true;
                    }
                    else if(masterlist.get(map.getPiece(piece)).getState() == 1 && masterlist.get(map.getPiece(piece)).getParty() == 1)
                    {
                        viable = true;
                        e = masterlist.get(map.getPiece(piece));
                        temp = piece;
                    }

                    else if(masterlist.get(map.getPiece(piece)).getState() == 0)
                    {
                        System.out.println("Piece is too busy being dead to move");
                    }
                    else if(masterlist.get(map.getPiece(piece)).getParty() == 2)
                    {
                        System.out.println("Nice Try");
                    }
                    else
                    {
                        System.out.println("invalid response");
                        piece = input.nextInt();
                        input.nextLine();
                    }
                }
                else
                {
                    System.out.println("pick something with a piece on it");
                    piece = map.getPiece(input.nextInt());
                    input.nextLine();
                }
            }
        }
        input.close();
        return turndone;
    }

   
    public void PlayerTurnFrameWork() 
    {

        int piecedone = 0;
        int turndone = 0;
        //Scanner input = new Scanner(System.in);
        
        for(Entity a:humanPieces)
        {
            a.resetap();
        }
         
        while(turndone == 0)
        {
            System.out.print("======================\n" + "Location: tile# with desired piece\n" + "999: End Turn\n"+ "======================\n" );

          
            turndone = turnpromptresponses(temp);

            if(turndone == 0)
            {
                int startLocation = map.getPieceLocation(temp);
                //count += 1;
                int ap = e.getAP();
                while (ap > 0 && piecedone == 0) 
                {   
                    map.displayMap();
                    System.out.print("======================\n" + "M: Move\n" + "A: Attack\n" + "H: Heal\n" + "E: End Piece Turn\n" + "======================\n" );
                    piecedone = piecepromptresponses(e, startLocation);
                    ap = e.getAP();
                }
            }
        }
        //input.close();
    }
        
}