package DrawingClasses;

import GamePlayControl.Controls;
import GamePlayControl.GamePlayController;
import Tilles.Block;
import Tilles.I;
import Tilles.Tiles;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class TestView extends JPanel {


    public static boolean next = false;
    private int xOffset = 200;
    private int yOffset = Block.size * 20;
    private int scale = 3;
    private int speed = 150;
    private final Color[] colors = {Color.CYAN, Color.CYAN, Color.YELLOW, Color.RED,
            Color.GREEN, Color.BLUE, Color.ORANGE, Color.MAGENTA};

    public static void main(String[] args) {
        TestView t = new TestView();
    }

    public TestView() {
        JFrame frame = new JFrame("Test window");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 720);
        frame.getContentPane().add(this);
        frame.addKeyListener(new Controls(this));
        frame.setVisible(true);
    }

    static Tiles tile = null;
    int count = 0;


    public static void setTile(I si) {
        tile = si;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.translate(0, 0);
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, 1000, 1000);

        Graphics2D g2d = (Graphics2D) g;
        g.translate(xOffset, Block.size * 22 * scale);
        g2d.scale(1.0, -1.0);


        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 10 * Block.size * scale, 22 * Block.size * scale);


        if (tile == null) {
            Random rand = new Random();
            int random = rand.nextInt(8);
            tile = GamePlayController.generateRandomTille(random);
        }

        try {
            Thread.sleep(speed);
        } catch (Exception e) {
        }

        if (tile.get() == null || tile.get()[0].getPositionY() <= 0) {
            GamePlayController.printBoard();
            Random rand = new Random();
            int random = rand.nextInt(8);
            tile = GamePlayController.generateRandomTille(random);
            GamePlayController.shiftBoard();
            repaint();
        } else {
            //Moves the given tile either down or sideways
            GamePlayController.move(tile);
            paintGame(g);
        }
    }


    //scales each element by scale(instance variable)
    private Block[] scaleTiles(Block[] unScaledTilles) {

        Block[] scaledTiles = new Block[4];

        for (int i = 0; i < 4; i++) {
            scaledTiles[i] = new Block(unScaledTilles[i].getPositionX() * 3, unScaledTilles[i].getPositionY() * 3, Color.CYAN, Block.size * 3);

        }
        return scaledTiles;

    }

    private void paintTile(Block[] tiles, Graphics2D graphics2D) {
        for (Block b : tiles) {
            graphics2D.draw(b);
        }
    }

    public static void setNext(boolean next) {
        TestView.next = next;
    }


    private void paintGame(Graphics g) {
        Block[] list = new Block[230];

        for (int i = 0; i < 23; i++) {
            for (int j = 0; j < 10; j++) {
                if (GamePlayController.getPlayingBoard()[i][j] != 0) {
                    g.setColor(colors[GamePlayController.getPlayingBoard()[i][j]]);
                    g.fillRect(scale * j * 10, i * 10 * scale, Block.size * scale, Block.size * scale);
                    g.setColor(Color.GRAY);
                    g.drawRect(scale * j * 10, i * 10 * scale, Block.size * scale, Block.size * scale);
                }
            }
        }
        repaint();
    }
}
