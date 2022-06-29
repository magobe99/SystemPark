
package com.Sistempark.SenaSP.SistemParkController;

import com.Sistempark.SenaSP.dao.UsuarioDao;
import com.Sistempark.SenaSP.domian.Usuario;
import com.Sistempark.SenaSP.servicioMail.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping
public class EnviarEmailController {
 @Autowired
    SendMailService sendMailService;
 @Autowired
   UsuarioDao usuario;
    
    @PostMapping("/enviaremail")
	public String enviaremail(@RequestParam("email") String correo /*,
			@RequestParam("subject")String subject,@RequestParam("body") String body*/) {	
                Usuario usuario1 = usuario.findByCorreo(correo.toLowerCase());
		String mensaje= sendMailService.plantillaCambioContra().
                              replace("URL_CAMBIO_CONTRASENA", "https://systempark.herokuapp.com/cambiarContraseña?idusuario="+usuario1.getIdusuario()).
                              replace("nom_usuario", usuario1.getNom_usuario()); //body + "\n\n  correo de contacto;" +subject+"\n asunto"  + "\n email" + correo;
		String subject= "Cambio de contraseña usuario " + usuario1.getNom_usuario();
                sendMailService.enviaremail("Sistempark22@gmail.com", correo, subject, mensaje);
		return "index_2";
	}
        
        
        
        
        
}