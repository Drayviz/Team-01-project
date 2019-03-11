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
    private Map map;
    private Pieces pieceLists = new Pieces();
    private MetaGame game;
    private Label toPlayer = new Label("Welcome!");

	@Override
    public void initialize(Stage primaryStage) {
        
		BorderPane root = new BorderPane(); //BorderPane is the main layout
        VBox topSection = new VBox(0);
        HBox underTop = new HBox();
        GridPane grid = new GridPane();
        
        toPlayer.setFont(Font.font("Courier New", 18));
        //toPlayer.setTextFill(Color.WHITE);
        toPlayer.setAlignment(Pos.CENTER);

        Button endTurn = new Button("End Turn");
        Button undoMove = new Button("Undo Move");
        Button viewParty = new Button("View party");
        Button viewEnemies = new Button("View enemies");
        Button attackOrder = new Button("Attack Order");

        HBox rightButton = new HBox(attackOrder);
        rightButton.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(rightButton, Priority.ALWAYS);

        underTop.getChildren().addAll(endTurn, undoMove, viewParty, viewEnemies, rightButton);
        underTop.setPadding(new Insets(2));

        topSection.getChildren().addAll(toPlayer, underTop);

        grid.setVgap(20);
        grid.setHgap(20);
        grid.setAlignment(Pos.CENTER);

/*         int count = 0;
        int dimensions = map.getDimensions();
        ArrayList<ArrayList<Integer>> proxyMap = map.getMaparray();
        for(int row = 0; row < dimensions; row++) {
            for(int col = 0; col < dimensions; col++) {
                count++;
                grid.setStyle("-fx-background-color: GREEN;");
                String submessage = "";
                ArrayList<Integer> tilearray = proxyMap.get(count - 1);
                if(tilearray.get(0) == 1 && tilearray.get(1) == 0)
                    {
                    submessage = "EMPTY";
                    }
                else
                    {
                    submessage = tilearray.get(1)+"";
                    }
                String message = count + ", " + submessage;
                grid.add(new Button(message), col, row);
            	
            }
        } */

        int count = 0;
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                count++;
                grid.setStyle("-fx-background-color: GREEN;");
                String submessage = "EMPTY";
                String message = count + ", " + submessage;
                grid.add(new Button(message), col, row);
            	
            }
        }
        
        topSection.setPadding(new Insets(10, 30, 30, 30));
        topSection.setSpacing(5);
        
        root.setPadding(new Insets(30,30,30,30));
        
        root.setCenter(grid);     
		root.setTop(topSection);
        //root.setStyle("-fx-background-color: BLACK;");

		Scene scene = new Scene(root, 640, 480);
	    primaryStage.setTitle("Planet Invaders");
	    primaryStage.setScene(scene);
        primaryStage.show();

        //EVENTHANDLING
        /* This is for the viewParty button */
        viewParty.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String forDisplay = "Party: ";
                for(Entity e:pieceLists.getPlayerParty()) {
                    forDisplay = forDisplay + e.getName() + " Atk: " + e.getAtk() + " Def: " + e.getDef() + " Movement: " + e.getMovement() + ", ";
                }
                toPlayer.setText(forDisplay);
            }
        });
    }

    public void gamePlaying(Stage primaryStage) {
        this.game = new MetaGame();
        this.game.startgame("five");
        
    }

		
	public static void main(String[] args) {
        launch(args);	
	}
}
/*                 ArrayList<String> hey = new ArrayList<String>();
                hey.add("a");
                hey.add("b");
                for (String a:hey) {
                    forDisplay = forDisplay + a;
                } */
/*                 ArrayList<Entity> f = pieceLists.getPlayerParty();
                Entity a = f.get(0);
                String b = a.getName(); 
                toPlayer.setText(b);*/