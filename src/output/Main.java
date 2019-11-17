package output;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class Main extends Application {
    Scene scene1,scene2,scene3,scene4,scene5;
    private String uName="";

    public void createLoginDialog(String version){
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle(version);
        dialog.setHeaderText("Enter a Username and Password");
        dialog.hide();

        ButtonType loginButtonType = new ButtonType(version, ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> username.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();


        result.ifPresent(usernamePassword -> {
            if(version.equals("Login")) {
                Login userLogin = new Login();
                boolean loginOkay = userLogin.checkLogin(usernamePassword.getKey(), usernamePassword.getValue());
                if(loginOkay){
                    uName=usernamePassword.getKey();
                }

            }
            else if (version.equals("Register")){
                Register userReg = new Register();
                try {
                    userReg.registerUser(usernamePassword.getKey(), usernamePassword.getValue());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Poker Game");

        //Label label1= new Label("This is the first scene");
        Image btImage = new Image("output/cards-2029819_640.png",200,200,false,false);
        Button image= new Button();
        image.setMinSize(200,200);
        image.setGraphic(new ImageView(btImage));
        Button button1= new Button("Play");
        button1.setOnAction(e -> primaryStage.setScene(scene2));


        Button button2= new Button("LeaderBoard");
        button2.setOnAction(e -> {
            Leaderboard lb = new Leaderboard();
            ListView listView = new ListView();
            List<String>leaderboardList = lb.getLeaderboard(uName);
            for(int i =0; i<leaderboardList.size();i++){
                String [] lbItems = leaderboardList.get(i).split(",");
                String item = lbItems[0]+"  |  "+lbItems[1];
                listView.getItems().add(item);
            }


            primaryStage.setScene(scene3);

        });

        Button button3= new Button("Exit");
        button3.setOnAction(e -> Platform.exit());

        Button login= new Button("Login");
        login.setOnAction(e -> createLoginDialog("Login"));

        Button register= new Button("Register");
        register.setOnAction(e -> createLoginDialog("Register"));

        VBox layout1 = new VBox(20);
        layout1.setAlignment(Pos.CENTER);
        layout1.getChildren().addAll(image,button1,button2,login,register,button3);
        BorderPane back = new BorderPane();
        back.setCenter(layout1);
        scene1= new Scene(back, 400, 500);

        //Game Selection Menu
        //Label label2= new Label("This is the second scene");
        Button button4= new Button("Single-player");
        button4.setOnAction(e -> primaryStage.setScene(scene4));
        Button button5= new Button("Multi-player");
        button5.setOnAction(e -> primaryStage.setScene(scene5));
        Button button6= new Button("Back");
        button6.setOnAction(e -> primaryStage.setScene(scene1));
        VBox layout2= new VBox(20);
        layout2.getChildren().addAll(button4,button5,button6);
        scene2= new Scene(layout2,400,500);

        //Single-player Menu
        //Label label2= new Label("This is the second scene");
        Button button7= new Button("Texas Hold'em");
        //button2.setOnAction(e -> primaryStage.setScene(scene1));
        Button button8= new Button("5 Card Draw");
        //button2.setOnAction(e -> primaryStage.setScene(scene1));
        Button button9= new Button("Back");
        button9.setOnAction(e -> primaryStage.setScene(scene2));
        VBox layout3= new VBox(20);
        layout3.getChildren().addAll(button7,button8,button9);
        scene4= new Scene(layout3,400,500);

        //Multi-player Menu
        //Label label2= new Label("This is the second scene");
        Button button10= new Button("Texas Hold'em");
        //button2.setOnAction(e -> primaryStage.setScene(scene1));
        Button button11= new Button("5 Card Draw");
        //button2.setOnAction(e -> primaryStage.setScene(scene1));
        Button button12= new Button("Back");
        button12.setOnAction(e -> primaryStage.setScene(scene2));
        VBox layout4= new VBox(20);
        layout4.getChildren().addAll(button10,button11,button12);
        scene5= new Scene(layout4,400,500);

        //Game Selection Menu
        //Label label2= new Label("This is the second scene");

        HBox lbHeadings = new HBox(20);
        VBox layout5= new VBox(20);
        scene3= new Scene(layout5,400,500);

        button2.setOnAction(e -> {
            Leaderboard lb = new Leaderboard();
            ListView listView = new ListView();
            List<String>leaderboardList = lb.getLeaderboard(uName);
            for(int i =0; i<leaderboardList.size();i++){
                String [] lbItems = leaderboardList.get(i).split(",");
                String item = lbItems[1]+"  |  "+lbItems[2];
                listView.getItems().add(item);
            }
            layout5.getChildren().addAll(listView);

            primaryStage.setScene(scene3);

        });

        Label score = new Label("SCORE");
        Label date = new Label("Date");
        lbHeadings.getChildren().addAll(score,date);
        Button button13= new Button("Back");
        button13.setOnAction(e -> {
            primaryStage.setScene(scene1);
            layout5.getChildren().remove(1);
        });
        layout5.getChildren().addAll(button13,lbHeadings);



        primaryStage.setScene(scene1);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
