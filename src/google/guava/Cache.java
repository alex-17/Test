package google.guava;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalListeners;
import com.google.common.cache.RemovalNotification;


public class Cache {

    public class cacheThread extends Thread{
        private LoadingCache<String,AtomicInteger> cache2;
        public cacheThread(LoadingCache<String,AtomicInteger> cache2){
            this.cache2 = cache2;
        }
        
        @Override
        public void run() {
            for(int j = 0 ; j < 5 ; j++){
                
                for(int i = 0 ; i < 10 ; i++){
                    try {
                        System.out.println("thread get:" + cache2.get(String.valueOf(i)));
                        
                        Thread.sleep(3000);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
                
            }
        
    }
    
    public static void main(String[] args) throws ExecutionException, InterruptedException {
       
    /*    LoadingCache<String,String> payloadCache = CacheBuilder.newBuilder().maximumSize(50).expireAfterAccess(10, TimeUnit.SECONDS).build(
                new CacheLoader<String, String>() {
                    public String load(String message) {
                        return "大哥大" + Math.random();
                    }
                });
        
        System.out.println("1/" + payloadCache.get("A"));
        Thread.sleep(3000);
        System.out.println("2/" + payloadCache.get("A"));
        Thread.sleep(15000);
        System.out.println("3/" + payloadCache.get("A"));
        */
        
        
        LoadingCache<String,AtomicInteger> cache2 = CacheBuilder.newBuilder().maximumSize(3).removalListener(RemovalListeners.asynchronous(new RemovalListener<String, AtomicInteger>() {

            @Override
            public void onRemoval(
                    RemovalNotification<String, AtomicInteger> node) {
                
                System.out.println("key=" + node.getKey() + ",value=" + node.getValue());
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
            }}, Executors.newFixedThreadPool(1))).build(
                new CacheLoader<String, AtomicInteger>() {
                    @Override
                    public AtomicInteger load(String appId) throws Exception {
                        return new AtomicInteger(Integer.valueOf(appId) + 1000);
                    }
      });
        
        Cache c = new Cache();
        
        Cache.cacheThread ct = c.new cacheThread(cache2);
        ct.start();
        
        for(int i = 0 ; i < 10 ; i++){
            System.out.println("get:" + cache2.get(String.valueOf(i)));
            Thread.sleep(2000);
        }
        System.out.println("size:" + cache2.size());
        
        Thread.sleep(Integer.MAX_VALUE);
    }
    
    

}
