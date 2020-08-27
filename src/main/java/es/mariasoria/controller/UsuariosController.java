package es.mariasoria.controller;

import es.mariasoria.model.Usuario;
import es.mariasoria.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired
	private UsuariosService usuariosService;
    
    @GetMapping("/index")
	public String mostrarIndex(Model model) {
    	List<Usuario> list = usuariosService.buscarTodos();
		model.addAttribute("usuarios", list);
    	return "usuarios/listUsuarios";
	}
    
    @GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {		    	
    	System.out.println("Borrando registro: " + idUsuario);
    	usuariosService.eliminar(idUsuario);
    	attributes.addFlashAttribute("msg", "El usuario fue eliminado correctamente");
		return "redirect:/usuarios/index";
	}
}
