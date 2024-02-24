package UserInterface;

import view.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;
import java.applet.Applet;
import java.applet.*;

import static UserInterface.Set.*;

public class UserInterface {
    public static int hasUser = 0;
    private String userName;
    public static AudioClip ac;
    static JFrame window = new JFrame("Welcome to the Othello");

    public static void userInterface() throws Exception {
//        if(negaColor==null) {
//            negaColor = Color.BLACK;
//        }
//        if(posiColor==null) {
//            posiColor = Color.WHITE;
//        }

        window.setSize(800, 600);
        window.setResizable(false);
        int width = window.getWidth();
        int height = window.getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();//设置居中
        Dimension Screensize = kit.getScreenSize();
        int screenWidth = Screensize.width;
        int screenHeight = Screensize.height;
        window.setLocation((screenWidth - width) / 2, (screenHeight - height) / 2); //居中




                System.out.println("begin!");
                URL url = null;
                File f1 = new File("./BGM/1.wav");
                try {
                    url = f1.toURL();
                } catch (MalformedURLException e) {
// TODO Auto-generated catch block
                    e.printStackTrace();
                }
                ac = Applet.newAudioClip(url);
                ac.loop();


        JPanel jp = new JPanel(new FlowLayout());
        Font f = new Font("宋体", Font.BOLD, 20);
        Dimension size = new Dimension(200, 80);

        JPanel jpall = new JPanel(new FlowLayout(FlowLayout.CENTER,0,30));
        JPanel jp2 = new JPanel();
        jp2.setVisible(true);
        jp2.setSize(800,400);
        Icon upbg = new ImageIcon("photo/1.jpg");
        JLabel jb = new JLabel(upbg);
        jp2.add(jb);
        jpall.add(jp2);



        JButton jb1 = new JButton("Start Game");
        jb1.setPreferredSize(size);
        jb1.setFont(f);
        jb1.setLocation(280,200);
        jp.add(jb1);
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                new StartGame().startGame();
            }
        });

        JButton jb2 = new JButton("Change USER");
        jb2.setLocation(380, 200);
        jb2.setPreferredSize(size);
        jb2.setFont(f);

        jp.add(jb2);
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                try {
                    new ChangeUser().changeUser();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton jb3 = new JButton("Set");
        jb3.setPreferredSize(size);
        jb3.setFont(f);
        jp.add(jb3);
        jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                //new Set().set();
                Set.set();
            }
        });

        JButton jb4 = new JButton("How to play");
        jb4.setSize(280, 300);
        jb4.setPreferredSize(size);
        jb4.setFont(f);
        jp.add(jb4);
        jb4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                try {
                    new HowToPlay().howToPlay();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        if(isMaximum){
            window.setExtendedState(Frame.MAXIMIZED_BOTH);
        }

        jpall.add(jp);
        window.add(jpall);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}

