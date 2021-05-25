package entities;

public class BusinessAccount extends Account { // classe especializada

    private Double loanLimit;

    public BusinessAccount() {
        super();
    }

    public BusinessAccount(Integer number, String holder, Double balance, Double loanLimit) {
        super(number, holder, balance); // super é o construtor da classe herdada || super serve para executar a logica do construtor da classe base
        this.loanLimit = loanLimit;
    }

    public Double getLoanLimit() {
        return loanLimit;
    }

    public void setLoanLimit(Double loanLimit) {
        this.loanLimit = loanLimit;
    }

    public void loan(double amount) {
        if (amount <= loanLimit) {
            balance += amount - 10.0;
        }
    }

    @Override
    public void withDraw(double amount) {
        super.withDraw(amount); // realiza saque com a regra da super classe
        balance -= 2.0; // aplica regra especifica da sobreposicao
    }
}
