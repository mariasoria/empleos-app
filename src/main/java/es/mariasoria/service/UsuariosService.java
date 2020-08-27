package es.mariasoria.service;

import es.mariasoria.model.Usuario;

import java.util.List;

public interface UsuariosService {

    void guardar(Usuario usuario);

    void eliminar(Integer idUsuario);

    List<Usuario> buscarTodos();
}
