package Refactor;

public abstract class Cuenta {
    private long numeroCuenta;
    private String titular;
    private long saldo;

    public Cuenta(long numeroCuenta, String titular) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = 0;
    }
    public void depositar (long monto) {
        this.saldo += monto;
    }
    public abstract void extraer (long monto) throws RuntimeException;

    public long getNumeroCuenta() {
        return this.numeroCuenta;
    }
    public String getTitular() {
        return this.titular;
    }
    public long getSaldo() {
        return this.saldo;
    }
    public void setNumeroCuenta(long numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
    public void setTitular(String titular) {
        this.titular = titular;
    }
    protected void setSaldo(long saldo) {
        this.saldo = saldo;
    }
}
