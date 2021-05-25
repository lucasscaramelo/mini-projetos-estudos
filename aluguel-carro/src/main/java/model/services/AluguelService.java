package model.services;

import model.entities.AluguelCarro;
import model.entities.Fatura;

public class AluguelService {

    private Double precoDia;
    private Double precoHora;

    private ImpostoService imposto;

    public AluguelService(Double precoDia, Double precoHora, ImpostoService imposto) {
        this.precoDia = precoDia;
        this.precoHora = precoHora;
        this.imposto = imposto;
    }

    public void processaFatura(AluguelCarro aluguelCarro) {
        long t1 = aluguelCarro.getStart().getTime();
        long t2 = aluguelCarro.getFinish().getTime();
        double horas = (double) (t2 - t1) / 1000/ 60/ 60;
        double basicPay;

        if (horas <= 12.0) {
            basicPay = Math.ceil(horas) * precoHora;
        }else  {
            basicPay = Math.ceil(horas / 24) * precoDia;
        }

        double tax = imposto.imposto(basicPay);

        aluguelCarro.setFatura(new Fatura(basicPay, tax));
    }
}
