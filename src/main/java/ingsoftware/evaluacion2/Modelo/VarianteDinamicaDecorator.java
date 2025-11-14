package ingsoftware.evaluacion2.Modelo;

public class VarianteDinamicaDecorator extends VarianteDecorator {

    private final Variante varianteDB;

    public VarianteDinamicaDecorator(Calculable componenteDecorado, Variante varianteDB) {
        super(componenteDecorado);
        this.varianteDB = varianteDB;
    }

    @Override
    public double calcularPrecio() {
        // al precio del mueble le suma el aumento de la variante
        return super.calcularPrecio() + varianteDB.getAumentoPrecio();
    }

    @Override
    public String getDescripcion() {
        // Llama a la descripción y le añade el nombre
        return super.getDescripcion() + ", " + varianteDB.getNombre();
    }
}