import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import java.util.ArrayList;

public class Events implements EventHandler<ActionEvent> {
    private BoardPanel board = new BoardPanel();
    private Map map = new Map();
    private Game game = new Game();
    private Pieces pieceLists = new Pieces();
    private int piecesPlaced = 0;
    private int place = 0;
    private int check = 0;
    private Label toPlayer = new Label();
    private String vary = "";
    private HumanPlayerGUI human;
    private boolean midMove;
    private boolean midAtk;
    private String display;

    /*For boardButton*/
    public Events(int place, String vary, Map map, Game game, Pieces pieceLists) {
        this.place = place;
        this.vary = vary;
        this.map = map;
        this.game = game;
        this.pieceLists = pieceLists;
    }

    /*For viewParty and viewEnemies*/
    public Events(String vary, Label toPlayer, String display) {
        this.vary = vary;
        this.toPlayer = toPlayer;
        this.display = display;
    }

    /*For move and attackOrder*/
    public Events(int place, String vary, Map map, Game game, Pieces pieceLists, HumanPlayerGUI human, Label toPlayer, boolean mid) {
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
    public Events(String vary, Map map, Game game, Pieces pieceLists, HumanPlayerGUI human, Label toPlayer) {
        this.vary = vary;
        this.map = map;
        this.game = game;
        this.pieceLists = pieceLists;
        this.human = human;
        this.toPlayer = toPlayer;
    }

    public void handle(ActionEvent event) {
        //dead if-SM
        if (this.vary.equals("board")) {
            Button boardButtonClk = (Button) event.getSource();
            map.setState(place, 1, board.getPiecesPlaced()+1); //This sets the piece itself
            board.setSubmessage("PIECE " + (board.getPiecesPlaced()+1));
            board.setMessage(place + ", " + board.getSubmessage());
            board.setPiecesPlaced(board.getPiecesPlaced()+1);
            boardButtonClk.setText(board.getMessage());
        }
        if (this.vary.equals("party")) {
/*            String forDisplay = "Party: ";
            for(Entity e:pieceLists.getPlayerParty()) {
                forDisplay = forDisplay + e.getName() + " (atk: " + e.getAtk() + ",hp: " + e.getHp() + ",mvt: " + e.getMovement() + ") ";
            }
            toPlayer.setText(forDisplay);*/
            toPlayer.setText(this.display);
        }
        if (this.vary.equals("enemy")) {
/*            String forDisplay = "Enemies: ";
            for(Entity e:pieceLists.getAIPieces()) {
                forDisplay = forDisplay + e.getName() + " (atk: " + e.getAtk() + ",hp: " + e.getHp() + ",mvt: " + e.getMovement() + ") ";
            }
            toPlayer.setText(forDisplay);*/
            toPlayer.setText(this.display);
        }
        if (this.vary.equals("move")) {
            if (board.getMidMove() == false) {
                toPlayer.setText("Click where to move then click the MOVE button again.");
                board.setMidMove(true);
                System.out.println("Before choice, " + board.getMidMove());

            }
            else {
                if (human.movePiece(place)) {
                    board.setMidMove(false);
                    toPlayer.setText("Successful move.");
                }
                else {
                    toPlayer.setText("Invalid selection.");
                }
            }
        }
        if (this.vary.equals("attack")) {
            if (board.getMidAtk() == false) {
                toPlayer.setText("Click who to attack then click the ATTACK button again.");
                board.setMidAtk(true);
                System.out.println("Before choice, " + board.getMidAtk());
                System.out.println("Place before, " + place);
            }
            else {
                System.out.println("Place after, " + place);
                if (human.attackPiece(place)) {
                    board.setMidAtk(false);
                    toPlayer.setText("Successful attack.");
                    System.out.println("New HP " + pieceLists.getMasterList().get((map.getPiece(place) - 1)).getHp());
                    System.out.println("New HP 2 " + pieceLists.getAIPieces().get(1).getHp() + "e4 " + pieceLists.getAIPieces().get(0).getHp() + "e6 " + pieceLists.getAIPieces().get(2).getHp());
                }
                else {
                    toPlayer.setText("Invalid selection.");
                }
            }
        }
        if (this.vary.equals("heal")) {
            if (human.healPiece()) {
                toPlayer.setText("Piece healed by 1 HP.");
            }
            //System.out.println("HP: " +  pieceLists.getMasterList().get((map.getPiece(place) - 1)).getHp())
        }
        if (this.vary.equals("endPiece")) {
            toPlayer.setText("Dead button...");
            human.endPieceTurn();
        }
    }
}

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