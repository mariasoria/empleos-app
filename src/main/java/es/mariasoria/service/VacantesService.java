package es.mariasoria.service;

import es.mariasoria.model.Vacante;

import java.util.List;

public interface VacantesService {

    List<Vacante> buscarTodas();

    Vacante buscarPorId (Integer idVacante);

    // agrega una vacante
    void guardar (Vacante vacante);

    List<Vacante> buscarDestacadas();

    // elimina una vacante
    void eliminar(Integer idVacante);
}

