package es.mariasoria.model;

import javax.persistence.*;
import java.util.Date;

/****
 *
 * representa la información publicada sobre una oferta de trabajo
 */

@Entity
@Table(name="Vacantes")
public class Vacante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // identifica la vacante (en nuestra BD)
    private String nombre; // especifica el titulo de la vacante
    private String descripcion; // descripción de la vacante
    private Date fecha; // fecha publicacion vacante
    private Double salario; // salario aprox de la vacante
    private Integer destacado; // 1 = destacado
    private String imagen="no-image.png";
    private String estatus;
    private String detalles;

    //@Transient
    // Creamos una relacion one to one entre vacantes y categorias
    // especificamos la columna con la que se marca la relacion entre ambas columnas
    // que sera el nombre de la clave foranea (chequear el pdf tema 8, pag 5)
    @OneToOne
    @JoinColumn(name="idCategoria")
    private Categoria categoria;


    public void reset(){
        this.imagen=null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Integer getDestacado() {
        return destacado;
    }

    public void setDestacado(Integer destacado) {
        this.destacado = destacado;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    // sobreescribimos este método xq queremos especificar cómo queremos mostrar cada cosa
    @Override
    public String toString() {
        return "Vacante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha=" + fecha +
                ", salario=" + salario +
                ", destacado=" + destacado +
                ", imagen='" + imagen + '\'' +
                ", estatus='" + estatus + '\'' +
                ", detalles='" + detalles + '\'' +
                ", categoria=" + categoria +
                '}';
    }
}
