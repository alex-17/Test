package thread;


public class Info {

    private String info;
    
    public Info(String info){
        this.info = info;
    }

    public String toString() {
        return info + "/" + super.toString();
    }
    
    
}
