package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.HashMap;
import java.net.*;

class ServerRole extends Thread
{
    ServerSocket listenSocket;
    MainPanel wnd;
    HashMap<String, PrintWriter> writers;

    public ServerRole(MainPanel serverWnd) {
        this.wnd = serverWnd;
        writers = new HashMap<String,PrintWriter>();
    }
    public void run()
    {
        try
        {
            listenSocket = new ServerSocket(8081);
            wnd.writeText("Server started...");
            wnd.writeText(wnd.myIP + " on port: 8081");
            while(true) {
                Socket client = listenSocket.accept();
                String ip = client.getInetAddress().getHostAddress();
                wnd.writeText(ip + " is connected...");
                wnd.addComboBoxItem(ip);

                ClientService connection = new ClientService(client,writers,wnd);
                connection.start();
            }
        }
        catch (IOException ex)
        {
            System.out.println(ex);
        }
    }
    public void closeSocket()
    {
        try
        {
            listenSocket.close();
            wnd.writeText("Server stopped...");
        }
        catch (IOException ex)
        {
            System.out.println(ex);
        }
    }
    public void sendLine(String ip,String msg)
    {
        PrintWriter toClient = writers.get(ip);
        toClient.println(msg);
        toClient.flush();
    }
}