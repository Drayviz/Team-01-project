/*DONE.*/

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

/*import text.*;
package gui;*/


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
/*		Button play1 = createButtons(165, 229);
		Button play2 = createButtons(140, 346);*/ //REMOVE*
		Button play11 = createButtons(6, 170, 1, 1);
		Button play12 = createButtons(170, 20, 1, 2);
		Button play13 = createButtons(95, 237, 1, 3);
		Button play14 = createButtons(148, 237, 1, 4);
		Button play15 = createButtons(180, 220, 1,5);
		Button play16 = createButtons(220, 220, 1, 6);
		Button play17 = createButtons(0, 320, 1, 7);
		Button play18 = createButtons(180, 370, 1, 8);
		Button back1 = createBackButton();
		back1.setOnAction(e -> substage2.setScene(mapScene));
		i1Buttons.getChildren().addAll(back1, play11, play12, play13, play14, play15, play16, play17, play18);

		/*Changes image of background to a map.*/
		Image r1Image = new Image("Images/museumIsland.png");
		ImageView r1View = new ImageView(r1Image);
		bindingLevel(r1View);
		island1.getChildren().addAll(r1View, i1Buttons);

		/*World 2.
		 * Creating buttons, instantiating BoardPanel appropriately, adding the buttons to the scene.*/
		StackPane island2 = new StackPane();

		FlowPane i2Buttons = new FlowPane();
		Button play21 = createButtons(35, 270, 2, 1);
		Button play22 = createButtons(100, 100, 2, 2);
		Button play23 = createButtons(200, 30, 2, 3);
		Button play24 = createButtons(200, 115, 2, 4);
		Button play25 = createButtons(270, 250, 2, 5);
		Button play26 = createButtons(100, 300, 2, 6);
		Button play27 = createButtons(-100, 387, 2, 7);
		Button play28 = createButtons(100, 390, 2, 8);
		Button back2 = createBackButton();
		back2.setOnAction(e -> substage2.setScene(mapScene));
		i2Buttons.getChildren().addAll(back2, play21, play22, play23, play24, play25, play26, play27, play28);

		/*Changes image of background to a map.*/
		Image r2Image = new Image("Images/desertIsland.png");
		ImageView r2View = new ImageView(r2Image);
		bindingLevel(r2View);
		island2.getChildren().addAll(r2View, i2Buttons);

		/*World 3.
		 * Creating buttons, instantiating BoardPanel appropriately, adding the buttons to the scene.*/
		StackPane island3 = new StackPane();

		FlowPane i3Buttons = new FlowPane();
		Button play31 = createButtons(160, 310, 3, 1);
		Button play32 = createButtons(6, 100, 3,2);
		Button play33 = createButtons(-25, 265, 3,3);
		Button play34 = createButtons(90, 272, 3,4);
		Button play35 = createButtons(90, 110, 3,5);
		Button play36 = createButtons(80, 272, 3,6);
		Button play37 = createButtons(130, 185, 3,7);
		Button play38 = createButtons(190, 280, 3,8);
		Button back3 = createBackButton();
		back3.setOnAction(e -> substage2.setScene(mapScene));
		i3Buttons.getChildren().addAll(back3, play31, play32, play33, play34, play35, play36, play37, play38);

		/*Changes image of background to a map.*/
		Image r3Image = new Image("Images/iceIsland.png");
		ImageView r3View = new ImageView(r3Image);
		bindingLevel(r3View);
		island3.getChildren().addAll(r3View, i3Buttons);

		/*World 4.
		 * Creating buttons, instantiating BoardPanel appropriately, adding the buttons to the scene.*/
		StackPane island4 = new StackPane();

		FlowPane i4Buttons = new FlowPane();
		Button back4 = createBackButton();
		Button play41 = createButtons(20, 190, 4,1);
		Button play42 = createButtons(80, 110, 4,2);
		Button play43 = createButtons(155, 135, 4,3);
		Button play44 = createButtons(250, 200, 4,4);
		Button play45 = createButtons(-40, 200, 4,5);
		Button play46 = createButtons(0, 250, 4,6);
		Button play47 = createButtons(80, 330, 4,7);
		Button play48 = createButtons(200, 310, 4,8);
		back4.setOnAction(e -> substage2.setScene(mapScene));
		i4Buttons.getChildren().addAll(back4, play41, play42, play43, play44, play45, play46, play47, play48);

		/*Changes image of background to a map.*/
		Image r4Image = new Image("Images/factoryIsland.png");
		ImageView r4View = new ImageView(r4Image);
		bindingLevel(r4View);
		island4.getChildren().addAll(r4View, i4Buttons);

		/*World 5.
		 * Creating buttons, instantiating BoardPanel appropriately, adding the buttons to the scene.*/
		StackPane island5 = new StackPane();

		FlowPane i5Buttons = new FlowPane();
/*		Button play11 = createButtons(160,227);
		Button play12 = createButtons(140,340);*/
		Button play51 = createButtons(6, 170, 5, 1);
		Button play52 = createButtons(170, 20, 5, 2);
		Button play53 = createButtons(95, 237, 5, 3);
		Button play54 = createButtons(148, 237, 5, 4);
		Button play55 = createButtons(180, 220, 5,5);
		Button play56 = createButtons(220, 220, 5, 6);
		Button play57 = createButtons(0, 320, 5, 7);
		Button play58 = createButtons(180, 370, 5, 8);
		Button back5 = createBackButton();
		back5.setOnAction(e -> substage2.setScene(mapScene));
		i5Buttons.getChildren().addAll(back5, play51, play52, play53, play54, play55, play56, play57, play58);

		/*Changes image of background to a map.*/
		Image r5Image = new Image("Images/museumIsland.png");
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
		substage2.setTitle("Space Ranch");
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
	public Button createButtons(int x, int y, int world, int lvl) {
		Button playButton = new Button("Play " + lvl);
		playButton.setTranslateX(x);
		playButton.setTranslateY(y);
		playButton.setOnAction(e -> new BoardPanel(world+"", lvl+""));
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