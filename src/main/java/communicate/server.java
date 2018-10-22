package communicate;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  public static void main(String[] args) throws IOException {
    socketServer();
  }
  /**
   * ホストを設置して、 SocketのThreadを始まる。Client側の接続を待つ。
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