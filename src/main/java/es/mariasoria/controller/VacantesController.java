package es.mariasoria.controller;

import es.mariasoria.model.Vacante;
import es.mariasoria.service.CategoriasService;
import es.mariasoria.service.VacantesService;
import es.mariasoria.util.Utileria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping ("/vacantes")
public class VacantesController {

    @Value("${empleosapp.ruta.imagenes}")
    private String ruta;

    @Autowired
    private VacantesService serviceVacantes;

    @Autowired
    private CategoriasService serviceCategorias;

    @GetMapping("/index")
    public String mostrarIndex (Model model){
        //TODO 1. Obtener todas las vacantes (recuperarlas con la clase de servicio)
        List<Vacante> vacantes = serviceVacantes.buscarTodas();
        //TODO 2. Agregar al model el listado de vacantes
        model.addAttribute("vacantes", vacantes);
        //TODO 3. Renderizar las vacantes en la vista (integrar el archivo template de los archivos descargados: empleos/listVacantes.html)
        //System.out.println(vacantes);
        //TODO 4. Agregar al menu una opcion llamada "vacantes" configurando la URL "vacantes/index"
        return "vacantes/listVacantes";
    }

    @GetMapping("/create")
    public String crear(Vacante vacante, Model model) {
        return "vacantes/formVacante";
    }

    @PostMapping("/save")
    public String guardar(Vacante vacante, BindingResult result, RedirectAttributes attributes, @RequestParam("archivoImagen") MultipartFile multiPart ){
        if (result.hasErrors()) {
            for (ObjectError error: result.getAllErrors()){
                System.out.println("Ocurrio un error: " + error.getDefaultMessage());
            }
            return "vacantes/formVacante";
        }
        if (!multiPart.isEmpty()) {
            //String ruta = "/empleos/img-vacantes/"; // Linux/MAC
            //String ruta = "c:/empleos/img-vacantes/"; // Windows
            String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
            if (nombreImagen != null){ // La imagen si se subio
                // Procesamos la variable nombreImagen
                vacante.setImagen(nombreImagen);
            }
        }
        // cuando llegue el objeto vacante al controlador
        // se agregara directamente a nuestra lista
        serviceVacantes.guardar(vacante);
        attributes.addFlashAttribute("msg", "Registro guardado");
        System.out.println("Vacante: " + vacante);
        return "redirect:/vacantes/index";
    }

    // para recibir la info de un formulario uso @RequestParam("")
    @GetMapping ("/delete/{id}")
    public String eliminar (@PathVariable("id") int idVacante, RedirectAttributes attributes){
        System.out.println("Borrando vacante con id: " + idVacante);
        serviceVacantes.eliminar(idVacante);
        attributes.addFlashAttribute("msg", "La vacante fue eliminada correctamente");
        return "redirect:/vacantes/index";
    }

    @GetMapping ("/edit/{id}")
    public String editar(@PathVariable ("id") int idVacante, Model model){
        Vacante vacante = serviceVacantes.buscarPorId(idVacante);
        model.addAttribute("vacante", vacante);
        return "vacantes/formVacante";
    }

    @GetMapping ("/view/{id}")
    public String verDetalle(@PathVariable("id") int idVacante, Model model) {

        Vacante vacante = serviceVacantes.buscarPorId(idVacante);
        System.out.println("Vacante: " + vacante);
        model.addAttribute("vacante", vacante);

        // Proximamente: Buscar los detalles de la vacante en la BD
        return "detalle";
    }

    @GetMapping(value = "/indexPaginate")
    public String mostrarIndexPaginado(Model model, Pageable page) {
        Page<Vacante> lista = serviceVacantes.buscarTodas(page);
        model.addAttribute("vacantes", lista);
        return "vacantes/listVacantes";
    }


    // Para que coja la fecha desde el formulario correctamente
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }


    @ModelAttribute // Permite añadir al model atributos comunes para todos los métodos que los necesiten de este controlador
    public void setGenericos (Model model){
        model.addAttribute("categorias", serviceCategorias.buscarTodas());
    }

}
