package service;

public class UsaInterestService implements InterestService{

    private double taxaJuros;

    public UsaInterestService(double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    @Override
    public double getTaxaJuros() {
        return taxaJuros;
    }

}
