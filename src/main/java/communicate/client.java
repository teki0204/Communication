package communicate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class client {
  public static void main(String[] args) throws IOException {
    socketClient();
  }
  
  /**
  * �V�����ڑ�������āAThread�v���O���������s������B
  * �ڑ������ꍇ�ɒʐM�̓��͂Əo�͂�Thread�v���O���������s������B
  * �ڑ����Ă��Ȃ��ꍇ�ɁuServer is off�v�ƕ\������B
  */
  public static void socketClient() throws IOException {
    Socket socket = new Socket("127.0.0.1", 8888);
    if (socket.isConnected()) {
      new writer(socket).start();
      new read(socket).start();
    } else {
      System.out.println("Server is off");
    }
  }
}

/**
/* message����͂���Thread
 */
class writer extends Thread {
  private Socket socket;
  private PrintWriter printWriter;
  private Scanner scanner = new Scanner(System.in);
  private String str = null;

  public writer(Socket socket) throws IOException {
    this.socket = socket;
    this.printWriter = new PrintWriter(socket.getOutputStream());
  }
  
  //Thread�̃v���O���������s������B
  public void run() {
    scanner.useDelimiter("\r\n");
    while (true) {
      System.out.print("Please input message�F");
      str = scanner.next();
      printWriter.write(str + "\r\n");
      printWriter.flush();
      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

/**
/* Client������̓��͂��o�b�t�@�[����B
 */
class read extends Thread {
  private Socket socket;
  private BufferedReader bufferedReader;
  private String str = null;

  public read(Socket socket) throws IOException {
    this.socket = socket;
    this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
  }
 
  //�o�b�t�@�[����message��ǂݏo���āA�\���B
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