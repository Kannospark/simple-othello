//设置有且仅有的两种棋子颜色
package model;

import java.awt.*;

import static UserInterface.Set.negaColor;
import static UserInterface.Set.posiColor;

public enum ChessPiece { //此为枚举类，只产生有限个数的对象
    BLACK(negaColor), WHITE(posiColor),GRAY(Color.GRAY);

    private final Color color;

    ChessPiece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
