package view;


import UserInterface.UserInterface;
import components.Hint;
import components.JudgeHuman;
import components.JudgeRobot;
import controller.GameController;
import model.ChessPiece;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static UserInterface.Main.bc;
import static UserInterface.Main.mainFrame;
import static UserInterface.Set.isMaximum;
import static UserInterface.ChangeUser.userName;
import static UserInterface.Set.isMaximum;
import static controller.GameController.cc;
import static controller.GameController.judgeCC;
import static view.StatusPanel.setPlayerText;


public class GameFrame extends JFrame {
    public static int chooseHint;
//    public static Color negaColor;
//    public static Color posiColor;

    public static GameController controller;
    private ChessBoardPanel chessBoardPanel;
    private static StatusPanel statusPanel;
    public  static  int reviewCount;


    public GameFrame(int frameSize) {

        this.setTitle(userName + "'s game");
        this.setLayout(null);

        //获取窗口边框的长度，将这些值加到主窗口大小上，这能使窗口大小和预期相符
        Insets inset = this.getInsets();
        if (!isMaximum)
            this.setSize(1200,800);
        if (isMaximum) {
            this.setExtendedState(Frame.MAXIMIZED_BOTH);
        }

        this.setLocationRelativeTo(null);


        chessBoardPanel = new ChessBoardPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.7));
        chessBoardPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, (this.getHeight() - chessBoardPanel.getHeight()) / 3);

        statusPanel = new StatusPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.1));
        statusPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, 0);
        controller = new GameController(chessBoardPanel, statusPanel);
        controller.setGamePanel(chessBoardPanel);

        this.add(chessBoardPanel);
        this.add(statusPanel);


        JButton restartBtn = new JButton("Restart");
        restartBtn.setSize(100, 50);
        restartBtn.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, (this.getHeight() + chessBoardPanel.getHeight()) / 2);
        add(restartBtn);
        restartBtn.addActionListener(e -> { //***********************************Restart按钮
            System.out.println("click restart Btn"); //需要写restart代码!!!
            dispose();
            mainFrame = new GameFrame(800);
            mainFrame.setVisible(true);
            GameFrame.controller.getGamePanel().repaint();
            //需要加上清空文档里储存的游戏步骤数据!!!______________
        });
//***********************************************************************************

        JButton loadGameBtn = new JButton("Load");
        loadGameBtn.setSize(100, 50);
        loadGameBtn.setLocation(restartBtn.getX() + restartBtn.getWidth() + 30, restartBtn.getY());
        add(loadGameBtn);
        loadGameBtn.addActionListener(e -> {
            System.out.println("clicked Load Btn");
            controller.readFileData();
            JOptionPane.showMessageDialog(null, "you have loaded a game!");
            for (int m = 0; m < 8; ++m) {
                for (int n = 0; n < 8; ++n) {
                    if (JudgeRobot.numArray[m][n] == -1) {
                        ChessBoardPanel.chessGrids[m][n].setChessPiece(ChessPiece.BLACK);
                    } else if (JudgeRobot.numArray[m][n] == 1) {
                        ChessBoardPanel.chessGrids[m][n].setChessPiece(ChessPiece.WHITE);
                    } else {
                        ChessBoardPanel.chessGrids[m][n].setChessPiece(null);
                    }
                }
            }
            repaint();
        });
