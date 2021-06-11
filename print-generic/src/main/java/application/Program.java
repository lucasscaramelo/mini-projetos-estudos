package application;

import services.PrintService;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        PrintService<Integer> ps = new PrintService<>();

        System.out.println("Quantos valores? ");
        int valor = sc.nextInt();

        for (int i=0; i < valor; i++) {
            int n = sc.nextInt();
            ps.addValue(n);
        }

        ps.print();

        sc.close();
    }
}
