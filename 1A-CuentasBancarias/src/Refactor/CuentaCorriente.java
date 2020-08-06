package Refactor;

public class CuentaCorriente extends Cuenta{
    private long descubiertoAcordado;

    public CuentaCorriente(long nCuenta, String titular, long descAcordado) {
        super(nCuenta, titular);
        this.descubiertoAcordado = descAcordado;
    }

    @Override
    public void extraer(long monto) throws RuntimeException {
        if (monto > super.getSaldo() + descubiertoAcordado)
            throw new RuntimeException("No hay dinero suficiente");
        super.setSaldo(this.getSaldo() - monto);
    }

    public long getDescubiertoAcordado() {
        return this.descubiertoAcordado;
    }
    public void setDescubiertoAcordado(long descubiertoAcordado) {
        this.descubiertoAcordado = descubiertoAcordado;
    }
}
