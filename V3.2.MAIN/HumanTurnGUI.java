/*Note: to quickly clean up unnecessary lines, search "REMOVE*"
 * Documented and rid of privacy leaks.*/

public class HumanTurnGUI extends Turn{
    private MapClass map = new MapClass();
    private boolean viable;
    private boolean success = false;

    /**Constructor.
     * @param map . This is the map in the current state of the game.
     * @param pieceLists . This is the information on all the pieces in the current state of the game.*/
    public HumanTurnGUI(MapClass map, PieceLibrary pieceLists) {
        super(map, pieceLists);
        this.map = map;
    }

    /**Initiates the turn of the piece by "selecting" it.
     * Ensures that the place indicated in the parameter is a location on the map with a valid piece.
     * @param place . This is the location on the map that the supposed piece is on.
     * @return so that the event in Events occurs or doesn't, according to the boolean returned.*/
    public boolean startTurn(int place) {
        viable = false;
        success = false;
        viable = isValidSelection(place);
        if (viable) {
            super.selectPiece(place);
        }
        return viable;
    }

    /**Resets the pieces' AP.  */
    public void resetTurn() {
        super.resetTurn();
    }

    /**Moves the piece if appropriate.
     * First checks if the piece has enough AP and is alive.
     * Then moves it if the place indicated is appropriate.
     * @param place . This is the location the user intends to move the piece to.
     * @return so that the event in Events occurs or doesn't, according to the boolean returned.*/
    public boolean movePiece(int place){
        viable = false;
        success = false;
        viable = super.checkPieceApAndHealth();
        if (viable) {
            success = super.movePiece(place);
            System.out.println("SUCCESS: " + success); //REMOVE*
        }
        return success;
    }

    /**Attacks an enemy if appropriate.
     * First checks if the piece attacking has enough AP and is alive.
     * Then attacks the enemy it if the place the supposed enemy is on is appropriate.
     * @param place . This is the location of the enemy that the user intends to attack.
     * @return so that the event in Events occurs or doesn't, according to the boolean returned.*/
    public boolean attackPiece(int place) {
        viable = false;
        success = false;
        viable = super.checkPieceApAndHealth();
        if (viable) {
            success = super.attackPiece(place);
        }
        return success;
    }
    /**Heals the piece if appropriate.
     * Checks if the piece has enough AP and is alive.
     * @return so that the event in Events occurs or doesn't, according to the boolean returned.*/
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