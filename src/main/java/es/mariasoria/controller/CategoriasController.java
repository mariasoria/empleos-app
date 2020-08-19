package es.mariasoria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import es.mariasoria.model.Categoria;
import es.mariasoria.service.CategoriasService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value="/categorias")
public class CategoriasController {

    @Autowired
    private CategoriasService catService;

    //@GetMapping("/index")
    @RequestMapping(value="/index", method= RequestMethod.GET)
    public String mostrarIndex(Model model) {
        // Renderizar el listado de Categorias (listCategorias.html)
        List<Categoria> lista = catService.buscarTodas();
        model.addAttribute("listaCategorias", lista);
        // Configurar la URL del botón para crear una Categoría
        return "categorias/listCategorias";
    }

    //@GetMapping("/create")
    @RequestMapping(value="/create", method=RequestMethod.GET)
    public String crear(Categoria categoria) {
        return "categorias/formCategoria";
    }

    //@PostMapping("/save")
    @RequestMapping(value="/save", method=RequestMethod.POST)
    // los valores entre "" de RequestParam deben coincidir con los atributos name del formulario html
    public String guardar(Categoria nuevaCategoria, BindingResult result, RedirectAttributes attributes) {
        //Validar errores de Data Binding y mostrarlos al usuario en caso de haber
        if (result.hasErrors()){
            System.out.println("Ha ocurrido algun error");
            return "categorias/formCategoria";
        }
        //Guardar el objeto Categoria a traves de la clase de servicio
        catService.guardar(nuevaCategoria);
        //Mostrar al usuario mensaje de confirmacion de registro guardado
        attributes.addFlashAttribute("message", "La categoría se ha guardado con éxito");
        return "redirect:/categorias/index";
    }



}
