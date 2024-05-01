module tictactoe.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens apostkef.FXTacToe to javafx.fxml;
    exports apostkef.FXTacToe;
}