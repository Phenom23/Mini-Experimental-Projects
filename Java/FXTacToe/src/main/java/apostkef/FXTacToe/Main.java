package apostkef.FXTacToe;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage stage){

        Menu menu = new Menu(stage);
        stage.setScene(menu.getScene());
        stage.show();

    } public static void main(String[] args){
        launch();
    }
}
