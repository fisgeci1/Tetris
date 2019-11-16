package GamePlayControl;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controls implements KeyListener {


    JPanel panel;

    public Controls(JPanel panel) {
        this.panel = panel;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D) {
//            System.out.println("Pressed");
            GamePlayController.setxDir(+1);
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
//            System.out.println("Pressed");
            GamePlayController.setxDir(-1);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            GamePlayController.setRotate(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
