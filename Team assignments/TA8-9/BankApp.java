import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.WindowEvent;
import javafx.geometry.Pos;
import java.io.PrintWriter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Random;


public class BankApp extends Application {
    /** Instance variables for the class that are set to default values.*/ 

    private BankAccount savingsInfo = new SavingsAccount();
    private Label accName;
    private Label balanceLabel;

    public static void main (String[] args) {
        launch(args);
    }

    /** This method creates a pop-up window for a bank account.
     * It displays the name of the user, the balance of the account, and a textbox allowing one to
     * enter an amount to deposit to/withdraw from the account (through buttons in the bottom).
     * The balance of the account changes each time the successful deposit/withdrawal occurs.
     * One can deposit and withdraw amounts as type <double>.
     * Depositing an amount < 0 or withdrawing an amount > balance itself is not permitted.  
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane root = new BorderPane(); //allows for easy organization of the window itself
        VBox topPane = new VBox(); //also for purpose of organization

        try{
            BufferedReader input = new BufferedReader(new FileReader("bank.txt"));
            savingsInfo = new SavingsAccount(input);
            if(savingsInfo.getAccountHolder().equals(null))
            {
                throw new Exception("File is Blank");
            }
            else
            {
                accName = new Label("Account Holder: " + savingsInfo.getAccountHolder().getName()); //displays the name of the user
                accName.setFont(Font.font("Calibri", 18));
                balanceLabel = new Label("Balance: " + savingsInfo.getBalance()); //displays initial balance of account as 0.00
                balanceLabel.setFont(Font.font("Calibri", 16));
            }
        }
        catch(Exception e)
        {
           accName = new Label("No previous info found. Creating new account."); 
           balanceLabel = new Label("What's your name? Press ENTER when finished."); 
        }
      
        topPane.getChildren().addAll(accName, balanceLabel);
        topPane.setAlignment(Pos.CENTER);

        HBox centerPane = new HBox();
        Label label3 = new Label("Enter:  "); //used to create textbox label...
        label3.setFont(Font.font("Calibri", 16));
        TextField amt = new TextField(); //... and this is the textbox
        amt.setPrefWidth(150);
        centerPane.getChildren().addAll(label3, amt);
        centerPane.setAlignment(Pos.CENTER);

        HBox botPane = new HBox();
        Button depositEvent = new Button("Deposit"); //used to create the Deposit button
        Button withdrawEvent = new Button("Withdraw"); //used to create the Withdraw button
        Button enterEvent = new Button("Enter");
        botPane.getChildren().addAll(depositEvent, withdrawEvent, enterEvent);
        botPane.setAlignment(Pos.CENTER);

        root.setTop(topPane);
        root.setCenter(centerPane);
        root.setBottom(botPane);

        /** Whenever DEPOSIT is clicked, it deposits the money (if possible) then changes the label on the GUI showing the balance. */
        depositEvent.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                try {
                    double dep = Double.parseDouble(amt.getText()); //parses the amount in the textbox and stores the value as a double
                    savingsInfo.deposit(dep); //calls upon the BankAccount class to deposit the amount indicated
                    balanceLabel.setText("Balance: " + savingsInfo.getBalance()); //displays the new balance
                }
                catch (Exception e){
                    balanceLabel.setText("Invalid input."); //Error message on GUI
                }
            }
        });

        /** Whenever WITHDRAW is clicked, it deposits the money (if possible) then changes the label on the GUI showing the balance. */
        withdrawEvent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                try {
                    double withdraw = Double.parseDouble(amt.getText()); //parses the amount in the textbox and stores the value as a double
                    savingsInfo.withdraw(withdraw); //calls upon the BankAccount class to withdraw the amount indicated
                    balanceLabel.setText("Balance: " + savingsInfo.getBalance()); //displays the new balance
                }
                catch (Exception e){
                    balanceLabel.setText("Invalid input."); //Error message on GUI
            }
        }
    });

        /** This is triggered when the user inputs their name (when they click ENTER).
         * Takes the value in the text box, sets it to the Customer's name.
         * Randomly generates and ID for the Customer.
         * Then, creates the file. */
        enterEvent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(savingsInfo.getAccountHolder() == null) {
                    
                    String name = amt.getText(); 
                    
                    savingsInfo.setAccountHolder(new Customer(name,idGenerator(1000,9999)));
                    accName.setText("Account Holder: " + savingsInfo.getAccountHolder().getName());
                    balanceLabel.setText("Balance: " + savingsInfo.getBalance());
                    
                    try{
                        savingsInfo.saveToTextFile("bank.txt");
                    }
                    catch(Exception a) {}
                }
            }
        });

        /** Updates the file whenever necessary. */
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                try {
                    savingsInfo.saveToTextFile("bank.txt");
                }
                catch(Exception e) {} 
            }
        });

        Scene scene = new Scene(root, 500, 250);
        primaryStage.setTitle("Team 01 / CPSC233 / TA8/9");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /** Generates random ID for the customer.
     * @param min the lowest number the ID could be
     * @param max the highest number the ID could be
     * @return the random ID */
    public static int idGenerator(int min, int max) {
        Random r = new Random();
        int randomNum = r.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
