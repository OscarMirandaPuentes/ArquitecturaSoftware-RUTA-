package vista;

import controlador.Controlador;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Ventana extends JFrame {

    Controlador ev;
    Tablero tb;
    private ImageIcon imagenDeFondo;
    private JPanel panel;
    PaletaColores c = new PaletaColores();


    public Ventana(Controlador e){
        super("Ruta");
        this.ev=e;
        initComponets();
    }

    public void initComponets(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1920, 1080);
        this.tb = new Tablero();
        this.add(tb);

        cargarFondo();
        // Crear el JPanel personalizado para dibujar la imagen de fondo
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (imagenDeFondo != null) {
                    g.drawImage(imagenDeFondo.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            }
        }; 
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
        this.add(panel);
        botones(panel);
        this.setVisible(true);
    }

    private void botones(JPanel panel2) {

        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 500));

        JButton dosJugadoresButton = new JButton("2 Jugadores");
        JButton enParejasButton = new JButton("En Parejas");
        JButton enTriosButton = new JButton("En Trios");

        dosJugadoresButton.addActionListener(ev);
        enParejasButton.addActionListener(ev);
        enTriosButton.addActionListener(ev);

        // Cambiar el color de fondo de los botones
        dosJugadoresButton.setBackground(c.rojo);
        enParejasButton.setBackground(c.rojo);
        enTriosButton.setBackground(c.rojo);

        dosJugadoresButton.setForeground(c.azul);
        enParejasButton.setForeground(c.azul);
        enTriosButton.setForeground(c.azul);

        dosJugadoresButton.setFont(c.font);
        enParejasButton.setFont(c.font);
        enTriosButton.setFont(c.font);

        panel2.add(dosJugadoresButton);
        panel2.add(enParejasButton);
        panel2.add(enTriosButton);
    }

    public void cargarFondo(){
        try {
            imagenDeFondo = new ImageIcon(ImageIO.read(new File("Ruta/Imagenes/RUTA.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String pedirNombre() {
        // Pedir al usuario que ingrese su nombre
        return JOptionPane.showInputDialog(null, "Por favor, ingrese su nombre:");
    }

    /*
    TO-DO: crear cartas 
    La sumatoria de los puntos avanzados debe estar en display todo el tiempo para que los oponentes y aliados sepan cuánto se ha avanzado. 
    */

}
