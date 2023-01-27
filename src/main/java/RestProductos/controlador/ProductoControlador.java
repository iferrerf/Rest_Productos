package RestProductos.controlador;

import RestProductos.modelo.Producto;
import RestProductos.repositorio.ProductoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProductoControlador {


    // Inicializa un objeto automaticamente cuando lo necesita usar
    //@Autowired
    //ProductoRepositorio productoRepositorio;

    // Hace lo mismo que el autowired con @RequierArgsConstructor
    private final ProductoRepositorio productoRepositorio;

    /**
     * Obtener un producto en base a su ID
     *
     * @param id
     * @return Null si no se encuentra el producto
     */

    @GetMapping("/api/productos")
    public Producto obtenerUno(@PathVariable Long id) {
        return productoRepositorio.findById(id).orElse(null);
    }

    @PostMapping("/api/productos")
    public Producto insertarProducto(@RequestBody Producto producto) {
       return productoRepositorio.save(producto);
    }

    @PutMapping("api/producto/{id}")
    public Producto editarProducto(@RequestBody Producto editar, @PathVariable Long id) {
        if (productoRepositorio.existsById(id)) {
            editar.setId(id);
            return productoRepositorio.save(editar);
        }else return null;
    }

    @PutMapping("api/producto2/{id}")
    public Producto editarProducto2(@RequestBody Producto editar, @PathVariable Long id) {
        editar.setId(id);
        return productoRepositorio.findById(id)
                .map(producto -> {
                    productoRepositorio.save(editar);
                    return editar;
                }).orElse(null);
    }

    @DeleteMapping("/api/productos/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        productoRepositorio.findById(id).ifPresent(productoRepositorio::delete);
    }


    @DeleteMapping("/api/productos2/{id}")
    public Producto eliminarProducto2(@PathVariable Long id) {
        if (productoRepositorio.existsById(id)){
            Producto producto = productoRepositorio.findById(id).get();
            productoRepositorio.deleteById(id);
            return producto;
        }
        else return null;
    }


}
