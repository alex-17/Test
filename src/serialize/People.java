package serialize;

import java.io.*;

public class People implements Serializable {

    private static final long serialVersionUID = -2862028108649283243L;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    private void writeObject(java.io.ObjectOutputStream out) {
        System.out.println("writeObject invoked");
        try {
            out.writeObject(this.message == null ? "hohohahaha" : this.message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readObject(java.io.ObjectInputStream in) {
        System.out.println("readObject invoked");
        try {
            this.message = (String) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("got message:" + message);
    }

    private Object writeReplace() {
        System.out.println("writeReplace invoked");
        return this;
    }

    private Object readResolve() {
        System.out.println("readResolve invoked");
        return this;
    }

    public Object serialize() {
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        Object rs = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            bais = new ByteArrayInputStream(baos.toByteArray());
            ois = new ObjectInputStream(bais);
            rs = ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return rs;
    }

    @Override
    public String toString() {
        return "--------People--------" + message;
    }

    public static void main(String[] args) {
        People people = new People();
        people.setMessage("过");
        Object o = people.serialize();
        System.out.println(o.getClass().getName());
    }

}
