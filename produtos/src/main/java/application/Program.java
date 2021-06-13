package application;

import entities.Product;

import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite quantos produtos vc quer cadastrar: ");
        int n = sc.nextInt(); // usuario digita um inteiro

        Product[] vect = new Product[n]; // criando vetor com objeto Product

        for (int i=0; i< vect.length; i++) {
            sc.nextLine(); // por conta da quebra de linha do nextInt do inicio
            String name = sc.nextLine();
            double price = sc.nextDouble();
            vect[i] = new Product(name, price); // vetor aponta pro objeto
        }

        double sum = 0.0;
        for (int i=0; i< vect.length; i++) {
            sum += vect[i].getPrice();
        }

        System.out.printf("Valor total: %.2f%n", sum);

        sc.close();
    }
}
