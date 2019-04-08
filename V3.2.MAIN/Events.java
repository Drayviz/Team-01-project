import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import java.util.ArrayList;

public class Events implements EventHandler<ActionEvent> {
    //private BoardPanel board = new BoardPanel("one", "one");
    private MapClass map = new MapClass();
    private Game game = new Game();
    private PieceLibrary pieceLists = new PieceLibrary();
    private int piecesPlaced = 0; //?
    private int place = 0;
    private Label toPlayer = new Label();
    private String vary = "";
    private HumanTurnGUI human;
    private boolean midMove;
    private boolean midAtk;
    private String display;

    /*For viewParty and viewEnemies*/
    public Events(String vary, Label toPlayer, String display) {
        this.vary = vary;
        this.toPlayer = toPlayer;
        this.display = display;
    }

    /*For move and attackOrder*/
    public Events(int place, String vary, MapClass map, Game game, PieceLibrary pieceLists, HumanTurnGUI human, Label toPlayer, boolean mid) {
        this.place = place;
        this.vary = vary;
        this.map = map;
        this.game = game;
        this.pieceLists = pieceLists;
        this.human = human;
        this.toPlayer = toPlayer;
        if (this.vary.equals("move")) {
            this.midMove = mid;
        }
        else if (this.vary.equals("attack")) {
            this.midAtk = mid;
        }
    }

    /*For heal and endPiece*/
    public Events(String vary, MapClass map, Game game, PieceLibrary pieceLists, HumanTurnGUI human, Label toPlayer) {
        this.vary = vary;
        this.map = map;
        this.game = game;
        this.pieceLists = pieceLists;
        this.human = human;
        this.toPlayer = toPlayer;
    }

    /** I use the variable VARY to distinguish which kind of implementation I get for each different button click.*/
    public void handle(ActionEvent event) {
        /** "party" and "enemy" change the toPlayer text based on the parameter inputted.*/
        if (this.vary.equals("party")) {
            toPlayer.setText(this.display);
        }
        if (this.vary.equals("enemy")) {
            toPlayer.setText(this.display);
        }
        /** This and attack need to be pressed twice to move/attack. Essentially, changes a variable in BoardPanel to signifiy what each press does.*/
        if (this.vary.equals("move")) {
            BoardPanel board = new BoardPanel();
            if (board.getMidMove() == false) {
                toPlayer.setText("Click where to move then click the MOVE button again.");
                board.setMidMove(true);
                System.out.println("Before choice, " + board.getMidMove()); //

            }
            else {
                if (human.movePiece(place)) {
                    board.setMidMove(false);
                    toPlayer.setText("Successful move.");
                }
                else {
                    board.setMidMove(false);
                    toPlayer.setText("Invalid selection.");
                }
            }
        }
        if (this.vary.equals("attack")) {
            BoardPanel board = new BoardPanel();
            if (board.getMidAtk() == false) {
                toPlayer.setText("Click who to attack then click the ATTACK button again.");
                board.setMidAtk(true);
                System.out.println("Before choice, " + board.getMidAtk()); //
                System.out.println("Place before, " + place); //
            }
            else {
                System.out.println("Place after, " + place); //
                if (human.attackPiece(place)) {
                    board.setMidAtk(false);
                    toPlayer.setText("Successful attack.");
                    System.out.println("New HP " + pieceLists.getMasterList().get((map.getPiece(place) - 1)).getHp()); //
                    System.out.println("New HP 2 " + pieceLists.getAIPieces().get(1).getHp() + "e4 " + pieceLists.getAIPieces().get(0).getHp() + "e6 " + pieceLists.getAIPieces().get(2).getHp()); //
                }
                else {
                    board.setMidAtk(false);
                    toPlayer.setText("Invalid selection.");
                }
            }
        }
        /** Heals the piece.*/
        if (this.vary.equals("heal")) {
            if (human.healPiece()) {
                toPlayer.setText("Piece healed by 1 HP.");
            }
            else {
                toPlayer.setText("Health is already full.");
            }
        }
    }
}

    /*For boardButton, dead.*/
/*    public Events(int place, String vary, Map map, Game game, PieceLibrary pieceLists) {
        this.place = place;
        this.vary = vary;
        this.map = map;
        this.game = game;
        this.pieceLists = pieceLists;
    }*/

/** Dead. */
/*        if (this.vary.equals("board")) {
                Button boardButtonClk = (Button) event.getSource();
                map.setState(place, 1, board.getPiecesPlaced()+1); //This sets the piece itself
                board.setSubmessage("PIECE " + (board.getPiecesPlaced()+1));
                board.setMessage(place + ", " + board.getSubmessage());
                board.setPiecesPlaced(board.getPiecesPlaced()+1);
                boardButtonClk.setText(board.getMessage());
                }*/

/*            else {
                if (game.getTurnCounter() == 0 && piecesPlaced < pieceLists.getPlayerParty().size()) { //This ensures that pieces can only be placed before the game begins, and there can't be more pieces placed than permitted.
                    if (map.getPiece(place) == 0 && place < (map.getDimensions() * map.getDimensions() - map.getDimensions() * 3)) { //This ensures that pieces can only be placed on empty spaces and not in the last 3 rows
                        map.setState(place, 1, piecesPlaced); //This sets the piece itself
                        submessage = "PIECE " + (piecesPlaced+1);
                        message = place + ", " + submessage;
                        piecesPlaced++;
                        System.out.println(piecesPlaced);
                        check = true;
                    }
                }
            }*/
/*        System.out.println("Turn counter: " + game.getTurnCounter());
        System.out.println("Pieces placed: " + board.getPiecesPlaced());
        System.out.println("Player party size: " + pieceLists.getPlayerParty().size());
        System.out.println("Submessage: " + board.getSubmessage());
        System.out.println("Message: " + board.getMessage());
        System.out.println("Pieces placed again: " + board.getPiecesPlaced());*/
