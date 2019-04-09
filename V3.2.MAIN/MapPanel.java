/*Documented.*/

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

public class MapPanel {

	/** Instance variables.
	* These are all the scenes that may be shown in this GUI.*/
	Stage substage2 = new Stage();
	GridPane map = new GridPane();
	Scene mapScene, IslandScene1, IslandScene2, IslandScene3, IslandScene4, IslandScene5;

	/** Constructor.
	 * Note that it is a constructor, rather than running as a start(). This is to allow other GUI's to instantiate MapPanel,
	 * which allows MapPanel to pop up when instantiated.
	 * This method creates the GUI itself and allows instantiation of the objects required for the game to run.
	 * There is also event-handling solely used to initiate BoardPanel appropriately.
	 * */
	MapPanel() {

		map.setStyle("-fx-background-color: BLACK");       
		map.setPadding(new Insets(50));	       
		map.setHgap(50);  
		map.setVgap(10);

		/*Creates images necessary for mapScene.*/
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

	    /*Changes the buttons into images.*/
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
		
		/*Binds buttons to pane so you can resize the window.*/
		binding(w1Butt, 100, 100);
		binding(w2Butt, 100, 100);
		binding(w3Butt, 100, 100);
		binding(w4Butt, 100, 100);
		binding(w5Butt, 100, 100);

		mapScene = new Scene(map, 780, 560);

		/*Aligning the buttons.*/
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
	    
	    /*Switching to the appropriate island scene when clicked.*/
	    w1Butt.setOnAction(e -> substage2.setScene(IslandScene1));
	    w2Butt.setOnAction(e -> substage2.setScene(IslandScene2));
	    w3Butt.setOnAction(e -> substage2.setScene(IslandScene3));
	    w4Butt.setOnAction(e -> substage2.setScene(IslandScene4));
	    w5Butt.setOnAction(e -> substage2.setScene(IslandScene5));

	    /*World 1.
		* Creating buttons, instantiating BoardPanel appropriately, adding the buttons to the scene.*/
		StackPane island1 = new StackPane();

		FlowPane i1Buttons = new FlowPane();
		Button play1 = createButtons(165, 229);
		Button play2 = createButtons(140, 346);
		Button back1 = createBackButton();
		play1.setOnAction(e -> new BoardPanel("1","1"));
		play2.setOnAction(e -> new BoardPanel("1","1"));
		back1.setOnAction(e -> substage2.setScene(mapScene));
		i1Buttons.getChildren().addAll(back1, play1, play2);

		/*Changes image of background to a map.*/
		Image r1Image = new Image("Images/r1.png");
		ImageView r1View = new ImageView(r1Image);
		bindingLevel(r1View);
		island1.getChildren().addAll(r1View, i1Buttons);

		/*World 2.
		 * Creating buttons, instantiating BoardPanel appropriately, adding the buttons to the scene.*/
		StackPane island2 = new StackPane();

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

		/*Changes image of background to a map.*/
		Image r2Image = new Image("Images/r2.png");
		ImageView r2View = new ImageView(r2Image);
		bindingLevel(r2View);
		island2.getChildren().addAll(r2View, i2Buttons);

		/*World 3.
		 * Creating buttons, instantiating BoardPanel appropriately, adding the buttons to the scene.*/
		StackPane island3 = new StackPane();

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

		/*Changes image of background to a map.*/
		Image r3Image = new Image("Images/r3.png");
		ImageView r3View = new ImageView(r3Image);
		bindingLevel(r3View);
		island3.getChildren().addAll(r3View, i3Buttons);

		/*World 4.
		 * Creating buttons, instantiating BoardPanel appropriately, adding the buttons to the scene.*/
		StackPane island4 = new StackPane();

		FlowPane i4Buttons = new FlowPane();
		Button back4 = createBackButton();
		Button play9 = createButtons(110, 250);
		Button play10 = createButtons(80, 110);
		play9.setOnAction(e -> new BoardPanel("1","1"));
		play10.setOnAction(e -> new BoardPanel("1","1"));
		back4.setOnAction(e -> substage2.setScene(mapScene));
		i4Buttons.getChildren().addAll(back4, play9, play10);

		/*Changes image of background to a map.*/
		Image r4Image = new Image("Images/r4.png");
		ImageView r4View = new ImageView(r4Image);
		bindingLevel(r4View);
		island4.getChildren().addAll(r4View, i4Buttons);

		/*World 5.
		 * Creating buttons, instantiating BoardPanel appropriately, adding the buttons to the scene.*/
		StackPane island5 = new StackPane();

		FlowPane i5Buttons = new FlowPane();
		Button back5 = createBackButton();
		Button play11 = createButtons(160,227);
		Button play12 = createButtons(140,340);
		play11.setOnAction(e -> new BoardPanel("1","1"));
		play12.setOnAction(e -> new BoardPanel("1","1"));
		back5.setOnAction(e -> substage2.setScene(mapScene));
		i5Buttons.getChildren().addAll(back5, play11, play12);

		/*Changes image of background to a map.*/
		Image r5Image = new Image("Images/r1.png");
		ImageView r5View = new ImageView(r5Image);
		bindingLevel(r5View);
		island5.getChildren().addAll(r5View, i5Buttons);

		/*Initializes the island scenes.*/
	    IslandScene1 = new Scene(island1, 640, 480);
	    IslandScene2 = new Scene(island2, 640, 480);
	    IslandScene3 = new Scene(island3, 640, 480);
	    IslandScene4 = new Scene(island4, 640, 480);
	    IslandScene5 = new Scene(island5, 640, 480);

		substage2.setScene(mapScene);
		substage2.setTitle("Fantasy Ranch");
		substage2.show();
	}

	/** Used to create the generic "Back" button.
	 * @return the "Back" button. */
	public Button createBackButton() {
		Button backButton = new Button("Back");
		backButton.setTranslateX(5);
		backButton.setTranslateY(5);
		return backButton;
	}

	/** Used to create the generic "Play" button.
	 * @param x . This is the x-coordinate of where the button should be.
	 * @param y . This is the y-coordinate of where the button should be.
	 * @return the "Play" button. */
	public Button createButtons(int x, int y) {
		Button playButton = new Button("Play");
		playButton.setTranslateX(x);
		playButton.setTranslateY(y);
		return playButton;
	}

	/** Used to create the bind buttons to the pane so you can resize the window.
	 * @param x . This is the x-coordinate of where the button should be.
	 * @param y . This is the y-coordinate of where the button should be. */
	public void binding(Button button, int x, int y) {
		button.prefWidthProperty().bind(map.widthProperty());
		button.prefHeightProperty().bind(map.heightProperty());
		button.setMinWidth(x);
		button.setMinHeight(y);
	}

	/** Used to create the bind buttons to the pane so you can resize the window.
	 * @param x . This is the x-coordinate of where the button should be.
	 * @param y . This is the y-coordinate of where the button should be. */
	public void bindingLevel(ImageView image) {
		image.fitWidthProperty().bind(substage2.widthProperty());
		image.fitHeightProperty().bind(substage2.heightProperty());
	}

}