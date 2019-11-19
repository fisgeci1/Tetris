package Tilles;

import DrawingClasses.TestView;
import GamePlayControl.GamePlayController;

import java.awt.*;

public class ZL extends Block implements Tiles {


    private Block[] tile = new Block[4];
    private static final Color COLOR = Color.GREEN;

    public ZL(int positionX, int positionY) {

        for (int i = 0; i < 2; i++) {
            tile[i] = new Block(positionX + getSizes() * i, positionY, COLOR, getSizes());
        }
        tile[2] = new Block(positionX, positionY - getSizes(), COLOR, getSizes());
        tile[3] = new Block(positionX - getSizes(), positionY - getSizes(), COLOR, getSizes());

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
        boolean succesful = true;
        Block[] temp = new Block[4];
        if (isHorizontal()) {
            if ((tile[0].getPositionY() + getSizes()) / 10 + 1 >= 23 || (tile[3].getPositionY() - getSizes()) / 10 <= 0) {
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

            int y = tile[0].getPositionY();
            int x = tile[0].getPositionX();
            if (isHorizontal()) {
                for (Block b : tile) {
                    GamePlayController.setPlayingBoard(b.getPositionY() / 10, b.getPositionX() / 10, 0);
                }
                System.out.println(tile[0].getPositionX() + "/" + tile[1].getPositionX());
                temp[0] = new Block(x, y, COLOR, getSizes());
                temp[1] = new Block(x, y + getSizes(), COLOR, getSizes());
                temp[2] = new Block(x + getSizes(), y, COLOR, getSizes());
                temp[3] = new Block(x + getSizes(), y - getSizes() * (1), COLOR, getSizes());

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
                temp[0] = new Block(x, y, COLOR, getSizes());
                temp[1] = new Block(x + getSizes(), y, COLOR, getSizes());
                temp[2] = new Block(x, y - getSizes(), COLOR, getSizes());
                temp[3] = new Block(x - getSizes() * (1), y - getSizes(), COLOR, getSizes());
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

    @Override
    public void moveX() {
        boolean succesfu = true;
        int dir = GamePlayController.getxDir();

        Block[] temp = new Block[4];
        if (tile[0].getPositionY() != 230) {
            if (dir >= 0) {
                if (isHorizontal()) {
                    if (tile[1].getPositionX() / 10 + 1 == 10) {
                        succesfu = false;
                        temp = tile;
                    }
                } else {
                    if (tile[3].getPositionX() / 10 + 1 == 10) {
                        succesfu = false;
                        temp = tile;
                    }
                }
            } else {
                if (isHorizontal()) {
                    if (tile[3].getPositionX() / 10 - 1 == -1) {
                        temp = tile;
                        succesfu = false;
                    }
                } else {
                    if (tile[0].getPositionX() / 10 - 1 == -1) {
                        temp = tile;
                        succesfu = false;
                    }
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

                        temp[i] = new Block(tile[i].getPositionX() + getSizes() * dir, tile[i].getPositionY(), COLOR, getSizes());
                        if (!succesfu || !(checkIfCanMove(temp[i].getPositionY() / 10, (temp[i]).getPositionX() / 10))) {
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


                        temp[i] = new Block(tile[i].getPositionX() + getSizes() * dir, tile[i].getPositionY(), COLOR, getSizes());
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
                        GamePlayController.setPlayingBoard(b.getPositionY() / 10, b.getPositionX() / 10, 4);
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
        if (isHorizontal()) {
            int y = tile[0].getPositionY();
            for (int i = 0; i < 2; i++) {

//                System.out.println(I[i].getPositionY());
                if (tile[0].getPositionY() == 230 || (tile[i].getPositionY() / 10 != 0) && (checkIfCanMove(tile[i].getPositionY() / 10 - 1, tile[i].getPositionX() / 10))) {

                    temp[i] = new Block(tile[i].getPositionX(), tile[i].getPositionY() - getSizes(), COLOR, getSizes());
                    succesful = true;
                } else {
                    succesful = false;
                    break;
                }
            }
            for (int i = 2; i < 4; i++) {

//                System.out.println(I[i].getPositionY());
                if (tile[0].getPositionY() == 230 || (tile[i].getPositionY() / 10 != 0) && (checkIfCanMove(tile[i].getPositionY() / 10 - 1, tile[i].getPositionX() / 10))) {

                    temp[i] = new Block(tile[i].getPositionX(), tile[i].getPositionY() - getSizes(), COLOR, getSizes());
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
            temp[0] = new Block(tile[0].getPositionX(), tile[0].getPositionY() - getSizes(), COLOR, getSizes());
            temp[1] = new Block(tile[1].getPositionX(), tile[1].getPositionY() - getSizes(), COLOR, getSizes());
            temp[2] = new Block(tile[2].getPositionX(), tile[2].getPositionY() - getSizes(), COLOR, getSizes());
            temp[3] = new Block(tile[3].getPositionX(), tile[3].getPositionY() - getSizes(), COLOR, getSizes());
            if (temp[3].getPositionY() / 10 != -1 &&
                    checkIfCanMove(temp[0].getPositionY() / 10, temp[0].getPositionX() / 10) &&
                    checkIfCanMove(temp[1].getPositionY() / 10, temp[1].getPositionX() / 10) &&
                    checkIfCanMove(temp[2].getPositionY() / 10, temp[2].getPositionX() / 10) &&
                    checkIfCanMove(temp[3].getPositionY() / 10, temp[3].getPositionX() / 10)
            ) {
                succesful = true;
            } else {
                succesful = false;
            }

        }
        if (succesful == false) {
            TestView.setNext(true);
            for (Block b : tile) {
                GamePlayController.setPlayingBoard(b.getPositionY() / 10, b.getPositionX() / 10, 4);
            }
            set(null);

        } else {


            for (Block b : temp) {
//                System.out.println(I[0].getPositionY());
                GamePlayController.setPlayingBoard(b.getPositionY() / 10, b.getPositionX() / 10, 4);
            }


            set(temp);
        }


    }


    private boolean isHorizontal() {
        return tile[0].getPositionY() == tile[1].getPositionY();
    }

    @Override
    public void set(Block[] i) {
        tile = i;
    }
}
