
package com.Sistempark.SenaSP.domian;

import javax.persistence.ManyToMany;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="permiso")
public class Permiso {
    
    @Id
    private String fecha_permiso;
    private String motivo_permiso;
    private String horario_permiso;
   
   /* Relacion  con la tabla Usuario */
    @ManyToMany(mappedBy = "permiso")
    private List<Empleado> empleado;
}