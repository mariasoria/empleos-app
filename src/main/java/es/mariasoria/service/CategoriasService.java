package es.mariasoria.service;

import java.util.List;
import es.mariasoria.model.Categoria;
import org.springframework.stereotype.Service;

public interface CategoriasService {

	// Guardar las categorías en una lista (LinkedList)
	void guardar(Categoria categoria);

	// Devuelev la lista de categorias
	List<Categoria> buscarTodas();

	//Recibe el id de una categoria y devuelve la categoria en caso de encontrarla
	Categoria buscarPorId(Integer idCategoria);

	// Ejercicio: Implementar método
	void eliminar(Integer idCategoria);
}
