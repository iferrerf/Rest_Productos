package RestProductos.controlador;

import RestProductos.modelo.Categoria;
import RestProductos.modelo.Producto;
import RestProductos.repositorio.CategoriaRepositorio;
import RestProductos.repositorio.ProductoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoriaControlador {


    // Inicializa un objeto automaticamente cuando lo necesita usar
    @Autowired
    //ProductoRepositorio productoRepositorio;

    // Hace lo mismo que el autowired con @RequierArgsConstructor
    private final CategoriaRepositorio categoriaRepositorio;


    /**
     * Obtener un producto en base a su ID
     *
     * @param id
     * @return Null si no se encuentra el producto
     */
/*    @GetMapping("/api/producto")
    public Producto obtenerUno(@PathVariable Long id) {
        return productoRepositorio.findById(id).orElse(null);
    }*/


    /**
     * Obtener un producto en base a su ID
     *
     * @param id
     * @return 404 si no encuentra el producto y
     */
    @GetMapping("/api/categoria/{id}")
    public ResponseEntity<?> obtenerUno2(@PathVariable Long id) {
        Categoria result = categoriaRepositorio.findById(id).orElse(null);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }


    /**
     * Obtener todos los productos
     *
     * @return 404 si no hay productos, 200 y la lista de productos si hay uno o más
     */
    @GetMapping("/api/categoria")
    public ResponseEntity<?> obtenerTodos() {
        List<Categoria> result = categoriaRepositorio.findAll();
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }


    @PostMapping("/api/categoria")
    public ResponseEntity<?> insertarProducto2(@RequestBody Categoria categoria) {
        Categoria salvado = categoriaRepositorio.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvado);
    }

/*    @PostMapping("/api/productos")
    public Producto insertarProducto(@RequestBody Producto producto) {
        return productoRepositorio.save(producto);
    }*/

    /**
     * @param editar
     * @return 200 ok si la edicion tiene exito, 404 si no se encuentra el producto a editar
     */
    @PutMapping("api/categoria/{id}")
    public ResponseEntity<?> editarProducto3(@RequestBody Categoria editar, @PathVariable Long id) {
        return categoriaRepositorio.findById(id).map(categoria -> {
            categoria.setNombre(editar.getNombre());
            return ResponseEntity.ok(categoriaRepositorio.save(categoria));
        }).orElseGet(() -> {
            return ResponseEntity.notFound().build();
        });

/*
        if (categoriaRepositorio.existsById(id)) {
            editar.setId(id);
            Categoria actualziado = categoriaRepositorio.save(editar);
            return ResponseEntity.ok(actualziado);
        }else return ResponseEntity.notFound().build();
*/

    }



/*    @PutMapping("api/categoria/{id}")
    public Producto editarProducto(@RequestBody Producto editar, @PathVariable Long id) {
        if (productoRepositorio.existsById(id)) {
            editar.setId(id);
            return productoRepositorio.save(editar);
        } else return null;
    }*/


    @PutMapping("api/categoria2/{id}")
    public Categoria editarProducto2(@RequestBody Categoria editar, @PathVariable Long id) {
        editar.setId(id);
        return categoriaRepositorio.findById(id)
                .map(producto -> {
                    categoriaRepositorio.save(editar);
                    return editar;
                }).orElse(null);
    }

    /**
     * @param id
     * @return 204 cuando consigue borrar el producto
     */

    @DeleteMapping("/api/categoria/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
        categoriaRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }

/*    @DeleteMapping("/api/categoria/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        productoRepositorio.findById(id).ifPresent(productoRepositorio::delete);
    }*/



/*
    @DeleteMapping("/api/categoria/{id}")
    public Producto eliminarProducto2(@PathVariable Long id) {
        if (productoRepositorio.existsById(id)) {
            Producto producto = productoRepositorio.findById(id).get();
            productoRepositorio.deleteById(id);
            return producto;
        } else return null;
    }
*/


}
