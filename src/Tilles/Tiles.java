package Tilles;

import java.awt.*;

public interface Tiles {
    public Block[] get();

    public void rotate();

    public void moveX();

    public void moveDown();

    public void set(Block[] i);
    public Color  getColor();
}
