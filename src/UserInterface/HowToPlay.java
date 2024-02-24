package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HowToPlay {
    public void howToPlay() throws IOException {
        JFrame window = new JFrame("Choose a game mode");
        window.setResizable(false);
        window.setSize(400,400);
        int width = window.getWidth();
        int height = window.getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension Screensize = kit.getScreenSize();
        int screenWidth = Screensize.width;
        int screenHeight = Screensize.height;
        window.setLocation((screenWidth-width)/2,(screenHeight-height)/2); //居中


        JPanel jp = new JPanel();
        Font f = new Font("宋体",Font.BOLD,20);
        Dimension size = new Dimension(600,500);

        Desktop desktop=Desktop.getDesktop();
        try {
            desktop.browse(new URI("https://jingyan.baidu.com/article/f71d60375866441ab741d15a.html"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }



        JButton c=new JButton("                                                     "
                +"                                   ");
        c.setContentAreaFilled(false);
        c.setBorderPainted(false);
        c.setEnabled(false);
        jp.add(c);

        JButton jb5_s = new JButton("Return");
        jb5_s.setPreferredSize(new Dimension(200,150));
        jb5_s.setBackground(Color.gray);
        jb5_s.setFont(f);
        jb5_s.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                try{
                    UserInterface.ac.stop();
                    UserInterface.userInterface();
                }
                catch(Exception wrong){
                    System.out.println("error HowToPlay!");
                }
            }
        });
        jp.add(jb5_s);

        window.add(jp);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
