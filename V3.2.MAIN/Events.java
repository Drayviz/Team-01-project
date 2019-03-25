import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

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

    /*For boardButton*/
    public Events(int place, String vary, Map map, Game game, Pieces pieceLists) {
        this.place = place;
        this.vary = vary;
        this.map = map;
        this.game = game;
        this.pieceLists = pieceLists;
    }

    /*For viewParty and viewEnemies*/
    public Events(String vary, Label toPlayer, Pieces pieceLists) {
        this.vary = vary;
        this.toPlayer = toPlayer;
        this.pieceLists = pieceLists;
    }

    /*For move and attackOrder*/
    public Events(int place, String vary, Map map, Game game, Pieces pieceLists, HumanPlayerGUI human) {
        this.place = place;
        this.vary = vary;
        this.map = map;
        this.game = game;
        this.pieceLists = pieceLists;
        this.human = human;
    }

    /*For heal and endPiece*/
    public Events(String vary, Map map, Game game, Pieces pieceLists, HumanPlayerGUI human) {
        this.vary = vary;
        this.map = map;
        this.game = game;
        this.pieceLists = pieceLists;
        this.human = human;
    }

    public void handle(ActionEvent event) {
        if (this.vary.equals("board")) {
            Button boardButtonClk = (Button) event.getSource();
            map.setState(place, 1, board.getPiecesPlaced()+1); //This sets the piece itself
            //submessage = "PIECE " + (piecesPlaced+1);
            board.setSubmessage("PIECE " + (board.getPiecesPlaced()+1));
            //message = place + ", " + submessage;
            board.setMessage(place + ", " + board.getSubmessage());
            //boardButton.setText(message);
            //piecesPlaced++;
            board.setPiecesPlaced(board.getPiecesPlaced()+1);
            //board.setBoardButton(board.getMessage());
            //map.displayMap();
            boardButtonClk.setText(board.getMessage());
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
        }
/*        System.out.println("Turn counter: " + game.getTurnCounter());
        System.out.println("Pieces placed: " + board.getPiecesPlaced());
        System.out.println("Player party size: " + pieceLists.getPlayerParty().size());
        System.out.println("Submessage: " + board.getSubmessage());
        System.out.println("Message: " + board.getMessage());
        System.out.println("Pieces placed again: " + board.getPiecesPlaced());*/
        if (this.vary.equals("party")) {
            String forDisplay = "Party: ";
            for(Entity e:pieceLists.getPlayerParty()) {
                forDisplay = forDisplay + e.getName() + " (atk: " + e.getAtk() + ",hp: " + e.getHp() + ",mvt: " + e.getMovement() + ") ";
            }
            toPlayer.setText(forDisplay);
        }
        if (this.vary.equals("enemy")) {
            String forDisplay = "Enemies: ";
            for(Entity e:pieceLists.getAIPieces()) {
                forDisplay = forDisplay + e.getName() + " (atk: " + e.getAtk() + ",hp: " + e.getHp() + ",mvt: " + e.getMovement() + ") ";
            }
            toPlayer.setText(forDisplay);
        }
        if (this.vary.equals("move")) {
            toPlayer.setText("Where would you like to move?");
            System.out.println("MOVE");
            human.movePiece(this.place);
        }
        if (this.vary.equals("attack")) {
            toPlayer.setText("Who would you like to attack?");
            System.out.println("ATK");
            human.attackPiece(this.place);
        }
        if (this.vary.equals("heal")) {
            toPlayer.setText("Piece healed by 1 HP.");
            System.out.println("HEL");
            human.healPiece();
        }
        if (this.vary.equals("endPiece")) {
            toPlayer.setText("Dead button...");
            System.out.println("DEAD");
            human.endPieceTurn();
        }
    }
}
