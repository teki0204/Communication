package communicate;

import java.io.IOException;
import java.net.Socket;

public class Client {

  public static void main(String[] args) throws IOException {
    socketClient();
  }

  /**
  * 新しい接続を作って、Threadプログラムを実行させる。
  * 接続した場合に通信の入力と出力のThreadプログラムを実行させる。
  * 接続していない場合に「Server is off」と表示する。
  */
  public static void socketClient() throws IOException {
    Socket socket = new Socket("127.0.0.1", 8888);
    if (socket.isConnected()) {
      new Writer(socket).start();
      new Read(socket).start();
    } else {
      System.out.println("Server is off");
    }
  }
}