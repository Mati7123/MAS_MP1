
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws Exception {
        if(new File("data").isFile())
        {
            try
            {
                FileInputStream fileInput = new FileInputStream("data");
                ObjectInputStream streamInput = new ObjectInputStream(fileInput);
                ObjectPlus.readExtents(streamInput);
                streamInput.close();
                fileInput.close();
            }
            catch(IOException i){
                i.printStackTrace();
                return;
            }
            catch(ClassNotFoundException c){
                System.out.println("Nie znaleziono klasy.");
                c.printStackTrace();
                return;
            }
        }


        LocalDate date = LocalDate.of(2000,1,11);
        Customer customer = new Customer("Andrzej", "Flak", date);
        customer.setParentName("Mariusz Flak");

        //customer.giveRegularCustomer();

        customer.giveRegularCustomer(20);
        customer.addDiseasesName("Skolioza");
        customer.addDiseasesName("Koronawirus");

        ObjectPlus.showExtents((Customer.class));

        System.out.println(Customer.getUnderAge(5));


        try
        {
            FileOutputStream fileOutput = new FileOutputStream("data");
            ObjectOutputStream StreamOutput = new ObjectOutputStream(fileOutput);
            ObjectPlus.saveExtents(StreamOutput);
            StreamOutput.close();
            fileOutput.close();
        }
        catch(IOException i)
        {
            i.printStackTrace();
        }

    }
}
