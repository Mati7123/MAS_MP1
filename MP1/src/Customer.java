


import java.time.LocalDate;
import java.time.Period;
import java.util.Vector;

public class Customer extends ObjectPlus {
    private static final long serialVersionUID = 2L;

    //Atrybuty proste, wymagane, pojedyncze, obiektu
    private String firstName;
    private String name;

    private boolean regularCustomer = false;

    //Atrybut złożony, wymagany, pojedynczy, obiektu

    private LocalDate birthDate;

    //Atrybut pochodny, prosty, wymagany, pojedynczy, obiektu
    private int diseasesNumber;

    //Atrybuty  proste, opcjonalne, pojedyncze, obiektu
    private Integer discount;
    private String parentName;

    //Atrybut prosty, powtarzalny, obiektu
    private Vector<String> medicalHistory = new Vector<>();

    //Atrybut prosty, pojedynczy, klasowy
    private static int minAgeMonth = 3;

    private double age;


    //Konstruktor
    Customer(String firstName, String name, LocalDate birthDate){
        super();
        if (firstName == null){throw new NullPointerException("Pole imie nie może być puste.");}
        this.firstName = firstName;
        if (name == null){throw new NullPointerException("Pole nazwisko nie może być puste.");}
        this.name = name;
        if (birthDate == null){throw new NullPointerException("Pole data urodzenia nie może być puste.");}
        this.birthDate = birthDate;
        if ( Period.between(birthDate, LocalDate.now()).getMonths() < minAgeMonth){
            throw new IllegalArgumentException("Klient musi miec skończone 3 miesiące");
        }
        age = ageCalc(birthDate);


    }

    //Metoda klasowa
    public static Vector<Customer> getUnderAge(int age ) {
        Vector<Customer> extent = new Vector<>();
        try {
            for (Object o : ObjectPlus.getExtent(Customer.class))
                if (((Customer) o).getAge() < age)
                    extent.add((Customer) o);
            return extent;
        } catch (ClassNotFoundException exception) {
            return null;
        }

    }

    //Przeciążenie metody
    public void giveRegularCustomer(){
        regularCustomer = true;;
    }

    public void giveRegularCustomer(int percent){
       discount = percent;
       regularCustomer = true;
    }
    //Metoda obiektu
    public void addDiseasesName(String diseasesName){
        medicalHistory.add(diseasesName);
    }

    // Przysłonięcie metody
    public String toString(){
        String join = "";
        join += "\nImie: " +firstName +
                "\nNazwisko: " + name +
                "\nData Urodzenia: " +  birthDate+
                "\nWiek: " + age+
                "\nStały klient: ";
        if (regularCustomer){
            join += "Tak";
        }
        else {
            join += "Nie";
        }
        join += "\nZnizka: ";
        if (discount!= null){
            join += discount;
        }
        else {
            join += "brak";
        }
        join += "\nPrawny opiekun: ";
        if (parentName!= null){
            join += parentName;
        }
        else{
            join += "brak";
        }
        join += "\nIlość chorób - " + getDiseasesNumber() + " Choroby :";
        if(medicalHistory.size() > 0)
        {
            join += medicalHistory + " ";
        }
        else
        {
            join += "Brak chorób";
        }
        join += minAgeMonth;
        return join;

    }

    private int ageCalc(LocalDate birthDate){
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }
    //Gettery i settery
    public double getAge() {
        return age;
    }

    public void setParentName(String guardianName) {
        this.parentName = guardianName;
    }

    public int getDiseasesNumber() {
        diseasesNumber = medicalHistory.size();
        return diseasesNumber;
    }

    public static void setMinAgeMonth(int minAgeMonth) {

        Customer.minAgeMonth = minAgeMonth;
    }
}
