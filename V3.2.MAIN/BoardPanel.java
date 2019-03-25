import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.ArrayList;

public class BoardPanel extends Application {

    /** Instance variables. The first four are to initialize various objects that the game needs to run,
     * while the bottom four are variables consistently used and changed throughout the usage of the GUI. */
    private Map map;
    private MetaGame startGame = new MetaGame();
    private Game game = new Game();
    private Pieces pieceLists = new Pieces();
    private HumanPlayerGUI human;
    private Turn turn;

    public Label toPlayer = new Label("Welcome!");
    private String submessage = "---------";
    private String message;
    //private Button boardButton;
    private static int piecesPlaced = 0;
    private Button move;
    private Button attackOrder;
    private Button heal;
    private Button endPieceTurn;


    /** This method sets up the appearance of the GUI itself, while also eventhandling when boardButton,
     * viewParty, viewEnemies, and endTurn buttons are clicked.
     *
     * Note: this method is incomplete in terms of running the game. Currently, the GUI can place AI pieces and
     * allow the player to place pieces, as well as allowing the player to make "moves". However, the player cannot
     * move nor attack pieces. Furthermore, the GUI stops responding after a seemingly arbitrary number of loops.
     * These aforementioned loops are run by the endTurn button, which calls the play() method in Game.
     *
     * Note 2: the GUI version of the game differs fromt the console-based. There are different methods, so far, in
     * MetaGame and Game however all other classes are similar.*/
    @Override
    public void start(Stage primaryStage) {
        /*These are the primary layouts used in the GUI.*/
        BorderPane root = new BorderPane();
        VBox topSection = new VBox(0);
        HBox underTop = new HBox();
        GridPane grid = new GridPane();

        /*Setting up the very important toPlayer label, which is set to change the message when most
        buttons are pressed.*/
        toPlayer.setFont(Font.font("Courier New", 18));
        //toPlayer.setTextFill(Color.WHITE);
        toPlayer.setAlignment(Pos.CENTER);

        /*Creation of most of the buttons. So far, "Move" and "Attack Order" are dead buttons.*/
        Button endTurn = new Button("End Turn");
        Button viewParty = new Button("View party");
        Button viewEnemies = new Button("View enemies");
        move = new Button("Move");
        attackOrder = new Button("Attack");
        heal = new Button("Heal");
        endPieceTurn = new Button("End piece turn");

        HBox rightButton = new HBox(move, attackOrder, heal, endPieceTurn);
        rightButton.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(rightButton, Priority.ALWAYS);

        underTop.getChildren().addAll(endTurn, viewParty, viewEnemies, rightButton);
        underTop.setPadding(new Insets(2));

        topSection.getChildren().addAll(toPlayer, underTop);

        grid.setVgap(20);
        grid.setHgap(20);
        grid.setAlignment(Pos.CENTER);

        //C O M M U N I C A T I O N
        /*This runs the method gamePlaying() in this class. (See below)*/
        gamePlaying();

        /*This is the initial map creation. The loop is supposed to create buttons in a grid (using GridPane),
         with the rows and columns equal to the map dimensions.*/
        for(int row = 0; row < map.getDimensions(); row++) {
            for(int col = 0; col < map.getDimensions(); col++) {
                grid.setStyle("-fx-background-color: GREEN;");
                int place = conversion(row,col,map.getDimensions()); //This runs the method conversion() in this class. (See below)
                findAIPieces(place-1); //This runs the method findAIPieces() in this class. (See below)
                setMessage(place + ", " + this.submessage);
                Button boardButton = new Button(message);
                grid.add(boardButton, col, row);
                /*METHOD #1: DOESN'T WORK*/
/*                if (game.getTurnCounter() == 0 && (this.piecesPlaced < pieceLists.getPlayerParty().size())) { //This ensures that pieces can only be placed before the game begins, and there can't be more pieces placed than permitted.
                    if (map.getPiece(place) == 0 && place < (map.getDimensions() * map.getDimensions() - map.getDimensions() * 3)) { //This ensures that pieces can only be placed on empty spaces and not in the last 3 rows
                        Events boardButtonEvent = new Events(place, "board", getMap(), getGame(), getPieceLists());
                        boardButton.setOnAction(boardButtonEvent);
                    }
                }*/
                /*METHOD #2: WORKS*/
                boardButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (buttonAction(place)) {
                            boardButton.setText(message);
                        }
                    }
                });
            }
        }

        topSection.setPadding(new Insets(10, 30, 30, 30));
        topSection.setSpacing(5);

        root.setPadding(new Insets(30,30,30,30));
        root.setCenter(grid);
        root.setTop(topSection);
        //root.setStyle("-fx-background-color: BLACK;");

        //Scene scene = new Scene(root, 640, 480);
        Scene scene = new Scene(root, 1150, 600);
        primaryStage.setTitle("Planet Invaders");
        primaryStage.setScene(scene);
        primaryStage.show();


        //E V E N T H A N D L I N G
        /* This is for the viewParty button */
        Events viewPartyEvent = new Events("party", getToPlayer(),getPieceLists());
        viewParty.setOnAction(viewPartyEvent);
        /*This is for the viewEnemies button*/
        Events viewEnemyEvent = new Events("enemy", getToPlayer(),getPieceLists());
        viewEnemies.setOnAction(viewEnemyEvent);

        /*This is for the endTurn button.
        The last two lines are special:
        The first of the two sets an instance variable <loopRun> in Game to true, which is what allows play() to run.
        The second line actually runs play().*/
        endTurn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(game.getTurnCounter() < game.getTotalTurns() && game.getGameDone() == 1) {
                    game.setTurnCounter();
                    System.out.println("Turn from BP: " + game.getTurnCounter());
                    toPlayer.setText("== AI ATTACK AND SETUP == ");
                    game.play();
                    if (game.getGameDone() == 1) {
                        toPlayer.setText("HUMAN TURN... Click tile with desired piece to execute turn.");

                        //toPlayer.setText("Choose whether you'd like to MOVE, ATTACK, HEAL, or END PIECE TURN.")

                    }
                }

