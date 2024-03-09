package conexiones;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Servidor extends Conexion //Se hereda de conexión para hacer uso de los sockets y demás
{
    private int numConexiones;
    
    public Servidor(int numConexiones) throws IOException {//Se usa el constructor para servidor de Conexion
        super("servidor");
        JOptionPane.showMessageDialog(null, "Esperando", "Servidor", JOptionPane.INFORMATION_MESSAGE); //Esperando conexión
        this.numConexiones = numConexiones;
    }

    //Método para iniciar el servidor
    public boolean esperarConexion() {
        boolean bandera = false;
        try {
            if(numConexiones > 0){
                cs = ss.accept(); //Accept comienza el socket y espera una conexión desde un cliente     
                JOptionPane.showMessageDialog(null, "Conexión exitosa","Servidor",JOptionPane.INFORMATION_MESSAGE);
                numConexiones--;
                bandera = true;
            }else
               JOptionPane.showMessageDialog(null, "Se han superado las conexiones", "Servidor", JOptionPane.ERROR_MESSAGE);           
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return bandera;
    }

    /**
     * Envia los datos al cliente
     * 
     * @param dato Datos a enviar
     */
    public void enviarDatoCliente(String dato) {
        try {
            salida = new DataOutputStream(cs.getOutputStream());
            salida.writeUTF(dato);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    /**
     * Limpia el buffer de la salida del servidor
     */
    public void limpiarSalida(){
        try {
            salida.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Cierra la conexión con el cliente.
     */
    public void cerrarConexiónCliente(){
        try {
            cs.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Lee los datos que ha enviado el cliente.
     * 
     * @return Devuelve los datos del cliente. 
     */
    public String leerDatosCliente(){
        String dato = null;
        try {
            entrada = new DataInputStream(cs.getInputStream());
            dato = entrada.readUTF();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return dato;       
    }
            
    /**
     * Finaliza el servidor
     */
    public void finalizarConexión()  {
        try {
            ss.close();//Se finaliza la conexión
            JOptionPane.showMessageDialog(null, "Fin Servidor", "Servidor", JOptionPane.INFORMATION_MESSAGE); //Esperando conexión
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void main(String[] args) throws IOException {
        Servidor servidor = new Servidor(2);
        servidor.esperarConexion();
        servidor.enviarDatoCliente("Hola que tal");
        System.out.println(servidor.leerDatosCliente());
        servidor.cerrarConexiónCliente();
        servidor.finalizarConexión();
    }
}
