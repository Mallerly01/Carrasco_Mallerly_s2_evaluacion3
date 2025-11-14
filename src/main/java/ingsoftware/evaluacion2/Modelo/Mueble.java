package ingsoftware.evaluacion2.Modelo;
import jakarta.persistence.*;


@Entity
@Table(name = "muebles")
public class Mueble implements Calculable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_mueble;

    @Column(nullable = false)
    private String nombre_mueble;

    private String tipo;

    @Column(nullable = false)
    private double precio_base;

    @Column(nullable = false)
    private int stock;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoMueble estado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TamañoMueble tamaño; 

    private String material;

    public Mueble(Long ID_mueble, String nombre_mueble, String tipo, double precio_base,
                  int stock, EstadoMueble estado, TamañoMueble tamaño, String material) {
        this.ID_mueble = ID_mueble;
        this.nombre_mueble = nombre_mueble;
        this.tipo = tipo;
        this.precio_base = precio_base;
        this.stock = stock;
        this.estado = estado;
        this.tamaño = tamaño;
        this.material = material;
    }
    public Mueble(){}

    @Override
    public double calcularPrecio() {
        return this.precio_base; 
    }
    
    @Override
    public String getDescripcion() {
        return this.nombre_mueble;
    }

    public Long getIdMueble() {
        return ID_mueble;
    }

    public void setIdMueble(Long ID_mueble) {
        this.ID_mueble = ID_mueble;
    }

    public String getNombreMueble() {
        return nombre_mueble;
    }

    public void setNombreMueble(String nombre_mueble) {
        this.nombre_mueble = nombre_mueble;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecioBase() {
        return precio_base;
    }

    public void setPrecioBase(double precio_base) {
        this.precio_base = precio_base;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public EstadoMueble getEstado() {
        return estado;
    }

    public void setEstado(EstadoMueble estado) {
        this.estado = estado;
    }

    public TamañoMueble getTamaño() {
        return tamaño;
    }

    public void setTamaño(TamañoMueble tamaño) {
        this.tamaño = tamaño;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}


