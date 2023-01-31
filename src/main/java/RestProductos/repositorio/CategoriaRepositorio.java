package RestProductos.repositorio;

import RestProductos.modelo.Categoria;
import RestProductos.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {
}
