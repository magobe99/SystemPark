/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Sistempark.SenaSP.dao;

import com.Sistempark.SenaSP.domian.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface UsuarioDao extends CrudRepository<Usuario, Integer> {
     public Usuario findByCorreoAndClave(String correo, String Clave);
     
      @Query(value = "select * from usuario where correo=?1", nativeQuery = true)
    public Usuario findByCorreo(String correo);
    
}
