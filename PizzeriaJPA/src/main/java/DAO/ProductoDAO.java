/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.Conexion;
import Persistencia.Producto;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

/**
 *
 * @author skevi
 */
public class ProductoDAO {
    
    private final Conexion conexion;

    public ProductoDAO() {
        this.conexion = new Conexion();
    }
    
    /**
     * Agrega un producto.
     * 
     * @param producto producto a agregar.
     */
    public void agregarProducto(Producto producto) {
        EntityManager em = conexion.crearConexion();
        EntityTransaction transaction = em.getTransaction();
        
        try {
            transaction.begin(); // Inicia la transacción
            em.persist(producto); // Guarda el producto en la base de datos
            transaction.commit(); // Confirma la transacción
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback(); // Reversa la transacción en caso de error
            }
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion(em); // Cierra el EntityManager
        }
    }

    /**
     * Busca productos cuyo precio sea mayor al del precio dado en el parámetro.
     * 
     * @param precio precio mínimo.
     * @return lista de productos cuyo precio es mayor que el especificado.
     */
    public List<Producto> buscarPorPrecio(BigDecimal precio) {
        EntityManager em = conexion.crearConexion();
        List<Producto> productos = null;

        try {
            String jpql = "SELECT p FROM Producto p WHERE p.precio > :precio";
            TypedQuery<Producto> query = em.createQuery(jpql, Producto.class);
            query.setParameter("precio", precio);
            productos = query.getResultList(); // Ejecuta la consulta y obtiene la lista de productos
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion(em); // Cierra el EntityManager
        }

        return productos; // Retorna la lista de productos
    }
    
}
