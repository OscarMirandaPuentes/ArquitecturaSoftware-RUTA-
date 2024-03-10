package conexiones;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente implements Runnable {
    private Socket cliente;
    private String nombre ;
    public String mensaje;
    private DataInputStream in;
    private DataOutputStream out;
    private int puerto = 2027;

    private String host = "localhost";

    public Cliente() {
        try {
            cliente = new Socket(host, puerto);
            
            in = new DataInputStream(cliente.getInputStream());
            out = new DataOutputStream(cliente.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return cliente;
    }
    public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

    @Override
    public void run() {
        try {
           
            while (true) {
                mensaje = in.readUTF();
                System.out.println("Mensaje recibido: " + mensaje);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Function to send messages to the server
    public void enviarMsg(String msg) {
        try {
            out.writeUTF(msg);
            out.flush(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void limpiarSalida(){
        try {
            out.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


}