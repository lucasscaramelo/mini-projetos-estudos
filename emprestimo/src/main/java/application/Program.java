package application;

import service.BrazilInterestService;
import service.InterestService;

import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Valor: ");
        double vlr = sc.nextDouble();

        System.out.print("Mes: ");
        int mes = sc.nextInt();

        InterestService is = new BrazilInterestService(2.0);
        double pagamento = is.pagamento(vlr, mes);

        System.out.println("Pagamento depois " + mes + "meses");
        System.out.println(String.format("%.2f" , pagamento));


        sc.close();
    }
}
