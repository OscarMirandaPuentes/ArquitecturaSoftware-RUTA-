package vista;

import controlador.Controlador;
import modelo.Carta;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tablero  extends JFrame{
    Cartas cartas = new Cartas();
    ArrayList<JButton> cardButtons = new ArrayList<JButton>();
    JButton jButton1;
    JButton jButton2;
    JButton jButton3;
    JButton jButton4;
    JButton jButton5;
    JButton jButton6;
    JButton jButton7;
    Controlador ev;
    public Tablero(Controlador e){
        super("Ruta");
        this.ev=e;
        setSize(1920, 1080);
        JPanel tablePanel = new JPanel();
        tablePanel.setBackground(Color.GREEN);
        add(tablePanel, BorderLayout.CENTER);

        // Panel para la mano del jugador
        JPanel playerHandPanel = new JPanel();
        playerHandPanel.setBackground(Color.LIGHT_GRAY);
        playerHandPanel.setLayout(new FlowLayout());
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
        botones(playerHandPanel);
    }

    private void botones(JPanel panel) {
        jButton1 = new JButton("1");
        jButton2 = new JButton("2");
        jButton3 = new JButton("3");
        jButton4 = new JButton("4");
        jButton5 = new JButton("5");
        jButton6 = new JButton("6");
        jButton7 = new JButton("7");

        cardButtons.add(jButton1);
        cardButtons.add(jButton2);
        cardButtons.add(jButton3);
        cardButtons.add(jButton4);
        cardButtons.add(jButton5);
        cardButtons.add(jButton6);
        cardButtons.add(jButton7);

        jButton1.addActionListener(ev);
        jButton2.addActionListener(ev);
        jButton3.addActionListener(ev);
        jButton4.addActionListener(ev);
        jButton5.addActionListener(ev);
        jButton6.addActionListener(ev);
        jButton7.addActionListener(ev);

        panel.add(jButton1);
        panel.add(jButton2);
        panel.add(jButton3);
        panel.add(jButton4);
        panel.add(jButton5);
        panel.add(jButton6);
        panel.add(jButton7);


    }
    public void setButtonIcons(List<Carta> mano) {
        for (int i = 0; i < mano.size(); i++) {
            ImageIcon iconoOriginal = cartas.obtenerImagen(mano.get(i).tipo);
            Image imagenOriginal = iconoOriginal.getImage();

            // Redimensionar la imagen a un tamaño más pequeño
            Image imagenRedimensionada = imagenOriginal.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Cambia el tamaño como desees

            // Crear un nuevo ImageIcon con la imagen redimensionada
            ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);

            // Establecer el icono redimensionado en el botón
            cardButtons.get(i).setIcon(iconoRedimensionado);
        }
    }

    public int accion() {
        String[] options = {"Usar", "Descartar"};
        int opt = JOptionPane.showOptionDialog(null, "Escoge",
                "Click a button",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        return opt;
    }

    public ArrayList<JButton> getCardButtons() {
        return cardButtons;
    }

    public void setCardButtons(ArrayList<JButton> cardButtons) {
        this.cardButtons = cardButtons;
    }
}
