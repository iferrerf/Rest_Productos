package RestProductos.dto.converter;

import RestProductos.dto.CreateProductoDTO;
import RestProductos.dto.ProductoDTO;
import RestProductos.modelo.Producto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // tiene que ser final!!
public class ProductoDTOConverter {
    // Como el @autowired
    private final ModelMapper modelMapper;

    public ProductoDTO convertToDTO(Producto producto){
        return modelMapper.map(producto, ProductoDTO.class);
    }


    public Producto convertDesdeProductoDTO(CreateProductoDTO createProductoDTO){
        return modelMapper.map(createProductoDTO, Producto.class);
    }

}
