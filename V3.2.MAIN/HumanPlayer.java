import java.util.Scanner;
import javafx.scene.control.Label; //PART OF GUI MODIFICATIONS

public class HumanPlayer extends Turn{
    private Map map = new Map();
    private int temp = 0;

    HumanPlayer(Map map,MapInfo level,Pieces pieceLists)
    {
        super(map,level,pieceLists);  
        this.map = map;
    

    }

    /*BEGIN GUI MODIFICATIONS*/
    private int turnDone = 0;
    private Label toPlayer = new Label();

    HumanPlayer(Map map, MapInfo level, Pieces pieceLists, Label toPlayer) {
        super(map,level,pieceLists);
        this.map = map;
        this.toPlayer = toPlayer;
    }

    public int piecepromptresponses()
    {
        //Scanner input = new Scanner(System.in);
        int checker = 0;
        int end = 0;
        //String Response = "";
        
        boolean viable = true;
        if (viable == true) {
            map.displayMap();
            //System.out.print("======================\n" + "m: Mooooove\n" + "a: Attack\n" + "h: Heal\n" + "e: End Piece Turn\n" + "======================\n");
            //Response = input.next();

            viable = super.checkPieceApAndHealth();
            
/*            if(Response.equals("m")) {
                System.out.print("Where would you like to move? ");
                end = input.nextInt();
                super.movepiece(end);
                viable = super.checkPieceApAndHealth();
            }
            else if(Response.equals("a")) {
                System.out.print("Which space is your target standing on? ");
                end = input.nextInt();
                super.attackpiece(end);
                viable = super.checkPieceApAndHealth();

            }
            else if(Response.equals("h")) {
                super.healpiece();
            }

            else if(Response.equals("e")) {
                viable = false;
                checker = 1;
            }
            if(viable == false) {
                checker = 1;
            }
            else
            {
                System.out.print("======================\n" + "m: Move\n" + "a: Attack\n" + "h: Heal\n" + "e: End Piece Turn\n" + "======================\n");
                System.out.println("invalid response");
                Response = input.next();

            }*/
        }
        return checker;
    }

    public boolean turnpromptresponses(int r)
    {
        //Scanner input = new Scanner(System.in);
        boolean viable = false;
        //turnDone = 0;
/*        if(r == 999)
        {
            turnDone = 1;
        }*/
        //else {
            System.out.println("pick something with a piece on it");
            viable = isValidSelection(r);
/*            if (viable == false) {
                r = input.nextInt();
                input.nextLine();
                viable = isValidSelection(r);
            }*/
            super.selectpiece(r);
        //}
        return viable;
    }

    public void PlayerTurnFrameWork(int place)
    {

        //turnDone = 0;
        //Scanner input = new Scanner(System.in);
        
        super.resetTurn();
       
        //if (turnDone == 0) {
            int piecedone = 0;
            map.displayMap();
            //System.out.print("======================\n" + "Location: tile# with desired piece\n" + "999: End Turn\n"+ "======================\n" );

            //temp = input.nextInt();
            //input.nextLine();
            //turnDone = turnpromptresponses(place); //CHANGE

            if(piecedone == 0) //&& turnDone == 0)
            {
                if(super.checkPieceApAndHealth() == false) {
                    piecedone = 1;
                }
                if (super.checkPieceApAndHealth() == true && piecedone == 0) {
                    map.displayMap();
                    System.out.print("======================\n" + "m: Move\n" + "a: Attack\n" + "h: Heal\n" + "e: End Piece Turn\n" + "======================\n" );
                    piecedone = piecepromptresponses();
                }
            }
        //}
    }
        
}