package application;

import entities.Account;
import entities.BusinessAccount;
import entities.SavingsAccount;

public class Program {

    public static void main(String[] args) {

        Account acc = new Account(1001, "Lucas", 0.0);
        BusinessAccount bacc = new BusinessAccount(1002, "Evelyn", 0.0, 500.0);

        // UPCASTING -- atribui um objeto da subclasse para super classe
        Account acc1 = bacc;  // não da erro pq a business account é uma account
        Account acc2 = new BusinessAccount(1003, "Bob", 0.0, 200.0); // atribuindo objetivo da subclasse para super classe
        Account acc3 = new SavingsAccount(1004, "Ana", 0.0, 0.01);

        // DOWNCASTING -- atribui um objeto da super classe para subclasse (inverso da anterior)
        BusinessAccount acc4 = (BusinessAccount) acc2;
        acc4.loan(100);


        // BusinessAccount acc5 = (BusinessAccount) acc3;  --- vai gerar erro de execucao pq acc3 nao e instancia de BusinessAccount
        // para testar, faça:
        if (acc3 instanceof BusinessAccount) {  // verificamos se acc3 é instancia de BusinessAccount
            BusinessAccount acc5 = (BusinessAccount) acc3;
            acc5.loan(200.0);
            System.out.println("Loan!");
        }

        // sem sobreposicao do metodo withDraw
        Account acc6 = new Account(1005, "Teste", 1000.0);
        acc6.withDraw(200.0);
        System.out.println(acc6.getBalance());

        // com sobreposicao do metodo withDraw
        Account acc7 = new SavingsAccount(1006, "Testando", 1000.0, 0.01);
        acc7.withDraw(200.0);
        System.out.println(acc7.getBalance());

        // com sobreposicao do metodo withDraw e uso da regra da super classe
        Account acc8 = new BusinessAccount(1006, "Testando", 1000.0, 200.0);
        acc8.withDraw(200.0);
        System.out.println(acc8.getBalance());
    }
}
