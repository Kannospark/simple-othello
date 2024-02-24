package view;

import model.ChessPiece;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel {
    private static JLabel playerLabel;
    private static JLabel scoreLabel;

    public StatusPanel(int width, int height) {
        this.setSize(width, height);
        this.setLayout(null);
        this.setVisible(true);

        playerLabel = new JLabel();
        playerLabel.setLocation(0, 10);
        playerLabel.setSize((int) (width * 0.4), height);
        playerLabel.setFont(new Font("Calibri", Font.BOLD, 30));
        setPlayerText(ChessPiece.BLACK.name());
        add(playerLabel);

        scoreLabel = new JLabel();
        scoreLabel.setLocation((int) (width * 0.4), 10);
        scoreLabel.setSize((int) (width * 0.5), height);
        scoreLabel.setFont(new Font("Calibri", Font.ITALIC, 25));
        setScoreText(2,2);
        add(scoreLabel);

    }
 //*****************************************************
    //需要改Score的计算方法
    public static void setScoreText(int black, int white) {
        scoreLabel.setText(String.format("BLACK: %d\tWHITE: %d", black, white));
    }

    public static void setPlayerText(String playerText) {
        playerLabel.setText(playerText + "'s turn");
    }
    
}
