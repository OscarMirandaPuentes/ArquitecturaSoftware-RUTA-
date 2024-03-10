package conexiones;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;

public class HiloServidor implements Runnable {
    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;
    private static final LinkedList<Socket> usuarios = new LinkedList<>(); // Use 'final' for shared resources

    public HiloServidor(Socket soc) {
        socket = soc;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("<h2>Bienvenido....</h2>");
            usuarios.add(socket); // Add the socket to the list of connected users
        } catch (IOException e) {
            throw new RuntimeException("Error initializing thread", e);
        }
    }

    @Override
    public void run() {
        try {
            // Infinite loop to listen for messages from the client
            while (true) {
                String received = in.readUTF();
                System.out.println("Mensaje recibido del cliente (" + socket.getInetAddress() + "): " + received);

                // Broadcast the received message to all connected users
                for (Socket userSocket : usuarios) {
                    try {
                        DataOutputStream userOut = new DataOutputStream(userSocket.getOutputStream());
                        userOut.writeUTF(received);
                    } catch (IOException ex) {
                        // Handle the exception, e.g., remove the disconnected user from the list
                        ex.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            
            usuarios.remove(socket);
            System.out.println("Cliente desconectado: " + socket.getInetAddress());
        } finally {
            try {
                socket.close(); 
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
