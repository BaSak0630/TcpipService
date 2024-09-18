package clent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MainPanel extends JPanel implements ActionListener {
    String ipAddress = "localhost";
    Socket chatClient;
    BufferedReader fromChatServer;
    PrintWriter toChatServer;
    Thread chatThread;

    ButtonPanel buttonPanel;
    InputPanel inputPanel;
    JTextArea textBox;
    JScrollPane textPane;
    MainPanel() {
        buttonPanel = new ButtonPanel(this);
        setLayout(new BorderLayout());

        textBox = new JTextArea();
        textPane = new JScrollPane(textBox);
        textBox.setBorder(BorderFactory.createLoweredBevelBorder());
        inputPanel = new InputPanel(this);

        add(buttonPanel,BorderLayout.NORTH);
        add(textPane,BorderLayout.CENTER);
        add(inputPanel,BorderLayout.SOUTH);
    }
    public void writeText(String msg) {
        textBox.append(msg + "\r\n");
    }
    public void sendLine(String msg) {
        toChatServer.println(msg);
        toChatServer.flush();
    }
    public void actionPerformed(ActionEvent ev) {
        String cmd = ev.getActionCommand();
        String ipAddress = buttonPanel.getIpAddress();
        if (cmd.equals("Report"))
        {
            try
            {
                Socket client = new Socket(ipAddress,8081);
                writeText("Reporting...");
                BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter toServer = new PrintWriter(client.getOutputStream());

                toServer.println("1");
                toServer.flush();

                String s = inputPanel.getReportText();
                toServer.println(s);
                toServer.flush();

                s = fromServer.readLine();
                writeText("[Server says] " + s);
            }
            catch (IOException ex)
            {
                System.out.println(ex);
            }
        } else if (cmd.equals("Query"))
        {
            try
            {
                Socket client = new Socket(ipAddress,8081);
                writeText("Asking...");
                BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter toServer = new PrintWriter(client.getOutputStream());

                toServer.println("2");
                toServer.flush();

                String s = fromServer.readLine();
                writeText("[Server answered] " + s + " Won.");
            }
            catch (IOException ex)
            {
                System.out.println(ex);
            }
        } else if (cmd.equals("Connect"))
        {
            try
            {
                chatClient = new Socket(ipAddress,8081);
                writeText("Connected...");
                fromChatServer = new BufferedReader(new InputStreamReader(chatClient.getInputStream()));
                toChatServer = new PrintWriter(chatClient.getOutputStream());

                toChatServer.println("0");
                toChatServer.flush();

                chatThread = new Thread() {
                    public void run() {
                        try
                        {
                            String msg;
                            while(true) {
                                msg = fromChatServer.readLine();
                                writeText("[Server says] " + msg);
                            }
                        }
                        catch (IOException ex)
                        {
                            System.out.println(ex);
                        }
                    }
                };
                chatThread.start();
            }
            catch (IOException ex)
            {
                System.out.println(ex);
                chatThread.stop();
            }
        } else if (cmd.equals("Close"))
        {
            try
            {
                fromChatServer.close();
                toChatServer.close();
                chatClient.close();
                chatThread.stop();
            }
            catch (IOException ex)
            {
                System.out.println(ex);
            }
        }
    }
}
