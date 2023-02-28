package RestProductos.controlador;

import RestProductos.dto.CreateProductoDTO;
import RestProductos.dto.ProductoDTO;
import RestProductos.dto.converter.ProductoDTOConverter;
import RestProductos.error.ProductoNoEncontradoException;
import RestProductos.modelo.Categoria;
import RestProductos.modelo.Producto;
import RestProductos.repositorio.CategoriaRepositorio;
import RestProductos.repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
//@RequiredArgsConstructor
public class ProductoControlador {


    // Inicializa un objeto automaticamente cuando lo necesita usar
    @Autowired
    ProductoRepositorio productoRepositorio;
    @Autowired
    ProductoDTOConverter productoDTOConverter;

    @Autowired
    CategoriaRepositorio categoriaRepositorio;

    // Hace lo mismo que el autowired con @RequiredArgsConstructor
    //private final ProductoRepositorio productoRepositorio;
    //private final ProductoDTOConverter productoDTOConverter;

    public ProductoControlador() {
    }


    /**
     * Obtener un producto en base a su ID
     *
     * @param id
     * @return Null si no se encuentra el producto
     */
    /*@GetMapping("/api/producto/{id}")
    public Producto obtenerUno(@PathVariable Long id) {
        return productoRepositorio.findById(id).orElse(null);
    }/*



    /**
     * Obtener un producto en base a su ID
     *
     * @param id
     * @return 404 con excepcion de ProductoNoEncontrado que hemos creado si no encuentra productos, 200 si devuelve un producto
     */
    @GetMapping("/api/producto/{id}")
    public Producto obtenerUnoById(@PathVariable Long id) {
/*      Producto result = productoRepositorio.findById(id).orElse(null);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);*/

        return productoRepositorio.findById(id).orElseThrow(() -> new ProductoNoEncontradoException(id));
    }


    /**
     * Obtener todos los productos
     *
     * @return 404 si no hay productos, 200 y la lista de productos si hay uno o más
     */
    @GetMapping("/api/productos")
    public ResponseEntity<?> obtenerTodos() {
        List<Producto> result = productoRepositorio.findAll();
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }


    @PostMapping("/api/productos")
    public ResponseEntity<?> insertarProducto(@RequestBody Producto producto) {
        Producto salvado = productoRepositorio.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvado);
    }

/*  @PostMapping("/api/productos")
    public Producto insertarProducto(@RequestBody Producto producto) {
        return productoRepositorio.save(producto);
    }*/

    /**
     * @param editar
     * @return 200 ok si la edicion tiene exito, 404 si no se encuentra el producto a editar
     */
    @PutMapping("api/producto/{id}")
    public ResponseEntity<?> editarProducto(@RequestBody Producto editar, @PathVariable Long id) {
        /*return productoRepositorio.findById(id).map(producto -> {
            producto.setNombre(editar.getNombre());
            producto.setPrecio(editar.getPrecio());
            return ResponseEntity.ok(productoRepositorio.save(producto));
        }).orElseGet(() -> {
            return ResponseEntity.notFound().build();
        });*/

        if (productoRepositorio.existsById(id)) {
            editar.setId(id);
            Producto actualziado = productoRepositorio.save(editar);
            return ResponseEntity.ok(actualziado);
        } else return ResponseEntity.notFound().build();

    }



/*    @PutMapping("api/producto/{id}")
    public Producto editarProducto(@RequestBody Producto editar, @PathVariable Long id) {
        if (productoRepositorio.existsById(id)) {
            editar.setId(id);
            return productoRepositorio.save(editar);
        } else return null;
    }*/


/*    @PutMapping("api/producto/{id}")
    public Producto editarProducto(@RequestBody Producto editar, @PathVariable Long id) {
        editar.setId(id);
        return productoRepositorio.findById(id)
                .map(producto -> {
                    productoRepositorio.save(editar);
                    return editar;
                }).orElse(null);
    }

    /**
     * @param id
     * @return 204 cuando consigue borrar el producto
     */

    @DeleteMapping("/api/producto/{id}")
    public ResponseEntity<?> eliminarProductoPorId(@PathVariable Long id) {
        if (productoRepositorio.existsById(id)) {
            productoRepositorio.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/producto3/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        productoRepositorio.findById(id).ifPresent(productoRepositorio::delete);
    }


    @DeleteMapping("/api/producto2/{id}")
    public Producto eliminarProducto2(@PathVariable Long id) {
        if (productoRepositorio.existsById(id)) {
            Producto producto = productoRepositorio.findById(id).get();
            productoRepositorio.deleteById(id);
            return producto;
        } else return null;
    }


    // DTO nuevo a partir de un producto
    @CrossOrigin(origins = "http://localhost:9998")
    @GetMapping("/api/productoDTO")
    public ResponseEntity<?> obtenerTodosConDTO() {
        /*List<Producto> result = productoRepositorio.findAll();
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<ProductoDTO> listaDTO = result.stream().map(
                    productoDTOConverter::convertToDTO).collect(Collectors.toList());
            return ResponseEntity.ok(listaDTO);
        }*/
        return productoRepositorio.findAll().isEmpty() ?  ResponseEntity.notFound().build() :
                ResponseEntity.ok(productoRepositorio.findAll().stream().map(productoDTOConverter::convertToDTO));

    }

    // Nuevo producto a traves de un DTO
    @CrossOrigin(origins = "http://localhost:9998")
    @PostMapping("/api/productoDTO")
    public ResponseEntity<?> nuevoProductoDTO(@RequestBody CreateProductoDTO nuevo) {
        // A fuego
/*
        Producto productoNuevo = new Producto();
        productoNuevo.setNombre(nuevo.getNombre());
        productoNuevo.setPrecio(nuevo.getPrecio());
        Categoria categoria = categoriaRepositorio.findById(nuevo.getCategoriaId()).orElse(null);
        productoNuevo.setCategoria(categoria);
*/

        // Con DTO converter
        Producto productoNuevo = productoDTOConverter.convertDesdeProductoDTO(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoRepositorio.save(productoNuevo));
    }


}
