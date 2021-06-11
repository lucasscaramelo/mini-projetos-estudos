package model.entities;

import model.entities.exceptions.DomainException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reserva {

    private Integer numeroQuarto;
    private Date checkin;
    private Date checkout;

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // static para nao ser instanciado para cada objeto

    public Reserva(Integer numeroQuarto, Date checkin, Date checkout) throws DomainException {
        if (!checkout.after(checkin)) {
            throw new DomainException("Data Checkout deve ser maior que data de CheckIn");
        }

        this.numeroQuarto = numeroQuarto;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public Integer getNumeroQuarto() {
        return numeroQuarto;
    }

    public void setNumeroQuarto(Integer numeroQuarto) {
        this.numeroQuarto = numeroQuarto;
    }

    public Date getCheckin() {
        return checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public long duracao() {
        long dif = checkout.getTime() - checkin.getTime();
        return TimeUnit.DAYS.convert(dif, TimeUnit.MILLISECONDS); // converte o valor dif para dias
    }

    public void updateDatas(Date checkin, Date checkout) throws DomainException {
        Date agora = new Date();

        if (checkin.before(agora) || checkout.before(agora)) {
            throw new DomainException("Datas antes da data atual"); // essa excecao serve para argumentos invalidos
        }

        if (!checkout.after(checkin)) {
            throw new DomainException("Data Checkout deve ser maior que data de CheckIn");
        }

        this.checkin = checkin;
        this.checkout = checkout;
    }

    @Override
    public String toString() {
        return "Reserva" +
                "Numero Quarto: " + numeroQuarto +
                ", Checkin: " + sdf.format(checkin) +
                ", Checkout: " + sdf.format(checkout) +
                ", Duração: " + duracao() +
                "noites";
    }
}
