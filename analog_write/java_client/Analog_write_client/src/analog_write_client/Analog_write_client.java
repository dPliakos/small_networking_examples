package analog_write_client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.*;

public class Analog_write_client {

    Socket s = null;
    InetAddress HOST = null;
    int PORT;
    int chunckLength = 3;
    
    
    boolean createSocket(String ip, int port) {
        this.PORT = port;
        try {
          this.HOST = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
          System.err.println("Error: could not create address.");
          return false;
        }
        try {
          this.s = new Socket(HOST, PORT);
        } catch (IOException e) {
          System.err.println("Error: could not create socket.");
          return false;
        }
        return true;
    }
    boolean close() {
      try {
        s.close();
      } catch(IOException e) {
        System.err.println("Error: could not close the port connection.");
        return false;
      }
      return true;
    }
    
    public boolean write(String num) {
        BufferedWriter out;
//        OutputStream out;
        try {
            out = new BufferedWriter(new OutputStreamWriter(this.s.getOutputStream()));
        } catch (IOException ex) {
            System.out.println("Error: could not open stram to socket.");
            close();
            return false;
        }
        
        for (int i=0; i<chunckLength - num.length(); i++)
            num = "0" + num;
        
        try {
            out.write(num);
            out.flush();
            System.out.println(num);
        } catch (IOException ex) {
            System.err.println("Error: could not write to socket.");
            close();
            return false;
        }
        close();
        return true;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Analog_write_client client = new Analog_write_client();
        
        if (client.createSocket("192.168.2.3", 5002))
            System.out.println(client.write("10"));
    }
    
}
