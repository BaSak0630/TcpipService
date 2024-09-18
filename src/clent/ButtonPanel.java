package clent;

import javax.swing.*;

public class ButtonPanel extends JPanel {
    MainPanel clientWnd;
    JTextField ip;
    JButton connectButton;
    JButton closeButton;
    ButtonPanel(MainPanel clientWnd) {
        this.clientWnd = clientWnd;

        ip = new JTextField(15);
        connectButton = new JButton("Connect");
        closeButton = new JButton("Close");

        ip.setText("localhost");

        add(ip);
        add(connectButton);
        add(closeButton);

        connectButton.addActionListener(clientWnd);
        closeButton.addActionListener(clientWnd);
    }
    String getIpAddress() {
        return ip.getText();
    }
}
