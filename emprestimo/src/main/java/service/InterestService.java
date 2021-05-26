package service;

import java.security.InvalidParameterException;

public interface InterestService {

    double getTaxaJuros();

    default double pagamento(double valor, double mes) {
        if (mes < 1) {
            throw new InvalidParameterException("Mes não pode ser menor do que 1");
        } else {
            return valor * Math.pow(1.0 + getTaxaJuros() / 100.0, mes);
        }
    }

}
