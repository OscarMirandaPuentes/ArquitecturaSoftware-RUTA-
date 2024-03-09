package conexiones;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.StringTokenizer;


public class Cliente extends Conexion {
    
    /**
     * Constructor de la clase 
     * 
     * @throws IOException Indica si se conecta o no
     */
    public Cliente() throws IOException{
        super("cliente");
    } //Se usa el constructor para cliente de Conexion

    
    /**
     * Lee los datos que le envia el servidor
     * 
     * @return Devuelve los datos
     */
    public String leerDatosServidor(){
        String dato = null;
        try {         
            //Flujo de datos del servidor hacia el cliente
            entrada = new DataInputStream(cs.getInputStream());
            dato = entrada.readUTF();              
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return dato;
    }
    /**
     * Envia los datos al servidor
     * 
     * @param dato Los datos que se quieren enviar
     */
    public void enviarDatosServidor(String dato){
        try {
            salida = new DataOutputStream(cs.getOutputStream());
            salida.writeUTF(dato);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Limpia el buffer de la salida del cliente.
     */
    public void limpiarSalida(){
        try {
            salida.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * Cierra el cliente.
     */
    public void cerrarCliente() {
        try {
            cs.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
       
    }

    
    public static void main(String[] args) throws IOException {
        Cliente cliente = new Cliente();
        StringTokenizer separador = new StringTokenizer(cliente.leerDatosServidor());
        System.out.println("x = " + separador.nextToken() + "  y = " + separador.nextToken());
        cliente.cerrarCliente();
    }
}
