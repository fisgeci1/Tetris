package GamePlayControl;

import Tilles.*;

import java.util.ArrayList;

public class GamePlayController {


    private I i = null;
    private static boolean rotate = false;
    private static int xDir;


    private static int[][] playingBoard = new int[23][10];

    public static void move(Tiles tile) {

        try {
            if (rotate) {
                tile.rotate();
            } else if (xDir == 0) {
                // Call method from obj
                tile.moveDown();
            } else if (xDir == 1 || xDir == -1) {
                tile.moveX();
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    public static void setxDir(int xDir) {
        GamePlayController.xDir = xDir;
    }

    public static int getxDir() {
        return xDir;
    }

    public static void setRotate(boolean rotate) {
        GamePlayController.rotate = rotate;
    }

    public static void setPlayingBoard(int i, int j, int val) {
        GamePlayController.playingBoard[i][j] = val;
    }

    public static int[][] getPlayingBoard() {
//        System.out.println("in here");

        return playingBoard;
    }

    public static void shiftBoard() {

        int[] affectedRows = new int[23];
        int[][] tempBoard = new int[23][10];
        for (int i = 0; i < 23; i++) {
            boolean noZero = true;
            for (int j = 0; j < 10; j++) {
                if (playingBoard[i][j] == 0) {
                    noZero = false;
                    break;
                }
            }
            if (noZero) {
                affectedRows[i] = 1;
                tempBoard[i] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            } else {
                affectedRows[i] = 0;
                tempBoard[i] = playingBoard[i];
            }
        }
        int[][] newBoard = new int[23][10];

        ArrayList<int[]> tempArray = new ArrayList<>();

        for (int i = 0; i < 23; i++) {
            if (affectedRows[i] != 1) {
                tempArray.add(tempBoard[i]);
            }
        }
        for (int i = 0; i < tempArray.size(); i++) {
            newBoard[i] = tempArray.get(i);
        }
        
        if (tempArray.size() - 1 < 23) {
            int elemntsToAdd = 23 - tempArray.size();
            for (int i = 23 - elemntsToAdd; i < 23; i++) {
                newBoard[i] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            }
        }
        setPlayingBoard(newBoard);
    }


    public static void setPlayingBoard(int[][] playingBoard) {
        GamePlayController.playingBoard = playingBoard;
    }

    public static void printBoard() {
        for (int i = 0; i < playingBoard.length; i++) {
            System.out.print(i + ": " + "[");
            for (int j = 0; j < playingBoard[i].length; j++) {

                System.out.printf("%d,", playingBoard[i][j]);
            }
            System.out.println("]");
        }
    }

    public static Tiles generateRandomTille(int num) {

        Tiles tile = null;

        switch (num) {
            case 1:
                tile = new I(50, Block.size * 23);
                break;
            case 2:
                tile = new O(50, Block.size * 23);
                break;
            case 3:
                tile = new ZR(50, Block.size * 23);
                break;
            default:
                tile = new I(50, Block.size * 23);
                break;
        }

        return tile;
    }
}
