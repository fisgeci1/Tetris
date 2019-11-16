package Tilles;

import DrawingClasses.TestView;
import GamePlayControl.GamePlayController;

import java.awt.*;

public class I extends Block implements Tiles {

    private Block[] tile = new Block[4];


    int xDirection = 1;
    int yDirection = -1;
    private final Color COLOR = Color.CYAN;


    public I(int positionX, int positionY) {

        for (int i = 0; i < 4; i++) {
            tile[i] = new Block(positionX + getSizes() * i, positionY, COLOR, getSizes());
        }
    }

    public void set(Block[] i) {
        tile = i;
    }

    @Override
    public Block[] get() {
        return tile;
    }

    @Override
    public void moveX() {
        boolean succesfu = true;
        int dir = GamePlayController.getxDir();

        Block[] temp = new Block[4];
        if (dir >= 0) {
            if (tile[3].getPositionX() / 10 + 1 == 10) {
                succesfu = false;
                temp = tile;
            }
        } else {
            if (tile[0].getPositionX() / 10 - 1 == -1) {
                temp = tile;
                succesfu = false;
            }
        }
        if (tile[0].getPositionY() != 230 && succesfu) {
            for (Block b : tile) {
                GamePlayController.setPlayingBoard(b.getPositionY() / 10, b.getPositionX() / 10, 0);
            }
        }
        if (succesfu) {
            if (isHorizontal()) {


                int x = tile[0].getPositionX() + getSizes() * dir;
                int y = tile[0].getPositionY();

                for (int i = 0; i < 4; i++) {

                    temp[i] = new Block(x + getSizes() * i, y, COLOR, getSizes());
                    if (!succesfu || !(checkIfCanMove(y / 10, (x + getSizes() * i) / 10))) {
                        succesfu = false;
                        temp = tile;
                        break;
                    }

                }

            }
            //When vertical
            else {
                int x = tile[0].getPositionX() + getSizes() * dir;
                for (int i = 0; i < 4; i++) {


                    temp[i] = new Block(x, tile[i].getPositionY(), COLOR, getSizes());
                    if (!succesfu || !checkIfCanMove(temp[i].getPositionY() / 10, x / 10)) {
                        temp = tile;
                        succesfu = false;
                        break;
                    }
                }

            }

            if (!succesfu) {
                for (Block b : temp) {
                    GamePlayController.setPlayingBoard(b.getPositionY() / 10, b.getPositionX() / 10, 0);
                }
            } else {
                for (Block b : temp) {
                    GamePlayController.setPlayingBoard(b.getPositionY() / 10, b.getPositionX() / 10, 1);
                }
            }
            set(temp);
            GamePlayController.setxDir(0);

        } else {
            GamePlayController.setxDir(0);
            set(tile);
        }


    }

    @Override
    public void rotate() {
        boolean succesful = true;
        Block[] temp = new Block[4];
        if (isHorizontal()) {
            if ((tile[2].getPositionY() + getSizes()) / 10 + 1 >= 23 || (tile[0].getPositionY() - 2 * getSizes()) / 10 <= 0) {
                System.out.println("unsuccesful");
                succesful = false;
                temp = tile;
            }
        } else {
            if ((tile[0].getPositionX() - 2 * getSizes()) / 10 <= -1 || (tile[3].getPositionX() + getSizes()) / 10 >= 10) {
                System.out.println("unsuccesful");
                succesful = false;
                temp = tile;
            }
        }
        if (tile[0].getPositionY() != 230 && succesful) {
            for (Block b : tile) {
                GamePlayController.setPlayingBoard(b.getPositionY() / 10, b.getPositionX() / 10, 0);
            }
        }
        if (succesful) {

            int y = tile[2].getPositionY();
            int x = tile[2].getPositionX();
            if (isHorizontal()) {
                for (Block b : tile) {
                    GamePlayController.setPlayingBoard(b.getPositionY() / 10, b.getPositionX() / 10, 0);
                }
                temp[0] = new Block(x, y + getSizes() * 2, COLOR, getSizes());
                temp[1] = new Block(x, y + getSizes() * 1, COLOR, getSizes());
                temp[2] = new Block(x, y + getSizes() * 0, COLOR, getSizes());
                temp[3] = new Block(x, y + getSizes() * (-1), COLOR, getSizes());

                for (int i = 0; i < 4; i++) {
                    if (!checkIfCanMove(temp[i].getPositionY() / 10, temp[i].getPositionX() / 10)) {
                        succesful = false;
                        temp = tile;
                        break;
                    }
                }
            }
            //Moving  vertically
            else {
                for (Block b : tile) {
                    GamePlayController.setPlayingBoard(b.getPositionY() / 10, b.getPositionX() / 10, 0);
                }
                temp[0] = new Block(x - getSizes() * 2, y, COLOR, getSizes());
                temp[1] = new Block(x - getSizes() * 1, y, COLOR, getSizes());
                temp[2] = new Block(x, y, COLOR, getSizes());
                temp[3] = new Block(x + getSizes() * (1), y, COLOR, getSizes());
                for (int i = 0; i < 4; i++) {
                    if (!checkIfCanMove(temp[i].getPositionY() / 10, temp[i].getPositionX() / 10)) {
                        succesful = false;
                        temp = tile;
                        break;
                    }
                }
            }
        }
        if (temp[0].getPositionY() / 10 == 0) {
            set(null);
        } else {
            set(temp);
        }
        GamePlayController.setRotate(false);
    }


    private boolean isHorizontal() {
        return tile[0].getPositionY() == tile[1].getPositionY();
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
        if (isHorizontal()) {
            int y = tile[0].getPositionY();
            for (int i = 0; i < 4; i++) {

//                System.out.println(I[i].getPositionY());
                if (tile[i].getPositionY() == 230 || (tile[i].getPositionY() / 10 != 0) && (checkIfCanMove(tile[i].getPositionY() / 10 - 1, tile[i].getPositionX() / 10))) {

                    temp[i] = new Block(tile[0].getPositionX() + getSizes() * i, y - getSizes(), COLOR, getSizes());
                    succesful = true;
                } else {
                    succesful = false;
                    break;
                }
            }
        }
        ///Moving verticaly
        else {


            int y = tile[2].getPositionY() - getSizes();
            int x = tile[2].getPositionX();
            temp[0] = new Block(x, y - getSizes() * 2, COLOR, getSizes());
            temp[1] = new Block(x, y - getSizes() * 1, COLOR, getSizes());
            temp[2] = new Block(x, y -  getSizes() * 0, COLOR, getSizes());
            temp[3] = new Block(x, y - getSizes() * (-1), COLOR, getSizes());
            if (temp[0].getPositionY() / 10 == -1 || checkIfCanMove(temp[0].getPositionY() / 10, temp[2].getPositionX() / 10)) {
                succesful = true;
            } else {
                succesful = false;
            }
        }
        if (succesful == false) {
            TestView.setNext(true);
            for (Block b : tile) {
                GamePlayController.setPlayingBoard(b.getPositionY() / 10, b.getPositionX() / 10, 1);
            }
            set(null);

        } else {

//                                    if (I[0].getPositionY() != 230) {
//                                        for (Block b : I) {
//                                            GamePlayController.setPlayingBoard(b.getPositionY() / 10, b.getPositionX() / 10, 0);
//                                        }

            for (Block b : temp) {
//                System.out.println(I[0].getPositionY());
                GamePlayController.setPlayingBoard(b.getPositionY() / 10, b.getPositionX() / 10, 1);
            }


            set(temp);
        }
//        }
    }

    @Override
    public Color getColor() {
        return this.COLOR;
    }
}
