package vista;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class Cartas {

    private Map<String, String> imagenesCartas;

    public Cartas() {
        // Inicializar el diccionario de imágenes de cartas
        imagenesCartas = new HashMap<>();

        imagenesCartas.put("Sin gasolina", "Ruta/Imagenes/Cartas/3.png");
        imagenesCartas.put("Pinchazo", "Ruta/Imagenes/Cartas/1.png");
        imagenesCartas.put("Accidente", "Ruta/Imagenes/Cartas/5.png");
        imagenesCartas.put("Límite de velocidad", "Ruta/Imagenes/Cartas/7.png");
        imagenesCartas.put("Pare", "Ruta/Imagenes/Cartas/9.png");
        imagenesCartas.put("Gasolina", "Ruta/Imagenes/Cartas/4.png");
        imagenesCartas.put("Llanta de repuesto", "Ruta/Imagenes/Cartas/2.png");
        imagenesCartas.put("Reparación", "Ruta/Imagenes/Cartas/6.png");
        imagenesCartas.put("Fin de límite", "Ruta/Imagenes/Cartas/8.png");
        imagenesCartas.put("Siga", "Ruta/Imagenes/Cartas/10.png");
        imagenesCartas.put("Cisterna", "Ruta/Imagenes/Cartas/16.png");
        imagenesCartas.put("Llanta irrompible", "Ruta/Imagenes/Cartas/17.png");
        imagenesCartas.put("As al volante", "Ruta/Imagenes/Cartas/18.png");
        imagenesCartas.put("Vía libre", "Ruta/Imagenes/Cartas/19.png");
        imagenesCartas.put("200", "Ruta/Imagenes/Cartas/11.png");
        imagenesCartas.put("100", "Ruta/Imagenes/Cartas/12.png");
        imagenesCartas.put("75", "Ruta/Imagenes/Cartas/13.png");
        imagenesCartas.put("50", "Ruta/Imagenes/Cartas/14.png");
        imagenesCartas.put("25", "Ruta/Imagenes/Cartas/15.png");
    }

    // Obtener la ubicación de la imagen de una carta dado su nombre
    public String obtenerRuta(String nombreCarta) {
        return imagenesCartas.getOrDefault(nombreCarta, "Ruta/Imagenes/Cartas/20.png");
    }

    public ImageIcon obtenerImagen(String nombreCarta) {
        String rutaImagen = obtenerRuta(nombreCarta);
        ImageIcon imagenCarta = new ImageIcon(rutaImagen);
        return imagenCarta;
    }
}
