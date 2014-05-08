package socket.noblocking;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.net.*;
import java.util.*;


public class ChatServer{
    private Selector selector = null;
    private ServerSocketChannel ssc = null;
    //服务器端通信端口号
    private int port = 8888;
    //在线用户列表
	private Hashtable<String,SocketChannel> userList = null;   
    
    public ChatServer(){}    
	public ChatServer(int port){
		this.port = port;
    }
    
	private ServerSocketChannel tmpSS = null;
	
    //初始化服务器
    public void init(){
    	try{
    		//创建选择器对象
			selector = Selector.open();
			//创建ServerSocketChannel
			ssc = ServerSocketChannel.open();
			tmpSS = ssc;
			System.out.println("init ssc=" + ssc);
			//设置ServerSocketChannel为非阻塞模式
			ssc.configureBlocking(false);
			//InetAddress ia = InetAddress.getLocalHost();
			InetAddress ia = InetAddress.getByName("127.0.0.1");
			System.out.println(ia.getHostAddress());
			InetSocketAddress isa = new InetSocketAddress(ia,port);
			//将与本通道相关的服务器套接字对象帮定到指定地址和端口
			ssc.socket().bind(isa);
			//创建在线用户列表
			userList = new Hashtable<String,SocketChannel>();
		}catch(IOException e){
			e.printStackTrace();	
		}
    }
    
    //启动服务器
	public void start(){
		try{
			//将ServerSocketChannel注册到Selector上，准备接收新连接请求
			SelectionKey acceptKey = ssc.register(selector, SelectionKey.OP_ACCEPT );
			SocketChannel sc;
			int n;	
			String name;   	//用户名
			String msg;		//用户发言信息
			while (true){	
				//选择当前所有处于就绪状态的通道所对应的选择键,并将这些键组成已选择键集
				System.out.println("start select()");
				n = selector.select(); //n为已选择键集中键的个数
				System.out.println("end selector()");
		    	if(n > 0 ){
		    		//获取此选择器的已选择键集。
					Set readyKeys = selector.selectedKeys(); 
					Iterator it = readyKeys.iterator();	
					//遍历当前已选择键集
					while (it.hasNext()) {
						SelectionKey key = (SelectionKey)it.next();
						//从当前已选择键集中移除当前键，避免重复处理
						it.remove();
						//如果当前键对应的通道已准备好接受新的套接字连接
						if (key.isAcceptable()) {
							//获取当前键对应的可选择通道（ServerSocketChannel）  
							ssc = (ServerSocketChannel) key.channel();
							System.out.println("ssc=" + ssc);
							if(tmpSS == ssc){
								System.out.println("tmp equals ssc");
							} else {
								System.out.println("tmp  not equals ssc");
							}
							//接收新的套接字连接请求，返回新建的SocketChannel
							sc = (SocketChannel) ssc.accept();
							//如果有新用户接入
							if(sc != null){
								//接收新上线用户姓名
								name = readMessage(sc);
								//设置新建的SocketChannel为非阻塞模式
								sc.configureBlocking(false);
								//将新建的SocketChannel注册到Selector上，准备进行数据"写"操作，
								//并将当前用户名以附件的方式附带记录到新建的选择键上。
								SelectionKey newKey = sc.register(selector,SelectionKey.OP_WRITE,name);	
								//将新上线用户信息加入到在线用户列表
								userList.put(name,sc);
								//发送"新用户上线"通知
								transmitMessage(name + " in!","--Server Info--");
							}
						}
						//否则，如果当前键对应的通道已准备好进行"写"操作
						else if (key.isWritable()) {
							//获取当前键对应的可选择通道（SocketChannel） 
							sc = (SocketChannel)key.channel();
							//接收该通道相应用户的发言信息
							msg = readMessage(sc);
							//获取选择键上附带记录的当前用户名
							name = key.attachment().toString();
							//如果用户提出要下线
							if(msg.equals("bye")){
								//从在线用户列表中移除当前用户
								userList.remove(name);
								//注销当前选择键对应的注册关系
								key.cancel();
								//关闭当前可选择通道 
								sc.close();
								//发送"用户下线"通知
								transmitMessage(name + " out!","--Server Info--");
							}
							//否则，如果接收到的用户发言信息非空（""）
							else if(msg.length() > 0){
								//转发用户发言信息
								transmitMessage(msg,name);
							}
						}
					}
				}
				//延时循环，降低服务器端处理负荷
				Thread.sleep(500);
			}
		}catch(Exception e){
			e.printStackTrace();	
		}
    }

	//转发用户发言信息
	public void transmitMessage(String msg,String name){
		try{
			ByteBuffer buffer = ByteBuffer.wrap((name + ":" + msg).getBytes("GBK"));
			Collection channels = userList.values();
			SocketChannel sc;
			for(Object o:channels){
				sc = (SocketChannel)o;
				sc.write(buffer);
				buffer.flip();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
    }
  
  	//接收用户发言信息
    public String readMessage(SocketChannel sc){
		String result = null;
		int n = 0;
        ByteBuffer buf = ByteBuffer.allocate(1024);
		try{
            n = sc.read(buf);
			buf.flip();
			Charset charset = Charset.forName("GBK");
			CharsetDecoder decoder = charset.newDecoder();
			CharBuffer charBuffer = decoder.decode(buf);
			result = charBuffer.toString();
        }catch(IOException e){
			e.printStackTrace();
		}
		return result;
    }

    public static void main(String args[]){
		ChatServer server = new ChatServer();
		server.init();
		server.start();
	}
}