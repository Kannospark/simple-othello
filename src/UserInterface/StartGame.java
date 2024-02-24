package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static UserInterface.Set.isMaximum;

public class StartGame {
    public static int isRobot; //1:Robot, -1:not_Robot
    public static int easyMode;

    public static void startGame(){
        JFrame window = new JFrame("Choose a game mode");
        window.setResizable(false);
        window.setSize(500,500);
        int width = window.getWidth();
        int height = window.getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension Screensize = kit.getScreenSize();
        int screenWidth = Screensize.width;
        int screenHeight = Screensize.height;
        window.setLocation((screenWidth-width)/2,(screenHeight-height)/2); //居中


        JPanel jp = new JPanel(new FlowLayout(FlowLayout.CENTER,50,30));
        Font f = new Font("宋体",Font.BOLD,20);
        Dimension size = new Dimension(200,100);

        JButton jb1_s = new JButton("双人模式");
        jb1_s.setPreferredSize(size);
        jb1_s.setFont(f);
 //       jb1_s.setBackground(Color.green);
        jb1_s.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isRobot = -1;
                UserInterface.ac.stop();
                Main.main();
                window.dispose();
            }
        });
        jp.add(jb1_s);



        JButton jb2_s = new JButton("人机Easy");
        jb2_s.setPreferredSize(size);
        jb2_s.setFont(f);
  //      jb2_s.setBackground(Color.green);
        jb2_s.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isRobot = 1 ;
                easyMode = 1;
                UserInterface.ac.stop();
                Main.main();
                window.dispose();
            }
        });
        jp.add(jb2_s);



        JButton jb3_s = new JButton("人机Hard");
        jb3_s.setPreferredSize(size);
        jb3_s.setFont(f);
        //      jb2_s.setBackground(Color.green);
        jb3_s.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isRobot = 1 ;
                easyMode = 0;
                UserInterface.ac.stop();
                Main.main();
                window.dispose();
            }
        });
        jp.add(jb3_s);

//        JButton jb3_s = new JButton("PVP");
//        jb3_s.setPreferredSize(size);
//  //      jb3_s.setBackground(Color.green);
//        jb3_s.setFont(f);
//        jp.add(jb3_s);

//        JButton jb4_s = new JButton("Cheat Mode");
//        jb4_s.setPreferredSize(size);
//        jb4_s.setBackground(Color.green);
//        jb4_s.setFont(f);
//        jp.add(jb4_s);

        JButton c=new JButton("                                                     "
                +"                                   ");
        c.setContentAreaFilled(false);
        c.setBorderPainted(false);
        c.setEnabled(false);
        jp.add(c);

        JButton jb5_s = new JButton("Return");
        jb5_s.setPreferredSize(new Dimension(100,50));
        jb5_s.setBackground(Color.gray);
        jb5_s.setFont(f);
        jb5_s.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed (ActionEvent e){
                    window.dispose();
                try {
                    UserInterface.ac.stop();
                    UserInterface.userInterface();
                }
                catch(Exception PrintStrackTrace){
                    System.out.println("error StartGame");
                }
            }
            });
        jp.add(jb5_s);
        if(isMaximum){
            window.setExtendedState(Frame.MAXIMIZED_BOTH);
        }


        window.add(jp);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
