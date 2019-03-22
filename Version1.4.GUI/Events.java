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
    private String vary = "";

    /*For boardButton*/
    public Events(int place, String vary, Map map, Game game, Pieces pieceLists) {
        this.place = place;
        this.vary = vary;
        this.map = map;
        this.game = game;
        this.pieceLists = pieceLists;
    }


    public Events(String vary, Map map, Game game, Pieces pieceLists) {
        this.vary = vary;
        this.map = map;
        this.game = game;
        this.pieceLists = pieceLists;
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
        System.out.println("Turn counter: " + game.getTurnCounter());
        System.out.println("Pieces placed: " + board.getPiecesPlaced());
        System.out.println("Player party size: " + pieceLists.getPlayerParty().size());
        System.out.println("Submessage: " + board.getSubmessage());
        System.out.println("Message: " + board.getMessage());
        System.out.println("Pieces placed again: " + board.getPiecesPlaced());
        if (this.vary.equals("party")) {
            Button viewPartyClk = (Button) event.getSource();
            String forDisplay = "Party: ";
            for(Entity e:pieceLists.getPlayerParty()) {
                forDisplay = forDisplay + e.getName() + " (atk: " + e.getAtk() + ",hp: " + e.getHp() + ",mvt: " + e.getMovement() + ") ";
            }
            System.out.println("dsfds");
            //board.setToPlayer(forDisplay);
            viewPartyClk.setText("esf");
        }
    }
}
