package UserInterface;

import view.GameFrame;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;



public class Main {
    public static AudioClip bc;
    public static GameFrame mainFrame;
    public static void main() {

        URL url = null; //开启音乐
        File f1 = new File("./BGM/2.wav");
        try {
            url = f1.toURL();
        } catch (MalformedURLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        bc = Applet.newAudioClip(url);
        bc.loop();


        SwingUtilities.invokeLater(() -> {
            mainFrame = new GameFrame(800);
            mainFrame.setVisible(true);
        });
    }
}
