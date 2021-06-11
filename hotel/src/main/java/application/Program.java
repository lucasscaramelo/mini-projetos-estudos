package application;

import model.entities.Reserva;
import model.entities.exceptions.DomainException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            System.out.print("Numero Quarto: ");
            int num = sc.nextInt();
            System.out.print("Data CheckIn (dd/MM/yyyy): ");
            Date checkin = sdf.parse(sc.next());
            System.out.print("Data CheckOut (dd/MM/yyyy): ");
            Date checkout = sdf.parse(sc.next());

            Reserva reserva = new Reserva(num, checkin, checkout);
            System.out.println("Dados: " + reserva);

        } catch (ParseException e) {
            System.out.println("Data inválida!" + e.getStackTrace());
        } catch (DomainException e) {
            System.out.println(e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Erro inesperado");
        }

        sc.close();
    }
}
