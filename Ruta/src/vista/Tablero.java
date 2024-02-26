package vista;

import javax.swing.*;
import java.awt.*;

public class Tablero  extends JPanel{
    public Tablero(){
        // Panel para la mesa de juego
        JPanel tablePanel = new JPanel();
        tablePanel.setBackground(Color.GREEN);
        add(tablePanel, BorderLayout.CENTER);

        // Panel para la mano del jugador
        JPanel playerHandPanel = new JPanel();
        playerHandPanel.setBackground(Color.LIGHT_GRAY);
        add(playerHandPanel, BorderLayout.SOUTH);

        // Panel para la pila de cartas
        JPanel discardPilePanel = new JPanel();
        discardPilePanel.setBackground(Color.WHITE);
        JLabel discardPileLabel = new JLabel("Discard Pile");
        discardPilePanel.add(discardPileLabel);
        add(discardPilePanel, BorderLayout.NORTH);

        // Panel para el mazo de cartas
        JPanel drawPilePanel = new JPanel();
        drawPilePanel.setBackground(Color.WHITE);
        JLabel drawPileLabel = new JLabel("Draw Pile");
        drawPilePanel.add(drawPileLabel);
        add(drawPilePanel, BorderLayout.EAST);
    }


}
