
package com.Sistempark.SenaSP.domian;

import java.util.List;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="empleado")
public class Empleado {
    
    
    @Id
    private Integer id_empleado;
    private String cargo;
    
   /* Relacion  con la tabla Usuario */
    @ManyToOne
    @JoinColumn(name="idUsuario")
    private Usuario usuario;
    
    
     /* Relacion  con la tabla Permisos */
    @ManyToMany
    @JoinTable(name="empleado_has_permiso",
            joinColumns = {@JoinColumn(name="empleado_id_empleado")},
            inverseJoinColumns = {@JoinColumn(name="permiso_fecha_permiso") })
     private List<Permiso> permiso;
    
    
      /* Relacion  con la tabla Roles */
    @ManyToMany
    @JoinTable(name="empleado_has_roles",
            joinColumns = {@JoinColumn(name="empleado_id_empleado")},
            inverseJoinColumns = {@JoinColumn(name="roles_id_roles") })
     private List<Roles> roles;
    
     /* Relacion  con la tabla Reservaciones atraciones */
    @ManyToMany
    @JoinTable(name="empleado_has_reservacionesatraciones",
            joinColumns = {@JoinColumn(name="empleado_id_empleado")},
            inverseJoinColumns = {@JoinColumn(name="reservacionesatraciones_codigoreservacion") })
     private List<Reservacionesatraciones> reservacionesatraciones;
    
     /* Relacion  con la tabla Reservacion eseventos */
    @ManyToMany
    @JoinTable(name="empleado_has_reservacioneseventos",
            joinColumns = {@JoinColumn(name="empleado_id_empleado")},
            inverseJoinColumns = {@JoinColumn(name="reservacioneseventos_codigoreservacion") })
     private List<Reservacioneseventos> reservacioneseventos;
    
    
    
}
