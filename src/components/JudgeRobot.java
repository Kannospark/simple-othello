package components;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import view.ChessBoardPanel;
import model.ChessPiece;
import components.*;
import view.GameFrame;

import static UserInterface.StartGame.easyMode;


public class JudgeRobot { //给出row和col

    public static int[][] numArray; //最终转化成Panel的棋盘
    private static int C;
    private static int ROW;
    private static int COL;

    public static boolean goRobot(){
        int maxFlipNumber = 0;
        int minFlipNumber = 99;
        C = 0;
        ROW = 0;
        COL = 0;
        for(int i=0; i<8; ++i){
            for(int j=0; j<8; ++j){
                boolean judge = JudgeHuman.judgeHuman(i,j);
                int flipNumber = JudgeHuman.flipNumber[i][j];
                if(easyMode == 0) {
                    if (judge && (flipNumber > maxFlipNumber)) {
//                        System.out.println("&&&");
//                        System.out.printf("maxFlipNumber : %s", maxFlipNumber);
//                        System.out.printf("JudgeHuman.flipNumber : %s\n", JudgeHuman.flipNumber[i][j]);
                        maxFlipNumber = flipNumber;
                        ROW = i;
                        COL = j;
                        C = 1;
//                    System.out.println("ROW: "+ROW);
//                    System.out.println("COL: "+COL);
                        //Arrays.copyOf(dataType[] srcArray,int length);
//                    for(int m=0;m<8;++m){
//                        for(int n=0;n<8;++n){
//                            copy[m][n] = JudgeHuman.ableArray[m][n];
//                            System.out.printf("%3d",copy[m][n]);
//                        }
//                        System.out.println();
//                    }
                    }
                }
                else if(easyMode == 1){
                    if (judge && (flipNumber < minFlipNumber)) {
//                        System.out.println("&&&");
//                        System.out.printf("minFlipNumber : %s", minFlipNumber);
//                        System.out.printf("JudgeHuman.flipNumber : %s\n", JudgeHuman.flipNumber[i][j]);
                        minFlipNumber = flipNumber;
                        ROW = i;
                        COL = j;
                        C = 1;
                    }
                }
            }
        }



        if(C == 1){return  true;}
        else{return false;}

    }



    public static boolean putRobotPiece(){
        if( C == 1){
                JudgeHuman.judgeHuman(ROW,COL);
                JudgeHuman.putHumanPiece(); //这里面已经有scoreCounting了
            return  true;
        }
        else {
            return false;
        }

    }



}


//        try {
//                System.out.println("Robot try done!********************************");
//                Thread.sleep(1000);
//            } catch (InterruptedException ie) {
//            }

//chessGrids[3][3].setChessPiece(ChessPiece.BLACK);

