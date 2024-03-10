package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class MenuInicio extends JFrame {

    private ImageIcon imagenDeFondo;
    public JPanel panel;
    PaletaColores c = new PaletaColores();
    public JButton crearSalaButton;
    public JButton unirseButton;
    public JButton salirButton ;


    public MenuInicio(){
        initComponets();
    }

    public void initComponets(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);
        //this.tb = new Tablero(ev);

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

        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 80, 300));

        crearSalaButton= new JButton("Crear sala");
        unirseButton = new JButton("Unirse");
        salirButton = new JButton("Salir");

    
        // Cambiar el color de fondo de los botones
        crearSalaButton.setBackground(c.rojo);
        unirseButton.setBackground(c.rojo);
        salirButton.setBackground(c.rojo);

        crearSalaButton.setForeground(c.azul);
        unirseButton.setForeground(c.azul);
        salirButton.setForeground(c.azul);

        crearSalaButton.setFont(c.font);
        unirseButton.setFont(c.font);
        salirButton.setFont(c.font);

        /*
        crearSalaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle crearSalaButton click here
                System.out.println("Crear sala button clicked!");
            }
        });

        unirseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle unirseButton click here
                System.out.println("Unirse button clicked!");
            }
        });

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle salirButton click here
                System.out.println("Salir button clicked!");
                // Example: Close the window
                dispose();
            }
        }); */

        panel2.add(crearSalaButton);
        panel2.add(unirseButton);
        panel2.add(salirButton);
    }

    public void cargarFondo(){
        try {
            imagenDeFondo = new ImageIcon(ImageIO.read(new File("Ruta/Imagenes/RUTA.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    TO-DO: crear cartas 
    La sumatoria de los puntos avanzados debe estar en display todo el tiempo para que los oponentes y aliados sepan cuánto se ha avanzado. 
    */

}

