package app;

import java.io.*;

import app.Company;
import data.Employee;
import data.Person;
import io.IO;
import io.Printer;

public class CompanyApp implements Serializable, Printer {
    String fileName = "Employees.obj";
    Employee person = null;

    public void CompanyApp() {
        Company company = new Company();
        File file = new File(fileName);
        IO inOut = new IO();

        boolean fileExists = file.exists();
        if (!fileExists) {
            try {
                fileExists = file.createNewFile();
            } catch (IOException e) {
                System.err.println("nie udało się utworzyć pliku");
            }
        }
        if (fileExists) {
            int currentChoice = inOut.choice();
            if (currentChoice == 1) {      // zapis plików
                for (int i = 0; i < company.employees.length; i++) {
                    company.addEmployeeManually();
                }
                try (
                        var fs = new FileOutputStream(fileName);
                        var os = new ObjectOutputStream(fs);
                ) {
                    for (Person employee : company.employees) {
                        os.writeObject(employee);
                    }
                } catch (FileNotFoundException e) {
                   System.err.println("nie znaleziono pliku");
                } catch (IOException e) {
                    System.err.println("Bład strumienia danych");
                    e.printStackTrace();
                }
            }  // koniec zapisu plików

            if (currentChoice == 2) {        // odczyt z pliku
                boolean endOfFile = false;
                do {
                    try (
                            var fis = new FileInputStream(fileName);
                            var ois = new ObjectInputStream(fis);
                    ) {
                        person = (Employee) ois.readObject();
                        company.addEmployeeFromFile(person);
                        if (ois.readObject()==null){
                            endOfFile = true;
                        }

                    } catch (IOException | ClassNotFoundException e) {
                        System.err.println("Bład strumienia danych");
                        e.printStackTrace();
                    }
                    if (person != null) {
                        print("Wczytano dane o: ");
                        print(person.toString());
                    }
                }    while (!endOfFile);     // koniec odczytu pliku
            }
        }
    }
}
