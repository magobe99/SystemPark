/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Sistempark.SenaSP.domian;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Data
@Table(name="usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idusuario;
    private String nom_usuario;
    private String clave;
    private String correo;
    private String genero;
    private String direccion;
    private String fecha_nacimiento;
    private String telefono;
    
    /* Relacion  con la tabla accesos */
    @ManyToMany
    @JoinTable(name="accesos_usuario",
            joinColumns = {@JoinColumn(name="idusuario")},
            inverseJoinColumns = {@JoinColumn(name="id_accesos") })
     private List<Accesos> roles;
    
     /* Relacion con la tabla Cliente */
    @OneToMany
    (mappedBy = "usuario")
    private List<Cliente> cliente;
    
    /* Relacion con la tabla Empleado */
    @OneToMany
    (mappedBy = "usuario")
    private List<Empleado> empleado;
    
   
    
} 
