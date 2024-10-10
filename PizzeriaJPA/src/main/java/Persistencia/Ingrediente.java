/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.io.Serializable;
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


    public Ingrediente() {
    }

    public Ingrediente(int cantidad, String nombre, TipoIngrediente tipo, Long id) {
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.tipo = tipo;
        this.id = id;
    }

    public Ingrediente(int cantidad, String nombre, TipoIngrediente tipo) {
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.tipo = tipo;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ingrediente)) {
            return false;
        }
        Ingrediente other = (Ingrediente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ingrediente{" + "cantidad=" + cantidad + ", nombre=" + nombre + ", tipo=" + tipo + ", id=" + id + '}';
    }
    
    
}

