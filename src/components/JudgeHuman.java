package components;

import UserInterface.EndingInterface;
import UserInterface.StartGame;
import model.ChessPiece;
import view.ChessBoardPanel;
import view.GameFrame;
import view.StatusPanel;

import java.awt.*;
import  java.util.ArrayList;

import static UserInterface.Set.negaColor;
import static view.GameFrame.chooseHint;


public class JudgeHuman { //给Hint和Robot用
//    private static int[][] numArray = JudgeRobot.numArray; //这样可以吗？——不可以，改变的时候会出问题!!!

    //    private int[][] numArray;
    public static int[][] ableArray;
    public static int[][] flipNumber = new int[8][8];
    public static ArrayList<int[][]> processArray; //黑白,Row,Col
    public static String winner;

    public static boolean judgeHuman(int i, int j) {
        ChessPiece thisPiece = GameFrame.controller.getCurrentPlayer(); //想要落子的那个地方
        Color A = thisPiece.getColor();
        flipNumber[i][j] = 0;
        int Co;
        int count = 0;
        ableArray = new int[8][8];
        for (int m = 0; m < 8; ++m) {
            for (int n = 0; n < 8; ++n) {
                ableArray[m][n] = JudgeRobot.numArray[m][n];
            }
        }

        if (A.equals(negaColor)) {
            Co = -1;
        } else {
            Co = 1;
        }


        //注意还需要每步走完后翻棋子！！！！！！

        if (JudgeRobot.numArray[i][j] == 0) {


            if (j + 2 <= 7) {
                if (JudgeRobot.numArray[i][j + 1] == -Co) {  //几种情况分类讨论
                    for (int q = j + 2; q <= 7; ++q) {
                        if (JudgeRobot.numArray[i][q] == 0) {
                            break;
                        } else if (JudgeRobot.numArray[i][q] == Co) {
//                            System.out.println("j+");
                            ableArray[i][j] = Co;
                            flipNumber[i][j] += q - j;
                            count++;
                            for (int m = j + 1; m < q; ++m) {
                                if (JudgeRobot.numArray[i][m] != 0) {
                                    ableArray[i][m] = Co;//翻棋 //???
                                }
                            }
                            break;      //这里不能用序号for循环的方式直接跳到底，因为有可能一个棋一箭双雕
                        }
                    }
                }
            }
            if (j - 2 >= 0) {
                if (JudgeRobot.numArray[i][j - 1] == -Co) {
                    for (int q = j - 2; q >= 0; --q) {
                        if (JudgeRobot.numArray[i][q] == 0) {
                            break;
                        } else if (JudgeRobot.numArray[i][q] == Co) {
//                            System.out.println("j-");
                            ableArray[i][j] = Co;
                            flipNumber[i][j] += j - q;
                            count++;
                            for (int m = j - 1; m > q; --m) {
                                if (JudgeRobot.numArray[i][m] != 0) {
                                    ableArray[i][m] = Co;//翻棋
                                }
                            }
                            break;
                        }

                    }
                }
            }
            if (i + 2 <= 7) {
                if (JudgeRobot.numArray[i + 1][j] == -Co) {
                    for (int q = i + 2; q <= 7; ++q) {
                        if (JudgeRobot.numArray[q][j] == 0) {
                            break;
                        } else if (JudgeRobot.numArray[q][j] == Co) {
//                            System.out.println("i+");
                            ableArray[i][j] = Co;
                            flipNumber[i][j] += q - i;
                            count++;
                            for (int m = i + 1; m < q; ++m) {
                                if (JudgeRobot.numArray[m][j] != 0) {
                                    ableArray[m][j] = Co;//翻棋
                                }
                            }
                            break;
                        }
                    }
                }
            }
            if (i - 2 >= 0) {
                if (JudgeRobot.numArray[i - 1][j] == -Co) {
                    for (int q = i - 2; q >= 0; --q) {
                        if (JudgeRobot.numArray[q][j] == 0) {
                            break;
                        } else if (JudgeRobot.numArray[q][j] == Co) {
//                            System.out.println("i-");
                            ableArray[i][j] = Co;
                            flipNumber[i][j] += i - q;
                            count++;
                            for (int m = i - 1; m > q; --m) {
                                if (JudgeRobot.numArray[m][j] != 0) {
                                    ableArray[m][j] = Co;//翻棋
                                }
                            }
                            break;
                        }
                    }
                }
            }
            if (i + 2 <= 7 && j + 2 <= 7) {
                if (JudgeRobot.numArray[i + 1][j + 1] == -Co) {
                    if (i >= j) {
                        for (int q = 2; q <= 7 - i; ++q) {
                            if (JudgeRobot.numArray[i + q][j + q] == 0) {
                                break;
                            } else if (JudgeRobot.numArray[i + q][j + q] == Co) {
//                                System.out.println("i+ j+");
                                ableArray[i][j] = Co;
                                flipNumber[i][j] += q;
                                count++;
                                for (int m = 1; m < q; ++m) {
                                    if (JudgeRobot.numArray[i + m][j + m] != 0) {
                                        ableArray[i + m][j + m] = Co;//翻棋
                                    }
                                }
                                break;
                            }
                        }
                    } else {
                        for (int q = 2; q <= 7 - j; ++q) {
                            if (JudgeRobot.numArray[i + q][j + q] == 0) {
                                break;
                            } else if (JudgeRobot.numArray[i + q][j + q] == Co) {
//                                System.out.println("i+ j+");
                                ableArray[i][j] = Co;
                                flipNumber[i][j] += q;
                                count++;
                                for (int m = 1; m < q; ++m) {
                                    if (JudgeRobot.numArray[i + m][j + m] != 0) {
                                        ableArray[i + m][j + m] = Co;//翻棋
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
            }
            if (i - 2 >= 0 && j - 2 >= 0) {
                if (JudgeRobot.numArray[i - 1][j - 1] == -Co) {
                    if (i >= j) {
                        for (int q = 2; q <= j; ++q) {
                            if (JudgeRobot.numArray[i - q][j - q] == 0) {
                                break;
                            } else if (JudgeRobot.numArray[i - q][j - q] == Co) {
//                                System.out.println("i- j-");
                                ableArray[i][j] = Co;
                                flipNumber[i][j] += q;
                                count++;
                                for (int m = 1; m < q; ++m) {
                                    if (JudgeRobot.numArray[i - m][j - m] != 0) {
                                        ableArray[i - m][j - m] = Co;//翻棋
                                    }
                                }
                                break;
                            }
                        }
                    } else {
                        for (int q = 2; q <= i; ++q) {
                            if (JudgeRobot.numArray[i - q][j - q] == 0) {
                                break;
                            } else if (JudgeRobot.numArray[i - q][j - q] == Co) {
//                                System.out.println("i- j-");
                                ableArray[i][j] = Co;
                                flipNumber[i][j] += q;
                                count++;
                                for (int m = 1; m < q; ++m) {
                                    if (JudgeRobot.numArray[i - m][j - m] != 0) {
                                        ableArray[i - m][j - m] = Co;//翻棋
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
            }
            if (i - 2 >= 0 && j + 2 <= 7) {
                if (JudgeRobot.numArray[i - 1][j + 1] == -Co) {
                    if (i >= 7 - j) {
                        for (int q = 2; q <= 7 - j; ++q) {
                            if (JudgeRobot.numArray[i - q][j + q] == 0) {
                                break;
                            } else if (JudgeRobot.numArray[i - q][j + q] == Co) {
//                                System.out.println("i- j+");
                                ableArray[i][j] = Co;
                                flipNumber[i][j] += q;
                                count++;
                                for (int m = 1; m < q; ++m) {
                                    if (JudgeRobot.numArray[i - m][j + m] != 0) {
                                        ableArray[i - m][j + m] = Co;//翻棋
                                    }
                                }
                            }
                        }
                    } else {
                        for (int q = 2; q <= i; ++q) {
                            if (JudgeRobot.numArray[i - q][j + q] == 0) {
                                break;
                            } else if (JudgeRobot.numArray[i - q][j + q] == Co) {
//                                System.out.println("i- j+");
                                ableArray[i][j] = Co;
                                flipNumber[i][j] += q;
                                count++;
                                for (int m = 1; m < q; ++m) {
                                    if (JudgeRobot.numArray[i - m][j + m] != 0) {
                                        ableArray[i - m][j + m] = Co;//翻棋
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (i + 2 <= 7 && j - 2 >= 0) {
                if (JudgeRobot.numArray[i + 1][j - 1] == -Co) {
                    if (7 - i >= j) {
                        for (int q = 2; q <= j; ++q) {
                            if (JudgeRobot.numArray[i + q][j - q] == 0) {
                                break;
                            } else if (JudgeRobot.numArray[i + q][j - q] == Co) {
//                                System.out.println("i+ j-");
                                ableArray[i][j] = Co;
                                flipNumber[i][j] += q;
                                count++;
                                for (int m = 1; m < q; ++m) {
                                    if (JudgeRobot.numArray[i + m][j - m] != 0) {
                                        ableArray[i + m][j - m] = Co;//翻棋
                                    }
                                }
                                break;

                            }

                        }
                    } else {
                        for (int q = 2; q <= 7 - i; ++q) {
                            if (JudgeRobot.numArray[i + q][j - q] == 0) {
                                break;
                            } else if (JudgeRobot.numArray[i + q][j - q] == Co) {
//                                System.out.println("i+ j-");
                                ableArray[i][j] = Co;
                                flipNumber[i][j] += q;
                                count++;
                                for (int m = 1; m < q; ++m) {
                                    if (JudgeRobot.numArray[i + m][j - m] != 0) {
                                        ableArray[i + m][j - m] = Co;//翻棋
                                    }
                                }
                                break;

                            }

                        }
                    }
                }
            }


            if (count != 0) {//如果可以走，则把根本上的棋盘换掉 + return true
                //System.out.println("judgeHuman done!");
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }

//    public static boolean noPossibleStep() { //一方没有可走的Step
//        int count = 0;
//        for (int m = 0; m < 8; ++m) {
//            for (int n = 0; n < 8; ++n) {
//                if (ableArray[m][n] - JudgeRobot.numArray[m][n] != 0) {
//                    count++;
//                }
//            }
//        }
//
//        if (count == 0) {
//            StatusPanel.setPlayerText("No Valid Step"); //*******最后要调整PlayerText的字体!!!
//            GameFrame.getStatusPanel().repaint();
//            try {
//                System.out.println("try done!");
//                Thread.sleep(10000);
//            } catch (InterruptedException ie) {
//            }
//            GameFrame.controller.swapPlayer();
//            GameFrame.getStatusPanel().repaint();
//            return true;
//        } else {
//            return false;
//        }
//    }

    public static void noPossibleStep_2(){ //另一方也没有可走的棋——游戏结束，判断胜负方
        if(Hint.judgeHint()){

            GameFrame.controller.swapPlayer();
            GameFrame.getStatusPanel().repaint();

            if(Hint.judgeHint()) {
                StatusPanel.setPlayerText("No Valid Step"); //*******最后要调整PlayerText的字体!!!
                GameFrame.getStatusPanel().repaint();
  //              GameFrame.setStatusText("Game Over"); //在之前都是In Progress
                GameFrame.getStatusPanel().repaint();
//****************************************************************
                if(blackScore > whiteScore){
//                    GameFrame.setWinnerText(black.name won!);
                    winner = "Black Wins!" ;
                }
                else{
//                    GameFrame.setWinnerText(white.name won!);
                    winner = "White Wins!";
                }
                EndingInterface endingInterface = new EndingInterface();
            }
        }
    }



    public static void putHumanPiece() {
//        blackScore = 0;
//        whiteScore = 0;
        int[][] array = new int[8][8];
        for (int m = 0; m < 8; ++m) {
            for (int n = 0; n < 8; ++n) {

                JudgeRobot.numArray[m][n] = ableArray[m][n];
                array[m][n] = ableArray[m][n];

                if (JudgeRobot.numArray[m][n] == -1) {
                    ChessBoardPanel.chessGrids[m][n].setChessPiece(ChessPiece.BLACK);
//                    blackScore++;
                } else if (JudgeRobot.numArray[m][n] == 1) {
                    ChessBoardPanel.chessGrids[m][n].setChessPiece(ChessPiece.WHITE);
//                    whiteScore++;
                } else {
                    ChessBoardPanel.chessGrids[m][n].setChessPiece(null);
                }
            }
        }
        processArray.add(array);
        scoreCounting();

    }

    public static void undoOperation() {
        if (processArray.size()>1) {
//            System.out.printf("Size is : %s", processArray.size());
            if(StartGame.isRobot == -1){
                for (int m = 0; m < 8; ++m) {
                    for (int n = 0; n < 8; ++n) {
                        JudgeRobot.numArray[m][n] = processArray.get(processArray.size() - 2)[m][n];
                    }
                }
                processArray.remove(processArray.size() - 1);
                GameFrame.controller.swapPlayer();
            }
            else if(StartGame.isRobot == 1 && processArray.size()>2){
                for (int m = 0; m < 8; ++m) {
                    for (int n = 0; n < 8; ++n) {
                        JudgeRobot.numArray[m][n] = processArray.get(processArray.size() - 3)[m][n];
                    }
                }
                processArray.remove(processArray.size() - 1);
                processArray.remove(processArray.size() - 1);
            }


            //Score 也需要一个ArrayList，撤回的时候可以一起回去！！！！！！！！！！！！！！！！！！！！！！！

//            System.out.println("Undo Check$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            for (int m = 0; m < 8; ++m) {
                for (int n = 0; n < 8; ++n) {
                    if (JudgeRobot.numArray[m][n] == -1) {
                        ChessBoardPanel.chessGrids[m][n].setChessPiece(ChessPiece.BLACK);
                    } else if (JudgeRobot.numArray[m][n] == 1) {
                        ChessBoardPanel.chessGrids[m][n].setChessPiece(ChessPiece.WHITE);
                    } else {
                        ChessBoardPanel.chessGrids[m][n].setChessPiece(null);
                    }
//                    System.out.printf("%3d",JudgeRobot.numArray[m][n]);
                }
//                System.out.println();
            }

            if (chooseHint == 1) {
                Hint.putHint();
            }
            scoreCounting();
        }
    }

    public static int blackScore;
    public static int whiteScore;

    public static void scoreCounting(){
        blackScore = 0;
        whiteScore = 0;
        System.out.println("scoreCounting%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        for (int m = 0; m < 8; ++m) {
            for (int n = 0; n < 8; ++n) {
                if (JudgeRobot.numArray[m][n] == -1) {
                    blackScore++;
                } else if (JudgeRobot.numArray[m][n] == 1) {
                    whiteScore++;
                }
                System.out.printf("%3d",JudgeRobot.numArray[m][n]);
            }
            System.out.println("blackScore: "+blackScore+" "+"whiteScore: "+whiteScore);
            System.out.println();
        }
        StatusPanel.setScoreText(blackScore, whiteScore);
    }
}
