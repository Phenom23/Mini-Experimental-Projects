package apostkef.FXTacToe;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Menu
{
    Text title;
    Button bt_PvP,bt_PvC;
    HBox menuHb, titleHb;
    BorderPane pane;
    Scene scene;

    public Menu(Stage stage) {

        title = new Text("Welcome to FXTacToe!");
        title.setFont(Stylist.bigFont());

        titleHb = new HBox(title);
        titleHb.setAlignment(Pos.CENTER);
        titleHb.setPadding(new Insets(80,20,80,20));


        bt_PvP = new Button("Player VS Player"); bt_PvP.setPrefHeight(40.0);
        bt_PvC = new Button("Player VS Computer"); bt_PvC.setPrefHeight(40.0);
        Stylist.NavigationButtonsStyle(bt_PvC); Stylist.NavigationButtonsStyle(bt_PvP);

        menuHb = new HBox(10, bt_PvP, bt_PvC);
        menuHb.setAlignment(Pos.CENTER);
        menuHb.setPadding(new Insets(10,10,40,10));

        Stylist.stageInfStyle(stage); stage.setTitle("FXTacToe");

        bt_PvP.setOnAction(event -> EventHandler.pvpLauncher(stage,scene));
        bt_PvC.setOnAction(event -> EventHandler.pvcLauncher(stage,scene));
        pane = new BorderPane();
        pane.setCenter(menuHb);
        pane.setMinHeight(300); pane.setMinWidth(350); pane.setPadding(new Insets(5));
        pane.setTop(titleHb);
        pane.setBottom(new Label("By Kefalos Apostolos - 2024 - v1.4"));
        pane.setBackground(Stylist.seashellBackgroundColor());
        scene = new Scene(pane);
        BorderPane.setAlignment(title, Pos.CENTER);
    }
    public Scene getScene(){
        return this.scene;
    }
}
