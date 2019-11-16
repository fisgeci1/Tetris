package Tilles;

import java.awt.*;

public class LL extends Block {
    private Block[] L = new Block[4];
    private static final Color COLOR = Color.ORANGE;

    public LL(int positionX, int positionY) {

        for (int i = 0; i < 3; i++) {
            L[i] = new Block(positionX, positionY + getSizes() * i, COLOR, getSizes());
        }

        L[3] = new Block(L[2].getPositionX() - getSizes(), L[2].getPositionY(), COLOR, getSizes());

    }

    public Block[] getL() {
        return L;
    }

}
