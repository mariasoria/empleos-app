package es.mariasoria.controller;

import es.mariasoria.model.Perfil;
import es.mariasoria.model.Usuario;
import es.mariasoria.model.Vacante;
import es.mariasoria.service.UsuariosService;
import es.mariasoria.service.VacantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private VacantesService vacantesService;

    @Autowired
    private UsuariosService usuariosService;


    @GetMapping("/signup")
    public String registrarse(Usuario usuario) {
        return "formRegistro";
    }

    @PostMapping("/signup")
    public String guardarRegistro(Usuario usuario, RedirectAttributes attributes) {
        // Es necesario definir primero el estatus y la fecha de registro
        usuario.setEstatus(1); // activado por defecto
        usuario.setFechaRegistro(new Date()); // Fecha actual del servidor

        // Creamos el perfil para el usuario nuevo
        Perfil perfil = new Perfil();
        perfil.setId(3); // Perfil USUARIO
        usuario.agregar(perfil);

        // Guardamos el usuario en la BD
        usuariosService.guardar(usuario);
        System.out.println("usuario creado: " + usuario);
        attributes.addFlashAttribute("msg", "El usuario fue creado correctamente");
        return "redirect:/usuarios/index";
    }

    @GetMapping ("/tabla")
    public String mostrarTabla (Model model){
        List <Vacante> listaVacantes = vacantesService.buscarTodas();
        model.addAttribute("vacantes", listaVacantes);
        return "tabla";
    }

    @GetMapping ("/detalle")
    public String mostrarDetalle(Model model) {
        Vacante vacante = new Vacante();
        vacante.setNombre("Ingeniero de comunicaciones");
        vacante.setDescripcion("Se solicita ingeniero para dar soporte a intranet");
        vacante.setFecha(new Date());
        vacante.setSalario(9700.0);
        model.addAttribute("vacante", vacante);
        return "detalle";
    }

    @GetMapping ("/listado")
    public String mostrarListado (Model model) {
        List<String> lista = new LinkedList<String>();
        lista.add("Ingeniero de Sistemas");
        lista.add("Auxiliar de Contabilidad");
        lista.add("Vendedor");
        lista.add("Arquitecto");

        model.addAttribute("empleos", lista);
        return "listado";
    }

    @GetMapping("/")
    public String mostrarHome(Model model) {
        //List <Vacante> listaVacantes = serviceVacantes.buscarTodas();
        //model.addAttribute("vacantes", listaVacantes);
        // estan comentados xq el metodo setGenericos ya carga las vacantes en el modelo
        return "home";
    }

    @ModelAttribute
    public void setGenericos(Model model){
        model.addAttribute("vacantes", vacantesService.buscarDestacadas());
    }



}
