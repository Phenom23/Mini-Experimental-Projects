package apostkef.FXTacToe;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PvcTable extends Table {

    int playerXTries = 5;
    int computerTries = 4;
    int turn = 1;  //1 for x, 2 for computer
    int[][] buttonMap = new int[3][3];

    public PvcTable(Stage stage) {
        super(stage);
        Referee referee = new Referee();

        //Initializing the map
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttonMap[i][j] = 0;
            }
        }

        ComputerAi computerAi = new ComputerAi(buttonMap);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int finalI = i; int finalJ = j; //necessary for the lambda expr.

                buttons[i][j].setOnAction(event ->
                {
                    if (buttonMap[finalI][finalJ] == 0)
                    {
                        if (turn == 1 && playerXTries > 0) {
                            buttonMap[finalI][finalJ] = 1;
                            playerXTries--;
                            turn = 2;
                            buttons[finalI][finalJ].setText("X");
                            buttons[finalI][finalJ].setDisable(true);
                        }
                        if (computerTries > 0) {
                            int[] computerPick = new int[2];
                            computerPick[0] = computerAi.pick()[0]; //necessary buffers in order not to recalculate many times,
                            computerPick[1] = computerAi.pick()[1]; //algorithm ain't work gonna otherwise

                            buttonMap[computerPick[0]][computerPick[1]] = 2;
                            computerTries--;
                            turn = 1;
                            buttons[computerPick[0]][computerPick[1]].setText("O");
                            buttons[computerPick[0]][computerPick[1]].setDisable(true);
                        }
                    }
                    if (referee.topChecker(buttonMap, true) != null) {
                        EndScreen end = new EndScreen(stage, referee.topChecker(buttonMap, true));
                        stage.setScene(end.getScene());
                    } else if (computerTries == 0 && playerXTries == 0 && (referee.topChecker(buttonMap, true) == null)){
                        EndScreen end = new EndScreen(stage, "No-one won");
                        stage.setScene(end.getScene());
                    }
                });
            }
        }
    }
    public Scene getScene(){return this.scene;}
}

