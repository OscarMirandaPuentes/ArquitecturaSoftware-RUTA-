package controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ControllerCliente implements Runnable {
    // Declare necessary variables for connection and communication
    private Socket cliente;
    private DataInputStream in;
    private DataOutputStream out;
    // The port should be the same as the server's listening port
    private int puerto = 2027;
    // Use localhost if on the same machine; otherwise, use the server machine's IP
    private String host = "localhost";
    private String mensajes = "";

    // Constructor receives the panel where messages will be displayed
    public ControllerCliente() {
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
            // Infinite loop listening for messages from the server and displaying them in the console
            while (true) {
                mensajes += in.readUTF();
                System.out.println(mensajes);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Function to send messages to the server
    public void enviarMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
