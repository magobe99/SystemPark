
package com.Sistempark.SenaSP.domian;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="roles")
public class Roles{
    
    @Id
    private Integer id_roles;
    private String nom_rol;
    
    /* Relacion  con la tabla Usuario */
    @ManyToMany(mappedBy = "roles")
    private List<Empleado> empleado;

    
}
