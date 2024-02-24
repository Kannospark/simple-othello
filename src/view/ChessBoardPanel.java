package view;

import components.ChessGridComponent;
import components.JudgeHuman;
import components.JudgeRobot;
import components.Hint;
import model.ChessPiece;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ChessBoardPanel extends JPanel {
    private final int CHESS_COUNT = 8; //又写了一遍有8*8个格子
     //8*8的大棋盘，每个元素是一个小格子+棋子
     public static ChessGridComponent[][] chessGrids;
     public static int cheatMode; // 0:不开(初始化)，1:开(取决于开关)

    public ChessBoardPanel(int width, int height) { //构造器
        this.setVisible(true); //可视化
        this.setFocusable(true);
        this.setLayout(null); //这里没有设置Panel的整体布局——但是前面可以设计一个Window里面几个Panel之间的布局
        this.setBackground(Color.BLACK); //设置背景为黑——这就是为什么小格子之间让出来的空隙是黑色
        int length = Math.min(width, height); //???
        this.setSize(length, length); //为什么要用minimum的长度setSize？？？这是在设置哪个Panel的Size？？？
        ChessGridComponent.gridSize = length / CHESS_COUNT; //每个格子平移的间距是length/8

//        *************************************************
//       ChessGridComponent.chessSizeWidth = (int) (ChessGridComponent.gridSize * 0.8); //棋子的外接长方形的尺寸!!!可在反转时改变!!!
//        ChessGridComponent.chessSizeHeight = (int) (ChessGridComponent.gridSize * 0.8); //需要改成可变的!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


        initialChessGrids();//return empty chessboard
        initialGame();//add initial four chess

//        repaint(); //重新画一遍
    }


    /**
     * set an empty chessboard
     */
    public void initialChessGrids() {
        ChessGridComponent.setChessSizeWidth(1);
        ChessGridComponent.setChessSizeHeight(1);
        chessGrids = new ChessGridComponent[CHESS_COUNT][CHESS_COUNT];

        //画出这个小格子
        for (int i = 0; i < CHESS_COUNT; i++) {
            for (int j = 0; j < CHESS_COUNT; j++) {
                ChessGridComponent gridComponent = new ChessGridComponent(i, j);
                gridComponent.setLocation(j * ChessGridComponent.gridSize, i * ChessGridComponent.gridSize);
                chessGrids[i][j] = gridComponent;
                this.add(chessGrids[i][j]);
            }
        }
    }

    /**
     * initial origin four chess
     */
    public void initialGame() {
        chessGrids[3][3].setChessPiece(ChessPiece.BLACK);
        chessGrids[3][4].setChessPiece(ChessPiece.WHITE);
        chessGrids[4][3].setChessPiece(ChessPiece.WHITE);
        chessGrids[4][4].setChessPiece(ChessPiece.BLACK);
        JudgeRobot.numArray = new int[8][8];
        JudgeRobot.numArray[3][3] = -1;
        JudgeRobot.numArray[3][4] = 1;
        JudgeRobot.numArray[4][3] = 1;
        JudgeRobot.numArray[4][4] = -1;
        int[][] copy = new int[8][8];
        copy[3][3] = -1;
        copy[3][4] = 1;
        copy[4][3] = 1;
        copy[4][4] = -1;
        JudgeHuman.processArray = new ArrayList<>();
        JudgeHuman.processArray.add(copy);
        cheatMode = 0;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    public boolean canClickGrid(int row, int col, ChessPiece currentPlayer) {
        //todo: complete this method
        return true;
    }

        public void repaintPanel(){
            repaint();
    }
}
