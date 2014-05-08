package apache_commons_lang;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Apple {

    private String color;

    public Apple(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
            
        if (obj == null){
            return false;
        }
            
        if (getClass() != obj.getClass()){
            return false;
        }
        
            
        Apple other = (Apple) obj;
        if (color == null) {
            if (other.color != null){
                return false;
            }
                
        } else if (!color.equals(other.color))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        /*final int prime = 31;
        int result = 1;
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        return result;*/
        return new HashCodeBuilder().append(color).toHashCode();
    }
    
    public static void main(String[] args) {
        Apple a1 = new Apple("green");
        System.out.println("a1 green hashcode=" + a1.hashCode());
        a1.setColor("greenn");
        System.out.println("a1 greenn hashcode=" + a1.hashCode());
        Apple a2 = new Apple("red");

        // hashMap stores apple type and its quantity
        Map<Apple, Integer> m = new HashMap<Apple, Integer>();
        m.put(a1, 10);
        m.put(a2, 20);
        System.out.println(m.get(new Apple("green")));
    }

    
    public String getColor() {
        return color;
    }

    
    public void setColor(String color) {
        this.color = color;
    }
    
}
