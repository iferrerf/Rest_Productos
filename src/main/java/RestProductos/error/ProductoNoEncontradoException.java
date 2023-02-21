package RestProductos.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductoNoEncontradoException extends  RuntimeException{

    private static final long serialVersionUID = 1L;

    public ProductoNoEncontradoException(Long id){
        super("No se puede encontrar el producto con la id: " + id);
    }

}
