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
        super.resetTurn();
        viable = isValidSelection(place);
        if (viable) {
            super.selectpiece(place);
        }
        return viable;
    }

    public boolean movePiece(int place){
        viable = super.checkPieceApAndHealth();
        if (viable) {
            success = super.movePiece(place);
        }
        return success;
    }

    public void attackPiece(int place) {
        super.attackPiece(place);
        viable = super.checkPieceApAndHealth();
    }

    public void healPiece() {
        super.healPiece();
    }

    public void endPieceTurn() {
        viable = false;
        super.unselectPiece();
    }
}