package com.example.demo;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.*;
public class HelloApplication extends Application {
    private static Bank b = new Bank();
    private TextField nameField, balanceField, addressField,accounttypeField,phoneno,accountno;
    private Label balanceValidation, acountInfo;
    AccountType accountType;
    Button btn0,btn1, btn2, btn3, btn4, btn5, btn6,btn7, btn8,btn9,btn10,btn11;
    int selectedAccountNumber;

    @Override
    public void start(Stage stage) throws IOException {
        /*File file=new File("final bank project.txt");
        FileWriter writer=null;
        try{
            file.createNewFile();
        }
        catch(IOException e){
            System.out.println("exception occured!");
            e.printStackTrace();
        }
        try{
            writer=new FileWriter("final bank project.txt");
        }
        catch(IOException e){
            System.out.println("Exception occured!!");
            e.printStackTrace();
        }*/
        defineButtons();
btn0.setOnAction(e->{
    loginButton(stage);
});
        btn1.setOnAction(e -> {
            showAccountField(stage);
        });

        btn2.setOnAction(e ->{
            showDepositFields(stage);
        });
        btn3.setOnAction(e->{
            showwithdrawFields(stage);
        });
        btn4.setOnAction(e ->{
            showAccountDetails(stage, -1);
        });
        btn5.setOnAction(e -> {
            setHomePage(stage);
        });

        btn6.setOnAction(e -> {
            createAccount();
        });

        btn7.setOnAction(e -> {
            depositCash();
        });

        btn8.setOnAction(e ->{
            int i = b.displayAccountInfo(selectedAccountNumber);
            showAccountDetails(stage, i);

        });
        btn9.setOnAction(e -> {
           withdrawCah();
        });
        btn10.setOnAction(e -> {
            startpage(stage);
        }
      );
        btn11.setOnAction(e -> {
                   setHomePage(stage);
                }
        );
        btn0.setMinSize(70, 50);
        startpage(stage);
        stage.setTitle("BANKING SYSTEM");
        stage.show();
  /*      // Save objects to file
      //  saveAccountToFile(new File("com/example/demo"), Bank b);

// Load objects from file
        loadAccountFromFile(new File("com/example/demo"));
    }*/}
    public static void main(String[] args) {

        launch();
    }
    public void startpage(Stage stage) {
        Image backgroundImage = new Image("C:\\task\\demo\\src\\main\\resources\\bank picture.jpg");
        ImageView backgroundImage1 = new ImageView(backgroundImage);
        VBox bt=new VBox(btn0);
        bt.setAlignment(Pos.CENTER);
        StackPane root = new StackPane();
        StackPane.setAlignment(backgroundImage1, Pos.CENTER);
        root.getChildren().add(backgroundImage1);
        root.getChildren().add(bt);
        Scene scene = new Scene(root, 700, 600);
        backgroundImage1.fitWidthProperty().bind(scene.widthProperty());
        backgroundImage1.fitHeightProperty().bind(scene.heightProperty());
        stage.setScene(scene);
        stage.show();
    }

    public void loginButton(Stage stage){
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        VBox rt=new VBox(12.0);
        rt.setAlignment(Pos.CENTER);

        if (!rt.getChildren().contains(btn5)) {
            rt.getChildren().addAll(usernameLabel, usernameField,passwordLabel, passwordField,btn11);
            Scene scene2=new Scene(rt,500,300);
            stage.setScene(scene2);
        }
    }
public void withdrawCah() {
    double balance;
    try {
        balance = Double.parseDouble(balanceField.getText());
        balanceValidation.setText("");
    } catch (Exception e) {
        balanceValidation.setText("Please input correct balance value");
        return;
    }
    b.withdrawCash(selectedAccountNumber, balance);

    }
    public void depositCash(){
        double balance;
        try {
            balance = Double.parseDouble(balanceField.getText());
            balanceValidation.setText("");
        }catch (Exception e)
        {
            balanceValidation.setText("Please input correct balance value");
            return;
        }
        b.depositCash(selectedAccountNumber,balance);
    }

