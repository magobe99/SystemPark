/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Sistempark.SenaSP.domian;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="accesos")
public class Accesos {
    
    @Id
    private Integer id_accesos;
    private String nomAccesos;
    
    /* Relacion  con la tabla Usuario */
    @ManyToMany(mappedBy = "roles")
    private List<Usuario> usuarios;

    
}
