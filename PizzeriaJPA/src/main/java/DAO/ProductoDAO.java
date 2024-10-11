/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author caarl
 */
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import persistencia.Producto;


public class ProductoDAO {
    private EntityManagerFactory emf;
    private EntityManager em;

    public ProductoDAO() {
        emf = Persistence.createEntityManagerFactory("pu-Pizzeria");
        em = emf.createEntityManager();
    }

    public void agregar(Producto producto) {
        try {
            em.getTransaction().begin();
            em.persist(producto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        }
    }

    public void actualizar(Producto producto) {
        try {
            em.getTransaction().begin();
            em.merge(producto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        }
    }

    public void eliminar(Long id) {
        try {
            Producto producto = em.find(Producto.class, id);
            if (producto != null) {
                em.getTransaction().begin();
                em.remove(producto);
                em.getTransaction().commit();
            }
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        }
    }

    public Producto buscarPorId(Long id) {
        return em.find(Producto.class, id);
    }

    public List<Producto> buscarTodos() {
        TypedQuery<Producto> query = em.createQuery("SELECT p FROM producto p", Producto.class);
        return query.getResultList();
    }
    
    public List<Producto> buscarPrecioMayorA250() {
    return em.createQuery("SELECT p FROM Producto p WHERE p.precio > 250", Producto.class)
             .getResultList();
}

    public void cerrar() {
        em.close();
        emf.close();
    }
}
