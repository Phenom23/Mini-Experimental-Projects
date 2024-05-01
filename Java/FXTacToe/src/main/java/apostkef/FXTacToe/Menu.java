package apostkef.FXTacToe;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
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
        title = new Text("Welcome to FXTacToc!");
        title.setFont(Font.font("Verdana", 20));

        titleHb = new HBox(title);
        titleHb.setAlignment(Pos.CENTER);
        titleHb.setPadding(new Insets(40,20,80,20));

        bt_PvP = new Button("Player vs Player");
        bt_PvP.setFocusTraversable(false);
        bt_PvC = new Button("Player vs Computer");
        bt_PvC.setFocusTraversable(false);

        menuHb = new HBox(10, bt_PvP, bt_PvC);
        menuHb.setAlignment(Pos.CENTER);
        menuHb.setPadding(new Insets(10));

        stage.setMinWidth(350); stage.setMinHeight(300);

        bt_PvP.setOnAction(event -> {
            PvpTable tbl = new PvpTable(stage);
            scene = tbl.getScene();
            stage.setScene(scene);
        });
        bt_PvC.setOnAction(event -> {
            PvcTable tbl2 = new PvcTable(stage);
            scene = tbl2.getScene();
            stage.setScene(scene);
        });

        pane = new BorderPane();
        pane.setCenter(menuHb);
        pane.setMinHeight(300); pane.setMinWidth(350); pane.setPadding(new Insets(5));
        pane.setTop(titleHb);
        pane.setBottom(new Label("By Kefalos Apostolos - 2024"));
        scene = new Scene(pane);
        BorderPane.setAlignment(title, Pos.CENTER);
    }
    public Scene getScene(){
        return this.scene;
    }
}
