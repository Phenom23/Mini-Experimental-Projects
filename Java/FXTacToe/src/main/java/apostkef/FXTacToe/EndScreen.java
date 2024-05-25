package apostkef.FXTacToe;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EndScreen {
    Button back, replay;
    BorderPane bPane;
    Text lbl;
    Scene scene;
    Stage endPopStage;
    public EndScreen(Stage mainStage, String msg, int typeGame){

        bPane = new BorderPane();
        back = new Button("↩ Back"); replay = new Button("↺ Replay"); lbl = new Text(msg);

        bPane.setPadding(new Insets(10)); lbl.setFont(Stylist.normalFont());

        Stylist.NavigationButtonsStyle(back); Stylist.NavigationButtonsStyle(replay);

        bPane.minHeight(350); bPane.minWidth(300);
        bPane.setBackground(Stylist.seashellBackgroundColor());

        endPopStage = new Stage();
        endPopStage.initOwner(mainStage);
        endPopStage.initModality(Modality.APPLICATION_MODAL);
        endPopStage.initStyle(StageStyle.UTILITY);

        endPopStage.setOnCloseRequest(event -> {
            EventHandler.backTableAction(mainStage);
            endPopStage.close();
        });

        Stylist.stageInfStyle(endPopStage);
        endPopStage.setTitle("FXTacToe - EndScreen");

        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(back,replay);

        bPane.setCenter(lbl);
        bPane.setBottom(hbox);

        back.setOnAction(event -> {
            EventHandler.backTableAction(mainStage);
            endPopStage.close();
        });
        replay.setOnAction(event -> {
            EventHandler.replayAction(mainStage,scene,typeGame);
            endPopStage.close();
        });

        scene = new Scene(bPane);
        endPopStage.setScene(scene);
    }
    public void showEndScreen() {
        endPopStage.show();
    }
}