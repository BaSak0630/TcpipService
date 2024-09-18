package server;

import javax.swing.*;

class ButtonPanel extends JPanel
{
    MainPanel serverWnd;
    JButton startButton;
    JButton stopButton;
    JButton salesRecordButton;
    ButtonPanel(MainPanel serverWnd) {
        this.serverWnd = serverWnd;

        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        salesRecordButton = new JButton("Sales Record");

        add(startButton);
        add(stopButton);
        add(salesRecordButton);

        startButton.addActionListener(serverWnd);
        stopButton.addActionListener(serverWnd);
        salesRecordButton.addActionListener(serverWnd);
    }
}