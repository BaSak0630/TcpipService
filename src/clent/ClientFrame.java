package clent;

import javax.swing.*;
import java.awt.*;

public class ClientFrame extends JFrame {
    ClientFrame() {
        super("Client");
        setSize(400,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.add(new MainPanel());
    }
}
