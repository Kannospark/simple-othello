//画每一个小格和里面的椭圆形棋子(在翻棋子的时候可以用椭圆变化)
//根据Listener对下子处有无棋子做判断
//每次下子后，有棋子的位置重新涂装paint
//flip();
//row/col getter
//set/get chessPiece

package components;

import UserInterface.StartGame;
import controller.GameController;
import components.JudgeHuman;
import model.*;
import view.ChessBoardPanel;
import view.GameFrame;


import java.awt.*;
import java.util.ArrayList;

import static components.JudgeHuman.processArray;
import static view.GameFrame.chooseHint;

public class ChessGridComponent extends BasicComponent {
    public static int chessSizeWidth; //棋子椭圆width
    public static int chessSizeHeight; //棋子椭圆width
    public static int gridSize; //gridSize从哪里给的数值？
    public static Color gridColor = new Color(	238, 216, 174);

    private ChessPiece chessPiece;
    private int row;
    private int col;
    public int[][] shouldFlip = new int[8][8]; //将需要翻转的棋子的数组坐标放入这个ArrayList


    public ChessGridComponent(int row, int col) { //位置
        this.setSize(gridSize, gridSize); //网格大小
        Dimension d = new Dimension(gridSize, gridSize);
        setPreferredSize(d);

        this.row = row;
        this.col = col;
    }
    public int getRow() { //没有用过row和col？？？那是怎么print出来8*8的方格呢？？？
        return row;
    }
    public int getCol() {
        return col;
    }
    //***************************************************************************************
    public static void setChessSizeWidth(double widthPercent) { chessSizeWidth = (int) (widthPercent * 0.8 * ChessGridComponent.gridSize);
    System.out.println("setWidth");}
    public static void setChessSizeHeight(double heightPercent){ chessSizeHeight = (int) (heightPercent * 0.8 * ChessGridComponent.gridSize);}

    public ChessPiece getChessPiece() {
        return chessPiece; //chessPiece的信息只有棋子的Color
    }

    public void setChessPiece(ChessPiece chessPiece) { //等价于setChessColor
        this.chessPiece = chessPiece;
    }


    @Override
    public void onMouseClicked() {
        //System.out.printf("%s clicked (%d, %d)\n", GameFrame.controller.getCurrentPlayer(), row, col);
        //todo: complete mouse click method

        //需要加入关闭hint和开启hint的操作!!!

        //创建对象

        if (GameFrame.controller.canClick(row, col)) {
            if (this.chessPiece == ChessPiece.GRAY || this.chessPiece == null) { //如果这个位置没有棋，那么下下来当前玩家的棋色
                //在这里进行判断，下这一步是否符合规则逻辑！！！
                //如果这里可以下的话，所有可以翻牌的位置都需要换颜色！！！
                if(JudgeHuman.judgeHuman(getRow(),getCol()) || ChessBoardPanel.cheatMode == 1){ //判断是否这里Human可以下子
                    GameFrame.reviewCount = 0;
                    if(chooseHint == 1) {
                        Hint.cancelHint(); //无hint模式：Hint.judgeHint; 加if!!!
                    }
                    GameFrame.controller.getGamePanel().repaint();

                    if(ChessBoardPanel.cheatMode == 0){
                        JudgeHuman.putHumanPiece();
                    }
                    else if(ChessBoardPanel.cheatMode == 1){
                        int[][] array = new int[8][8];
                        for(int m=0;m<8;++m){
                            for(int n=0;n<8;++n){
                                array[m][n] = processArray.get(processArray.size()-1)[m][n];
                            }
                        }

                        if(GameFrame.controller.getCurrentPlayer().equals(ChessPiece.BLACK)){
                            JudgeRobot.numArray[getRow()][getCol()] = -1;
                            array[getRow()][getCol()] = -1;
                            ChessBoardPanel.chessGrids[getRow()][getCol()].setChessPiece(ChessPiece.BLACK);
                        }
                        else if(GameFrame.controller.getCurrentPlayer().equals(ChessPiece.WHITE)){
                            JudgeRobot.numArray[getRow()][getCol()] = 1;
                            array[getRow()][getCol()] = 1;
                            ChessBoardPanel.chessGrids[getRow()][getCol()].setChessPiece(ChessPiece.WHITE);
                        }
                       JudgeHuman.scoreCounting();
                        processArray.add(array);
                        System.out.println(JudgeRobot.numArray[getRow()][getCol()]);
                    }

// ///                   Hint.paintNumArray();

                    GameFrame.controller.swapPlayer(); //换对方玩家
                    GameFrame.controller.getGamePanel().repaint();
                    if(StartGame.isRobot == -1) {
                        if (chooseHint == 1) {
                            Hint.putHint(); //无hint模式：不写东西;——加if!!!
                        }
// ///                   Hint.paintNumArray();

//                    GameFrame.controller.getGamePanel().revalidate();
                        GameFrame.controller.getGamePanel().repaint();

                        JudgeHuman.noPossibleStep_2();
                    }
                    else if(StartGame.isRobot == 1){
                        if(JudgeRobot.goRobot()){
                            JudgeRobot.putRobotPiece();
                            GameFrame.controller.swapPlayer();
                            if (chooseHint == 1) {
                                Hint.putHint(); //无hint模式：不写东西;——加if!!!
                            }
                            repaint();
                        }


                        JudgeHuman.noPossibleStep_2();
                    }
                }
                //this.chessPiece = GameFrame.controller.getCurrentPlayer(); //决定下棋的那个格子的棋子颜色
            }

            //加入下子之后的逻辑！！！把其它该翻的位置都翻过来！！！
        }
    }


    public void drawPiece(Graphics g) { //画加上刚刚下的子的所有小格子 //由于这一步是click判断和下子之后，所以棋盘底层已经换成想要的棋盘了，没问题


        g.setColor(gridColor); //把将要涂装的颜色设置为gridColor
        g.fillRect(1, 1, this.getWidth() - 2, this.getHeight() - 2); //把方格子涂满这个颜色
//        System.out.printf("chessSizeWidth:%s, gridSize%s\n",chessSizeWidth,gridSize);
        if (ChessBoardPanel.chessGrids[getRow()][getCol()].getChessPiece() != null) { //如果这个格子里面有棋子 this.chessPiece

            //这里需要判断整个位置是否需要翻牌！！！
            g.setColor(ChessBoardPanel.chessGrids[getRow()][getCol()].getChessPiece().getColor()); //把将要涂装的颜色设置为当前格子里的chessPiece的颜色
            g.fillOval((gridSize - chessSizeWidth) / 2, (gridSize - chessSizeHeight) / 2, chessSizeWidth, chessSizeHeight);


            //涂满一个长方形的内切椭圆——x:外长方形左上角横坐标，y:外长方形左上角纵坐标，椭圆width，椭圆height
            //System.out.printf("%s, %s, %s\n",ChessBoardPanel.chessGrids[getRow()][getCol()].getChessPiece().getColor(), getRow(), getCol());
        }
    }

    //在哪里可以遍历print整个8*8的格子啊？？？


    @Override
    public void paintComponent(Graphics g) {
        super.printComponents(g);
                drawPiece(g);
        //System.out.println("paintComponent done!");
    }




}
