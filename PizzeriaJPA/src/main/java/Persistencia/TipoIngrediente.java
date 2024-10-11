/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;



@Entity
@Table(name = "tipos")
public class TipoIngrediente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @OneToMany(mappedBy = "tipo")
    private List<Ingrediente> ingredientes = new ArrayList<>();

    public TipoIngrediente() {
    }

    public TipoIngrediente(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoIngrediente(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.descripcion);
        hash = 71 * hash + Objects.hashCode(this.ingredientes);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipoIngrediente other = (TipoIngrediente) obj;
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.ingredientes, other.ingredientes);
    }

    @Override
    public String toString() {
        return "TipoIngrediente{" + "id=" + id + ", descripcion=" + descripcion + 
               ", ingredientes=" + ingredientes + '}';
    }
}
