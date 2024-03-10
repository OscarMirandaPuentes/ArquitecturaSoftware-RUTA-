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
       
   //Funcion para que el servidor empieze a recibir conexiones de clientes
    public void escuchar(){
        try {
            //Creamos el socket servidor
            ServerSocket servidor = new ServerSocket(puerto,noConexiones);
            int contador = 0;
            //Ciclo infinito para estar escuchando por nuevos clientes
            while(contador < noConexiones){
                System.out.println("Escuchando....");
                //Cuando un cliente se conecte guardamos el socket en nuestra lista
                Socket cliente = servidor.accept();
                contador++;
                usuarios.add(cliente);
                //Instanciamos un hilo que estara atendiendo al cliente y lo ponemos a escuchar
                Runnable  run = new HiloServidor(cliente);
                Thread hilo = new Thread(run);
                hilo.start();
            }
            System.out.println("Conexiones completadas");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
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
    
    //Funcion main para correr el servidor
    public static void main(String[] args) {
        Servidor servidor= new Servidor();
        servidor.escuchar();
    }
}