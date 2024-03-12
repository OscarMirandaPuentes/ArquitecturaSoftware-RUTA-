package conexiones;

import controlador.ControllerCliente;
import modelo.Administrador;
import modelo.Carta;
import modelo.Jugador;
import modelo.Mazo;
import vista.Login;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class Cliente implements NetworkGame {
    // Creating private instance variables
    private int numOfPlayers = 2; // Number of Players
    private static final int WAITING_TIME_JOIN = 500; // Time waited for server reply
    public static final String DEFAULT_IP = "127.0.0.1";
    public static final int DEFAULT_PORT = 2396;

    private int playerID = -1, currentIdx = -1, latestIdx = -1; // The idnex of the current player (-1 for unset)

    private String playerName = "New Player"; // The name of the local player
    private String serverIP; // The IP address of the game server
    private int serverPort; // The TCP port of the game server
    private Socket sock; // The socket connection to the game server
    private ObjectOutputStream oos; // An ObjectOutputStream for sending messages to the server
    private ControllerCliente cli;

    private ArrayList<Jugador> playerList; // A list of players
    private boolean gameJoined = false, gameInProgress = false;

    public Cliente(int numOfPlayers)
    {
        this.numOfPlayers = numOfPlayers;
        Login login = new Login(this);
        System.out.format("IP: %s Port: %s%n", serverIP, serverPort);

        this.playerList = new ArrayList<Jugador>();
        for(int i = 0;i < this.numOfPlayers; ++i)
        {
            this.playerList.add(new Jugador(""));
            this.playerList.get(i).setNombre(null);
        }

        this.makeConnection();

    }

    @Override
    public void makeConnection()
    {
        try {
            this.sock = new Socket(InetAddress.getByName(serverIP), serverPort);
            oos = new ObjectOutputStream(sock.getOutputStream());

            Thread serverHandler = new Thread(new ServerHandler(sock));
            serverHandler.start();

            System.out.println("Connection established");
            System.out.println("Joining game...");

            // Send JOIN message
            this.sendMessage(new CardGameMessage(CardGameMessage.JOIN, -1, this.playerName));

            Thread.sleep(WAITING_TIME_JOIN);

            if(this.gameJoined)
            {
                this.setServerPort(this.sock.getPort());
                this.setServerIP(this.sock.getInetAddress().getHostAddress());
                System.out.format("Joined %s:%d successfully, waiting game to start...", this.serverIP, this.serverPort);
                this.sendMessage(new CardGameMessage(CardGameMessage.READY, -1, null));
            } else {
                System.out.println("Joined uncessfully.");
            }

        } catch(Exception ex) {
            System.out.println("Server non-responding, please check connetion.");
            JOptionPane.showConfirmDialog(null,
                    "Server is not responding, please re-connect.",
                    "Error",
                    JOptionPane.OK_OPTION,
                    JOptionPane.ERROR_MESSAGE);

            // ex.printStackTrace();

        }
    }

    public boolean getConnectionStatus()
    {
        return this.gameJoined;
    }

    private class ServerHandler implements Runnable
    {
        private Socket mySock;
        private ObjectInputStream ois;

        private ServerHandler(Socket sock)
        {
            try{
                mySock = sock;
                ois = new ObjectInputStream(mySock.getInputStream());

                System.out.println("Server Handler standing by.");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void run()
        {
            CardGameMessage message;
            try{
                while( (message = (CardGameMessage) ois.readObject()) != null)
                {
                    parseMessage(message);
                }
            } catch(Exception ex) {

                System.out.println("Connection lost, please re-connect");

                // disConnection();
                // Create dialog
                int response = JOptionPane.showConfirmDialog(null,
                        String.format("Connection lost, would you like to reconnect?"),
                        "Reconnect",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);


                if(response == JOptionPane.OK_OPTION)
                {
                    makeConnection();
                }
                ex.printStackTrace();
            }
        }
    }

    @Override
    public synchronized void parseMessage(GameMessage message) {
        int id = message.getPlayerID();
        String name;
        System.out.format("[MESSAGE RECIVED] TYPE: %d >>> PARSING%n", message.getType());

        // Parse messages according to type
        switch (message.getType()) {
            case CardGameMessage.PLAYER_LIST:
                System.out.println(message.getPlayerID() + " " + playerID);
                this.setPlayerID(message.getPlayerID());
                this.playerList.get(this.getPlayerID()).setNombre(this.getPlayerName());
                String[] names = (String[]) message.getData();
                for (int i = 0; i < numOfPlayers; ++i) {
                    // Explicitly set the name to an empty string
                    // Otherwise null will be converted to the default player
                    // nameby the getName() method
                    if (this.getPlayerID() != i)
                        this.playerList.get(i).setNombre(names[i] == null ? "" : names[i]);

                    System.out.println(this.playerList.get(i).getNombre());

                }

                break;

            case CardGameMessage.JOIN:
                if (id == this.getPlayerID()) {
                    this.gameJoined = true;
                } else if (this.gameJoined) {
                    if (id >= 0 && id < numOfPlayers) {
                        this.playerList.get(id).setNombre((String) message.getData());
                    }
                }
                break;

            case CardGameMessage.FULL:
                JOptionPane.showConfirmDialog(null,
                        String.format("Failed joining game: server is unreachable or full."),
                        "Failed joining game",
                        JOptionPane.OK_OPTION);
                break;


            case CardGameMessage.READY:
                if(id >= 0 && id < numOfPlayers)
                {
                    name = this.playerList.get(id).getNombre();
                }
                break;

            case CardGameMessage.START:
                this.gameInProgress = true;
                //this.table.printMsg("Game started");
                this.start((Mazo) message.getData());
                break;
            case CardGameMessage.MOVE:
                if(0 <= id && id < numOfPlayers)
                {
                    this.checkMove(id, (int[]) message.getData());
                    cli.obtenerJugadorActual(playerID);
                }
                break;
            /*
            case CardGameMessage.MSG:
                ((BigTwoTable) this.table).printChatMsg( (String) message.getData() );
                break;

            default:
                System.out.println("Received unparsable message.");

        }

    */
        }
    }

    public void makeMove(int[] carta) {
        sendMessage(new CardGameMessage(CardGameMessage.MOVE,
                playerID, carta));
    }

    private void checkMove(int id, int[] data){
        if (id % 2 == 0){
            playerList.get(id).tipoAccion(data[0],cli.getA().getJ().getEquipo1(), cli.getA().getJ().getEquipo2(), data[1]);
        }
    }

    @Override
    public synchronized void sendMessage(GameMessage message)
    {
        try
        {
            System.out.format("[MESSAGE SUCCESSFULLY SENT] TYPE: %d%n", message.getType());
            oos.writeObject(message);
            oos.flush();
        } catch (Exception ex) {
            System.out.println("[MESSAGE SENT FAILED]");
            ex.printStackTrace();
        }
    }
    public int getLatestIdx()
    {
        return this.latestIdx;
    }

    public synchronized void disConnection()
    {
        try
        {
            // Try to close the current socket
            if(this.sock != null)
            {
                this.sock.close();
                this.sock = null;
            }
            /*
            for(CardGamePlayer player : playerList)
            {
                player.removeAllCards();
                if(playerID == -1 ? true : player != playerList.get(playerID))
                { player.setName(""); }
            }*/
            this.gameInProgress = false;
            this.gameJoined = false;
            this.playerID = -1;

        } catch (IOException ex) {
            System.out.println("Disconnection failed.");
        }
    }


    public void start(Mazo mazo)
    {
        //Organizar equipos
        for(int i = 0; i < numOfPlayers; ++i)
        {
            if (i % 2 == 0) {
                this.cli.getA().getJ().getEquipo1().agregarJugador(playerList.get(i));
            } else {
                this.cli.getA().getJ().getEquipo2().agregarJugador(playerList.get(i));
            }

        }


        // Tomar cartas
        cli.getA().iniciarJuego(mazo);
        cli.mostratVista();
        cli.obtenerJugadorActual(playerID);
    }



    public int getCurrentIdx()
    {
        return this.currentIdx;
    }

    @Override
    public int getPlayerID()
    {
        return this.playerID;
    }

    @Override
    public void setPlayerID(int playerID)
    {
        this.playerID = playerID;
    }

    @Override
    public String getPlayerName()
    {
        return this.playerName;
    }

    @Override
    public void setPlayerName(String playerName)
    {
        this.playerName = playerName == "" ? "Anonymous" : playerName;
    }

    @Override
    public String getServerIP()
    {
        return this.serverIP;
    }

    @Override
    public void setServerIP(String serverIP)
    {
        try {
            Inet4Address add = (Inet4Address) InetAddress.getByName(serverIP);
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null,
                    "Illegal IP host address.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            this.serverIP = DEFAULT_IP;
        }

        this.serverIP = serverIP;
    }

    @Override
    public int getServerPort()
    {
        return this.serverPort;
    }

    @Override
    public void setServerPort(int serverPort)
    {
        this.serverPort = (serverPort > 1024) ? serverPort : DEFAULT_PORT;
    }

    public ControllerCliente getCli() {
        return cli;
    }

    public void setCli(ControllerCliente cli) {
        this.cli = cli;
    }
}