package socket.simple;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.lang3.RandomStringUtils;

public class Server {

	public static void start(){
		try {
			ServerSocket s = new ServerSocket(9999);
			boolean go = true;
			while(go){
				go = false;
				Socket s1 = s.accept();
				OutputStream os = s1.getOutputStream();
				DataOutputStream dos = new DataOutputStream(os);
				
				
				try {
					System.out.println("this is Server(sleep...)");
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				dos.writeUTF("hello" + RandomStringUtils.randomAlphabetic(3));

				//清空此数据输出流。这迫使所有缓冲的输出字节被写出到流中。
				dos.flush();

				
				dos.close();
				s1.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		
		start();
		
	}
	
}
