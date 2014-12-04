package google.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;

/**
 * Created by wangying on 2014/9/3.
 */
public class Cache2 {

    public static void main(String[] args) throws ExecutionException {

        LoadingCache<String,String> cache = CacheBuilder.newBuilder().maximumSize(100).build(
                new CacheLoader<String, String>() {
                    public String load(String key) {
                        return String.valueOf(Math.random());
                    }
                });

        System.out.println(cache.get("A"));


    }
}
