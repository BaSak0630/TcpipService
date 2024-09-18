package server;

import javax.swing.*;
import java.awt.*;

public class ServerFrame extends JFrame
{
    ServerFrame() {
        super("Server");
        setSize(400,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.add(new MainPanel());
    }
}
