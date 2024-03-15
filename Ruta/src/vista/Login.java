package vista;

import conexiones.Cliente;

import java.net.Inet4Address;
import java.net.InetAddress;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Login extends JFrame {

    public Login(Cliente cli) {
        JTextField playerNameField = new JTextField(cli.getPlayerName(), 8);
        JPanel playerNamePanel = new JPanel();
        playerNamePanel.add(new JLabel("Your Name:"));
        playerNamePanel.add(playerNameField);

        JOptionPane.showConfirmDialog(null,
                playerNamePanel,
                "Welcome to Big Two Game",
                JOptionPane.OK_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        cli.setPlayerName(playerNameField.getText());


        boolean matched = false;
        while(!matched)
        {
            // Create dialog
            JTextField serverIPField = new JTextField(cli.DEFAULT_IP, 7);
            JTextField serverPortField = new JTextField("" + cli.DEFAULT_PORT, 6);
            JPanel serverPanel = new JPanel();
            serverPanel.add(new JLabel("IP Address:"));
            serverPanel.add(serverIPField);
            serverPanel.add(Box.createVerticalStrut(5));
            serverPanel.add(new JLabel("Port:"));
            serverPanel.add(serverPortField);

            int response = JOptionPane.showConfirmDialog(null,
                    serverPanel,
                    "Server Configuration",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if(response == JOptionPane.OK_OPTION)
            {
                int port = Integer.parseInt(serverPortField.getText());
                String ip = serverIPField.getText();

                try {
                    Inet4Address add = (Inet4Address) InetAddress.getByName(ip);
                    matched = true;
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null,
                            "Illegal IP host address.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

                if( matched && (port > 1024) )
                {
                    cli.setServerIP(ip);
                    cli.setServerPort(port);
                    matched = true;
                } else if(matched) {
                    matched = false;
                    JOptionPane.showMessageDialog(null,
                            "Illegal IP host address or TCP port number.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            } else {
                cli.setServerIP(cli.DEFAULT_IP);
                cli.setServerPort(cli.DEFAULT_PORT);
                matched = true;
            }
        }
    }
}
