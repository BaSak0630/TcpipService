package server;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;

class ClientService extends Thread
{
    Socket client;
    HashMap<String, PrintWriter> writers;
    MainPanel wnd;
    ClientService(Socket client,HashMap<String,PrintWriter> writers,MainPanel wnd) {
        this.client = client;
        this.writers = writers;
        this.wnd = wnd;
    }
    public void run()
    {
        try
        {
            BufferedReader fromClient =
                    new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter toClient =
                    new PrintWriter(new OutputStreamWriter(client.getOutputStream()));

            int n;

            String s = fromClient.readLine();
            n = Integer.parseInt(s);
            String ip = client.getInetAddress().getHostAddress();

            if (n == 1) // report mode
            {
                wnd.writeText("[" + ip + "] " + "reported his selling today.");
                String selling = fromClient.readLine();
                wnd.writeText("[" + ip + "] " +  selling + " Won.");
                toClient.println("Thanks!!!");
                toClient.flush();

                fromClient.close();
                toClient.close();
                client.close();
                return;
            } else if (n == 2) // query mode
            {
                int x = (int)(Math.random()*10);

                toClient.println(""+x);
                toClient.flush();
                wnd.writeText("[" + ip + "] " + "asked his total selling.");

                fromClient.close();
                toClient.close();
                client.close();
                return;
            }
            // chatting mode
            writers.put(ip,toClient);
            String msg;
            while(true) {
                msg = fromClient.readLine();
                if (msg.equals("-1"))
                {
                    break;
                }
                wnd.writeText("[" + ip + " says] " + msg);
            }

            writers.remove(ip);
            wnd.writeText("Connection with " + ip + " closed.");

            fromClient.close();
            toClient.close();
            client.close();
        }
        catch (IOException ex)
        {
            System.out.println(ex);
        }
    }
}