package socket.simple;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void start(){
		
		try {
			Socket s1 = new Socket("127.0.0.1",9999);
			
			InputStream is = s1.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			
			//readUTF马上就此阻塞
			System.out.println(dis.readUTF());
			dis.close();
			s1.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {

		start();
		
	}

}
