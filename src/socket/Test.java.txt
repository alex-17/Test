package socket;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.utils.AddrUtil;

public class Test {

	public static void main(String[] args) throws IOException {

		MemcachedClientBuilder builder = new XMemcachedClientBuilder(
				AddrUtil.getAddresses("localhost:11211"));
		
		//builder.setSessionLocator(new KetamaMemcachedSessionLocator());
		
		//Xmemcached是基于java nio的client实现，默认对一个memcached节点只有一个连接，这在通常情况下已经有非常优异的表现。
		//但是在典型的高并发环境下,nio的单连接也会遇到性能瓶颈。因此XMemcached支持设置nio的连接池，允许建立多个连接到同一个memcached节点，
		//但是请注意，这些连接之间是不同步的，因此你的应用需要自己保证数据更新的同步
		builder.setConnectionPoolSize(5);
		
		MemcachedClient memcachedClient = builder.build();
		
		
		try {
			memcachedClient.set("hello", 0, "Hello,xmemcached");
			String value = memcachedClient.get("hello");
			System.out.println("hello=" + value);
			memcachedClient.delete("hello");
			value = memcachedClient.get("hello");
			System.out.println("hello=" + value);
		} catch (MemcachedException e) {
			System.err.println("MemcachedClient operation fail");
			e.printStackTrace();
		} catch (TimeoutException e) {
			System.err.println("MemcachedClient operation timeout");
			e.printStackTrace();
		} catch (InterruptedException e) {
			// ignore
		}
		try {
			// close memcached client
			memcachedClient.shutdown();
		} catch (IOException e) {
			System.err.println("Shutdown MemcachedClient fail");
			e.printStackTrace();
		}

	}

}
