package es.mariasoria.service;

import es.mariasoria.model.Vacante;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VacantesService {

    List<Vacante> buscarTodas();

    Vacante buscarPorId (Integer idVacante);

    // agrega una vacante
    void guardar (Vacante vacante);

    List<Vacante> buscarDestacadas();

    // elimina una vacante
    void eliminar(Integer idVacante);

    List<Vacante> buscarByExample(Example<Vacante> example);

    Page<Vacante> buscarTodas(Pageable page);
}

