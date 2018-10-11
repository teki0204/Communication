package communicate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class server {
  public static void main(String[] args) throws IOException {
    socketServer();
  }

  /**
  /* ホストを設置して、 SocketのThreadを始まる。Client側の接続を待つ。
   */
  public static void socketServer() throws IOException {
    System.out.println("Waiting for client");
    ServerSocket server = new ServerSocket(8888);
    while (true) {
      Socket socket = server.accept();
      System.out.println(socket.getInetAddress().getHostAddress() + " Connected in ");
      new SocketThread(socket).start();
    }
  }
}

/**
/* Client側から入力したmessageを受信する。
 */
class SocketThread extends Thread {
  private static List<PrintWriter> list = new ArrayList<PrintWriter>();
  private BufferedReader bufferedReader;
  private PrintWriter printWriter;

  public SocketThread(Socket socket) throws IOException {
    this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    this.printWriter = new PrintWriter(socket.getOutputStream());
    list.add(printWriter);
  }
  
  //バッファーからmessageを読み出して、表す。
  public void run() {
    String string = null;
    while (true) {
      try {
        string = bufferedReader.readLine();
        System.out.println("Client message：" + string);
      } catch (IOException e) {
      }
    }
  }
}