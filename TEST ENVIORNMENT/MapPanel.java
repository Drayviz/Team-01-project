import java.io.FileInputStream;

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

public class MapPanel extends Application{
	
	Stage window;
	Scene mapScene, IslandScene1, IslandScene2, IslandScene3, IslandScene4, IslandScene5;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		
		GridPane map = new GridPane();
		map.setStyle("-fx-background-color: BLACK");       
		map.setPadding(new Insets(50));	       
		map.setHgap(50);  
		map.setVgap(10);
		
		// changes button to image
		
		FileInputStream w1 = new FileInputStream("Images/w1.png");
	    Image w1Image = new Image(w1);
	    ImageView w1View = new ImageView(w1Image);
	    
	    FileInputStream w2 = new FileInputStream("Images/w2.png");
	    Image w2Image = new Image(w2);
	    ImageView w2View = new ImageView(w2Image);
	    
	    FileInputStream w3 = new FileInputStream("Images/w3.png");
	    Image w3Image = new Image(w3);
	    ImageView w3View = new ImageView(w3Image);
	    
	    FileInputStream w4 = new FileInputStream("Images/w4.png");
	    Image w4Image = new Image(w4);
	    ImageView w4View = new ImageView(w4Image);
	    
	    FileInputStream w5 = new FileInputStream("Images/w1.png");
	    Image w5Image = new Image(w5);
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
	    
	    w1Butt.setOnAction(e -> window.setScene(IslandScene1));
	    w2Butt.setOnAction(e -> window.setScene(IslandScene2));
	    w3Butt.setOnAction(e -> window.setScene(IslandScene3));
	    w4Butt.setOnAction(e -> window.setScene(IslandScene4));
	    w5Butt.setOnAction(e -> window.setScene(IslandScene5));
	    	    
	    // GridPane for the 5 islands
	    
	    GridPane island1 = new GridPane();
		GridPane island2 = new GridPane();		
		GridPane island3 = new GridPane();	
		GridPane island4 = new GridPane();		 
		GridPane island5 = new GridPane();
		 
		
		
		//Play buttons for world 1
		
		Button play1 = new Button("Play");
		play1.setTranslateX(220);
		play1.setTranslateY(245);
		Pane pView1 = new Pane(play1);
		
		Button play2 = new Button("Play");
		play2.setTranslateX(235);
		play2.setTranslateY(360);
		Pane pView2 = new Pane(play2);
		
		//Play buttons for world 2
		
		Button play3 = new Button("Play");
		play3.setTranslateX(140);
		play3.setTranslateY(290);
		Pane pView3 = new Pane(play3);
		
		Button play4 = new Button("Play");
		play4.setTranslateX(390);
		play4.setTranslateY(320);
		Pane pView4 = new Pane(play4);
		
		Button play5 = new Button("Play");
		play5.setTranslateX(420);
		play5.setTranslateY(410);
		Pane pView5 = new Pane(play5);
		
		//Play buttons for world 3
		
		Button play6 = new Button("Play");
		play6.setTranslateX(320);
		play6.setTranslateY(155);
		Pane pView6 = new Pane(play6);
		
		Button play7 = new Button("Play");
		play7.setTranslateX(345);
		play7.setTranslateY(285);
		Pane pView7 = new Pane(play7);
		
		Button play8 = new Button("Play");
		play8.setTranslateX(500);
		play8.setTranslateY(290);
		Pane pView8 = new Pane(play8);
		
		//Play buttons for world 4
		
		Button play9 = new Button("Play");
		play9.setTranslateX(160);
		play9.setTranslateY(270);
		Pane pView9 = new Pane(play9);
		
		Button play10 = new Button("Play");
		play10.setTranslateX(180);
		play10.setTranslateY(125);
		Pane pView10 = new Pane(play10);
		
		//Play buttons for world 5
		
		Button play11 = new Button("Play");
		play11.setTranslateX(220);
		play11.setTranslateY(245);
		Pane pView11 = new Pane(play11);
		
		Button play12 = new Button("Play");
		play12.setTranslateX(235);
		play12.setTranslateY(360);
		Pane pView12 = new Pane(play12);
		
		//changes image of background to a map
		
		//Region 1
		FileInputStream r1 = new FileInputStream("Images/r1.png");
		Image r1Image = new Image(r1);
	   	ImageView r1View = new ImageView(r1Image);
	   	r1View.fitWidthProperty().bind(window.widthProperty());
	    r1View.fitHeightProperty().bind(window.heightProperty());
	    island1.getChildren().addAll(r1View, pView1,pView2);
	       
	    //Region 2
	    FileInputStream r2 = new FileInputStream("Images/r2.png");
	    Image r2Image = new Image(r2);
	    ImageView r2View = new ImageView(r2Image);
	    r2View.fitWidthProperty().bind(window.widthProperty());
	    r2View.fitHeightProperty().bind(window.heightProperty());
	    island2.getChildren().addAll(r2View,pView3,pView4,pView5);
	    
	    //Region 3
	    FileInputStream r3 = new FileInputStream("Images/r3.png");
	    Image r3Image = new Image(r3);
	    ImageView r3View = new ImageView(r3Image);
	    r3View.fitWidthProperty().bind(window.widthProperty());
	    r3View.fitHeightProperty().bind(window.heightProperty());
	    island3.getChildren().addAll(r3View,pView6,pView7,pView8);
	    
	    //Region 4
	    FileInputStream r4 = new FileInputStream("Images/r4.png");
	    Image r4Image = new Image(r4);
	    ImageView r4View = new ImageView(r4Image);
	    r4View.fitWidthProperty().bind(window.widthProperty());
	    r4View.fitHeightProperty().bind(window.heightProperty());
	    island4.getChildren().addAll(r4View,pView9,pView10);
	    
	    //Region 5
	    FileInputStream r5 = new FileInputStream("Images/r1.png");
	    Image r5Image = new Image(r5);
	    ImageView r5View = new ImageView(r5Image);
	    r5View.fitWidthProperty().bind(window.widthProperty());
	    r5View.fitHeightProperty().bind(window.heightProperty());
	    island5.getChildren().addAll(r5View,pView11,pView12);
	    
		
	    // initializes the size ration of scene
	    
	    IslandScene1 = new Scene(island1, 640, 480);
	    IslandScene2 = new Scene(island2, 640, 480);
	    IslandScene3 = new Scene(island3, 640, 480);
	    IslandScene4 = new Scene(island4, 640, 480);
	    IslandScene5 = new Scene(island5, 640, 480);
	   
		window.setScene(mapScene);
		window.setTitle("Space Invaders");
		window.show();
		
	   
	}

}
