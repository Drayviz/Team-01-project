import java.util.Scanner;
import javafx.scene.control.Label;

public class HumanPlayerGUI extends Turn{
    private Map map = new Map();
    private int temp = 0;
    private int turnDone = 0;
    private Label toPlayer = new Label();
    private Entity piece;
    private boolean viable; //?
    private boolean success = false;

    public HumanPlayerGUI(Map map, Pieces pieceLists) {
        super(map, pieceLists);
        this.map = map;
    }
    public HumanPlayerGUI(Map map, Pieces pieceLists, Label toPlayer) {
        super(map,pieceLists);
        this.map = map;
        this.toPlayer = toPlayer;
    }

    public boolean startTurn(int place) {
        viable = false;
        success = false;
        viable = isValidSelection(place);
        if (viable) {
            super.selectPiece(place);
        }
        return viable;
    }

    public void resetTurn() {
        super.resetTurn();
    }

    public boolean movePiece(int place){
        viable = false;
        success = false;
        viable = super.checkPieceApAndHealth();
        if (viable) {
            success = super.movePiece(place);
        }
        return success;
    }

    public boolean attackPiece(int place) {
        viable = false;
        success = false;
        viable = super.checkPieceApAndHealth();
        if (viable) {
            success = super.attackPiece(place);
        }
        return success;
    }

    public boolean healPiece() {
        viable = false;
        success = false;
        viable = super.checkPieceApAndHealth();
        if (viable) {
            success = super.healPiece();
        }
        return success;
    }

    public void endPieceTurn() {
        viable = false;
        super.unselectPiece();
    }
}