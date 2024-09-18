package server;

import javax.swing.*;
import java.awt.*;

class InputPanel extends JPanel
{
    MainPanel serverWnd;
    JComboBox<String> ips;
    JTextField textInput;
    InputPanel(MainPanel serverWnd) {
        this.serverWnd = serverWnd;
        setLayout(new GridLayout(2,1));

        ips = new JComboBox<String>();
        textInput = new JTextField();
        textInput.addKeyListener(new InputListener(textInput,serverWnd));

        add(ips);
        add(textInput);
    }
    public void addComboBoxItem(String ip) {
        int n;

        ips.removeItem(ip);
        ips.insertItemAt(ip,0);
        ips.setSelectedIndex(0);
    }
    String getSelectedIP() {
        return ips.getItemAt(ips.getSelectedIndex());
    }
}