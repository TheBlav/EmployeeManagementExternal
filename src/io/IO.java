package io;
import java.util.InputMismatchException;
import java.util.Scanner;
import data.Employee;

public class IO implements Printer{
   private String firstName, lastName;
    private double salary;
    Scanner sc = new Scanner(System.in);
    public Employee getEmployee(){
        boolean error = true;
        do {
            try {
                print("Podaj imię pracownika:");
                firstName = sc.nextLine();
                print("Podaj nazwisko pracownika: ");
                lastName = sc.nextLine();
                print("Podaj wysokość wypłaty pracownika: ");
                salary = sc.nextDouble();
                sc.nextLine();

            } catch (InputMismatchException e) {
                System.err.println("podano błędne dane, spróbuj ponownie. ");
                error = false;
                sc.nextLine();
            }
        } while (error);
        return new Employee(firstName, lastName, salary);
    }

    public int choice() {
        boolean error = false;
        int choice = 0;
        do {
            try {

                print("Wybierz działanie: ");
                print("Zapis danych pracowników - wybierz 1");
                print("Odczyt danych pracowników - wybierz 2");
                print("Wybieram: ");
                choice = sc.nextInt();
            } catch (InputMismatchException e){
                System.err.println("Podano błędną wartość, spróbuj ponownie: ");
                sc.nextLine();
                error = true;
            }
        } while (error);
        return choice;
    }
}
