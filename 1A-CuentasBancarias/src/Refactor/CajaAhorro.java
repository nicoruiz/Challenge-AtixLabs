package Refactor;

public class CajaAhorro extends Cuenta{

    public CajaAhorro(long numeroCuenta, String titular) {
        super(numeroCuenta, titular);
    }

    @Override
    public void extraer(long monto) throws RuntimeException {
        if (monto > super.getSaldo())
            throw new RuntimeException("No hay dinero suficiente");
        super.setSaldo(super.getSaldo() - monto);
    }
}
