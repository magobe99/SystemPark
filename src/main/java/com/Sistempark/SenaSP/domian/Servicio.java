/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Sistempark.SenaSP.domian;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name="servicio")
public class Servicio {
   
    @Id
    /*@GeneratedValue(strategy=GenerationType.IDENTITY)*/
    private Integer cod_servicio;
    private String nom_servicio;
    private Integer precio_servicio;
    private String tiempo_servicio;
    
    
   /* Relacion  con la tabla Cliente */

    /*@ManyToMany(mappedBy = "servicio")
    private List<Cliente> cliente;
    
    /* Relacion  con la tabla Reservaciones Atracciones */
    
   /* @OneToMany
    (mappedBy = "servicio")
    private List<Reservacionesatraciones> reservacionesatraciones;
    
    /* Relacion  con la tabla Reservaciones Eventos */
    
   /* @OneToMany
    (mappedBy = "servicio")
    private List<Reservacioneseventos> reservacioneseventos;
    */
}