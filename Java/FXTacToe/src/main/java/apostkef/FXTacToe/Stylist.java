package apostkef.FXTacToe;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Stylist {

    public static DropShadow shadow(){
        DropShadow shadow = new DropShadow();
        shadow.setOffsetX(5.5);
        shadow.setOffsetY(5.0);
        shadow.setBlurType(BlurType.THREE_PASS_BOX);
        shadow.setRadius(12.0);
        return shadow;
    }
    public static void stageInfStyle(Stage stage){
        stage.setResizable(false);
        stage.setMinWidth(350); stage.setMinHeight(300);
    }
    public static void TacTableButtonsCanvaStyle(Button button){
        button.setFocusTraversable(false);
        button.setPrefSize(100, 100);
        button.setEffect(Stylist.shadow());
        button.setTextFill(Color.WHITE);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 47));
        button.setBackground(Stylist.khakiBackgroundButton());
        button.setStyle("-fx-opacity: 0.96;");
    }
    public static void NavigationButtonsStyle(Button button){
        button.setEffect(Stylist.shadow());
        button.setFocusTraversable(false);
        button.setBackground(Stylist.lavenderBackgroundColor());
    }


    public static Background tealBackgroundButton(){
        return new Background(new BackgroundFill(Color.TEAL, new CornerRadii(5), new Insets(3)));
    }
    public static Background orangeBackgroundButton(){
        return new Background(new BackgroundFill(Color.ORANGERED, new CornerRadii(5), new Insets(3)));
    }
    public static Background khakiBackgroundButton(){
        return new Background(new BackgroundFill(Color.KHAKI, new CornerRadii(5), new Insets(3)));
    }
    public static Font normalFont(){
       return new Font("Vernada",17);
    }
    public static Font bigFont(){
        return new Font("Vernada",24);
    }
    public static Background seashellBackgroundColor() {
        return new Background(new BackgroundFill(Color.SEASHELL, CornerRadii.EMPTY, Insets.EMPTY));
    }
    public static Background lavenderBackgroundColor() {
        return new Background(new BackgroundFill(Color.LAVENDERBLUSH, new CornerRadii(12.0), Insets.EMPTY));
    }
}
