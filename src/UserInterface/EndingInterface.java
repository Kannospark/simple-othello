package UserInterface;

import components.JudgeHuman;
import view.GameFrame;

import javax.swing.*;
import java.awt.*;

import static UserInterface.Main.bc;
import static UserInterface.Main.mainFrame;
import static controller.GameController.cc;
import static controller.GameController.judgeCC;

public class EndingInterface{
    public EndingInterface(){
        JFrame window = new JFrame(JudgeHuman.winner);
        window.setSize(420,200);
        window.setLayout(null);
        window.setVisible(true);
        int width = window.getWidth();
        int height = window.getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension Screensize = kit.getScreenSize();
        int screenWidth = Screensize.width;
        int screenHeight = Screensize.height;
        window.setLocation((screenWidth-width)/2,(screenHeight-height)/2); //居中
        Font f = new Font("宋体",Font.BOLD,15);


//        JLabel score = new JLabel();
//
//        JLabel blackWin = new JLabel("Black Win!");
//        blackWin.setLocation((window.getWidth()-blackWin.getWidth())/2,(window.getHeight()-blackWin.getHeight())/2);
//
//        JLabel whiteWin = new JLabel("White Win!");
//        whiteWin.setLocation((window.getWidth()-whiteWin.getWidth())/2,(window.getHeight()-whiteWin.getHeight())/2);

//        if(JudgeHuman.winner == 1){
//            window.add(whiteWin);
//        }
//        else{
//            window.add(blackWin);
//        }

        JButton restartBtn = new JButton("restart");
        restartBtn.setFont(f);
        restartBtn.setSize(150,50);
        restartBtn.setLocation(30,30);
        restartBtn.addActionListener(e -> {
            window.dispose();
            mainFrame.dispose();
            mainFrame = new GameFrame(800);
            if(judgeCC == 1)
            cc.stop();
            else{
                bc.stop();
            }
            GameFrame.controller.getGamePanel().repaint();});
           window.add(restartBtn);


        JButton exitBtn = new JButton("Home");
        exitBtn.setFont(f);
        exitBtn.setSize(150,50);
        exitBtn.setLocation(210,30);
        exitBtn.addActionListener(e -> {
            window.dispose();
            mainFrame.dispose();
            if(judgeCC == 1)
            cc.stop();
            else{
                bc.stop();
            }
            try {
                UserInterface.userInterface();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        window.add(exitBtn);

//        if(win)                //判断是否获胜
//        window.add(win);
//        else{
//        window.add(lose);

    }
}
