package es.mariasoria.service.db;

import es.mariasoria.model.Categoria;
import es.mariasoria.repository.CategoriasRepository;
import es.mariasoria.service.CategoriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class CategoriasServiceJpa implements CategoriasService {

    @Autowired
    private CategoriasRepository categoriasRepo;

    @Override
    public void guardar(Categoria categoria) {
        categoriasRepo.save(categoria);
    }

    @Override
    public List<Categoria> buscarTodas() {
        return categoriasRepo.findAll();
    }

    @Override
    public Categoria buscarPorId(Integer idCategoria) {
        Optional<Categoria> optional = categoriasRepo.findById(idCategoria);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public void eliminar(Integer idCategoria) {
        categoriasRepo.deleteById(idCategoria);
    }

    @Override
    public Page<Categoria> buscarTodas(Pageable page) {
        return categoriasRepo.findAll(page);
    }
}
