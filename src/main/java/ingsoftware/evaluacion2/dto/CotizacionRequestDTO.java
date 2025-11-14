package ingsoftware.evaluacion2.dto;

import java.util.List;

public class CotizacionRequestDTO {
    
    private List<ItemCotizacionDTO> items;

    public CotizacionRequestDTO(List<ItemCotizacionDTO> items) {
        this.items = items;
    }

    public CotizacionRequestDTO(){}

    public List<ItemCotizacionDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemCotizacionDTO> items) {
        this.items = items;
    }
}