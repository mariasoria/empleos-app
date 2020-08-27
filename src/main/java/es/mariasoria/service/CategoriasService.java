package es.mariasoria.service;

import java.util.List;
import es.mariasoria.model.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

public interface CategoriasService {

	// Guardar las categorías en una lista (LinkedList)
	void guardar(Categoria categoria);

	// Devuelve la lista de categorias
	List<Categoria> buscarTodas();

	//Recibe el id de una categoria y devuelve la categoria en caso de encontrarla
	Categoria buscarPorId(Integer idCategoria);

	void eliminar(Integer idCategoria);

	Page<Categoria> buscarTodas(Pageable page);
}
