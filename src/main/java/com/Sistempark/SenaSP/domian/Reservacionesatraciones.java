/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Sistempark.SenaSP.domian;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table(name = "reservacionesatraciones")
public class Reservacionesatraciones {
  
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer codigoreservacion;
    private String atracion;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private Time hora;
    private String personas;
    
    /* Relacion  con la tabla Servicio */
   /* @ManyToOne
    @JoinColumn(name="cod_servicio")
    private Servicio servicio;
    
   /* Relacion  con la tabla Empleado */
    @ManyToMany(mappedBy = "reservacionesatraciones")
    private List<Empleado> empleado;


    
}
