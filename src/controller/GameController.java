//设置初始地游戏数据(但不是画，只是设置数据)
//交换棋子颜色
//设置最新Score和最新游戏状态——可改变计分规则!!!
//获取当前棋子颜色
//获取棋盘所在地Panel画布
//向文件读取和写入棋盘信息
//判断是否可点击

package controller;

import components.JudgeHuman;
import components.JudgeRobot;
import model.ChessPiece;
import view.*;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.List;

import static UserInterface.ChangeUser.userName;
import static UserInterface.Main.bc;
import static UserInterface.Set.negaColor;
import static components.JudgeHuman.*;


public class GameController {


    private ChessBoardPanel gamePanel; //棋盘所在的画布区域
    private StatusPanel statusPanel; //比赛进程所在的画布区域
    private static ChessPiece currentPlayer; //当前棋子的颜色

    private String path = " /save";

    public GameController(ChessBoardPanel gamePanel, StatusPanel statusPanel) { //构造器
        this.gamePanel = gamePanel; //构造棋盘所在的画布
        this.statusPanel = statusPanel; //构造比赛进程所在的画布
        this.currentPlayer = ChessPiece.BLACK; //设置第一步的棋子颜色为BLACK

    }

    public void swapPlayer() { //交换棋子颜色
        countScore(); //在交换前先把前一个颜色下的Score加上
        currentPlayer = (currentPlayer == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK; //交换棋子颜色
        //expression1 ? expression2 : expression3
        //其中，expression1是一个布尔表达式。如果expression1为真，那么expression2被求值；反之，expression3被求值；

        statusPanel.setPlayerText(currentPlayer.name()); //设置最新Player显示 //……………………………………………………………………………………………………
        statusPanel.setScoreText(blackScore, whiteScore); //设置最新Score显示
    }


    public static AudioClip cc;
    public static int judgeCC=0;
    public static int judgeExciting = 0;
    public void countScore() { //对双方的得分进行计数!!! 可改变计分规则!!! //************************分数的设定等到其它功能都改完了再改
        //todo: modify the countScore method
        //这里modify之后可以实现计分规则的重新设置!!!
        //可以设置为吃掉的敌方棋子个数!!!
        if (currentPlayer == ChessPiece.BLACK) {
            blackScore++;
        } else {
            whiteScore++;
        }
        if(blackScore+whiteScore > 50 && judgeExciting == 0){
            bc.stop();
            URL url = null; //开启音乐
            File f1 = new File("./BGM/3.wav");
            try {
                url = f1.toURL();
            } catch (MalformedURLException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }
            cc = Applet.newAudioClip(url);
            cc.loop();
            judgeCC = 1;
            judgeExciting = 1;
        }
    }


    public ChessPiece getCurrentPlayer() {
        return currentPlayer;
    } //获取当前棋子颜色

    public static void setCurrentPlayer(ChessPiece chessPiece){
        currentPlayer = chessPiece;
    }


    public ChessBoardPanel getGamePanel() {
        return gamePanel;
    } //获取棋盘所在的画布


    public void setGamePanel(ChessBoardPanel gamePanel) {
        this.gamePanel = gamePanel;
    }


    public void readFileData() { //从file中读取棋盘
        //todo: read data from file
        boolean judge = false;
        List<String> fileData = new ArrayList<>(); //fileData以String的方式被放进List
        try {
            //File file = new File()
            FileReader fileReader = new FileReader("./save/"+userName+".txt"); //建立新fileReader
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            //BufferedReader和BufferedWriter在内存中会自带一个8kb的字节缓冲区，并且他提供了一个程序员比较喜欢用的方法readLine();

             String player = bufferedReader.readLine();
             if(Integer.parseInt(player) == 1){
                 currentPlayer = ChessPiece.WHITE;
             }
             else if(Integer.parseInt(player) == -1){
                 currentPlayer = ChessPiece.BLACK;
             }
             else{
                 JOptionPane.showMessageDialog(null, "Invalid file!");
                 judge = true;
             }
             if(!judge) {
                 statusPanel.setPlayerText(currentPlayer.name()); //设置最新Player显示 //……………………………………………………………………………………………………
                 statusPanel.setScoreText(blackScore, whiteScore); //设置最新Score显示

                 String line;
                 while ((line = bufferedReader.readLine()) != null) { //当file里面还有剩余的String的话，就继续读，没有就停下
                     fileData.add(line); //是一行一行地放入fileData这个文件里——所以棋盘信息在文件里应该以什么形式组织???
                 }
                 int i = 0;
                 for (String str : fileData) {
                     if (i == 8) break;
                     String[] stringlist = str.split(" ");
                     for (int j = 0; j < 8; j++) {
                         JudgeRobot.numArray[i][j] = Integer.parseInt(stringlist[j]);
                         //System.out.print(JudgeRobot.numArray[i][j]);
                     }
                     //System.out.println();
                     i++;
                 }
                 JudgeHuman.noPossibleStep_2();

                 fileData.forEach(System.out::println); //遍历fileData内部的所有元素并println
             }
        } catch (IOException e) { //即使没有文件也不会直接关闭程序!!!
            e.printStackTrace();
        }
    }

    public void writeDataToFile() throws IOException { //把每一次的棋盘信息存到一个文件里面!!!内容，形式
        //todo: write data into file
        String fileName = "./save/";
        File file = new File(fileName);
        File checkFile = new File(fileName+userName+".txt");
        if (!file.exists()) {
            file.mkdirs();// mkdirs创建多级目录
        }
        try {
            // 二、检查目标文件是否存在，不存在则创建
            if (!checkFile.exists()) {
                checkFile.createNewFile();// 创建目标文件
            }
            else{
                System.out.println("sdufhiawehucehncunhejcurehcuqfnhi");
                    checkFile.delete();
                    checkFile.createNewFile();
            }

            FileWriter writer = new FileWriter(checkFile, false);


            ChessPiece thisPiece = GameFrame.controller.getCurrentPlayer(); //想要落子的那个地方//记录当前玩家
            Color A = thisPiece.getColor();
            int Co;
            if (A.equals(negaColor)) {
                Co = -1;
            } else {
                Co = 1;
            }
            writer.append(String.valueOf(Co)+"\n");
            // 三、向目标文件中写入内容
            // FileWriter(File file, boolean append)，append为true时为追加模式，false或缺省则为覆盖模式
            Collections.reverse(JudgeHuman.processArray);
            for(int[][] array : JudgeHuman.processArray) {
                for(int i = 0;i<8;i++){
                for(int j = 0 ; j<8;j++){
                    if(j!=7) {
                        System.out.print(array[i][j] + " ");
                        writer.append(String.valueOf(array[i][j]) + " ");
                    }
                    if(j==7){
                        System.out.println(array[i][j]);
                        writer.append(String.valueOf(array[i][j])+"\n");
                        if(i==7){
                            writer.append("\n");
                        }
                    }
                }
            }
            }
            Collections.reverse(JudgeHuman.processArray);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean canClick(int row, int col) {
        return gamePanel.canClickGrid(row, col, currentPlayer);
    }
}
