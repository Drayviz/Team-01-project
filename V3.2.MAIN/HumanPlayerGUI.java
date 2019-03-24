import java.util.Scanner;
import javafx.scene.control.Label;

public class HumanPlayerGUI extends Turn{
    private Map map = new Map();
    private int temp = 0;
    private int turnDone = 0;
    private Label toPlayer = new Label();
    private Entity piece;
    private boolean viable; //?

    public HumanPlayerGUI(Map map,MapInfo level,Pieces pieceLists) {
        super(map,level,pieceLists);
        this.map = map;
    }
    public HumanPlayerGUI(Map map, MapInfo level, Pieces pieceLists, Label toPlayer) {
        super(map,level,pieceLists);
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

    public void movePiece(int place) {
        super.movepiece(place);
        viable = super.checkPieceApAndHealth();
    }

    public void attackPiece(int place) {
        super.attackpiece(place);
        viable = super.checkPieceApAndHealth();
    }

    public void healPiece() {
        super.healpiece();
    }

    public void endPieceTurn() {
        viable = false;
    }
}