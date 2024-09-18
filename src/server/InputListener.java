package server;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class InputListener extends KeyAdapter
{
    MainPanel serverWnd;
    JTextField textInput;
    InputListener(JTextField textInput,MainPanel serverWnd) {
        this.serverWnd = serverWnd;
        this.textInput = textInput;
    }
    public void keyPressed(KeyEvent ev) {
        int keyCode = ev.getKeyCode();
        if (keyCode == KeyEvent.VK_ENTER)
        {
            String msg = textInput.getText().trim();
            serverWnd.writeText("[I say ] " + msg);
            String clientIP = serverWnd.getSelectedIP();
            serverWnd.sendLine(clientIP,msg);
            textInput.setText("");
        }
    }
}