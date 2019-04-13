/*DONE*/

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

/*package gui;
* import text.*;*/

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
		bindingMenu(NGText);
		bindingMenu(NGGui);
		bindingMenu(cont);
		bindingMenu(quit);
	    
	    leftButts.setSpacing(5);
	    menu.setAlignment(Pos.CENTER_LEFT);

	    menuScene = new Scene(menu, 780, 560);
	    mapEditorScene = new Scene(editor, 1200, 800);
	    
	    grass = new Button("");
			setImages(grass, tile);
	    snow = new Button("");
			setImages(snow, snowTile);
	    wasteland = new Button("");
			setImages(wasteland, wasteTile);
	    volcano = new Button("");
	    	setImages(volcano, volTile);
	    desert = new Button("");
	    	setImages(desert, sandTile);
	    mountain = new Button("");
	    	setImages(mountain, mountTile);
	    pit = new Button("");
			setImages(pit, pitTile);
	    water = new Button("");
			setImages(water, waterTile);
	    com1 = new Button("");
			setImages(com1, com1Tile);
	    com2 = new Button("");
	    	setImages(com2, com2Tile);
	    com3 = new Button("");
			setImages(com3, com3Tile);
	    hq = new Button("");
			setImages(hq, hqTile);
	    power = new Button("");
			setImages(power, powTile);
	    
	    VBox rightButts = new VBox(grass, snow, wasteland, volcano, desert, mountain);
		editor.setRight(rightButts);
		editor.setTop(backButton);
		
		VBox rightButts2 = new VBox(pit, water, com1, com2, com3, hq, power);
		editor.setLeft(rightButts2);
		editor.setTop(backButton);

		bindingEditor(grass);
		bindingEditor(snow);
		bindingEditor(wasteland);
		bindingEditor(volcano);
		bindingEditor(desert);
		bindingEditor(mountain);
		bindingEditor(pit);
		bindingEditor(water);
		bindingEditor(com1);
		bindingEditor(com2);
		bindingEditor(com3);
		bindingEditor(hq);
		bindingEditor(power);

		editor.setStyle("-fx-background-color: BLACK");
		editor.setPadding(new Insets(50));
		rightButts.setSpacing(10);

	    primaryStage.setScene(menuScene);
		primaryStage.setTitle("Space Ranch");
		primaryStage.show();
		
		/** EVENT HANDLING */
		
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

	/**
	 * To create the tile grid.
	 * */
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

	/** To avoid code duplication. When called, sets the image to the button and sets the button as transparent.
	 * @param button . The button to be manipulated.*/
	public void setImages(Button button, Image image) {
		button.setGraphic(new ImageView(image));
		button.setStyle("-fx-background-color: TRANSPARENT");
	}

	/** To avoid code duplication. When called, binds the button to the scene.
	 * @param button . The button to be manipulated.
	 * @param image . The image meant to be on the button.*/
	public void bindingEditor(Button button) {
		button.prefWidthProperty().bind(editor.widthProperty());
		button.prefHeightProperty().bind(editor.heightProperty());
		button.setMaxWidth(100);
		button.setMaxHeight(100);
	}

	/** To avoid code duplication. When called, binds the button to the scene.
	 * @param button . The button to be manipulated.*/
	public void bindingMenu(Button button) {
		button.prefWidthProperty().bind(menu.widthProperty());
		button.prefHeightProperty().bind(menu.heightProperty());
		button.setMaxWidth(150);
		button.setMaxHeight(25);
	}
					
	public static void main(String[] args) {
		launch(args);
	}
}
