package apostkef.FXTacToe;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EndScreen {
    Button back;
    BorderPane bPane;
    Text lbl;
    Scene scene;
    Stage endPopStage;
    public EndScreen(Stage mainStage, String msg){
        bPane = new BorderPane(); back = new Button("Go back"); lbl = new Text(msg);
        bPane.setPadding(new Insets(10)); lbl.setFont(new Font("Vernada",16));
        back.setFocusTraversable(false);
        bPane.minHeight(350); bPane.minWidth(300);
        endPopStage = new Stage();
        endPopStage.initOwner(mainStage);endPopStage.initModality(Modality.APPLICATION_MODAL);endPopStage.initStyle(StageStyle.UTILITY);
        endPopStage.setOnCloseRequest(event -> {event.consume();});
        endPopStage.setMinWidth(350);endPopStage.setMinHeight(300); endPopStage.setResizable(false);endPopStage.setTitle("FXTacToe - EndScreen");

        bPane.setCenter(lbl);
        bPane.setBottom(back);
        back.setOnAction(event -> {
            Menu menu = new Menu(mainStage);
            mainStage.setScene(menu.getScene());
            endPopStage.close();
        });
        scene = new Scene(bPane);
        endPopStage.setScene(scene);
    }

    public void showEndScreen() {
        endPopStage.show();
    }
}