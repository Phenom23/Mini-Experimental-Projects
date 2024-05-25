package apostkef.FXTacToe;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

class Table
{
    protected Button[][] buttons = new Button[3][3];
    Button back = new Button("↩ Back");

    protected GridPane gPane = new GridPane();
    protected Scene scene;

    public Table(Stage stage){ //initializes the table

        gPane.setHgap(5); gPane.setVgap(5); gPane.setPadding(new Insets(10));

        ColumnConstraints col1 = new ColumnConstraints(); col1.setPercentWidth(33.33);
        ColumnConstraints col2 = new ColumnConstraints(); col2.setPercentWidth(33.33);
        ColumnConstraints col3 = new ColumnConstraints(); col3.setPercentWidth(33.33);
        gPane.getColumnConstraints().addAll(col1,col2,col3);

        RowConstraints row1 = new RowConstraints(); row1.setPercentHeight(31);
        RowConstraints row2 = new RowConstraints(); row2.setPercentHeight(31);
        RowConstraints row3 = new RowConstraints(); row3.setPercentHeight(31);
        gPane.getRowConstraints().addAll(row1,row2,row3);

        for(int rw=0;rw<3;rw++){
            for(int cl=0; cl<3;cl++){
                buttons[rw][cl] = new Button();
                Stylist.TacTableButtonsCanvaStyle(buttons[rw][cl]);
                gPane.add(buttons[rw][cl],cl,rw);
            }
        }

        Stylist.NavigationButtonsStyle(back);

        gPane.add(back,0,3);
        gPane.setBackground(Stylist.seashellBackgroundColor());

        back.setOnAction(event -> EventHandler.backTableAction(stage));

        scene = new Scene(gPane);

    }
}