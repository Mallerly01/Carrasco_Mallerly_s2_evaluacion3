package ingsoftware.evaluacion2.Modelo;

public abstract class VarianteDecorator implements Calculable {

    protected Calculable componenteDecorado;

    public VarianteDecorator(Calculable componenteDecorado) {
        this.componenteDecorado = componenteDecorado;
    }

    @Override
    public double calcularPrecio() {
        return componenteDecorado.calcularPrecio();
    }

    @Override
    public String getDescripcion() {
        return componenteDecorado.getDescripcion();
    }
}