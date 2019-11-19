package Tilles;

import DrawingClasses.TestView;
import GamePlayControl.GamePlayController;

import java.awt.*;

public class O extends Block implements Tiles {

    private Block[] tile = new Block[4];
    private final Color COLOR = Color.YELLOW;

    public O(int positionX, int positionY) {

        for (int i = 0; i < 2; i++) {
            tile[i] = new Block(positionX + getSizes() * i, positionY, COLOR, getSizes());
        }
        tile[2] = new Block(positionX, positionY - getSizes(), COLOR, getSizes());
        tile[3] = new Block(positionX + getSizes(), positionY - getSizes(), COLOR, getSizes());

    }

    public Block[] getTile() {
        return tile;
    }

    @Override
    public Block[] get() {
        return tile;
    }

    @Override
    public void rotate() {
        GamePlayController.setRotate(false);
    }

    @Override
    public void moveX() {
        boolean succesfu = true;
        int dir = GamePlayController.getxDir();

        Block[] temp = new Block[4];
        if (tile[1].getPositionY() != 230) {
            if (dir >= 0) {
                if (tile[1].getPositionX() / 10 + 1 >= 10) {
                    succesfu = false;
                    temp = tile;
                }
            } else {
                if (tile[0].getPositionX() / 10 - 1 <= -1) {
                    succesfu = false;
                    temp = tile;
                }
            }
            if (tile[0].getPositionY() != 230 && succesfu) {
                for (Block b : tile) {
                    GamePlayController.setPlayingBoard(b.getPositionY() / 10, b.getPositionX() / 10, 0);
                }
            }
            if (succesfu) {


                int x = tile[0].getPositionX() + getSizes() * dir;
                int y = tile[0].getPositionY();

                System.out.println("/////////////");
                System.out.println(tile[0].getPositionX());
                System.out.println(x);
                System.out.println("/////////////");


                temp[0] = new Block(x, y, COLOR, getSizes());
                temp[1] = new Block(tile[0].getPositionX(), y, COLOR, getSizes());
                temp[2] = new Block(x, y - getSizes(), COLOR, getSizes());
                temp[3] = new Block(tile[0].getPositionX(), y - getSizes(), COLOR, getSizes());

                if (!succesfu || (!(checkIfCanMove(temp[0].getPositionY() / 10, tile[0].getPositionX() / 10 + GamePlayController.getxDir())) ||
                        !(checkIfCanMove(temp[1].getPositionY() / 10, tile[1].getPositionX() / 10 + GamePlayController.getxDir()))
                        || !(checkIfCanMove((temp[2].getPositionY()) / 10, tile[2].getPositionX() / 10 + GamePlayController.getxDir()) ||
                        !(checkIfCanMove((temp[2].getPositionY()) / 10, tile[3].getPositionX() / 10 + GamePlayController.getxDir()))))) {
                    succesfu = false;
                    temp = tile;

                }

                if (!succesfu) {
                    for (Block b : tile) {
                        GamePlayController.setPlayingBoard(b.getPositionY() / 10, b.getPositionX() / 10, 0);
                    }
                } else {
                    for (Block b : temp) {
                        GamePlayController.setPlayingBoard(b.getPositionY() / 10, b.getPositionX() / 10, 2);
                    }
                }
                set(temp);
                GamePlayController.setxDir(0);

            } else {
                GamePlayController.setxDir(0);
                set(tile);
            }
        } else {
            GamePlayController.setxDir(0);
            set(tile);
        }
    }

    @Override
    public void moveDown() {
        boolean succesful = false;
        Block[] temp = new Block[4];
        if (tile[0].getPositionY() != 230) {
            for (Block b : tile) {
                GamePlayController.setPlayingBoard(b.getPositionY() / 10, b.getPositionX() / 10, 0);
            }
        }
        int y = tile[0].getPositionY();

//                System.out.println(I[i].getPositionY());
        if (tile[0].getPositionY() == 230 || (tile[2].getPositionY() / 10 != 0) && (
                (checkIfCanMove(tile[2].getPositionY() / 10 - 1, tile[2].getPositionX() / 10)) &&
                        (checkIfCanMove(tile[3].getPositionY() / 10 - 1, tile[3].getPositionX() / 10)))) {

            temp[0] = new Block(tile[0].getPositionX(), y - getSizes(), COLOR, getSizes());
            temp[1] = new Block(tile[0].getPositionX() + getSizes(), y - getSizes(), COLOR, getSizes());
            temp[2] = new Block(tile[0].getPositionX(), y - getSizes() * 2, COLOR, getSizes());
            temp[3] = new Block(tile[0].getPositionX() + getSizes(), y - getSizes() * 2, COLOR, getSizes());
            succesful = true;
        } else {
            succesful = false;
        }

        if (succesful == false) {
            TestView.setNext(true);
            for (Block b : tile) {
                GamePlayController.setPlayingBoard(b.getPositionY() / 10, b.getPositionX() / 10, 2);
            }
            set(null);

        } else {

            for (Block b : temp) {
                GamePlayController.setPlayingBoard(b.getPositionY() / 10, b.getPositionX() / 10, 2);
            }


            set(temp);
        }
    }

    @Override
    public void set(Block[] i) {
        tile = i;
    }

    @Override
    public Color getColor() {
        return this.COLOR;
    }
}
