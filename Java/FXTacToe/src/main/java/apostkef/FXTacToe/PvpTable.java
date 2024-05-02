package apostkef.FXTacToe;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PvpTable extends Table
{
    int playerXTries = 5;
    int playerOTries = 4;
    int turn = 1;  //1 for x, 2 for O
    int[][] buttonMap = new int[3][3];

    public PvpTable(Stage stage) {
        super(stage);
        Referee referee = new Referee();
        stage.setResizable(false); stage.setTitle("FXTacToe - PvP");

        //Initializing the map
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttonMap[i][j] = 0;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                int finalI = i; int finalJ = j;

                buttons[i][j].setOnAction(event -> {
                    if (turn == 1 && playerXTries > 0) {
                        buttonMap[finalI][finalJ] = 1;
                        playerXTries--;
                        turn = 2;
                        buttons[finalI][finalJ].setText("X");
                        buttons[finalI][finalJ].setDisable(true);
                    } else if (turn == 2 && playerOTries > 0) {
                        buttonMap[finalI][finalJ] = 2;
                        playerOTries--;
                        turn = 1;
                        buttons[finalI][finalJ].setText("O");
                        buttons[finalI][finalJ].setDisable(true);
                    }
                    if (referee.topChecker(buttonMap, false) != null) {
                        EndScreen end = new EndScreen(stage, referee.topChecker(buttonMap, false));
                        end.showEndScreen();
                    } else if (playerOTries == 0 && playerXTries == 0 && (referee.topChecker(buttonMap, false) == null)) {
                        EndScreen end = new EndScreen(stage, "No-one won");
                        end.showEndScreen();
                    }
                });
            }
        }
    }
    public Scene getScene(){return this.scene;}
}

