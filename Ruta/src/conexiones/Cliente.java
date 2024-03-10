package conexiones;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente implements Runnable {
    private Socket cliente;
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

    @Override
    public void run() {
        try {
           
            while (true) {
                String mensaje = in.readUTF();
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


public static void main(String[] args) throws IOException {
    Cliente cliente = new Cliente();
    cliente.run();

}
}