package application;

import entities.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Employee> list = new ArrayList<>();

        System.out.print("How many employees will be registred? ");
        int n = sc.nextInt();

        for (int i=0; i<n; i++) {
            System.out.println("Employee #" + (i + 1) + ":");
            System.out.print("id: ");
            Integer id = sc.nextInt();

            while (hasId(list, id)) {
                System.out.println("Id already exists!");
            }

            System.out.print("name: ");
            sc.nextLine();
            String name = sc.nextLine();
            System.out.print("salary: ");
            Double salary = sc.nextDouble();

            Employee emp = new Employee(id, name, salary);
            list.add(emp);
        }

        System.out.println("Enter the employee id that will have salary increase: ");
        int idsalary = sc.nextInt();

       // Employee emp = list.stream().filter(x -> x.getId() == idsalary).findFirst().orElse(null);

        Integer position = findPositionId(list, idsalary);
        if (position == null) {
            System.out.println("This id does not exist!");
        } else {
            System.out.print("Enter the percentage: ");
            double percent = sc.nextDouble();
            list.get(position).increaseSalary(percent);
        }

        System.out.println("List of employees:");
        for (Employee emp : list){
            System.out.println(emp);
        }

        sc.close();
    }

    static Integer findPositionId(List<Employee> list, int id) {
        for (int i=0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                return i;
            }
        }
        return null;
    }

    static boolean hasId(List<Employee> list, int id) {
        Employee emp = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        return emp != null;
    }
}
