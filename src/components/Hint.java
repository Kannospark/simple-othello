package components;

import model.ChessPiece;
import view.ChessBoardPanel;
import view.GameFrame;

import java.awt.*;

import static UserInterface.Set.negaColor;
import static UserInterface.Set.posiColor;

public class Hint {
    private static int[][] hintArray;

    public static void putHint() { //判断是否可以符合规则，即是否可以落子 //在cheating模式下用!!!
        hintArray = new int[8][8];
        int COUNT = 0;
        for(int i=0;i<8;++i){
            for(int j=0;j<8;++j){
                if(JudgeHuman.judgeHuman(i,j)){
                    ChessBoardPanel.chessGrids[i][j].setChessPiece(ChessPiece.GRAY);
                    hintArray[i][j] = 1;
                    COUNT=-1;
                }
//                System.out.printf("%3d",hintArray[i][j]);
            }
//            System.out.println();
        }

//        if(COUNT == 0){
//            System.out.println("The Game is Over!");
//            //添加弹窗，弹窗弹出来之后会自动暂停页面上的鼠标Listener，只允许用户选择弹窗上的按钮
//        }


    }

    public static void cancelHint(){

        for(int i=0;i<8;++i) {
            for (int j = 0; j < 8; ++j) {
                if(hintArray[i][j] == 1) {
                    ChessBoardPanel.chessGrids[i][j].setChessPiece(null);
                }
//                System.out.printf("%3d",hintArray[i][j]);
            }
//            System.out.println();
        }
        //System.out.println("Cancel Hint Done!");
    }

    public static boolean judgeHint() { //判断是否可以符合规则，即是否可以落子 //在无提示情况下用!!!
        int COUNT = 0;
        for(int i=0;i<8;++i){
            for(int j=0;j<8;++j){
                if(JudgeHuman.judgeHuman(i,j)){
                    COUNT = -1;
                }
            }
        }
        if(COUNT == 0){
            return true;
            //添加弹窗，弹窗弹出来之后会自动暂停页面上的鼠标Listener，只允许用户选择弹窗上的按钮
        }
        else {
            return false;
        }
    }

    public static void SHOW(){
        int[][] testArray = new int[8][8];
        for(int m=0;m<8;++m){
            for(int n=0;n<8;++n){
                if (ChessBoardPanel.chessGrids[m][n].getChessPiece() != null) {
                    if ((ChessBoardPanel.chessGrids[m][n].getChessPiece().getColor()).equals(Color.BLACK)) {
                        testArray[m][n] = -1;
                    }
                    else if((ChessBoardPanel.chessGrids[m][n].getChessPiece().getColor()).equals(Color.WHITE)) {
                        testArray[m][n] = 1;
                    }
                    else{
                        testArray[m][n] = 5;
                    }
                }
//                System.out.printf("%3d", testArray[m][m]);

            }
//            System.out.println();
        }

    }

    public static void paintNumArray(){
        for(int i=0;i<8;++i){
            for(int j=0;j<8;++j){
                System.out.printf("%5d", JudgeRobot.numArray[i][j]);
            }
            System.out.println();
        }
    }

}
