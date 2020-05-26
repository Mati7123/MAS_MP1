import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Vector;

public class ObjectPlus implements Serializable {
    private static final long serialVersionUID = 2L;

    private static Hashtable extents= new Hashtable<>();

    private static Vector extent;


    public ObjectPlus(){
        extent = null;
        Class aClass = this.getClass();

        if (extents.containsKey(aClass)){
            extent = (Vector) extents.get(aClass);
        }
        else {
            extent = new Vector();
            extents.put(aClass, extent);
        }
        extent.add(this);

    }

    public static void saveExtents( ObjectOutputStream stream) throws IOException{
        stream.writeObject(extents);
    }

    public static void readExtents (ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extents = (Hashtable) stream.readObject();
    }

    public static void showExtents(Class klasa) throws Exception {
        Vector extent = null;
        if (extents.containsKey(klasa)){
            extent = (Vector) extents.get(klasa);
        }
        else{
            throw new Exception ("Nienzna klasa " + klasa);
        }
        System.out.println("Ekstensja klasy: " + klasa.getSimpleName());
        for (Object o : extent){
            System.out.println(o + "\n");
        }
    }
    public static Vector getExtent(Class klasa) throws ClassNotFoundException {
        Vector extent = null;
        if (extents.containsKey(klasa)){
            extent = (Vector) extents.get(klasa);
        }
        else{
            throw new ClassNotFoundException ("Nienzna klasa " + klasa);
        }
        return extent;
    }
}

