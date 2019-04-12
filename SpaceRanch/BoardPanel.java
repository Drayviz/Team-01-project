/*Note: to quickly clean up unnecessary lines, search "REMOVE*"
* Documented and rid of privacy leaks.*/

import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.*;
import java.util.*;
import javafx.event.*;
import javafx.stage.*;
import javafx.geometry.*;
import javafx.application.*;

public class BoardPanel {

    /** Instance variables.
     * The first 6 are to initialize various objects that the game needs to run.
     * The next 5 are helper variables that allow for easier communication.
     * The next 12 are variables that creates physical entities on the GUI; they're instance variables for easier communication.
     * The bottom 5 are to create images.
     * */
    private MapClass map;
    private MetaGame startGame = new MetaGame();
    private Game game = new Game();
    private PieceLibrary pieceLists = new PieceLibrary();
    private HumanTurnGUI human;
    private Turn turn;
    private Terrain terrain = new Terrain();
    
    public static boolean midMove = false;
    public static boolean midAtk = false;
    private int piecesPlaced = 0;
    private String partyDisplay;
    private String enemyDisplay;
    private String buildingDisplay;

    public Label toPlayer = new Label("YOU'RE UNDER ATTACK. PLACE YOUR MECHS."); //
    public Label turnLabel = new Label("X TURNS LEFT");
    private Button boardButton;
    private Button move;
    private Button attackOrder;
    private Button heal;
    private Button updateState;
    private Button viewParty;
    private Button viewEnemies;
    private Button viewBuildings;
    private GridPane grid = new GridPane();
    private GridPane tileGrid = new GridPane();
    private BorderPane root = new BorderPane();

    private Image piece1 = new Image("Images/combatMech.png"); //
    private Image piece2 = new Image("Images/judoMech.png"); //
    private Image piece3 = new Image("Images/flameMech.png"); //
    private Image eFastboi = new Image("Images/hornet.png"); //
    private Image eRanged = new Image("Images/rocketMech.png");
    private Image eTitan = new Image("Images/aegisMech.png");
    private Image eArtill = new Image("Images/artilleryMech.png");
    private Image tile = new Image("Images/tile.png"); //
    private Image mountain = new Image("Images/mountain.png"); //
    private Image river = new Image("Images/water.png"); //
    private Image pitfall = new Image("Images/chasm.png"); //
    private Image build1 = new Image("Images/1.png");
    private Image build2 = new Image("Images/2.png");
    private Image build3 = new Image("Images/3.png");
    private Image hq = new Image("Images/hq.png");
    private Image power = new Image("Images/power.png");

    /** Default constructor.*/
    BoardPanel() {}

