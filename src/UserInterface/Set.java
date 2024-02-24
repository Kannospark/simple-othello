package UserInterface;

import UserInterface.UserInterface;
import view.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.yellow;

public class Set {
    public static Color color ;
    public static boolean isMaximum = false;
    public static Color negaColor = Color.BLACK;
    public static Color posiColor = Color.WHITE;

    public static void set() {
        JFrame window = new JFrame("Choose a game mode");
        window.setResizable(false);
        window.setSize(600, 500);
        int width = window.getWidth();
        int height = window.getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension Screensize = kit.getScreenSize();
        int screenWidth = Screensize.width;
        int screenHeight = Screensize.height;
        window.setLocation((screenWidth - width) / 2, (screenHeight - height) / 2); //居中


        JPanel jp = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 30));
        Font f = new Font("宋体", Font.BOLD, 18);
        Dimension size = new Dimension(140, 90);



//************************************************************************************************

        JPanel panel = new JPanel();
        panel.setLocation(30,30);
        // 创建一个标签, 用于显示选择的原色
        final JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(150, 150));
        label.setOpaque(true);
        panel.add(label);

        JButton btn = new JButton("选择颜色");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 显示颜色选取器对话框, 返回选取的颜色（线程将被阻塞, 直到对话框被关闭）
                color = JColorChooser.showDialog(window, "选取颜色", null);

                // 如果用户取消或关闭窗口, 则返回的 color 为 null
                if (color == null) {
                    return;
                }

                // 把选取的颜色设置为标签的背景
                label.setBackground(color);

                // 获取颜色的 ARGB 各个分量值
                int alpha = color.getAlpha();
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                label.setText("A=" + String.format("%02x", alpha) + ", " +
                        String.format("#%02x%02x%02x", red, green, blue));
            }
        });
        panel.add(btn);

        window.setContentPane(panel);
        window.setVisible(true);
        //******************************************************************


        JButton jb1 = new JButton("Positive color");
        jb1.setFont(f);
        jb1.setPreferredSize(size);
        jb1.setBackground(Color.WHITE);
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            jb1.setBackground(color);
                posiColor = color;
               // System.out.println("positive color :"+color);
            }
        });
        jp.add(jb1);

        JButton jb2 = new JButton("negative color");
        jb2.setFont(f);
        jb2.setPreferredSize(size);
        jb2.setBackground(Color.black);
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            jb2.setBackground(color);
                negaColor = color;
               // System.out.println("negative color : "+negaColor);
            }
        });
        jp.add(jb2);


        JButton jb5_s = new JButton("Return");
        jb5_s.setPreferredSize(new Dimension(100, 50));
        jb5_s.setBackground(Color.gray);
        jb5_s.setFont(f);
        jb5_s.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (negaColor == posiColor) {
                    JOptionPane.showMessageDialog(null, "You can't use the same colors!");//提示
                } else {
                    window.dispose();
                    try {
                        UserInterface.ac.stop();
                        UserInterface.userInterface();
                    } catch (Exception PrintStrackTrace) {
                        System.out.println("error StartGame");
                    }
                }
            }
        });
        jp.add(jb5_s);


        window.add(jp);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
