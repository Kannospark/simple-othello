package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class ChangeUser {
    public static String userName;

    public ChangeUser() throws FileNotFoundException {
    }

    public void changeUser() {
        JFrame window = new JFrame("Choose a game mode");
        window.setResizable(false);
        window.setSize(300, 200);
        int width = window.getWidth();
        int height = window.getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension Screensize = kit.getScreenSize();
        int screenWidth = Screensize.width;
        int screenHeight = Screensize.height;
        window.setLocation((screenWidth - width) / 2, (screenHeight - height) / 2); //居中

        ReadFile.main();//检索文件夹并赋值hm
        System.out.println(Player.hm);

        JTextField text = new JTextField(10);
        text.setText("2-10 words");


        JLabel jb = new JLabel("set your name:");
        JPanel jp1 = new JPanel(new FlowLayout());
        jp1.add(jb);
        jp1.add(text);

        JButton jb1 = new JButton("confirm");
        jb1.addActionListener(e -> {
            userName =  text.getText();
            if (userName.length() > 2 && userName.length() <= 10) {
                                                                         //System.out.println(Player.hm + "hashmap");//检测
                    if (Player.hm.containsKey(userName+".zzx")) {
                        System.out.println("到了登录！");
                        //登录之前的用户
                        JOptionPane.showMessageDialog(null, "you have logged in!");
                    }

                        //注册并创建用户
                    if (!Player.hm.containsKey(userName+".zzx")) {
                        System.out.println(" 到了注册！");
                        try {
                            Player player = new Player(userName);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        JOptionPane.showMessageDialog(null, "you have enrolled a new Player!");
                    }
                    //切换回之前的窗口并加名字
                window.dispose();
                try {
                    UserInterface.ac.stop();
                    UserInterface.userInterface();
                    UserInterface.window.setTitle(userName);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "please input in 2-10 words!");//提示
            }
        });

        jp1.add(jb1);

        window.add(jp1);
        window.setVisible(true);


        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}