//***********************************************************************************

        JButton saveGameBtn = new JButton("Save");
        saveGameBtn.setSize(100, 50);
        saveGameBtn.setLocation(loadGameBtn.getX() + restartBtn.getWidth() + 30, restartBtn.getY());
        add(saveGameBtn);
        saveGameBtn.addActionListener(e -> {
            System.out.println("clicked Save Btn");
            JOptionPane.showMessageDialog(null, "you have save a game!");
            try {
                controller.writeDataToFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
//***********************************************************************************

        JButton undoBtn = new JButton("Undo");
        undoBtn.setSize(100, 50);
        undoBtn.setLocation(saveGameBtn.getX() + restartBtn.getWidth() + 30, restartBtn.getY());
        add(undoBtn);
        undoBtn.addActionListener(e -> {
            System.out.println("clicked Undo Btn");
            JudgeHuman.undoOperation();
            repaint(); //因为这里其它的Score和状态等等都需要回到之前的状态
            //_______________________________________________需要加上删除文件中的步骤吗？？？
        });

        JButton homeBtn = new JButton("Home");
        homeBtn.setSize(100, 50);
        homeBtn.setLocation(50,100);
        add(homeBtn);
        homeBtn.addActionListener(e -> {
            System.out.println("clicked Home Button");
            try {
                if(judgeCC == 1) {
                    cc.stop();
                }else{
                    bc.stop();
                }
                this.dispose();
                UserInterface.userInterface();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


//***********************************************************************
//        Hint.paintNumArray();
        JButton hintBtn = new JButton("Hint");
        hintBtn.setSize(100, 50);
        hintBtn.setLocation(50,30);
        add(hintBtn);
        hintBtn.addActionListener(e -> {
                    System.out.println("clicked Hint button");
                    System.out.println(chooseHint);
                    if (chooseHint == 0) {
                        Hint.putHint();
                        repaint();
                        chooseHint = 1;
                    } else {
                        Hint.cancelHint();
                        repaint();
                        chooseHint = 0;
                    }
                });

 //********************************************************************
            JButton cheatBtn = new JButton("Normal");
            cheatBtn.setSize(100, 50);
            cheatBtn.setLocation(50,170);
            add(cheatBtn);
            cheatBtn.addActionListener(e -> {
                System.out.println("clicked Cheat button");
                System.out.println(ChessBoardPanel.cheatMode);
                if(ChessBoardPanel.cheatMode == 0){
                    ChessBoardPanel.cheatMode = 1;
                    cheatBtn.setText("Cheat");
                    repaint();
                }
                else {
                    ChessBoardPanel.cheatMode = 0;
                    cheatBtn.setText("Normal");
                    repaint();
                }
        });

//********************************************************************
        JButton reviewBtn = new JButton("Review");
        reviewBtn.setSize(100, 50);
        reviewBtn.setLocation(50,240);
        add(reviewBtn);
        reviewBtn.addActionListener(e -> {
            System.out.println("clicked Review button");
            if(reviewCount < JudgeHuman.processArray.size()-1){

                for(int m=0;m<8;++m){
                    for(int n=0;n<8;++n){
                        if (JudgeHuman.processArray.get(reviewCount)[m][n] == -1) {
                            ChessBoardPanel.chessGrids[m][n].setChessPiece(ChessPiece.BLACK);
                        } else if (JudgeHuman.processArray.get(reviewCount)[m][n] == 1) {
                            ChessBoardPanel.chessGrids[m][n].setChessPiece(ChessPiece.WHITE);
                        } else {
                            ChessBoardPanel.chessGrids[m][n].setChessPiece(null);
                        }
                    }
                }

                repaint();
                reviewCount++;
            }
            else if(reviewCount == JudgeHuman.processArray.size()-1){
                for(int m=0;m<8;++m){
                    for(int n=0;n<8;++n){
                        if (JudgeHuman.processArray.get(reviewCount)[m][n] == -1) {
                            ChessBoardPanel.chessGrids[m][n].setChessPiece(ChessPiece.BLACK);
                        } else if (JudgeHuman.processArray.get(reviewCount)[m][n] == 1) {
                            ChessBoardPanel.chessGrids[m][n].setChessPiece(ChessPiece.WHITE);
                        } else {
                            ChessBoardPanel.chessGrids[m][n].setChessPiece(null);
                        }
                    }
                }
                if (chooseHint == 1) {
                    Hint.putHint(); //无hint模式：不写东西;——加if!!!
                }
                repaint();
                reviewCount++;
            }




        });


        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }



    public static StatusPanel getStatusPanel(){
        return statusPanel;
    }
}
