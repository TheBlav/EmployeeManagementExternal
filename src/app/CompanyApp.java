package app;
import data.Employee;
import io.Printer;

import javax.print.DocFlavor;
import java.io.*;
import java.util.Scanner;

public class CompanyApp implements Printer {
    private static final String fileName = "Employees.info";
    private static final int READ_FROM_USER = 1;
    private static final int READ_FROM_FILE = 2;

    static Scanner sc = new Scanner(System.in);

    public void CompanyApp (){
        print("Wprowarzanie danych pracowników: " + READ_FROM_USER);
        print("Wczytaj i wyświetl dane z pliku: " + READ_FROM_FILE);
        int option = sc.nextInt();
        sc.nextLine();

        if (option == READ_FROM_USER){
            Company company = createCompany();
            WriteFile(company);
            print(company.toString());
        }
        else if (option == READ_FROM_FILE) {
            Company company = null;
            try {
                company = readFile();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("błąd odczytu danych");
                e.printStackTrace();
            }
            print(company.toString());
        }
        sc.close();
    }

    private Company readFile() throws IOException, ClassNotFoundException {
        try(
                var fis = new FileInputStream(fileName);
                var ois = new ObjectInputStream(fis);
                ){
            return (Company) ois.readObject();
        }
        }


    private void WriteFile(Company company) {
        try(
                var fis = new FileOutputStream(fileName);
                var oos = new ObjectOutputStream(fis);
                ){
            oos.writeObject(company);
            print("Zapisano dane do pliku");
        } catch (IOException e){
            System.err.println("Błąd zapisu danych");
        }
    }

    private static Company createCompany(){
        Company company = new Company();
        for (int i=0; i<company.MAX_EMPLOYEES; i++){
            System.out.print("Wprowadź imię: ");
            String firstName = sc.nextLine();
            System.out.print("Wprowadź nazwisko: ");
            String lastName = sc.nextLine();
            System.out.print("Wprowadź wypłątę: ");
            double salary = sc.nextDouble();
            sc.nextLine();
            company.add(new Employee(firstName, lastName, salary));
        }
            return company;
    }
}
