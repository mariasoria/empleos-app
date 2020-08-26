package es.mariasoria.repository;

import es.mariasoria.model.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacantesRepository extends JpaRepository<Vacante, Integer> {

    List<Vacante> findByEstatus (String estatus);

    List<Vacante> findByDestacadoAndEstatusOrderByIdDesc (Integer destacado, String estatus);

    // Buscamos las vacantes con un salario definido entre s1 y s2 (rango)
    List <Vacante> findBySalarioBetweenOrderBySalarioDesc(double s1, double s2);

    // Buscamos las vacantes por varios estatus. Se le pasará un arreglo como parámetro
    List <Vacante> findByEstatusIn(String[] estatus);


}
