package Tilles;

import GamePlayControl.GamePlayController;

import java.awt.*;

public class Block extends Rectangle {

    private Color color;
    public static int size = 10;
    private int positionX, positionY;

    public Block() {
    }


    public Block(int positionX, int positionY, Color color, int size) {
        super(positionX, positionY, size, size);
        this.color = color;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getSizes() {
        return size;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public Color getColor() {
        return color;
    }

    public boolean checkIfCanMove(int i, int j) {
//        System.out.println(i + " - " + j);
        return GamePlayController.getPlayingBoard()[i][j] == 0;
    }
}
