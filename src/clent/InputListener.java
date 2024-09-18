package clent;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputListener extends KeyAdapter {
    MainPanel clientWnd;
    JTextField textInput;
    InputListener(JTextField textInput,MainPanel clientWnd) {
        this.clientWnd = clientWnd;
        this.textInput = textInput;
    }
    public void keyPressed(KeyEvent ev) {
        int keyCode = ev.getKeyCode();
        if (keyCode == KeyEvent.VK_ENTER)
        {
            String msg = textInput.getText().trim();
            clientWnd.writeText("[I say] " + msg);
            clientWnd.sendLine(msg);
            textInput.setText("");
        }
    }
}
