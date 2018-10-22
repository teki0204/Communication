package communicate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Client側からの入力をバッファーする。
 */
class Read extends Thread {
  
  private Socket socket;
  private BufferedReader bufferedReader;
  private String str = null;

  public Read(Socket socket) throws IOException {
    this.socket = socket;
    this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
  }
  
  //バッファーからmessageを読み出して、表す。
  public void run() {
    while (true) {
      try {
        str = bufferedReader.readLine();
        System.out.println(str);
      } catch (IOException e) {
      }
      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
        
        e.printStackTrace();
      }
    }
  }
}