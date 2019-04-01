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
import javafx.geometry.Pos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.util.Random;

/*Not working:
* - Customer instantiation with BufferedReader (thus, the account number and most importantly the name don't update on the GUI
* - Error message doesn't appear on GUI when entering incorrect inputs for Deposit and Withdraw*/

public class BankApp extends Application {
    /** Instance variables: 
     * BankInfo used to intialize BankAccount variable
     */
    private BankAccount savingsInfo = new SavingsAccount();
    //private BankAccount chequingInfo;
    private Customer customer = new Customer();

    private Label accName;
    private Label balanceLabel;
    //private Random r = new Random();

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

        accName = new Label("Account Holder: " + customer.getName()); //displays the name of the user
        accName.setFont(Font.font("Calibri", 18));
        balanceLabel = new Label("Balance: " + savingsInfo.getBalance()); //displays initial balance of account as 0.00
        balanceLabel.setFont(Font.font("Calibri", 16));

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

        /** This is where instantiate is called */
        instantiate("accInfo.txt");

        /** try-catches included in an attempt to display input... it doesn't work but at least it doesn't allow to user to
         * put negative values in
         */
        depositEvent.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                try {
                    double dep = Double.parseDouble(amt.getText()); //parses the amount in the textbox and stores the value as a double
                    savingsInfo.deposit(dep); //calls upon the BankAccount class to deposit the amount indicated
                    balanceLabel.setText("Balance: " + savingsInfo.getBalance()); //displays the new balance
                }
                catch (Exception e){
                    System.out.println("esfsd"); //
                    String eS = e + ""; //
                    balanceLabel.setText(eS);
                }
                try {
                    createFile("accInfo.txt", update());
                    updateStuff();
                }
                catch (Exception e){
                    String eS = e + "";
                    accName.setText(eS);
                    System.out.println("sdfs");
                }
            }
        });

        /** try-catches included in an attempt to display input... it doesn't work but at least it doesn't allow to user to
         * put negative values in
         */
        withdrawEvent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                try {
                    double withdraw = Double.parseDouble(amt.getText()); //parses the amount in the textbox and stores the value as a double
                    savingsInfo.withdraw(withdraw); //calls upon the BankAccount class to withdraw the amount indicated
                    balanceLabel.setText("Balance: " + savingsInfo.getBalance()); //displays the new balance
                }
                catch (Exception e){
                    balanceLabel.setText("Invalid input.");
                }
                try {
                    createFile("accInfo.txt", update());
                    updateStuff();
                }
                catch (Exception e){
                    String eS = e + "";
                    accName.setText(eS);
                }
            }
        });

        /** This is triggered when the user inputs their name (when they click ENTER)
         */
        enterEvent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                String name = amt.getText(); //parses the amount in the textbox and stores the value as a double
                customer.setName(name); //calls upon the BankAccount class to withdraw the amount indicated
                customer.setID(idGenerator(1000,9999));
                accName.setText("Account Holder: " + customer.getName()); //displays the new balance
                balanceLabel.setText("Balance: " + savingsInfo.getBalance());
                try {
                    createFile("accInfo.txt", update()); //tries to create the file, calls update() to get appropriate info for file
                }
                catch (Exception e){
                    String eS = e + "";
                    accName.setText(eS);
                }
                instantiate("accInfo.txt"); //since the file exists, it can freely instantiate SavingsAccount and (supposedly) Customer for info
            }
        });

        Scene scene = new Scene(root, 500, 250);
        primaryStage.setTitle("Team 01 / CPSC233 / TA5");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /** Method to generate random ID */
    public static int idGenerator(int min, int max) {
        Random r = new Random();
        int randomNum = r.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    /** Stolen from IA8/9 JUnits
     * Just used to create the file
    */
    public void createFile(String filename, String[] lines) throws IOException {
        PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
        for (String line : lines) {
            output.println(line);
        }
        output.close();
    }

    /** This method just updates the lines meant to be written in the file */
    public String[] update() {
        String[] linesInFile = {savingsInfo.getBalance() + "", savingsInfo.getAccountNumber(), customer.getName(), customer.getID() + "",  "0.05", "50"};
        return linesInFile;
    }

    /** ... Is this method necessary?? I forgot what i was doing with it lmao */
    public void updateStuff() {
            accName.setText("Account Holder: " + customer.getName());
            balanceLabel.setText("Balance: " + savingsInfo.getBalance());
    }

    /** So for this method I meant to instantiate the SavingsAccount and Customer with the BufferReader,
     * and if it's unable to it prompt the user for a name and creates a new account (go to anonymous class <enterEvent>)
     * Tho for some reason it's NEVER able to instantiate Customer with the BufferReader, that's why I commented it out
     */
    public void instantiate(String fileName) {
        try{
            BufferedReader input = new BufferedReader(new FileReader(fileName));
            savingsInfo = new SavingsAccount(input); //?
            //customer = new Customer(input);
            accName.setText("Account Holder: " + customer.getName());
            balanceLabel.setText("Balance: " + savingsInfo.getBalance());
        }
        catch (Exception e){
            accName.setText("No previous info found. Creating new account.");
            balanceLabel.setText("What's your name? Press ENTER when finished.");
        }
    }
}
