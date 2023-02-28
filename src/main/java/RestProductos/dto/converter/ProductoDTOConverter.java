package RestProductos.dto.converter;

import RestProductos.dto.CreateProductoDTO;
import RestProductos.dto.ProductoDTO;
import RestProductos.modelo.Producto;
import RestProductos.repositorio.CategoriaRepositorio;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // tiene que ser final!!
public class ProductoDTOConverter {
    // Como el @autowired
    @Autowired
    CategoriaRepositorio categoriaRepositorio;
    private final ModelMapper modelMapper;

    public ProductoDTO convertToDTO(Producto producto){
        return modelMapper.map(producto, ProductoDTO.class);
    }


    public Producto convertDesdeProductoDTO(CreateProductoDTO createProductoDTO){
        Producto producto = modelMapper.map(createProductoDTO, Producto.class);
        producto.setCategoria(categoriaRepositorio.findById(producto.getCategoria().getCodigo()).orElse(null));
        return producto;
    }

}
