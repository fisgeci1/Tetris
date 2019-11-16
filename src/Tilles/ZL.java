package Tilles;

import java.awt.*;

public class ZL extends Block {

    private Block[] Z = new Block[4];
    private static final Color COLOR = Color.RED;

    public ZL(int positionX, int positionY) {

        for (int i = 0; i < 2; i++) {
            Z[i] = new Block(positionX + getSizes() * i, positionY, COLOR, getSizes());
        }
        Z[2] = new Block(positionX  , positionY + getSizes(), COLOR, getSizes());
        Z[3] = new Block(positionX - getSizes() , positionY + getSizes(), COLOR, getSizes());

    }

    public Block[] getZ() {
        return Z;
    }
}