    public void createAccount() {
        double balance;
        try {
            balance = Double.parseDouble(balanceField.getText());
            balanceValidation.setText("");
        }catch (Exception e)
        {
            balanceValidation.setText("Please input correct balance value");
         return;
        }
        BankAccount acc = new BankAccount(accountType, balance);
        AccountHolder accountHolders = new AccountHolder(nameField.getText(), addressField.getText(), phoneno.getText());
        b.createAccount(acc, accountHolders);
    }
    public void setHomePage(Stage stage){
        VBox root=new VBox(12.0);
        root.setAlignment(Pos.CENTER);

        if (!root.getChildren().contains(btn1)) {
            root.getChildren().addAll(btn1, btn2, btn3, btn4,btn10);
        }
        Scene scene=new Scene(root,500,300);
        stage.setScene(scene);
    }

    public void showAccountField(Stage stage){
        nameField = new TextField();
        nameField.setPromptText("Enter your name here...");
        balanceField = new TextField();
        balanceField.setPromptText("Enter balance to deposit : ");
        balanceValidation = new Label();
        addressField = new TextField();
        addressField.setPromptText("Enter Address : ");
        ComboBox<AccountType> dropdown = new ComboBox<>();
        dropdown.setPromptText("Select Account Type");
        dropdown.setItems(FXCollections.observableArrayList(AccountType.values()));
        phoneno=new TextField();
        phoneno.setPromptText("enter your phone number: ");

        dropdown.setOnAction(e -> {
            accountType = dropdown.getValue();
        });

        VBox root1=new VBox(12.0);
        root1.setAlignment(Pos.CENTER);

        if (!root1.getChildren().contains(btn5)) {
            root1.getChildren().addAll(btn5, nameField, balanceField, addressField, dropdown, phoneno, btn6);
            Scene scene2=new Scene(root1,500,300);
            stage.setScene(scene2);
        }
    }
    public void showDepositFields(Stage stage){
        ComboBox<Integer> dropdown =accountNumberField();
        balanceField = new TextField();
        balanceField.setPromptText("Enter balance to deposit : ");

        VBox root=new VBox(12.0);
        root.getChildren().addAll(btn5, dropdown, balanceField, btn7);
        Scene scene2=new Scene(root,500,300);
        stage.setScene(scene2);

    }
    public void showwithdrawFields(Stage stage){
        ComboBox<Integer> dropdown =accountNumberField();
        balanceField = new TextField();
        balanceField.setPromptText("Enter balance to withdraw : ");
        VBox root=new VBox(12.0);
        root.getChildren().addAll(btn5, dropdown, balanceField, btn9);
        Scene scene2=new Scene(root,500,300);
        stage.setScene(scene2);
    }

    public void showAccountDetails(Stage stage, int i){
        ComboBox<Integer> dropdown =accountNumberField();
        VBox root=new VBox(12.0);
        if (i >-1) {
            BankAccount account = b.getAccounts().get(i);
            AccountHolder holder=new AccountHolder(nameField.getText(),addressField.getText(),phoneno.getText() );
            acountInfo = new Label("Account Information: \nAccount Number: " + account.getAccountNumber()+"\nAccount Type: "+account.getAccountType()+"\nBalance: "+account.getBalance()+"\n\nAccountHolder Details: \nName: "+holder.getName()+"\n Phone number: "+holder.getPhoneNumber()+"\n Address: "+holder.getAddress());
        }
        else{
            acountInfo = new Label();
        }
        root.getChildren().addAll(btn5, dropdown, btn8, acountInfo);

        Scene scene2=new Scene(root,500,300);
        stage.setScene(scene2);

    }

    public ComboBox<Integer> accountNumberField(){
        Integer[] accounts = new Integer[b.getAccounts().size()];
        for (int i = 0; i < b.getAccounts().size(); i++) {
            accounts[i] = b.getAccounts().get(i).getAccountNumber();
        }
        ComboBox<Integer> dropdown = new ComboBox<>();
        dropdown.setPromptText("Select Account Number");
        dropdown.setItems(FXCollections.observableArrayList(accounts));
        dropdown.setOnAction(e -> {
            selectedAccountNumber = dropdown.getValue();
        });

        return dropdown;
    }
    public void defineButtons(){
        btn0=new Button("Login");
        btn1 = new Button("1-CREATE BANKACCOUNT");
        btn2 = new Button("2-DEPOSIT CASH");
        btn3 = new Button("3-WITHDRAW CASH");
        btn4 = new Button("4-DISPLAY ACCOUNT INFORMATION");
        btn5 = new Button("move to home page");
        btn6 = new Button("Create Account");
        btn7 = new Button("Depoit cash");
        btn8 = new Button("View Details");
        btn9=new Button("withdraw cash");
        btn10=new Button("login");
        btn11=new Button("login");
    }
}
