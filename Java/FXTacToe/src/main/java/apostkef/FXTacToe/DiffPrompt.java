package apostkef.FXTacToe;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DiffPrompt {
    BorderPane bPane;
    Stage diffWin;
    Scene scene;
    int diff ;
    Text text;
    public DiffPrompt(Stage mainStage){
        this.diff =0;
        bPane = new BorderPane(); bPane.setPadding(new Insets(10));
        bPane.minHeight(350); bPane.minWidth(300);

        ToggleGroup difficulties = new ToggleGroup();
        RadioButton easy = new RadioButton("Easy"); easy.setToggleGroup(difficulties); easy.setOnAction(event -> {this.diff = 1; diffWin.close();});
        RadioButton normal = new RadioButton("Normal"); normal.setToggleGroup(difficulties); normal.setOnAction(event -> {this.diff = 2;diffWin.close();});
        RadioButton hard = new RadioButton("Hard"); hard.setToggleGroup(difficulties); hard.setOnAction(event -> {this.diff = 3;diffWin.close();});
        RadioButton extreme = new RadioButton("Extreme!"); extreme.setToggleGroup(difficulties); extreme.setOnAction(event -> {this.diff = 4;diffWin.close();});
        HBox hbox = new HBox();
        hbox.getChildren().addAll(easy,normal,hard,extreme);
        hbox.setSpacing(10);

        text = new Text("Pick a difficulty:"); text.setFont(new Font("Vernada",16));
        HBox hbox2 = new HBox(text); hbox2.setPadding(new Insets(40,10,10,10));

        bPane.setTop(hbox2);
        bPane.setCenter(hbox);
        hbox.setAlignment(Pos.CENTER);
        hbox2.setAlignment(Pos.CENTER);

        diffWin = new Stage();
        diffWin.initOwner(mainStage);diffWin.initModality(Modality.APPLICATION_MODAL);diffWin.initStyle(StageStyle.UTILITY);
        diffWin.setMinWidth(350);diffWin.setMinHeight(300); diffWin.setResizable(false);diffWin.setTitle("FXTacToe - Difficulty Selection Screen");
        scene = new Scene(bPane);
        diffWin.setScene(scene);
    }
    public void showDiffPrompt() {
        diffWin.showAndWait();
    }
    public int getDiff(){return this.diff;}
}
