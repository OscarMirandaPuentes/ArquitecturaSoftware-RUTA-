package Interfaz;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Animacion extends JPanel implements Runnable {

    private int i;
    private boolean reversed;

    public Animacion() {
        i = 0;
        reversed = false;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Draw a line that bounces from side to side
        g.setColor(Color.GREEN);
        g.fillRect(i, getHeight() / 2, 60, 8);

        if (i == 0 || i > getWidth() - 45) {
            reversed = i > (getWidth() - 45);
        }

        if (!reversed) {
            i += 5;
        } else {
            i -= 5;
        }
    }

    /**
     * The thread for running the animation in the background.
     */
    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
