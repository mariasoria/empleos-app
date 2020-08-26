package es.mariasoria.model;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="Usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String email;
    private String username;
    private String password;
    private Integer estatus;
    private Date fechaRegistro;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="UsuarioPerfil",
            joinColumns = @JoinColumn(name="idUsuario"),
            inverseJoinColumns = @JoinColumn(name="idPerfil"))
    private List<Perfil> perfiles;

    // Este método nos permitirá añadir perfiles.
    // En caso de no haber ninguno ya, creará la lista
    // sino, lo añadirá a la ya creada
    // Con esta funcion cada vez que se cree un usuario, automáticam+ se le subirán todos los
    // perfiles ya creados gracias al @ManyToMany y al FetchType.EAGER
    public void agregar(Perfil tempPerfil){
        if(perfiles == null){
            perfiles = new LinkedList<Perfil>();
        }
        perfiles.add(tempPerfil);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<Perfil> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }

}
