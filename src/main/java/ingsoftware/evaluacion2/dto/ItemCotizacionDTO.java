package ingsoftware.evaluacion2.dto;

public class ItemCotizacionDTO {
    
    private Long muebleId;
    private Long varianteId;
    private int cantidad;

    public ItemCotizacionDTO(Long muebleId, Long varianteId, int cantidad) {
        this.muebleId = muebleId;
        this.varianteId = varianteId;
        this.cantidad = cantidad;
    }

    public ItemCotizacionDTO(){}

    public Long getMuebleId() {
        return muebleId;
    }

    public void setMuebleId(Long muebleId) {
        this.muebleId = muebleId;
    }

    public Long getVarianteId() {
        return varianteId;
    }

    public void setVarianteId(Long varianteId) {
        this.varianteId = varianteId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}