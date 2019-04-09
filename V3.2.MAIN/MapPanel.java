import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

public class MapPanel {

	Scene mapScene, IslandScene1, IslandScene2, IslandScene3, IslandScene4, IslandScene5;

	//MapPanel() {}

	MapPanel() {

		Stage substage2 = new Stage();
		GridPane map = new GridPane();
		map.setStyle("-fx-background-color: BLACK");       
		map.setPadding(new Insets(50));	       
		map.setHgap(50);  
		map.setVgap(10);
		
		// changes button to image

	    Image w1Image = new Image("Images/w1.png");
	    ImageView w1View = new ImageView(w1Image);

	    Image w2Image = new Image("Images/w2.png");
	    ImageView w2View = new ImageView(w2Image);

	    Image w3Image = new Image("Images/w3.png");
	    ImageView w3View = new ImageView(w3Image);

	    Image w4Image = new Image("Images/w4.png");
	    ImageView w4View = new ImageView(w4Image);

	    Image w5Image = new Image("Images/w1.png");
	    ImageView w5View = new ImageView(w5Image);
		
		Button w1Butt = new Button("",w1View);
			w1Butt.setStyle("-fx-background-color: TRANSPARENT");
		Button w2Butt = new Button("", w2View);
			w2Butt.setStyle("-fx-background-color: TRANSPARENT");
		Button w3Butt = new Button("", w3View);
			w3Butt.setStyle("-fx-background-color: TRANSPARENT");
		Button w4Butt = new Button("", w4View);
			w4Butt.setStyle("-fx-background-color: TRANSPARENT");
		Button w5Butt = new Button("", w5View);
			w5Butt.setStyle("-fx-background-color: TRANSPARENT");
		
		// Binds buttons to pane so you can resize the window.
		w1Butt.prefWidthProperty().bind(map.widthProperty());
		w1Butt.prefHeightProperty().bind(map.heightProperty());
		w1Butt.setMinWidth(100);
		w1Butt.setMinHeight(100);
		
		w2Butt.prefWidthProperty().bind(map.widthProperty());
		w2Butt.prefHeightProperty().bind(map.heightProperty());
		w2Butt.setMinWidth(100);
		w2Butt.setMinHeight(100);
		
		w3Butt.prefWidthProperty().bind(map.widthProperty());
		w3Butt.prefHeightProperty().bind(map.heightProperty());
		w3Butt.setMinWidth(100);
		w3Butt.setMinHeight(100);
		
		w4Butt.prefWidthProperty().bind(map.widthProperty());
		w4Butt.prefHeightProperty().bind(map.heightProperty());
		w4Butt.setMinWidth(100);
		w4Butt.setMinHeight(100);
		
		w5Butt.prefWidthProperty().bind(map.widthProperty());
		w5Butt.prefHeightProperty().bind(map.heightProperty());
		w5Butt.setMinWidth(100);
		w5Butt.setMinHeight(100);
		
				
		mapScene = new Scene(map, 780, 560);
		
		//Alignments for the buttons
		GridPane.setHalignment(w3Butt, HPos.RIGHT);
	    map.add(w3Butt, 1, 4);
	    
	    GridPane.setHalignment(w5Butt, HPos.RIGHT);
	    map.add(w5Butt, 3, 2);
	    
	    GridPane.setHalignment(w4Butt, HPos.RIGHT);
	    map.add(w4Butt, 2, 1);
	    
	    GridPane.setHalignment(w1Butt, HPos.RIGHT);
	    map.add(w1Butt, 0, 1);
	    
	    GridPane.setHalignment(w2Butt, HPos.RIGHT);
	    map.add(w2Butt, 1, 2);
	    
	    // Switches to each island scene
	    
	    w1Butt.setOnAction(e -> substage2.setScene(IslandScene1));
	    w2Butt.setOnAction(e -> substage2.setScene(IslandScene2));
	    w3Butt.setOnAction(e -> substage2.setScene(IslandScene3));
	    w4Butt.setOnAction(e -> substage2.setScene(IslandScene4));
	    w5Butt.setOnAction(e -> substage2.setScene(IslandScene5));
	    	    
	    // GridPane for the 5 islands
	    
	    StackPane island1 = new StackPane();
		StackPane island2 = new StackPane();
		StackPane island3 = new StackPane();
		StackPane island4 = new StackPane();
		StackPane island5 = new StackPane();

		//Region 1

		//Play buttons for world 1

		FlowPane i1Buttons = new FlowPane();
		Button play1 = createButtons(165, 229);
		Button play2 = createButtons(140, 346);
		Button back1 = createBackButton();
		play1.setOnAction(e -> new BoardPanel("1","1"));
		play2.setOnAction(e -> new BoardPanel("1","1"));
		back1.setOnAction(e -> substage2.setScene(mapScene));
		i1Buttons.getChildren().addAll(back1, play1, play2);

		//changes image of background to a map
		Image r1Image = new Image("Images/r1.png");
		ImageView r1View = new ImageView(r1Image);
		r1View.fitWidthProperty().bind(substage2.widthProperty());
		r1View.fitHeightProperty().bind(substage2.heightProperty());
		island1.getChildren().addAll(r1View, i1Buttons);

		//Region 2

		FlowPane i2Buttons = new FlowPane();
		Button play3 = createButtons(96, 270);
		Button play4 = createButtons(297, 300);
		Button play5 = createButtons(275, 387);
		Button back2 = createBackButton();
		play3.setOnAction(e -> new BoardPanel("1","1"));
		play4.setOnAction(e -> new BoardPanel("1","1"));
		play5.setOnAction(e -> new BoardPanel("1","1"));
		back2.setOnAction(e -> substage2.setScene(mapScene));
		i2Buttons.getChildren().addAll(back2, play3, play4, play5);

		//changes image of background to a map
		Image r2Image = new Image("Images/r2.png");
		ImageView r2View = new ImageView(r2Image);
		r2View.fitWidthProperty().bind(substage2.widthProperty());
		r2View.fitHeightProperty().bind(substage2.heightProperty());
		island2.getChildren().addAll(r2View, i2Buttons);

		//Region 3

		//Play buttons for world 3
		FlowPane i3Buttons = new FlowPane();
		Button play6 = createButtons(275, 138);
		Button play7 = createButtons(240, 265);
		Button play8 = createButtons(365, 272);
		Button back3 = createBackButton();
		play6.setOnAction(e -> new BoardPanel("1","1"));
		play7.setOnAction(e -> new BoardPanel("1","1"));
		play8.setOnAction(e -> new BoardPanel("1","1"));
		back3.setOnAction(e -> substage2.setScene(mapScene));
		i3Buttons.getChildren().addAll(back3, play6, play7, play8);

		//changes image of background to a map
		Image r3Image = new Image("Images/r3.png");
		ImageView r3View = new ImageView(r3Image);
		r3View.fitWidthProperty().bind(substage2.widthProperty());
		r3View.fitHeightProperty().bind(substage2.heightProperty());
		island3.getChildren().addAll(r3View, i3Buttons);

		//Region 4

		//Play buttons for world 4
		FlowPane i4Buttons = new FlowPane();
		Button back4 = createBackButton();
		Button play9 = createButtons(110, 250);
		Button play10 = createButtons(80, 110);
		play9.setOnAction(e -> new BoardPanel("1","1"));
		play10.setOnAction(e -> new BoardPanel("1","1"));
		back4.setOnAction(e -> substage2.setScene(mapScene));
		i4Buttons.getChildren().addAll(back4, play9, play10);

		//changes image of background to a map
		Image r4Image = new Image("Images/r4.png");
		ImageView r4View = new ImageView(r4Image);
		r4View.fitWidthProperty().bind(substage2.widthProperty());
		r4View.fitHeightProperty().bind(substage2.heightProperty());
		island4.getChildren().addAll(r4View, i4Buttons);

		//Region 5

		//Play buttons for world 5
		
		FlowPane i5Buttons = new FlowPane();
		Button back5 = createBackButton();
		Button play11 = createButtons(160,227);
		Button play12 = createButtons(140,340);
		play11.setOnAction(e -> new BoardPanel("1","1"));
		play12.setOnAction(e -> new BoardPanel("1","1"));
		back5.setOnAction(e -> substage2.setScene(mapScene));
		i5Buttons.getChildren().addAll(back5, play11, play12);

		//changes image of background to a map
		Image r5Image = new Image("Images/r1.png");
		ImageView r5View = new ImageView(r5Image);
		r5View.fitWidthProperty().bind(substage2.widthProperty());
		r5View.fitHeightProperty().bind(substage2.heightProperty());
		island5.getChildren().addAll(r5View, i5Buttons);

	    // initializes the size ration of scene
	    
	    IslandScene1 = new Scene(island1, 640, 480);
	    IslandScene2 = new Scene(island2, 640, 480);
	    IslandScene3 = new Scene(island3, 640, 480);
	    IslandScene4 = new Scene(island4, 640, 480);
	    IslandScene5 = new Scene(island5, 640, 480);

		substage2.setScene(mapScene);
		substage2.setTitle("Fantasy Ranch");
		substage2.show();
	}

	public Button createBackButton() {
		Button backButton = new Button("Back");
		backButton.setTranslateX(5);
		backButton.setTranslateY(5);
		return backButton;
	}

	public Button createButtons(int x, int y) {
		Button playButton = new Button("Play");
		playButton.setTranslateX(x);
		playButton.setTranslateY(y);
		return playButton;
	}

}