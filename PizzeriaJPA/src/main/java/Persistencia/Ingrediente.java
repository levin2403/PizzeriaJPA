/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author skevi
 */
@Entity
@Table(name="Ingredientes")
public class Ingrediente implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name= "id_ingrediente")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private int cantidad;
    private String nombre;
    
    @ManyToOne
    @JoinColumn(name= "id_tipo",nullable = false)
    private TipoIngrediente tipo;

    @ManyToMany(mappedBy="ingredientes")
    List<Producto> productos;

    public Ingrediente() {
    }

    public Ingrediente(int cantidad, String nombre, TipoIngrediente tipo, 
            List<Producto> productos) {
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.tipo = tipo;
        this.productos = productos;
    }

    public Ingrediente(Long id, int cantidad, String nombre, 
            TipoIngrediente tipo, List<Producto> productos) {
        this.id = id;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.tipo = tipo;
        this.productos = productos;
    }
    
    

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoIngrediente getTipo() {
        return tipo;
    }

    public void setTipo(TipoIngrediente tipo) {
        this.tipo = tipo;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
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
        final Ingrediente other = (Ingrediente) obj;
        if (this.cantidad != other.cantidad) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        return Objects.equals(this.productos, other.productos);
    }

    @Override
    public String toString() {
        return "Ingrediente{" + "id=" + id + ", cantidad=" + cantidad + 
                ", nombre=" + nombre + ", tipo=" + tipo + ", productos=" + 
                productos + '}';
    }
    
    
}

