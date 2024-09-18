package clent;

import javax.swing.*;
import java.awt.*;

public class InputPanel extends JPanel {
    MainPanel clientWnd;
    JTextField textInput;
    ReportPanel reportPanel;
    InputPanel(MainPanel clientWnd) {
        this.clientWnd = clientWnd;
        setLayout(new GridLayout(2,1));

        textInput = new JTextField();
        textInput.addKeyListener(new InputListener(textInput,clientWnd));
        reportPanel = new ReportPanel(clientWnd);

        add(textInput);
        add(reportPanel);
    }
    String getReportText() {
        return reportPanel.getReportText();
    }
}
