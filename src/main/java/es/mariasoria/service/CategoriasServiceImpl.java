package es.mariasoria.service;

import es.mariasoria.model.Categoria;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CategoriasServiceImpl implements CategoriasService {

    private List<Categoria> lista = null;

    public CategoriasServiceImpl(){
        lista = new LinkedList<Categoria>();

        //Categoria 1 => Ventas
        Categoria categoria1 = new Categoria();
        categoria1.setId(1);
        categoria1.setNombre("Ventas");
        categoria1.setDescripcion("Descripción categoria Ventas");

        //Categoria 2 => Contabilidad
        Categoria categoria2 = new Categoria();
        categoria2.setId(2);
        categoria2.setNombre("Contabilidad");
        categoria2.setDescripcion("Descripción categoria Contabilidad");

        //Categoria 3 => Transporte
        Categoria categoria3 = new Categoria();
        categoria3.setId(3);
        categoria3.setNombre("Transporte");
        categoria3.setDescripcion("Descripción categoria Transporte");

        //Categoria 4 => Informatica
        Categoria categoria4 = new Categoria();
        categoria4.setId(4);
        categoria4.setNombre("Informatica");
        categoria4.setDescripcion("Descripción categoria Informatica");

        //Categoria 5 => Construccion
        Categoria categoria5 = new Categoria();
        categoria5.setId(5);
        categoria5.setNombre("Construccion");
        categoria5.setDescripcion("Descripción categoria Construccion");

        //Categoria 5 => Construccion
        Categoria categoria6 = new Categoria();
        categoria6.setId(6);
        categoria6.setNombre("Marketing");
        categoria6.setDescripcion("Descripción categoria Marketing");

        lista.add(categoria1);
        lista.add(categoria2);
        lista.add(categoria3);
        lista.add(categoria4);
        lista.add(categoria5);
        lista.add(categoria6);
    }

    // Guardar las categorías en una lista (LinkedList)
    @Override
    public void guardar(Categoria categoria) {
        lista.add(categoria);
    }

    // Devuelve la lista de categorias
    @Override
    public List<Categoria> buscarTodas() {
        return lista;
    }

    //Recibe el id de una categoria y devuelve la categoria en caso de encontrarla
    @Override
    public Categoria buscarPorId(Integer idCategoria) {
        for(Categoria cat : lista){
            if (cat.getId() == idCategoria){
                return cat;
            }
        }
        return null;
    }
}
