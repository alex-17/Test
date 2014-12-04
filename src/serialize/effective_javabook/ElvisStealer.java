package serialize.effective_javabook;

import java.io.*;


public class ElvisStealer implements Serializable {
    static Elvis impersonator;
    private Elvis payload;

    private Object readResolve() {
        System.out.println("--------ElvisStealer readResolve--------------");
        // Save a reference to the "unresolved" Elvis instance
        impersonator = payload;
        // Return an object of correct type for favorites field
        return new String[]{"A Fool Such as I"};
    }

    private static final long serialVersionUID = 0;

    public byte[] serialize() {
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}