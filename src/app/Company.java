package app;
import data.Employee;
import io.Printer;

import java.io.Serializable;

public class Company implements Printer, Serializable {
   public static final int MAX_EMPLOYEES = 3;
   private Employee[] employees = new Employee[MAX_EMPLOYEES];
   private int currentEmployee = 0;


   public void add(Employee employee) {
       employees[currentEmployee] = employee;
       currentEmployee++;
   }



    @Override
    public String toString() {
       StringBuilder builder = new StringBuilder();
        for (Employee employee : employees) {
            builder.append(employee.toString());
            builder.append("\n");
        }
        return builder.toString();
    }
}
