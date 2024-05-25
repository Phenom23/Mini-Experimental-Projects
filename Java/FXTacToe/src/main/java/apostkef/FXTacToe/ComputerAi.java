package apostkef.FXTacToe;
import java.util.Arrays;

public class ComputerAi
{
    private int[][] buttonMap;
    double randomness;

    public ComputerAi(int[][] buttonMap, int diff){
        this.buttonMap = buttonMap;
        if(diff == 3)
            this.randomness = 0.25;
        else if (diff == 2)
            this.randomness = 0.35;
        else if (diff == 1)
            this.randomness = 0.45;
    }

    public int[] pick(int diff, int computerTries){

        if(diff == 4){ //extreme difficulty
            if(!Arrays.equals(
                    twoPlusOne(true), new int[]{-1, -1}) ){
                return twoPlusOne(true);
            }
            else if (buttonMap[1][1] == 1 && buttonMap [2][2] == 1 && buttonMap[0][2] == 0 &&
                    Arrays.equals( twoPlusOne(false), new int[]{-1, -1} ) ){
                return new int[]{0,2}; //edgecase
            }
            else if (!Arrays.equals( twoPlusOne(false), new int[]{-1, -1} ) ){
                return twoPlusOne(false);
            }
            if (buttonMap[1][1] == 0){ //Preference to start from the middle if possible
                return new int[]{1,1};
            }
            else if (!Arrays.equals(onePlusOne(), new int[]{-1, -1})){
                return onePlusOne();
            }
            else{
                return RandomMove();
            }
        }
        else if(diff == 3){ //hard difficulty
            double rdm = Math.random();
            if(rdm < randomness && computerTries < 4){
                randomness-=0.15;
                System.out.println(randomness);
                return RandomMove();
            }
            else if(!Arrays.equals(
                    twoPlusOne(true), new int[]{-1, -1}) ){
                return twoPlusOne(true);
            }
            else if (buttonMap[1][1] == 1 && buttonMap [2][2] == 1 && buttonMap[0][2] == 0 &&
                    Arrays.equals( twoPlusOne(false), new int[]{-1, -1} ) ){
                return new int[]{0,2}; //edgecase
            }
            else if (!Arrays.equals( twoPlusOne(false), new int[]{-1, -1} ) ){
                return twoPlusOne(false);
            }
            else if (!Arrays.equals(onePlusOne(), new int[]{-1, -1})){
                return onePlusOne();
            }
            else{
                return RandomMove();
            }
        }
        else if (diff == 2){ //normal difficulty
            double rdm = Math.random();
            if(rdm < randomness && computerTries < 4){
                randomness-=0.15;
                System.out.println(randomness);
                return RandomMove();
            }
            else if(!Arrays.equals(
                    twoPlusOne(false), new int[]{-1, -1}) ){
                return twoPlusOne(false);
            }
            else if (buttonMap[1][1] == 1 && buttonMap [2][2] == 1 && buttonMap[0][2] == 0 &&
                    Arrays.equals( twoPlusOne(false), new int[]{-1, -1} ) ){
                return new int[]{0,2}; //edgecase
            }
            else if (!Arrays.equals( twoPlusOne(true), new int[]{-1, -1} ) ){
                return twoPlusOne(true);
            }
            else if (!Arrays.equals(onePlusOne(), new int[]{-1, -1})){
                return onePlusOne();
            }
            else{
                return RandomMove();
            }
        }
        else{ //easy difficulty
            double rdm = Math.random();
            if(rdm < randomness && computerTries < 4){
                randomness-=0.15;
                System.out.println(randomness);
                return RandomMove();
            }
            else if(!Arrays.equals(
                    twoPlusOne(false), new int[]{-1, -1}) ){
                return twoPlusOne(false);
            }
            else if (buttonMap[1][1] == 1 && buttonMap [2][2] == 1 && buttonMap[0][2] == 0 &&
                    Arrays.equals( twoPlusOne(false), new int[]{-1, -1} ) ){
                return new int[]{0,2}; //edgecase
            }
            else if (!Arrays.equals( twoPlusOne(true), new int[]{-1, -1} ) ){
                return twoPlusOne(true);
            }
            else if (!Arrays.equals(onePlusOne(), new int[]{-1, -1})){
                return onePlusOne();
            }
            else{
                return RandomMove();
            }
        }
    }

