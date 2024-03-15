package conexiones;

public interface NetworkGame {
    public int getPlayerID();

    public void setPlayerID(int playerID);

    public String getPlayerName();

    public void setPlayerName(String playerName);

    public String getServerIP();

    public void setServerIP(String serverIP);

    public int getServerPort();

    public void setServerPort(int serverPort);

    public void makeConnection();

    public void parseMessage(GameMessage message);

    public void sendMessage(GameMessage message);
}
