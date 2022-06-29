/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Sistempark.SenaSP.domian;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="cliente")
public class Cliente {
    
    @Id
    private Integer id_cliente;
    private String tipo_cliente;
    
    /* Relacion  con la tabla Usuario */
    @ManyToOne
    @JoinColumn(name="idUsuario")
    private Usuario usuario;
    
    /* Relacion  con la tabla Servicio */
    
    /*@ManyToMany
    @JoinTable(name ="cliente_has_servicio",
       joinColumns ={@JoinColumn(name="Cliente_id_cliente")},
       inverseJoinColumns = {@JoinColumn(name = "servicio_cod_servicio")})
    private List<Servicio> servicio;
    */
 
}
