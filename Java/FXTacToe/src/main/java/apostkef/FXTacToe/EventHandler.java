package apostkef.FXTacToe;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EventHandler {

    public static void backTableAction(Stage stage){
        Menu menu = new Menu(stage);
        stage.setScene(menu.getScene());
    }
    public static void pvpLauncher(Stage stage, Scene scene){
        PvpTable tbl = new PvpTable(stage);
        scene = tbl.getScene();
        stage.setScene(scene);
    }
    public static void pvcLauncher(Stage stage, Scene scene){
        DiffPrompt prompt = new DiffPrompt(stage);
        prompt.showDiffPrompt();
        if(prompt.getDiff() != 0){
            PvcTable tbl2 = new PvcTable(stage, prompt.getDiff());
            scene = tbl2.getScene();
            stage.setScene(scene);
        }
    }
    public static void replayAction(Stage mainStage, Scene scene, int typeGame){
        if(typeGame == 1){
            PvpTable tbl = new PvpTable(mainStage);
            scene = tbl.getScene();
            mainStage.setScene(scene);
        }
        else if (typeGame == 2){
            DiffPrompt prompt = new DiffPrompt(mainStage);
            prompt.showDiffPrompt();
            if(prompt.getDiff() != 0){
                PvcTable tbl2 = new PvcTable(mainStage, prompt.getDiff());
                scene = tbl2.getScene();
                mainStage.setScene(scene);
            }
        }
    }
}
