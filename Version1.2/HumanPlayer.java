import java.util.Scanner;
import java.util.ArrayList;


public class HumanPlayer extends Game{
    private ArrayList<Entity> humanPieces = new ArrayList<Entity>();
    private ArrayList<Entity> masterlist = new ArrayList<Entity>();
    private Map map = new Map();

    HumanPlayer()
    {   
        this.map = super.getMap();
        this.masterlist = super.getPieceLists().getMasterList();
        this.humanPieces = super.getPieceLists().getPlayerParty();

    }

    public int piecepromptresponses(Entity e, String Response)
    {
        int checker = 0;
        map.displayMap();
        boolean viable = false;
        while(viable == false)
        {
            if(Response == "M") 
            {
                System.out.print("Where would you like to move? "); 
                viable = super.isValidMove(startLocation, input.nextInt(),e); //first, checking to see if move is valid
                if (viable == true) 
                { 
                    map.moveState(startLocation, end);
                    e.ActionTakes(1); //ap is reduced          
                } 
            }
            if(Response == "A")
            {
                System.out.print("Which space is your target standing on? ");
                viable = super.isValidAtk(startLocation, input.nextInt(),e);
                if (viable == true) 
                {
                    e.setHp(masterlist.get(target));
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
        }
        return checker;
    }

    public int turnpromptresponses(int r)
    {
        boolean viable = false;
        Entity somedude = new Entity();
        while(viable == false)
        {
            if(r < humanPieces.size() && r >= 0)
            {
                if(masterlist.get(r).getState() == 0)
                {
                    System.out.println("Piece is too busy being dead to move");
                }
                else
                {
                    somedude = masterlist.get(r);
                    viable = true;
                }

            }
            if(r == 4)
            {
                turndone = 1;
                viable = true;
            }


        }
        return turndone;
    }

   
    public void PlayerTurnFrameWork() 
    {

        int piecedone = 0;
        int turndone = 0;
        int count = -1; //For aesthetic, just to keep track of which piece is being used
        Scanner input = new Scanner(System.in);
        
        for(Entity a:humanPieces)
        {
            a.resetap();
        }
         
        while(turndone == 0)
        {
            System.out.print("======================\n" + "Party Token: Piece You Want\n" + "4: End Turn\n"+ "======================\n" );

            int temp = input.nextInt();
            input.nextLine();
            turndone = turnpromptresponses(temp);

            if(turndone == 0)
            {
                int startLocation = map.getPieceLocation(temp);
                int ap = e.getAP(); //getting ap
                //count += 1;
                
                while (ap > 0 && piecedone == 0) 
                {   
                    map.displayMap();
                    System.out.print("======================\n" + "M: Move\n" + "A: Attack\n" + "H: Heal\n" + "E: End Piece Turn\n" + "======================\n" );
                    piecedone = promptresponses(e, input.nextLine());
                }
            }
        }
    }
        
}