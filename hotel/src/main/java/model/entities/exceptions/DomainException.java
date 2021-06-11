package model.entities.exceptions;

public class DomainException extends Exception{

    private static final long serialVerisonUID = 1L;

    public DomainException(String msg) {
        super(msg);
    }
}
