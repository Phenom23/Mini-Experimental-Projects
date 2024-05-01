package apostkef.FXTacToe;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EndScreen {
    Button back;
    BorderPane bPane;
    Text lbl;
    Scene scene;
    public EndScreen(Stage stage, String msg){
        bPane = new BorderPane(); back = new Button("Go back"); lbl = new Text(msg);
        bPane.setPadding(new Insets(10)); lbl.setFont(new Font("Vernada",16));
        back.setFocusTraversable(false);
        bPane.minHeight(350); bPane.minWidth(300);
        stage.setMinWidth(350);stage.setMinHeight(300);

        bPane.setCenter(lbl);
        bPane.setBottom(back);
        back.setOnAction(event -> {
            Menu menu = new Menu(stage);
            stage.setScene(menu.getScene());
        });
        scene = new Scene(bPane);
    }

    public Scene getScene() {
        return scene;
    }
}