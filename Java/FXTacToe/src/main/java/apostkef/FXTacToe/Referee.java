package apostkef.FXTacToe;

public class Referee {
    public String topChecker(int[][] buttonMap, boolean pvc){
        if (diagChecker(buttonMap) != 0){
            if(diagChecker(buttonMap) == 1){
                return"Player X won by a diagonal line";
            }
            else {
                if(!pvc)
                    return"Player O won by a diagonal line";
                else
                    return"Computer won by a diagonal line";
            }
        }
        else if (HorizChecker(buttonMap) !=0){
            if(HorizChecker(buttonMap) == 1){
                return"Player X won by a horizontal line";
            }
            else {
                if(!pvc){
                    return"Player O won by a horizontal line";
                }
                else
                    return "Computer won by a horizontal line";
            }
        }
        else if (VertChecker(buttonMap) !=0){
            if(VertChecker(buttonMap) == 1){
                return"Player X won by a vertical line";
            }
            else {
                if(!pvc){
                    return "Player O won by a vertical line";
                }
                else
                    return "Computer won by a vertical line";
            }
        }
        return null;
    }

    private int diagChecker(int[][] buttonMap){
        if (    (buttonMap[0][0] == 1 && buttonMap[1][1] == 1 && buttonMap[2][2] == 1) ||
                (buttonMap[0][2] == 1 && buttonMap[1][1] == 1 && buttonMap[2][0] == 1)) {
            return 1;
        }
        else if((buttonMap[0][0] == 2 && buttonMap[1][1] == 2 && buttonMap[2][2] == 2) ||
                (buttonMap[0][2] == 2 && buttonMap[1][1] == 2 && buttonMap[2][0] == 2)) {
            return 2;
        }
        return 0;
    }

    private int HorizChecker(int[][] buttonMap){
        if(     (buttonMap[0][0] == 1 && buttonMap[0][1] == 1 && buttonMap[0][2] == 1) ||
                (buttonMap[1][0] == 1 && buttonMap[1][1] == 1 && buttonMap[1][2] == 1) ||
                (buttonMap[2][0] == 1 && buttonMap[2][1] == 1 && buttonMap[2][2] == 1)) {
            return 1;
        }
        else if((buttonMap[0][0] == 2 && buttonMap[0][1] == 2 && buttonMap[0][2] == 2) ||
                (buttonMap[1][0] == 2 && buttonMap[1][1] == 2 && buttonMap[1][2] == 2) ||
                (buttonMap[2][0] == 2 && buttonMap[2][1] == 2 && buttonMap[2][2] == 2)) {
            return 2;
        }
        return 0;
    }

    private int VertChecker(int[][] buttonMap){
        if ((buttonMap[0][0] == 1 && buttonMap[1][0] == 1 && buttonMap[2][0] == 1) ||
            (buttonMap[0][1] == 1 && buttonMap[1][1] == 1 && buttonMap[2][1] == 1) ||
            (buttonMap[0][2] == 1 && buttonMap[1][2] == 1 && buttonMap[2][2] == 1)) {
            return 1;
        }
        else if((buttonMap[0][0] == 2 && buttonMap[1][0] == 2 && buttonMap[2][0] == 2) ||
                (buttonMap[0][1] == 2 && buttonMap[1][1] == 2 && buttonMap[2][1] == 2) ||
                (buttonMap[0][2] == 2 && buttonMap[1][2] == 2 && buttonMap[2][2] == 2)) {
            return 2;
        }
        return 0;
    }
}

