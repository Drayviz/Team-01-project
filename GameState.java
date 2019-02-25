public class GameState {
    //private MapInfo level; unnecessary?
    private Map map;
    //private Entity player; unnecessary?

    /* Constructor */
    public GameState(Map currentMap) {
        this.map = new Map(currentMap);
    }

    /* If all is good, updates changes to the map (obv by creating a new one, because privacy!!!) */
    public Map update(Map newMap) {
        this.map = new Map(newMap);
    }

    /* This method considers whether a move is valid based on where the piece is and where the piece wants to go.
    first if-SM: considers if the piece wants to move to the same spot; invalid
    second if-SM: only allows piece to move left, right, up, down (not diagonally, if necessary will implement later), respectively
    third if-SM: doesn't allow one to move to a space if it's already occupied.
    returns true if move is valid.  */
    public boolean isValidMove(int start, int end) {
        boolean viable = true;
    
        if (start == end) {
            viable = false;
        }
        
        /* is map.getRows() even a method??? lol */
        if ((end != start - 1 ) && (end != start + 1) && (end != start - map.getRows()) && (end != start + map.getRows())) {
            viable = false;
        }
        for (Array a:map) {
            if (a[2] == end) {
                if (a[3] != 0) {
                    viable = false;
                } 
            }
        }
        return viable;
    }

    /* This method considers whether an attack is valid based on where the piece is and where the target is.
    first if-SM: considers if the target is to the left, right, above, or below the attacker(not diagonally, if necessary will implement later), respectively
    second if-SM: the two for-loops are used to obtain the <party> of the pieces. If the pieces are in the same party, the attack is invalid (no friendly-fire)
        NOTE: I have not considered an error if the spot they want to attack is unoccupied
    returns true if attack is valid.  */    
    public boolean isValidAtk(int start, int end) {
        boolean viable = true;
        int indicator1;
        int indicator2;

        if ((end != start - 1 ) && (end != start + 1) && (end != start - map.getRows()) && (end != start + map.getRows())) {
            viable = false;
        }

        for (Array a:map) {
            if (a[2] == start) {
                indicator1 = a[3].getParty();
            }
        }
        for (Array b:map) {
            if (b[2] == end) {
                indicator1 = b[3].getParty();
            }
        }
        if (indicator1 == indicator2) {
            viable = false;
        }

        return viable;
    }

    /* Scours the map to look for dead pieces. Removes them when dead. I'm not sure how well this method works, so if it's buggy just comment it out for the time being.*/
    public ArrayList<Entity> removePieceFromLists() {
        for (Array a:map) {
            if (a[1] != 0) {
                int state = a[1].getState(); //retrieves state
                int party = a[1].getParty(); //retrieves party (just so we know which )
                if (state == 0) { //if dead
                    if (party == 1) {
                        for (Entity h:getHumanPieces()) {
                            if (a[1].equals(h)) {
                                return new ArrayList<Entity>(getHumanPieces.remove(h)); //creates new list without presence of dead boy
                            }
                        }
                    }
                    else if (party == 2) {
                        for (Entity h:getAIPieces()) {
                            if (a[1].equals(h)) {
                                return new ArrayList<Entity>(getAIPieces.remove(h)); //creates new list without presence of dead boy
                            }
                        }
                    }
                }
            }
        }        
    }

    public Map removePieceFromMap() {
        for (Array a:map) {
            if (a[1] != 0) {
                int state = a[1].getState(); //retrieves state
                int party = a[1].getParty(); //retrieves party (just so we know which )
                if (state == 0) { //if dead
                    a[1] = 0; //wipes that bitch off the map
                }
            }
        }
        return new Map(map);        
    }

    /* This method scours the map, checking each Array in the ArrayList map. So, as long as a piece is occupied (line 81), the method will obtain the <party>> of the piece on said spot.
    If the piece is an enemy, the enemyCount is added to.
    returns true when there are no enemies on the board.  */
    public boolean hasWon() {
        boolean won = false;
        int enemyCount = 0;

        for (Array a:map) {
            if (a[1] != 0) {
                int party = a[1].getParty();
                if (party == 2) {
                    enemyCount = enemyCount +1;
                }
            }
        }
        if (enemyCount == 0) {
            won = true;
        }

        return won;
    }

    /* Same as hasWon(), except counts the player's pieces instead. 
    returns true when there are no more player pieces. :( */
    public boolean hasLost() {
        boolean lost = false;
        int playerCount = 0;

        for (Array a:map) {
            if (a[1] != 0) {
                int party = a[1].getParty();
                if (party == 1) {
                    playerCount = playerCount +1;
                }
            }
        }

        if (playerCount == 0) {
            lost = true;
        }

        return lost;        
    }
    
    /* I think this method is only necessary for AI; I will do this later */
/*     public Turn[] getAllValidMoves() {
        return null;
    } */

    /* Will do this in the future */
/*     public int checkObjectives() {

    } */

}