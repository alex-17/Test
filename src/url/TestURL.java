package url;


public class TestURL {
    
    public static final String URL_FLAG = "/";
    
    public static String getHost(String uri){
        String contextPath = "forest";
        String host = "www.root.com";
        //确定是否存在网址分隔符（反斜杠）
        boolean hostFind = host.endsWith( URL_FLAG );
        boolean urlFind = uri.startsWith( URL_FLAG );
        
        if( hostFind && urlFind ){
            return host + uri.substring(1,uri.length());
        }
        
        if( !hostFind && !urlFind ){
            return host + URL_FLAG + uri;
        }
        
        return host + uri;
    }
    
    public static void main(String[] args) {
        
        
    }

}
