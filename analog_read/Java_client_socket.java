import java.net.InetAddress;
import java.net.Socket;
import java.io.*;
import java.util.Arrays;

class Java_client_socket {

  Socket s = null;
  InetAddress HOST = null;
  int PORT;
  int chunckLength = 3;

  public Java_client_socket() {
    // pass
  }

  public boolean createSocket(String ip, int port) {
    this.PORT = port;
    try {
      this.HOST = InetAddress.getByName(ip);
    } catch (Exception e) {
      System.err.println("Error: could not create address.");
      return false;
    }
    try {
      this.s = new Socket(HOST, PORT);
    } catch (Exception e) {
      System.err.println("Error: could not create socket.");
      return false;
    }
    return true;
  }

  public boolean close() {
    try {
      s.close();
    } catch(Exception e) {
      System.err.println("Error: could not close the port connection.");
      return false;
    }
    return true;
  }

  public int read() {
    int count = 0;
    try {
      InputStreamReader inpt = new InputStreamReader(this.s.getInputStream());
      String line = "";
      while (true) {
        char[] store = new char[chunckLength];
        int current = inpt.read(store, 0, chunckLength);
        for (char s : store) line += s;
        System.out.println(line);
        count += 1;
        if (current < 0) break;
      }
    } catch (Exception e) {
      System.err.println("Error: could not create the socket.");
      return -1;
    }
    return count;
  }

  public static void main(String args[]) {
    int count = 0;
    Java_client_socket s = new Java_client_socket();

    if (!s.createSocket("192.168.2.3", 5002)) System.exit(1);
    if ((count = s.read()) > 0) System.out.println("Received: " + count + " bytes.");
    System.out.println("Socket closed " + (s.close() ? "successfully." : " with error."));

  }
}
