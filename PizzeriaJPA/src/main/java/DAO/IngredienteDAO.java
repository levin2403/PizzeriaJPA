/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.Conexion;
import Persistencia.Ingrediente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

/**
 *
 * @author skevi
 */
public class IngredienteDAO {
    
    private final Conexion conexion;

    public IngredienteDAO() {
        this.conexion = new Conexion();
    }
    
/**
     * Añade un ingrediente.
     * 
     * @param ingrediente Ingrediente a agregar.
     */
    public void agregar(Ingrediente ingrediente) {
        EntityManager em = conexion.crearConexion();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin(); // Inicia la transacción
            em.persist(ingrediente); // Persiste el ingrediente en la base de datos
            transaction.commit(); // Confirma la transacción
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback(); // Reversa la transacción en caso de error
            }
            e.printStackTrace();
        } finally {
            em.close(); // Cierra el EntityManager
        }
    }

    /**
     * Busca ingredientes cuyo nombre comience o termine por la letra "A".
     * 
     * @return Lista de ingredientes.
     */
    public List<Ingrediente> buscar() {
        EntityManager em = conexion.crearConexion();
        List<Ingrediente> ingredientes = null;

        try {
            // Consulta JPQL para buscar ingredientes cuyo nombre comience o termine con "A"
            String jpql = "SELECT i FROM Ingrediente i WHERE i.nombre LIKE 'A%' OR i.nombre LIKE '%A'";
            TypedQuery<Ingrediente> query = em.createQuery(jpql, Ingrediente.class);
            ingredientes = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close(); // Cierra el EntityManager
        }

        return ingredientes;
    }
    
}
