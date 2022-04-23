package app;
import data.Employee;
import data.Person;
import io.IO;
import io.Printer;

public class Company implements Printer {
   private final int MAX_EMPLOYEES = 3;
   Person[] employees = new Employee[MAX_EMPLOYEES];
   private int curretEmployee = 0;
   IO inOut = new IO();
   public void addEmployeeManually(){
        if (curretEmployee<MAX_EMPLOYEES) {
            employees[curretEmployee] = inOut.getEmployee();
            curretEmployee++;
        }
        else
            print("Osiąnięto maksymalną liczbę pracowników: "+MAX_EMPLOYEES);

   }

   public void addEmployeeFromFile(Employee employee){
       if (curretEmployee<MAX_EMPLOYEES) {
           employees[curretEmployee] = employee;
           curretEmployee++;
       }
       else
           print("Osiąnięto maksymalną liczbę pracowników: "+MAX_EMPLOYEES);
   }

    public void printEmployees () {
        for (Person employee : employees) {
            print(employee.toString());

        }


    }

}
