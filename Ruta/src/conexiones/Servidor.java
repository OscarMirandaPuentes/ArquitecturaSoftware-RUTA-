package conexiones;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Servidor {

    private final int puerto = 2027;
    private int noConexiones = 2;
    public String nombre;
    private LinkedList<Socket> usuarios = new LinkedList<>();
    private LinkedList<DataOutputStream> flujosSalida = new LinkedList<>();
    public ArrayList<Cliente> listaClientes = new ArrayList<>();
    private Map<String, Socket> clientesMap = new HashMap<>();

    public void cantidadConexiones(int num) {
        this.noConexiones = num;
        System.out.println("Cantidad de conexiones: " + noConexiones);
    }

    public LinkedList<Socket> getUsuarios() {
        return usuarios;
    }

    public LinkedList<DataOutputStream> getFlujosSalida() {
        return flujosSalida;
    }

    public boolean escuchar() {
   
        boolean bandera = false;
        try {
            ServerSocket servidor = new ServerSocket(puerto, noConexiones);
            int contador = 0;

            while (contador < (noConexiones - 1)) {
                System.out.println("Escuchando....");
                Socket cliente = servidor.accept();

                usuarios.add(cliente);
                contador++;

                
                DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
                flujosSalida.add(out);
                
                
                Runnable run = new HiloServidor(cliente);
                Thread hilo = new Thread(run);
                hilo.setName(this.getNombre());
                
                System.out.println("cliente nombre: " + hilo.getName());
                asociarCliente(hilo.getName(),cliente);

                hilo.start();

            }
            bandera = true;
            enviarMensajeATodos("true");
            System.out.println("Conexiones completadas");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bandera;
    }

    public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


    // Método para asociar un cliente y su socket
    public void asociarCliente(String nombreCliente, Socket socketCliente) {
        clientesMap.put(nombreCliente, socketCliente);
    }

    // Método para enviar un mensaje a un cliente específico
    public void enviarMensajeACliente(String nombreCliente, String mensaje) {
        Socket clienteSocket = clientesMap.get(nombreCliente);
        if (clienteSocket != null) {
            try {
                DataOutputStream out = new DataOutputStream(clienteSocket.getOutputStream());
                out.writeUTF(mensaje);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para enviar un mensaje a todos los clientes
    public void enviarMensajeATodos(String mensaje) {
        for (DataOutputStream out : flujosSalida) {
            try {
                StringTokenizer tokenizer = new StringTokenizer(mensaje, "|");
                while (tokenizer.hasMoreTokens()) {
                    out.writeUTF(tokenizer.nextToken());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
