package service;

public class BrazilInterestService implements InterestService{

    private double taxaJuros;

    public BrazilInterestService(double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    @Override
    public double getTaxaJuros() {
        return taxaJuros;
    }

}
