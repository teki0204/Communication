package communicate;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


/**
 * messageを入力するThread
 */
class Writer extends Thread {
  
  private Socket socket;
  private PrintWriter printWriter;
  private Scanner scanner = new Scanner(System.in);

  public Writer(Socket socket) throws IOException {
    this.socket = socket;
    this.printWriter = new PrintWriter(socket.getOutputStream());
  }
  
  //Threadのプログラムを実行させる。
  //keyboardで入力する。1つの文が複数行に渡って、return keyを入力すると終了する。
  public void run() {
    System.out.print("Please input message：");
    while (true) {
      String nextLine = scanner.nextLine();
      printWriter.write(nextLine + "\r\n");
      if (nextLine.equals("")) {
        printWriter.flush();
        System.out.print("Please input message：");
      }
      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}