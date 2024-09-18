package server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;

class MainPanel extends JPanel implements ActionListener
{
    ButtonPanel buttonPanel;
    JTextArea textBox;
    JScrollPane textPane;
    InputPanel inputPanel;
    ServerRole server;
    String myIP;

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
        writeText("Please press start button");

        try
        {
            InetAddress address = InetAddress.getLocalHost();
            myIP = address.getHostAddress();
        }
        catch (Exception ex)
        {
        }
    }
    String getSelectedIP() {
        return inputPanel.getSelectedIP();
    }
    void sendLine(String ip,String msg) {
        server.sendLine(ip,msg);
    }
    public void addComboBoxItem(String ip) {
        inputPanel.addComboBoxItem(ip);
    }
    public void writeText(String msg) {
        textBox.append(msg + "\r\n");
    }
    public void actionPerformed(ActionEvent ev) {
        String command = ev.getActionCommand();
        if (command.equals("Start"))
        {
            server = new ServerRole(this);
            server.start();
        } else if (command.equals("Stop"))
        {
            server.closeSocket();
            server.stop();
            server = null;
        }
    }
}