//Abstract class 只是一个抽象的Listener

package components;

import controller.GameController;
import model.ChessPiece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static UserInterface.Set.negaColor;
import static UserInterface.Set.posiColor;

public abstract class BasicComponent extends JComponent {
    public BasicComponent() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                onMouseClicked(); //对应的event在哪里？--这是一个抽象类
            }
        });
    }

    public abstract void onMouseClicked();

    public static Color flip(int i, int j, ChessPiece chessPiece){
        if(chessPiece.getColor() == negaColor){ return posiColor; }
        else{ return negaColor; }
    }
}