/*                  <FOR DEBUGGING THE LOOP>
                    if(game.getTurnCounter() < game.getTotalTurns() && game.getGameDone() == 1) {
                    System.out.println("Total turns: " + game.getTotalTurns());
                    game.setTurnCounter();
                    System.out.println("Turn from BP: " + game.getTurnCounter());
                    game.play();
                    System.out.println("Human goes...");
                }*/
            }
        });
    }

    /**This method is used to instantiate the objects necessary to start the game so that the game can be run through the same class as the GUI
     * (this serves to start the GUI and the game itself simultaneously)
     * All these commands are meant to set the game up, before the turn loop runs.*/
    public void gamePlaying() {
        this.game = startGame.startgame("one","five");
        this.pieceLists = game.getPieces();
        this.map = game.getMap();
        game.placeAIPieces();
        this.turn = new Turn(getMap(), getPieceLists());
        this.human = new HumanPlayerGUI(this.map, this.pieceLists);
        if (game.getTurnCounter() == 0) {
            toPlayer.setText("Place your pieces! ");
        }
    }

    /**This method is necessary because the row and column numbers of the grid don't correspond to anything.
     * Thus, this method converts the row and column numbers into one integer that increases for each button added.
     *@param rowIndex . This is the row the button is in.
     *@param columnIndex . This is the column the button is in.
     *@param dimensions . This is the dimensions of the map itself.*/
    public int conversion(int rowIndex, int columnIndex, int dimensions) {
        return dimensions - (dimensions - columnIndex - 1) + dimensions*rowIndex;
    }

    /**This goes hand-in-hand with placeAIPieces() in Game. This ensures that the enemy pieces are already placed when the map is created.
     *@param index . This is the converted number (using conversion()) of the location of the button.*/
    public void findAIPieces(int index) {
        ArrayList<ArrayList<Integer>> proxy = map.getMaparray();
        ArrayList<Integer> tilearray = proxy.get(index);
        if (tilearray.get(1) != 0) {
            submessage = "ENEMY " + tilearray.get(1);
        }
        else {
            submessage = "---------";
        }
    }

    public boolean buttonAction(int place) {
        boolean check = false;
        //For placing pieces
        if (game.getTurnCounter() == 0) {
            if (piecesPlaced < pieceLists.getPlayerParty().size()) { //This ensures that pieces can only be placed before the game begins, and there can't be more pieces placed than permitted.
                if (map.getPiece(place) == 0 && place < map.getDimensions() * map.getDimensions() - map.getDimensions() * 3) { //This ensures that pieces can only be placed on empty spaces and not in the last 3 rows
                    piecesPlaced++;
                    System.out.println("Place: " + map.getPiece(place));
                    map.setState(place, 1, piecesPlaced); //This sets the piece itself
                    submessage = "PIECE " + (piecesPlaced);
                    message = place + ", " + submessage;

                    //System.out.println(piecesPlaced);
                    check = true;
                    map.displayMap();
                }
                else {
                    toPlayer.setText("Pieces are not permitted to be placed here.");
                }
                }
            else {
                toPlayer.setText("No more pieces are permitted to be placed.");
            }
        }
        else {
            if (map.getPiece(place) == 0) {
                toPlayer.setText("Please select again.");
            }
            else {
                if (human.startTurn(place)) {
                    toPlayer.setText("Choose to MOVE, ATTACK, OR HEAL.");
                    Events moveEvent = new Events(place, "move", getMap(), getGame(), getPieceLists(), getHuman());
                    Events attackOrderEvent = new Events(place, "attack", getMap(), getGame(), getPieceLists(), getHuman());
                    Events healEvent = new Events("heal", getMap(), getGame(), getPieceLists(), getHuman());
                    Events endPieceTurnEvent = new Events("endPiece", getMap(), getGame(), getPieceLists(), getHuman());
                    move.setOnAction(moveEvent);
                    attackOrder.setOnAction(attackOrderEvent);
                    heal.setOnAction(healEvent);
                    endPieceTurn.setOnAction(endPieceTurnEvent);
                }
            }
        }
        return check;
    }

    public Label getToPlayer() {
        return toPlayer;
    }

    public String getSubmessage() {
        return new String(this.submessage);
    }

    public String getMessage() {
        return new String(this.message);
    }

    public int getPiecesPlaced() {
        return new Integer(this.piecesPlaced);
    }

    public void setToPlayer(Label lbl) {
        this.toPlayer = lbl;
    }

    public void setSubmessage(String submsg) {
        this.submessage = new String(submsg);
    }

    public void setMessage(String msg) {
        this.message = new String(msg);
    }

    public void setPiecesPlaced(int placed) {
        this.piecesPlaced = new Integer(placed);
    }

    public Map getMap() {
        return this.map;
    }
    public Game getGame() {
        return this.game;
    }
    public Pieces getPieceLists() {
        return this.pieceLists;
    }

    public HumanPlayerGUI getHuman() {
        return this.human;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
