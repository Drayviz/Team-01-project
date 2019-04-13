/*Documented and rid of privacy leaks.*/

/*package gui;
import text.*;*/

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import java.util.ArrayList;

public class Events implements EventHandler<ActionEvent> {
    private MapClass map = new MapClass();
    private PieceLibrary pieceLists = new PieceLibrary();
    private int place = 0;
    private Label toPlayer = new Label();
    private String vary = "";
    private HumanTurnGUI human;
    private String display;

    /**Constructor.
     * Events that occur when the buttons "viewParty" and "viewEnemies" are pressed.
     * @param vary . This string determines which action to evoke in handle().
     * @param toPlayer . This brings information of the label from BoardPanel into the event-handler.
     * @param display . This brings information of the display text on toParty into the event-handler.*/
    public Events(String vary, Label toPlayer, String display) {
        this.vary = new String(vary);
        this.toPlayer = toPlayer;
        this.display = new String(display);
    }

    /**Constructor.
     * Events that occur when the buttons "move" and "attackOrder" are pressed.
     * @param place . This is the place where action occurs (whether it's the place the piece is on or where they want to attack/move).
     * @param vary . This string determines which action to evoke in handle().
     * @param map . This is the map in the current state of the game.
     * @param pieceLists . This is the information on all the pieces in the current state of the game.
     * @param human . This is the information of the HumanTurnGUI in the current state of the game.
     * @param toPlayer . This brings information of the label from BoardPanel into the event-handler.*/
    public Events(int place, String vary, MapClass map, PieceLibrary pieceLists, HumanTurnGUI human, Label toPlayer) {
        this.place = new Integer(place);
        this.vary = new String(vary);
        this.map = new MapClass(map);
        this.pieceLists = new PieceLibrary(pieceLists);
        this.human = new HumanTurnGUI(human);
        this.toPlayer = toPlayer;
    }

    /**Constructor.
     * Events that occur when the buttons "heal" is pressed.
     * @param vary . This string determines which action to evoke in handle().
     * @param map . This is the map in the current state of the game.
     * @param pieceLists . This is the information on all the pieces in the current state of the game.
     * @param human . This is the information of the HumanTurnGUI in the current state of the game.
     * @param toPlayer . This brings information of the label from BoardPanel into the event-handler.*/
    public Events(String vary, MapClass map, PieceLibrary pieceLists, HumanTurnGUI human, Label toPlayer) {
        this.vary = new String(vary);
        this.map = new MapClass(map);
        this.pieceLists = new PieceLibrary(pieceLists);
        this.human = new HumanTurnGUI(human);
        this.toPlayer = toPlayer;
    }

    /** I use the variable VARY to distinguish which kind of implementation I get for each different button click.*/
    public void handle(ActionEvent event) {
        BoardPanel board = new BoardPanel();
        /** "party" and "enemy" change the toPlayer text based on the parameter inputted.*/
        if (this.vary.equals("party")) {
            toPlayer.setText(this.display);
        }
        if (this.vary.equals("enemy")) {
            toPlayer.setText(this.display);
        }
        if (this.vary.equals("build")) {
            toPlayer.setText(this.display);
        }
        /** "move" and "attack" need to be pressed twice to move/attack.
         * Essentially, changes a variable in BoardPanel to signifiy what each press does.*/
        if (this.vary.equals("move")) {
            if (board.getMidMove() == false) {
                toPlayer.setText("Click where to move then click the MOVE button again.");
                board.setMidMove(true);
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
            if (board.getMidAtk() == false) {
                toPlayer.setText("Click who to attack then click the ATTACK button again.");
                board.setMidAtk(true);
            }
            else {
                if (human.attackPiece(place)) {
                    board.setMidAtk(false);
                    toPlayer.setText("Successful attack.");
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