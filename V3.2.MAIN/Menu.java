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
	private Scene menuScene, conScene, mapEditorScene;
	
	@Override
	public void start(Stage primaryStage) {
		
		GridPane menu = new GridPane();
		menu.setStyle("-fx-background-color: BLACK");
		
		GridPane editor = new GridPane();
		GridPane grid = new GridPane();
		
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
	    mapEditorScene = new Scene(editor, 1080, 800);
	    
	    grass = new Button("Grass");
	    snow = new Button("Snow");
	    wasteland = new Button("Wasteland");
	    volcano = new Button("Volcano");
	    desert = new Button("Desert");
	    mountain = new Button("Mountain");
	    pit = new Button("Pit");
	    water = new Button("Water");
	    
	    VBox rightButts = new VBox(grass, snow, wasteland, volcano, desert, mountain, pit, water);
		editor.getChildren().addAll(rightButts);
		
		grass.prefWidthProperty().bind(editor.widthProperty());
		grass.prefHeightProperty().bind(editor.heightProperty());
		grass.setMaxWidth(150);
		grass.setMaxHeight(150);
	    
		snow.prefWidthProperty().bind(editor.widthProperty());
		snow.prefHeightProperty().bind(editor.heightProperty());
		snow.setMaxWidth(150);
		snow.setMaxHeight(150);
	    
		wasteland.prefWidthProperty().bind(editor.widthProperty());
		wasteland.prefHeightProperty().bind(editor.heightProperty());
		wasteland.setMaxWidth(150);
		wasteland.setMaxHeight(150);
	    
		volcano.prefWidthProperty().bind(editor.widthProperty());
		volcano.prefHeightProperty().bind(editor.heightProperty());
		volcano.setMaxWidth(150);
		volcano.setMaxHeight(150);
	    
		desert.prefWidthProperty().bind(editor.widthProperty());
		desert.prefHeightProperty().bind(editor.heightProperty());
		desert.setMaxWidth(150);
		desert.setMaxHeight(150);
	    
		mountain.prefWidthProperty().bind(editor.widthProperty());
		mountain.prefHeightProperty().bind(editor.heightProperty());
		mountain.setMaxWidth(150);
		mountain.setMaxHeight(150);
	    
		pit.prefWidthProperty().bind(editor.widthProperty());
		pit.prefHeightProperty().bind(editor.heightProperty());
		pit.setMaxWidth(150);
		pit.setMaxHeight(150);
	    
		water.prefWidthProperty().bind(editor.widthProperty());
		water.prefHeightProperty().bind(editor.heightProperty());
		water.setMaxWidth(150);
		water.setMaxHeight(150);
		
		editor.setStyle("-fx-background-color: BLACK");
		editor.setPadding(new Insets(50));	       
		editor.setHgap(30);  
		editor.setVgap(30);
		rightButts.setSpacing(10);
	    editor.setAlignment(Pos.CENTER_RIGHT);
	    
	    
	    primaryStage.setScene(menuScene);
		primaryStage.setTitle("Fantasy Ranch");
		primaryStage.show();
		
		// EVENT HANDLING
		
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
            	MetaGame x = new MetaGame();
            	primaryStage.close();
            	x.mapEditor();
            	
              }
        });
	}
	
	
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
