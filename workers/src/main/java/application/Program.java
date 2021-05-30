package application;

import entities.Departament;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


        System.out.print("Enter department's name: ");
        String department = sc.nextLine();

        System.out.print("----- Enter worker data -----");
        System.out.print("Name: ");
        String workerName = sc.nextLine();

        System.out.print("Level: ");
        String workerLevel = sc.nextLine();

        System.out.print("Base Salary: ");
        double workerSalary = sc.nextDouble();

        Worker worker = new Worker(workerName, WorkerLevel.valueOf(workerLevel), workerSalary, new Departament(department));

        System.out.print("How many contracts to this worker? ");
        int n = sc.nextInt();

        for (int i=1; i<n; i++) {
            System.out.println("--- Enter contract #" + i + "data ---");
            System.out.print("Date (DD/MM/YYYY): ");
            Date contractDate = sdf.parse(sc.next());

            System.out.print("Value per hour: ");
            double value = sc.nextDouble();

            System.out.print("Duration (hours): ");
            int hours = sc.nextInt();

            HourContract contract = new HourContract(contractDate, value, hours);
            worker.addContract(contract);
        }

        System.out.println("Enter month and year to calculate income (MM/YYYY): ");
        String monthAndYear = sc.next();
        int m = Integer.parseInt(monthAndYear.substring(0, 2));
        int y = Integer.parseInt(monthAndYear.substring(3));

        System.out.println("Name: " + worker.getName());
        System.out.println("Department: " + worker.getDepartament().getName());
        System.out.println("Income for " + monthAndYear + ": " + String.format("%.2f", worker.income(y,m)));

        sc.close();
    }
}