    private int[] twoPlusOne(boolean attack){  //finds the third blank from 2 of the same
        int target;
        if(!attack){target = 1;} else{target = 2;}

        //horizontal checking
        for(int row = 0; row <3; row++){

            if (buttonMap[row][0] == 0 && buttonMap[row][1] == target && buttonMap[row][2] == target){
                return new int[]{row,0};
            }
            else if (buttonMap[row][0] == target && buttonMap[row][1] == 0  &&  buttonMap[row][2] == target){
                return new int[]{row,1};
            }
            else if(buttonMap[row][0] == target && buttonMap[row][1] == target && buttonMap[row][2] == 0){
                return new int[]{row,2};
            }
        }

        //vertical checking
        for(int col = 0; col <3; col++){

            if (buttonMap[0][col] == 0 && buttonMap[1][col] == target && buttonMap[2][col] == target){
                return new int[]{0,col};
            }
            else if (buttonMap[0][col] == target && buttonMap[1][col] == 0  &&  buttonMap[2][col] == target){
                return new int[]{1,col};
            }
            else if(buttonMap[0][col] == target && buttonMap[1][col] == target && buttonMap[2][col] == 0){
                return new int[]{2,col};
            }
        }

        //main diagonal checking
        if(buttonMap[0][0] == 0 && buttonMap[1][1] == target && buttonMap[2][2] == target){
            return new int[]{0, 0};
        }
        else if (buttonMap[0][0] == target && buttonMap[1][1] == 0 && buttonMap[2][2] == target){
            return new int[]{1,1};
        }
        else if(buttonMap[0][0] == target && buttonMap[1][1] == target && buttonMap[2][2] == 0){
            return new int[]{2,2};
        }

        //secondary diagonal checking
        if(buttonMap[0][2] == 0 && buttonMap[1][1] == target && buttonMap[2][0] == target){
            return new int[]{0,2};
        }
        else if (buttonMap[0][2] == target && buttonMap[1][1] == 0 && buttonMap[2][0] == target) {
            return new int[]{1,1};
        }
        else if(buttonMap[0][2] == target && buttonMap[1][1] == target && buttonMap[2][0] == 0){
            return new int[]{2,0};
        }
        return new int[]{-1,-1};
    }

    private int[] onePlusOne(){

        //horizontal checking
        for(int row = 0; row <3; row++){

            if (buttonMap[row][0] == 0 && buttonMap[row][1] == 2  &&  buttonMap[row][2] == 0){
                return new int[]{row,0};
            }
            else if ((buttonMap[row][0] == 2 && buttonMap[row][1] == 0 && buttonMap[row][2] == 0) ||
                    (buttonMap[row][0] == 0 && buttonMap[row][1] == 0 && buttonMap[row][2] == 2)) {
                return new int[]{row,1};
            }
        }

        //vertical checking
        for(int col = 0; col <3; col++){

            if (buttonMap[0][col] == 0 && buttonMap[1][col] == 2 && buttonMap[2][col] == 0){
                return new int[]{0,col};
            }
            else if ((buttonMap[0][col] == 2 && buttonMap[1][col] == 0  &&  buttonMap[2][col] == 0) ||
                    (buttonMap[0][col] == 0 && buttonMap[1][col] == 0 && buttonMap[2][col] == 2)){
                return new int[]{1,col};
            }
        }

        //main diagonal checking
        if(buttonMap[0][0] == 0 && buttonMap[1][1] == 2 && buttonMap[2][2] == 0){
            return new int[]{0, 0};
        }
        else if((buttonMap[0][0] == 2 && buttonMap[1][1] == 0 && buttonMap[2][2] == 0) ||
                (buttonMap[0][0] == 0 && buttonMap[1][1] == 0 && buttonMap[2][2] == 2)){
            return new int[]{1,1};
        }

        //secondary diagonal checking
        if(buttonMap[0][2] == 0 && buttonMap[1][1] == 2 && buttonMap[2][0] == 0){
            return new int[]{0,2};
        }
        else if((buttonMap[0][2] == 2 && buttonMap[1][1] == 0 && buttonMap[2][0] == 0) ||
                (buttonMap[0][2] == 0 && buttonMap[1][1] == 0 && buttonMap[2][0] == 2)){
            return new int[]{1,1};
        }
        return new int[]{-1,-1};
    }

    private int[] RandomMove(){
        for(int row = 0; row <3; row++){
            for(int col =0; col <3; col++){
                if(this.buttonMap[row][col] == 0){
                    return new int[]{row,col};
                }
            }
        }
        return new int[]{-1,-1};
    }
}
