package es.mariasoria.service.db;

import es.mariasoria.model.Usuario;
import es.mariasoria.repository.UsuariosRepository;
import es.mariasoria.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosServiceJpa implements UsuariosService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Override
    public void guardar(Usuario usuario) {
        usuariosRepository.save(usuario);
    }

    @Override
    public void eliminar(Integer idUsuario) {
        usuariosRepository.deleteById(idUsuario);
    }

    @Override
    public List<Usuario> buscarTodos() {
        return usuariosRepository.findAll();
    }
}
