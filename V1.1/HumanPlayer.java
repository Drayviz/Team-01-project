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

    public void piecepromptresponses(Entity e, String Response)
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
                    e.a = ap - 1; //ap is reduced
                    super.update(); //when all is g, the move is made and a map is returned, which is updated as the new map            
                } 
            }
            if(Response == "A")
            {
                System.out.print("Which space is your target standing on? ");
                viable = super.isValidAtk(startLocation, input.nextInt(),e);
                if (viable == true) 
                {
                    e.setHp(masterlist.get(target));
                    ap = ap - 2;
                    super.update();
                }
            }
        
            if(Response == "H")
            {
                System.out.println("Piece has been healed by 1 hp");
                e.heal();
            }

            if(Response == "E")
            {
                viable = true;
            }
        }
    }

    public void turnpromptresponses(int Response)
    {
        if(Response <)
    }

    public void PlayerTurnFrameWork() 
    {
        int count = -1; //For aesthetic, just to keep track of which piece is being used
        Scanner input = new Scanner(System.in);
        for (Entity e:humanPieces) 
        {
            System.out.print("======================\n" + "Party Token: Piece You Want\n" + "4: End Turn\n"+ "======================\n" );


            int startLocation = map.getPieceLocation(count);
            int ap = e.getAP(); //getting ap
            count += 1;
            while (ap > 0) 
            {   
                map.displayMap();
                System.out.print("======================\n" + "M: Move\n" + "A: Attack\n" + "H: Heal\n" + "E: End Piece Turn\n" + "======================\n" );
                promptresponses(e, input.nextLine());
            }
        }
    }
}