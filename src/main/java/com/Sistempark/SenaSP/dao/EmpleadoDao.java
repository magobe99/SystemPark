/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Sistempark.SenaSP.dao;

import com.Sistempark.SenaSP.domian.Empleado;
import org.springframework.data.repository.CrudRepository;


public interface EmpleadoDao extends CrudRepository<Empleado, Integer> {
    
}
