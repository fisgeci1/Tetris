package Tilles;

import DrawingClasses.TestView;
import GamePlayControl.GamePlayController;

import java.awt.*;

public class LR extends Block implements Tiles {
    private Block[] tile = new Block[4];
    private static final Color COLOR = Color.BLUE;

    public LR(int positionX, int positionY) {

        int temp = 0;
        for (int i = 0; i < 3; i++) {
            tile[i] = new Block(positionX + getSizes() * i, positionY, COLOR, getSizes());
//            i = temp;
        }

        tile[3] = new Block(tile[2].getPositionX(), tile[2].getPositionY() + getSizes(), COLOR, getSizes());

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
        if (isHorizontal() == 1) {
            if ((tile[0].getPositionY() + getSizes()) / 10 + 1 >= 23 || (tile[2].getPositionY() - getSizes() * 2) / 10 <= 0) {
                System.out.println("unsuccesful");
                succesful = false;
                temp = tile;
            }
        } else if (isHorizontal() == 2) {
            if ((tile[0].getPositionX() - 2 * getSizes()) / 10 <= -1 || (tile[2].getPositionX() + getSizes() * 2) / 10 >= 10) {
                System.out.println("unsuccesful");
                succesful = false;
                temp = tile;
            }
        } else {
            if ((tile[0].getPositionX() - 2 * getSizes()) / 10 <= -1 || (tile[2].getPositionX() + getSizes() * 2) / 10 >= 10) {
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
            if (isHorizontal() == 1) {
                for (Block b : tile) {
                    GamePlayController.setPlayingBoard(b.getPositionY() / 10, b.getPositionX() / 10, 0);
                }
                temp[0] = new Block(x, y - getSizes() * 2, COLOR, getSizes());
                temp[1] = new Block(x, y - getSizes(), COLOR, getSizes());
                temp[2] = new Block(x, y, COLOR, getSizes());
                temp[3] = new Block(x - getSizes(), y, COLOR, getSizes());

                for (int i = 0; i < 4; i++) {
                    if (!checkIfCanMove(temp[i].getPositionY() / 10, temp[i].getPositionX() / 10)) {
                        succesful = false;
                        temp = tile;
                        break;
                    }
                }
            }
            //Moving  vertically
            else if (isHorizontal() == 2) {
                for (Block b : tile) {
                    GamePlayController.setPlayingBoard(b.getPositionY() / 10, b.getPositionX() / 10, 0);
                }
                temp[0] = new Block(x + getSizes() * 2, y, COLOR, getSizes());
                temp[1] = new Block(x + getSizes(), y, COLOR, getSizes());
                temp[2] = new Block(x, y, COLOR, getSizes());
                temp[3] = new Block(x, y - getSizes(), COLOR, getSizes());
                for (int i = 0; i < 4; i++) {
                    if (!checkIfCanMove(temp[i].getPositionY() / 10, temp[i].getPositionX() / 10)) {
                        succesful = false;
                        temp = tile;
                        break;
                    }
                }
            } else if (isHorizontal() == 3) {
                temp[0] = new Block(x, y + getSizes() * 2, COLOR, getSizes());
                temp[1] = new Block(x, y + getSizes(), COLOR, getSizes());
                temp[2] = new Block(x, y, COLOR, getSizes());
                temp[3] = new Block(x + getSizes(), y, COLOR, getSizes());
                for (int i = 0; i < 4; i++) {
                    if (!checkIfCanMove(temp[i].getPositionY() / 10, temp[i].getPositionX() / 10)) {
                        succesful = false;
                        temp = tile;
                        break;
                    }
                }
            } else {
                temp[0] = new Block(x - getSizes() * 2, y, COLOR, getSizes());
                temp[1] = new Block(x - getSizes(), y, COLOR, getSizes());
                temp[2] = new Block(x, y, COLOR, getSizes());
                temp[3] = new Block(x, y + getSizes(), COLOR, getSizes());
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
        if (tile[3].getPositionY() != 230) {
            if (dir >= 0) {
                if (isHorizontal() == 1) {
                    if (tile[3].getPositionX() / 10 + 1 == 10) {
                        succesfu = false;
                        temp = tile;
                    }
                } else {
                    if (tile[0].getPositionX() / 10 + 1 == 10) {
                        succesfu = false;
                        temp = tile;
                    }
                }
            } else {
                if (isHorizontal() == 1) {
                    if (tile[0].getPositionX() / 10 - 1 == -1) {
                        temp = tile;
                        succesfu = false;
                    }
                } else if (isHorizontal() == 2) {
                    if (tile[3].getPositionX() / 10 - 1 == -1) {
                        temp = tile;
                        succesfu = false;
                    }
                } else if (isHorizontal() == 3) {
                    if (tile[2].getPositionX() / 10 - 1 == -1) {
                        temp = tile;
                        succesfu = false;
                    }
                } else {
                    if (tile[2].getPositionX() / 10 - 1 == -1) {
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
                if (isHorizontal() == 1) {


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
                        GamePlayController.setPlayingBoard(b.getPositionY() / 10, b.getPositionX() / 10, 5);
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
        if (tile[3].getPositionY() != 230) {
            for (Block b : tile) {
                GamePlayController.setPlayingBoard(b.getPositionY() / 10, b.getPositionX() / 10, 0);
            }
        }


        int y = tile[2].getPositionY() - getSizes();
        int x = tile[2].getPositionX();
        temp[0] = new Block(tile[0].getPositionX(), tile[0].getPositionY() - getSizes(), COLOR, getSizes());
        temp[1] = new Block(tile[1].getPositionX(), tile[1].getPositionY() - getSizes(), COLOR, getSizes());
        temp[2] = new Block(tile[2].getPositionX(), tile[2].getPositionY() - getSizes(), COLOR, getSizes());
        temp[3] = new Block(tile[3].getPositionX(), tile[3].getPositionY() - getSizes(), COLOR, getSizes());
        if ((temp[2].getPositionY() / 10 != -1 && (temp[3].getPositionY() / 10 != -1)) &&
                checkIfCanMove(temp[0].getPositionY() / 10, temp[0].getPositionX() / 10) &&
                checkIfCanMove(temp[1].getPositionY() / 10, temp[1].getPositionX() / 10) &&
                checkIfCanMove(temp[2].getPositionY() / 10, temp[2].getPositionX() / 10) &&
                checkIfCanMove(temp[3].getPositionY() / 10, temp[3].getPositionX() / 10)
        ) {
            succesful = true;
        } else {
            succesful = false;
        }

        if (succesful == false) {
            TestView.setNext(true);
            for (Block b : tile) {
                GamePlayController.setPlayingBoard(b.getPositionY() / 10, b.getPositionX() / 10, 5);
            }
            set(null);

        } else {


            for (Block b : temp) {
//                System.out.println(I[0].getPositionY());
                GamePlayController.setPlayingBoard(b.getPositionY() / 10, b.getPositionX() / 10, 5);
            }


            set(temp);
        }


    }


    private int isHorizontal() {
        if (tile[2].getPositionY() - tile[3].getPositionY() < 0) {
            return 1;
        } else if (tile[2].getPositionY() - tile[3].getPositionY() > 0) {
            return 3;
        } else if (tile[2].getPositionY() - tile[3].getPositionY() == 0 && tile[2].getPositionY() > tile[1].getPositionY()) {
            return 2;
        } else {
            return 4;
        }
    }

    @Override
    public void set(Block[] i) {
        tile = i;
    }

}
