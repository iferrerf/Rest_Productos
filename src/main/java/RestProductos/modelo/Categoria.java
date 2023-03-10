package RestProductos.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long codigo;
    String nombre;

}
