/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Sistempark.SenaSP.SistemParkController;
import com.Sistempark.SenaSP.domian.MailServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SendMailUserController {

    @Autowired
    private MailServiceUsuario mailService;

    @GetMapping("envioCorreoUser")
    public String envioCorreoUser() {
        return "envioCorreoUser";
    }

    @PostMapping("/sendEmailUsuario")
    public String sendEmailUsuario(@RequestParam("to") String to,@RequestParam("subject") String subject, @RequestParam("body") String body) {
       
        String message = body;
        mailService.sendEmailUsuario("Sistempark22@gmail.com", to , subject, message);

        return "envioCorreoUser";
    }
}
