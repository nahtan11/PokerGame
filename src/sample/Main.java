package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    Scene scene1,scene2,scene3,scene4,scene5;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Poker Game");

        //Main Menu
        //Label label1= new Label("This is the first scene");
        Button button1= new Button("Play");
        button1.setOnAction(e -> primaryStage.setScene(scene2));
        Button button2= new Button("LeaderBoard");
        button2.setOnAction(e -> primaryStage.setScene(scene3));
        Button button3= new Button("Exit");
        button3.setOnAction(e -> Platform.exit());
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(button1,button2,button3);
        scene1= new Scene(layout1, 400, 500);

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
        Button button13= new Button("Back");
        button13.setOnAction(e -> primaryStage.setScene(scene2));
        VBox layout5= new VBox(20);
        layout5.getChildren().addAll(button13);
        scene3= new Scene(layout5,400,500);


        primaryStage.setScene(scene1);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
