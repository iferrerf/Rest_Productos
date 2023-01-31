package RestProductos.dto;

import lombok.Data;

@Data
public class CreateProductoDTO {

    private String nombre;
    private Double precio;
    private Long categoriaId;


}
