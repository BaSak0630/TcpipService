package clent;

import javax.swing.*;

public class ReportPanel extends JPanel {
    MainPanel clientWnd;
    JTextField reportText;
    JButton reportButton;
    JButton queryButton;
    ReportPanel(MainPanel clientWnd) {
        this.clientWnd = clientWnd;

        reportText = new JTextField(15);
        reportButton = new JButton("Report");
        queryButton = new JButton("Query");

        add(reportText);
        add(reportButton);
        add(queryButton);

        reportButton.addActionListener(clientWnd);
        queryButton.addActionListener(clientWnd);
    }
    String getReportText() {
        return reportText.getText();
    }
}
