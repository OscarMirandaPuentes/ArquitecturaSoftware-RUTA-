package conexiones;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

public class HiloServidor implements Runnable {
    private final Socket socket;
    String nombre;
    private final DataInputStream in;
    private final DataOutputStream out;
    private static final HashMap<Socket, DataOutputStream> usuarios = new HashMap<>(); // Use 'final' for shared resources

    public HiloServidor(Socket soc) {
        socket = soc;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("<h2>Bienvenido....</h2>");
            usuarios.put(socket, out); // Add the socket and its output stream to the map
            
        } catch (IOException e) {
            throw new RuntimeException("Error initializing thread", e);
        }
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
            // Infinite loop to listen for messages from the client
            while (true) {
                String received = in.readUTF();
                System.out.println("Mensaje recibido del cliente (" + socket.getInetAddress() + "): " + received);
                //System.out.println("cliente (" + socket. + ")");

                // Broadcast the received message to all connected users
                for (DataOutputStream userOut : usuarios.values()) {
                    try {
                        userOut.writeUTF(received);
                    } catch (IOException ex) {
                        // Handle the exception, e.g., remove the disconnected user from the map
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
