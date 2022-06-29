/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Sistempark.SenaSP.SistemParkController;

import com.Sistempark.SenaSP.dao.AccesosDao;
import com.Sistempark.SenaSP.dao.Accesos_UsuarioDao;
import com.Sistempark.SenaSP.dao.ClienteDao;
import com.Sistempark.SenaSP.dao.EmpleadoDao;
import com.Sistempark.SenaSP.dao.PermisoDao;
import com.Sistempark.SenaSP.dao.ReservacionesatracionesDao;
import com.Sistempark.SenaSP.dao.ReservacioneseventosDao;
import com.Sistempark.SenaSP.dao.RolesDao;
import com.Sistempark.SenaSP.dao.ServicioDao;
import com.Sistempark.SenaSP.dao.UsuarioDao;
import com.Sistempark.SenaSP.domian.Permiso;
import com.Sistempark.SenaSP.domian.Servicio;
import com.Sistempark.SenaSP.domian.Accesos_Usuario;
import com.Sistempark.SenaSP.domian.Reservacionesatraciones;
import com.Sistempark.SenaSP.domian.Reservacioneseventos;
import com.Sistempark.SenaSP.domian.UploadFile;
import com.Sistempark.SenaSP.domian.Usuario;
import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class SistemParkController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private ReservacionesatracionesDao reservacionesatracionesDao;

    @Autowired
    private ReservacioneseventosDao reservacioneseventoDao;
     
    @Autowired
    private PermisoDao permisoDao;
    
    @Autowired
    private ServicioDao servicioDao;

    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private Accesos_UsuarioDao accesos_UsuarioDao;
    
    /*
    @Autowired
    private ClienteDao clienteDao;
     
    @Autowired
    private AccesosDao accesoDao;
      
  
    @Autowired
    private RolesDao rolesDao;
    
    
    @Autowired
    private EmpleadoDao empleadoDao;
    
     
       
    
    /*Pagina de inicio
    @GetMapping("/")
    public String index() {

        return "index";
    }*/
    private String idusuario;
   
    
    
    
    /*Pagina de inicio*/
    @GetMapping("/")
    public String index() {

        return "index_2";
    }
    
    /*Pagina de inicio formulario de contacto*/
    @GetMapping("/contacform")
    public String contacform() {

        return "contacformu";
    }
    
    
    /*Pagina de Login*/
    @GetMapping("/login")
    public String login(Usuario usuario) {

        return "LoginRegistro";
    }
    
    /*Guarda datos del Usuario*/
    @PostMapping("/guardarUsuario")
    public String guardarCliente(Usuario usuario) {
        usuarioDao.save(usuario);
        return "redirect:index_2";
    }

    /*Inicio de sesion con la bases de datos*/
    @PostMapping("/IniciarSesion")
    public String iniciarSesion(Usuario usuario, Model model) {
        Usuario validar = usuarioDao.findByCorreoAndClave(usuario.getCorreo(), usuario.getClave());

        if (validar == null) {
            return "redirect:/";
        } else if (validar.getRoles().get(0).getNomAccesos().equals("Administrador")) {
            List<Reservacionesatraciones> lstReservaciones;
            lstReservaciones = (List<Reservacionesatraciones>) reservacionesatracionesDao.findAll();
            model.addAttribute("lstReservaciones", lstReservaciones);
            return "DashboarAdmin";
        } else if (validar.getRoles().get(0).getNomAccesos().equals("Cliente")) {
            
             List<Reservacionesatraciones> lstReservaciones;
             lstReservaciones = (List<Reservacionesatraciones>) reservacionesatracionesDao.findAll();
             model.addAttribute("lstReservaciones", lstReservaciones);
            
            return "dashboarCliente";

        } else if (validar.getRoles().get(0).getNomAccesos().equals("Empleado")) {
            
            List<Permiso> lstpermi;
            lstpermi = (List<Permiso>) permisoDao.findAll();
            model.addAttribute("lstpermi", lstpermi);

            
            return "dashboardEmple";
        }
        else{

             return "redirect:/login";
        }

    }
    
    

    /*Pagina de Fomulario para recuperar la contraseña*/
    @GetMapping("/FormularioC")
    public String contra() {

        return "recuperacionContra";
    }

   

    /*Pagina de Administrador nueva  */
    @GetMapping("/admin")
    public String admin(Model model) {
        
        List<Reservacionesatraciones> lstReservaciones;
        lstReservaciones = (List<Reservacionesatraciones>) reservacionesatracionesDao.findAll();
        model.addAttribute("lstReservaciones", lstReservaciones);

        return "DashboarAdmin";
    }
    
    
    /*Dashboard de Admin*/
    @GetMapping("/administrador")
    public String indexadministrador(Model model) {

        List<Reservacionesatraciones> lstReservaciones;
        lstReservaciones = (List<Reservacionesatraciones>) reservacionesatracionesDao.findAll();
        model.addAttribute("lstReservaciones", lstReservaciones);

        return "dashboardAdmin";
    }

    
    
    /*Pagina de Reservacion atracción Admin nueva*/
    @GetMapping("/reservanAtra")
    public String Reservacion1(Reservacionesatraciones reservacionesatraciones) {

        return "dashAdminAtra";
    }

    /*Guarda datos del reservacion atracion admin*/
    @PostMapping("/guardarResAtra")
    public String guardarAtraRes(Reservacionesatraciones reservacionesatraciones) {
        reservacionesatracionesDao.save(reservacionesatraciones);
        return "redirect:admin";
    }

  
    
    /*Pagina de Modifcar Reservacion atracción Admin*/
    @GetMapping("/modiReserAtrac")
    public String ModReservacion2(Reservacionesatraciones reservacionesatraciones) {

        return "dashAdminAtraMod";
    }

   

    /*Boton de eliminar Reservacion Atrac */
    @RequestMapping("/eliminarReservacionClient")
    public String eliminarCliente(@RequestParam(name = "codigoreservacion", defaultValue = "0") Integer codigoreservacion) {
        reservacionesatracionesDao.deleteById(codigoreservacion);
        return "redirect:admin";
    }

    /*Boton de Modificar Reservacion Atrac */
    @RequestMapping("/modificarReservacionClient")
    public String modificarCliente(@RequestParam(name = "codigoreservacion", defaultValue = "0") Integer codigoreservacion, Model model) {
        Reservacionesatraciones reservacionesatraciones = reservacionesatracionesDao.findById(codigoreservacion).orElse(null);
        model.addAttribute("reservacionesatraciones", reservacionesatraciones);
        List<Reservacionesatraciones> lstReservaciones;
        lstReservaciones = (List<Reservacionesatraciones>) reservacionesatracionesDao.findAll();
        model.addAttribute("lstReservaciones", lstReservaciones);
        
        return "dashAdminAtraMod";
    }
    
    /*Guarda datos del reservacion atracion admin*/
    @PostMapping("/actualizarReservacion")
    public String guardarAtraResMod(Reservacionesatraciones reservacionesatraciones) {
        reservacionesatracionesDao.save(reservacionesatraciones);
        return "redirect:admin";
    }
    
    /*-----------------------------------------------------------------------------------------
    
  
    
     /*Pagina de Reservacion de Evento Admin NUEVA*/
    @GetMapping("/reservEventAdmin")
    public String ReservEventAdmin1(Reservacioneseventos reservacioneseventos) {

        return "dashAdminEvent";
    }

    /*Guarda datos del reservacion Eventos admin*/
    @PostMapping("/guardarResEvent")
    public String guardarEventResv(Reservacioneseventos reservacioneseventos) {
        reservacioneseventoDao.save(reservacioneseventos);
        return "redirect:admin";
    }
 
   
    
    /*Pagina de Modificar Reservacion de Evento Admin NUEVO*/
    @GetMapping("/modifEventAdmin")
    public String ModifEventAdmin1(Reservacioneseventos reservacioneseventos ) {

        return "dashAdminEventMod";
    }
    
    /*Boton de eliminar Reservacion Evento */
    @RequestMapping("/eliminarReservacionEvent")
    public String eliminarEvent(@RequestParam(name = "codigoreservacion", defaultValue = "0") Integer codigoreservacion) {
        reservacioneseventoDao.deleteById(codigoreservacion);
        return "redirect:consultEventAdmin";
    }

    /*Boton de Modificar Reservacion Evento */
    @RequestMapping("/modificarReservacionEvent")
    public String modificarEvent(@RequestParam(name = "codigoreservacion", defaultValue = "0") Integer codigoreservacion, Model model) {
        Reservacioneseventos reservacioneseventos = reservacioneseventoDao.findById(codigoreservacion).orElse(null);
        model.addAttribute("reservacioneseventos", reservacioneseventos);
       List<Reservacioneseventos> lstReservacionesEvent;
        lstReservacionesEvent = (List<Reservacioneseventos>) reservacioneseventoDao.findAll();
        model.addAttribute("lstReservacionesEvent", lstReservacionesEvent);
        return "dashAdminEventMod";
    }
    
    /*Guarda datos del reservacion Evento admin*/
    @PostMapping("/actualizarReservacionEvent")
    public String guardarEventResMod(Reservacioneseventos reservacioneseventos) {
        reservacioneseventoDao.save(reservacioneseventos);
        return "redirect:consultEventAdmin";
    }
    
    
    
    
    /*-----------------------------------------------------------------------------------------------------

 
    
     /*Pagina de Consualtar Reservacion atracción Admin*/
    @GetMapping("/consultaAtraAdmin")
    public String consultAtraAdmin1(Model model) {

        List<Reservacionesatraciones> lstReservaciones;
        lstReservaciones = (List<Reservacionesatraciones>) reservacionesatracionesDao.findAll();
        model.addAttribute("lstReservaciones", lstReservaciones);

        return "dashAdminAtraCon";
    }

    
    
    /*Pagina de Consualtar Reservacion Eventos Admin*/
    @GetMapping("/consultEventAdmin")
    public String consultEventAdmin1(Model model) {

        List<Reservacioneseventos> lstReservacionesEvent;
        lstReservacionesEvent = (List<Reservacioneseventos>) reservacioneseventoDao.findAll();
        model.addAttribute("lstReservacionesEvent", lstReservacionesEvent);

        return "dashAdminEventCon";
    }
    
    
    

    /*-----------------------------------------------------------------------------------------------------

    
    
    /*Pagina de formulario de servicio Admin NUEVO*/
    
    @GetMapping("/formularioSer")
    public String formServi1(Servicio servicio) {

        return "dashAdminServi";
    }
    
   /*Guarda datos del servicio admin*/
    @PostMapping("/guardarServ")
    public String guardarServ(Servicio servicio) {
        servicioDao.save(servicio);
        return "redirect:formularioSer";
    } 
    
    
     
    
    /*listado de servicios NUEVO */
    @GetMapping("/modifiServi")
    public String modifiServi1(Model model) {
        
        List<Servicio> lstServicio;
        lstServicio = (List<Servicio>) servicioDao.findAll();
        model.addAttribute("lstServicio", lstServicio);

        return "dashAdminServiCon";
    }
   
    
    /*Boton de eliminar Servicios */
    @RequestMapping("/eliminarServi")
    public String eliminarServi(@RequestParam(name = "cod_servicio", defaultValue = "0") Integer cod_servicio) {
        servicioDao.deleteById(cod_servicio);
        return "redirect:modifiServi";
    }
    
    
    
    /*Boton de Modificar Reservacion Servicio */
    @RequestMapping("/modificarServicio")
    public String modiservi(@RequestParam(name = "cod_servicio", defaultValue = "0") Integer cod_servicio, Model model) {
        Servicio servicio = servicioDao.findById(cod_servicio).orElse(null);
        model.addAttribute("servicio", servicio);
        List<Servicio> lstServicio;
        lstServicio = (List<Servicio>) servicioDao.findAll();
        model.addAttribute("lstServicio", lstServicio);
        return "dashAdminServiMod";
    }
  
     /*Guarda datos del Servicio */
    @PostMapping("/guardarModiSer")
    public String guardarModiSer(Servicio servicio) {
        servicioDao.save(servicio);
        return "redirect:admin";
    }
    
    
    /* Carga de datos por Excel*/
    
     @RequestMapping("/uploadFile")
    public String saveFileExcel(MultipartFile file, Model model) throws IOException, CsvValidationException {
        UploadFile upl = new UploadFile();
        List<Servicio> lstServicio = upl.guardarFile(file);

        this.servicioDao.saveAll(lstServicio);

        return "redirect:modifiServi";
    }  
    
    
    /*--------------------------------------------------------------------------------------------------------*/

    
  
    
     /*Pagina de envio de correos NUEVO */
    @GetMapping("/controlUsu")
    public String enviocorreo(Model model) {

        List<Usuario> lstUsuarios;
        lstUsuarios = (List<Usuario>) usuarioDao.findAll();
        model.addAttribute("lstUsuarios", lstUsuarios);

        return "envioCorreoUser";
    }
    
    
    
    
   /*Boton de eliminar Usuario */
    @RequestMapping("/eliminarUsuario")
    public String eliminarUsuario(@RequestParam(name = "idusuario", defaultValue = "0") Integer idusuario) {
        usuarioDao.deleteById(idusuario);
        return "redirect:administrador";
    }
    
     /*Boton de Modificar usuario */
    @RequestMapping("/modificaUsuario")
    public String modificarUsuario(@RequestParam(name = "idusuario", defaultValue = "0") Integer idusuario, Model model) {
        Usuario usuario = usuarioDao.findById(idusuario).orElse(null);
        model.addAttribute("usuario", usuario);
        List<Usuario> lstUsuarios;
        lstUsuarios = (List<Usuario>) usuarioDao.findAll();
        model.addAttribute("lstUsuarios", lstUsuarios);
        
        return "";
    }
    
    
    
   
    
     /*Pagina de Consultar los usuarios del sistema  */
    @GetMapping("/controlus")
    public String controlUs(Model model) {

        List<Usuario> lstUsuarios;
        lstUsuarios = (List<Usuario>) usuarioDao.findAll();
        model.addAttribute("lstUsuarios", lstUsuarios);

        return "dashAdminConUsu";
    }
    
    /*Guarda datos del Accesos_Usuario*/
    @PostMapping("/guardarAccesos")
    public String guardarCliente(Accesos_Usuario accesos_usuario) {
        accesos_UsuarioDao.save(accesos_usuario);
        return "redirect:controlus";
    }
    

    
    
    
    
    
    
   
    
  /*--------------------------------------------------------------------------------------------------------*/
  
    
    
    
    
    
    
    
   
    /*--------------------------------------------------------------------------------------------------------*/
 /*Dashboard Cliente*/
   
    
    /*Dashboard Cliente NUEVO*/
    @GetMapping("/cliente")
    public String DashCliente1(Model model) {

        List<Reservacionesatraciones> lstReservaciones;
        lstReservaciones = (List<Reservacionesatraciones>) reservacionesatracionesDao.findAll();
        model.addAttribute("lstReservaciones", lstReservaciones);

        return "dashboarCliente";
    }

   
    
     /*Pagina de Catalogo de Atra */
    @GetMapping("/catalogoAtra")
    public String CatalogoAtra() {

        return "dashClienCatAtra";
    }

   
    
    /*Pagina de Catalogo de Event */
    @GetMapping("/catalogoEvent")
    public String CatalogoEvent() {

        return "dashClienCatEvent";
    }
    
    /*-----------------------------------------------------------------------------------------------------
    
    

  
    
    /*Pagina de Reservaciones de Atra Cliente NUEVO*/
    @GetMapping("/resevAtraCli")
    public String resevAtraCli1(Reservacionesatraciones reservacionesatraciones) {

        return "dashClientAtraR";
    }
    
    /*Pagina de Reservaciones de Atra Cliente  */
     @PostMapping("/guardarResAtraCli")
    public String guardarAtraResCli(Reservacionesatraciones reservacionesatraciones) {
        reservacionesatracionesDao.save(reservacionesatraciones);
        return "redirect:cliente";
    }

   
    
     /*Pagina de Modficar Reservaciones de Atra Cliente  */
    @GetMapping("/resevAtraModCli")
    public String resevAtraModCli(Reservacionesatraciones reservacionesatraciones) {

        return "dashClientAtraRMod";
    }

    
    

    
      /*Boton de eliminar Reservacion Atrac Cliente */
    @RequestMapping("/eliminarReservacionAtraCli")
    public String eliminarClient(@RequestParam(name = "codigoreservacion", defaultValue = "0") Integer codigoreservacion) {
        reservacionesatracionesDao.deleteById(codigoreservacion);
        return "redirect:cliente";
    }

    /*Boton de Modificar Reservacion Atrac  Cliente*/
   @RequestMapping("/modificarReservacionAtraCli")
    public String modificarClient(@RequestParam(name = "codigoreservacion", defaultValue = "0") Integer codigoreservacion, Model model) {
        Reservacionesatraciones reservacionesatraciones = reservacionesatracionesDao.findById(codigoreservacion).orElse(null);
        model.addAttribute("reservacionesatraciones", reservacionesatraciones);
        List<Reservacionesatraciones> lstReservaciones;
        lstReservaciones = (List<Reservacionesatraciones>) reservacionesatracionesDao.findAll();
        model.addAttribute("lstReservaciones", lstReservaciones);
        return "dashClientAtraRMod";
    }
    
     /*Guarda datos del reservacion atracion admin*/
    @PostMapping("/actualizarReservacionAtraCli")
    public String guardarAtraResModCli(Reservacionesatraciones reservacionesatraciones) {
        reservacionesatracionesDao.save(reservacionesatraciones);
        return "redirect:cliente";
    }
    
    
    
    
    /*-----------------------------------------------------------------------------------------------------------------
    

   
    
    /*Pagina de Reservaciones de Event Cliente  */
    @GetMapping("/resevEventCli")
    public String resevEventCli(Reservacioneseventos reservacioneseventos) {

        return "dashClientEventR";
    }
    
    /*Guarda datos del reservacion Eventos Cliente*/
    @PostMapping("/guardarResEventCli")
    public String guardarEventResvCli(Reservacioneseventos reservacioneseventos) {
        reservacioneseventoDao.save(reservacioneseventos);
        return "redirect:cliente";
    }

    
    
     /*Pagina de Modificar Reservaciones de Event Cliente  */
    @GetMapping("/resevModEventCli")
    public String resevModEventCli(Reservacioneseventos reservacioneseventos) {

        return "dashClientEventRMod";
    }
    
    
    
      /*Boton de eliminar Reservacion Evento Cli */
    @RequestMapping("/eliminarReservacionEventCli")
    public String eliminarClientEvent(@RequestParam(name = "codigoreservacion", defaultValue = "0") Integer codigoreservacion) {
        reservacioneseventoDao.deleteById(codigoreservacion);
        return "redirect:cliente";
    }

    /*Boton de Modificar Reservacion Evento Cli */
   @RequestMapping("/modificarReservacionEventCli")
    public String modificarClientEvent(@RequestParam(name = "codigoreservacion", defaultValue = "0") Integer codigoreservacion, Model model) {
        Reservacioneseventos reservacioneseventos = reservacioneseventoDao.findById(codigoreservacion).orElse(null);
        model.addAttribute("reservacioneseventos", reservacioneseventos);
       List<Reservacioneseventos> lstReservacionesEvent;
        lstReservacionesEvent = (List<Reservacioneseventos>) reservacioneseventoDao.findAll();
        model.addAttribute("lstReservacionesEvent", lstReservacionesEvent);
        return "dashClientEventRMod";
    }
    
     /*Guarda datos del reservacion Event Cli*/
    @PostMapping("/actualizarReservacionEventCli")
    public String guardarEventResModCli(Reservacioneseventos reservacioneseventos) {
        reservacioneseventoDao.save(reservacioneseventos);
        return "redirect:cliente";
    }
    
    /*-------------------------------------------------------------------------------------------------------------------------

   
    
    /*Pagina de Consultar Reservaciones de Atra Cliente  Nuevo*/
    @GetMapping("/consulAtraCli")
    public String consulAtraCli(Model model) {

        List<Reservacionesatraciones> lstReservaciones;
        lstReservaciones = (List<Reservacionesatraciones>) reservacionesatracionesDao.findAll();
        model.addAttribute("lstReservaciones", lstReservaciones);

        return "dashClientAtraCon";
    }

   
    
      /*Pagina de Consultar Reservaciones de Event Cliente  */
    @GetMapping("/consulEventCli")
    public String consulEventCli(Model model) {

        List<Reservacioneseventos> lstReservacionesEvent;
        lstReservacionesEvent = (List<Reservacioneseventos>) reservacioneseventoDao.findAll();
        model.addAttribute("lstReservacionesEvent", lstReservacionesEvent);

        return "dashClientEventCon";
    }

   
    /*--------------------------------------------------------------------------------------------------------*/
 
    
    
    
    
    
    
  /*--------------------------------------------------------------------------------------------------------*/
  
   /*Pagina de formulario Cambio de contraseña */
    @GetMapping("/cambiarContraseña")
    public String formularioCambioContraseña(Model model, @RequestParam(name = "idusuario") String idusuario){
        model.addAttribute("idusuario", idusuario);
        this.idusuario = idusuario;
        return "cambiarContraseña";
    }

    
    /*Pagina de formulario Modificar Cambio de contraseña */
    @PostMapping("/ModificarContraseña")
    public String ModificarContra(Model model, @RequestBody MultiValueMap<String, String> formData) {
        String clave = formData.get("contrasena_usuario").get(0);
        String idusuario = this.idusuario;
        Usuario usuario = usuarioDao.findById(Integer.parseInt(idusuario)).orElse(null);
        usuario.setClave(clave);
        //usuario.setContrasena_usuario(usuario.getContrasena_usuario());
        usuarioDao.save(usuario);
        model.addAttribute("usuario", usuario);
        return "redirect:/";
    }

    
    
    

}
