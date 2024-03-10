package conexiones;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import modelo.Administrador;


public class Servidor {
    //Inicializamos el puerto y el numero maximo de conexciones que acepta el servidor
    private final int puerto = 2027;
    private int noConexiones = 2;
    //Creamos una lista de sockets, donde guardaremos los sockets que se vayan conectando
    private LinkedList<Socket> usuarios = new LinkedList<Socket>();

    public void cantidadConexiones (int num){
        this.noConexiones = num;
        System.out.println("cantidad de conexiones: "+noConexiones);
    }
    public LinkedList<Socket> getUsuarios() {
        return usuarios;
    }
       
   //Funcion para que el servidor empieze a recibir conexiones de clientes
    public boolean escuchar(){
        boolean bandera = false;
        try {
           
            ServerSocket servidor = new ServerSocket(puerto,noConexiones);
            int contador = 0;
            //Ciclo infinito para estar escuchando por nuevos clientes
            while(contador < (noConexiones-1)){
                System.out.println("Escuchando....");
                //Cuando un cliente se conecte guardamos el socket en nuestra lista
                Socket cliente = servidor.accept();
                contador++;
                usuarios.add(cliente);
                
                Runnable  run = new HiloServidor(cliente);
                Thread hilo = new Thread(run);
                hilo.start();
            }
            bandera = true;
            System.out.println("Conexiones completadas");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bandera;
        
    }
    private LinkedList<DataOutputStream> outputStreams = new LinkedList<>();
    public synchronized void broadcastMessage(String message) {
        for (DataOutputStream dos : outputStreams) {
            try {
                dos.writeUTF(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
  
    public static void main(String[] args) {
        Servidor servidor= new Servidor();
        servidor.escuchar();
    }
}