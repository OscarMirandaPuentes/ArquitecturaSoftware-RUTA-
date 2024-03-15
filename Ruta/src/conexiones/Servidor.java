package conexiones;

import modelo.Mazo;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    // The name of this card game server
    private String serverName;
    // The maximum number of players in a card game
    private final int maxNumOfPlayers;
    // Array for holding sockets of the clients
    private Socket[] clientSockets;
    // Array for holding ObjectOutputStreams of the clients
    private ObjectOutputStream[] clientOutputStreams;
    // Array for holding player names of the clients
    private String[] clientNames;
    // Array for holding ready states of the clients
    private boolean[] clientReadyStates;
    // number of current players
    private int numOfPlayers = 0;
    // the main frame of the server
    private boolean serverUp = false;

    public Servidor(String sN, int maxNumOfPlayers) {
        this.serverName = sN;
        this.maxNumOfPlayers = maxNumOfPlayers;

        // creates arrays for holding client sockets, output streams, player
        // names, and ready states
        clientSockets = new Socket[maxNumOfPlayers];
        clientOutputStreams = new ObjectOutputStream[maxNumOfPlayers];
        clientNames = new String[maxNumOfPlayers];
        clientReadyStates = new boolean[maxNumOfPlayers];

    }

    public void start(int port) {
        // start the server
        try {
            // creates a ServerScoket
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Starts up the server at localhost:"
                    + serverSocket.getLocalPort());
            serverUp = true;
            while (serverUp) {
                // waits for clients to connect
                Socket clientSocket = serverSocket.accept();
                addConnection(clientSocket);
            } // while
            serverSocket.close();
        } catch (Exception ex) {
            System.out.println("Error in starting up the server at localhost:" + port);
            ex.printStackTrace();
        }
    }

    private synchronized void parseMessage(Socket clientSocket,
                                           CardGameMessage message) {
        // updates the playerID
        for (int i = 0; i < maxNumOfPlayers; i++) {
            if (clientSockets[i] == clientSocket) {
                message.setPlayerID(i);
                break;
            }
        }

        // parses the message based on it type
        switch (message.getType()) {
            case CardGameMessage.JOIN:
                // adds a player to the game
                addPlayer(clientSocket, (String) message.getData());
                break;
            case CardGameMessage.READY:
                // marks the specified player as ready for a new game
                setReadyState(clientSocket);
                break;
            case CardGameMessage.MOVE:
                System.out.println("Broadcasts a \"MOVE\" message from "
                        + clientSocket.getRemoteSocketAddress());
                // broadcast the MOVE message to all clients
                broadcastMessage(message);
                break;
            case CardGameMessage.MSG:
                System.out.println("Broadcasts a user message from "
                        + clientSocket.getRemoteSocketAddress());
                // broadcast the user message to all clients
                broadcastUserMessage(clientSocket, (String) message.getData());
                break;
            default:
                System.out.println("Wrong message type: " + message.getType());
                // invalid message
                break;
        }
    } // parseMessage

    /**
     * Broadcasts the specified message to all clients.
     *
     * @param message
     *            the specified message to be broadcast to all clients
     */
    private synchronized void broadcastMessage(CardGameMessage message) {
        if (numOfPlayers > 0) {
            for (int i = 0; i < maxNumOfPlayers; i++) {
                if (clientSockets[i] != null && clientOutputStreams[i] != null) {
                    try {
                        clientOutputStreams[i].writeObject(message);
                    } catch (Exception ex) {
                        System.out.println("Error in broadcasting a message to the client at "
                                + clientSockets[i].getRemoteSocketAddress());
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    private synchronized void addConnection(Socket clientSocket) {
        // adds this connection to the server if the server is not full
        if (numOfPlayers < maxNumOfPlayers) {
            // locates the first empty slot for the new connection
            for (int i = 0; i < maxNumOfPlayers; i++) {
                if (clientSockets[i] == null) {
                    try {
                        // creates an ObjectOutputStream for this client socket
                        ObjectOutputStream oostream = new ObjectOutputStream(
                                clientSocket.getOutputStream());

                        clientSockets[i] = clientSocket;
                        clientOutputStreams[i] = oostream;
                        clientNames[i] = null;
                        clientReadyStates[i] = false;
                        numOfPlayers++;
                        System.out.println("Establishes a connection with a client at "
                                + clientSocket.getRemoteSocketAddress());

                        // creates a thread for receiving messages from this
                        // client
                        Thread t = new Thread(new ClientHandler(clientSocket));
                        t.start();

                        // sends the player list to the new player
                        oostream.writeObject(new CardGameMessage(
                                CardGameMessage.PLAYER_LIST, i, clientNames));
                    } catch (Exception ex) {
                        System.out.println("Error in establishing a connection with a client at "
                                + clientSocket.getRemoteSocketAddress());
                        ex.printStackTrace();
                    }
                    break;
                }
            } // for
        } else {
            // Max. no. of players reached
            System.out.println("Server is full: cannot establish a connection with a client at "
                    + clientSocket.getRemoteSocketAddress());

            // creates a thread for sending a FULL message to this client, waits
            // for 1000 milliseconds and closes the socket
            Thread t = new Thread(new ClientHandler2(clientSocket));
            t.start();
        }

    }

    private synchronized void removeConnection(Socket clientSocket) {
        if (numOfPlayers > 0) {
            // locates the client socket in the array
            for (int i = 0; i < maxNumOfPlayers; i++) {
                if (clientSockets[i] == clientSocket) {
                    String name = clientNames[i];

                    clientSockets[i] = null;
                    clientOutputStreams[i] = null;
                    clientNames[i] = null;
                    clientReadyStates[i] = false;
                    numOfPlayers--;

                    System.out.println(name + " (" + clientSocket.getRemoteSocketAddress()
                            + ") leaves the game.");

                    String remoteAddress = clientSocket
                            .getRemoteSocketAddress().toString();

                    // broadcasts a message about the leaving of this player
                    broadcastMessage(new CardGameMessage(CardGameMessage.QUIT,
                            i, remoteAddress));
                    break;
                }
            }
        }
    }

    private synchronized void addPlayer(Socket clientSocket, String name) {
        if (numOfPlayers > 0) {
            // locates the client socket in the array
            for (int i = 0; i < maxNumOfPlayers; i++) {
                if (clientSockets[i] == clientSocket) {
                    // updates the name of the new player
                    clientNames[i] = name;

                    System.out.println(name + " (" + clientSocket.getRemoteSocketAddress()
                            + ") joins the game.");

                    // broadcasts a message about this player joining the game
                    broadcastMessage(new CardGameMessage(CardGameMessage.JOIN,
                            i, name));
                    break;
                }
            }
        }
    }

    private synchronized void setReadyState(Socket clientSocket) {
        if (numOfPlayers > 0) {
            // locates the client socket in the array
            for (int i = 0; i < maxNumOfPlayers; i++) {
                if (clientSockets[i] == clientSocket) {
                    clientReadyStates[i] = true;
                    System.out.println(clientNames[i] + " ("
                            + clientSocket.getRemoteSocketAddress()
                            + " ) is ready for the next game.");
                    broadcastMessage(new CardGameMessage(CardGameMessage.READY,
                            i, null));
                    break;
                }
            }
        }

        // checks if all players are ready
        if (numOfPlayers == maxNumOfPlayers) {
            for (int i = 0; i < maxNumOfPlayers; i++) {
                if (clientReadyStates[i] == false) {
                    // returns if any of the players is not ready
                    return;
                }
            }

            // resets the ready states of all the players for the next game
            for (int i = 0; i < maxNumOfPlayers; i++) {
                clientReadyStates[i] = false;
            }

            // creates a new deck, shuffles the deck, and starts a new game
            Mazo deck = new Mazo();
            System.out.println("All players are ready. Game starts.");
            broadcastMessage(new CardGameMessage(CardGameMessage.START, -1,
                    deck));
        }
    }

    public Mazo createDeck() {
        return new Mazo();
    }

    private synchronized void broadcastUserMessage(Socket clientSocket,
                                                   String msg) {
        if (numOfPlayers > 0) {
            // locates the client in the array
            for (int i = 0; i < maxNumOfPlayers; i++) {
                if (clientSockets[i] == clientSocket) {
                    String longMsg = clientNames[i] + " ("
                            + clientSocket.getRemoteSocketAddress() + "): "
                            + msg;
                    broadcastMessage(new CardGameMessage(CardGameMessage.MSG,
                            i, longMsg));
                    break;
                }
            }
        }
    }

    private class ClientHandler implements Runnable {
        private Socket clientSocket; // socket connection to the client
        private ObjectInputStream oistream; // ObjectInputStream of the client

        /**
         * Creates and returns an instance of the ClientHandler class.
         *
         * @param clientSocket
         *            the socket connection to the client
         */
        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            try {
                // creates an ObjectInputStream and chains it to the InputStream
                // of the client socket
                oistream = new ObjectInputStream(clientSocket.getInputStream());
            } catch (Exception ex) {
                System.out.println("Error in creating an ObjectInputStream for the client at "
                        + clientSocket.getRemoteSocketAddress());
                ex.printStackTrace();
            }
        } // constructor

        // implementation of method from the Runnable interface
        public void run() {
            CardGameMessage message;
            try {
                // waits for messages from the client
                while ((message = (CardGameMessage) oistream.readObject()) != null) {
                    System.out.println("Message received from "
                            + clientSocket.getRemoteSocketAddress());
                    parseMessage(clientSocket, message);
                } // close while
            } catch (Exception ex) {
                System.out.println("Error in receiving messages from the client at "
                        + clientSocket.getRemoteSocketAddress());
                ex.printStackTrace();
                // possible connection loss, removes the connection
                removeConnection(clientSocket);
            }
        } // run
    } // ClientHandler

    private class ClientHandler2 implements Runnable {
        private Socket clientSocket; // socket connection to the client

        /**
         * Creates and returns an instance of the ClientHandler2 class.
         *
         * @param clientSocket
         *            the socket connection to the client
         */
        public ClientHandler2(Socket clientSocket) {
            this.clientSocket = clientSocket;
        } // constructor

        // implementation of method from the Runnable interface
        public void run() {
            try {
                // creates an ObjectOutputStream and chains it to the
                // OutputStream
                // of the client socket
                ObjectOutputStream oostream = new ObjectOutputStream(
                        clientSocket.getOutputStream());
                // sends a FULL message to the client
                oostream.writeObject(new CardGameMessage(CardGameMessage.FULL,
                        -1, null));
                oostream.flush();
            } catch (Exception ex) {
                System.out.println("Error in sending a FULL message to the client at "
                        + clientSocket.getRemoteSocketAddress());
                ex.printStackTrace();
            }

            // sleeps for 1000 milliseconds before closing the socket
            try {
                Thread.sleep(1000);
            } catch (Exception ex) {
                System.out.println("Error in sleeping before closing the client socket at "
                        + clientSocket.getRemoteSocketAddress());
                ex.printStackTrace();
            }

            // closes the socket
            try {
                clientSocket.close();
            } catch (Exception ex) {
                System.out.println("Error in closing the client socket at "
                        + clientSocket.getRemoteSocketAddress());
                ex.printStackTrace();
            }
        } // run
    }
}