    /** Constructor.
     * Note that it is a constructor, rather than running as a start(). This is to allow other GUI's to instantiate BoardPanel,
     * which allows BoardPanel to pop up when instantiated.
     * This method creates the GUI itself and allows instantiation of the objects required for the game to run.
     * There are also 2 anonymous classes for event-handling for the buttons updateState and endTurn.
     * @param world is the world of the level the player would like to play.
     * @param lvl is the level the player would like to play.
     * */
    BoardPanel(String world, String lvl) {
        Stage substage = new Stage();
        /*These are the primary layouts used in the GUI.*/

        VBox topSection = new VBox(0);
        HBox underTop = new HBox();

        /*Setting up the very important toPlayer label, which is set to change the message when most
        buttons are pressed.*/

        turnLabel.setFont(Font.font("Courier New", 18));
        toPlayer.setFont(Font.font("Courier New", 18));
        turnLabel.setAlignment(Pos.CENTER);
        toPlayer.setAlignment(Pos.CENTER);

        Button endTurn = new Button("End Turn");
        viewParty = new Button("View party");
        viewEnemies = new Button("View enemies");
        viewBuildings = new Button("View buildings");
        move = new Button("Move");
        attackOrder = new Button("Attack");
        heal = new Button("Heal");
        updateState = new Button("Update state");
        Button showTileGrid = new Button("Show tiles");

        HBox rightButton = new HBox(move, attackOrder, heal, updateState, showTileGrid);
        rightButton.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(rightButton, Priority.ALWAYS);

        underTop.getChildren().addAll(endTurn, viewParty, viewEnemies, viewBuildings, rightButton);
        underTop.setPadding(new Insets(2));

        topSection.getChildren().addAll(toPlayer, turnLabel, underTop);

        tileGrid.setVgap(20);
        tileGrid.setHgap(20);
        tileGrid.setAlignment(Pos.CENTER);

        grid.setVgap(20);
        grid.setHgap(20);
        grid.setAlignment(Pos.CENTER);

        /*These two methods are for instantiating the game with necessary info and for updating
        * the GUI with the info corresponding to the current state of the game (see below)*/
        gamePlaying(world, lvl);
        update();

        topSection.setPadding(new Insets(10, 30, 30, 30));
        topSection.setSpacing(5);

        root.setPadding(new Insets(30,30,30,30));
        root.setCenter(grid);
        root.setTop(topSection);

        Scene scene = new Scene(root, 1600, 600);
        substage.setTitle("Space Ranch");
        substage.setScene(scene);
        substage.show();


        //E V E N T H A N D L I N G
        /*This updates the appearance of the GUI based on the current information of the game.*/
        updateState.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                update();
            }
        });

        showTileGrid.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showTileGrid();
            }
        });
        /*This is for the endTurn button. This is what the user presses to end their turn.
        * Since the game loop in Game is unable to be fully run in that class alone, the human-player
        * part of the game loop is run through this event.*/
        endTurn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                human.resetTurn();
                if (game.getTurnCounter() != 0) {
                    game.oneLessTurn();
                    game.setGameDone(game.hasWon());
                    update();
                    if (game.getGameDone() == 1) {
                        toPlayer.setText("AI SETUP AND ATTACK");
                        game.play();
                        if (game.getGameDone() == 1) {
                            toPlayer.setText("HUMAN TURN... Click tile with desired piece to execute turn.");
                        }
                    }
                }
            }
        });
    }

    /**This method is used to instantiate the objects necessary to start the game so that the game can be run through the same class as the GUI
     * (this serves to start the GUI and the game itself simultaneously)
     * All these commands are meant to set the game up, before the turn loop runs.
     * @param world ensures the game created has the proper info according to the world chosen by the player.
     * @param world ensures the game created has the proper info according to the level chosen by the player.*/
    public void gamePlaying(String world, String lvl) {
        this.game = startGame.startGUIGame(world, lvl);
        this.pieceLists = game.getPieces();
        this.map = game.getMap();
        game.placeAIPieces();
        this.turn = new Turn(getMap(), getPieceLists());
        this.human = new HumanTurnGUI(this.map, this.pieceLists);
    }

    /**This method is necessary because the row and column numbers of the grid don't correspond to anything.
     * Thus, this method converts the row and column numbers into one integer that increases for each button added.
     *@param rowIndex . This is the row the button is in.
     *@param columnIndex . This is the column the button is in.
     *@param dimensions . This is the dimensions of the map itself.
     *@return the number of that spot on the grid that is the same as its counterpart on the map.*/
    public int conversion(int rowIndex, int columnIndex, int dimensions) {
        return dimensions - (dimensions - columnIndex - 1) + dimensions*rowIndex;
    }

    /**This methods updates the appearance of the GUI according to the information of the game (from changes in the map due to movement, etc).*/
    public void update() {
        turnLabel.setText(game.getTurnCounter() + " TURNS LEFT / POWERGRID HP: " + game.getPowerGrid());
        map.displayMap(); //REMOVE*
        updateGrid();
        updateDisplay(1);
        updateDisplay(2);
        updateDisplay(3);
        /* This is updates the states of the entities for the viewParty button */
        Events viewPartyEvent = new Events("party", getToPlayer(), getPartyDisplay());
        viewParty.setOnAction(viewPartyEvent);
        /*This is updates the states of the entities for the viewEnemies button*/
        Events viewEnemyEvent = new Events("enemy", getToPlayer(), getEnemyDisplay());
        viewEnemies.setOnAction(viewEnemyEvent);
        Events viewBuildingEvent = new Events("build", getToPlayer(), getBuildingDisplay());
        viewBuildings.setOnAction(viewBuildingEvent);
    }

    /*From the info provided by the map, this method shows all the tiles are each coordinate.*/
    public void showTileGrid() {
        if (game.getTurnCounter() != map.getTurns()+1) {
            this.tileGrid = new GridPane();
            tileGrid.setVgap(20);
            tileGrid.setHgap(20);
            tileGrid.setAlignment(Pos.CENTER);
            root.setCenter(tileGrid);
        }
        for(int row = 0; row < map.getDimensions(); row++) {
            for(int col = 0; col < map.getDimensions(); col++) {
                tileGrid.setStyle("-fx-background-image: url('https://www.solarsystemscope.com/textures/download/2k_moon.jpg')");
                int place = conversion(row,col,map.getDimensions()); //This runs the method conversion() in this class.
                String value = findPieces(place-1); //This runs the method findAIPieces() in this class.
                Button tileButton = new Button();
                tileButton.setStyle("-fx-background-color: transparent;");
                if (value.contains("z")) { tileButton.setGraphic(new ImageView(mountain)); }
                else if (value.contains("y")) { tileButton.setGraphic(new ImageView(river)); }
                else if (value.contains("x")) { tileButton.setGraphic(new ImageView(pitfall)); }
                else if (value.contains("w")) { tileButton.setGraphic(new ImageView(build1)); }
                else if (value.contains("v")) { tileButton.setGraphic(new ImageView(build2)); }
                else if (value.contains("u")) { tileButton.setGraphic(new ImageView(build3)); }
                else if (value.contains("t")) { tileButton.setGraphic(new ImageView(hq)); }
                else if (value.contains("s")) { tileButton.setGraphic(new ImageView(power)); }
                else if (value.contains("0")) { tileButton.setGraphic(new ImageView(tile)); }
                tileGrid.add(tileButton, col, row);
            }
        }
    }

    /*This is the initial map creation. The loop is supposed to create buttons in a grid (using GridPane),
    with the rows and columns equal to the map dimensions. This map gets created repeatedly as the state of Map changes.*/
    public void updateGrid() {
        if (game.getTurnCounter() != map.getTurns()+1) {
            this.grid = new GridPane();
            grid.setVgap(20);
            grid.setHgap(20);
            grid.setAlignment(Pos.CENTER);
            root.setCenter(grid);
        }
        for(int row = 0; row < map.getDimensions(); row++) {
            for(int col = 0; col < map.getDimensions(); col++) {
                grid.setStyle("-fx-background-image: url('https://www.solarsystemscope.com/textures/download/2k_moon.jpg')");
                int place = conversion(row,col,map.getDimensions()); //This runs the method conversion() in this class.
                String value = findPieces(place-1); //This runs the method findAIPieces() in this class.
                Button boardButton = new Button();
                boardButton.setStyle("-fx-background-color: transparent;");
                if (value.contains("a")) { boardButton.setGraphic(new ImageView(piece1)); }
                else if (value.contains("b")) { boardButton.setGraphic(new ImageView(piece2)); }
                else if (value.contains("c")) { boardButton.setGraphic(new ImageView(piece3)); }
                else if (value.contains("d")) { boardButton.setGraphic(new ImageView(eTitan)); }
                else if (value.contains("e")) { boardButton.setGraphic(new ImageView(eArtill)); }
                else if (value.contains("f")) { boardButton.setGraphic(new ImageView(eFastboi)); }
                else if (value.contains("g")) { boardButton.setGraphic(new ImageView(eRanged)); }
                else if (value.contains("z")) { boardButton.setGraphic(new ImageView(mountain)); }
                else if (value.contains("y")) { boardButton.setGraphic(new ImageView(river)); }
                else if (value.contains("x")) { boardButton.setGraphic(new ImageView(pitfall)); }
                else if (value.contains("w")) { boardButton.setGraphic(new ImageView(build1)); }
                else if (value.contains("v")) { boardButton.setGraphic(new ImageView(build2)); }
                else if (value.contains("u")) { boardButton.setGraphic(new ImageView(build3)); }
                else if (value.contains("t")) { boardButton.setGraphic(new ImageView(hq)); }
                else if (value.contains("s")) { boardButton.setGraphic(new ImageView(power)); }
                else if (value.contains("0")) { boardButton.setGraphic(new ImageView(tile)); }
                grid.add(boardButton, col, row);
                /*When any button on the grid is clicked, 3 things can happen:
                 * 1. If it's the first turn, the user places a piece.
                 * 2. If it's not the first turn and the user is in the middle of a move, the user selects where to move.
                 * 2. If it's not the first turn and the user is in the middle of an attack, the user selects a target to attack.*/
                boardButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (game.getTurnCounter() == (map.getTurns())) {
                            if (buttonAction(place)) {
                                updateGrid();
                            }
                        }
                        else {
                            buttonActionAfter(place);
                            if (map.getPiece(place) == 0 && getMidMove() == true) {
                                System.out.println("Midmove success"); //REMOVE*
                                buttonActionMidMove(place);
                            }
                            else if (getMidAtk()) {
                                System.out.println("MidAtk success"); //REMOVE*
                                buttonActionMidMove(place);
                            }
                        }
                    }
                });
            }
        }
    }

    /**This goes hand-in-hand with placeAIPieces() in Game. This ensures that the enemy pieces are already placed when the map is created.
     *@param index . This is the converted number (using conversion()) of the location of the button.
     * @return a value that will decide the type of piece it is.*/
    public String findPieces(int index) {
        String toReturn = "0";
        ArrayList<ArrayList<Integer>> proxy = map.getMaparray();
        ArrayList<Integer> tileArray = proxy.get(index);

        if (tileArray.get(0) != 1) {
            if (tileArray.get(0) == 6) {
                toReturn = toReturn + "z";
            }
            else if (tileArray.get(0) == 7) {
                toReturn = toReturn + "y";
            }
            else if (tileArray.get(0) == 8) {
                toReturn = toReturn + "x";
            }
            else if (tileArray.get(0) == 20) {
                toReturn = toReturn + "w";
            }
            else if (tileArray.get(0) == 30) {
                toReturn = toReturn + "v";
            }
            else if (tileArray.get(0) == 40) {
                toReturn = toReturn + "u";
            }
            else if (tileArray.get(0) == 50) {
                toReturn = toReturn + "t";
            }
            else if (tileArray.get(0) == 60) {
                toReturn = toReturn + "s";
            }
        }
        if (tileArray.get(1) != 0 && tileArray.get(1) > pieceLists.getHumanPieces().size()) {
            if (pieceLists.getMasterList().get(tileArray.get(1) - 1).getPrefabIdentifyer() == 10) {
                toReturn = toReturn + "d";
            } else if (pieceLists.getMasterList().get(tileArray.get(1) - 1).getPrefabIdentifyer() == 20) {
                toReturn = toReturn + "e";
            } else if (pieceLists.getMasterList().get(tileArray.get(1) - 1).getPrefabIdentifyer() == 30) {
                toReturn = toReturn + "f";
            } else if (pieceLists.getMasterList().get(tileArray.get(1) - 1).getPrefabIdentifyer() == 40) {
                toReturn = toReturn + "g";
            }
        }
        if (tileArray.get(1) != 0 && tileArray.get(1) <= pieceLists.getHumanPieces().size()) {
            if (tileArray.get(1) == 1) {
                toReturn = toReturn + "a";
            }
            else if (tileArray.get(1) == 2) {
                toReturn = toReturn + "b";
            }
            else if (tileArray.get(1) == 3) {
                toReturn = toReturn + "c";
            }
        }
        return toReturn;
    }

    /**This updates the information in viewParty and viewEnemies based on the information on all the pieces.
     * It also updates the toPlayer text based on if the win/loss condition is met.
     * @param party, this is the type of the piece (player or AI)*/
    public void updateDisplay(int party) {
        int forBuilding = pieceLists.getAIPieces().size() +  pieceLists.getPlayerParty().size();
        if (party == 1) {
            this.partyDisplay = "Party: ";
            for (int count = 0; count < pieceLists.getPlayerParty().size(); count++) {
                this.partyDisplay = this.partyDisplay + pieceLists.getMasterList().get(count).getName() + " (atk: " + pieceLists.getMasterList().get(count).getAtk() + ",hp: " + pieceLists.getMasterList().get(count).getHp() + ",AP: " + pieceLists.getMasterList().get(count).getAP() + ") ";
            }
        }
        if (party == 2) {
            this.enemyDisplay = "Enemies: ";
            for (int count = pieceLists.getAIPieces().size(); count < forBuilding; count++) {
                this.enemyDisplay = this.enemyDisplay + pieceLists.getMasterList().get(count).getName() + " (atk: " + pieceLists.getMasterList().get(count).getAtk() + ",hp: " + pieceLists.getMasterList().get(count).getHp() + ",AP: " + pieceLists.getMasterList().get(count).getAP() + ") ";
            }
        }
        if (party == 3) {
            this.buildingDisplay = "Buildings: ";
            for (int count = forBuilding; count < pieceLists.getMasterList().size(); count++) {
                this.buildingDisplay = this.buildingDisplay + pieceLists.getMasterList().get(count).getName() + "(hp: " + pieceLists.getMasterList().get(count).getHp() + ") ";
            }
        }
        if (game.getGameDone() == 2) {
            toPlayer.setText("Heavy Victory...");
        }
        else if (game.getGameDone() == 3) {
            toPlayer.setText("ur party is ded");
        }
        else if (game.getGameDone() == 4) {
            toPlayer.setText("You won and killed all the enemies just in time!");
        }
        else if (game.getGameDone() == 5) {
            toPlayer.setText("You have defended yourself from the enemies!");
        }
        else if (game.getGameDone() == 6) {
            toPlayer.setText("You won and killed all the enemies!");
        }
    }

    /** This is the implementation of eventhandling for the boardButton at turn 0.
     * @param place . This is the place that the player intends to place their piece.
     * @return check, which determines if the placing of the piece was valid or not.*/
    public boolean buttonAction(int place) {
        boolean check = false;
        if (piecesPlaced < pieceLists.getPlayerParty().size()) { //This ensures that pieces can only be placed before the game begins, and there can't be more pieces placed than permitted.
            if (map.getPiece(place) == 0 && place < (map.getDimensions() * map.getDimensions() - map.getDimensions() * 3 + 1) && !(terrain.checkMountain(place, getMap()))) { //This ensures that pieces can only be placed on empty spaces and not in the last 3 rows
                piecesPlaced++;
                map.setState(place, 1, piecesPlaced); //This sets the piece itself
                check = true;
                map.displayMap(); //REMOVE*
            }
            else {
                toPlayer.setText("Pieces are not permitted to be placed here.");
            }
        }
        else {
            toPlayer.setText("No more pieces are permitted to be placed.");
        }
        return check;
    }

    /** This is the implementation of eventhandling for the boardBUtton after turn 0; allows for movement and attacking.
     * @param place. This is the place that the user intends to move to or the place of the enemy the user intends to attack.*/
    public void buttonActionAfter(int place) {
        if (map.getPiece(place) == 0) {
                toPlayer.setText("TILE " + place + " selected.");
                System.out.println(getMidMove()); //REMOVE*
        }
        else {
            if (human.startTurn(place)) {
                toPlayer.setText("Choose to MOVE, ATTACK, OR HEAL.");
                Events moveEvent = new Events(place, "move", getMap(), getPieceLists(), getHuman(), getToPlayer());
                Events attackOrderEvent = new Events(place, "attack", getMap(), getPieceLists(), getHuman(), getToPlayer());
                Events healEvent = new Events("heal", getMap(), getPieceLists(), getHuman(), getToPlayer());
                move.setOnAction(moveEvent);
                attackOrder.setOnAction(attackOrderEvent);
                heal.setOnAction(healEvent);
            }
        }
    }

    /** This is the implementation of eventhandling for the boardBUtton after the MOVE and ATTACK buttons are pressed;
     * creates a new Events object so the piece can be moved/attack. This is to allow for differentiation of events that
     * occur for different presses of the MOVE and ATTACK button.
     * @param place. This is the place that the user intends to move to or the place of the enemy the user intends to attack.*/
    public void buttonActionMidMove(int place) {
        Events move2Event = new Events(place, "move", getMap(), getPieceLists(), getHuman(), getToPlayer());
        move.setOnAction(move2Event);
        Events attackOrder2Event = new Events(place, "attack", getMap(), getPieceLists(), getHuman(), getToPlayer());
        attackOrder.setOnAction(attackOrder2Event);
    }

    /**Getters and setters to allow easy communication between classes.*/
    public Label getToPlayer() {
        return toPlayer;
    }
    public MapClass getMap() {
        return this.map;
    }
    public PieceLibrary getPieceLists() {
        return this.pieceLists;
    }
    public HumanTurnGUI getHuman() {
        return this.human;
    }
    public boolean getMidMove() {
        return new Boolean(this.midMove);
    }
    public void setMidMove(boolean bool) {
        this.midMove = new Boolean(bool);
    }
    public boolean getMidAtk() {
        return new Boolean(this.midAtk);
    }
    public void setMidAtk(boolean bool) {
        this.midAtk = new Boolean(bool);
    }

    public String getPartyDisplay() {
        return new String(this.partyDisplay);
    }
    public String getEnemyDisplay() {
        return new String(this.enemyDisplay);
    }
    public String getBuildingDisplay() {
        return new String(this.buildingDisplay);
    }
}
/*Extra stuff*/

                    /* System.out.println("total turns: " + map.getTurns());
                System.out.println("has won: " +game.hasWon());
                System.out.println("Gamedone from BP: " + game.getGameDone());
                System.out.println("Turn from BP: " + game.getGUIturnCounter()); */
//System.out.println("Turns left: " + game.getGUI)
