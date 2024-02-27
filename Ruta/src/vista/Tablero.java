package vista;

import controlador.Controlador;
import modelo.Carta;
import modelo.Equipo;

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

    ImageIcon pilaBEq1 = new ImageIcon();
    ImageIcon pilaBEq2 = new ImageIcon();
    ImageIcon pilaDEq1 = new ImageIcon();
    ImageIcon pilaDEq2 = new ImageIcon();
    ImageIcon pilaVEq1 = new ImageIcon();
    ImageIcon pilaVEq2 = new ImageIcon();

    JLabel labelImagenBEq1 = new JLabel(pilaBEq1);
    JLabel labelImagenBEq2 = new JLabel(pilaBEq2);
    JLabel labelImagenDEq1 = new JLabel(pilaDEq1);
    JLabel labelImagenDEq2 = new JLabel(pilaDEq2);
    JLabel labelImagenpilaVEq1 = new JLabel(pilaVEq1);
    JLabel labelImagenpilaVEq2 = new JLabel(pilaVEq2);

    JLabel name;


    public Tablero(Controlador e){
        super("Ruta");
        this.ev=e;
        setSize(1920, 1080);
        initComponents();
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

    private void initComponents(){
        JPanel tablePanel = new JPanel();
        tablePanel.setBackground(Color.GREEN);
        add(tablePanel, BorderLayout.CENTER);

        // Panel para la mano del jugador
        JPanel playerHandPanel = new JPanel();
        playerHandPanel.setBackground(Color.LIGHT_GRAY);
        playerHandPanel.setLayout(new FlowLayout());
        add(playerHandPanel, BorderLayout.SOUTH);

        // Panel para la pila de cartas
        JPanel discardPilePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 150, 0));
        discardPilePanel.setBackground(Color.WHITE);
        JLabel discardPileLabel = new JLabel("Discard Pile");
        discardPilePanel.add(discardPileLabel);
        add(discardPilePanel, BorderLayout.NORTH);

        // Panel para el mazo de cartas
        JPanel drawPilePanel = new JPanel();
        drawPilePanel.setBackground(Color.WHITE);
        JLabel drawPileLabel = new JLabel("Draw Pile");
        this.name = new JLabel("");
        drawPilePanel.add(drawPileLabel);
        add(drawPilePanel, BorderLayout.EAST);
        add(name, BorderLayout.EAST);

        botones(playerHandPanel);

        // Inicializar labels de las pilas
        discardPilePanel.add(labelImagenBEq1);
        discardPilePanel.add(labelImagenBEq2);
        discardPilePanel.add(labelImagenDEq1);
        discardPilePanel.add(labelImagenDEq2);
        discardPilePanel.add(labelImagenpilaVEq1);
        discardPilePanel.add(labelImagenpilaVEq2);
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

    public void setPilas(Equipo eq1, Equipo eq2, String name) {
        this.name.setText(name);
        if(!eq1.pilaBatalla.isEmpty()){
            pilaBEq1 = cartas.obtenerImagen(eq1.pilaBatalla.cimaCarta().tipo);
            Image imagenRedimensionadaBEq1 = redimensionarImagen(pilaBEq1);
            establecerImagen(labelImagenBEq1, imagenRedimensionadaBEq1, "Pila Batalla Equipo 1 vacía");
        }
        if(!eq2.pilaBatalla.isEmpty()){
            pilaBEq2 = cartas.obtenerImagen(eq2.pilaBatalla.cimaCarta().tipo);
            Image imagenRedimensionadaBEq2 = redimensionarImagen(pilaBEq2);
            establecerImagen(labelImagenBEq2, imagenRedimensionadaBEq2, "Pila Batalla Equipo 1 vacía");
        }

        if(!eq1.pilaDistancia.isEmpty()){
            pilaDEq1 = cartas.obtenerImagen(eq1.pilaDistancia.cimaCarta().tipo);
            Image imagenRedimensionadaDEq1 = redimensionarImagen(pilaDEq1);
            establecerImagen(labelImagenDEq1, imagenRedimensionadaDEq1, "Pila Batalla Equipo 1 vacía");
        }
        if(!eq2.pilaDistancia.isEmpty()){
            pilaDEq2 = cartas.obtenerImagen(eq2.pilaDistancia.cimaCarta().tipo);
            Image imagenRedimensionadaDEq2 = redimensionarImagen(pilaDEq2);
            establecerImagen(labelImagenDEq2, imagenRedimensionadaDEq2, "Pila Batalla Equipo 1 vacía");
        }

        if(!eq1.pilaVelocidad.isEmpty()){
            pilaVEq1 = cartas.obtenerImagen(eq1.pilaVelocidad.cimaCarta().tipo);
            Image imagenRedimensionadaVEq1 = redimensionarImagen(pilaVEq1);
            establecerImagen(labelImagenpilaVEq1, imagenRedimensionadaVEq1, "Pila Batalla Equipo 1 vacía");
        }
        if(!eq2.pilaVelocidad.isEmpty()){
            pilaVEq2 = cartas.obtenerImagen(eq2.pilaVelocidad.cimaCarta().tipo);
            Image imagenRedimensionadaVEq2 = redimensionarImagen(pilaVEq2);
            establecerImagen(labelImagenpilaVEq2, imagenRedimensionadaVEq2, "Pila Batalla Equipo 1 vacía");
        }
    }

    // Método para redimensionar una imagen
    private Image redimensionarImagen(ImageIcon imagen) {
        return imagen.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    }

    // Método para establecer una imagen en un JLabel o un texto si la imagen está vacía
    private void establecerImagen(JLabel label, Image imagen, String textoVacio) {
        if (imagen != null) {
            label.setIcon(new ImageIcon(imagen));
        } else {
            label.setText(textoVacio);
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
