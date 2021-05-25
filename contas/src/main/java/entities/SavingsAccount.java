package entities;

public final class SavingsAccount extends Account{ // com o final significa que a classe nao pode ser herdada

    private Double interestRate;

    public SavingsAccount() {
        super();
    }

    public SavingsAccount(Integer number, String holder, Double balance, Double interestRate) {
        super(number, holder, balance);
        this.interestRate = interestRate;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public void updateBalance() {
        balance += balance * interestRate;
    }

    @Override // sobreposicao de metodo
    public final void withDraw(double amount) { // com a palavra final o metodo nao pode ser sobreposto
        balance -= amount;
    }
}
