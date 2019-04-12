import java.util.Scanner;

import java.util.ArrayList;


public class HumanPlayer extends Game{
    private ArrayList<Entity> humanPieces = new ArrayList<Entity>();
    private ArrayList<Entity> masterlist = new ArrayList<Entity>();
    private Map map = new Map();
    private Entity e = new Entity();
    private int start = 0;
    private int temp = 0;

    HumanPlayer(){}

    HumanPlayer(Map map, MapInfo level, Pieces pieceLists)
    {   
        this.map = map;
        this.masterlist = pieceLists.getMasterList();
        this.humanPieces = pieceLists.getPlayerParty();

    }

    public int piecepromptresponses()
    {
        Scanner input = new Scanner(System.in);
        int checker = 0;
        int end = 0;
        String Response = "";
        map.displayMap();
        boolean viable = false;
        while(viable == false)
        {
            Response = input.next();
            input.nextLine();

            if(e.getAP() <= 0)
            {
                viable = true;
            }
            
            else if(Response.equals("m")) 
            {
                System.out.print("Where would you like to move? "); 
                end = input.nextInt();
                input.nextLine();

                if(map.getPiece(end) == 0)
                {
                    viable = super.isValidMove(start, end ,e); //first, checking to see if move is valid
                }
                if (viable == true) 
                { 
                    map.moveState(start, end);
                    e.ActionTakes(1); //ap is reduced          
                } 
            }
            else if(Response.equals("a"))
            {
                System.out.print("Which space is your target standing on? ");
                end = input.nextInt();
                input.nextLine();
                viable = super.isValidAtk(start, end,e);
                if (viable == true) 
                {
                    e.setHp(masterlist.get(map.getPiece(end)));
                    e.ActionTakes(2); //ap is reduced    
                }
            }
        
            else if(Response.equals("h"))
            {
                System.out.println("Piece has been healed by 1 hp");
                e.heal();
                e.ActionTakes(2);
            }

            else if(Response.equals("e"))
            {
                viable = true;
                checker = 1;
            }
            else
            {
                System.out.print("======================\n" + "m: Move\n" + "a: Attack\n" + "h: Heal\n" + "e: End Piece Turn\n" + "======================\n");
                System.out.println("invalid response");
                Response = input.next();
               
            }
        }
        return checker;
    }

    public int turnpromptresponses(int r)
    {
        Scanner input = new Scanner(System.in);
        boolean viable = false;
        int turndone = 0;
        int re = 0;
        int piece = 0;
        int pp = 0;
        int ps = 0;
       
        if(r == 999)
        {
            turndone = 1;
        }
        else
        {
            piece = map.getPiece(r);
            re = r;
            pp = masterlist.get(map.getPiece(piece) - 1).getParty();
            ps = masterlist.get(map.getPiece(piece) - 1).getState();
            while(viable == false)
            {
                if(piece != 0)
                {
                    if(piece == 999)
                    {
                        turndone = 1;
                        viable = true;
                    }
                    else if(pp == 1 && ps == 1)
                    {
                        viable = true;
                        e = masterlist.get(piece);
                        start = re;
                        temp = piece;
                    }

                    else if(ps == 0)
                    {
                        System.out.println("Piece is too busy being dead to move");
                        piece = 0;
                    }
                    else if(pp == 2)
                    {
                        System.out.println("Nice Try");
                        piece = 0;
                    }
                    else
                    {
                        re = input.nextInt();
                        input.nextLine();
                        piece = map.getPiece(re);
                        pp = masterlist.get(map.getPiece(piece) - 1).getParty();
                        ps = masterlist.get(map.getPiece(piece) - 1).getState();
                    }
                }
                else
                {
                    System.out.println("pick something with a piece on it");
                    re = input.nextInt();
                    piece = map.getPiece(re);
                    pp = masterlist.get(map.getPiece(piece) - 1).getParty();
                    ps = masterlist.get(map.getPiece(piece) - 1).getState();
                    

                }
            }
        }
    
        return turndone;
    }

   
    public void PlayerTurnFrameWork() 
    {

        int turndone = 0;
        Scanner input = new Scanner(System.in);
        
        for(Entity a:humanPieces)
        {
            a.resetap();
        }
         
        while(turndone == 0)
        {
            
            int piecedone = 0;
            System.out.print("======================\n" + "Location: tile# with desired piece\n" + "999: End Turn\n"+ "======================\n" );
            
            temp = input.nextInt();
            input.nextLine();
     
            turndone = turnpromptresponses(temp);

            if(piecedone == 0 && turndone == 0)
            {
                //count += 1;
                int ap = e.getAP();
                if(ap <= 0)
                {
                    System.out.println("=====PIECE IS OUT OF AP======");
                    piecedone = 1;
                }
                while (ap > 0 && piecedone == 0) 
                {   
                    map.displayMap();
                    System.out.print("======================\n" + "m: Move\n" + "a: Attack\n" + "h: Heal\n" + "e: End Piece Turn\n" + "======================\n" );
                    piecedone = piecepromptresponses();
                    ap = e.getAP();
                }
            }
        }
        
    }
        
}