import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Scanner;

public class Menu extends Application{
	
	private Button NGText;
	private Button NGGui;
	private Button cont;
	private Button quit;
	private Button grass;
	private Button snow;
	private Button wasteland;
	private Button volcano;
	private Button desert;
	private Button mountain;
	private Button pit;
	private Button water;
	private Button gridButt;
	private Button com1;
	private Button com2;
	private Button com3;
	private Button hq;
	private Button power;
	private GridPane menu;
	private BorderPane editor;
	private GridPane grid;
	private Scene menuScene, mapEditorScene;
	
	private Image tile = new Image("file:Images/tile.png"); //
	private Image com1Tile = new Image("file:Images/1.png"); //
	private Image com2Tile = new Image("file:Images/2.png"); //
	private Image com3Tile = new Image("file:Images/3.png"); //
	private Image hqTile = new Image("file:Images/hq.png"); //
	private Image lavaTile = new Image("file:Images/lava.png"); //
	private Image mountTile = new Image("file:Images/mountain.png"); //
	private Image powTile = new Image("file:Images/power.png"); //
	private Image sandTile = new Image("file:Images/sand.png"); //
	private Image snowTile = new Image("file:Images/snow.png"); //
	private Image volTile = new Image("file:Images/volcano.png"); //
	private Image wasteTile = new Image("file:Images/wasteland.png"); //
	private Image waterTile = new Image("file:Images/water.png"); //
	private Image pitTile = new Image("file:Images/pit.png"); //
   
	
	@Override
	public void start(Stage primaryStage) {
		
		menu = new GridPane();
		menu.setStyle("-fx-background-color: BLACK");
		
		editor = new BorderPane();
		grid = new GridPane();
		
		// images for the buttons
		Image Image1 = new Image("file:Images/NGT.png");
	    ImageView View1 = new ImageView(Image1);

	    Image Image2 = new Image("file:Images/NGG.png");
	    ImageView View2 = new ImageView(Image2);

	    Image Image3 = new Image("file:Images/Continue.png");
	    ImageView View3 = new ImageView(Image3);

	    Image Image4 = new Image("file:Images/quit.png");
	    ImageView View4 = new ImageView(Image4);
	    
	    //buttons are turned transparent
	    NGText = new Button("",View1);
	    NGText.setStyle("-fx-background-color: TRANSPARENT");
		NGGui = new Button("",View2);
		NGGui.setStyle("-fx-background-color: TRANSPARENT");
		cont = new Button("", View3);
		cont.setStyle("-fx-background-color: TRANSPARENT");
		quit = new Button("", View4);
		quit.setStyle("-fx-background-color: TRANSPARENT");

		Button backButton = new Button("Back");
		backButton.setTranslateX(0);
		backButton.setTranslateY(0);
		
		VBox leftButts = new VBox(NGText, NGGui, cont, quit);
		menu.getChildren().addAll(leftButts);
	    
		//binds button to window
	    NGText.prefWidthProperty().bind(menu.widthProperty());
	    NGText.prefHeightProperty().bind(menu.heightProperty());
	    NGText.setMaxWidth(150);
	    NGText.setMaxHeight(25);
		
	    NGGui.prefWidthProperty().bind(menu.widthProperty());
	    NGGui.prefHeightProperty().bind(menu.heightProperty());
	    NGGui.setMaxWidth(150);
	    NGGui.setMaxHeight(25);
		
	    cont.prefWidthProperty().bind(menu.widthProperty());
	    cont.prefHeightProperty().bind(menu.heightProperty());
	    cont.setMaxWidth(150);
	    cont.setMaxHeight(25);
		
	    quit.prefWidthProperty().bind(menu.widthProperty());
	    quit.prefHeightProperty().bind(menu.heightProperty());
	    quit.setMaxWidth(150);
	    quit.setMaxHeight(25);
	    
	    leftButts.setSpacing(5);
	    menu.setAlignment(Pos.CENTER_LEFT);

	    menuScene = new Scene(menu, 780, 560);
	    mapEditorScene = new Scene(editor, 1200, 800);
	    
	    grass = new Button("");
	    	grass.setGraphic(new ImageView(tile));
	    	grass.setStyle("-fx-background-color: TRANSPARENT");
	    snow = new Button("");
	    	snow.setGraphic(new ImageView(snowTile));
	    	snow.setStyle("-fx-background-color: TRANSPARENT");
	    wasteland = new Button("");
	    	wasteland.setGraphic(new ImageView(wasteTile));
	    	wasteland.setStyle("-fx-background-color: TRANSPARENT");
	    volcano = new Button("");
	    	volcano.setGraphic(new ImageView(volTile));
	    	volcano.setStyle("-fx-background-color: TRANSPARENT");
	    desert = new Button("");
	    	desert.setGraphic(new ImageView(sandTile));
	    	desert.setStyle("-fx-background-color: TRANSPARENT");
	    mountain = new Button("");
	    	mountain.setGraphic(new ImageView(mountTile));
	    	mountain.setStyle("-fx-background-color: TRANSPARENT");
	    pit = new Button("");
	    	pit.setGraphic(new ImageView(pitTile));
	    	pit.setStyle("-fx-background-color: TRANSPARENT");
	    water = new Button("");
	    	water.setGraphic(new ImageView(waterTile));
	    	water.setStyle("-fx-background-color: TRANSPARENT");
	    com1 = new Button("");
	    	com1.setGraphic(new ImageView(com1Tile));
	    	com1.setStyle("-fx-background-color: TRANSPARENT");
	    com2 = new Button("");
	    	com2.setGraphic(new ImageView(com2Tile));
	    	com2.setStyle("-fx-background-color: TRANSPARENT");
	    com3 = new Button("");
	    	com3.setGraphic(new ImageView(com3Tile));
	    	com3.setStyle("-fx-background-color: TRANSPARENT");
	    hq = new Button("");
	    	hq.setGraphic(new ImageView(hqTile));
	    	hq.setStyle("-fx-background-color: TRANSPARENT");
	    power = new Button("");
	    	power.setGraphic(new ImageView(powTile));
	    	power.setStyle("-fx-background-color: TRANSPARENT");
	    
	    VBox rightButts = new VBox(grass, snow, wasteland, volcano, desert, mountain);
		editor.setRight(rightButts);
		editor.setTop(backButton);
		
		VBox rightButts2 = new VBox(pit, water, com1, com2, com3, hq, power);
		editor.setLeft(rightButts2);
		editor.setTop(backButton);
		
		grass.prefWidthProperty().bind(editor.widthProperty());
		grass.prefHeightProperty().bind(editor.heightProperty());
		grass.setMaxWidth(100);
		grass.setMaxHeight(100);
	    
		snow.prefWidthProperty().bind(editor.widthProperty());
		snow.prefHeightProperty().bind(editor.heightProperty());
		snow.setMaxWidth(100);
		snow.setMaxHeight(100);
	    
		wasteland.prefWidthProperty().bind(editor.widthProperty());
		wasteland.prefHeightProperty().bind(editor.heightProperty());
		wasteland.setMaxWidth(100);
		wasteland.setMaxHeight(100);
	    
		volcano.prefWidthProperty().bind(editor.widthProperty());
		volcano.prefHeightProperty().bind(editor.heightProperty());
		volcano.setMaxWidth(100);
		volcano.setMaxHeight(100);
	    
		desert.prefWidthProperty().bind(editor.widthProperty());
		desert.prefHeightProperty().bind(editor.heightProperty());
		desert.setMaxWidth(100);
		desert.setMaxHeight(100);
	    
		mountain.prefWidthProperty().bind(editor.widthProperty());
		mountain.prefHeightProperty().bind(editor.heightProperty());
		mountain.setMaxWidth(100);
		mountain.setMaxHeight(100);
	    
		pit.prefWidthProperty().bind(editor.widthProperty());
		pit.prefHeightProperty().bind(editor.heightProperty());
		pit.setMaxWidth(100);
		pit.setMaxHeight(100);
	    
		water.prefWidthProperty().bind(editor.widthProperty());
		water.prefHeightProperty().bind(editor.heightProperty());
		water.setMaxWidth(100);
		water.setMaxHeight(100);
		
		com1.prefWidthProperty().bind(editor.widthProperty());
		com1.prefHeightProperty().bind(editor.heightProperty());
		com1.setMaxWidth(100);
		com1.setMaxHeight(100);
		
		com2.prefWidthProperty().bind(editor.widthProperty());
		com2.prefHeightProperty().bind(editor.heightProperty());
		com2.setMaxWidth(100);
		com2.setMaxHeight(100);

		com3.prefWidthProperty().bind(editor.widthProperty());
		com3.prefHeightProperty().bind(editor.heightProperty());
		com3.setMaxWidth(100);
		com3.setMaxHeight(100);
		
		hq.prefWidthProperty().bind(editor.widthProperty());
		hq.prefHeightProperty().bind(editor.heightProperty());
		hq.setMaxWidth(100);
		hq.setMaxHeight(100);
		
		power.prefWidthProperty().bind(editor.widthProperty());
		power.prefHeightProperty().bind(editor.heightProperty());
		power.setMaxWidth(100);
		power.setMaxHeight(100);
		
		
		editor.setStyle("-fx-background-color: BLACK");
		editor.setPadding(new Insets(50));
		rightButts.setSpacing(10);
	    
	    
	    primaryStage.setScene(menuScene);
		primaryStage.setTitle("Fantasy Ranch");
		primaryStage.show();
		
		// EVENT HANDLING
		
		tileGrid();
		NGGui.setOnAction(e -> primaryStage.setScene(mapEditorScene));
		
		quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	System.exit(0);
              }
        });
		
		NGText.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
				MetaGame a = new MetaGame();
            	primaryStage.close();
				Scanner b = new Scanner(System.in);
				a.mapEditor_Editor();
				System.out.println("========enter world and level to play===========");
				String fileinput = b.nextLine();
				String[] Value = fileinput.split(" ");
				fileinput = Value[0];
				String fileinput2 = Value[1];
				a.startGame(fileinput,fileinput2);
              }
        });
		cont.setOnAction(e -> new MapPanel());
		backButton.setOnAction(e -> primaryStage.setScene(menuScene));
	}
	
	public void tileGrid() {
		gridButt = new Button("");
		
		grid.setVgap(20);
        grid.setHgap(20);
        grid.setAlignment(Pos.CENTER);
        editor.setCenter(grid);
        
        
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {                
                gridButt = new Button("");
                gridButt.setStyle("-fx-background-color: transparent;");
                
                gridButt.prefWidthProperty().bind(editor.widthProperty());
                gridButt.prefHeightProperty().bind(editor.heightProperty());
                gridButt.setMaxWidth(75);
                gridButt.setMaxHeight(75);
                
                gridButt.setGraphic(new ImageView(tile));
        		
                grid.add(gridButt, col, row);
            }
        }
	}
					
	public static void main(String[] args) {
		launch(args);
	}
}
