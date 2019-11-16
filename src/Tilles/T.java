package Tilles;

import java.awt.*;

public class T extends Block {

    private Block[] T = new Block[4];
    private static final Color COLOR = Color.MAGENTA;

    public T(int positionX, int positionY) {

        for (int i = 0; i < 3; i++) {
            T[i] = new Block(positionX + getSizes() * i, positionY, COLOR, getSizes());
        }
        T[3] = new Block(T[1].getPositionX(), positionY + getSizes(), COLOR, getSizes());
    }

    public Block[] getT() {
        return T;
    }
}
