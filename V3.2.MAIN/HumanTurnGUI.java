import java.util.Scanner;
import javafx.scene.control.Label;

public class HumanTurnGUI extends Turn{
    private MapClass map = new MapClass();
    private Label toPlayer = new Label();
    private boolean viable;
    private boolean success = false;

    public HumanTurnGUI(MapClass map, PieceLibrary pieceLists) {
        super(map, pieceLists);
        this.map = map;
    }
    public HumanTurnGUI(MapClass map, PieceLibrary pieceLists, Label toPlayer) {
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
            System.out.println("SUCCESS: " + success);
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
}